package marche.traitement.Classes.Systeme.Taxes;

import marche.traitement.Classes.Acteurs.Participant;

import java.util.*;

/**
 * Classe correspondant a une taxe
 */
public abstract class Taxe {

    /**
     * Prelevement effectué sur le solde du participant
     */
    protected double prelevement;

    public Taxe(){}

    /**
     * @param solde : solde du participant
     * @return : la cotisation des taxes du participant
     */
    public Double recupererCotisation(Double solde){
        return (solde/100)*this.prelevement;
    }

    public double getPrelevement() {
        return prelevement;
    }

    public void setPrelevement(double prelevement) {
        this.prelevement = prelevement;
    }
}