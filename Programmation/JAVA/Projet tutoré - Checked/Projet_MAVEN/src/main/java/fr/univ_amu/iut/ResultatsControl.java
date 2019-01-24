package fr.univ_amu.iut;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Show all comments of a product
 */
class ResultatsControl extends BorderPane {

    /**
     * Contains everything in the page
     */
    @FXML
    private BorderPane resultatsRoot;

    /**
     * Icons of menu bar
     */
    @FXML
    private SVGPath starICON, searchICON, profileICON, settingsICON, publierButton;

    /**
     * Contains every elements in the center of the border pane
     */
    @FXML
    private VBox vBoxContent;

    /**
     * Icon of the search bar
     */
    @FXML
    private SVGPath searchBarIcon;

    /**
     * Container of all comments displayed
     */
    @FXML
    private VBox commentsContainer;

    /**
     * Title of the product
     */
    @FXML
    private Label title;

    /**
     * Button publish
     */
    @FXML
    private Region buttonPublier;

    /**
     * Container of the publish button
     */
    @FXML
    private HBox buttonContainer;

    /**
     * Default constructor
     *
     * @param productControl linked product
     */
    ResultatsControl(ProductControl productControl) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Resultats.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        myProductControl = productControl;
        initialize();
    }

    /**
     * Initialization
     */
    private void initialize() {
        publierButton.setContent(SvgPicture.getPublierButtonSVG());
        starICON.setContent(SvgPicture.getStarSVG());
        searchICON.setContent(SvgPicture.getSearchSVG());
        profileICON.setContent(SvgPicture.getProfileSVG());
        settingsICON.setContent(SvgPicture.getSettingsSVG());
        title.setText(myProductControl.getMyProduct().getName());

        if (Database.getIsConnected()) {
            buttonPublier.setOpacity(1);
            buttonPublier.setDisable(false);
        } else {
            buttonPublier.setOpacity(0);
            buttonPublier.setDisable(true);
            buttonContainer.getChildren().remove(buttonPublier);
        }

        for (Comment comment : myProductControl.getMyProduct().getMyComments()) {
            addShortComment(new ShortComment(comment));
        }
    }

    /**
     * Transform shortcomment to comment
     *
     * @param comment shortcomment to be transformed
     * @return Comment
     */
    private Comment asComment(ShortComment comment) {
        return comment.getMyComment();
    }

    /**
     * Add short comment to the page
     *
     * @param comment linked comment
     */
    public void addShortComment(ShortComment comment) {
        commentsContainer.getChildren().add(comment);
        comment.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(),
                        new Scene(new CommentViewControl(asComment((ShortComment) mouseEvent.getSource()))));
            }
        });
    }

    /**
     * Linked product controller
     */
    private ProductControl myProductControl;

    /**
     * load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * load ProductView page
     */
    @FXML
    private void ProductView() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new ProductViewControl(myProductControl.getMyProduct().getCategory())));
    }

    /**
     * load Publier page
     */
    @FXML
    private void PublierNewComment() {
        Main.startNewPage((Stage) resultatsRoot.getScene().getWindow(), new Scene(new PublierControl(myProductControl.getMyProduct())));
    }

}