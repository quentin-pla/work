package marche.traitement.Classes.Systeme.Taxes;

import marche.traitement.Classes.Acteurs.Fermier.Fermier;
import marche.traitement.Classes.Acteurs.Participant;

/**
 * Classe correspondant a une taxe de type taxe petit producteur
 */
public class TaxePetitProducteur extends Taxe{

    /**
     * Constructeur par défaut de TaxePetitProducteur
     */
    public TaxePetitProducteur() {
        this.prelevement = 5;
    }

}