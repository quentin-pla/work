package fr.univ_amu.iut;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A Comment of a Product.
 * Have a title, a content, can contains pictures of the product.
 * Each comment can be like, dislike, or favorite mentionned.
 */
public class Comment {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param _title           Title of the Comment
     * @param _content         What does the user thinks about the Product
     * @param _productNotation Notation Mark given to the Product by the user
     * @param picturesLink     Links for the pictures of the commented Product
     * @param _author          Author of the Comment
     * @param product          Product that concerned by the Comment
     */
    public Comment(String _title, String _content, double _productNotation, List<String> picturesLink, UserAccount _author, Product product) {
        id = ++lastId;
        date = new Date();
        title = _title;
        content = _content;
        likes = 0;
        dislikes = 0;
        ratio = 1;
        nbOfFavoritesMentions = 0;
        productNotation = _productNotation;


        for (String link : picturesLink) {
            Image picture = new Image(link);
            pictures.add(picture);
        }

        myAuthor = _author;
        myProduct = product;
        isDeleted = false;
    }

    // **************************************************
    // Destructors
    // **************************************************

    /**
     * Call when the Comment is deleted by a Moderator
     *
     * @param moderator Moderator that deleted the Comment
     */
    public void finalize(Moderator moderator) {
        isDeleted = true;
        new Archive("Delete comment.", moderator, this);
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Last ID given to a Comment
     */
    private static int lastId = 0;

    /**
     * ID of the Comment
     */
    private int id;

    /**
     * Date of publication
     */
    private Date date;

    /**
     * Title
     */
    private String title;

    /**
     * What does the user thinks about the Product
     */
    private String content;

    /**
     * Number of likes
     */
    private Integer likes;

    /**
     * Number of dislikes
     */
    private Integer dislikes;

    /**
     * Ratio of likes/dislikes
     */
    private double ratio;

    /**
     * Number of Favorite mentions
     */
    private Integer nbOfFavoritesMentions;

    /**
     * Mark given to the commented Product
     */
    private double productNotation;

    /**
     * Pictures of the commented Product
     */
    private List<Image> pictures = new ArrayList<>();

    /**
     * UserAccount that posted the Comment
     */
    private UserAccount myAuthor;

    /**
     * Product that concerned by the Comment
     */
    private Product myProduct;

    /**
     * If TRUE acts like if the Comment is deleted for the users
     */
    private boolean isDeleted;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * id getter
     *
     * @return attribute id
     */
    public int getId() {
        return id;
    }

    /**
     * date getter
     *
     * @return attribute date
     */
    public Date getDate() {
        return date;
    }

    /**
     * title getter
     *
     * @return attribute title
     */
    public String getTitle() {
        return title;
    }

    /**
     * content getter
     *
     * @return attribute content
     */
    public String getContent() {
        return content;
    }

    /**
     * likes getter
     *
     * @return attribute likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * dislikes getter
     *
     * @return attribute dislikes
     */
    public Integer getDislikes() {
        return dislikes;
    }

    /**
     * ratio getter
     *
     * @return attribute ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * nbOfFavoritesMentions getter
     *
     * @return attribute nbOfFavoritesMentions
     */
    public Integer getNbOfFavorites() {
        return nbOfFavoritesMentions;
    }

    /**
     * productNotation getter
     *
     * @return attribute productNotation
     */
    public double getProductNotation() {
        return productNotation;
    }

    /**
     * pictures getter
     *
     * @return list of pictures of the comment
     */
    public List<Image> getPictures() {
        return pictures;
    }

    /**
     * myAuthor getter
     *
     * @return attribute myAuthor
     */
    public UserAccount getMyAuthor() {
        return myAuthor;
    }

    /**
     * myProduct getter
     *
     * @return attribute myProduct
     */
    public Product getMyProduct() {
        return myProduct;
    }

    /**
     * isDeleted getter
     *
     * @return attribute isDeleted
     */
    public boolean getStatus() {
        return isDeleted;
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Increments the numbers of likes of the Comment
     */
    public void addLike() {
        ++likes;
        myAuthor.updateLikesAndDislikesRatio();
    }

    /**
     * Decrements the numbers of likes of the Comment
     */
    public void removeLike() {
        if (likes > 0)
            --likes;
        myAuthor.updateLikesAndDislikesRatio();
    }

    /**
     * Increments the numbers of dislikes of the Comment
     */
    public void addDislike() {
        ++dislikes;
        myAuthor.updateLikesAndDislikesRatio();
    }

    /**
     * Decrements the numbers of dislikes of the Comment
     */
    public void removeDislike() {
        if (dislikes > 0)
            --dislikes;
        myAuthor.updateLikesAndDislikesRatio();
    }

    /**
     * Increments the numbers of Favorite Mentions of the Comment
     */
    public void addFavorite() {
        ++nbOfFavoritesMentions;
    }

    /**
     * Decrements the numbers of Favorite Mentions of the Comment
     */
    public void removeFavorite() {
        if (nbOfFavoritesMentions > 0)
            --nbOfFavoritesMentions;
    }
}