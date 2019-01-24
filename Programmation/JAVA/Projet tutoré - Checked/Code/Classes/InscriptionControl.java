package fr.univ_amu.iut;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Inscription page
 */
class InscriptionControl extends VBox {

    /**
     * Line of the draw
     */
    @FXML
    private Path path;

    /**
     * Contains everything of the page
     */
    @FXML
    private VBox inscriptionRoot;

    /**
     * Contains return
     */
    @FXML
    private HBox top;

    /**
     * Firstname of the user
     */
    @FXML
    private TextField prenom;

    /**
     * LastName of the user
     */
    @FXML
    private TextField nom;

    /**
     * Pseudo of the user
     */
    @FXML
    private TextField pseudo;

    /**
     * Mail address of the user
     */
    @FXML
    private TextField adressemail;

    /**
     * Password of the user
     */
    @FXML
    private PasswordField mdp;

    /**
     * Validate button
     */
    @FXML
    private Button valider;

    /**
     * CheckBox to check to confirm the inscription
     */
    @FXML
    private CheckBox acceptercond;

    /**
     * Contains the path
     */
    @FXML
    private Pane espacesignature;

    /**
     * Default constructor
     */
    InscriptionControl() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Inscription.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        prenom.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                inscriptionRoot.requestFocus();
                firstTime.setValue(false);
            }
        });

        initialize();

        espacesignature.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                path.getElements().clear();
                path.getElements().add(new MoveTo(mouseEvent.getX(), mouseEvent.getY()));
            }
        });

        espacesignature.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getX() < espacesignature.getWidth() && mouseEvent.getY() < espacesignature.getHeight()) {
                    if (mouseEvent.getX() > 0 && mouseEvent.getY() > 0) {
                        path.getElements().add(new LineTo(mouseEvent.getX(), mouseEvent.getY()));
                    }
                }
            }
        });
    }

    /***
     * Initialization
     */
    private void initialize() {
        path.setStrokeWidth(1);
        path.setStroke(Color.BLACK);
    }

    /**
     * Check if the fields are good or not
     *
     * @return attribute boolean
     */
    private boolean checkFields() {
        return prenom.getText().length() >= 3 &&
                nom.getText().length() >= 3 &&
                pseudo.getText().length() >= 3 &&
                adressemail.getText().length() >= 4 && adressemail.getText().contains("@") && adressemail.getText().contains(".") &&
                mdp.getText().length() >= 3 &&
                acceptercond.isSelected() &&
                espacesignature.getChildren().contains(path);
    }

    /**
     * Call the pop up that will be displayed on the screen
     */
    @FXML
    private void callPopUp() {
        PopUp popup;
        String icon;
        String text;
        if (!isPseudoAvailable(pseudo)) {
            icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
            text = "Pseudo inutilisable";
        } else if (checkFields()) {
            CreateAccount();
            icon = "fr/univ_amu/iut/Images/validCheck200.png";
            text = "Compte " + prenom.getText() + " créé !";
        } else {
            icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
            text = "Champs incomplets";
        }

        popup = new PopUp(icon, text, new InscriptionControl(), 180);

        popup.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (checkFields())
                    Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new AccueilControl()));
                else Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new InscriptionControl()));
            }
        });

        Main.startNewPage((Stage) inscriptionRoot.getScene().getWindow(), new Scene(popup));
    }

    /**
     * Check if the pseudo is available
     *
     * @param pseudo desired pseudo of the user
     * @return attribute boolean
     */
    private boolean isPseudoAvailable(TextField pseudo) {
        for (UserAccount user : Main.getSystem().getMyUsers()) {
            if (pseudo.getText().equals(user.getPseudo()))
                return false;
        }
        return true;
    }

    /**
     * load Accueil page
     */
    @FXML
    private void Accueil() {
        Main.startNewPage((Stage) inscriptionRoot.getScene().getWindow(), new Scene(new AccueilControl()));
    }

    /**
     * load Inscription page
     */
    @FXML
    private void Inscription() {
        Main.startNewPage((Stage) inscriptionRoot.getScene().getWindow(), new Scene(new InscriptionControl()));
    }

    /**
     * Create a new user account
     */
    @FXML
    private void CreateAccount() {
        UserAccount user;
        user = new UserAccount(pseudo.getText(), mdp.getText(), prenom.getText(), nom.getText(), adressemail.getText());
        Main.getSystem().addUserAccount(user);
    }
}