package marche.traitement.Classes.Produits.Fruits;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.ProduitFermier;

import java.util.*;

/**
 * Classe correspondant aux fruits
 */
public abstract class Fruit extends ProduitFermier {

    /**
     * Date de peremption du fruit
     */
    protected Date datePeremption;

    /**
     * Constructeur par défaut de Fruit
     */
    public Fruit() {}


    public Date getDatePeremption() {
        return datePeremption;
    }
}