<?php
namespace Cookburn\Models;

use Core\Controllers\Model;

class Burn extends Model{

    /**
     * Récupération du nombre de like d'une recette
     * @param $ID
     * @return array
     */
    public function getNumber($ID){
        return $this->get_db()->query('SELECT COUNT(*) AS value FROM BURN WHERE ID = '.'\''.$ID.'\'', __CLASS__);
    }

    /**
     * Retourne tous les tuples de la table BURN
     * @return array
     */
    public function getAll(){
        return $this->get_db()->query('SELECT * FROM BURN', __CLASS__);
    }

    /**
     * Supprime tous les burn d'une recette
     */
    public function deleteByIdRecette(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM BURN WHERE ID=?', $var);
    }

    /**
     * Supprime tous les burn des recettes d'un utilisateur, Supprime tous les burns d'un utilisateur
     */
    public function deleteByIdUt(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM BURN WHERE ID_UT=?', $var);
        $recette = $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID_UT=?', $var, __CLASS__);
        $string = "";
        foreach($recette as $re){
            $string = $string.($re->ID).',';
        }
        if($string != "") {
            $string = substr($string, 0, (strlen($string) - 1));
            $this->get_db()->insert3('DELETE FROM BURN WHERE ID IN (' . $string . ')');
        }
    }

    /**
     * Ajoute un burn à une recette
     */
    public function like(){
        $this->get_db()->insert2('INSERT INTO BURN (ID_UT,ID) VALUES (?,?) ', [$_SESSION['id_ut'],$_POST['id']]);
    }

    /**
     * retire un burn à une recette
     */
    public function dislike(){
        $this->get_db()->insert2('DELETE FROM BURN WHERE ID_UT=? AND ID=?', [$_SESSION['id_ut'],$_POST['id']]);
    }

    /**
     * Verifie si l'utilisateur a déja like la recette ou non
     * @param $ID
     * @return array|mixed
     */
    public function verifierLikeUt($ID){
        return $this->get_db()->prepare('SELECT * FROM BURN WHERE ID_UT =? AND ID=?', [$_SESSION['id_ut'], $ID], __CLASS__, true);
    }
}
