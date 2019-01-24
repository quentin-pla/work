package marche.traitement.Classes.Systeme.AlgoRepartition;

/**
 * Interface de comparaison
 * @param <T>
 */
public interface Comparateur<T> {
    /**
     * compare deux objets
     * @param o1 premier objet a comparer
     * @param o2  deuxieme objet a comparer
     * @return un nombre negatif, zero, ou un nombre negatif si o1 est plus petit que, egale a, ou plus grand que o2
     */
    public int compare(T o1, T o2);
}
