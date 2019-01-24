package fr.univ_amu.iut;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A pop up
 */
class PopUp extends StackPane {

    // **************************************************
    // Constructors
    // **************************************************

    /**
     * Default constructor
     *
     * @param imageicon Picture to display on the pop up
     * @param text      Text to display on the pop up
     * @param page      Current page
     */
    PopUp(String imageicon, String text, Node page, int stringsize) {
        ImageView popupIcon = new ImageView();
        Label popupText = new Label();
        VBox popup = new VBox();
        StackPane souscouche = new StackPane(popup);

        popupIcon.setImage(new Image(imageicon, 100, 100, true, false));
        popupText.setText(text);
        popupText.setWrapText(true);

        popup.setSpacing(15);
        popup.setAlignment(Pos.CENTER);
        popup.setMaxSize(260, 180);
        popup.setMinSize(260, 180);
        popup.setPrefSize(260, 180);
        popup.setStyle("-fx-background-radius: 15;-fx-background-color: white");
        popup.getChildren().addAll(popupIcon, popupText);
        popup.setPadding(new Insets(20, 20, 20, 20));

        popupText.setStyle("-fx-font-size: " + stringsize + "%;-fx-font-family: 'Avenir Light';-fx-text-fill: black");

        souscouche.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        souscouche.setMaxWidth(this.getMaxWidth());
        souscouche.setMaxHeight(this.getMaxHeight());

        this.getChildren().addAll(page, souscouche);
        this.getChildren().get(0).setEffect(blurEffect);

        InitblurFade(10);
        InitpopupFade(popup, 0, 1);
    }

    // **************************************************
    // Fields
    // **************************************************

    /**
     * Animation of appearing of the pop up
     */
    private static FadeTransition popupFade = new FadeTransition(Duration.seconds(0.12));

    /**
     * Blur effect behind the pop up
     */
    private static final BoxBlur blurEffect = new BoxBlur(0, 0, 10);

    /**
     * Speed of blur appearing
     */
    private static Timeline blurFade = new Timeline();

    // **************************************************
    // Private methods
    // **************************************************

    /**
     * Inits the pop up fade
     *
     * @param popup      PopUp on which add the fade
     * @param startValue Opacity of the pop at the beginning of appearing
     * @param endValue   Opacity of the pop at the end of appearing
     */
    private void InitpopupFade(VBox popup, Integer startValue, Integer endValue) {
        popupFade.setFromValue(startValue);
        popupFade.setToValue(endValue);
        popupFade.setCycleCount(1);
        popupFade.setNode(popup);
        popupFade.play();
    }

    /**
     * Inits the blur fade
     *
     * @param endValue Duration of the blur fade
     */
    private void InitblurFade(Integer endValue) {
        blurFade.setCycleCount(1);
        final KeyValue blurWidthP = new KeyValue(blurEffect.widthProperty(), endValue);
        final KeyValue blurHeightP = new KeyValue(blurEffect.heightProperty(), endValue);
        final KeyFrame kf = new KeyFrame(Duration.millis(100), blurWidthP, blurHeightP);
        blurFade.getKeyFrames().add(kf);
        blurFade.play();
    }
}