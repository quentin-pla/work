package marche.traitement.Classes.Systeme;

import marche.traitement.Classes.Acteurs.Controleur;
import marche.traitement.Classes.Acteurs.Fermier.Apiculteur;
import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Acteurs.Fermier.Horticulteur;
import marche.traitement.Classes.Acteurs.Fermier.ProducteurDeViande;
import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Acteurs.Paysan;
import marche.traitement.Classes.Produits.Labels.BIO;
import marche.traitement.Classes.Systeme.AlgoRepartition.AlgoRepartitionAcheteursTemps;
import marche.traitement.Classes.Systeme.Offres.*;
import marche.traitement.Classes.Systeme.Taxes.TaxeBio;
import marche.traitement.Classes.Systeme.Taxes.TaxeClassique;
import marche.traitement.Classes.Systeme.Taxes.TaxePetitProducteur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe correspondant a un marché
 */
public class Marche {

    /**
     * Correspond a la région ou se situe le marché
     */
    private String region;

    /**
     * Correspond au temps
     */
    private int uniteTemps;

    /**
     * Correspond a la Limite de temps avant la recolte des ta
     */
    private int delaiRecupTaxe;

    /**
     * Correspond a la bourse lier au marché
     */
    private Bourse maBourse;

    /**
     * Correspond au controleur du marché
     */
    private Controleur monControleur;

    /**
     * Correspond au Grand livre du marché qui contient les traces des transaction du dit marché
     */
    private GrandLivreDuMarche monGrandLivreDuMarche;

    /**
     * Correspond a la liste des participants du marché
     */
    private List<Participant>mesParticipants = new ArrayList<Participant>();

    /**
     * Constructeur par défaut de Marche
     */
    public Marche(String region) {
        this.region = region;
        this.uniteTemps = 0;
        this.delaiRecupTaxe = 3;
        this.maBourse = new Bourse(this);
        this.monControleur = new Controleur(this, new AlgoRepartitionAcheteursTemps());
        this.monGrandLivreDuMarche = new GrandLivreDuMarche(this);
        System.out.println("Création d'un nouveau marché dans la région " + this.region);
    }

    /**
     * Permet d'ajouter des participant au marché
     */
    public void ajouterParticipant(Participant participant){
        participant.setMonMarche(this);
        this.mesParticipants.add(participant);
        System.out.println("Ajout d'un nouveau participant: " + participant.getClass().getSimpleName());
    }

    /**
     * Permet de diminuer le temps avant la prochaine recolte des taxes
     */
    public void incrementerTemps() {
        ++this.uniteTemps;
        System.out.println("[i] Incrémentation du temps d'une unité (Temps actuel: " + this.uniteTemps + ")");
        verifierDelaiTaxe();
    }

    /**
     * Permet d'appliquer les taxes a tout les participants du marché
     */
    private void appliquerTaxe() {
        for(Participant participant : getMesParticipants()){
            if(participant.getClass().getSuperclass().getSimpleName().equals("Fermier")){
                if(((Fermier)participant).estPetitProducteur()) participant.setMaTaxe(new TaxePetitProducteur());
                else if(((Fermier)participant).estProducteurBIO()) participant.setMaTaxe(new TaxeBio());
                else participant.setMaTaxe(new TaxeClassique());
            }
            else participant.setMaTaxe(new TaxeClassique());
        }
    }

    /**
     * Permet de récuperer les taxes de tout les participant du marché
     */
    public void recupererTaxe() {
        appliquerTaxe();
        for(Participant participant : getMesParticipants()){
            Double prelevement = participant.getMaTaxe().recupererCotisation(participant.getSolde());
            participant.setSolde(participant.getSolde() - prelevement);
            getMonControleur().getArgentTaxe(prelevement);
        }
        System.out.println("[i] Récupération des taxes sur les participants effectuée...");
    }

    /**
     * Permet de verifier le temps avant la prochaine recolte des taxes
     */
    public void verifierDelaiTaxe(){
        if(uniteTemps == delaiRecupTaxe) {
            recupererTaxe();
            GrandLivreDuMarche.reinitialiserDernieresTransactions();
        }
    }

    /**
     * Permet de forcer toutes les ventes en cour du marché
     */
    public void forcerVentesEnCours(){
        for(Participant participant : getMesParticipants())
            for (Offre offre : participant.getMesOffres())
                if (offre.getClass().getSimpleName().equals("Vente"))
                    while(getMonControleur().getMonCatalogue().verifierSiOffreAchatPourVente((Vente)offre))
                        participant.forcerVente(getMonControleur().getMonCatalogue().getVente((Vente)offre));
    }

    /**
     * Getter de monGrandLivreDuMarche
     * @return le grand livre du marché
     */
    public GrandLivreDuMarche getMonGrandLivreDuMarche() {
        return monGrandLivreDuMarche;
    }

    /**
     * Setter de
     * @param monGrandLivreDuMarche grand livre du marché
     */
    public void setMonGrandLivreDuMarche(GrandLivreDuMarche monGrandLivreDuMarche) {
        this.monGrandLivreDuMarche = monGrandLivreDuMarche;
    }

    /**
     * Getter de region monGrandLivreDuMarche
     * @return : la région ou se situe le marché
     */
    public String getRegion() {
        return region;
    }

    /**
     * Getter de unitetemps
     * @return le temps
     */
    public Integer getUniteTemps() {
        return uniteTemps;
    }

    /**
     * Getter de delaiRecupTaxe
     * @return le delai avant la prochaine recolte des taxes
     */
    public Integer getDelaiRecupTaxe() { return delaiRecupTaxe; }

    /**
     * Getter de maBourse
     * @return la Bourse du marché
     */
    public Bourse getMaBourse() {
        return maBourse;
    }

    /**
     * Getter de monControleur
     * @return : le controleur du marché
     */
    public Controleur getMonControleur() {
        return monControleur;
    }

    /**
     * Getter de mesParticipants
     * @return les participant du marché
     */
    public List<Participant> getMesParticipants() {
        return mesParticipants;
    }


    public void setDelaiRecupTaxe(int delaiRecupTaxe) {
        this.delaiRecupTaxe = delaiRecupTaxe;
    }

    /**
     * Le Main
     * @param args arguments
     */
    public static void main(String[] args) {
        Marche marcheMarseille = new Marche("PACA");
        Apiculteur apiculteur = new Apiculteur(80);
        Horticulteur horticulteur = new Horticulteur(300);
        ProducteurDeViande producteurDeViande = new ProducteurDeViande(50);
        marcheMarseille.ajouterParticipant(apiculteur);
        marcheMarseille.ajouterParticipant(horticulteur);
        marcheMarseille.ajouterParticipant(producteurDeViande);
        marcheMarseille.ajouterParticipant(new Paysan());
        apiculteur.produire("miel","haute",80);
        apiculteur.getMonProduit(apiculteur.getMesProduits().get(0)).addLabel(new BIO());
        horticulteur.produire("carotte","moyenne",300);
        producteurDeViande.produire("vache","basse",5);
        apiculteur.proposerOffreVente(new Vente(apiculteur.getMonProduit(apiculteur.getMesProduits().get(0)),20,5.99));
        horticulteur.proposerOffreVente(new Vente(horticulteur.getMonProduit(horticulteur.getMesProduits().get(0)),300,0.49));
        producteurDeViande.proposerOffreVente(new Vente(producteurDeViande.getMonProduit(producteurDeViande.getMesProduits().get(0)),3,149.99));
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(0).ajouterAbonne(horticulteur);
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(1).ajouterAbonne(horticulteur);
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(0).ajouterAbonne(apiculteur);
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(0).notifierParticipant();
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(1).supprimerAbonne(apiculteur);
        marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(1).notifierParticipant();
        horticulteur.setSolde(1000);
        apiculteur.setSolde(1000);
        producteurDeViande.setSolde(50);
        apiculteur.proposerOffreAchat(new Achat(marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(1),250));
        horticulteur.proposerOffreAchat(new Achat(marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(2),1));
        producteurDeViande.proposerOffreAchat(new Achat(marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(0),3));
        horticulteur.proposerOffreAchat(new Achat(marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(0),100)); // Normalement offre refusée
        horticulteur.proposerOffreAchat(new Achat(marcheMarseille.getMonControleur().getMonCatalogue().getOffresVente().get(1),10)); // Normalement offre refusée
        marcheMarseille.forcerVentesEnCours();
    }
}