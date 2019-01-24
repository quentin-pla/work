package fr.univ_amu.iut;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * All Selected comments will be displayed here
 */
public class SelectionControl extends BorderPane {

    @FXML
    private SVGPath starICON;

    @FXML
    private SVGPath searchICON;

    @FXML
    private SVGPath profileICON;

    @FXML
    private SVGPath settingsICON;

    @FXML
    private BorderPane borderPane;

    @FXML
    private HBox btnclassements;

    @FXML
    private VBox vBoxContent;

    /**
     * Default constructor
     */
    /*public*/ SelectionControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/Selection.fxml"));
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
        Database.addContentSelection(this);
    }

    /**
     * load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) borderPane.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) borderPane.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) borderPane.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) borderPane.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * Convert a selectedcomment to a comment
     *
     * @param comment selectedcomment to be converted
     * @return Comment
     */
    private Comment asComment(SelectedComment comment) {
        return comment.getMyComment();
    }

    /**
     * Add a selectedcomment to the page
     *
     * @param comment comment to add
     */
    public void addSelectedComment(SelectedComment comment) {
        comment.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Main.startNewPage((Stage) borderPane.getScene().getWindow(),
                        new Scene(new CommentViewControl(asComment((SelectedComment) mouseEvent.getSource()))));
            }
        });
        vBoxContent.getChildren().add(comment);
    }
}