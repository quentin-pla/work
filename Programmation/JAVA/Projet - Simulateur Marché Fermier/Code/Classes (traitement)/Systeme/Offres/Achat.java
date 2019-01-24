package marche.traitement.Classes.Systeme.Offres;

/**
 * Classe correspondant a une offre de type achat
 */
public class Achat extends Offre {

    /**
     * Correspond au produit que l'on veut acheter
     */
    private Vente offreVente;

    /**
     * Constructeur par défaut de Achat
     */
    public Achat(Vente offre, int quantite) {
        if(quantite > 0) {
            this.quantite = quantite;
            this.offreVente = offre;
            this.prix = offre.getPrixUnite()*quantite;
            System.out.println("Proposition d'une offre d'achat pour la vente: " + getOffreVente().getProduit().getNom() + " ("+ getQuantite() + " unité(s)) " + getPrix() + "€");
        }
    }

    /**
     * toString de Achat
     * @return un string contenant le nom du produit que l'on souhaite acheter ainsi que sa quantité et son prix
     */
    public String toString(){
        return "Achat: " + getOffreVente().getProduit().getNom() + " ("+ getQuantite() + " unité(s)) " + getPrix() + "€";
    }

    /**
     * Getter de offreVente
     * @return le produit que l'on souhaite acheter
     */
    public Vente getOffreVente() {
        return this.offreVente;
    }
}