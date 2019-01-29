<?php

namespace Cookburn\Controllers;

use Cookburn\Models\Favoris;
use Core\Controllers\Controller;
use Cookburn\Models\Ingredient;
use Cookburn\Models\Ingredient_Recette;
use Cookburn\Models\Etape_Recette;
use Cookburn\Models\Recettes;
use Cookburn\Models\Burn;
use function Sodium\add;

class RecetteController extends Controller{

    public function __get($key)
    {
        $method = 'get'.strtoupper($key);
        $this->$key = $this->$method();
        return $this->$key;
    }

    /**
     * Affiche toute les recettes
     * @param $title
     */
    public function showAll($title){
        $recette = new Recettes();
        $recettes = $recette->getAll();

        $this->render('default','home',$title,compact('recettes','pageNumber'));
    }

    /**
     * Affiche toute les recettes du menu principal
     * @param $title
     */
    public function showAllDesc($title){


        if (file_get_contents('pas') == null) $pas = 2;
        else $pas = file_get_contents('pas');

        $recette = new Recettes();
        $recettes = $recette->getAllDesc();
        $recetteTOP = $recettes[0];
        $totalNumber = count($recettes);
        $pageNumber = ceil($totalNumber/$pas);

        $pageType = 'defaultCarousel';
        if ( !isset($_GET['currentPage']) || $_GET['currentPage'] > $pageNumber || $_GET['currentPage'] <= 0 || !is_numeric($_GET['currentPage'])) {
            $currentPage = 1;
            $pagined = false;
        }
        else {
            $currentPage = $_GET['currentPage'];
            $pageType = 'defaultCarousel';
            $pagined = true;
        }

        $i=0;
        $recettesAAfficher = [];
        while($i < $pas && ($currentPage - 1) * $pas + $i < $totalNumber) {
            $id = $recettes[($currentPage - 1) * $pas + $i]->ID;
            array_push($recettesAAfficher, $recette->getRecetteAndBurnByParamId($id));
            ++$i;
        }
        $recettes = $recettesAAfficher;

        $this->render($pageType,'home',$title,compact('recetteTOP','recettes','pageNumber','currentPage','pagined'));
    }

    /**
     * Affichage des résultat de la recherche de l'utilisateur
     * @param $title
     */
    public function showBySearch($title){
        $recette = new Recettes();
        if($_SESSION['login'] == 'oui') $recettes = $recette->getLikeKeyword();
        else $recettes = $recette->getLikeKeyword2();


        $this->render('default','search',$title,compact('recettes','pageNumber','currentPage'));
    }

    /**
     * @return string
     */
    public function getURL(){
        return 'index.php?p=recette&ID='.$this->ID;
    }

    /**
     * Affiche le contenue d'une recette
     */
    public function showRecette(){
        $recette = new Recettes();
        $recette = $recette->getById();
        if($recette->ID == null){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            if (!isset($_SESSION['login'])||$_SESSION['login'] == 'non' && $recette->STATUT != 2) {
                $connecion_controller = new ConnexionController();
                $connecion_controller->affichageConnexionExterieur($recette->ID);
            }
            else {
                $title = "Recette";
                $etape = new Etape_Recette();
                $etapes = $etape->getById($recette->ID);

                $favorite = new Favoris();
                if(isset($_SESSION['id_ut'])) {
                    $favorite = $favorite->verifierFavorisUt();
                }
                else{
                    $favorite = null;
                }
                $isFavorite = false;
                if ($favorite != null) {
                    $isFavorite = true;
                }

                $like = new Burn();
                if(isset($_SESSION['id_ut'])) {
                    $like = $like->verifierLikeUt($recette->ID);
                }
                else{
                    $like = false;
                }
                $isLiked = false;
                if ($like) {
                    $isLiked = true;
                }
                $ingredient_recette = new Ingredient_Recette();
                $ingredient_recette = $ingredient_recette->getByID($recette->ID);

                $recette = [$recette, $etapes, $ingredient_recette];
                $this->render('default', 'recette', $title, compact('recette', 'isFavorite', 'isLiked'));
            }
        }
    }

    /**
     * Insertion d'une nouvelle recette dans la BD
     */
    public function creationRecette(){
        if($_POST['nom_re'] != null && $_SESSION['id_ut'] != null) {
            $recette = new Recettes();
            $ID = $recette->insert();
            $recette = $recette->getById2($ID);
            $title = $recette->NOM_RE;
            $etape = new Etape_Recette();
            $etapes = $etape->getById($recette->ID);
            $ingredient_recette = new Ingredient_Recette();
            $ingredient_recette = $ingredient_recette->getByID($recette->ID);
            $ingredient = new Ingredient();
            $ingredient = $ingredient->getAll();
            $recette = [$recette, $etapes, $ingredient_recette, $ingredient];
            $this->render('default', 'recette', $title, compact('recette'));
        }
        else{
            $profil_controller = new ProfilController();
            $profil_controller->showUtCourant();
            echo '<script type="text/javascript">alert("Nom de la Recette vide !!!")</script>';
        }
    }

    /**
     * Affiche la page de modification de recette
     */
    public function modifierRecette(){
        $recette = new Recettes();
        $recette = $recette->getById2($_POST['id']);
        if($recette->ID == null){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            if($this->verifIdUt($_POST['id'])) {

                $title = "Recette";
                $etape = new Etape_Recette();
                $etapes = $etape->getById($recette->ID);
                $ingredient_recette = new Ingredient_Recette();
                $ingredient_recette = $ingredient_recette->getByID($recette->ID);
                $ingredient = new Ingredient();
                $ingredient = $ingredient->getAllSaufCeuxDansRecette($recette->ID);
                $recette = [$recette, $etapes, $ingredient_recette, $ingredient];
                $this->render('default', 'creationRecette', $title, compact('recette'));
            }
        }
    }

    /**
     * Ajoute une etape a une recette
     */
    public function insertEtape(){
        if($this->verifIdUt($_POST['id'])) {

            $etape = new Etape_Recette();
            $etape->insert();

            $this->modifierRecette();
        }
    }

    /**
     * Supprime une etape d'une recette
     */
    public function deleteEtape(){
        if($this->verifIdUt($_POST['id'])) {
            $etape = new Etape_Recette();
            $etape->delete();
            $this->modifierRecette();
        }
    }

    /**
     * Change la position de deux etapes
     */
    public function swapEtape(){
        if($this->verifIdUt($_POST['id'])) {
            $etape = new Etape_Recette();
            $etape->swapEtape();
            $this->modifierRecette();
        }
    }

    /**
     * Ajoute un ingrédient à une recette
     */
    public function addIngr(){
        if($this->verifIdUt($_POST['id'])) {
            $ingredient = new Ingredient();
            $ingredient = $ingredient->getByName2();
            $ingredient_recette = new Ingredient_Recette();
            $ingredient_recette->addIngr($ingredient->ID_INGR);
            $this->modifierRecette();
        }
    }

    /**
     * Supprime un ingrédient d'une recette
     */
    public function delIngr(){
        if($this->verifIdUt($_POST['id'])) {
            $ingredient_recette = new Ingredient_Recette();
            $ingredient_recette->delIngr();
            $this->modifierRecette();
        }
    }

    /**
     * Supprime une recette
     */
    public function deleteRecette(){
        if($this->verifIdUt($_POST['id'])) {
            $ingredient_recette = new Ingredient_Recette();
            $ingredient_recette->delIngrByIdRecette();
            $etape_recette = new Etape_Recette();
            $etape_recette->deleteByIdRecette();
            $burn = new Burn();
            $burn->deleteByIdRecette();
            $fav = new Favoris();
            $fav->deleteByIdRecette();
            $recette = new Recettes();
            $recette->delete();
        }
        else{
            $this->showAllDesc('Recettes à la une');
        }
    }

    /**
     * Update de la description longue d'une recette
     */
    public function upDescL(){
        if($this->verifIdUt($_POST['id'])) {
            $recette = new Recettes();
            $recette->upDESC_L();
            $this->modifierRecette();
        }
    }

    /**
     * Update de la description courte de la recette
     */
    public function upDescC(){
        if($this->verifIdUt($_POST['id'])) {
            $recette = new Recettes();
            $recette->upDESC_C();
            $this->modifierRecette();
        }
    }

    /**
     * Ajout d'une recette en favoris
     */
    public function addFav(){
        $favoris = new Favoris();
        $favoris->add();
        $this->showRecette2();
    }

    /**
     * Affichage du contenue d'une recette
     */
    public function showRecette2(){
        $recette = new Recettes();
        $recette = $recette->getById2($_POST['id']);
        if($recette->ID == null){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            if (!isset($_SESSION['login']) || $_SESSION['login'] == 'non' && $recette->STATUT != 2) {
                $connecion_controller = new ConnexionController();
                $connecion_controller->affichageConnexionExterieur($recette->ID);
            } else {
                $title = 'Recette';
                $etape = new Etape_Recette();
                $etapes = $etape->getById($recette->ID);

                $favorite = new Favoris();
                $favorite = $favorite->verifierFavorisUt2();
                $isFavorite = false;
                if ($favorite != null) {
                    $isFavorite = true;
                }

                $like = new Burn();
                $like = $like->verifierLikeUt($_POST['id']);
                if ($like != null) {
                    $isLiked = true;
                } else {
                    $isLiked = false;
                }
                $ingredient_recette = new Ingredient_Recette();
                $ingredient_recette = $ingredient_recette->getByID($recette->ID);

                $recette = [$recette, $etapes, $ingredient_recette];
                $this->render('default', 'recette', $title, compact('recette', 'isFavorite', 'isLiked'));
            }
        }
    }

    /**
     * Suppression d'un favoris
     */
    public function  deleteFavFromRecette(){
        $favoris = new Favoris();
        $favoris->delete();
        $this->showRecette2();
    }

    /**
     * Ajout/Suppression d'un favoris
     */
    public function addDeleteFavoris(){
        if($_SESSION['login'] == 'oui') {
            $favorite = new Favoris();
            $favorite = $favorite->verifierFavorisUt2();
            if ($favorite != null) {
                $this->deleteFavFromRecette();
            } else {
                $this->addFav();
            }
        }
        else{
            $this->showAllDesc('Recettes à la une');
        }
    }

    /**
     * Like/Dislike d'une recette
     */
    public function likeDislike(){
        if($_SESSION['login'] == 'non'){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            $verLike = new Burn();
            $like = new Burn();
            $verLike = $verLike->verifierLikeUt($_POST['id']);
            if ($verLike != null) {
                $like->dislike();

            } else {
                $like->like();
            }
            $nb_burn = new Burn();
            $nb_burn = $nb_burn->getNumber($_POST['id']);
            $recette = new Recettes();
            if ($nb_burn[0]->value >= 10) {
                $recette->upSTATUT(2);
            } else {
                $recette->upSTATUT(1);
            }
            $this->showRecette2();
        }
    }

    /**
     * Affichage d'un ingrédient
     */
    public function showIngredient(){
        $ingredient = new Ingredient();
        $ingredient = $ingredient->getById();
        if($ingredient->ID_INGR == null){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            $title = "Ingrédient";
            $this->render('default', 'ingredient', $title, compact('ingredient'));
        }
    }

    /**
     * Affichage d'un ingrédient
     * @param $ID
     */
    public function showIngredient2($ID){
        $ingredient = new Ingredient();
        $ingredient = [$ingredient->getById2($ID)];
        if($ingredient[0]->ID_INGR == null){
            $this->showAllDesc('Recettes à la une');
        }
        else {
            $title = "Ingrédient";
            $this->render('default', 'ingredient', $title, compact('ingredient'));
        }
    }

    /**
     * Affichage de la page de modification d'un ingrédient
     */
    public function showCreationIngredient(){
        if($this->verifIdUtIngr($_POST['id_ingr'])) {
            $ingredient = new Ingredient();
            $ingredient = [$ingredient->getById()];
            $title = "Ingrédient";
            $this->render('default', 'creationIngredient', $title, compact('ingredient'));
        }
    }

    /**
     * Affichage de la page de modification d'un ingrédient
     * @param $ID
     */
    public function showCreationIngredient2($ID){
        if($this->verifIdUtIngr($ID)) {
            $ingredient = new Ingredient();
            $ingredient = $ingredient->getById2($ID);
            $title = "Ingrédient";
            $this->render('default', 'creationIngredient', $title, compact('ingredient'));
        }
    }

    /**
     * Update de la description d'un ingrédient
     */
    public function upDescrIngr(){
        if($this->verifIdUtIngr($_POST['id_ingr'])) {

            $ingredient = new Ingredient();
            $ingredient->upDESCR_INGR();
            $this->showCreationIngredient();
        }
    }

    /**
     * Update du nom d'un ingrédient
     */
    public function upNomIngr(){
        if($this->verifIdUtIngr($_POST['id_ingr'])) {

            $ingredient = new Ingredient();
            $ingredient->upNOM_INGR();
            $this->showCreationIngredient();
        }
    }

    /**
     * Update de la photo d'un ingrédient
     */
    public function upPhotoIngr(){
        if($this->verifIdUtIngr($_POST['id'])) {

            $ingredient = new Ingredient();
            $ingredient->upPHOTO_INGR();
            $this->showCreationIngredient2($_POST['id']);
        }
    }

    /**
     * Insertion d'un nouvel ingrédient
     */
    public function insertIngredient(){
        if($_POST['nom_ingr'] != null) {
            if($_SESSION['login'] == 'oui') {
                $ingredient = new Ingredient();
                $ID = $ingredient->insertIngredient();
                $this->showCreationIngredient2($ID);
            }
            else{
                $this->showAllDesc('Recettes à la une');
            }
        }
        else{
            $profil_controller = new ProfilController();
            $profil_controller->showUtCourant();
            echo '<script type="text/javascript">alert("Nom de l\'Ingrédient vide !!!")</script>';
        }
    }

    /**
     * Suppression d'un ingrédient
     */
    public function deleteIngredient(){
        if($this->verifIdUtIngr($_POST['id_ingr'])) {
            $ingredient_recette = new Ingredient_Recette();
            $ingredient_recette->delIngrByIdIngr();
            $ingredient = new Ingredient();
            $ingredient->delete();
            $profil_controller = new ProfilController();
            $profil_controller->showUtCourant();
        }
    }

    /**
     * Update de la photo d'une recette
     */
    public function upPhoto(){
        if($this->verifIdUt($_POST['id'])){
            $recette = new Recettes();
            $recette->upURL_IMAGE();
            $this->modifierRecette();
        }
    }

    /**
     * Update du statut d'une recette
     */
    public function chgSTATUT(){
        if($this->verifIdUt($_POST['id'])) {
            $recette = new Recettes();
            if ($_POST['statut'] == 0) {
                $nb_burn = new Burn();
                $nb_burn = $nb_burn->getNumber($_POST['id']);
                if ($nb_burn[0]->value >= 10) {
                    $recette->upSTATUT(2);
                } else {
                    $recette->upSTATUT(1);
                }
            } else {
                $recette->upSTATUT(0);
            }

            $this->modifierRecette();
        }
    }

    /**
     * Update du nombre de convive d'une recette
     */
    public function upNbConv(){
        if($this->verifIdUt($_POST['id'])) {
            $recette = new Recettes();
            $recette->upNbConv();
            $this->modifierRecette();
        }
    }

    /**
     * Update du nom d'une recette
     */
    public function upNomRecette(){
        if($this->verifIdUt($_POST['id'])) {
            $recette = new Recettes();
            $recette->upNOM_RE();
            $this->modifierRecette();
        }
    }

    /**
     * Verification que l'utilisateur est bien le propriétaire de la recette ou su'il est admin afin de le modifier
     * @param $id
     * @return bool
     */
    public function verifIdUt($id){
        $recette = new Recettes();
        $recette =$recette->getById2($id);
        if($recette->ID_UT != $_SESSION['id_ut'] && $_SESSION['admin'] != 'oui'){
            $this->showAllDesc('Recettes à la une');
            return false;
        }
        return true;
    }

    /**
     * Verification que l'utilisateur est bien le propriétaire de l'ingrédient ou su'il est admin afin de le modifier
     * @param $id
     * @return bool
     */
    public  function verifIdUtIngr($id_ingr){
        $ingredient = new Ingredient();
        $ingredient = $ingredient->getById2($id_ingr);
        if($ingredient->ID_UT != $_SESSION['id_ut'] && $_SESSION['admin'] != 'oui'){
            $this->showAllDesc('Recettes à la une');
            return false;
        }

        return true;
    }
}