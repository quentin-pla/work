package fr.univ_amu.iut;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.List;

public class ProductControl extends VBox {

    /**
     * All Controllers of the class Product
     */
    private static final List<ProductControl> allProductsControls = new ArrayList<>();

    {
        ProductControl.allProductsControls.add(this);
    }

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     */
    public ProductControl(Product myProduct) {
        this.myProduct = myProduct;

        this.setPrefSize(320, 320);
        this.setMinSize(320, 320);
        this.setMaxSize(320, 320);
        this.setStyle("-fx-border-radius: 15;-fx-border-color: grey");
        this.setAlignment(Pos.TOP_CENTER);
        this.setCursor(Cursor.HAND);

        Label productTitle = new Label(myProduct.getName());
        productTitle.setStyle("-fx-font-size: 250%;-fx-text-fill: black;-fx-font-family: 'Avenir Medium'");
        VBox.setMargin(productTitle, new Insets(5, 0, 3, 0));

        HBox starsAndBrand = new HBox();
        starsAndBrand.setSpacing(2);
        starsAndBrand.setPrefHeight(18);
        starsAndBrand.setMinHeight(18);
        starsAndBrand.setMaxHeight(18);
        starsAndBrand.setAlignment(Pos.CENTER);
        VBox.setMargin(starsAndBrand, new Insets(0, 0, 15, 0));

        Label productBrand = new Label(myProduct.getBrand() + " |");
        productBrand.setStyle("-fx-font-size: 160%;-fx-text-fill: black");
        HBox.setMargin(productBrand, new Insets(0, 5, 0, 0));

        Region star1Region = new Region();
        Region star2Region = new Region();
        Region star3Region = new Region();
        Region star4Region = new Region();
        Region star5Region = new Region();

        star1Region.setPrefSize(16, 16);
        star1Region.setMaxSize(16, 16);
        star1Region.setMinSize(16, 16);
        star1Region.setStyle("-fx-background-color: #e2c422;");

        star2Region.setPrefSize(16, 16);
        star2Region.setMaxSize(16, 16);
        star2Region.setMinSize(16, 16);
        star2Region.setStyle("-fx-background-color: #e2c422;");

        star3Region.setPrefSize(16, 16);
        star3Region.setMaxSize(16, 16);
        star3Region.setMinSize(16, 16);
        star3Region.setStyle("-fx-background-color: #e2c422;");

        star4Region.setPrefSize(16, 16);
        star4Region.setMaxSize(16, 16);
        star4Region.setMinSize(16, 16);
        star4Region.setStyle("-fx-background-color: #e2c422;");

        star5Region.setPrefSize(16, 16);
        star5Region.setMaxSize(16, 16);
        star5Region.setMinSize(16, 16);
        star5Region.setStyle("-fx-background-color: #e2c422;");

        SVGPath star1 = new SVGPath();
        SVGPath star2 = new SVGPath();
        SVGPath star3 = new SVGPath();
        SVGPath star4 = new SVGPath();
        SVGPath star5 = new SVGPath();

        star1.setContent(SvgPicture.getFullStar());
        star2.setContent(SvgPicture.getFullStar());
        star3.setContent(SvgPicture.getFullStar());
        star4.setContent(SvgPicture.getFullStar());
        star5.setContent(SvgPicture.getFullStar());

        star1Region.setShape(star1);
        star2Region.setShape(star2);
        star3Region.setShape(star3);
        star4Region.setShape(star4);
        star5Region.setShape(star5);

        starsAndBrand.getChildren().addAll(productBrand, star1Region, star2Region, star3Region, star4Region, star5Region);

        ImageView productImage = new ImageView(myProduct.getPicture());
        productImage.setFitWidth(300);
        productImage.setPreserveRatio(true);

        this.getChildren().addAll(productTitle, starsAndBrand, productImage);
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Linked product
     */
    private Product myProduct;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * allProducts getter
     *
     * @return attribute allProducts
     */
    public static List<ProductControl> getAllProductsControls() {
        return allProductsControls;
    }

    /**
     * myProduct getter
     *
     * @return attribute myProduct
     */
    public Product getMyProduct() {
        return myProduct;
    }

    // **************************************************
    // Setters
    // **************************************************

    // **************************************************
    // Public methods
    // **************************************************

}
