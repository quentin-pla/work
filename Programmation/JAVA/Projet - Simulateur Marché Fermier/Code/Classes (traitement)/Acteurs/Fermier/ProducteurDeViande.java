package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Produits.Animaux.ProductionVache;

/**
 * Classe correspondant aux Fermiers de type ProducteurDeViande
 */
public class ProducteurDeViande extends Fermier {

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du fermier
     */
    public ProducteurDeViande(int limiteProduction) {
        super(limiteProduction);
        setMonUsine(new ProductionVache());
    }
}