<?php
include 'modal/modalCreationRecette/modalAjoutEtape.php';
include 'modal/modalCreationRecette/modalSwapEtape.php';
include 'modal/modalCreationRecette/modalAjoutIngredient.php';
include 'modal/modalCreationRecette/modalDeleteRecette.php';
include 'modal/modalCreationRecette/modalAjoutDescC.php';
include 'modal/modalCreationRecette/modalAjoutDescL.php';
include 'modal/modalCreationRecette/modalUpStatutRecette.php';
include 'modal/modalCreationRecette/modalAjoutPhotoRecette.php';
include 'modal/modalCreationRecette/modalUpNbConvive.php';
include 'modal/modalCreationRecette/modalUpNom.php';
?>



<div class="container p-0">

    <div class="row justify-content-center align-items-center bg-grey">

        <div class="col-sm-3 p-0">
            <img class="img w-100" id="avatar" src="<?= $recette[0]->URL_IMAGE; ?>" alt="recette" />
            <div class="item w-100">
                <span class="burns"><?= $recette[0]->NBBURN; ?><i class="material-icons pr-0 pl-1" style="font-size:15px;color:white;">whatshot</i></span>
            </div>
        </div>

        <div id="infosRecette" class="col-sm-8">
            <h3><?= $recette[0]->NOM_RE; ?></h3>
            <h5><?= $recette[0]->NOM_UT; ?></h5>
            <h4><?php if($recette[0]->STATUT == 0){echo 'Brouillon';} else{echo 'Publique';} ?></h4>
            <h4><i class="material-icons text-white" style="font-size:30px;vertical-align: -6px;">people</i> <?= $recette[0]->NB_CONV; ?></h4>
            <h4 class="dropdown-toggle pointer" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Description
            </h4>
            <div class="collapse" id="collapseExample">
                <div class="pl-3 mb-2">
                    <p><?= $recette[0]->DESC_L; ?></p>
                </div>
            </div>
        </div>

        <div class="col-sm-1 p-0 text-center">
            <?php
                echo '<form id="validSettings" class="mb-3" action="index.php" method="get">
                          <input type="hidden" name="p" value="recette">
                          <input type="hidden" name="ID" value=' . $recette[0]->ID . '>
                          <a class="pointer" onclick="document.getElementById(\'validSettings\').submit()">
                            <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#28e257">check</i>
                          </a>
                      </form>';
            ?>
            <div class="dropdown mb-3">
                <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff5e14">add</i>
                <div class="dropdown-menu dropdown-menu-right bg-lightgrey p-0">
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutIngredient">Ingrédient</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutEtape">Étape</a>
                </div>
            </div>
            <div class="dropdown">
                <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff5e14">edit</i>
                <div class="dropdown-menu dropdown-menu-right bg-lightgrey p-0">
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpNom">Nom</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutDescC">Description courte</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutDescL">Description longue</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalSwapEtape">Échanger étape</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpNbConvive">Convives</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalAjoutPhotoRecette">Image</a>
                    <a class="dropdown-item text-white" data-toggle="modal" data-target="#myModalUpStatutRecette">
                        <?php if($recette[0]->STATUT == 0){
                            echo 'Publier';
                        }
                        else{
                            echo 'Rendre invisible';
                        }
                        ?>
                    </a>
                    <a class="dropdown-item text-danger" data-toggle="modal" data-target="#myModalDeleteRecette">Supprimer</a>
                </div>
            </div>
        </div>
    </div>

    <div id="recetteIngStep" class="row">
        <div class="col-sm-3 p-0">
            <h3 class="text-center p-2" style="background: #ff5e14;">Ingrédients</h3>
            <?php foreach($recette[2] as $ingr): ?>
                <div class="d-flex flex-row justify-content-left border-bottom border-right border-2" style="padding: 3%;background: white">
                    <img class="img" src="<?= $ingr->URL_IMAGE ?>" width="40px" height="40px">
                    <h4 class="text-dark pt-2 pl-2"><?= $ingr->NOM_INGR.' ('.$ingr->QUANTITE.' '.$ingr->UNITE.')' ?></h4>
                </div>
            <?php endforeach; ?>
        </div>

        <div class="col-sm-9 p-0" style="background: #444444">
            <h3 class="text-center p-2">Étapes</h3>
            <?php foreach($recette[1] as $etape): ?>
                <div class="d-flex flex-row justify-content-left border-bottom border-2 p-3 bg-white">
                    <span class="etapebadge mr-2"><?= $etape->NUMERO ?></span>
                    <h4 class="text-dark m-0"><?= $etape->ETAPE ?></h4>
                    <form id="deleteStep" action="index.php" method="post">
                        <input type="hidden" name="p" value="deleteEtape">
                        <input type="hidden" name="id" value=<?php echo $recette[0]->ID; ?>>
                        <input type="hidden" name="num" value=<?php echo $etape->NUMERO; ?>>
                        <a class="pointer" onclick="document.getElementById('deleteStep').submit()">
                            <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#ff0000;vertical-align: -12px;">clear</i>
                        </a>
                    </form>
                </div>
            <?php endforeach; ?>
        </div>
    </div>
</div>