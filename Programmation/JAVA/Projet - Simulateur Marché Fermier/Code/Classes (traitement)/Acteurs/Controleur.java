package marche.traitement.Classes.Acteurs;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.AlgoRepartition.SelectionAcheteur;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.*;

import java.util.*;

/**
 * Classe correspondant aux Participants de type Controleur
 */
public class Controleur extends Participant {

    /**
     * Marché actuel
     */
    private Marche monMarche;

    /**
     * Catalogue actuel
     */
    private Catalogue monCatalogue;

    /**
     * Marge d'encadrement des prix
     */
    private double margeEncadrementPrix;

    /**
     * Liste des offres à valider
     */
    private List<Offre> mesOffresAValider = new ArrayList<Offre>();

    /**
     * Liste des produits illégaux
     */
    private List<ProduitFermier> produitsIllegaux = new ArrayList<ProduitFermier>();

    /**
     * Algorithme de séléction d'un acheteur
     */
    private SelectionAcheteur monAlgoSelectionAcheteur;

    /**
     * Constructeur par défaut
     * @param marche Marché actuel
     * @param algo algorithme de reparttion choisi
     */
    public Controleur(Marche marche, SelectionAcheteur algo) {
        this.monMarche = marche;
        this.margeEncadrementPrix = 20;
        this.monCatalogue = new Catalogue(this);
        this.monAlgoSelectionAcheteur = algo;
        System.out.println("[i] Création d'un catalogue relié à ce controleur" + "(" + this.monCatalogue + ")");
        System.out.println("[i] Algorithme choisi pour la sélection de l'acheteur: " + this.monAlgoSelectionAcheteur.getClass().getSimpleName());
    }

    /**
     * Valide une offre en verifiant la conformité d'une vente
     * @param offre Offre à valier
     */
    protected void validerOffreVente(Vente offre){
        if(verifierStock(offre.getProduit(),offre.getQuantite()) && verifierEncadrementPrix(offre) && verifierCommercialisation(offre.getProduit())) {
            offre.setDateCreation(new Date());
            offre.setMonCatalogue(getMonCatalogue());
            getMonCatalogue().ajouterOffreVente(offre);
            getMonMarche().getMaBourse().majProduit(offre.getProduit(), offre.getPrixUnite());
            offre.getParticipant().getMesOffres().add(offre);
            getMonMarche().getMonGrandLivreDuMarche().archiverTransaction(new Transaction(offre, getMonMarche().getMonGrandLivreDuMarche()));
        }
        else System.out.println("L'offre de vente n'a pas pu être ajoutée dans le catalogue.");
    }

    /**
     * Vérifie si le vendeur a un stock suffisant
     * @param produit produit a verfier le stock
     * @param quantite quantite demandee
     * @return true si le stock du vendeur est superieur a la quantite demandee
     */
    private boolean verifierStock(ProduitFermier produit, Integer quantite) {
        if(produit.getMonAcquereur().getStockProduit(produit) >= quantite) return true;
        else System.out.println("[!] Stock du produit insuffisant pour la création de l'offre !");
        return false;
    }

    /**
     * Verifie si l'encadrement du prix est valide
     * @param offre offre a verifier
     * @return true si le prix de l'offre est compris dans l'encadrement
     */
    public boolean verifierEncadrementPrix(Vente offre) {
        double prixMoyen = getMonMarche().getMaBourse().getPrixMoyenProduit(offre.getProduit());
        if(prixMoyen == 0)return true;
        double margeLimite = (prixMoyen * getMargeEncadrementPrix())/100;
        if(offre.getPrixUnite() <= (prixMoyen + margeLimite) && offre.getPrixUnite() >= (prixMoyen - margeLimite) && offre.getPrixUnite() > 0) return true;
        else System.out.println("[!] Marge du prix proposé trop élevée par rapport à celui de la Bourse !");
        return false;
    }

    /**
     * Vérifie la légalité d'un produit
     * @param produit Produit à vérifier
     * @return true si le produit n'est pas contenu dans la liste des produits illegaux
     */
    private boolean verifierCommercialisation(ProduitFermier produit) {
        if(!getProduitsIllegaux().contains(produit)) return true;
        else System.out.println("[!] Produit interdit à la vente !");
        return false;
    }

    /**
     * Vérifie si l'achat est correct (solde suffisant)
     * @param offreAchat offre d'achat
     */
    protected void validerOffreAchat(Achat offreAchat){
        if(verifierSolde(offreAchat.getParticipant(),offreAchat.getPrix()) && verifierNonAchatPropreProduit(offreAchat) && verifierStock(offreAchat.getOffreVente().getProduit(),offreAchat.getQuantite())){
            offreAchat.setDateCreation(new Date());
            offreAchat.setMonCatalogue(getMonCatalogue());
            getMonCatalogue().ajouterOffreAchat(offreAchat);
            offreAchat.getParticipant().getMesOffres().add(offreAchat);
            getMonMarche().getMonGrandLivreDuMarche().archiverTransaction(new Transaction(offreAchat, getMonMarche().getMonGrandLivreDuMarche()));
        }
        else System.out.println("L'offre d'achat n'a pas pu être ajoutée dans le catalogue.");
    }

    /**
     * Vérifie si le solde d'un acheteur est suffisant
     * @param acheteur Acheteur en question
     * @param prixOffre Prix de l'offre que l'acheteur souhaite acquérir
     * @return true si le solde d'un acheteur est superieur au prix de l'offre
     */
    private boolean verifierSolde(Participant acheteur, double prixOffre){
        if(acheteur.getSolde() > prixOffre) return true;
        else System.out.println("[!] Solde insuffisant, proposition refusée !");
        return false;
    }

    /**
     * Vérifie si l'utilisateur n'essaye pas d'acheter un de ses produits
     * @param achat Offre d'achat
     * @return false si la vente du participant est comprise dans ses achats
     */
    private boolean verifierNonAchatPropreProduit(Achat achat){
        for(Vente vente : achat.getParticipant().getMesVentes())
            if(achat.getOffreVente().equals(vente)){
                System.out.println("[!] L'utilisateur ne peut pas acheter son propre produit !");
                return false;
            }
        return true;
    }

    /**
     * Validation générale d'une transaction
     * @param offreAchat Offre à vérifier
     * @return true si la quantite demandee ne depasse pas la quantite de l'offre et si l'acheteur a assez d'argent
     */
    private boolean validerTransaction(Achat offreAchat) {
        Participant vendeur = offreAchat.getOffreVente().getParticipant();
        Participant acheteur = offreAchat.getParticipant();
        if(vendeur.getStockProduit(offreAchat.getOffreVente().getProduit()) >= offreAchat.getQuantite()){
            if(acheteur.getSolde() >= offreAchat.getPrix()) return true;
            else System.out.println("[!] L'acheteur n'a pas assez d'argent pour effectuer la transaction !");
        }
        else System.out.println("[!] Le vendeur n'a pas assez de stock pour effectuer la transaction !");
        return false;
    }

    /**
     * Effectue une transaction (envoi du produit, maj des soldes, maj du catalogue)
     * @param offreAchat Offre effectuée
     */
    public void effectuerTransaction(Achat offreAchat){
        if(validerTransaction(offreAchat)){
            System.out.println("Une offre d'achat vient d'être acceptée: " + offreAchat.getOffreVente().getProduit().getNom() + " ("+ offreAchat.getQuantite() + " unité(s)) " + offreAchat.getPrix() + "€");
            Participant vendeur = offreAchat.getOffreVente().getParticipant();
            Participant acheteur = offreAchat.getParticipant();
            vendeur.envoyerProduit(offreAchat.getOffreVente().getProduit(),offreAchat.getQuantite(), acheteur);
            vendeur.encaisserArgent(acheteur,offreAchat.getPrix());
            offreAchat.getOffreVente().reduireStock(offreAchat.getQuantite());
            getMonCatalogue().getOffresAchat().remove(offreAchat);
            offreAchat.supprimerTousAbonne();
            offreAchat.getParticipant().getMesOffres().remove(offreAchat);
            System.out.println("[i] Transaction effectuée avec succés !");
            majOffres(offreAchat.getOffreVente());
            majProduits(offreAchat.getParticipant(),offreAchat.getOffreVente().getParticipant());
            getMonMarche().incrementerTemps();
        }
        else System.out.println("[!] La transaction est annulée !");
    }

    /**
     * Mise à jour de l'acquereur de chaque produits du participant
     * @param acheteur acheteur de l'offre de vente
     * @param vendeur vendeur
     */
    private void majProduits(Participant acheteur, Participant vendeur){
        for(ProduitFermier produitFermier : acheteur.getMesProduits()) produitFermier.setMonAcquereur(acheteur);
        for(ProduitFermier produitFermier : vendeur.getMesProduits()) produitFermier.setMonAcquereur(vendeur);
    }

    /**
     * Met à jour le catalogue
     * @param offreVente Offre permettant de mettre à jour le catalogue
     */
    public void majOffres(Vente offreVente){
        System.out.println("[i] Mise à jour des offres dans le catalogue...");
        if(offreVente.getQuantite() == 0){
            for(Achat offreAchat : getMonCatalogue().getOffresAchat()){
                if(offreAchat.getOffreVente().equals(offreVente))getMonCatalogue().getOffresAchat().remove(offreAchat);
            }
            offreVente.supprimerTousAbonne();
            offreVente.getParticipant().getMesOffres().remove(offreVente);
            getMonCatalogue().getOffresVente().remove(offreVente);
        }
        else if(offreVente.getQuantite() > 0){
            for(Achat offreAchat : getMonCatalogue().getOffresAchat()){
                if(offreAchat.getOffreVente().equals(offreVente) && offreAchat.getQuantite() > offreVente.getQuantite()) {
                    offreAchat.supprimerTousAbonne();
                    offreAchat.getParticipant().getMesOffres().remove(offreAchat);
                    getMonCatalogue().getOffresAchat().remove(offreAchat);
                }
            }
        }
        getMonCatalogue().afficherOffresDispo();
    }

    /**
     * Choisi un acheteur pour une offre
     * @param offreVente Offre dont il faut choisir l'acheteur
     * @return acheteur choisi
     */
    public Achat choisirAcheteur(Vente offreVente) {
        List<Achat>offresAchat = new ArrayList<Achat>();
        for(Achat offreAchat : getMonCatalogue().getOffresAchat())
            if(offreAchat.getOffreVente().equals(offreVente))offresAchat.add(offreAchat);
        return getMonAlgoSelectionAcheteur().selectionnerAcheteur(offresAchat);
    }

    /**
     * Ajoute un produit à la liste des produits illégaux
     * @param produitFermier Produit à interdire
     */
    public void interdireProduit(ProduitFermier produitFermier) { this.getProduitsIllegaux().add(produitFermier); }

    /**
     * ajoute au solde le montant de la taxe
     * @param montant montant de la taxe
     */
    public void getArgentTaxe(Double montant) { this.setSolde(this.getSolde() + montant); }

    /**
     * Retourne le marché actuel
     * @return monMarche
     */
    public Marche getMonMarche() {
        return this.monMarche;
    }

    /**
     * Retourne le catalogue actuel
     * @return monCataogue
     */
    public Catalogue getMonCatalogue() { return this.monCatalogue; }

    /**
     * Retourne l'encadrement du prix
     * @return margeEncadrementPrix
     */
    public double getMargeEncadrementPrix() {
        return this.margeEncadrementPrix;
    }

    /**
     * Retourne les offres à valider
     * @return mesOffresAValider
     */
    private List<Offre> getMesOffresAValider() {
        return this.mesOffresAValider;
    }

    /**
     * Retourne les produits illégaux
     * @return produitsIllegaux
     */
    public List<ProduitFermier> getProduitsIllegaux() {
        return this.produitsIllegaux;
    }

    /**
     * Sélectionne l'algorithme d'achat
     * @return monAlgoSelectionAcheteur
     */
    public SelectionAcheteur getMonAlgoSelectionAcheteur() {
        return this.monAlgoSelectionAcheteur;
    }

    /**
     * Modifie le marché actuel
     * @param monMarche Nouveau marché actuel
     */
    public void setMonMarche(Marche monMarche) {
        this.monMarche = monMarche;
    }

    /**
     * Modifie la marge d'encadrement du prix
     * @param margeEncadrementPrix Nouvelle marge d'encadrement du prix
     */
    public void setMargeEncadrementPrix(double margeEncadrementPrix) {
        this.margeEncadrementPrix = margeEncadrementPrix;
    }

    /**
     * Modifie l'algorithme de sélection d'acheteur à utiliser
     * @param monAlgoSelectionAcheteur Algorithme de sélection d'acheteur
     */
    public void setMonAlgoSelectionAcheteur(SelectionAcheteur monAlgoSelectionAcheteur) {
        this.monAlgoSelectionAcheteur = monAlgoSelectionAcheteur;
    }

    /**
     * Modifie le catalogue actuel
     * @param monCatalogue Nouveau catalogue actuel
     */
    public void setMonCatalogue(Catalogue monCatalogue) {
        this.monCatalogue = monCatalogue;
    }

    /**
     * Modifie la liste des produits illégaux
     * @param produitsIllegaux Nouvelle liste de produits illégaux
     */
    public void setProduitsIllegaux(List<ProduitFermier> produitsIllegaux) {
        this.produitsIllegaux = produitsIllegaux;
    }
}