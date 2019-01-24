package marche.traitement.Classes.Produits.Liquides;

/**
 * Classe correspondant aux liquides de type lait
 */
public class Lait extends Liquide {

    /**
     * Constructeur par défaut de Lait
     */
    public Lait() {
        this.datePeremption = setDatePeremption(30);
    }
}