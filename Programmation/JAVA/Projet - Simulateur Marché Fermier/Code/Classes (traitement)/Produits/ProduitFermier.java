package marche.traitement.Classes.Produits;

import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.Labels.BIO;
import marche.traitement.Classes.Produits.Labels.Label;
import marche.traitement.Classes.Produits.Labels.LabelRouge;

import java.util.*;

/**
 * Classe correspondant aux produits fermier
 */
public abstract class ProduitFermier {

    /**
     * Correspond a l'acquereur du produit
     */
    protected Participant monAcquereur;

    /**
     * Correspond au nom du produit
     */
    protected String nom;

    /**
     * Correspond a la qualité du produit
     */
    protected String qualite;

    /**
     * Correspond au(x) label(s) du produit
     */
    protected Set<Label> mesLabels = new HashSet<Label>();

    /**
     * Constructeur par défaut de ProduitFermier
     */
    public ProduitFermier() {
        this.monAcquereur = null;
        this.nom = null;
        this.qualite = null;
    }

    /**
     * Permet de vérifier si le produit n'est pas périmé
     * @param datePeremption : la date de péremption du produit
     * @return true si la date de peremption n'est pas depassee
     */
    public boolean valider(Date datePeremption) {
        Date dateActuelle = new Date();
        return datePeremption.after(dateActuelle);
    }

    /**
     * Setter de DatePeremption
     * @param tempsConservation : le temps de conservation du produit
     * @return la date de péremption du produit
     */
    public Date setDatePeremption(int tempsConservation){
        Date dateActuelle = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateActuelle);
        cal.add(Calendar.DATE, tempsConservation);
        return cal.getTime();
    }

    /**
     * verifie si le produit a un label bio
     * @return true si la liste des labels du produit contient bio
     */
    public boolean estBIO(){
        return this.mesLabels.stream().anyMatch(o -> o.getClass().getSimpleName().equals("BIO"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitFermier that = (ProduitFermier) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        Set<String>mesTitreLabels = new HashSet<>();
        if(!getMesLabels().isEmpty())
            for(Label label : getMesLabels()) mesTitreLabels.add(label.getClass().getSimpleName());
        return Objects.hash(monAcquereur, nom, qualite, mesTitreLabels);
    }

    /**
     * Getter de monAcquereur
     * @return l'acquereur du produit
     */
    public Participant getMonAcquereur() {
        return this.monAcquereur;
    }

    /**
     * Getter de nom
     * @return le nom du produit
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Getter de qualite
     * @return la qualité du produit
     */
    public String getQualite() {
        return this.qualite;
    }

    /**
     * Getter de mesLabels
     * @return la liste des labels du produit
     */
    public List<Label> getMesLabels() {
        return new ArrayList<Label>(mesLabels);
    }

    /**
     * Permet d'ajouter un label au produit
     * @param label : le label a ajouter
     */
    public void addLabel(Label label)
    {
        System.out.println("Ajout du label " + label.getClass().getSimpleName() + " au produit " + this.getNom());
        this.mesLabels.add(label);
    }

    /**
     * Permet d'afficher le(s) label(s) du produit
     */
    public void showLabels() {
        for(Label label : mesLabels){
            System.out.println(label.getClass().getSimpleName());
        }
    }

    /**
     * Setter de monAcquereur
     * @param monAcquereur : l'aquereur du produit
     */
    public void setMonAcquereur(Participant monAcquereur) {
        this.monAcquereur = monAcquereur;
    }

    /**
     * Setter de nom
     * @param nom : le nom du produit
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter de qualité
     * @param qualite : la qualité du produit
     */
    public void setQualite(String qualite) {
        this.qualite = qualite;
    }

    /**
     * Setter de mesLabels
     * @param mesLabels : la liste des labels du produit
     */
    public void setMesLabels(Set<Label> mesLabels) {
        this.mesLabels = mesLabels;
    }
}