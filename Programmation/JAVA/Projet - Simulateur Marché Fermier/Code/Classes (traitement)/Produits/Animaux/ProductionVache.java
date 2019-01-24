package marche.traitement.Classes.Produits.Animaux;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Produits.UniteDeProduction;

/**
 *  Classe correspondant aux unités de production de type production de vache
 */
public class ProductionVache implements UniteDeProduction {
    
    /**
     * Permet de créer un produit
     * @return une instance de la classe Vache
     */
    public ProduitFermier creerProduit(){
        return new Vache();
    }

    @Override
    public String getNomProduit() {
        return "Viande de vache";
    }

}