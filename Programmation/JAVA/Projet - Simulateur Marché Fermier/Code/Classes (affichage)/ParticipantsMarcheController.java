package marche.affichage;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Vente;

import java.util.List;

public class ParticipantsMarcheController {

    private Marche marcheActuel;

    private List<Participant> participants;

    private boolean selectionner;

    private String typeRetour;

    private Integer retourVente;

    @FXML
    private VBox contenu, tableContent;

    @FXML
    private Button ajouter, retour;

    @FXML
    private Label titre;

    private void afficherParticipants(){
        tableContent.getChildren().add(ligneTop());
        for(Participant participant : participants){
            if(selectionner && typeRetour.equals("V")){
                if(participant.getMesProduits().size() > 0) tableContent.getChildren().add(creerLigne(participant));
            }
            else if(selectionner && typeRetour.equals("A")){
                Vente vente = marcheActuel.getMonControleur().getMonCatalogue().getOffresVente().get(retourVente);
                if(participant.getSolde() > vente.getPrixUnite() && !participant.equals(vente.getParticipant())) tableContent.getChildren().add(creerLigne(participant));
            }
            else if(selectionner && typeRetour.equals("P")){
                if(participant.getClass().getSuperclass().getSimpleName().equals("Fermier") && ((Fermier) participant).getQuantiteProduction() < ((Fermier) participant).getLimiteProduction()) tableContent.getChildren().add(creerLigne(participant));
            }
            else tableContent.getChildren().add(creerLigne(participant));
        }
    }

    private HBox creerLigne(Participant participant){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(0,5,0,5));
        ligne.setId("ligne");
        Label role = new Label(participant.getClass().getSimpleName());
        Label solde = new Label(participant.getSolde().toString() + "€");
        Label gain = new Label(participant.getGain().toString() + "€");
        role.setId("label");
        solde.setId("label");
        gain.setId("label");
        role.setPrefWidth(160);
        solde.setPrefWidth(120);
        gain.setPrefWidth(120);
        Label production;
        if(participant instanceof Fermier) production = new Label(((Fermier)participant).getQuantiteProduction().toString() + "/" + ((Fermier)participant).getLimiteProduction().toString() + " unités");
        else production = new Label("x");
        production.setId("label");
        production.setPrefWidth(140);
        Label offresvente = new Label(getNombreOffresVente(participant).toString());
        Label offresachat = new Label(getNombreOffresAchat(participant).toString());
        offresvente.setId("label");
        offresachat.setId("label");
        offresvente.setPrefWidth(125);
        offresachat.setPrefWidth(123);
        ligne.getChildren().addAll(role,solde,gain,production,offresvente,offresachat);
        if(selectionner){
            ligne.setCursor(Cursor.HAND);
            String donnees;
            if(typeRetour.equals("V") || typeRetour.equals("A")) {
                donnees = typeRetour + ((Integer) Main.marches.indexOf(marcheActuel)).toString() + ((Integer) marcheActuel.getMesParticipants().indexOf(participant)).toString();
                if (typeRetour.equals("A")) donnees += retourVente.toString();
                final String finalDonnees = donnees;
                ligne.setOnMouseClicked(e -> Main.changerPage("AjoutOffre_RetourParticipant", finalDonnees));
            }
            else if(typeRetour.equals("P")) {
                donnees = ((Integer) Main.marches.indexOf(marcheActuel)).toString() + ((Integer) marcheActuel.getMesParticipants().indexOf(participant)).toString();
                final String finalDonnees = donnees;
                ligne.setOnMouseClicked(e -> Main.changerPage("AjoutProduit_RetourParticipant", finalDonnees));
            }
        }
        return ligne;
    }

    private HBox ligneTop(){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(5));
        ligne.setId("ligneTop");
        Label role = new Label("Rôle");
        Label solde = new Label("Solde");
        Label gain = new Label("Gains");
        Label production = new Label("Production");
        Label offresvente = new Label("Offres Vente");
        Label offresachat = new Label("Offres Achat");
        role.setId("label");
        solde.setId("label");
        gain.setId("label");
        production.setId("label");
        offresvente.setId("label");
        offresachat.setId("label");
        role.setPrefWidth(160);
        solde.setPrefWidth(120);
        gain.setPrefWidth(120);
        production.setPrefWidth(140);
        offresvente.setPrefWidth(125);
        offresachat.setPrefWidth(123);
        ligne.getChildren().addAll(role,solde,gain,production,offresvente,offresachat);
        return ligne;
    }

    private Integer getNombreOffresVente(Participant participant){
        int cpt = 0;
        List<Vente>ventes = marcheActuel.getMonControleur().getMonCatalogue().getOffresVente();
        for(Vente vente : ventes) if(vente.getParticipant().equals(participant))++cpt;
        return cpt;
    }

    private Integer getNombreOffresAchat(Participant participant){
        int cpt = 0;
        List<Achat>achats = marcheActuel.getMonControleur().getMonCatalogue().getOffresAchat();
        for(Achat achat : achats) if(achat.getParticipant().equals(participant))++cpt;
        return cpt;
    }

    @FXML
    private void afficherPageAjout(){
        Main.changerPage("AjoutParticipant",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherPageAjoutOffre(){
        Main.changerPage("AjoutOffre",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherPageAjoutProduit(){
        Main.changerPage("AjoutProduit",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherMarche(){
        Main.changerPage("Marche",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        this.participants = marcheActuel.getMesParticipants();
        afficherParticipants();
    }

    public void seulementSelectionner(){
        selectionner = true;
        ajouter.setVisible(false);
        ajouter.setManaged(false);
        titre.setText("Sélection");
        if(typeRetour.equals("P")) retour.setOnAction(e -> afficherPageAjoutProduit());
        else retour.setOnAction(e -> afficherPageAjoutOffre());
    }

    public void setTypeRetour(String typeRetour) {
        this.typeRetour = typeRetour;
    }

    public void setRetourVente(Integer index){
        this.retourVente = index;
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
