<?php

namespace Cookburn\Models;

use Core\Controllers\Model;

class Ingredient_Recette extends  Model
{
    /**
     * Renvoie tous les ingrédient d'une recette
     * @param $ID
     * @return array|mixed
     */
    public function getByID($ID){
        return $this->get_db()->prepare('SELECT INGREDIENT.ID_INGR, INGREDIENT.NOM_INGR, QUANTITE, UNITE, URL_IMAGE FROM INGREDIENT_RECETTE JOIN INGREDIENT ON  INGREDIENT_RECETTE.ID_INGR = INGREDIENT.ID_INGR WHERE ID = ?', [$ID], __CLASS__);
    }

    /**
     * Ajout d'un ingrédient dans une rtecette
     * @param $ID_INGR
     */
    public function addIngr($ID_INGR){
        $var=[$ID_INGR,$_POST['id'],$_POST['quantite'],$_POST['unite']];
        $this->get_db()->insert2('INSERT INTO INGREDIENT_RECETTE (ID_INGR,ID,QUANTITE,UNITE) VALUES (?,?,?,?)',$var);
    }

    /**
     * Suppression d'un ingrédient d'une recette
     */
    public function delIngr(){
        $var = [$_POST['id'], $_POST['id_ingr']];
        $this->get_db()->insert2('DELETE FROM INGREDIENT_RECETTE WHERE ID=? AND ID_INGR = ?', $var);
    }

    /**
     * Suppression d'un ingrédient d'une recette
     */
    public function delIngrByIdRecette(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM INGREDIENT_RECETTE WHERE ID=?', $var);
    }

    /**
     * Suppression de tous les INGREDIENT_RECETTE en fonction d'un ingrédient
     */
    public function delIngrByIdIngr(){
        $var = [$_POST['id_ingr']];
        $this->get_db()->insert2('DELETE FROM INGREDIENT_RECETTE WHERE ID_INGR=?', $var);
    }

    /**
     * Supprime tous les INGREDIENT_RECETTE en fonction d'un ingrédient d'un utilisateur
     */
    public function delIngrByIdUt(){
        $var = [$_POST['id']];
        $recette = $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID_UT=?', $var, __CLASS__);
        $string = "";
        foreach($recette as $re){
            $string = $string.($re->ID).',';
        }
        if($string != "") {
            $string = substr($string, 0, (strlen($string) - 1));
            $this->get_db()->insert3('DELETE FROM INGREDIENT_RECETTE WHERE ID IN (' . $string . ')');
        }
    }
}