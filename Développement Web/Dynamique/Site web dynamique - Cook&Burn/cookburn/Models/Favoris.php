<?php
namespace Cookburn\Models;
use Core\Controllers\Model;

class Favoris extends Model {
    /**
     * Retourne tous les favoris d'un utilisateur
     * @return array|mixed
     */
    public function getByUtCourant() {
        return $this->get_db()->prepare('SELECT * FROM FAVORI WHERE ID_UT = ?', [$_SESSION['id_ut']], __CLASS__);
    }

    /**
     * Verifie si l'utilisateur a une recette en favoris
     * @return array|mixed
     */
    public function verifierFavorisUt(){
        return $this->get_db()->prepare('SELECT * FROM FAVORI  WHERE ID_UT = ? AND ID=?', [$_SESSION['id_ut'],$_GET['ID']], __CLASS__,true);

    }

    /**
     * Verifie si l'utilisateur a une recette en favoris
     * @return array|mixed
     */
    public function verifierFavorisUt2(){
        return $this->get_db()->prepare('SELECT * FROM FAVORI  WHERE ID_UT = ? AND ID=?', [$_SESSION['id_ut'],$_POST['id']], __CLASS__,true);

    }

    /**
     * Ajout d'un favoris
     */
    public function add(){
        $this->get_db()->insert2('INSERT INTO FAVORI (ID_UT,ID) VALUES (?,?)',[$_SESSION['id_ut'],$_POST['id']]);
    }

    /**
     * Suppression d'un favoris
     */
    public function delete(){
        $this->get_db()->insert2('DELETE FROM FAVORI WHERE ID_UT = ? AND ID=?',[$_SESSION['id_ut'],$_POST['id']]);
    }

    /**
     * Suppression de tous les favoris d'un utilisateur
     */
    public function deleteByUt(){
        $var=[$_POST['id']];
        $this->get_db()->insert2('DELETE FROM FAVORI WHERE ID_UT = ?',$var);

        $recette = $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID_UT=?', $var, __CLASS__);
        $string = "";
        foreach($recette as $re){
            $string = $string.($re->ID).',';
        }
        if($string != "") {
            $string = substr($string, 0, (strlen($string) - 1));
            $this->get_db()->insert3('DELETE FROM FAVORI WHERE ID IN (' . $string . ')');
        }
    }

    /**
     * Suppression de tous les favoris correspondant à une recette
     */
    public function deleteByIdRecette(){
        $var=[$_POST['id']];
        $this->get_db()->insert2('DELETE FROM FAVORI WHERE ID = ?',$var);
    }
}