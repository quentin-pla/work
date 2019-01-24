package marche.affichage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Acteurs.*;
import marche.traitement.Classes.Acteurs.Fermier.*;
import marche.traitement.Classes.Systeme.Marche;

import java.util.Arrays;
import java.util.List;

public class AjoutParticipantController {

    private Marche marcheActuel;

    private String type;

    private Double solde = 0.0;

    private Integer limiteProduction = 20;

    @FXML
    private VBox contenu, groupeType, groupeSaisie, groupeLimiteProd;

    @FXML
    private Label messageCreation;

    @FXML
    private TextField saisieLimiteProd, saisieSolde;

    @FXML
    private Button boutonValider;

    private void setRadios(){
        ToggleGroup choixType = new ToggleGroup();
        RadioButton type1 = new RadioButton("Apiculteur");
        RadioButton type2 = new RadioButton("Arboriculteur");
        RadioButton type3 = new RadioButton("Horticulteur");
        RadioButton type4 = new RadioButton("Producteur de viande");
        RadioButton type5 = new RadioButton("Producteur laitier");
        RadioButton type6 = new RadioButton("Centrale d'achat");
        RadioButton type7 = new RadioButton("Grossiste");
        RadioButton type8 = new RadioButton("Trader");
        RadioButton type9 = new RadioButton("Paysan");
        List<RadioButton>radioButtons = Arrays.asList(type1,type2,type3,type4,type5,type6,type7,type8,type9);
        for(RadioButton radioButton : radioButtons){
            radioButton.setToggleGroup(choixType);
            radioButton.setId("radioButton");
        }
        groupeType.getChildren().addAll(type1,type2,type3,type4,type5,type6,type7,type8,type9);

        choixType.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixType.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    type = radioButton.getText();
                    switch (type){
                        case "Apiculteur": case "Arboriculteur": case "Horticulteur": case "Producteur de viande": case "Producteur laitier":
                            groupeLimiteProd.setVisible(true);
                            groupeLimiteProd.setManaged(true);
                            break;
                        default:
                            groupeLimiteProd.setVisible(false);
                            groupeLimiteProd.setManaged(false);
                            break;
                    }
                    saisieSolde.setDisable(false);
                    boutonValider.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void creerParticipant(){
        if(saisieSolde.getText().length() > 0) solde = Double.parseDouble(saisieSolde.getText());
        if(saisieLimiteProd.getText().length() > 0) limiteProduction = Integer.parseInt(saisieLimiteProd.getText());
        Participant participant = null;
        switch(type){
            case "Apiculteur":
                participant = new Apiculteur(limiteProduction);
                break;
            case "Arboriculteur":
                participant = new Arboriculteur(limiteProduction);
                break;
            case "Horticulteur":
                participant = new Horticulteur(limiteProduction);
                break;
            case "Producteur de viande":
                participant = new ProducteurDeViande(limiteProduction);
                break;
            case "Producteur laitier":
                participant = new ProducteurLaitier(limiteProduction);
                break;
            case "Grossiste":
                participant = new Grossiste();
                break;
            case "Centrale d'achat":
                participant = new CentraleAchat();
                break;
            case "Trader":
                participant = new Trader();
                break;
            case "Paysan":
                participant = new Paysan();
                break;
        }
        if(participant != null){
            participant.setSolde(solde);
            marcheActuel.ajouterParticipant(participant);
            String message;
            message = type;
            if(type.equals("Centrale d'achat")) message += " ajoutée dans le marché de ";
            else message += " ajouté dans le marché de ";
            message += marcheActuel.getRegion();
            messageCreation.setText(message);
            messageCreation.setVisible(true);
        }
    }

    @FXML
    private void afficherParticipantsMarche(){
        Main.changerPage("ParticipantsMarche",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        setRadios();
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
