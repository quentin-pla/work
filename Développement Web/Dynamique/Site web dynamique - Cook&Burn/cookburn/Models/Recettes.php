<?php
namespace Cookburn\Models;

use Core\Controllers\Model;

class Recettes extends Model{

    /**
     * Renvoie toute les recettes
     * @return array
     */
    public function getAll(){
        return $this->get_db()->query('SELECT RECETTE.ID, NOM_RE, NB_CONV, DESC_C, RECETTE.STATUT, URL_IMAGE, UTILISATEUR.NOM_UT, COUNT(*) NBBURN
                                                FROM RECETTE
                                                JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                GROUP BY RECETTE.ID', __CLASS__);
    }

    /**
     * Renvoie toutes les recettes en fonction du role de l'utilisateur par ordre de nb de like
     * @return array
     */
    public function getAllDesc(){
        if(isset($_SESSION['id_ut']) && $_SESSION['id_ut'] != null){
            return $this->get_db()->query('SELECT RECETTE.*, COUNT(BURN.ID) NBBURN 
                                                FROM RECETTE LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                WHERE STATUT != 0
                                                GROUP BY ID
                                                ORDER BY NBBURN DESC', __CLASS__);
        }
        else {
           return $this->get_db()->query('SELECT RECETTE.*, COUNT(BURN.ID) NBBURN 
                                                FROM RECETTE LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                WHERE STATUT = 2
                                                GROUP BY ID
                                                ORDER BY NBBURN DESC', __CLASS__);
        }
    }
    public function getRecetteAndBurnByParamId($id) {
        return $this->get_db()->prepare('SELECT RECETTE.*, COUNT(BURN.ID) NBBURN
                                                FROM RECETTE LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                WHERE RECETTE.ID = ?', [$id], __CLASS__, true);
    }

    /**
     * Renvoie une recette en fonction de son nom
     * @return array|mixed
     */
    public function getByName(){
        return $this->get_db()->prepare('SELECT * FROM RECETTE WHERE NOM_RE = ?', [$_GET['keywords']], __CLASS__);
    }

    /**
     * Renvoie toutes les recettes d'un utilisateur
     * @return array|mixed
     */
    public function getByUser(){
        return $this->get_db()->prepare('SELECT * FROM RECETTE WHERE NOM_UT = ?', [$_GET['keywords']], __CLASS__);
    }

    /**
     * Renvoie des recettes en fonction d'un mot clé
     * @return array|mixed
     */
    public function getLikeKeyword(){
        $key = '%'.$_GET['keywords'].'%';
            return $this->get_db()->prepare('SELECT NOM_RE, UTILISATEUR.NOM_UT,RECETTE.URL_IMAGE,DESC_C,RECETTE.ID, RECETTE.STATUT,  COUNT(BURN.ID) NBBURN
            
                                                      FROM RECETTE JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                          LEFT JOIN BURN ON BURN.ID = RECETTE.ID
                                                      WHERE RECETTE.ID IN 
                                                              (SELECT DISTINCT RECETTE.ID
                                                                  FROM RECETTE
                                                                  LEFT JOIN INGREDIENT_RECETTE ON RECETTE.ID = INGREDIENT_RECETTE.ID
                                                                  JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                                  LEFT JOIN INGREDIENT ON INGREDIENT.ID_INGR = INGREDIENT_RECETTE.ID_INGR
                                                                  WHERE RECETTE.STATUT != 0 AND
                                                                    (NOM_RE LIKE ?
                                                                     OR NOM_UT LIKE ?
                                                                     OR DESC_L LIKE ?
                                                                     OR DESC_C LIKE ?
                                                                     OR NOM_INGR LIKE ?
                                                                     OR UTILISATEUR.NOM_UT LIKE ?))
                                                      GROUP BY RECETTE.ID', [$key, $key, $key, $key, $key, $key], __CLASS__);
    }

    /**
     * Renvoie des recettes en fonction d'un mot clé
     * @return array|mixed
     */
    public function getLikeKeyword2(){
        $key = '%'.$_GET['keywords'].'%';
        return $this->get_db()->prepare('SELECT NOM_RE, UTILISATEUR.NOM_UT,RECETTE.URL_IMAGE,DESC_C,RECETTE.ID, RECETTE.STATUT,  COUNT(*) NBBURN
            
                                                      FROM RECETTE JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                          LEFT JOIN BURN ON BURN.ID = RECETTE.ID
                                                      WHERE RECETTE.ID IN 
                                                              (SELECT DISTINCT RECETTE.ID
                                                                  FROM RECETTE
                                                                  LEFT JOIN INGREDIENT_RECETTE ON RECETTE.ID = INGREDIENT_RECETTE.ID
                                                                  JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                                  LEFT JOIN INGREDIENT ON INGREDIENT.ID_INGR = INGREDIENT_RECETTE.ID_INGR
                                                                  WHERE RECETTE.STATUT = 2 AND 
                                                                     ( NOM_RE LIKE ?
                                                                     OR NOM_UT LIKE ?
                                                                     OR DESC_L LIKE ?
                                                                     OR DESC_C LIKE ?
                                                                     OR NOM_INGR LIKE ?
                                                                     OR UTILISATEUR.NOM_UT LIKE ?))
                                                      GROUP BY RECETTE.ID', [$key, $key, $key, $key, $key, $key], __CLASS__);
    }

    /**
     * Renvoie une recette en fonction d'un ID
     * @return array|mixed
     */
    public function getById(){
        return $this->get_db()->prepare('SELECT RECETTE.ID, RECETTE.ID_UT, NOM_RE, NB_CONV, DESC_C, DESC_L, RECETTE.STATUT, URL_IMAGE, UTILISATEUR.NOM_UT, COUNT(BURN.ID) NBBURN
                                                  FROM RECETTE
                                                  JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                  LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                  WHERE RECETTE.ID = ?', [$_GET['ID']], __CLASS__,true);
    }

    /**
     * Renvoie une recette en fonction d'un ID
     * @param $ID
     * @return null
     */
    public function getById2($ID){
        $recette = $this->get_db()->query('SELECT RECETTE.ID, RECETTE.ID_UT, NOM_RE, NB_CONV, DESC_C, DESC_L, RECETTE.STATUT, URL_IMAGE, UTILISATEUR.NOM_UT, COUNT(BURN.ID) NBBURN
                                                  FROM RECETTE
                                                  JOIN UTILISATEUR ON RECETTE.ID_UT = UTILISATEUR.ID_UT
                                                  LEFT JOIN BURN ON RECETTE.ID = BURN.ID
                                                  WHERE RECETTE.ID = '. $ID, __CLASS__);
        if (count($recette) !== 1) return null;
        return $recette[0];
    }

    /**
     * Renvoie toute les recettes d'un utilisateur
     * @return array|mixed
     */
    public function getByUtCourant(){
        return $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID_UT = ?', [$_SESSION['id_ut']], __CLASS__);
    }

    /**
     * Rencoie toute les recette en fonction des favcris d'un utilisateur
     * @return array|mixed
     */
    public function getUtCourantFavs(){
        return $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID IN (SELECT * FROM FAVORI WHERE NOM_UT = ?)', [$_SESSION['nom_ut']], __CLASS__);
    }

    /**
     * Insertion d'une recette
     * @return string
     */
    public function insert(){
        $var = [$_POST['nom_re'],$_SESSION['id_ut']];
        return $this->get_db()->insert('INSERT INTO RECETTE (NOM_RE,ID_UT,STATUT) VALUES (?,?,0)', $var);
    }

    /**
     * Update de l'image d'une recette
     */
    public function upURL_IMAGE(){
        $var=[$_POST['link'], $_POST['id']];
        $this->get_db()->insert2('UPDATE RECETTE SET URL_IMAGE=? WHERE ID=?', $var);
    }

    /**
     * Update du nopmbre de convive d'une recette
     */
    public function upNbConv(){
        $var=[$_POST['nbConv'], $_POST['id']];
        $this->get_db()->insert2('UPDATE RECETTE SET NB_CONV=? WHERE ID=?', $var);
    }

    /**
     * Update de la description longue d'une recette
     */
    public function upDESC_L(){
        $var=[$_POST['desc'], $_POST['id']];
        $this->get_db()->insert2('UPDATE RECETTE SET DESC_L=? WHERE ID=?', $var);
    }
    /**
     * Update de la description courte d'un utilisateur
     */
    public function upDESC_C(){
        $var=[$_POST['desc'], $_POST['id']];
        $this->get_db()->insert2('UPDATE RECETTE SET DESC_C=? WHERE ID=?', $var);
    }

    /**
     * Upsate du statut d'une recette
     * @param $var
     */
    public function upSTATUT($var){
        $this->get_db()->insert2('UPDATE RECETTE SET STATUT=? WHERE ID=?', [$var,$_POST['id']]);
    }

    /**
     * Update du nombre de convive d'une recette
     * @param $var
     */
    public function upNB_CONV($var){
        $this->get_db()->insert2('UPDATE RECETTE SET NB_CONV=? WHERE ID=?', [$var]);
    }

    /**
     * Update du nom d'une recette
     */
    public function upNOM_RE(){
        $this->get_db()->insert2('UPDATE RECETTE SET NOM_RE=? WHERE ID=?', [$_POST['nom_re'],$_POST['id']]);
    }

    /**
     * Supprime une recette
     */
    public function delete(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM RECETTE WHERE ID=?', $var);
    }

    /**
     * Supprime toute les recettes d'un utilisateur
     */
    public function deleteByUt(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM RECETTE WHERE ID_UT=?', $var);
    }
}