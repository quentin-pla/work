package marche.traitement.Classes.Systeme.AlgoRepartition;

import marche.traitement.Classes.Systeme.Offres.Achat;

import java.util.Comparator;

/**
 * Classe servant a comparer des achats selon leurs quantitées
 */
public class ComparateurQuantiteAchats implements Comparateur<Achat>, Comparator<Achat> {

    /**
     * Compare deux achats selon leurs quantitées
     * @param a1 Un des achats a comparer
     * @param a2 Un des achats a comparer
     * @return un nombre negatif, zero, ou un nombre negatif si la quantite de a1 est plus petit que, egale a, ou plus grand que la quantite de a2
     */
    @Override
    public int compare(Achat a1, Achat a2) {
        return a1.getQuantite().compareTo(a2.getQuantite());
    }
}