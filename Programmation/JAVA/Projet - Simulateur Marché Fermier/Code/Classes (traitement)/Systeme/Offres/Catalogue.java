package marche.traitement.Classes.Systeme.Offres;

import marche.traitement.Classes.Acteurs.Controleur;
import marche.traitement.Classes.Produits.ProduitFermier;

import java.util.*;

/**
 * Classe correspondant a un Catalogue
 */
public class Catalogue {

    /**
     * Correspond au controleur du catalogue
     */
    private Controleur monControleur;

    /**
     * Corresponds aux offres de vente du catalogue
     */
    private List<Vente> offresVente = new ArrayList<Vente>();

    /**
     * Corresponds aux offres de vente du catalogue
     */
    private List<Achat> offresAchat = new ArrayList<Achat>();

    /**
     * Constructeur par défaut de Catalogue
     */
    public Catalogue(Controleur controleur) {
        this.monControleur = controleur;
    }

    /**
     * Permet d'ajouter une offre de vente dans le catalogue
     * @param offre : l'offre que l'on souhaite ajouter
     */
    public void ajouterOffreVente(Vente offre){
        getOffresVente().add(offre);
        System.out.println("[i] Ajout de l'offre de vente dans le catalogue.");
    }

    /**
     * Permet d'ajouter une offre de d'achat dans le catalogue
     * @param offre : l'offre que l'on souhaite ajouter
     */
    public void ajouterOffreAchat(Achat offre){
        getOffresAchat().add(offre);
        System.out.println("[i] Ajout de l'offre d'achat dans le catalogue.");
    }

    /**
     * Permet d'afficher les offres disponible
     */
    public void afficherOffresDispo(){
        System.out.println("Offres de vente disponibles:");
        if(getOffresVente().size() > 0) for(Vente offreVente : getOffresVente()) System.out.println(offreVente.toString());
        else System.out.println("Aucune offre de vente.");
        System.out.println("Offres d'achat disponibles:");
        if(getOffresAchat().size() > 0) for(Achat offreAchat : getOffresAchat()) System.out.println(offreAchat.toString());
        else System.out.println("Aucune offre d'achat disponible.");
    }

    /**
     * Permet de vérifier si des offres d'achat sont encore disponibles pour une offre de vente
     * @param vente offre de vente
     * @return true, s'il y a encore des offres d'achat disponibles pour l'offre de vente
     */
    public boolean verifierSiOffreAchatPourVente(Vente vente){
        for(Achat achat : getOffresAchat())
            if(achat.getOffreVente().equals(vente)) return true;
        return false;
    }

    /**
     * Getter d'une offre de vente
     * @param vente offre de vente
     * @return la même offre se trouvant dans la liste des offres de vente disponibles
     */
    public Vente getVente(Vente vente){
        for(Vente v : getOffresVente()){
            if(v.equals(vente)) return v;
        }
        return null;
    }

    /**
     * Getter de monControleur
     * @return le controleur du catalogue
     */
    public Controleur getMonControleur() {
        return this.monControleur;
    }

    /**
     * Getter de offresVente
     * @return les offres de vente du catalogue
     */
    public List<Vente> getOffresVente() {
        return offresVente;
    }

    /**
     * Getter de offresAchat
     * @return les offres d'achat du catalogue
     */
    public List<Achat> getOffresAchat() {
        return offresAchat;
    }
}