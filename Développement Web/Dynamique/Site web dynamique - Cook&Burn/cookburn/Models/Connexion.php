<?php
namespace Cookburn\Models;

use Core\Controllers\Model;

class Connexion extends Model
{
    /**
     * Retourne un utilisateur en fonction d'un nom
     * @return array|mixed
     */
    public function tryConnexion(){
        $L = [$_POST['login']];
        return $this->get_db()->prepare('SELECT * FROM UTILISATEUR WHERE NOM_UT = ?', $L, __CLASS__,true);
    }

    /**
     * @return array|mixed
     */
    public function tryAdmin(){
        $L = [$_POST['login']];
        return $this->get_db()->prepare('SELECT * FROM UTILISATEUR WHERE  STATUT = \'A\' AND NOM_UT = ?', $L, __CLASS__);
    }
}