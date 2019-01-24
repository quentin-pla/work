package marche.traitement.Classes.Produits.Liquides;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;

/**
 * Classe correspondant aux unités de production de type production de miel
 */
public class ProductionMiel implements UniteDeProduction {

    /**
     * Permet de créer un produit
     * @return une instance de la classe Miel
     */
    public ProduitFermier creerProduit(){
        return new Miel();
    }

    @Override
    public String getNomProduit() {
        return "Miel";
    }

}