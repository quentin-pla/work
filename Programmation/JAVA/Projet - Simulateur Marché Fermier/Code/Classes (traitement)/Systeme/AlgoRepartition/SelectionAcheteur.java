package marche.traitement.Classes.Systeme.AlgoRepartition;

import marche.traitement.Classes.Systeme.Offres.Achat;

import java.util.*;

/**
 * Interface de séléction d'un acheteur
 */
public interface SelectionAcheteur {

    /**
     * Séléctione un acheteur dans une liste
     * @param listeAchat Liste d'achats
     * @return l'achat avec l'acheteur selectionne
     */
    public Achat selectionnerAcheteur(List<Achat> listeAchat);

}