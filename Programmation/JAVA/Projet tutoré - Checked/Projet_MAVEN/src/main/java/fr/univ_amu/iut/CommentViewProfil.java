package fr.univ_amu.iut;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A product comment athor profile controller
 */
/*public*/ class CommentViewProfil extends BorderPane {

    private UserAccount user;

    //###### FXML PAGE CONTENT #######//

    /**
     * Contains every elements of the page
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
     * Icon of the level of the user
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
     * badge of the user
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
     * Container of instagram icon and user account
     */
    @FXML
    private HBox instagramContent;

    /**
     * Acocunt of the user
     */
    @FXML
    private Label instagramAccount;

    /**
     * Website of the user
     */
    @FXML
    private Label userWebsite;

    /**
     * Container of website icon and user website
     */
    @FXML
    private HBox websiteContent;

    /**
     * Container of every elements in the center of the border pane
     */
    @FXML
    private AnchorPane PageConnected;

    /**
     * Container of the badge of the user
     */
    @FXML
    private Region badgeContainer;

    /**
     * Circle around personal picture of the user to show its xp
     */
    @FXML
    private Circle xpCircle;

    //###### MENU BAR ICONS #######//

    /**
     * Icons of the menu bar
     */
    @FXML
    private SVGPath starICON,searchICON,profileICON,settingsICON;

    /**
     * Linked comment
     */
    private Comment myComment;

    /**
     * Default constructor
     *
     * @param comment linked comment
     */
    /*public*/ CommentViewProfil(Comment comment) {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CommentViewProfil.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        myComment = comment;
        user = myComment.getMyAuthor();
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
        setProfil();
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
     * load CommentView page
     */
    @FXML
    private void CommentView() {
        Main.startNewPage((Stage) profileRoot.getScene().getWindow(), new Scene(new CommentViewControl(myComment)));
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
    public void setProfil() {
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