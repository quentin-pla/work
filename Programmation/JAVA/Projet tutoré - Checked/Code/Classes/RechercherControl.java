package fr.univ_amu.iut;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.io.IOException;

/**
 * Search a comment, product, user...
 */
/*public*/ class RechercherControl extends BorderPane {

    @FXML
    private SVGPath starICON;

    @FXML
    private SVGPath searchICON;

    @FXML
    private SVGPath profileICON;

    @FXML
    private SVGPath settingsICON;

    @FXML
    private BorderPane rechercherRoot;

    @FXML
    private VBox vBoxContent;

    @FXML
    private SVGPath searchBarIcon;

    @FXML
    private TextField searchBar;

    @FXML
    private VBox test;

    @FXML
    private Region arrow;

    @FXML
    private Region logo;

    /**
     * Default constructor
     */
    /*public*/ RechercherControl() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Rechercher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        searchBar.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                vBoxContent.requestFocus();
                firstTime.setValue(false);
            }
        });
        initialize();
    }

    /**
     * Initialization
     */
    private void initialize() {
        starICON.setContent(SvgPicture.getStarSVG());
        searchICON.setContent(SvgPicture.getSearchSVG());
        profileICON.setContent(SvgPicture.getProfileSVG());
        settingsICON.setContent(SvgPicture.getSettingsSVG());
        searchBarIcon.setContent(SvgPicture.getSearchSVG());
        Main.getSystem().addContentRechercher(this);
    }

    /**
     * load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) rechercherRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) rechercherRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) rechercherRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) rechercherRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * Display all the categories
     */
    public void displayCategories() {
        for (Category c : Category.getAllCategories()) {
            CategoryControl cc = new CategoryControl(c);
            if (cc.getMyCategory().getParent() == null) {
                vBoxContent.getChildren().add(cc);
                if (cc.getMyCategory().getIsUpperCategory()) cc.displayArrow();
            }
        }
    }
}
