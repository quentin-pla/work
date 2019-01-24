package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Produits.Liquides.ProductionMiel;

/**
 * Classe correspondant aux Fermiers de type Apiculteur
 */
public class Apiculteur extends Fermier {

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du fermier
     */
    public Apiculteur(int limiteProduction) {
        super(limiteProduction);
        setMonUsine(new ProductionMiel());
    }
}