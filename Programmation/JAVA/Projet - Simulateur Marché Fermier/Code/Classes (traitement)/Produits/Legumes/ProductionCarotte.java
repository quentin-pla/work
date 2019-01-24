package marche.traitement.Classes.Produits.Legumes;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;

/**
 * Classe correspondant aux unités de production de type production de carotte
 */
public class ProductionCarotte implements UniteDeProduction {

    /**
     * Permet de créer un produit
     * @return un instance de la classe Carotte
     */
    public ProduitFermier creerProduit(){
        return new Carotte();
    }

    @Override
    public String getNomProduit() {
        return "Carotte";
    }

}