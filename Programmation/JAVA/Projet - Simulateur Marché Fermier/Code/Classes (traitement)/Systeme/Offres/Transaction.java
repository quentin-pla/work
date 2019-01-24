package marche.traitement.Classes.Systeme.Offres;

import java.util.*;

/**
 * Classe correspondant a une transaction
 */
public class Transaction {

    /**
     * Correspond a la date de la transaction
     */
    private Date date;

    /**
     * Correspond au Grand livre du marché
     */
    private GrandLivreDuMarche monLivre;

    /**
     * Correspond a l'offre concerner par la transaction
     */
    private Offre mesOffres;

    /**
     * Constructeur par défaut de Transaction
     */
    public Transaction(Offre offre, GrandLivreDuMarche monLivre) {
        this.monLivre = monLivre;
        this.mesOffres = offre;
        this.date = offre.getDateCreation();
    }

    /**
     * Getter de date
     * @return la date de la transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter de monlivre
     * @return le Grand livre du marché de la transaction
     */
    public GrandLivreDuMarche getMonLivre() {
        return monLivre;
    }

    /**
     * Getter de monOffre
     * @return l'offre concerner par la transaction
     */
    public Offre getMonOffre() {
        return mesOffres;
    }
}