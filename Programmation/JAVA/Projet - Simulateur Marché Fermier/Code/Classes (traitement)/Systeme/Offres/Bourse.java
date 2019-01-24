package marche.traitement.Classes.Systeme.Offres;

import marche.traitement.Classes.Produits.ProduitFermier;
import marche.traitement.Classes.Systeme.Marche;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Classe correspondant a la bourse d'un marché
 */
public class Bourse {

    /**
     * Map qui associe à chaque produit, les prix qui lui ont été associés.
     */
    private Map <ProduitFermier, List<Double>> mesProduits = new HashMap<ProduitFermier, List<Double>>();

    /**
     * Correspond au marché de la bourse
     */
    private Marche monMarche;

    /**
     * Constructeur par defaut de Bourse
     */
    public Bourse(Marche marche) {
        this.monMarche = marche;
    }

    /**
     * Permet de mettre a jour un produit
     * @param produit : produit que l'on souhaite mettre a jour
     */
    public void majProduit(ProduitFermier produit, double prixUnite){
        boolean productFound = false;
        if(getMesProduits().size() > 0) {
            for (Map.Entry<ProduitFermier, List<Double>> it : getMesProduits().entrySet())
                if (it.getKey().equals(produit)) {
                    productFound = true;
                    double oldPrice = getPrixMoyenProduit(produit);
                    it.getValue().add(prixUnite);
                    double differencePrix = 100 * ((getPrixMoyenProduit(produit) - oldPrice) / oldPrice);
                    System.out.print("[i] Mise à jour du prix du produit dans la Bourse ");
                    if(differencePrix > 0) System.out.println("(Augmentation de " + new DecimalFormat("0.00").format(differencePrix) + "%)");
                    else System.out.println("(Diminution de " + new DecimalFormat("0.00").format(differencePrix*-1) + "%)");
                }
        }
        if(!productFound){
            getMesProduits().put(produit, new ArrayList<Double>());
            getMesProduits().get(produit).add(prixUnite);
            System.out.println("[i] Insertion du produit " + produit.getNom() + " dans la Bourse.");
        }
    }

    /**
     * Permet de savoir le prix moyen d'un produit
     * @param produit : produit dont on souhaite savoir le prix moyen
     * @return le prix moyen du produit
     */
    public Double getPrixMoyenProduit(ProduitFermier produit){
        boolean productFound = false;
        if(getMesProduits().size() > 0) {
            for (Map.Entry<ProduitFermier, List<Double>> it : getMesProduits().entrySet()) {
                if (it.getKey().equals(produit)){
                    productFound = true;
                    double somme = 0;
                    for(double prix : it.getValue())somme += prix;
                    return somme / it.getValue().size();
                }
            }
        }
        return 0.0;
    }

    /**
     * Permet de supprimer un produit
     * @param produit : le produit que l'on souhaite supprimer
     */
    public void supprimerProduit(ProduitFermier produit){ getMesProduits().remove(produit); }

    /**
     * Getter de mesPoduits
     * @return la liste des produits et de leurs prix
     */
    public Map <ProduitFermier, List<Double>> getMesProduits() {
        return this.mesProduits;
    }

    /**
     * Getter de monMarche
     * @return le marché de ma bourse
     */
    public Marche getMonMarche() {
        return this.monMarche;
    }
}