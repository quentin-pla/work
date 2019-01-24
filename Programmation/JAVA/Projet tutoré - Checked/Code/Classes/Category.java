package fr.univ_amu.iut;

import java.util.ArrayList;
import java.util.List;

/**
 * A product category
 */
public class Category {

    /**
     * List of all the instanced categories
     */
    private static final List<Category> allCategories = new ArrayList<>();

    {
        Category.allCategories.add(this);
    }

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Constructor of the category
     *
     * @param title   Title of the category
     * @param picture Pic of the category
     */
    Category(String title, CategoryPicture picture) {
        this.picture = picture;
        this.title = title;
        isUpperCategory = false;
        parent = null;
        myUnderCategories = new ArrayList<>();
        myProducts = new ArrayList<>();
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Width of the logo
     */
    private int width;

    /**
     * Height of the logo
     */
    private int height;

    /**
     * The scg picture
     */
    private CategoryPicture picture;

    /**
     * Make us know if the category has children
     */
    private boolean isUpperCategory;

    /**
     * The direct parent of the current category
     */
    private Category parent;

    /**
     * Know if the category is open or not
     */
    private Boolean isOpen = false;

    /**
     * Name of the category
     */
    private String title;

    /**
     * Under categories of this category
     */
    private List<Category> myUnderCategories;

    /**
     * Products of this category
     */
    private List<Product> myProducts;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * parent getter
     *
     * @return attribute parent
     */
    public Category getParent() {
        return parent;
    }

    /**
     * height getter
     *
     * @return attribure height
     */
    public int getHeight() {
        return height;
    }

    /**
     * width getter
     *
     * @return attribute width
     */
    public int getWidth() {
        return width;
    }

    /**
     * allCategories getter
     *
     * @return attribute allCategories
     */
    public static List<Category> getAllCategories() {
        return allCategories;
    }

    /**
     * title getter
     *
     * @return attribute title
     */
    public String getCategoryTitle() {
        return title;
    }

    /**
     * myProducts getter
     *
     * @return attribute myProducts
     */
    public List<Product> getMyProducts() {
        return myProducts;
    }

    /**
     * myProducts getter
     *
     * @return attribute myProducts
     */
    public List<Category> getMyUnderCategories() {
        return myUnderCategories;
    }

    /**
     * isUpperCategory getter
     *
     * @return attribute isUpperCategory
     */
    public boolean getIsUpperCategory() {
        return isUpperCategory;
    }

    /**
     * isOpen getter
     *
     * @return attribure isOpen
     */
    public Boolean getOpen() {
        return isOpen;
    }

    /**
     * picture getter
     *
     * @return attrbute picture
     */
    public CategoryPicture getPicture() {
        return picture;
    }

    // **************************************************
    // Setters
    // **************************************************

    /**
     * title setter
     *
     * @param title New title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * isOpen setter
     *
     * @param open New isOpen value
     */
    public void setOpen(Boolean open) {
        isOpen = open;
    }

    /**
     * isUppercategory setter
     *
     * @param upperCategory New isUpperCategory value
     */
    public void setUpperCategory(boolean upperCategory) {
        isUpperCategory = upperCategory;
    }

    // **************************************************
    // Package private methods
    // **************************************************

    /**
     * Add an under category to the current category
     *
     * @param category New under category
     */
    void addUnderCategory(Category category) {
        isUpperCategory = true;
        myUnderCategories.add(category);
        category.parent = this;
    }

    /**
     * Add a product to the current category
     *
     * @param product New product
     */
    void addProduct(Product product) {
        myProducts.add(product);
    }
}
