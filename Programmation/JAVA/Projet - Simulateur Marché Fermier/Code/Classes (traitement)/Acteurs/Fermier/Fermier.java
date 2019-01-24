package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.Labels.Label;
import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;
import marche.traitement.Classes.Systeme.Offres.GrandLivreDuMarche;
import marche.traitement.Classes.Systeme.Offres.Transaction;

/**
 * Classe correspondant aux Fermiers de type Participant
 */
public abstract class Fermier extends Participant {

    /**
     * Limite de production du fermier
     */
    private int limiteProduction;

    /**
     * Quantité de production du fermier
     */
    private int quantiteProduction;

    /**
     * Usine du fermier
     */
    private UniteDeProduction monUsine;

    /**
     * limite de la quantite pour un petit producteur
     */
    private  final double limiteQuantite = 100;

    /**
     * limite de prix pour un petit producteur
     */
    private  final double limitePrix = 100;

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du Fermier
     */
    protected Fermier(int limiteProduction) {
        this.limiteProduction = limiteProduction;
        this.quantiteProduction = 0;
        this.monUsine = null;
    }

    /**
     * Verifie pour chaque produit vendu d'un fermier qu'il est bio
     * @return true si tous les produits d'un fermier sont bio
     */
    public boolean estProducteurBIO() {
        for(Transaction transaction : GrandLivreDuMarche.getDernieresVentesProducteur(this)) {
            if (! transaction.getMonOffre().getProduitFermier().estBIO())
                return false;
        }
        return true;
    }

    /**
     * Verifie pour chaque produit vendu qu'il ne depasse pas la limite de quantite et de prix pour un petit producteur
     * @return true si toutes les offres ne depassent pas la limite
     */
    public boolean estPetitProducteur() {
        for(Transaction transaction : GrandLivreDuMarche.getDernieresVentesProducteur(this)) {
            if (transaction.getMonOffre().getQuantite() > limiteQuantite && transaction.getMonOffre().getPrix() > limitePrix)
                return false;
        }
        return true;
    }

    /**
     * Produire un produit
     * @param nom Nom du produit
     * @param qualite Qualité du produit
     * @param quantite Quantité du produit
     */
    public void produire(String nom, String qualite, int quantite) {
        if(peutProduire(quantite)) {
            System.out.println("Production de " + nom + " de " + qualite + " qualité (" + quantite + " unités)");
            this.quantiteProduction += quantite;
            ProduitFermier produit = getMonUsine().creerProduit();
            produit.setMonAcquereur(this);
            produit.setNom(nom.toLowerCase());
            produit.setQualite(qualite);
            for (; quantite > 0; --quantite)acquerirProduit(produit);
        }
    }

    /**
     * Produire un produit avec un label
     * @param nom Nom du produit
     * @param qualite Qualité du produit
     * @param quantite Quantité du produit
     * @param label Label du produit
     */
    public void produire(String nom, String qualite, int quantite, Label label) {
        if(peutProduire(quantite)) {
            System.out.println("Production de " + nom + " de " + qualite + " qualité (" + quantite + " unités)");
            this.quantiteProduction += quantite;
            ProduitFermier produit = getMonUsine().creerProduit();
            produit.setMonAcquereur(this);
            produit.setNom(nom.toLowerCase());
            produit.setQualite(qualite);
            produit.addLabel(label);
            for (; quantite > 0; --quantite)acquerirProduit(produit);
        }
    }

    /**
     * Verifie si le Fermier peut produire
     * @param quantite Quantité à produire
     * @return true si le fermier n'a pas dépassé sa limite de production
     */
    private boolean peutProduire(int quantite) {
        if(getQuantiteProduction() + quantite <= getLimiteProduction()) return true;
        else System.out.println("Production impossible, limite de production atteinte !");
        return false;
    }

    /**
     * Retourne la limite de production actuelle
     * @return limiteProduction
     */
    public Integer getLimiteProduction() {
        return limiteProduction;
    }

    /**
     * Quantité de production actuelle du fermier
     * @return quantiteProduction
     */
    public Integer getQuantiteProduction() {
        return quantiteProduction;
    }

    /**
     * Retourne l'usine actuelle du Fermier
     * @return monUsine
     */
    public UniteDeProduction getMonUsine() {
        return monUsine;
    }

    /**
     * Modifie la limite du production du fermier
     * @param limiteProduction Nouvelle limite de production du Fermier
     */
    public void setLimiteProduction(int limiteProduction) {
        this.limiteProduction = limiteProduction;
    }

    /**
     * Modifie l'usine du Fermier
     * @param monUsine Nouvelle usine du Fermier
     */
    public void setMonUsine(UniteDeProduction monUsine) {
        this.monUsine = monUsine;
    }
}