<?php
namespace Cookburn\Models;
use Core\Controllers\Model;
class Utilisateur extends Model{

    /**
     * Renvoie l'utilisateur actuellment connecté
     * @return array|mixed
     */
    public function getUtCourant(){
        return $this->get_db()->prepare('SELECT * FROM UTILISATEUR WHERE ID_UT = ?',[$_SESSION['id_ut']], __CLASS__, true);
    }

    /**
     * Renvoie tous les utilisateurs
     * @return array
     */
    public function getAll(){
        return $this->get_db()->query('SELECT * FROM UTILISATEUR', __CLASS__);
    }

    /**
     * Supprime un utilisateur
     */
    public function delete(){
        $this->get_db()->insert2('DELETE FROM UTILISATEUR WHERE ID_UT=?',[$_POST['id']]);
    }

    /**
     * Renvoie le mail d'un utilisateur
     * @return array|mixed
     */
    public function getMailUt(){
        return $this->get_db()->prepare('SELECT * FROM UTILISATEUR WHERE MAIL = ?' [$_SESSION['mail']], __CLASS__, true);
    }

    /**
     * @return array|mixed
     */
    public function getUtByMailAndName() {
        return $this->get_db()->prepare('SELECT * FROM UTILISATEUR WHERE NOM_UT = ? AND MAIL = ?', [$_POST['pseudo'],$_POST['mail']],__CLASS__, true);
    }

    /**
     * Update du mot de passe d'un utilisateur
     * @param $mdp
     */
    public function updateMDP($mdp){
        $mdpHash = password_hash($mdp, PASSWORD_BCRYPT);
        $this->get_db()->insert2('UPDATE UTILISATEUR SET MDP = ? WHERE ID_UT = ?', [$mdpHash,$_SESSION['id_ut']]);
    }

    /**
     * Update du mot de passe d'un utilisateur
     * @param $mdp
     * @param $id_ut
     */
    public function updateMDP2($mdp,$id_ut){
        $mdpHash = password_hash($mdp, PASSWORD_BCRYPT);
        $this->get_db()->insert2('UPDATE UTILISATEUR SET MDP = ? WHERE ID_UT = ?', [$mdpHash,$id_ut]);
    }

    /**
     * Update du pseudo d'un utilisateur
     */
    public function updatePseudo(){
        $this->get_db()->insert2('UPDATE UTILISATEUR SET NOM_UT = ? WHERE ID_UT = ?', [$_POST['pseudo'],$_SESSION['id_ut']]);
    }

    /**
     * Update du mail d'un utilisateur
     */
    public function updateMail(){
        $this->get_db()->insert2('UPDATE UTILISATEUR SET MAIL = ? WHERE ID_UT = ?', [$_POST['mail'],$_SESSION['id_ut']]);
    }

    /**
     * Update de la phot d'un utilisateur
     */
    public function updateAvatar(){
        $var = [$_POST['link'],$_POST['id']];
        $this->get_db()->insert2('UPDATE UTILISATEUR SET AVATAR = ? WHERE ID_UT = ?', $var);
    }
}