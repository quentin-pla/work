package marche.traitement.Classes.Produits.Legumes;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.ProduitFermier;

import java.util.*;

/**
 * Classe correspondant aux légumes
 */
public abstract class Legume extends ProduitFermier {

    /**
     * Correspond a la date de péremption du légume
     */
    protected Date datePeremption;

    /**
     * Constructeur par défaut de Legume
     */
    public Legume() {}

    public Date getDatePeremption() {
        return datePeremption;
    }
}