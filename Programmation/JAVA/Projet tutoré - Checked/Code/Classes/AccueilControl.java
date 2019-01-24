package fr.univ_amu.iut;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

/**
 * Home page (where to sign in) controller
 */
/*public*/ class AccueilControl extends VBox {

    // **************************************************
    // Fields
    // **************************************************

    @FXML
    /**
     * Field that contains everything in the page
     */
    private VBox vBox;

    @FXML
    /**
     * Field where to write identifier
     */
    private TextField identifiant;

    @FXML
    /**
     * Field where to write password
     */
    private PasswordField mdp;

    @FXML
    /**
     * Logo checked
     */
    private ImageView logo;

    @FXML
    /**
     * Button to connect
     */
    private Button connexion;

    @FXML
    /**
     * Button to create account
     */
    private Button creerCompte;

    @FXML
    /**
     * Label to click on if you forgot your password
     */
    private Label mdpOublie;

    @FXML
    /**
     * Labal to click on to skip identification (to use the app without an account)
     */
    private Label skip;

    /**
     * TRUE if connection has been done successfully
     */
    private boolean connectionSuccessful = false;

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     */
    /*public*/ AccueilControl() {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Accueil.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        identifiant.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                vBox.requestFocus();
                firstTime.setValue(false);
            }
        });
    }

    // **************************************************
    // Private methods
    // **************************************************

    @FXML
    /**
     * Checks all fields and connect account if all fields are valid
     * Displays a pop up when trying to connect
     */
    private void CheckAccount() {
        PopUp popup;          // PopUp to display
        String icon = "";     // icon to display on the PopUp
        String text = "";     // text to display on the PopUp
        int stringsize = 100; // size of the text

        // if all fields are filled
        if (identifiant.getText().length() < 1 || mdp.getText().length() < 1) {
            icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
            text = "Champs incomplets";
            stringsize = 180;
        } else {
            // for each account
            for (UserAccount user : Main.getSystem().getMyUsers()) {

                // if the pseudo exists
                if (identifiant.getText().toLowerCase().equals(user.getPseudo().toLowerCase())) {

                    // if the password is valid
                    if (mdp.getText().equals(user.getPassword())) {

                        // if the user is banned
                        if (user.isUserBanned()) {
                            connectionSuccessful = false;
                            icon = "/fr/univ_amu/iut/Images/redCross200.PNG";

                            // is the user is permanently banned
                            if (user.isUserPermanentlyBanned()) {
                                text = "Banni définitivement";
                                stringsize = 180;
                            } else {
                                text = createBannedPopUpMessage(user, text);
                                stringsize = 130;
                            } // end if user is banned but not permanently
                        } // end if user is banned
                        else {
                            connectionSuccessful = true;
                            icon = "fr/univ_amu/iut/Images/validCheck200.png";
                            text = "Bienvenue " + user.getFirstName() + " !";
                            stringsize = 180;
                            Main.getSystem().setIsConnected(true);   // indicate that a user is connected
                            Main.getSystem().setConnectedUser(user); // indicate which user is connected
                        } // end if user is not banned

                        break; // break when the good user has been found
                    } // end if password is valid
                    else {
                        connectionSuccessful = false;
                        icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
                        text = "Identifiant ou mot de passe incorrect";
                        stringsize = 100;
                    } // end if password is not valid
                } // end if pseudo have been found
                else {
                    connectionSuccessful = false;
                    icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
                    text = "Identifiant ou mot de passe incorrect";
                    stringsize = 100;
                } // end if pseudo has not been found
            } // end for each account
        } // end if all fields are filled

        popup = new PopUp(icon, text, new AccueilControl(), stringsize); // create PopUp to display
        popup.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (connectionSuccessful)
                    Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new SelectionControl()));
                else Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new AccueilControl()));
            }
        });
        Main.startNewPage((Stage) vBox.getScene().getWindow(), new Scene(popup)); // display pop up
    }

    /**
     * Create the message to display on the pop up when user is banned
     *
     * @param user banned user
     * @param text text to display whatever the type of ban
     * @return text to display on the pop up
     */
    private String createBannedPopUpMessage(UserAccount user, String text) {
        Date currentDate = new Date();
        long durationOfBanInSeconds = (user.getUnbanDate().getTime() - currentDate.getTime()) / 1000;
        long banDaysLeft = durationOfBanInSeconds / 86400;
        long banHoursLeft = (durationOfBanInSeconds % 86400) / 3600;
        long banMinutesLeft = (durationOfBanInSeconds % 3600) / 60;
        long banSecondsLeft = durationOfBanInSeconds % 60;

        text = "Banni pendant ";

        // display number of ban day left only if there is 1 day or more left
        if (banDaysLeft > 0) {
            /* round the number to the nearest integer */
            if (banHoursLeft > 12)
                text += banDaysLeft + 1;
            else
                text += banDaysLeft;

            text += " jour";
            if (banDaysLeft > 1) text += 's';
        } else if (banHoursLeft == 23 && banMinutesLeft > 30) // 24 hour = 1 day
            text += "1 jour";

            // display number of ban hours left only if there is 1 hour or more left
        else if (banHoursLeft > 0) {
            /* round the number to the nearest integer */
            if (banMinutesLeft > 30)
                text += banHoursLeft + 1;
            else
                text += banHoursLeft;

            text += " heure";
            if (banHoursLeft > 1) text += 's';
        } else if (banMinutesLeft == 59 && banSecondsLeft > 30) // 60 minutes = 1 hour
            text += "1 heure";

            // display number of ban minutes left only if there is 1 minute or more left
        else if (banMinutesLeft > 0) {
            /* round the number to the nearest integer */
            if (banSecondsLeft > 30)
                text += banMinutesLeft + 1;
            else
                text += banMinutesLeft;

            text += " minute";
            if (banMinutesLeft > 1) text += 's';
        }

        // display number of ban seconds left only if there is 1 second or more left
        else if (banSecondsLeft > 0) {
            text += banSecondsLeft + " seconde";
            if (banSecondsLeft > 1) text += 's';
        }

        return text;
    }

    @FXML
    /**
     * Loads inscription page
     */
    private void Inscription() {
        Main.startNewPage((Stage) vBox.getScene().getWindow(), new Scene(new InscriptionControl()));
    }

    @FXML
    /**
     * Loads account recuperation page
     */
    private void Recuperation() {
        Main.startNewPage((Stage) vBox.getScene().getWindow(), new Scene(new RecuperationControl()));
    }

    @FXML
    /**
     * Loads selection page
     */
    private void Selection() {
        Main.startNewPage((Stage) vBox.getScene().getWindow(), new Scene(new SelectionControl()));
    }
}