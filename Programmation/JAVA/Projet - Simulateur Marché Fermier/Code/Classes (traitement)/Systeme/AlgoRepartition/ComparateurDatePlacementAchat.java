package marche.traitement.Classes.Systeme.AlgoRepartition;

import marche.traitement.Classes.Systeme.Offres.Achat;

import java.util.Comparator;


/**
 * Classe servant à comparer des achats selon leurs dates de création
 */
public class ComparateurDatePlacementAchat implements Comparateur<Achat>, Comparator<Achat> {

    /**
     * Compare deux achats selon leurs dates de création
     * @param a1 Un des achats à comparer
     * @param a2 Un des achats à comparer
     * @return un nombre negatif, zero, ou un nombre negatif si la date de a1 est plus petit que, egale a, ou plus grand que la date de a2
     */
    @Override
    public int compare(Achat a1, Achat a2) { return a1.getDateCreation().compareTo(a2.getDateCreation()); }
}
