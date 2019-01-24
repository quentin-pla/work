package fr.univ_amu.iut;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.List;

/**
 * The comments which are selected by the team that will be in the first page of the application
 */
public class SelectedComment extends GridPane {

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Image of the product described in the comment
     */
    private Image image;

    /**
     * Comment of the product described in the comment
     */
    private Comment myComment;

    /**
     * Name of the product described in the comment
     */
    private String productName;

    /**
     * Tittle of the comment
     */
    private String commentTitle;

    /**
     * BackgroundColor of the comment
     */
    private String backgroundColor;

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param comment_        the selected comment
     * @param backgroundColor Background color
     */
    SelectedComment(Comment comment_, String backgroundColor, String productPicture) {
        this.myComment = comment_;
        this.image = new Image(productPicture);
        this.productName = myComment.getMyProduct().getName();
        this.commentTitle = myComment.getTitle();
        this.backgroundColor = backgroundColor;
        this.setCursor(Cursor.HAND);

        setMaxSize(320, 320);
        setMinSize(320, 320);
        setPrefSize(320, 320);
        setStyle("-fx-background-radius: 20 20 20 20;" + "-fx-background-color: " + this.backgroundColor);
        VBox.setMargin(this, new Insets(0, 0, 20, 0));

        Label comment = new Label("\"" + this.commentTitle + "\"");
        comment.setWrapText(false);
        comment.setAlignment(Pos.CENTER);
        comment.setMinWidth(280);
        comment.setMaxWidth(280);
        comment.setStyle("-fx-text-fill: white;-fx-font-size: " + setTitleSize() + "%;-fx-font-family: 'Avenir Light'");
        Label product = new Label(this.productName);
        product.setStyle("-fx-text-fill: white;-fx-font-size: 200%;-fx-font-family: 'Avenir Medium'");
        VBox labelcontent = new VBox(comment, product);
        labelcontent.setMinSize(280, 90);
        labelcontent.setMaxSize(280, 90);
        SelectedComment.setMargin(labelcontent, new Insets(10, 20, 0, 20));

        ImageView productimage = new ImageView(this.image);
        BorderPane imagecontener = new BorderPane();
        imagecontener.setMaxSize(320, 210);
        imagecontener.setMinSize(320, 210);
        imagecontener.setPrefSize(320, 210);
        BorderPane.setAlignment(productimage, Pos.BOTTOM_CENTER);
        productimage.setPreserveRatio(true);
        productimage.setFitHeight(210);
        productimage.setFitWidth(320);
        imagecontener.setBottom(productimage);
        SelectedComment.setMargin(imagecontener, new Insets(10, 0, 0, 0));

        SelectedComment.setConstraints(labelcontent, 0, 0);
        SelectedComment.setConstraints(imagecontener, 0, 1);
        this.getChildren().addAll(labelcontent, imagecontener);
    }

    // **************************************************
    // Getters
    // **************************************************

    /**
     * Set the size of the title
     *
     * @return integer
     */
    private int setTitleSize() {
        if (this.commentTitle.length() < 15) {
            return 300;
        } else if (this.commentTitle.length() >= 15 && this.commentTitle.length() < 20) {
            return 240;
        } else if (this.commentTitle.length() >= 20 && this.commentTitle.length() < 25) {
            return 180;
        } else
            return 150;
    }

    /**
     * Image getter
     *
     * @return attribute image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Product title getter
     *
     * @return attribute productTitle
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Comment title getter
     *
     * @return attribute commentTitle
     */
    public String getCommentTitle() {
        return commentTitle;
    }

    /**
     * Background color getter
     *
     * @return return backgroundColor
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Comment getter
     *
     * @return return myComment
     */
    public Comment getMyComment() { return myComment; }
}
