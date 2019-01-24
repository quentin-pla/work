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
import java.util.Set;

/**
 * A commercial product
 */
public class Product extends VBox {

    /**
     * All instances of class Product
     */
    private static final List<Product> allProducts = new ArrayList<>();

    {
        Product.allProducts.add(this);
    }

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param name           Name of the Product
     * @param brand          Brand of the product
     * @param pictureLink    Link for the picture of the Product
     * @param summary        What is the product ?
     * @param technicalSheet Technical Sheet of the Product
     * @param category       Category of the Product
     */
    public Product(String name, String brand, String pictureLink, String summary, List<String> technicalSheet, Category category) {
        id = ++lastId;
        this.name = name;
        this.brand = brand;
        this.picture = new Image(pictureLink);
        this.summary = summary;
        this.technicalSheet = technicalSheet;
        this.category = category;
        avgMark = -1.0d;
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a Product
     */
    private static int lastId = 0;

    /**
     * ID of the Product
     */
    private int id;

    /**
     * Name of the Product
     */
    private String name;

    /**
     * Brand of the Product
     */
    private String brand;

    /**
     * Picture of the Product
     */
    private Image picture;

    /**
     * Average mark of the Product
     * Calculated with all the marks given to the Product in the Comments
     */
    private double avgMark;

    /**
     * What is the product ?
     */
    private String summary;

    /**
     * Technical Sheet of the Product
     */
    private List<String> technicalSheet/* = new ArrayList<>()*/;

    /**
     * Category of the Product
     */
    private Category category;

    /**
     * All Comments of the Product
     */
    private List<Comment> myComments = new ArrayList<>();

    // **************************************************
    // Getters
    // **************************************************

    /**
     * allProducts getter
     *
     * @return attribute allProducts
     */
    public static List<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * myComments getter
     *
     * @return attribute myComments
     */
    public List<Comment> getMyComments() {
        return myComments;
    }

    /**
     * id getter
     *
     * @return attribute id
     */
    public int getID() {
        return id;
    }

    /**
     * name getter
     *
     * @return attribute name
     */
    public String getName() {
        return name;
    }

    /**
     * avgMark getter
     *
     * @return attribute avgMark
     */
    public double getAvgMark() {
        return avgMark;
    }

    /**
     * picture getter
     *
     * @return attribute picture
     */
    public Image getPicture() {
        return picture;
    }

    /**
     * summary getter
     *
     * @return attribute summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * category getter
     *
     * @return attribute category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * technicalSheet getter
     *
     * @return attribute technicalSheet
     */
    public List<String> getTechnicalSheet() {
        return technicalSheet;
    }

    /**
     * brand getter
     *
     * @return attribute brand
     */
    public String getBrand() {
        return brand;
    }

    // **************************************************
    // Setters
    // **************************************************

    /**
     * category setter
     *
     * @param category New category of the Product
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * technical sheet setter
     *
     * @param technicalSheet New technical sheet of the Product
     */
    public void updateTechnicalSheet(List<String> technicalSheet) {
        this.technicalSheet = technicalSheet;
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Add the Product to user favorites
     *
     * @param user UserAccount to which the Product is added in favorites
     */
    public void addInFavorite(UserAccount user) {
        user.addFavoriteProduct(this);
    }

    /**
     * Remove the Product from user favorites
     *
     * @param user UserAccount to which the Product is removed from favorites
     */
    public void removeFromFavorite(UserAccount user) {
        user.removeFavoriteProduct(this);
    }

    /**
     * Downloads a PDF file with the picture of the Product,
     * its name, its summary and the its 3 most reliable Comments
     */
    public void downloadProduct() { }

    /**
     * Add a Comment to the Product
     *
     * @param comment Comment to add
     */
    void addComment(Comment comment) {
        myComments.add(comment);
    }
}