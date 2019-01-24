package marche.traitement.Classes.Produits.Fruits;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;

/**
 * Classe correspondant aux unités de production de type production de pommes
 */
public class ProductionPomme implements UniteDeProduction {

    /**
     * Permet de créer un produit
     * @return une instance de la classe Pomme
     */
    public ProduitFermier creerProduit(){
        return new Pomme();
    }

    @Override
    public String getNomProduit() {
        return "Pomme";
    }

}