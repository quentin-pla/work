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
import java.util.ArrayList;
import java.util.List;

/**
 * Publish a comment
 */
/*public*/ class PublierControl extends BorderPane {

    /**
     * Container of all elements of the page
     */
    @FXML
    private BorderPane publierRoot;

    /**
     * Contains all elements of the center of the border pane
     */
    @FXML
    private VBox vBox;

    /**
     * Stars displayed
     */
    @FXML
    private SVGPath star1, star2, star3, star4, star5;

    /**
     * Menu bar icons
     */
    @FXML
    private SVGPath starICON, searchICON, profileICON, settingsICON;

    /**
     * Name of the product
     */
    @FXML
    private Label productName;

    /**
     * Title of the comment
     */
    @FXML
    private TextField commentTitle;

    /**
     * Content of the comment
     */
    @FXML
    private TextArea commentContent;

    /**
     * Linked product
     */
    private Product myProduct;

    /**
     * Notation of the product in the comment
     */
    private double myNotation = 0;

    /**
     * Default constructor
     *
     * @param product linked product
     */
    PublierControl(Product product) {
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Publier.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        commentTitle.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                vBox.requestFocus();
                firstTime.setValue(false);
            }
        });
        myProduct = product;
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
        productName.setText(myProduct.getName());
        resetStarPattern();
    }

    /**
     * Check if fields are good
     */
    private boolean checkFields() {
        return commentTitle.getText().length() >= 3 &&
                commentContent.getText().length() >= 10;
    }

    /**
     * Call the pop up displayed
     */
    @FXML
    private void callPopUp() {
        PopUp popup;
        String icon = new String();
        String text = new String();
        if (!checkFields()) {
            icon = "/fr/univ_amu/iut/Images/redCross200.PNG";
            text = "Champs incomplets";
        } else if (checkFields()) {
            CreateComment();
            icon = "fr/univ_amu/iut/Images/validCheck200.png";
            text = "Commentaire créé !";
        }

        popup = new PopUp(icon, text, new PublierControl(myProduct), 180);

        popup.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (checkFields())
                    Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new ResultatsControl(new ProductControl(myProduct))));
                else Main.startNewPage((Stage) popup.getScene().getWindow(), new Scene(new PublierControl(myProduct)));
            }
        });

        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(popup));
    }

    /**
     * load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * load Resultats page
     */
    @FXML
    private void Resultats() {
        Main.startNewPage((Stage) publierRoot.getScene().getWindow(), new Scene(new ResultatsControl(new ProductControl(myProduct))));
    }

    /**
     * Reset the stars to be empty stars
     */
    @FXML
    private void resetStarPattern() {
        star1.setContent(SvgPicture.getEmptyStar());
        star2.setContent(SvgPicture.getEmptyStar());
        star3.setContent(SvgPicture.getEmptyStar());
        star4.setContent(SvgPicture.getEmptyStar());
        star5.setContent(SvgPicture.getEmptyStar());
    }

    /**
     * Set star 1 SVG
     */
    @FXML
    private void star1Pattern() {
        if (star1.getContent().equals(SvgPicture.getEmptyStar())) {
            star1.setContent(SvgPicture.getFullStar());
            myNotation = 1;
        } else if (star1.getContent().equals(SvgPicture.getFullStar())) {
            star2.setContent(SvgPicture.getEmptyStar());
            star3.setContent(SvgPicture.getEmptyStar());
            star4.setContent(SvgPicture.getEmptyStar());
            star5.setContent(SvgPicture.getEmptyStar());
        }
    }

    /**
     * Set star 2 SVG
     */
    @FXML
    private void star2Pattern() {
        if (star2.getContent().equals(SvgPicture.getEmptyStar())) {
            star1.setContent(SvgPicture.getFullStar());
            star2.setContent(SvgPicture.getFullStar());
            myNotation = 2;
        } else if (star2.getContent().equals(SvgPicture.getFullStar())) {
            star3.setContent(SvgPicture.getEmptyStar());
            star4.setContent(SvgPicture.getEmptyStar());
            star5.setContent(SvgPicture.getEmptyStar());
        }
    }

    /**
     * Set star 3 SVG
     */
    @FXML
    private void star3Pattern() {
        if (star3.getContent().equals(SvgPicture.getEmptyStar())) {
            star1.setContent(SvgPicture.getFullStar());
            star2.setContent(SvgPicture.getFullStar());
            star3.setContent(SvgPicture.getFullStar());
            myNotation = 3;
        } else if (star3.getContent().equals(SvgPicture.getFullStar())) {
            star4.setContent(SvgPicture.getEmptyStar());
            star5.setContent(SvgPicture.getEmptyStar());
        }
    }

    /**
     * Set star 4 SVG
     */
    @FXML
    private void star4Pattern() {
        if (star4.getContent().equals(SvgPicture.getEmptyStar())) {
            star1.setContent(SvgPicture.getFullStar());
            star2.setContent(SvgPicture.getFullStar());
            star3.setContent(SvgPicture.getFullStar());
            star4.setContent(SvgPicture.getFullStar());
            myNotation = 4;
        } else if (star4.getContent().equals(SvgPicture.getFullStar())) {
            star5.setContent(SvgPicture.getEmptyStar());
        }
    }

    /**
     * Set star 5 SVG
     */
    @FXML
    private void star5Pattern() {
        if (star5.getContent().equals(SvgPicture.getEmptyStar())) {
            star1.setContent(SvgPicture.getFullStar());
            star2.setContent(SvgPicture.getFullStar());
            star3.setContent(SvgPicture.getFullStar());
            star4.setContent(SvgPicture.getFullStar());
            star5.setContent(SvgPicture.getFullStar());
            myNotation = 5;
        }
    }

    /**
     * Allow to create a new comment
     */
    @FXML
    private void CreateComment() {
        Comment comment;
        if (checkFields()) {
            comment = new Comment(commentTitle.getText(), commentContent.getText(), myNotation, new ArrayList<>(), Database.getConnectedUser(), myProduct);
            Database.addComment(comment);
        }
    }

}