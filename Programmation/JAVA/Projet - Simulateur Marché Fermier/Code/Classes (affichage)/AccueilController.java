package marche.affichage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Systeme.Marche;

public class AccueilController {

    @FXML
    private VBox contenu;

    @FXML
    private HBox ajoutMarche;

    @FXML
    private HBox marchesDispo;

    @FXML
    private Button retour;

    @FXML
    private Label titreCreer;

    @FXML
    private Label titreChoix;

    @FXML
    private Label erreurCreation;

    @FXML
    private TextField saisieRegion;

    @FXML
    private void initialize(){
        verifierMarche();
    }

    /**
     * Vérifie si il existe un marché, si ce n'est pas le cas permet d'en créer un
     */
    private void verifierMarche(){
        if(Main.marches.isEmpty()){
            afficherCreationMarche();
        }
        else{
            afficherMarchesDispo();
        }
    }

    /**
     * Ajouter un bouton
     * @param titre Titre du bouton
     * @param donnee Données associées au bouton
     * @return le bouton
     */
    private Button ajouterBouton(String titre, Integer donnee){
        DataButton bouton = new DataButton(titre, donnee);
        bouton.setPrefSize(200,200);
        bouton.setOnAction(e -> afficherMarche(bouton.getDonnee()));
        return bouton;
    }

    /**
     * Affiche l'interface de création de marché
     */
    private void afficherCreationMarche(){
        erreurCreation.setVisible(false);
        erreurCreation.setManaged(true);
        retour.setVisible(true);
        titreChoix.setVisible(false);
        titreChoix.setManaged(false);
        marchesDispo.setVisible(false);
        marchesDispo.setManaged(false);
        titreCreer.setVisible(true);
        titreCreer.setManaged(true);
        ajoutMarche.setVisible(true);
        ajoutMarche.setManaged(true);
        saisieRegion.clear();
        saisieRegion.requestFocus();
    }

    @FXML
    /**
     * Affiche les marchés créés disponibles
     */
    private void afficherMarchesDispo(){
        erreurCreation.setVisible(false);
        erreurCreation.setManaged(false);
        retour.setVisible(false);
        marchesDispo.getChildren().clear();
        titreCreer.setVisible(false);
        titreCreer.setManaged(false);
        ajoutMarche.setVisible(false);
        ajoutMarche.setManaged(false);
        titreChoix.setVisible(true);
        titreChoix.setManaged(true);
        marchesDispo.setVisible(true);
        marchesDispo.setManaged(true);
        for (Marche marche : Main.marches){
            marchesDispo.getChildren().add(ajouterBouton(marche.getRegion(), Main.marches.indexOf(marche)));
        }
        if(Main.marches.size() <= 2){
            Button ajouter = new Button("+");
            ajouter.setStyle("-fx-font-size: 50px");
            ajouter.setPrefSize(200,200);
            ajouter.setOnAction(e -> afficherCreationMarche());
            marchesDispo.getChildren().add(ajouter);
        }
    }

    @FXML
    /**
     * Permet de créer un marché
     */
    private void creerMarche(){
        String region = saisieRegion.getText().toLowerCase();

        boolean verifAlphabet = region.chars().allMatch(Character::isLetter);

        if(region.length() >= 3 && region.length() <= 20 && verifAlphabet){
            region = region.substring(0, 1).toUpperCase() + region.substring(1);
            boolean marcheExisteDeja = false;
            for(Marche marche : Main.marches){
                if(marche.getRegion().equals(saisieRegion.getText())){
                    erreurCreation.setVisible(true);
                    erreurCreation.setText("Erreur, marché déjà existant !");
                    marcheExisteDeja = true;
                }
            }
            if(!marcheExisteDeja){
                Main.marches.add(new Marche(region));
                ajoutMarche.setVisible(false);
                titreCreer.setVisible(false);
                afficherMarchesDispo();
            }
        }
        else{
            erreurCreation.setVisible(true);
            if(!verifAlphabet) erreurCreation.setText("Erreur, caractères non alphabétiques !");
            else if(saisieRegion.getText().length() < 3) erreurCreation.setText("Erreur, nom trop court ! (3 caractères minimum)");
            else erreurCreation.setText("Erreur, nom trop long ! (20 caractères maximum)");
        }
    }

    /**
     * Affiche les différents éléments d'un marché
     * @param indexMarche
     */
    private void afficherMarche(Integer indexMarche){
        Main.changerPage("Marche",indexMarche.toString());
    }
}
