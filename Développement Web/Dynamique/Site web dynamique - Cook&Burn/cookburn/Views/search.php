<?php
if(count($recettes) > 0) {
    foreach ($recettes as $recette):
?>
                <div id="recette" class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                    <a class="card w-100" href="<?= 'index.php?p=recette&ID='.$recette->ID ?>">
                        <img class="card-img-top" src="<?php if($recette->URL_IMAGE != null){echo $recette->URL_IMAGE;}else{echo'https://lachroniquefacile.fr/wp-content/uploads/2018/07/%EF%BC%9F.png';} ?>" alt="Card image">
                        <div class="item w-100">
                            <div class="card-body text-center p-3">
                                <span class="burns"><?= $recette->NBBURN; ?><i class="material-icons pr-0 pl-1" style="font-size:15px;color:white;">whatshot</i></span>
                                <h3 class="card-title text-left mb-2"><?= $recette->NOM_RE; ?></h3>
                                <p class="card-text text-left"><?= $recette->DESC_C; ?></p>
                            </div>
                        </div>
                    </a>
                </div>
            <?php
    endforeach;
}
else echo '<div class="d-flex justify-content-center w-100" style="height: 30vh"><h3 style="padding-top: 5vw">Oops... Aucun résultat.</h3></div>'; ?>
