package marche.traitement.Classes.Acteurs.Fermier;

import marche.traitement.Classes.Produits.Fruits.ProductionPomme;

/**
 * Classe correspondant aux Fermiers de type Arboriculteur
 */
public class Arboriculteur extends Fermier {

    /**
     * Constructeur par défaut
     * @param limiteProduction limite de production du fermier
     */
    public Arboriculteur(int limiteProduction) {
        super(limiteProduction);
        setMonUsine(new ProductionPomme());
    }
}