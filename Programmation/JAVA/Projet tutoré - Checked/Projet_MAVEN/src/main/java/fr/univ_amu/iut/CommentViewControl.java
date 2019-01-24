package fr.univ_amu.iut;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;

/**
 * A product comment controller
 */
class CommentViewControl extends BorderPane {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param comment Comment to display
     */
    CommentViewControl(Comment comment) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/CommentView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        myComment = comment;
        initialize();
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * All svg icons displayed
     */
    @FXML
    private SVGPath starICON, searchICON, profileICON, settingsICON;

    /**
     * Layout that contains everything in the page
     */
    @FXML
    private BorderPane commentViewRoot;

    /**
     * Layout that contains every comments
     */
    @FXML
    private VBox commentContainer;

    /**
     * Svg star pictures (for the product notation)
     */
    @FXML
    private SVGPath star1, star2, star3, star4, star5;

    /**
     * Labels to display the name of the concerned product and the title of the comment
     */
    @FXML
    private Label commentProduct, commentTitle, pseudoComment, lvlComment;

    /**
     * To display the content of the comment
     */
    @FXML
    private Text commentContent;

    /**
     * Svp pictures to display next to the number of likes, dislikes, and favorite mentions of the comment
     */
    @FXML
    private SVGPath likeSVG, dislikeSVG, favoriteSVG, lvlIcon;

    /**
     * Svp pictures to click on to like, dislike, or to give a favorite mention to the comment
     */
    @FXML
    private Region buttonLike, buttonDislike, buttonFavorite, buttonShare;

    /**
     * Labels to display the number of likes, dislikes, and favorite mentions of the comment
     */
    @FXML
    private Label likeStats, dislikeStats, favoriteStats;

    /**
     * The profile picture of the user
     */
    @FXML
    private ImageView userPicture;

    /**
     * Container of all buttons
     */
    @FXML
    private HBox buttonsContainer;

    /**
     * Container of all pictures
     */
    @FXML
    private VBox picturesContainer;

    /**
     * Comment to display
     */
    private Comment myComment;

    // **************************************************
    // Getters
    // **************************************************

    /**
     * myComment getter
     *
     * @return attribute myComment
     */
    public Comment getMyComment() {
        return myComment;
    }

    // **************************************************
    // Public methods
    // **************************************************

    // void setComment used to be public but were unused outside of the class

    // **************************************************
    // Private methods
    // **************************************************

    /**
     * initializes comment
     */
    private void setComment() {
        commentProduct.setText(myComment.getMyProduct().getName());
        commentTitle.setText("\"" + myComment.getTitle() + "\"");
        commentContent.setText(myComment.getContent());
        userPicture.setImage(new Image(myComment.getMyAuthor().getPersonalPicture()));
        pseudoComment.setText(myComment.getMyAuthor().getPseudo());
        lvlComment.setText("lvl. " + myComment.getMyAuthor().getLevel().toString());
        star1.setContent(setStar(1));
        star2.setContent(setStar(2));
        star3.setContent(setStar(3));
        star4.setContent(setStar(4));
        star5.setContent(setStar(5));
        for(Image image : myComment.getPictures()){
            ImageView container = new ImageView(image);
            container.setFitWidth(320);
            container.setPreserveRatio(true);
            picturesContainer.getChildren().add(container);
        }
    }

    /**
     * initialize all icons and initialize the comment through a function
     */
    private void initialize() {
        starICON.setContent(SvgPicture.getStarSVG());
        searchICON.setContent(SvgPicture.getSearchSVG());
        profileICON.setContent(SvgPicture.getProfileSVG());
        settingsICON.setContent(SvgPicture.getSettingsSVG());
        setButtons();
        createBindings();
        setComment();
    }

    /**
     * Choose to display a full star or an empty star
     *
     * @param star set SVG of the star thanks to its value
     * @return svg picture of the star to display
     */
    private String setStar(int star) {
        if (myComment.getProductNotation() >= star)
            return SvgPicture.getFullStar();

        return SvgPicture.getEmptyStar();
    }

    /**
     * Set how buttons will be displayed
     */
    private void setButtons() {
        if(!Database.getIsConnected())
            buttonsContainer.getChildren().removeAll(buttonLike,buttonDislike,buttonFavorite);
        else if(buttonsContainer.getChildren().isEmpty())
            buttonsContainer.getChildren().addAll(buttonLike,buttonDislike,buttonFavorite);

        if(Database.getIsConnected()) {
            if (Database.getConnectedUser().getMyLikedComments().contains(myComment))
                likeSVG.setContent(SvgPicture.getLikeCommentViewFull());
            else if (Database.getConnectedUser().getMyDislikedComments().contains(myComment))
                dislikeSVG.setContent(SvgPicture.getDislikeCommentViewFull());
            else if (Database.getConnectedUser().getMyFavoriteComments().contains(myComment))
                favoriteSVG.setContent(SvgPicture.getFavoriteCommentViewFull());
        }
    }

    /**
     * Create the bindings for the text of statistics labels (like,dislike,favorite)
     */
    private void createBindings() {
        likeStats.textProperty().bind(new SimpleIntegerProperty(myComment.getLikes()).asString());

        dislikeStats.textProperty().bind(new SimpleIntegerProperty(myComment.getDislikes()).asString());

        favoriteStats.textProperty().bind(new SimpleIntegerProperty(myComment.getNbOfFavorites()).asString());
    }

    /**
     * Allow to like comment
     */
    @FXML
    private void likeComment() {
        if(likeSVG.getContent().equals(SvgPicture.getLikeCommentViewEmpty()) &&
                !Database.getConnectedUser().getMyLikedComments().contains(myComment) &&
                !Database.getConnectedUser().getMyDislikedComments().contains(myComment)) {
            myComment.addLike();
            Database.getConnectedUser().addLikedComment(myComment);
            likeSVG.setContent(SvgPicture.getLikeCommentViewFull());
        }
        else if(likeSVG.getContent().equals(SvgPicture.getLikeCommentViewFull()) &&
                Database.getConnectedUser().getMyLikedComments().contains(myComment) &&
                !Database.getConnectedUser().getMyDislikedComments().contains(myComment)) {
            myComment.removeLike();
            Database.getConnectedUser().removeLikedComment(myComment);
            likeSVG.setContent(SvgPicture.getLikeCommentViewEmpty());
        }
        else if(dislikeSVG.getContent().equals(SvgPicture.getDislikeCommentViewFull()) &&
                likeSVG.getContent().equals(SvgPicture.getLikeCommentViewEmpty()) &&
                !Database.getConnectedUser().getMyLikedComments().contains(myComment) &&
                Database.getConnectedUser().getMyDislikedComments().contains(myComment)) {
            myComment.addLike();
            myComment.removeDislike();
            Database.getConnectedUser().removeDislikedComment(myComment);
            Database.getConnectedUser().addLikedComment(myComment);
            likeSVG.setContent(SvgPicture.getLikeCommentViewFull());
            dislikeSVG.setContent(SvgPicture.getDislikeCommentViewEmpty());
        }
        System.out.println(myComment.getLikes());
    }

    /**
     * Allow to dislike a comment
     */
    @FXML
    private void dislikeComment() {
        if(dislikeSVG.getContent().equals(SvgPicture.getDislikeCommentViewEmpty()) &&
                !Database.getConnectedUser().getMyDislikedComments().contains(myComment) &&
                !Database.getConnectedUser().getMyLikedComments().contains(myComment)) {
            myComment.addDislike();
            Database.getConnectedUser().addDislikedComment(myComment);
            dislikeSVG.setContent(SvgPicture.getDislikeCommentViewFull());
        }
        else if(dislikeSVG.getContent().equals(SvgPicture.getDislikeCommentViewFull()) &&
                !Database.getConnectedUser().getMyLikedComments().contains(myComment) &&
                Database.getConnectedUser().getMyDislikedComments().contains(myComment)) {
            myComment.removeDislike();
            Database.getConnectedUser().removeDislikedComment(myComment);
            dislikeSVG.setContent(SvgPicture.getDislikeCommentViewEmpty());
        }
        else if(likeSVG.getContent().equals(SvgPicture.getLikeCommentViewFull()) &&
                dislikeSVG.getContent().equals(SvgPicture.getDislikeCommentViewEmpty()) &&
                Database.getConnectedUser().getMyLikedComments().contains(myComment) &&
                !Database.getConnectedUser().getMyDislikedComments().contains(myComment)) {
            myComment.addDislike();
            myComment.removeLike();
            Database.getConnectedUser().removeLikedComment(myComment);
            Database.getConnectedUser().addDislikedComment(myComment);
            dislikeSVG.setContent(SvgPicture.getDislikeCommentViewFull());
            likeSVG.setContent(SvgPicture.getLikeCommentViewEmpty());
        }
    }

    /**
     * Allow to put in favorite a comment
     */
    @FXML
    private void favoriteComment() {
        if(favoriteSVG.getContent().equals(SvgPicture.getFavoriteCommentViewEmpty()) &&
                !Database.getConnectedUser().getMyFavoriteComments().contains(myComment)) {
            myComment.addFavorite();
            Database.getConnectedUser().addFavoriteComment(myComment);
            favoriteSVG.setContent(SvgPicture.getFavoriteCommentViewFull());
        }
        else if(favoriteSVG.getContent().equals(SvgPicture.getFavoriteCommentViewFull()) &&
                Database.getConnectedUser().getMyFavoriteComments().contains(myComment)) {
            myComment.removeFavorite();
            Database.getConnectedUser().removeFavoriteComment(myComment);
            favoriteSVG.setContent(SvgPicture.getFavoriteCommentViewEmpty());
        }
    }

    /**
     * Load selection page
     */
    @FXML
    private void Selection() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new SelectionControl()));
    }

    /**
     * load profil page
     */
    @FXML
    private void Profil() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new ProfilControl()));
    }

    /**
     * load parametres page
     */
    @FXML
    private void Parametres() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new ParametresControl()));
    }

    /**
     * load rechercher page
     */
    @FXML
    private void Rechercher() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new RechercherControl()));
    }

    /**
     * load resultats page
     */
    @FXML
    private void Resultats() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new ResultatsControl(new ProductControl(myComment.getMyProduct()))));
    }

    /**
     * load the user profile of the comment's author
     */
    @FXML
    private void goToUserProfil() {
        Main.startNewPage((Stage) commentViewRoot.getScene().getWindow(), new Scene(new CommentViewProfil(myComment)));
    }

}