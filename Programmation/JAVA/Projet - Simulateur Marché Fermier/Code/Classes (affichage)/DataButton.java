package marche.affichage;

import javafx.scene.control.Button;

public class DataButton extends Button {

    private int donnee;

    public DataButton(String titre, Integer donnee){
        this.donnee = donnee;
        this.setText(titre);
    }

    public int getDonnee() {
        return donnee;
    }

    public void setDonnee(int donnee) {
        this.donnee = donnee;
    }
}
