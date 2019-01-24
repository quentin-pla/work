package marche.traitement.Classes.Produits.Liquides;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.ProduitFermier;

import java.util.*;

/**
 *  Classe correspondant aux liquides
 */
public abstract class Liquide extends ProduitFermier {

    /**
     * Correspond a la date de péremption du liquide
     */
    protected Date datePeremption;

    /**
     * Constructeur par défaut de Liquide
     */
    public Liquide() {}

    public Date getDatePeremption() {
        return datePeremption;
    }
}