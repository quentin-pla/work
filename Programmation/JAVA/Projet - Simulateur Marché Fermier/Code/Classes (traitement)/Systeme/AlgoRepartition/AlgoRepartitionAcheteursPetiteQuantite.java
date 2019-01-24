package marche.traitement.Classes.Systeme.AlgoRepartition;

import marche.traitement.Classes.Systeme.Offres.Achat;

import java.util.*;

/**
 * Classe correspondant à l'algorithme de répartition des acheteurs de petites quantités
 */
public class AlgoRepartitionAcheteursPetiteQuantite implements SelectionAcheteur {

    /**
     * Constructeur par défaut
     */
    public AlgoRepartitionAcheteursPetiteQuantite() {}

    /**
     * Sélectionne un acheteur dans une liste selon la quantité d'achat
     * @param listeAchats Liste d'achats
     * @return l'achat avec l'acheteur qui a la plus petite quantite
     */
    @Override
    public Achat selectionnerAcheteur(List<Achat> listeAchats) {
        SortedSet<Achat> listAchatsTries = new TreeSet<Achat>(new ComparateurQuantiteAchats());
        listAchatsTries.addAll(listeAchats);
        return listAchatsTries.first();
    }
}