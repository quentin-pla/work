package marche.traitement.Classes.Systeme.AlgoRepartition;

import marche.traitement.Classes.Systeme.Offres.Achat;

import java.util.*;

/**
 * Classe correspondant à l'algorithme de répartition des acheteurs selon le temps
 */
public class AlgoRepartitionAcheteursTemps implements SelectionAcheteur {

    /**
     * Constructeur par défaut
     */
    public AlgoRepartitionAcheteursTemps() {}

    /**
     * Séléctionne un acheteur dans une liste selon le moment ou il a acheté
     * @param listeAchats Liste d'achats
     * @return l'achat avec l'acheteur qui a achete en premier
     */
    @Override
    public Achat selectionnerAcheteur(List<Achat> listeAchats) {
        SortedSet<Achat> listAchatsTries = new TreeSet<Achat>(new ComparateurDatePlacementAchat());
        listAchatsTries.addAll(listeAchats);
        return listAchatsTries.first();
    }
}