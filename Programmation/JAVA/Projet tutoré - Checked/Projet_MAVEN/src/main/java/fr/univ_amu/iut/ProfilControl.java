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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.annotation.XmlList;
import java.io.IOException;

/**
 * Profile of the user
 */
/*public*/ class ProfilControl extends BorderPane {

    /**
     * User's profile
     */
    private UserAccount user;

    //###### FXML PAGE CONTENT #######//

    /**
     * Container of every elements of the page
     */
    @FXML
    private BorderPane profileRoot;

    /**
     * Cover picture of the user
     */
    @FXML
    private ImageView coverPicture;

    /**
     * Personal picture of the user
     */
    @FXML
    private ImageView personalPicture;

    /**
     * Icon of the user's level
     */
    @FXML
    private SVGPath lvlIcon;

    /**
     * Level of the user
     */
    @FXML
    private Label userLevel;

    /**
     * Number of followers of the user
     */
    @FXML
    private Label userFollowers;

    /**
     * Badge of the user
     */
    @FXML
    private SVGPath badge;

    /**
     * Pseudo of the user
     */
    @FXML
    private Label userPseudo;

    /**
     * Description of the user
     */
    @FXML
    private Text userDescription;

    /**
     * Container of instagram icon and account
     */
    @FXML
    private HBox instagramContent;

    /**
     * Instagram account of the user
     */
    @FXML
    private Label instagramAccount;

    /**
     * Website of the user
     */
    @FXML
    private Label userWebsite;

    /**
     * Container of website icon and user's website
     */
    @FXML
    private HBox websiteContent;

    /**
     * Contains every elements of the center of the border pane
     */
    @FXML
    private AnchorPane PageConnected;

    /**
     * Container of the user's badge
     */
    @FXML
    private Region badgeContainer;

    /**
     * Circle around personal picture to see user's xp
     */
    @FXML
    private Circle xpCircle;

    /**
     * Menu bar icons
     */
    @FXML
    private SVGPath starICON,searchICON,profileICON,settingsICON;

    /**
     * Default constructor
     */
    /*public*/ ProfilControl() {
        FXMLLoader fxmlLoader;
        if (!Main.getSystem().getIsConnected())
            fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProfilNotConnected.fxml"));
        else fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Profil.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

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
        if (Main.getSystem().getIsConnected()) Main.getSystem().addConnectedProfil(this);
    }

    /**
     * load Accueil page
     */
    @FXML
    private void Accueil() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new AccueilControl()));
    }

    /**
     * load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * Adjust the number of followers if too much high
     */
    @FXML
    private void correctNumberFollowers() {
        int lengthlimit = 3;
        String nbfollowers = this.user.getNumberOfFollowers().toString();
        if (nbfollowers.length() > 3)
            userFollowers.setText(nbfollowers.substring(0, nbfollowers.length() - lengthlimit) + "k");
    }

    /**
     * Set the badge color of the user
     */
    @FXML
    private void correctBadgeColor() {
        if (this.badge.getContent().equals(Badges.RELIABLE.getSVGPicture())) {
            this.badgeContainer.setStyle("-fx-background-color:" + Badges.RELIABLE.getColor());
            this.xpCircle.setStyle("-fx-fill:" + Badges.RELIABLE.getColor());
        } else if (this.badge.getContent().equals(Badges.NON_RELIABLE.getSVGPicture())) {
            this.badgeContainer.setStyle("-fx-background-color:" + Badges.NON_RELIABLE.getColor());
            this.xpCircle.setStyle("-fx-fill:" + Badges.NON_RELIABLE.getColor());
        } else {
            this.badgeContainer.setStyle("-fx-background-color:" + Badges.DEFAULT.getColor());
            this.xpCircle.setStyle("-fx-fill:" + Badges.DEFAULT.getColor());
        }
    }

    /**
     * Display social networks of the user if available
     */
    @FXML
    private void showSocialNetworks() {
        if (this.user.getInstagramAccount().isEmpty()) {
            instagramContent.setDisable(true);
            instagramContent.setOpacity(0);
        } else {
            instagramContent.setDisable(false);
            instagramContent.setOpacity(1);
        }

        if (this.user.getUserWebsite().isEmpty()) {
            websiteContent.setDisable(true);
            websiteContent.setOpacity(0);
        } else {
            websiteContent.setDisable(false);
            websiteContent.setOpacity(1);
        }
    }

    /**
     * Set the profil of the user
     */
    @FXML
    public void setProfil(UserAccount user) {
        this.user = user;
        coverPicture.setImage(new Image(this.user.getCoverPicture()));
        personalPicture.setImage(new Image(this.user.getPersonalPicture()));
        userLevel.setText("lvl. " + this.user.getLevel().toString());
        userFollowers.setText(this.user.getNumberOfFollowers().toString());
        badge.setContent(this.user.getBadge().getSVGPicture());
        userPseudo.setText(this.user.getPseudo());
        userDescription.setText(this.user.getUserDescription());
        instagramAccount.setText(this.user.getInstagramAccount());
        userWebsite.setText(this.user.getUserWebsite());
        correctNumberFollowers();
        correctBadgeColor();
        showSocialNetworks();
    }
}