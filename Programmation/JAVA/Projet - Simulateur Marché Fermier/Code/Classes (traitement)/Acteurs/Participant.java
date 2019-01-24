package marche.traitement.Classes.Acteurs;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Offre;
import marche.traitement.Classes.Systeme.Offres.Vente;
import marche.traitement.Classes.Systeme.Taxes.Taxe;

import java.util.*;

/**
 * Classe correspondant aux Participants
 */
public abstract class Participant {

    /**
     * Solde du Participant
     */
    private double solde;

    /**
     * Gain du participant
     */
    private double gain;

    /**
     * Produits du Participant
     */
    private List<ProduitFermier> mesProduits = new ArrayList<ProduitFermier>();

    /**
     * Taxe du Participant
     */
    private Taxe maTaxe;

    /**
     * Offres proposées par le Participant
     */
    private List<Offre> mesOffres = new ArrayList<Offre>();

    /**
     * Marche sur lequel est situé la Participant
     */
    private Marche monMarche;

    /**
     * Constructeur par défaut
     */
    protected Participant() {
        this.solde = 0;
        this.gain = 0;
        this.maTaxe = null;
    }

    /**
     * Notifie selon une offre
     * @param offre Offre à notifier
     */
    public void notifier(Offre offre) {
        System.out.println("Notification des Participants");
        offre.notifierParticipant();
    }

    /**
     * Proposer la vente d'un produit
     * @param offre Offre à proposer
     */
    public void proposerOffreVente(Vente offre) {
        if(offre.getProduit() != null && offre.getQuantite() > 0 && offre.getPrixUnite() > 0) {
            offre.setParticipant(this);
            getMonMarche().getMonControleur().validerOffreVente(offre);
        }
        else System.out.println("Offre invalide !");
    }

    /**
     * Proposer l'achat d'un produit
     * @param offre Offre à acheter
     */
    public void proposerOffreAchat(Achat offre){
        if(offre.getQuantite() > 0){
            offre.setParticipant(this);
            getMonMarche().getMonControleur().validerOffreAchat(offre);
        }
    }

    /**
     * Envoi d'un produit à un acquereur et retrait au vendeur
     * @param produitFermier Produit à envoyer
     * @param quantite Quantité à envoyer
     * @param acheteur Acheteur du produit
     */
    protected void envoyerProduit(ProduitFermier produitFermier, int quantite, Participant acheteur){
        if(getStockProduit(produitFermier) >= quantite){
            for(;quantite > 0;--quantite) {
                ProduitFermier sauvegardeProduit = produitFermier;
                getMesProduits().remove(produitFermier);
                sauvegardeProduit.setMonAcquereur(acheteur);
                acheteur.acquerirProduit(sauvegardeProduit);
            }
        }
    }

    /**
     * Encaisser l'argent
     * @param acheteur Participant ayant acheté
     * @param montant Montant à créditer
     */
    protected void encaisserArgent(Participant acheteur, double montant){
        acheteur.envoyerArgent(montant);
        this.solde += montant;
        this.gain += montant;
    }

    /**
     * Force une vente
     * @param offreVente Offre devant être forcée
     */
    public void forcerVente(Vente offreVente){
        if(getMonMarche().getMonControleur().getMonCatalogue().verifierSiOffreAchatPourVente(offreVente))
            getMonMarche().getMonControleur().effectuerTransaction(getMonMarche().getMonControleur().choisirAcheteur(offreVente));
    }

    /**
     * Obtenir le stock d'un produit
     * @param produit Produit en qestion
     * @return le stock d'un produit fermier
     */
    public int getStockProduit(ProduitFermier produit){
        return Collections.frequency(getMesProduits(), produit);
    }

    /**
     * Obtenir un produit selon son nom
     * @param produit Nom d'un produit
     * @return le produit correspondant au nom entré
     */
    public ProduitFermier getMonProduit(ProduitFermier produit){
        if(getMesProduits() != null){
            for(ProduitFermier produitFermier : getMesProduits()){
                if(produit.equals(produitFermier))return produit;
            }
        }
        System.out.println("Produit introuvable !");
        return null;
    }

    /**
     * Acquérir un produit
     * @param produit Produit à ajouter à mes produits
     */
    protected void acquerirProduit(ProduitFermier produit){
        if(produitDejaExistant(produit)) {
            for (ProduitFermier produitFermier : getMesProduits())
                if (produit.equals(produitFermier)) {
                    produit = produitFermier;
                }
        }
        getMesProduits().add(produit);
    }

    /**
     * Vérifie si le produit en paramètre existe déjà dans la liste de produits
     * @param produit produit
     * @return boolean
     */
    private boolean produitDejaExistant(ProduitFermier produit){
        for(ProduitFermier produitFermier : getMesProduits()) {
            if (produitFermier.equals(produit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Envoyer de l'argent
     * @param montant Montant à envoyer
     */
    protected void envoyerArgent(double montant){ this.solde -= montant; }

    /**
     * Obtenir mon solde
     * @return solde
     */
    public Double getSolde() {
        return this.solde;
    }

    /**
     * Obtenir mon gain
     * @return gain
     */
    public Double getGain() {
        return this.gain;
    }

    /**
     * Obtenir mes produits
     * @return mesProduits
     */
    public List<ProduitFermier> getMesProduits() {
        return this.mesProduits;
    }

    /**
     * Obtenir ma taxe
     * @return maTaxe
     */
    public Taxe getMaTaxe() { return this.maTaxe; }

    /**
     * Obtenir mes offres en cours
     * @return mesOffres
     */
    public List<Offre> getMesOffres() {
        return this.mesOffres;
    }

    /**
     * Obtenir mes ventes en cours
     * @return mesVentes
     */
    public List<Vente> getMesVentes() {
        List<Vente> mesVentes = new ArrayList<Vente>();
        for(Offre offre : getMesOffres()){
            if(offre.getClass().getSimpleName().equals("Vente")) mesVentes.add((Vente)offre);
        }
        return mesVentes;
    }

    /**
     * Obtenir mon marché
     * @return monMarche
     */
    public Marche getMonMarche() {
        return this.monMarche;
    }

    /**
     * Modifier mon solde
     * @param solde Nouveau solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

    /**
     * Modifier mes gains
     * @param gain Nouveau gain
     */
    protected void setGain(double gain) {
        this.gain = gain;
    }

    /**
     * Modifier ma taxe
     * @param maTaxe Nouvelle taxe
     */
    public void setMaTaxe(Taxe maTaxe) {
        this.maTaxe = maTaxe;
    }

    /**
     * Modifier mon marché
     * @param monMarche Nouveau marché
     */
    public void setMonMarche(Marche monMarche) {
        this.monMarche = monMarche;
    }


}