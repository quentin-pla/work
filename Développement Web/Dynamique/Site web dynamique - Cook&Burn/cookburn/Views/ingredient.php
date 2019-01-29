<?php
include 'modal/modalEditIngredient/modalUpDescrIngr.php';
include 'modal/modalEditIngredient/modalUpNomIngr.php';
include 'modal/modalEditIngredient/modalDeleteIngredient.php';
include 'modal/modalEditIngredient/modalUpPhotoIngr.php';
?>

<div class="container p-0">

<div class="row justify-content-center align-items-center bg-grey">

    <div class="col-sm-3 p-0">
        <img class="img w-100" id="avatar" src="<?= $ingredient[0]->URL_IMAGE; ?>" alt="recette" />
    </div>

    <div id="infosRecette" class="col-sm-8">
        <h3><?= $ingredient[0]->NOM_INGR; ?></h3>
        <h5><?= $ingredient[0]->NOM_UT; ?></h5>
        <h4 class="dropdown-toggle pointer" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            Description
        </h4>
        <div class="collapse" id="collapseExample">
            <div class="pl-3 mb-2">
                <p><?= $ingredient[0]->DESCR_INGR; ?></p>
            </div>
        </div>
    </div>

    <div class="col-sm-1 p-0 text-center">
        <?php
        if($_SESSION['login'] == 'oui') {
            if ($_SESSION['id_ut'] == $ingredient[0]->ID_UT) {
                echo '<form id="getSettings" action="index.php" method="post">
                            <input type="hidden" name="p" value="modIngredient">
                            <input type="hidden" name="id_ingr" value=' . $ingredient[0]->ID_INGR . '>
                            <a class="pointer" onclick="document.getElementById(\'getSettings\').submit()">
                                <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#c6c6c6">settings</i>
                            </a>
                      </form>';
            }
        }
        ?>
    </div>
</div>