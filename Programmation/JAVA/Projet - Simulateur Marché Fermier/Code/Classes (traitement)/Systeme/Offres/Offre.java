package marche.traitement.Classes.Systeme.Offres;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.ProduitFermier;

import marche.traitement.Classes.Acteurs.Participant;

import java.util.*;

/**
 * Classe correspondant a une offre
 */
public abstract class Offre {
    /**
     * Correspond a la liste des personnes abonné a l'offre
     */
    private List<Participant> mesAbonnes = new ArrayList<Participant>();

    /**
     * Correspond au drix de l'offre
     */
    protected double prix;

    /**
     * Correspond a la uantite du produit lié à l'offre
     */
    protected int quantite;

    /**
     * Correspond a la personne qui fait l'offre
     */
    protected Participant participant;

    /**
     * Date de création de l'offre
     */
    protected Date dateCreation;

    /**
     * Correspond au produit lié à l'offre
     */
    protected Catalogue monCatalogue;

    protected ProduitFermier produitFermier;

    /**
     * Getter de quantité
     * @return la quantité du produit lier a l'offre
     */
    public List<Participant> getMesAbonnes() {
        return mesAbonnes;
    }

    /**
     * Setter de mes abonnés
     * @param mesAbonnes Liste de participants
     */
    public void setMesAbonnes(List<Participant> mesAbonnes) {
        this.mesAbonnes = mesAbonnes;
    }

    /**
     * Getter de la date de création de l'offre
     * @return dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Setter de la date de création de l'offre
     * @param dateCreation date
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter de la quantité de l'offre
     * @return quantite
     */
    public Integer getQuantite() {
        return quantite;
    }

    /**
     * Getter de participant
     * @return le participant qui fait l'offre
     */
    public Participant getParticipant() { return participant; }

    /**
     * Getter de monCatalogue
     * @return le produit lié a l'offre
     */
    public Catalogue getMonCatalogue() { return monCatalogue; }

    /**
     * Getter de prix
     * @return le prix de l'offre
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Setter de prix
     * @param prix : le prix de l"offre
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Setter de quantité
     * @param quantite : la quantité de l'offre
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Setter de participant
     * @param participant : le participant qui fait l'offre
     */
    public void setParticipant(Participant participant) { this.participant = participant; }

    /**
     * Setter de monCatalogue
     * @param monCatalogue : le produit lié a l'offre
     */
    public void setMonCatalogue(Catalogue monCatalogue) {
        this.monCatalogue = monCatalogue;
    }
    /**
     * Permet d'ajouter un abonne a une offre
     * @param participant le participant qui s'est abonne
     */
    public void ajouterAbonne(Participant participant) {
        mesAbonnes.add(participant);
    }

    /**
     * Permet de supprimer un abonne a une offre
     * @param participant le participat qui se desabonne
     */
    public void supprimerAbonne(Participant participant) {
        mesAbonnes.remove(participant);
    }

    /**
     * supprimer l'ensemble des abonnés liés à l'offre
     */
    public void supprimerTousAbonne(){ mesAbonnes.clear(); }

    /**
     * Permet de notifier le participant d'une nouvelle offre
     */
    public void notifierParticipant() {
        for (Participant participant : mesAbonnes) {
            System.out.println("Nouvelle offre : " + this.toString());
        }
    }


    public ProduitFermier getProduitFermier() {
        return produitFermier;
    }
}