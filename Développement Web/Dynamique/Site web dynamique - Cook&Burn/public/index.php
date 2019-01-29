<?php
require '../vendor/autoload.php';

use Cookburn\Controllers\ConnexionController;
use Cookburn\Controllers\RecetteController;
use Cookburn\Controllers\ProfilController;

session_start();
if(isset($_GET['p'])){
    $p = $_GET['p'];
} else if(isset($_POST['p'])) {
    $p = $_POST['p'];
}
else {
    $p = 'home';
}

if($p == 'home'){
    $title = 'Recettes';
    $controller = new RecetteController();
    $controller->showAllDesc($title);
} elseif($p === 'search'){
    $title = 'Résultats de la recherche';
    $controller = new RecetteController();
    $controller->showBySearch($title);
} elseif($p === 'recette') {
    $controller = new RecetteController();
    $controller->showRecette();
} elseif($p === 'connexion') {
    $controller = new ConnexionController();
    $connectionfail = $controller->TryConnexion();
    $title = 'Recettes à la une';
    $controller = new RecetteController();
    $controller->showAllDesc($title);
    if($connectionfail == true){
        echo '<script type="text/javascript">alert("Connexion refusé")</script>';
    }
} elseif($p === 'profile'){
    $controller = new ProfilController();
    $controller->showUtCourant();
} elseif($p === 'admin'){
    $controller = new ProfilController();
    $controller->showAdmin();
}
elseif($p === 'creaRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->creationRecette();
}
elseif($p === 'insertEtape'){
    $recette_controller = new RecetteController();
    $recette_controller->insertEtape();
}
elseif($p === 'modifierRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->modifierRecette();
}
elseif($p === 'deleteEtape'){
    $recette_controller = new RecetteController();
    $recette_controller->deleteEtape();
}
elseif($p === 'swapEtape'){
    $recette_controller = new RecetteController();
    $recette_controller->swapEtape();
}
elseif($p === 'addIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->addIngr();
}
elseif($p === 'deleteIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->delIngr();
}
elseif($p === 'chgPhotoRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->upPhoto();
}
elseif($p === 'deleteRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->deleteRecette();
    $title = 'Recettes à la une';
    $recette_controller->showAllDesc($title);
}
elseif ($p === 'deleteUt'){
    $utilisateur = new ProfilController();
    $utilisateur->deleteUt();
}
elseif ($p === 'upDescL'){
    $recette_controller = new RecetteController();
    $recette_controller->upDescL();
}
elseif ($p === 'upDescC'){
    $recette_controller = new RecetteController();
    $recette_controller->upDescC();
}
elseif ($p === 'addDeleteFav') {
    $recette_controller = new RecetteController();
    $recette_controller->addDeleteFavoris();
}
elseif ($p === 'deleteFavFromProfile') {
    $recette_controller = new ProfilController();
    $recette_controller->deleteFavFromProfile();
}
elseif ($p === 'likeDislike') {
    $recette_controller = new RecetteController();
    $recette_controller->likeDislike();
}
elseif ($p === 'chgMDP'){
    $profil_controller = new ProfilController();
    $profil_controller->upMDP();
}
elseif ($p === 'mail'){
    $recup_mail = new ConnexionController();
    $recup_mail->envoieCode();
}
elseif ($p === 'changeMDP'){
    $recup_mdp = new ConnexionController();
    $recup_mdp->changeMDP();
}
elseif ($p === 'chgMail'){
    $profil_controller = new ProfilController();
    $profil_controller->updateMail();
}
elseif ($p === 'affichageChangeMDP'){
    $connexion_controller = new ConnexionController();
    $connexion_controller->affichageChangeMDP();
}
elseif ($p === 'ingredient'){
    $recette_controller = new RecetteController();
    $recette_controller->showIngredient2($_GET['id_ingr']);
}
elseif ($p === 'modIngredient'){
    $recette_controller = new RecetteController();
    $recette_controller->showCreationIngredient();
}
elseif ($p === 'upDescrIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->upDescrIngr();
}
elseif ($p === 'upNomIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->upNomIngr();
}
elseif ($p === 'upPhotoIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->upPhotoIngr();
}
elseif ($p === 'InsertIngr'){
    $recette_controller = new RecetteController();
    $recette_controller->insertIngredient();
}
elseif ($p === 'deleteIngredient'){
    $recette_controller = new RecetteController();
    $recette_controller->deleteIngredient();
}
elseif ($p === 'chgPseudo'){
    $profil_controller = new ProfilController();
    $profil_controller->upPseudo();
}
elseif ($p === 'chgAvatar'){
    $profil_controller = new ProfilController();
    $profil_controller->upAvatar();
}
elseif ($p === 'pagination'){
    $recette_controller = new RecetteController();
    $recette_controller->showAllDesc('Recettes');
}
elseif ($p === 'upStatutRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->chgSTATUT();
}
elseif ($p === 'upNbConvive'){
    $recette_controller = new RecetteController();
    $recette_controller->upNbConv();

}
elseif ($p === 'upNpmRecette'){
    $recette_controller = new RecetteController();
    $recette_controller->upNomRecette();
}
elseif ($p === 'majPas'){
    if (is_numeric($_POST['pas']) && $_POST['pas'] > 0) {
        $nouveauPas = $_POST['pas'];
        $file = 'pas';

        unlink($file);
        file_put_contents($file,$nouveauPas);
    }
    $profil_controller = new ProfilController();
    $profil_controller->showAdmin();
}
elseif ($p === 'connexion2'){
    $connexion_controller = new ConnexionController();
    $connexion_controller->TryConnexion2();
}