package fr.univ_amu.iut;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Product design object
 */
class ProductViewControl extends BorderPane {

    /**
     * Container of every elements of the page
     */
    @FXML
    private BorderPane productViewRoot;

    /**
     * Container of all products
     */
    @FXML
    private VBox productsContainer;

    /**
     * Displayed stars
     */
    @FXML
    private SVGPath star1, star2, star3, star4, star5;

    /**
     * Title of the category
     */
    @FXML
    private Label title;

    /**
     * Icons of the menu bar
     */
    @FXML
    private SVGPath starICON,searchICON,profileICON,settingsICON;

    /**
     * Default constructor
     *
     * @param category linked category
     */
    ProductViewControl(Category category) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/ProductView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        myCategory = category;
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
        title.setText(myCategory.getCategoryTitle());
        for (Product product : myCategory.getMyProducts()) {
            addProduct(product);
        }
        for (ProductControl productControl : ProductControl.getAllProductsControls()) {
            productControl.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> Main.startNewPage((Stage) productControl.getScene().getWindow(),
                    new Scene(new ResultatsControl(productControl))));
        }
    }

    /**
     * Add a product to the page
     * @param product
     */
    public void addProduct(Product product) {
        productsContainer.getChildren().add(new ProductControl(product));
    }

    /**
     * Linked category of the product
     */
    private Category myCategory;

    /**
     * Load Selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) productViewRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load Profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) productViewRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load Parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) productViewRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load Rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) productViewRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }
}