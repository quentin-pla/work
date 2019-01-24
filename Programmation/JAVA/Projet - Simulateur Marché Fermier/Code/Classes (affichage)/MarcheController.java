package marche.affichage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Systeme.Marche;

public class MarcheController {

    private Marche marcheActuel;

    @FXML
    private VBox contenu;

    @FXML
    private Label nomMarche;

    @FXML
    private Button parametres;

    @FXML
    private void initialize(){}

    @FXML
    private void afficherAccueil(){
        Main.changerPage("Accueil",null);
    }

    @FXML
    private void afficherParametres(){
        Main.changerPage("ParametresMarche",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherParticipants(){
        Main.changerPage("ParticipantsMarche",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherOffres(){
        Main.changerPage("OffresMarche",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherBourse(){
        Main.changerPage("Bourse",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marcheActuel) {
        this.marcheActuel = marcheActuel;
        setNomMarche(this.marcheActuel.getRegion());
    }

    public void setNomMarche(String nomMarche) {
        this.nomMarche.setText("Marché de " + nomMarche);
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }

}
