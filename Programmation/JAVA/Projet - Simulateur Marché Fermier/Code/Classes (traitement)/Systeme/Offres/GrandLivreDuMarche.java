package marche.traitement.Classes.Systeme.Offres;

import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Systeme.Marche;

import java.util.*;

/**
 * Classe correspondant au Grand livre du marché
 */
public class GrandLivreDuMarche {

    /**
     * Correspond a la liste des transactions du Grand livre
     */
    private List<Transaction> mesTransactions = new ArrayList<Transaction>();

    private static List<Transaction> mesDernieresTransactions = new ArrayList<Transaction>();

    private Marche monMarche;

    /**
     * Constructeur par défaut de GrandLivreDuMarche
     */
    public GrandLivreDuMarche(Marche marche) {
        this.monMarche = marche;
    }

    /**
     * Permet d'archiver une transaction
     * @param t : la transaction a archiver
     */
    public void archiverTransaction(Transaction t) {
        this.mesTransactions.add(t);
        mesDernieresTransactions.add(t);
        System.out.println("[i] Ajout de l'offre dans le grand livre du marché");
    }

    /**
     * Getter de mesTransactions
     * @return toutes les transactions présentent dans le Grand livre du marché
     */
    public List<Transaction> getMesTransactions() {
        return mesTransactions;
    }

    public List<Vente> getHistoriqueVentes() {
        List<Vente>ventes = new ArrayList<Vente>();
        for(Transaction transaction : mesTransactions){
            if(transaction.getMonOffre().getClass().getSimpleName().equals("Vente")){
                ventes.add((Vente)transaction.getMonOffre());
            }
        }
        return ventes;
    }

    /**
     * Permet de recupérer l'historique d'achat du grand livre du marché
     * @return : l'historique d'achat
     */
    public List<Achat> getHistoriqueAchats() {
        List<Achat>achats = new ArrayList<Achat>();
        for(Transaction transaction : mesTransactions){
            if(transaction.getMonOffre().getClass().getSimpleName().equals("Achat")){
                achats.add((Achat)transaction.getMonOffre());
            }
        }
        return achats;
    }

    public static List<Transaction> getDernieresVentesProducteur(Fermier producteur) {
        List<Transaction> dernieresVentesProducteur = new ArrayList<Transaction>();
        for (Transaction transaction : mesDernieresTransactions) {
            if (transaction.getMonOffre().getParticipant() == producteur && transaction.getMonOffre().getClass().getSimpleName().equals("Vente"))
                dernieresVentesProducteur.add(transaction);
        }
        return dernieresVentesProducteur;
    }

    public static void reinitialiserDernieresTransactions() {
        mesDernieresTransactions.clear();
    }
}