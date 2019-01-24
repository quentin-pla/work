package marche.affichage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Acteurs.Participant;
import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Vente;

import java.text.DecimalFormat;
import java.util.*;

public class AjoutOffreController {

    private Marche marcheActuel;

    private Participant participant;

    private ProduitFermier produit;

    private Vente vente;

    private Integer quantite;

    private Integer quantiteMax;

    private Double prixUnitaire;

    private Double prix;

    private String typeOffre;

    @FXML
    private VBox contenu, listeProduits, selectionParticipant, selectionOffre, groupeQuantite, groupePrix;

    @FXML
    private HBox listeTypeOffre, parametresOffre;

    @FXML
    private Label messageCreation, labelPrix;

    @FXML
    private TextField saisieQuantite, saisiePrix;

    @FXML
    private Button boutonValider, boutonSelectionParticipant;


    private void setRadioTypeOffre(String type){
        ToggleGroup choixTypeOffre = new ToggleGroup();
        RadioButton offreVente = new RadioButton("Vente");
        RadioButton offreAchat = new RadioButton("Achat");
        offreVente.setToggleGroup(choixTypeOffre);
        offreAchat.setToggleGroup(choixTypeOffre);
        offreVente.setId("radioButton");
        offreAchat.setId("radioButton");
        listeTypeOffre.getChildren().addAll(offreVente,offreAchat);

        if(type != null){
            if(type.equals("V")){
                choixTypeOffre.selectToggle(offreVente);
                affichageVente();
                parametresOffre.setVisible(true);
                boutonSelectionParticipant.setDisable(false);
            }
            else {
                choixTypeOffre.selectToggle(offreAchat);
                affichageAchat();
                parametresOffre.setVisible(true);
                if(vente != null) boutonSelectionParticipant.setDisable(false);
                else boutonSelectionParticipant.setDisable(true);
            }
        }

        choixTypeOffre.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixTypeOffre.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    typeOffre = radioButton.getText().substring(0,1);
                    if(typeOffre.equals("V")){
                        affichageVente();
                    }
                    else {
                        affichageAchat();
                    }
                    parametresOffre.setVisible(true);
                }
            }
        });
    }

    /**
     * Affiche une vente
     */
    private void affichageVente(){
        listeProduits.setVisible(true);
        listeProduits.setManaged(true);
        selectionOffre.setVisible(false);
        selectionOffre.setManaged(false);
        labelPrix.setText("Prix unitaire");
        boutonSelectionParticipant.setDisable(false);
    }

    /**
     * Affiche un achat
     */
    private void affichageAchat(){
        selectionOffre.setVisible(true);
        selectionOffre.setManaged(true);
        listeProduits.setVisible(false);
        listeProduits.setManaged(false);
        labelPrix.setText("Prix");
        saisiePrix.setDisable(true);
        if(vente != null) boutonSelectionParticipant.setDisable(false);
        else boutonSelectionParticipant.setDisable(true);
    }

    /**
     * Affiche les différents éléments d'un produit
     */
    private void afficherProduits(){
        ToggleGroup choixProduit = new ToggleGroup();
        RadioButton radioButton;

        Set<ProduitFermier> listeProduitUnique = new HashSet<>(participant.getMesProduits());
        
        for(ProduitFermier produitFermier : listeProduitUnique){
            String nomProduit = produitFermier.getNom();
            if(!produitFermier.getMesLabels().isEmpty()) {
                nomProduit += (" (");
                if(produitFermier.getMesLabels().size() == 1){
                    nomProduit += produitFermier.getMesLabels().get(0).getClass().getSimpleName();
                }
                else {
                    for (marche.traitement.Classes.Produits.Labels.Label label : produitFermier.getMesLabels())
                        nomProduit += label.getClass().getSimpleName() + " ";
                }
                nomProduit += (")");
            }
            radioButton = new RadioButton(nomProduit);
            radioButton.setToggleGroup(choixProduit);
            radioButton.setId("radioButton");
            radioButton.setUserData(participant.getMesProduits().indexOf(produitFermier));
            listeProduits.getChildren().add(radioButton);
        }

        choixProduit.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle ancien, Toggle nouveau) {
                if (choixProduit.getSelectedToggle() != null) {
                    RadioButton radioButton = (RadioButton)nouveau.getToggleGroup().getSelectedToggle();
                    Integer produitSelectionne = (Integer)radioButton.getUserData();
                    produit = participant.getMonProduit(participant.getMesProduits().get(produitSelectionne));
                    quantiteMax = participant.getStockProduit(produit);
                    if(quantiteMax > 1) saisieQuantite.setPromptText("1 - " + quantiteMax.toString());
                    else saisieQuantite.setPromptText(quantiteMax.toString());
                    prixUnitaire = marcheActuel.getMaBourse().getPrixMoyenProduit(produit);
                    saisiePrix.setPromptText(new DecimalFormat("0.00").format(prixUnitaire) + "€");
                    saisieQuantite.setDisable(false);
                    saisiePrix.setDisable(false);
                    boutonValider.setDisable(false);
                    corrigerQuantitePrixVente();
                }
            }
        });
        if(vente != null && participant != null) boutonValider.setDisable(false);
    }

    private void corrigerQuantitePrixVente(){
        saisieQuantite.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean verifNombre = saisieQuantite.getText().chars().allMatch(Character::isDigit);
            if(verifNombre && saisieQuantite.getText().length() > 0){
                quantite = Integer.parseInt(saisieQuantite.getText());
                if(quantite > quantiteMax){
                    quantite = quantiteMax;
                    saisieQuantite.setText(quantite.toString());
                }
                else if(quantite <= 0){
                    quantite = 1;
                    saisieQuantite.setText(quantite.toString());
                }
            }
            else {
                quantite = 1;
                Platform.runLater(() -> {
                    saisieQuantite.clear();
                });
            }
        });

        saisiePrix.textProperty().addListener((observable, oldValue, newValue) -> {
            String saisieNombre = saisiePrix.getText().replace(".","");
            boolean verifNombre = saisieNombre.chars().allMatch(Character::isDigit);
            int nombrePoint = saisiePrix.getText().length() - saisiePrix.getText().replace(".","").length();
            if (saisiePrix.getText().length() > 0 && verifNombre && nombrePoint <= 1){
                if(saisiePrix.getCharacters().charAt(0) == '.') {
                    saisiePrix.setText("0.");
                    prixUnitaire = 0.0;
                }
                else prixUnitaire = Double.parseDouble(saisiePrix.getText());
            }
            else {
                Platform.runLater(() -> {
                    saisiePrix.clear();                                  
                });
                prixUnitaire = marcheActuel.getMaBourse().getPrixMoyenProduit(produit);
            }
        });
    }

    private void afficherPrixAchat(){
        Double copieSolde = participant.getSolde();
        prixUnitaire = vente.getPrixUnite();
        quantiteMax = 0;
        while ((copieSolde >= prixUnitaire) && prixUnitaire != 0)
        {
            copieSolde -= prixUnitaire;
            quantiteMax++;
        }
        if(quantiteMax >= vente.getQuantite()) quantiteMax = vente.getQuantite();
        quantite = 1;
        if(quantiteMax > 1) saisieQuantite.setPromptText("1 - " + quantiteMax.toString());
        else saisieQuantite.setPromptText("1");
        saisiePrix.setText(new DecimalFormat("0.00").format(prixUnitaire) + "€");
        saisieQuantite.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean verifNombre = saisieQuantite.getText().chars().allMatch(Character::isDigit);
            if(verifNombre && saisieQuantite.getText().length() > 0){
                boutonValider.setDisable(false);
                quantite = Integer.parseInt(saisieQuantite.getText());
                if(quantite > quantiteMax){
                    quantite = quantiteMax;
                    saisieQuantite.setText(quantite.toString());
                }
                else if(quantite <= 0){
                    quantite = 1;
                    saisieQuantite.setText(quantite.toString());
                }
                prix = quantite*prixUnitaire;
                saisiePrix.setText(new DecimalFormat("0.00").format(prix) + "€");
            }
            else {
                quantite = 1;
                Platform.runLater(() -> {
                    saisieQuantite.clear();
                });
            }
        });
    }

    @FXML
    private void creerOffre(){
        if(typeOffre.equals("V")){
            if (saisieQuantite.getText().length() > 0) quantite = Integer.parseInt(saisieQuantite.getText());
            else quantite = 1;
            if (!saisiePrix.getText().equals("0.") && saisiePrix.getText().length() > 0) prixUnitaire = Double.parseDouble(saisiePrix.getText());
            Vente vente = new Vente(produit, quantite, prixUnitaire);
            if(prixUnitaire > 0 && quantite <= quantiteMax && marcheActuel.getMonControleur().verifierEncadrementPrix(vente)) {
                participant.proposerOffreVente(vente);
                messageCreation.setText("Offre de vente ajoutée dans le catalogue");
                messageCreation.setId("messageCreation");
            }
            else{
                if(quantite > quantiteMax) messageCreation.setText("Pas assez de stock disponible pour créer l'offre");
                else messageCreation.setText("Marge trop élevée par rapport au prix en bourse");
                messageCreation.setId("erreurCreation");
            }
            messageCreation.setVisible(true);
        }
        else {
            participant.proposerOffreAchat(new Achat(vente,quantite));
            messageCreation.setText("Offre d'achat ajoutée dans le catalogue");
            messageCreation.setId("messageCreation");
            messageCreation.setVisible(true);
        }
    }

    private void afficherInfoParticipant(){
        Label role = new Label(participant.getClass().getSimpleName());
        role.setId("texte");
        selectionParticipant.getChildren().add(role);
        if(typeOffre.equals("A")){
            saisieQuantite.setDisable(false);
            boutonValider.setDisable(false);
        }
    }

    private void afficherInfoVente(){
        Label produit = new Label(vente.getProduit().getNom());
        produit.setId("texte");
        selectionOffre.getChildren().add(produit);
        boutonSelectionParticipant.setDisable(false);
    }

    @FXML
    private void afficherParticipantsMarcheSelection(){
        String donnees = typeOffre + getIndexMarcheActuelString();
        if(typeOffre.equals("A")) donnees += ((Integer)marcheActuel.getMonControleur().getMonCatalogue().getOffresVente().indexOf(vente)).toString();
        Main.changerPage("ParticipantsMarche_Selection",donnees);
    }

    @FXML
    private void afficherOffresMarcheSelection(){
        Main.changerPage("OffresMarche_Selection",typeOffre + getIndexMarcheActuelString());
    }

    @FXML
    private void afficherOffresMarche(){
        Main.changerPage("OffresMarche",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        if(participant != null && vente != null){
            if(typeOffre.equals("A")) afficherPrixAchat();
        }
        setRadioTypeOffre(this.typeOffre);
    }

    public void setParticipant(Participant participant){
        this.participant = participant;
        afficherInfoParticipant();
        if(typeOffre.equals("V")) afficherProduits();
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
        afficherInfoVente();
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
