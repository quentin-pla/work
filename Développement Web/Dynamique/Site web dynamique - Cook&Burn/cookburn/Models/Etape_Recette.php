<?php
/**
 * Created by PhpStorm.
 * User: sylva
 * Date: 16/10/2018
 * Time: 11:42
 */

namespace Cookburn\Models;

use Core\Controllers\Model;

class Etape_Recette extends Model
{
    /**
     * Retourne toutes les étapes d'une recette
     * @param $ID
     * @return array|mixed
     */
    public function getById($ID){
        return $this->get_db()->prepare('SELECT * FROM ETAPE_RECETTE WHERE ID = ? GROUP BY NUMERO', [$ID], __CLASS__);
    }

    /**
     * Insertion d'une nouvelle etape
     */
    public function insert(){

        $nbetape = $this->getNbEtape();
        if($nbetape->NB == 0){
            $nbetape = 1;
        }
        else{
            $nbetape = ($nbetape->NB)+1;
        }

        $var=[$_POST['etape'],$_POST['id'],$nbetape];

        $this->get_db()->insert2('INSERT INTO ETAPE_RECETTE (ETAPE,ID,NUMERO) VALUES (?,?,?)', $var);

    }

    /**
     * Update du numéro d'une étape
     * @param $var
     */
    public function updateNumero($var){
        $this->get_db()->insert2('UPDATE ETAPE_RECETTE SET NUMERO = ? WHERE ID=? AND NUMERO = ?', $var);
    }

    /**
     * Retourne le nombre d'étape d'une recette
     * @return array|mixed
     */
    public function getNbEtape(){
        return $this->get_db()->prepare('SELECT MAX(NUMERO) NB FROM ETAPE_RECETTE WHERE ID = ?', [$_POST['id']], __CLASS__,true);
    }

    /**
     * Supprime une etape d'une recette
     */
    public function delete(){
        $var = [$_POST['id'],$_POST['num']];
        $this->get_db()->insert2('DELETE FROM ETAPE_RECETTE WHERE ID=? AND NUMERO = ?', $var);
        $nbetape = $this->getNbEtape();
        if($nbetape->NB != 0) {
            for ($i = $_POST['num']; $i < $nbetape->NB; ++$i) {
                $this->updateNumero([$i, $_POST['id'], $i + 1]);
            }
        }
    }

    /**
     * Supprime toutes les étapes d'une recette
     */
    public function deleteByIdRecette(){
        $var = [$_POST['id']];
        $this->get_db()->insert2('DELETE FROM ETAPE_RECETTE WHERE ID=?', $var);
    }

    /**
     * Swap les numéros de deux étapoes
     */
    public function swapEtape(){
        $nbetape = $this->getNbEtape();
        if($_POST['v1'] != $_POST['v2']) {
            $this->get_db()->insert2('UPDATE ETAPE_RECETTE SET NUMERO = ? WHERE ID=? AND NUMERO = ?', [($nbetape->NB+1), $_POST['id'], $_POST['v1']]);
            $this->get_db()->insert2('UPDATE ETAPE_RECETTE SET NUMERO = ? WHERE ID=? AND NUMERO = ?', [$_POST['v1'], $_POST['id'], $_POST['v2']]);
            $this->get_db()->insert2('UPDATE ETAPE_RECETTE SET NUMERO = ? WHERE ID=? AND NUMERO = ?', [$_POST['v2'], $_POST['id'], ($nbetape->NB+1)]);
        }
    }

    /**
     * Supprime toutes les etapes des recettes d'un utilisateur
     */
    public function deleteByIdUt(){
        $var = [$_POST['id']];
        $recette = $this->get_db()->prepare('SELECT * FROM RECETTE WHERE ID_UT=?', $var, __CLASS__);
        $string = "";
        foreach($recette as $re){
            $string = $string.($re->ID).',';
        }
        if($string != "") {
            $string = substr($string, 0, (strlen($string) - 1));
            $this->get_db()->insert3('DELETE FROM ETAPE_RECETTE WHERE ID IN (' . $string . ')');
        }
    }
}