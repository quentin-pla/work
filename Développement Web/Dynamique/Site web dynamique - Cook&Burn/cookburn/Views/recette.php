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
            if($_SESSION['login'] == 'oui') {
                if ($_SESSION['id_ut'] != $recette[0]->ID_UT) {
                    echo '<form action="index.php" method="post">
                                <input type="hidden" name="p" value="addDeleteFav">
                                <input type="hidden" name="id" value=' . $recette[0]->ID . '>';
                    if ($isFavorite == false) {
                        echo '<button class="btnFav btn" ><i class="material-icons">grade</i></button>';
                    } else {
                        echo '<button id="btnFavPlein" class="btnFav btn" ><i class="material-icons">grade</i></button>';
                    }
                    echo '</form>';
                    echo '<form action="index.php" method="post">
                                <input type="hidden" name="p" value="likeDislike">
                                <input type="hidden" name="id" value=' . $recette[0]->ID . '>';
                    if ($isLiked == false) {
                        echo '<button class="btnLike btnFav btn" ><i class="material-icons">whatshot</i></button>';
                    } else {
                        echo '<button id="btnLikePlein" class="btnLike btnFav btn" ><i class="material-icons">whatshot</i></button>';
                    }
                    echo '</form>';
                }
            }
            if($_SESSION['login'] == 'oui') {
            if ($_SESSION['id_ut'] == $recette[0]->ID_UT) {
                echo '<form id="getSettings" action="index.php" method="post">
                        <input type="hidden" name="p" value="modifierRecette">
                        <input type="hidden" name="id" value=' . $recette[0]->ID . '>
                        <a class="pointer" onclick="document.getElementById(\'getSettings\').submit()">
                            <i class="material-icons" data-toggle="dropdown" style="font-size:30px;color:#c6c6c6">settings</i>
                        </a>';
                echo '</form>';
            }
            }
                ?>
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
                </div>
            <?php endforeach; ?>
        </div>
    </div>
        </br>
    <div class="sharethis-inline-share-buttons"></div>
</div>
