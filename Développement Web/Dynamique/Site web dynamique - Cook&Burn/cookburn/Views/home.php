<?php
if ($pagined) echo'<a id ="icibas" href="#icihaut"></a><script>document.getElementById(\'icibas\').click(); document</script>';
?>

<div class="row justify-content-center align-items-center w-100" style="background: linear-gradient(gold,goldenrod);padding-top: 30px;margin: 30px 0 30px 30px">
    <div id="recette" class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" style="padding-right: 30px">
        <a class="card w-100" href="<?= 'index.php?p=recette&ID='.$recetteTOP->ID ?>">
            <img class="card-img-top" src="<?php if($recetteTOP->URL_IMAGE != null){echo $recetteTOP->URL_IMAGE;}else{echo'https://lachroniquefacile.fr/wp-content/uploads/2018/07/%EF%BC%9F.png';} ?>" alt="Card image">
            <div class="item w-100">
                <div class="card-body text-center p-3">
                    <span class="burns"><?= $recetteTOP->NBBURN; ?><i class="material-icons pr-0 pl-1" style="font-size:15px;color:white;">whatshot</i></span>
                    <h3 class="card-title text-left mb-2"><?= $recetteTOP->NOM_RE; ?></h3>
                    <p class="card-text text-left"><?= $recetteTOP->DESC_C; ?></p>
                </div>
            </div>
        </a>
    </div>
    <div id="toprecette" class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" style="padding: 0 30px 30px 30px">
        <h2>Recette à la une</h2>
        <h3><?= $recetteTOP->NOM_RE; ?></h3>
    </div>
</div>

<?php foreach($recettes as $recette):?>

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

<?php endforeach; ?>

<!-- PAGINATION -->

<div id="listPage" class="d-flex w-100 justify-content-center" style="margin-left:30px">
    <div class="d-flex">
        <?php
            if ($currentPage > 1) echo'
                    <form action="index.php" method="get">
                        <input type="hidden" name="p" value="pagination">
                        <input type="hidden" name="currentPage" value="1">
                        <button style="margin-left: 2px"> << </button>
                    </form>
                    <form action="index.php" method="get">
                        <input type="hidden" name="p" value="pagination">
                        <input type="hidden" name="currentPage" value="'. ($currentPage-1) .'">
                        <button> < </button>
                    </form>
            ';
            echo '
                    <form action="index.php" method="get">
                        <input type="hidden" name="p" value="pagination">
                        <input type="hidden" name="currentPage" value="'. $currentPage .'">
                        <button id="btnPageMilieu">' . $currentPage . '</button>
                    </form>';
            if ($currentPage < $pageNumber) echo'
                    <form action="index.php" method="get">
                        <input type="hidden" name="p" value="pagination">
                        <input type="hidden" name="currentPage" value="'. ($currentPage+1) .'">
                        <button> > </button>
                    </form>
                    <form action="index.php" method="get">
                        <input type="hidden" name="p" value="pagination">
                        <input type="hidden" name="currentPage" value="'. $pageNumber .'">
                        <button style="margin-right: 2px"> >> </button>
                    </form>
            ';
        ?>
    </div>
</div>