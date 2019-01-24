package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Produits.Liquides.ProductionLait;

/**
 * Classe correspondant aux Fermiers de type ProducteurLaitier
 */
public class ProducteurLaitier extends Fermier {

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du fermier
     */
    public ProducteurLaitier(int limiteProduction) {
        super(limiteProduction);
        setMonUsine(new ProductionLait());
    }
}