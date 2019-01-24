package marche.affichage;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Offre;
import marche.traitement.Classes.Systeme.Offres.Vente;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OffresMarcheController {

    private Marche marcheActuel;

    private boolean selectionner;

    private String retourOffre;

    private List<Offre> offres;

    @FXML
    private VBox tableContent;

    @FXML
    private Button ajouter, retour;

    @FXML
    private Label titre;

    private void afficherOffres(){
        tableContent.getChildren().add(ligneTop());
        for(Offre offre : offres){
            if(selectionner){
                if(offre.getClass().getSimpleName().equals("Vente")) tableContent.getChildren().add(creerLigne(offre));
            }
            else tableContent.getChildren().add(creerLigne(offre));
        }
    }

    private HBox ligneTop(){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(5));
        ligne.setId("ligneTop");
        Label type = new Label("Type");
        Label participant = new Label("Participant");
        Label produit = new Label("Produit");
        Label qualite = new Label("Qualite");
        Label quantite = new Label("Quantite");
        Label prix = new Label("Prix");
        type.setId("label");
        participant.setId("label");
        produit.setId("label");
        qualite.setId("label");
        quantite.setId("label");
        prix.setId("label");
        type.setPrefWidth(80);
        participant.setPrefWidth(160);
        produit.setPrefWidth(170);
        qualite.setPrefWidth(130);
        quantite.setPrefWidth(125);
        prix.setPrefWidth(120);
        ligne.getChildren().addAll(type,participant,produit,qualite,quantite,prix);
        return ligne;
    }

    private HBox creerLigne(Offre offre){
        HBox ligne = new HBox();
        ligne.setPadding(new Insets(0,5,0,5));
        ligne.setId("ligne");
        Label type = new Label(offre.getClass().getSimpleName());
        Label participant = new Label(offre.getParticipant().getClass().getSimpleName());
        Label produit = new Label();
        ProduitFermier produitOffre;
        String nomProduitComplet;
        if(offre.getClass().getSimpleName().equals("Vente")){
            nomProduitComplet = ((Vente) offre).getProduit().getNom();
            produitOffre = ((Vente) offre).getProduit();
        }
        else {
            nomProduitComplet = ((Achat) offre).getOffreVente().getProduit().getNom();
            produitOffre = ((Achat) offre).getOffreVente().getProduit();
        }
        if(!produitOffre.getMesLabels().isEmpty()) {
            nomProduitComplet += (" (");
            if(produitOffre.getMesLabels().size() == 1){
                nomProduitComplet += produitOffre.getMesLabels().get(0).getClass().getSimpleName();
            }
            else {
                for (marche.traitement.Classes.Produits.Labels.Label label : produitOffre.getMesLabels())
                    nomProduitComplet += label.getClass().getSimpleName() + " ";
            }
            nomProduitComplet += (")");
        }
        produit.setText(nomProduitComplet);
        Label qualite = new Label();
        if(offre.getClass().getSimpleName().equals("Vente")) qualite.setText(((Vente) offre).getProduit().getQualite());
        else qualite.setText(((Achat) offre).getOffreVente().getProduit().getQualite());
        Label quantite = new Label(offre.getQuantite().toString() + " unité(s)");
        Label prix = new Label();
        if(offre.getClass().getSimpleName().equals("Vente")) prix.setText(new DecimalFormat("0.00").format(((Vente) offre).getPrixUnite()) + "€/unité");
        else prix.setText(((Achat) offre).getPrix().toString() + "€");
        type.setId("label");
        participant.setId("label");
        produit.setId("label");
        qualite.setId("label");
        quantite.setId("label");
        prix.setId("label");
        type.setPrefWidth(80);
        participant.setPrefWidth(160);
        produit.setPrefWidth(170);
        qualite.setPrefWidth(130);
        quantite.setPrefWidth(125);
        prix.setPrefWidth(120);
        ligne.getChildren().addAll(type,participant,produit,qualite,quantite,prix);
        if(selectionner){
            ligne.setCursor(Cursor.HAND);
            String donnees = retourOffre + getIndexMarcheActuelString() + marcheActuel.getMonControleur().getMonCatalogue().getOffresVente().indexOf(offre);
            ligne.setOnMouseClicked(e -> Main.changerPage("AjoutOffre_RetourOffre", donnees));
        }
        return ligne;
    }

    @FXML
    private void afficherPageAjout(){
        Main.changerPage("AjoutOffre",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherMarche(){
        Main.changerPage("Marche",getIndexMarcheActuelString());
    }

    @FXML
    private void afficherPageAjoutOffre(){
        Main.changerPage("AjoutOffre",getIndexMarcheActuelString());
    }

    public void setMarcheActuel(Marche marche){
        this.marcheActuel = marche;
        this.offres = Stream.concat(this.marcheActuel.getMonControleur().getMonCatalogue().getOffresVente().stream(), this.marcheActuel.getMonControleur().getMonCatalogue().getOffresAchat().stream()).collect(Collectors.toList());
        afficherOffres();
    }

    public void seulementSelectionner(){
        selectionner = true;
        ajouter.setVisible(false);
        ajouter.setManaged(false);
        titre.setText("Sélection");
        retour.setOnAction(e -> afficherPageAjoutOffre());
    }

    public void setRetourOffre(String retourOffre) {
        this.retourOffre = retourOffre;
    }

    private String getIndexMarcheActuelString(){ return String.valueOf(Main.marches.indexOf(marcheActuel)); }
}
