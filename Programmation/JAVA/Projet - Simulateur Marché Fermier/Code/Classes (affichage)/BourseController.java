package marche.affichage;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Produits.Labels.LabelRouge;
import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Offre;
import marche.traitement.Classes.Systeme.Offres.Vente;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BourseController {

    private Marche marcheActuel;

    private Set<ProduitFermier> produits = new HashSet<ProduitFermier>();

    @FXML
    private VBox tableContent;

    @FXML
    private Button ajouter;

    private void afficherProduits(){
        tableContent.getChildren().add(ligneTop());
        for(ProduitFermier produitFermier : produits){
            tableContent.getChildren().add(creerLigne(produitFermier));
        }
    }

    private HBox ligneTop(){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(5));
        ligne.setId("ligneTop");
        Label type = new Label("Type");
        Label nom = new Label("Nom");
        Label qualite = new Label("Qualite");
        Label labels = new Label("Label(s)");
        Label prixMoyen = new Label("Prix moyen");
        type.setId("label");
        nom.setId("label");
        qualite.setId("label");
        labels.setId("label");
        prixMoyen.setId("label");
        type.setPrefWidth(130);
        nom.setPrefWidth(170);
        qualite.setPrefWidth(147);
        labels.setPrefWidth(190);
        prixMoyen.setPrefWidth(150);
        ligne.getChildren().addAll(type,nom,qualite,labels,prixMoyen);
        return ligne;
    }

    private HBox creerLigne(ProduitFermier produit){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(0,5,0,5));
        ligne.setId("ligne");
        Label type = new Label(produit.getClass().getSimpleName());
        Label nom = new Label(produit.getNom());
        Label qualite = new Label(produit.getQualite());
        Label labels = new Label();
        if(produit.getMesLabels().size() > 0)
            for(marche.traitement.Classes.Produits.Labels.Label label : produit.getMesLabels()) labels.setText(labels.getText().concat(label.getClass().getSimpleName() + " "));
        else labels.setText("x");
        String prixMoyenProduit = new DecimalFormat("0.00").format(marcheActuel.getMaBourse().getPrixMoyenProduit(produit)) + "€/unité";
        Label prixMoyen = new Label(prixMoyenProduit);
        type.setId("label");
        nom.setId("label");
        qualite.setId("label");
        labels.setId("label");
        prixMoyen.setId("label");
        type.setPrefWidth(130);
        nom.setPrefWidth(170);
        qualite.setPrefWidth(147);
        labels.setPrefWidth(190);
        prixMoyen.setPrefWidth(150);
        ligne.getChildren().addAll(type,nom,qualite,labels,prixMoyen);
        return ligne;
    }

    @FXML
    private void afficherMarche(){
        Main.changerPage("Marche",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherPageAjout(){
        Main.changerPage("AjoutProduit",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        this.produits.addAll(marcheActuel.getMaBourse().getMesProduits().keySet());
        afficherProduits();
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
