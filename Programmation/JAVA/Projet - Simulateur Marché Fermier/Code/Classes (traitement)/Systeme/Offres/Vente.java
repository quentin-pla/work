package marche.traitement.Classes.Systeme.Offres;

import marche.traitement.Classes.Produits.ProduitFermier;

import java.text.DecimalFormat;

/**
 * Classe correspondant a un offre de type vente
 */
public class Vente extends Offre {

    /**
     * Correspond au produit concerné par la vente
     */
    protected ProduitFermier produit;

    /**
     * Constructeur par défaut de Vente
     */
    public Vente(ProduitFermier produit, int quantite, double prixUnite) {
        if(produit != null && quantite > 0 && prixUnite > 0) {
            this.produit = produit;
            this.quantite = quantite;
            this.prix = prixUnite;
            System.out.println("Proposition d'une offre de vente: " + getProduit().getNom() + " (" + this.quantite + " unité(s)) " + new DecimalFormat("0.00").format(this.prix) + "€/unité");
        }
    }

    /**
     * Getter de prix
     * @return le prix à l'unité du produit mis en vente
     */
    public double getPrixUnite() {
        return this.prix;
    }

    /**
     * Getter de produit
     * @return le produit concerné par la vente
     */
    public ProduitFermier getProduit() {
        return this.produit;
    }

    /**
     * toString de Vente
     * @return un string contenant le nom du produit que l'on souhaite vendre ainsi que sa quantité et son prix a l'unité
     */
    public String toString(){
        return "Vente: " + getProduit().getNom() + " (" + this.quantite + " unité(s)) " + new DecimalFormat("0.00").format(this.prix) + "€/unité";
    }

    /**
     * Permet de reduire la quantité du produit lié a l'offre
     * @param quantite : la quantité a enlever
     */
    public void reduireStock(int quantite) {
        if(this.quantite - quantite >= 0) this.quantite -= quantite;
    }

    /**
     * Setter de produit
     * @param produit : le produit que l'on souhaite vendre
     */
    public void setProduit(ProduitFermier produit) {
        this.produit = produit;
    }
}