<?php
/**
 * Created by PhpStorm.
 * User: sylva
 * Date: 16/10/2018
 * Time: 11:50
 */

namespace Cookburn\Models;

use Core\Controllers\Model;

class Ingredient extends Model
{
    /**
     * Retourne un ingrédient en fonction d'un nom
     * @param $NOM_INGR
     * @return array
     */
    public function getByName($NOM_INGR){
        return $this->get_db()->query('SELECT * FROM INGREDIENT WHERE NOM_INGR = '.'\''.$NOM_INGR.'\'', __CLASS__);
    }

    /**
     * Retourne la liste de tous les ingrédients
     * @return array
     */
    public function getAll(){
        return $this->get_db()->query('SELECT ID_INGR, NOM_INGR, DESCR_INGR, URL_IMAGE, NOM_UT FROM INGREDIENT
                                                JOIN UTILISATEUR ON INGREDIENT.ID_UT = UTILISATEUR.ID_UT
                                                GROUP BY ID_INGR', __CLASS__);
    }

    /**
     * Retourne la liste de tous les ingrédients sauf ceux déjà présent dans la recette
     * @param $ID
     * @return array|mixed
     */
    public function getAllSaufCeuxDansRecette($ID){
        return $this->get_db()->prepare('SELECT * FROM INGREDIENT WHERE ID_INGR NOT IN (SELECT ID_INGR FROM INGREDIENT_RECETTE WHERE ID = ?) ORDER BY NOM_INGR',[$ID], __CLASS__);
    }

    /**
     * Retourne la liste de tous les ingrédients en fonction d'un nom
     * @return array|mixed
     */
    public function getByName2(){
        return $this->get_db()->prepare('SELECT * FROM INGREDIENT WHERE NOM_INGR = ?',[$_POST['nom_ingr']],__CLASS__, true);
    }

    /**
     * Retourne la liste de tous les ingrédients en fonction d'un ID_INGR
     * @return array|mixed
     */
    public function getById(){
        return $this->get_db()->prepare('SELECT ID_INGR, NOM_INGR, DESCR_INGR, URL_IMAGE, INGREDIENT.ID_UT, NOM_UT
                                                  FROM INGREDIENT
                                                  JOIN UTILISATEUR ON INGREDIENT.ID_UT = UTILISATEUR.ID_UT
                                                  WHERE ID_INGR = ?',[$_POST['id_ingr']],__CLASS__, true);
    }

    /**
     * Retourne la liste de tous les ingrédients en fonction d'un ID_INGR
     * @param $ID_INGR
     * @return array|mixed
     */
    public function getById2($ID_INGR){
        return $this->get_db()->prepare('SELECT ID_INGR, NOM_INGR, DESCR_INGR, URL_IMAGE, INGREDIENT.ID_UT, NOM_UT
                                                  FROM INGREDIENT
                                                  JOIN UTILISATEUR ON INGREDIENT.ID_UT = UTILISATEUR.ID_UT
                                                  WHERE ID_INGR = ?',[$ID_INGR],__CLASS__,true);
    }

    /**
     * Retourne la liste de tous les ingrédients en fonction d'un utilisateur
     * @return array|mixed
     */
    public function getByUtCourant() {
        return $this->get_db()->prepare('SELECT * FROM INGREDIENT WHERE ID_UT = ?',[$_SESSION['id_ut']], __CLASS__);
    }

    /**
     * Update de la description d'un ingrédient
     */
    public function upDESCR_INGR(){
        $this->get_db()->insert2('UPDATE INGREDIENT SET DESCR_INGR = ? WHERE ID_INGR = ?',[$_POST['descr_ingr'],$_POST['id_ingr']]);
    }

    /**
     * Update du nom d'un ingrédient
     */
    public function upNOM_INGR(){
        $this->get_db()->insert2('UPDATE INGREDIENT SET NOM_INGR = ? WHERE ID_INGR = ?',[$_POST['nom_ingr'],$_POST['id_ingr']]);
    }

    /**
     * Update de la photo d'un ingrédient
     */
    public function upPHOTO_INGR(){
        $this->get_db()->insert2('UPDATE INGREDIENT SET URL_IMAGE = ? WHERE ID_INGR = ?',[$_POST['link'],$_POST['id']]);
    }

    /**
     * Création d'un nouvelle ingrédient
     * @return string
     */
    public function insertIngredient(){
        return $this->get_db()->insert('INSERT INTO INGREDIENT (NOM_INGR,DESCR_INGR,ID_UT) VALUES (?,?,?)',[$_POST['nom_ingr'],$_POST['descr_ingr'],$_SESSION['id_ut']]);
    }

    /**
     * Suppression d'un ingrédient
     */
    public function delete(){
        $var = [$_POST['id_ingr']];
        $this->get_db()->insert2('DELETE FROM INGREDIENT WHERE ID_INGR=?', $var);
    }
}