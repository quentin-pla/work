package marche.affichage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Systeme.AlgoRepartition.AlgoRepartitionAcheteursPetiteQuantite;
import marche.traitement.Classes.Systeme.AlgoRepartition.AlgoRepartitionAcheteursTemps;
import marche.traitement.Classes.Systeme.Marche;

public class ParametresMarcheController {

    private Marche marcheActuel;

    @FXML
    private VBox contenu, listeAlgo;

    @FXML
    private Label nomMarche, uniteTemps, taxesRecup;

    @FXML
    private Button boutonForcerVentes, boutonValider;

    @FXML
    private TextField saisieDelai;

    @FXML
    private void initialize(){}

    private void setRadio(){
        ToggleGroup choixAlgo = new ToggleGroup();
        RadioButton algoTemps = new RadioButton("Algorithme sélection temps");
        RadioButton algoPetitAchat = new RadioButton("Algorithme sélection quantité");
        algoTemps.setToggleGroup(choixAlgo);
        algoPetitAchat.setToggleGroup(choixAlgo);
        algoTemps.setId("radioButton");
        algoPetitAchat.setId("radioButton");
        listeAlgo.getChildren().addAll(algoTemps,algoPetitAchat);

        if(marcheActuel.getMonControleur().getMonAlgoSelectionAcheteur().getClass().getSimpleName().equals("AlgoRepartitionAcheteursTemps"))
            choixAlgo.selectToggle(algoTemps);
        else
            choixAlgo.selectToggle(algoPetitAchat);

        choixAlgo.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixAlgo.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    String algo = radioButton.getText().substring(0,1);
                    if(algo.equals("Algorithme sélection temps")){
                        marcheActuel.getMonControleur().setMonAlgoSelectionAcheteur(new AlgoRepartitionAcheteursTemps());
                    }
                    else {
                        marcheActuel.getMonControleur().setMonAlgoSelectionAcheteur(new AlgoRepartitionAcheteursPetiteQuantite());
                    }
                }
            }
        });
    }

    private void setSaisieDelai(){
        saisieDelai.setPromptText(marcheActuel.getDelaiRecupTaxe().toString());
        saisieDelai.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean verifNombre = saisieDelai.getText().chars().allMatch(Character::isDigit);
                if(verifNombre && saisieDelai.getText().length() > 0 && !saisieDelai.getText().equals("0")) {
                    marcheActuel.setDelaiRecupTaxe(Integer.parseInt(saisieDelai.getText()));
                }
                else {
                    Platform.runLater(() -> {
                        saisieDelai.clear();
                    });
                }
            }
        });
    }

    @FXML
    private void forcerVentesMarche(){
        Main.marches.get(Main.marches.indexOf(marcheActuel)).forcerVentesEnCours();
        setUniteTemps(this.marcheActuel.getUniteTemps());
        setTaxesRecup(this.marcheActuel.getMonControleur().getSolde());
    }

    @FXML
    private void afficherMarche(){
        Main.changerPage("Marche",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marcheActuel) {
        this.marcheActuel = marcheActuel;
        setNomMarche(this.marcheActuel.getRegion());
        setUniteTemps(this.marcheActuel.getUniteTemps());
        setTaxesRecup(this.marcheActuel.getMonControleur().getSolde());
        setRadio();
        setSaisieDelai();
    }

    public void setNomMarche(String nomMarche) {
        this.nomMarche.setText("Marché de " + nomMarche);
    }

    private void setUniteTemps(Integer valeur){
        uniteTemps.setText("Unite de temps: " + valeur.toString());
    }

    private void setTaxesRecup(Double valeur){
        taxesRecup.setText("Taxes récupérées: " + valeur.toString());
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
