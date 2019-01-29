<?php
namespace Cookburn\Models;
use Core\Controllers\Model;

class Recuperation extends Model{

    /**
     * Insertion d'un code de récupération dans la BD
     * @param $id_ut
     * @param $code
     */
    public function insertCodeAndUt($id_ut, $code){
        $this->get_db()->insert2('INSERT INTO RECUPERATION(ID_UT,CODE) VALUES (?, ?)', [$id_ut,$code]);
    }

    /**
     * Suppression d'un code de récupération dans la BD
     * @param $id_ut
     * @param $code
     */
    public function deleteCodeAndUt($id_ut, $code){
        $this->get_db()->insert2('DELETE FROM RECUPERATION WHERE ID_UT = ? AND CODE = ?', [$id_ut,$code]);
    }

    /**
     * Renvoie le code de récupération d'un utilisateur
     * @param $id_ut
     * @return array|mixed
     */
    public function getByUt($id_ut){
        return $this->get_db()->prepare('SELECT * FROM RECUPERATION WHERE ID_UT = ?',[$id_ut],__CLASS__, true);
    }

    /**
     * Supprime le coded de récupération d'un utilisateur
     * @param $id_ut
     */
    public function deleteByUt($id_ut){
        $this->get_db()->insert2('DELETE FROM RECUPERATION WHERE ID_UT = ?', [$id_ut]);
    }

    /**
     * Renvoie le code de récupération d'un utilisateur
     * @return array|mixed
     */
    public function getCode(){
        return $this->get_db()->prepare('SELECT CODE FROM RECUPERATION WHERE ID_UT = ?', [$_POST['pseudo']], __CLASS__, true);
    }

    /**
     * Renvoie le code de récupération d'un utilisateur
     * @return array|mixed
     */
    public function getCodeByUt(){
        return $this->get_db()->prepare('SELECT CODE FROM RECUPERATION WHERE ID_UT = ?', [$_POST['id_ut']], __CLASS__, true);
    }

    /**
     * Renvoie un code de récupération en fonction d'un code et d'un id_ut
     * @return array|mixed
     */
    public function getCodeByUtAndCode(){
        return $this->get_db()->prepare('SELECT CODE FROM RECUPERATION WHERE ID_UT = ? AND CODE = ?', [$_GET['id_ut'],$_GET['code']], __CLASS__, true);
    }

    /**
     * Renvoie un code de récupération en fonction d'un code et d'un id_ut
     * @return array|mixed
     */
    public function getCodeByUtAndCode2(){
        return $this->get_db()->prepare('SELECT CODE FROM RECUPERATION WHERE ID_UT = ? AND CODE = ?', [$_POST['id_ut'],$_POST['code']], __CLASS__, true);
    }
}