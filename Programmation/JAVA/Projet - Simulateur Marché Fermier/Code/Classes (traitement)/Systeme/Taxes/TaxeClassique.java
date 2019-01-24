package marche.traitement.Classes.Systeme.Taxes;

import marche.traitement.Classes.Acteurs.Participant;

/**
 * Classe corespondant a une taxe de type taxe classique
 */
public class TaxeClassique extends Taxe{

    /**
     * Constructeur par défaut de TaxeClassique
     */
    public TaxeClassique() {
        this.prelevement = 15;
    }

}