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
            echo '<form class="mb-3" id="validSettings" action="index.php" method="get">
                        <input type="hidden" name="p" value="ingredient">
                        <input type="hidden" name="id_ingr" value=' . $ingredient[0]->ID_INGR . '>
                        <a class="pointer" onclick="document.getElementById(\'validSettings\').submit()">
                            <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#28e257">check</i>
                        </a>
                  </form>';
                    ?>
                    <div class="dropdown">
                        <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff5e14">edit</i>
                        <div class="dropdown-menu dropdown-menu-right bg-lightgrey p-0">
                            <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpNomIngr">Nom</a>
                            <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpDescrIngr">Description</a>
                            <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpPhotoIngr">Photo</a>
                            <a class="dropdown-item text-danger" data-toggle="modal" data-target="#myModalDeleteIngredient">Supprimer</a>
                        </div>
                    </div>
        </div>
    </div>