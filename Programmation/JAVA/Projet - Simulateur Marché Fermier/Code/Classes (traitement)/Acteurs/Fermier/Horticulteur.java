package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Produits.Legumes.ProductionCarotte;

/**
 * Classe correspondant aux Fermiers de type Horticulteur
 */
public class Horticulteur extends Fermier {

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du fermier
     */
    public Horticulteur(int limiteProduction) {
        super(limiteProduction);
        setMonUsine(new ProductionCarotte());
    }
}