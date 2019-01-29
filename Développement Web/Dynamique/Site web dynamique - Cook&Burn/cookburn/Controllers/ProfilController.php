<?php

namespace Cookburn\Controllers;

use Cookburn\Models\Burn;
use Cookburn\Models\Etape_Recette;
use Cookburn\Models\Favoris;
use Cookburn\Models\Ingredient_Recette;
use Cookburn\Models\Recettes;
use Cookburn\Models\Utilisateur;
use Core\Controllers\Controller;
use Cookburn\Models\Ingredient;

class ProfilController extends Controller {

    /**
     * Affiche le profil de l'utilisateur
     */
    public function showUtCourant()
    {
        if ($_SESSION['login'] != null) {

            $utilisateur = new Utilisateur();
            $utilisateur = $utilisateur->getUtCourant();

            $favoris = new Favoris();
            $favoris = $favoris->getByUtCourant();

            $recettesFavs = [];
            foreach ($favoris as $fav) {
                $recetteFav = new Recettes();
                $recetteFav = $recetteFav->getById2($fav->ID);
                array_push($recettesFavs, $recetteFav);
            }
            $ingredients = new Ingredient();
            $ingredients = $ingredients->getByUtCourant();

            $recettes = new Recettes();
            $recettes = $recettes->getByUtCourant();
            $this->render('default', 'profile', 'Profil', compact('utilisateur', 'recettesFavs', 'recettes', 'ingredients'));
        }
        else{
            $recette_controller = new RecetteController();
            $recette_controller->showAllDesc('Recettes à la une');
        }
    }

    /**
     * Affiche le panneau admin
     */
    public function showAdmin(){
        $utilisateurs = new Utilisateur();
        $utilisateurs = $utilisateurs->getAll();

        $recettes = new Recettes();
        $recettes = $recettes->getAll();

        $ingredients = new Ingredient();
        $ingredients = $ingredients->getAll();

        $this->render('default','admin','Panneau d\'administration', compact('utilisateurs','ingredients','recettes'));

    }

    /**
     * Supprime un utilisateur
     */
    public function deleteUt(){
        $ingredient_recette = new Ingredient_Recette();
        $ingredient_recette->delIngrByIdUt();

        $etape_recette = new Etape_Recette();
        $etape_recette->deleteByIdUt();

        $burn = new Burn();
        $burn->deleteByIdUt();

        $recette = new Favoris();
        $recette->deleteByUt();

        $recette = new Recettes();
        $recette->deleteByUt();

        $ut = new Utilisateur();
        $ut->delete();

        $this->showAdmin();
    }
    public function __get($key)
    {
        $method = 'get'.strtoupper($key);
        $this->$key = $this->$method();
        return $this->$key;
    }

    /**
     * Supprime un favoris de l'utilisateur
     */
    public function  deleteFavFromProfile(){
        $favoris = new Favoris();
        $favoris->delete();
        $this->showUtCourant();
    }

    /**
     * Changer le mot de passe de l'utilisateur
     */
    public function upMDP(){
        $utilisateur = new Utilisateur();
        $utilisateur = $utilisateur->getUtCourant();
        if (password_verify($_POST['oldPWD'], $utilisateur->MDP)) {
            if($_POST['newPWD1'] == $_POST['newPWD2']){
                $utilisateur = new Utilisateur();
                $utilisateur->updateMDP($_POST['newPWD1']);
                $this->showUtCourant();
                echo '<script type="text/javascript">alert("Mot de passe changé !!")</script>';

            }
            else{
                $this->showUtCourant();
                echo '<script type="text/javascript">alert("Le mot de passe de confirmation n\'est pas le même que celui rentré !")</script>';

            }
        }
        else{
            $this->showUtCourant();
            echo '<script type="text/javascript">alert("L\'ancien mot de passe ne correspond pas.")</script>';
        }
    }

    /**
     * Changer le pseudo de l'utilisateur
     */
    public function upPseudo(){
        $utilisateur = new Utilisateur();
        $utilisateur->updatePseudo();
        $this->showUtCourant();
    }

    /**
     *  changer le mail de l'utilisateur
     */
    public function updateMail(){
        if(filter_var($_POST['mail'], FILTER_VALIDATE_EMAIL)){
            $utilisateur = new Utilisateur();
            $utilisateur->updateMail();
            $this->showUtCourant();
        }else{
            $this->showUtCourant();
            echo '<script type="text/javascript">alert("Email invalide")</script>';
        }
    }

    /**
     * Changer la photo de profil de l'utilisateur
     */
    public function upAvatar(){
        $utilisateur = new Utilisateur();
        $utilisateur->updateAvatar();
        $this->showUtCourant();
    }
}