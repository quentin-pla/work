package fr.univ_amu.iut;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Database mySystem = new Database();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new AccueilControl());
        scene.getStylesheets().addAll("/fr/univ_amu/iut/style.css");
        stage.setScene(scene);
        stage.setTitle("Checked");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Start a new page
     *
     * @param stage stage of the actual scene
     * @param scene new scene to be displayed
     */
    public static void startNewPage(Stage stage, Scene scene) {
        scene.getStylesheets().addAll("/fr/univ_amu/iut/style.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * mySystem getter
     *
     * @return mySystem
     */
    public static Database getSystem() {
        return mySystem;
    }
}