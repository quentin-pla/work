package fr.univ_amu.iut;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/*public*/ class ParametresControl extends BorderPane {

    private static FadeTransition popupFade = new FadeTransition(Duration.seconds(0.12));

    private static final BoxBlur blurEffect = new BoxBlur(0, 0, 10);

    private static Timeline blurFade = new Timeline();

    @FXML
    private BorderPane parametresRoot;

    @FXML
    private VBox vBox;

    @FXML
    private TextField produit;

    @FXML
    private TextField titre;

    @FXML
    private Label note;

    @FXML
    private TextArea description;

    @FXML
    private Button ajouterPhoto;

    @FXML
    private Button valider;

    @FXML
    private SVGPath star1;

    @FXML
    private SVGPath star2;

    @FXML
    private SVGPath star3;

    @FXML
    private SVGPath star4;

    @FXML
    private SVGPath star5;

    @FXML
    private SVGPath starICON;

    @FXML
    private SVGPath searchICON;

    @FXML
    private SVGPath profileICON;

    @FXML
    private SVGPath settingsICON;

    @FXML
    private Button log;

    /*public*/ ParametresControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Parametres.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();
    }

    private void initialize() {
        starICON.setContent(SvgPicture.getStarSVG());
        searchICON.setContent(SvgPicture.getSearchSVG());
        profileICON.setContent(SvgPicture.getProfileSVG());
        settingsICON.setContent(SvgPicture.getSettingsSVG());
        setLogButton();
    }

    @FXML
    private void Accueil() {
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new AccueilControl()));
    }

    @FXML
    private void Selection() {
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    @FXML
    private void Profil() {
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    @FXML
    private void setLogButton() {
        if (!Main.getSystem().getIsConnected()) {
            log.setStyle("-fx-background-color: #009e0f ; -fx-border-color: #004c07");
            log.setText("Connexion");
        } else {
            log.setStyle("-fx-background-color: #ff0000 ; -fx-border-color: #680000");
            log.setText("Déconnexion");
        }
    }

    @FXML
    private void Disconnect() {
        Main.getSystem().setIsConnected(false);
        Main.startNewPage((Stage) parametresRoot.getScene().getWindow(), new Scene(new AccueilControl()));
    }
}