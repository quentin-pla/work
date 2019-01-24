package marche.traitement.Classes.Produits.Liquides;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;

/**
 * Classe correspondant aux unités de production de type production de lait
 */
public class ProductionLait implements UniteDeProduction {

    /**
     * Permet de créer un produit
     * @return une instance de la classe lait
     */
    public ProduitFermier creerProduit(){
        return new Lait();
    }

    @Override
    public String getNomProduit() {
        return "Lait";
    }

}