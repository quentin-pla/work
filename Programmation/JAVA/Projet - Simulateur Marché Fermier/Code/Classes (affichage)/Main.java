package marche.affichage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import marche.traitement.Classes.Acteurs.Fermier.Apiculteur;
import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Acteurs.Fermier.Horticulteur;
import marche.traitement.Classes.Acteurs.Paysan;
import marche.traitement.Classes.Produits.Labels.BIO;
import marche.traitement.Classes.Systeme.Marche;
import marche.traitement.Classes.Systeme.Offres.Achat;
import marche.traitement.Classes.Systeme.Offres.Vente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

    public static List<Marche>marches = new ArrayList<Marche>();

    public static Parent root;
    public static Stage stage;

    public void start(Stage primaryStage) throws Exception {
        Main.marches.add(new Marche("Marseille"));
        simulerMarche(marches.get(0));
        stage = primaryStage;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("FXML/Accueil.fxml")));
        stage.setTitle("Simulateur Marché Fermier");
        stage.setScene(new Scene(root,1000,700));
        stage.getScene().getStylesheets().add("/CSS/style.css");
        stage.setResizable(false);
        stage.show();
    }

    private void simulerMarche(Marche marche){
        Apiculteur apiculteur = new Apiculteur(100);
        Horticulteur horticulteur = new Horticulteur(100);
        marche.ajouterParticipant(apiculteur);
        marche.ajouterParticipant(horticulteur);
        marche.ajouterParticipant(new Paysan());
        apiculteur.produire("miel","haute",10, new BIO());
        horticulteur.produire("carotte","moyenne",10);
        apiculteur.proposerOffreVente(new Vente(apiculteur.getMonProduit(apiculteur.getMesProduits().get(0)),5,5.99));
        horticulteur.proposerOffreVente(new Vente(horticulteur.getMonProduit(horticulteur.getMesProduits().get(0)),2,0.49));
        horticulteur.setSolde(10);
        horticulteur.proposerOffreAchat(new Achat(marche.getMonControleur().getMonCatalogue().getOffresVente().get(0),1));
        apiculteur.forcerVente(marche.getMonControleur().getMonCatalogue().getOffresVente().get(0));
        apiculteur.proposerOffreVente(new Vente(apiculteur.getMesProduits().get(0),9,5.99));
    }

    public static void changerPage(String page, String donnee){
        try {
            String fxmlLink = page.split("_", 2)[0];
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource("FXML/" + fxmlLink + ".fxml"));
            root = fxmlLoader.load();

            int indexMarche;
            int indexParticipant;
            int indexVente;
            String typeOffre;

            switch (page){
                case "Marche":
                    ((MarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "ParametresMarche":
                    ((ParametresMarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "ParticipantsMarche":
                    ((ParticipantsMarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "OffresMarche":
                    ((OffresMarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "Bourse":
                    ((BourseController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "AjoutParticipant":
                    ((AjoutParticipantController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "AjoutOffre":
                    ((AjoutOffreController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "AjoutProduit":
                    ((AjoutProduitController)fxmlLoader.getController()).setMarcheActuel(marches.get(Integer.parseInt(donnee)));
                    break;
                case "ParticipantsMarche_Selection":
                    typeOffre = donnee.substring(0,1);
                    indexMarche = Integer.parseInt(donnee.substring(1,2));
                    if(typeOffre.equals("A")){
                        indexVente = Integer.parseInt(donnee.substring(2,3));
                        ((ParticipantsMarcheController)fxmlLoader.getController()).setRetourVente(indexVente);
                    }
                    ((ParticipantsMarcheController)fxmlLoader.getController()).setTypeRetour(typeOffre);
                    ((ParticipantsMarcheController)fxmlLoader.getController()).seulementSelectionner();
                    ((ParticipantsMarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(indexMarche));
                    break;
                case "OffresMarche_Selection":
                    typeOffre = donnee.substring(0,1);
                    indexMarche = Integer.parseInt(donnee.substring(1,2));
                    ((OffresMarcheController)fxmlLoader.getController()).seulementSelectionner();
                    ((OffresMarcheController)fxmlLoader.getController()).setRetourOffre(typeOffre);
                    ((OffresMarcheController)fxmlLoader.getController()).setMarcheActuel(marches.get(indexMarche));
                    break;
                case "AjoutOffre_RetourParticipant":
                    typeOffre = donnee.substring(0,1);
                    indexMarche = Integer.parseInt(donnee.substring(1,2));
                    indexParticipant = Integer.parseInt(donnee.substring(2,3));
                    if(typeOffre.equals("A")){
                        indexVente = Integer.parseInt(donnee.substring(3,4));
                        ((AjoutOffreController)fxmlLoader.getController()).setVente(marches.get(indexMarche).getMonControleur().getMonCatalogue().getOffresVente().get(indexVente));
                    }
                    ((AjoutOffreController)fxmlLoader.getController()).setTypeOffre(typeOffre);
                    ((AjoutOffreController)fxmlLoader.getController()).setParticipant(marches.get(indexMarche).getMesParticipants().get(indexParticipant));
                    ((AjoutOffreController)fxmlLoader.getController()).setMarcheActuel(marches.get(indexMarche));
                    break;
                case "AjoutOffre_RetourOffre":
                    typeOffre = donnee.substring(0,1);
                    indexMarche = Integer.parseInt(donnee.substring(1,2));
                    if(typeOffre.equals("A")){
                        indexVente = Integer.parseInt(donnee.substring(2,3));
                        ((AjoutOffreController)fxmlLoader.getController()).setVente(marches.get(indexMarche).getMonControleur().getMonCatalogue().getOffresVente().get(indexVente));
                    }
                    ((AjoutOffreController)fxmlLoader.getController()).setTypeOffre(typeOffre);
                    ((AjoutOffreController)fxmlLoader.getController()).setMarcheActuel(marches.get(indexMarche));
                    break;
                case "AjoutProduit_RetourParticipant":
                    indexMarche = Integer.parseInt(donnee.substring(0,1));
                    indexParticipant = Integer.parseInt(donnee.substring(1,2));
                    ((AjoutProduitController)fxmlLoader.getController()).setFermier((Fermier)marches.get(indexMarche).getMesParticipants().get(indexParticipant));
                    ((AjoutProduitController)fxmlLoader.getController()).setMarcheActuel(marches.get(indexMarche));
                    break;
            }

            stage.setScene(new Scene(root,1000,700));
            stage.getScene().getStylesheets().add("/CSS/style.css");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
