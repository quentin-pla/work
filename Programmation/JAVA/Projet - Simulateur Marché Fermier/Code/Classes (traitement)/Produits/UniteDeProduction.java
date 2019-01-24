package marche.traitement.Classes.Produits;

/**
 * Classe correspondant a une unité de production
 */
public interface UniteDeProduction {

    /**
     * @return une nouvelle unité du produit passé en paramètre
     */
    public ProduitFermier creerProduit();

    public String getNomProduit();
}