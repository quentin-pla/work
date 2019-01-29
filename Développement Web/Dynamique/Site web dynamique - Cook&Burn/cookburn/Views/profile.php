<?php

include 'modal/modalCreationRecette/modalCreationRecette.php';
include 'modal/modalChangementProfil/modalAjoutPhotoProfil.php';
include 'modal/modalChangementProfil/modalChangementMotDePasse.php';
include 'modal/modalChangementProfil/modalChangementPseudo.php';
include 'modal/modalChangementProfil/modalChangementMail.php';
include 'modal/modalEditIngredient/modalInsertIngredient.php';

if ($_SESSION['login'] != 'oui') {
    header('Location:/public/index.php');
}

?>
<script src="../public/javascript/buttonSwitch.js"></script>

<div class="container p-0">

    <div class="row justify-content-center align-items-center bg-grey">

        <div class="col-sm-2 p-0">
            <img class="img w-100" id="avatar" src="<?= $utilisateur->AVATAR; ?>" alt="avatar" />
        </div>

        <div id="infosUser" class="col-sm-9">
            <h3 id="listProfileTitle"><?= $utilisateur->NOM_UT; ?></h3>
            <h4><?= $utilisateur->MAIL; ?></h4>
        </div>

        <div class="col-sm-1 p-0 text-center">
            <div class="dropdown mb-3">
                <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff5e14">add</i>
                <div class="dropdown-menu dropdown-menu-right bg-lightgrey p-0">
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalCreationRecette">Recette</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalInsertIngr">Ingrédient</a>
                </div>
            </div>

            <div class="dropdown">
                <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff5e14">edit</i>
                <div class="dropdown-menu dropdown-menu-right bg-lightgrey p-0">
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalChgPseudo">Pseudo</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalChgMDP">Mot de passe</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalChgMail">Mail</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutPhotoProfil">Photo de profil</a>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div id="profilButtons" class="col-sm-2">

                <div id="btnS1" class="btnSwitch btnActifProfil row justify-content-center align-items-center py-2" onclick="switchButton('btnS1','maListeFav')">
                    <div class="col text-center">
                        <i class="material-icons d-sm-block d-none" style="font-size:70px;color:white;">star</i>
                        <h4>Favoris</h4>
                    </div>
                </div>

                <div id="btnS2" class="btnSwitch row justify-content-center align-items-center py-2" onclick="switchButton('btnS2','maListeRec')">
                    <div class="col text-center">
                        <i class="material-icons d-sm-block d-none" style="font-size:70px;color:white;">insert_drive_file</i>
                        <h4>Recettes</h4>
                    </div>
                </div>

                <div id="btnS3" class="btnSwitch row justify-content-center align-items-center py-2" onclick="switchButton('btnS3','maListeIngr')">
                    <div class="col text-center">
                        <i class="material-icons d-sm-block d-none" style="font-size:70px;color:white;">local_offer</i>
                        <h4>Ingrédients</h4>
                    </div>
                </div>

        </div>

        <div class="col-sm-10 p-0" style="background: #444444">

            <div class="listProfile listProfileActive" id="maListeFav">
                <?php foreach ($recettesFavs as $fav){
                    echo '
                        <div class="recetteProfil col-xs-12 col-sm-6 col-md-3 col-lg-2">
                            <img src="' . $fav->URL_IMAGE . '">
                            <div class="textImage">
                                <h3>' . $fav->NOM_RE . '</h3>
                                <p>'. $fav->DESC_C . '</p>
                            </div>
                            <a href="index.php?p=recette&ID='. $fav->ID . '"><div style="position:absolute; top:0; height: 100%; width: 100%;"></div></a>
                        </div>
                    ';
                } ?>
            </div>
            <div class="listProfile" id="maListeRec">
                <?php foreach ($recettes as $recette){
                    echo '
                        <div class="recetteProfil col-xs-12 col-sm-5 col-md-2 col-lg-2">
                            <img src="' . $recette->URL_IMAGE . '">
                            <div class="textImage">
                                <h3>' . $recette->NOM_RE . '</h3>
                                <p>'. $recette->DESC_C . '</p>
                            </div>
                            <a href="index.php?p=recette&ID='. $recette->ID . '"><div style="position:absolute; top:0; height: 100%; width: 100%;"></div></a>
                        </div>
                    ';
                } ?>
            </div>
            <div class="listProfile" id="maListeIngr">
                <?php foreach ($ingredients as $ingr){
                    echo '
                        <div class="recetteProfil col-xs-12 col-sm-5 col-md-2 col-lg-2">
                            <img src="' . $ingr->URL_IMAGE . '">
                            <div class="textImage">
                                <h3>' . $ingr->NOM_INGR . '</h3>
                                <p>'. $ingr->DESCR_INGR . '</p>
                            </div>
                            <a href="index.php?p=ingredient&id_ingr='. $ingr->ID_INGR . '"><div style="position:absolute; top:0; height: 100%; width: 100%;"></div></a>
                        </div>
                    ';
                } ?>
            </div>
        </div>
    </div>
</div>