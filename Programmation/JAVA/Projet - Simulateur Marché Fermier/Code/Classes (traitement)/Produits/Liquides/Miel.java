package marche.traitement.Classes.Produits.Liquides;

/**
 * Classe correspondant aux liquides de type Miel
 */
public class Miel extends Liquide {

    /**
     * Constructeur par défaut de Miel
     */
    public Miel() {
        this.datePeremption = setDatePeremption(360);
    }
}