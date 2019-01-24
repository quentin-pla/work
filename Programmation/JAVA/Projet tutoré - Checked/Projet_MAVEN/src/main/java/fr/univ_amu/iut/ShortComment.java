package fr.univ_amu.iut;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * A short version of a comment
 */
public class ShortComment extends VBox {

    /**
     * Default constructor
     *
     * @param comment linked comment
     */
    ShortComment(Comment comment) {
        myComment = comment;

        Label favoriteStats = new Label();
        Label likeStats = new Label();
        Label dislikeStats = new Label();
        Label userPseudo = new Label();
        Label commentProduct = new Label();
        Text commentText = new Text();
        Label commentTitle = new Label();

        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: white;-fx-border-radius: 15;-fx-border-width: 3;-fx-border-color: linear-gradient(to right bottom,limegreen,darkgreen)");
        this.setPrefSize(340, 171);
        this.setMinSize(340, 171);
        this.setMaxSize(340, 171);
        this.setCursor(Cursor.HAND);

        HBox hboxContent = new HBox();
        hboxContent.setAlignment(Pos.CENTER_LEFT);
        hboxContent.setSpacing(5);
        hboxContent.setPrefSize(320, 30);
        hboxContent.setMinSize(320, 30);
        hboxContent.setMaxSize(320, 30);

        commentProduct.setText(myComment.getMyProduct().getName());
        commentProduct.setPrefWidth(195);
        commentProduct.setMinWidth(195);
        commentProduct.setMaxWidth(195);
        commentProduct.setStyle("-fx-font-size: 150%;-fx-text-fill: black;-fx-font-family: 'Avenir Medium'");

        HBox hBoxStars = new HBox();
        hBoxStars.setSpacing(2);
        hBoxStars.setPrefHeight(22);
        hBoxStars.setMaxHeight(22);
        hBoxStars.setMinHeight(22);

        Region star1Region = new Region();
        Region star2Region = new Region();
        Region star3Region = new Region();
        Region star4Region = new Region();
        Region star5Region = new Region();

        star1Region.setPrefSize(22, 22);
        star1Region.setMaxSize(22, 22);
        star1Region.setMinSize(22, 22);
        star1Region.setStyle("-fx-background-color: #e2c422;");

        star2Region.setPrefSize(22, 22);
        star2Region.setMaxSize(22, 22);
        star2Region.setMinSize(22, 22);
        star2Region.setStyle("-fx-background-color: #e2c422;");

        star3Region.setPrefSize(22, 22);
        star3Region.setMaxSize(22, 22);
        star3Region.setMinSize(22, 22);
        star3Region.setStyle("-fx-background-color: #e2c422;");

        star4Region.setPrefSize(22, 22);
        star4Region.setMaxSize(22, 22);
        star4Region.setMinSize(22, 22);
        star4Region.setStyle("-fx-background-color: #e2c422;");

        star5Region.setPrefSize(22, 22);
        star5Region.setMaxSize(22, 22);
        star5Region.setMinSize(22, 22);
        star5Region.setStyle("-fx-background-color: #e2c422;");

        star1.setContent(setStar(1));
        star2.setContent(setStar(2));
        star3.setContent(setStar(3));
        star4.setContent(setStar(4));
        star5.setContent(setStar(5));

        star1Region.setShape(star1);
        star2Region.setShape(star2);
        star3Region.setShape(star3);
        star4Region.setShape(star4);
        star5Region.setShape(star5);

        hBoxStars.getChildren().addAll(star1Region, star2Region, star3Region, star4Region, star5Region);

        hboxContent.getChildren().addAll(commentProduct, hBoxStars);
        VBox.setMargin(hboxContent, new Insets(2, 0, 0, 0));

        commentTitle.setAlignment(Pos.CENTER_LEFT);
        commentTitle.setText("\"" + myComment.getTitle() + "\"");
        commentTitle.setPrefWidth(320);
        commentTitle.setMaxWidth(320);
        commentTitle.setMinWidth(320);
        commentTitle.setStyle("-fx-font-size: 140%;-fx-text-fill: #595959;");

        TextFlow commentContent = new TextFlow(commentText);
        commentContent.setTextAlignment(TextAlignment.JUSTIFY);
        commentContent.setPrefSize(320, 50);
        commentContent.setMaxSize(320, 50);
        commentContent.setMinSize(320, 50);
        VBox.setMargin(commentContent, new Insets(5, 0, 3, 0));

        commentText.setText(cutCommentText() + "...");
        commentText.setStyle("-fx-font-size: 110%;-fx-text-fill: black;");

        HBox hBoxBottom = new HBox();
        hBoxBottom.setAlignment(Pos.CENTER_LEFT);
        hBoxBottom.setPrefSize(320, 40);
        hBoxBottom.setMaxSize(320, 40);
        hBoxBottom.setMinSize(320, 40);

        AnchorPane userPictureContainer = new AnchorPane();
        userPictureContainer.setPrefSize(40, 40);
        userPictureContainer.setMaxSize(40, 40);
        userPictureContainer.setMinSize(40, 40);

        Circle badgeCircle = new Circle();
        badgeCircle.setLayoutX(20);
        badgeCircle.setLayoutY(20);
        badgeCircle.setRadius(17);
        badgeCircle.setStyle("-fx-fill: green");

        ImageView imageContainer = new ImageView();

        Circle imageViewClip = new Circle();
        imageViewClip.setLayoutX(15);
        imageViewClip.setLayoutY(15);
        imageViewClip.setRadius(15);

        imageContainer.setLayoutX(5);
        imageContainer.setLayoutY(5);
        imageContainer.setFitHeight(30);
        imageContainer.setPreserveRatio(true);
        imageContainer.setClip(imageViewClip);

        Image image = new Image(myComment.getMyAuthor().getPersonalPicture());

        imageContainer.setImage(image);

        userPictureContainer.getChildren().addAll(badgeCircle, imageContainer);

        userPseudo.setText(myComment.getMyAuthor().getPseudo());
        userPseudo.setPrefWidth(97);
        userPseudo.setMaxWidth(97);
        userPseudo.setMinWidth(97);
        userPseudo.setStyle("-fx-font-size: 130%;-fx-text-fill: black;");
        HBox.setMargin(userPseudo, new Insets(0, 0, 0, 3));

        HBox likeStatistics = new HBox();
        HBox dislikeStatistics = new HBox();
        HBox favoriteStatistics = new HBox();

        likeStatistics.setSpacing(2);
        likeStatistics.setAlignment(Pos.CENTER_LEFT);
        likeStatistics.setPrefSize(52, 22);
        likeStatistics.setMaxSize(52, 22);
        likeStatistics.setMinSize(52, 22);

        dislikeStatistics.setSpacing(2);
        dislikeStatistics.setAlignment(Pos.CENTER_LEFT);
        dislikeStatistics.setPrefSize(52, 22);
        dislikeStatistics.setMaxSize(52, 22);
        dislikeStatistics.setMinSize(52, 22);

        favoriteStatistics.setSpacing(2);
        favoriteStatistics.setAlignment(Pos.CENTER_LEFT);
        favoriteStatistics.setPrefSize(52, 22);
        favoriteStatistics.setMaxSize(52, 22);
        favoriteStatistics.setMinSize(52, 22);

        Region likeSVGRegion = new Region();
        Region dislikeSVGRegion = new Region();
        Region favoriteSVGRegion = new Region();

        likeSVGRegion.setPrefSize(16, 16);
        likeSVGRegion.setMinSize(16, 16);
        likeSVGRegion.setMaxSize(16, 16);
        likeSVGRegion.setStyle("-fx-background-color: green");

        dislikeSVGRegion.setPrefSize(16, 16);
        dislikeSVGRegion.setMinSize(16, 16);
        dislikeSVGRegion.setMaxSize(16, 16);
        dislikeSVGRegion.setStyle("-fx-background-color: red");

        favoriteSVGRegion.setPrefSize(16, 16);
        favoriteSVGRegion.setMinSize(16, 16);
        favoriteSVGRegion.setMaxSize(16, 16);
        favoriteSVGRegion.setStyle("-fx-background-color: purple");

        SVGPath likeSVG = new SVGPath();
        SVGPath dislikeSVG = new SVGPath();
        SVGPath favoriteSVG = new SVGPath();

        likeSVG.setContent("M198 448h172c15.7 0 28.6-9.6 34.2-23.4l57.1-135.4c1.7-4.4 2.6-9 2.6-14v-38.6c0-21.1-17-44.6-37.8-44.6H306.9l18-81.5.6-6c0-7.9-3.2-15.1-8.3-20.3L297 64 171 191.3c-6.8 6.9-11 16.5-11 27.1v192c0 21.1 17.2 37.6 38 37.6zM48 224h64v224H48z");
        dislikeSVG.setContent("M314 64H142c-15.7 0-28.6 9.6-34.2 23.4L50.6 222.8c-1.7 4.4-2.6 9-2.6 14v38.6c0 21.1 17 44.6 37.8 44.6h119.3l-18 81.5-.6 6c0 7.9 3.2 15.1 8.3 20.3l20 20.1L341 320.7c6.8-6.9 11-16.5 11-27.1v-192c0-21.1-17.2-37.6-38-37.6zM400 64h64v224h-64z");
        favoriteSVG.setContent("M352 56h-1c-39.7 0-74.8 21-95 52-20.2-31-55.3-52-95-52h-1c-61.9.6-112 50.9-112 113 0 37 16.2 89.5 47.8 132.7C156 384 256 456 256 456s100-72 160.2-154.3C447.8 258.5 464 206 464 169c0-62.1-50.1-112.4-112-113");

        likeSVGRegion.setShape(likeSVG);
        dislikeSVGRegion.setShape(dislikeSVG);
        favoriteSVGRegion.setShape(favoriteSVG);

        likeStats.setStyle("-fx-font-size: 130%;-fx-text-fill: green");
        likeStats.setText(myComment.getLikes().toString());

        dislikeStats.setStyle("-fx-font-size: 130%;-fx-text-fill: red");
        dislikeStats.setText(myComment.getDislikes().toString());

        favoriteStats.setStyle("-fx-font-size: 130%;-fx-text-fill: purple");
        favoriteStats.setText(myComment.getNbOfFavorites().toString());

        HBox.setMargin(likeStatistics, new Insets(0, 12, 0, 0));
        HBox.setMargin(dislikeStatistics, new Insets(0, 12, 0, 0));

        likeStatistics.getChildren().addAll(likeSVGRegion, likeStats);
        dislikeStatistics.getChildren().addAll(dislikeSVGRegion, dislikeStats);
        favoriteStatistics.getChildren().addAll(favoriteSVGRegion, favoriteStats);

        VBox.setMargin(hBoxBottom, new Insets(5, 0, 0, 0));

        hBoxBottom.getChildren().addAll(userPictureContainer, userPseudo, likeStatistics, dislikeStatistics, favoriteStatistics);

        this.getChildren().addAll(hboxContent, commentTitle, commentContent, hBoxBottom);
    }

    /**
     * Linked comment
     */
    private Comment myComment;

    /**
     * myComment getter
     *
     * @return myComment
     */
    public Comment getMyComment() {
        return myComment;
    }

    /**
     * Stars displayed
     */
    private SVGPath star1 = new SVGPath();
    private SVGPath star2 = new SVGPath();
    private SVGPath star3 = new SVGPath();
    private SVGPath star4 = new SVGPath();
    private SVGPath star5 = new SVGPath();

    /**
     * Set the shape of the star
     *
     * @param star star to be set
     * @return String
     */
    private String setStar(int star) {
        if (myComment.getProductNotation() >= star) return SvgPicture.getFullStar();
        else
            return SvgPicture.getEmptyStar();
    }

    /**
     * Cut the content of a comment if too long
     *
     * @return attribute shortContent
     */
    private String cutCommentText() {
        String shortContent;
        if (myComment.getContent().length() > 150) shortContent = myComment.getContent().substring(0, 150);
        else shortContent = myComment.getContent();
        return shortContent;
    }
}
