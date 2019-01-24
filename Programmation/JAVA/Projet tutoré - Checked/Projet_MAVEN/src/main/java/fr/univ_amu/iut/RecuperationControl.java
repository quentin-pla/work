package fr.univ_amu.iut;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * If you lost your password
 */
/*public*/ class RecuperationControl extends VBox {

    /**
     * Contains everything in the page
     */
    @FXML
    private VBox vBox;

    /**
     * Contains return
     */
    @FXML
    private HBox top;

    /**
     * Mail address of the user
     */
    @FXML
    private TextField adressemail;

    /**
     * Default constructor
     */
    /*public*/ RecuperationControl() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Recuperation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        adressemail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                vBox.requestFocus();
                firstTime.setValue(false);
            }
        });
    }

    /**
     * load Accueil page
     */
    @FXML
    private void Accueil() {
        Main.startNewPage((Stage) vBox.getScene().getWindow(), new Scene(new AccueilControl()));
    }

}