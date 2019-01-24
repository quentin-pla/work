package marche.affichage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Produits.Labels.*;
import marche.traitement.Classes.Systeme.Marche;

import java.util.Arrays;
import java.util.List;

public class AjoutProduitController {

    private Marche marcheActuel;

    private Fermier fermier;

    private marche.traitement.Classes.Produits.Labels.Label label;

    private String nom;

    private Integer quantite;

    private String qualite;

    @FXML
    private VBox groupeLabel, groupeQualite, selectionParticipant;

    @FXML
    private Label messageCreation;

    @FXML
    private TextField saisieNom, saisieQuantite;

    @FXML
    private Button boutonValider, boutonSelectionParticipant, retour;

    private void setRadios(){
        ToggleGroup choixLabel = new ToggleGroup();
        RadioButton type1 = new RadioButton("Aucun");
        RadioButton type2 = new RadioButton("BIO");
        RadioButton type3 = new RadioButton("AOP");
        RadioButton type4 = new RadioButton("AOC");
        RadioButton type5 = new RadioButton("IGP");
        RadioButton type6 = new RadioButton("Label Rouge");
        List<RadioButton> radioButtons = Arrays.asList(type1,type2,type3,type4,type5,type6);
        for(RadioButton radioButton : radioButtons){
            radioButton.setToggleGroup(choixLabel);
            radioButton.setId("radioButton");
        }
        groupeLabel.getChildren().addAll(type1,type2,type3,type4,type5,type6);

        choixLabel.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixLabel.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    switch (radioButton.getText()){
                        case "Aucun":
                            label = null;
                            break;
                        case "AOC":
                            label = new AOC();
                            break;
                        case "AOP":
                            label = new AOP();
                            break;
                        case "BIO":
                            label = new BIO();
                            break;
                        case "IGP":
                            label = new IGP();
                            break;
                        case "Label Rouge":
                            label = new LabelRouge();
                            break;
                    }
                    saisieNom.setDisable(false);
                    saisieQuantite.setDisable(false);
                    boutonValider.setDisable(false);
                    corrigerNom();
                    corrigerQuantite();
                }
            }
        });

        ToggleGroup choixQualite = new ToggleGroup();
        RadioButton haute = new RadioButton("Haute");
        RadioButton moyenne = new RadioButton("Moyenne");
        RadioButton basse = new RadioButton("Basse");
        radioButtons = Arrays.asList(haute,moyenne,basse);
        for(RadioButton radioButton : radioButtons){
            radioButton.setToggleGroup(choixQualite);
            radioButton.setId("radioButton");
        }
        groupeQualite.getChildren().addAll(haute,moyenne,basse);

        choixQualite.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixQualite.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    qualite = radioButton.getText();
                    groupeLabel.setDisable(false);
                }
            }
        });

    }

    @FXML
    private void creerProduit(){
        if(nom.length() > 0 && quantite > 0 && qualite != null && label != null) fermier.produire(nom,qualite,quantite, label);
        else if(nom.length() > 0 && quantite > 0 && qualite != null) fermier.produire(nom,qualite,quantite);
        messageCreation.setText("Production de " + fermier.getMonUsine().getNomProduit() + " pour " + quantite + " unité(s)");
        messageCreation.setVisible(true);
    }

    private void corrigerNom(){
        nom = fermier.getMonUsine().getNomProduit();
        saisieNom.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean verifAlpha = saisieNom.getText().chars().allMatch(Character::isLetter);
            if(verifAlpha && saisieNom.getText().length() > 0){
                nom = saisieNom.getText();
                saisieQuantite.setDisable(false);
                saisieNom.setText(nom);
                corrigerQuantite();
            }
            else {
                nom = fermier.getMonUsine().getNomProduit();
                Platform.runLater(() -> {
                    saisieNom.clear();
                });
            }
        });
    }

    private void corrigerQuantite(){
        quantite = 1;
        saisieQuantite.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean verifNombre = saisieQuantite.getText().chars().allMatch(Character::isDigit);
            if(verifNombre && saisieQuantite.getText().length() > 0) {
                Integer quantiteAProduire = Integer.parseInt(saisieQuantite.getText());
                if(quantiteAProduire > 0 && quantiteAProduire <= (fermier.getLimiteProduction() - fermier.getQuantiteProduction())){
                    quantite = Integer.parseInt(saisieQuantite.getText());
                    saisieQuantite.setText(quantite.toString());
                }
                else if(quantiteAProduire <= 0){
                    quantite = 1;
                    saisieQuantite.setText(quantite.toString());
                }
                else {
                    quantite = (fermier.getLimiteProduction() - fermier.getQuantiteProduction());
                    saisieQuantite.setText(quantite.toString());
                }
            }
            else {
                quantite = 1;
            }
        });
    }

    private void afficherInfoFermier(){
        Label role = new Label(fermier.getClass().getSimpleName());
        role.setId("texte");
        selectionParticipant.getChildren().add(role);
        saisieNom.setPromptText(fermier.getMonUsine().getNomProduit());
        saisieQuantite.setPromptText("1 - " + String.valueOf(fermier.getLimiteProduction() - fermier.getQuantiteProduction()));
        groupeQualite.setDisable(false);
    }

    @FXML
    private void afficherParticipantsMarcheSelection(){
        String donnees = "P" + getIndexMarcheActuelString();
        Main.changerPage("ParticipantsMarche_Selection",donnees);
    }

    @FXML
    private void afficherBourse(){
        Main.changerPage("Bourse",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        setRadios();
    }

    public void setFermier(Fermier fermier) {
        this.fermier = fermier;
        afficherInfoFermier();
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
