<!DOCTYPE html> <html lang="fr">
<head><title>CookBurn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="https://i.imgur.com/QebCger.png" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="../public/css/style.css" type="text/css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script type='text/javascript' src='//platform-api.sharethis.com/js/sharethis.js#property=5bde280dc7a9470012145d9c&product=inline-share-buttons' async='async'></script>
</head>
<header>

    <!-- onclick sur les 3 barres -->

    <div class="bg-lightgrey collapse" id="navbarHeader">
        <div class="container">
            <div class="row">
                <div id="paveBox" class="col-sm-8 col-md-7 py-4">
                    <h3 class="text-white mb-2">Qui sommes nous ?</h3>
                    <p class="text-muted text-grey">Nous produisons des barbecues connectés de haute qualité
                        pour nos clients à travers le monde. Grâce à l'interface tactile disponible sur
                        nos barbecues, vous aurez la possibilité de choisir la recette que vous souhaitez
                        cuisiner afin que toute la cuisson se fasse efficacement et automatiquement.</p>
                </div>
                <div class="col-sm-4 offset-md-1 py-4 justify-content-center d-flex">
                    <?php
                    if(!isset($_SESSION['login'])) $_SESSION['login'] = 'non';
                    if($_SESSION['login'] == "oui") {
                        echo '<div class="d-flex flex-column justify-content-center h-100" id="decoContain">
                                    <h3 class="text-center text-white mb-2 d-block d-sm-none">Connecté avec '.$_SESSION['nom_ut'].'</h3>
                                    <form action="index.php" method="post">
                                         <input type="hidden" name="p" value="connexion">
                                       <input type="hidden" name="login">
                                       <input type="hidden" name="password">
                                       <button type="submit" class="btn w-100 bg-lightgrey" id="btndeconnexion">Deconnexion</button>
                                    </form>
                                  </div>';
                    }
                    else{
                        echo '<div class="d-flex flex-column justify-content-center h-100">
                                <a id="btnconnexion" href="#" type="button" class="btn w-100 bg-lightgrey" data-toggle="modal" data-target="#myModal">Connexion</a>
                               </div>';
                    }
                    ?>
                </div>
            </div>
        </div>
    </div>

    <!-- header de base -->

    <div class="navbar fixed-top navbar-dark bg-grey">
        <div id="pseudoContainer" class="container d-inline-flex flex-row justify-content-between">
            <a href="index.php?p=home" class="navbar-brand d-flex align-items-center mr-1">
                <img src="https://i.imgur.com/cKWfOBJ.png" width=30>
            </a>
            <form action="index.php" method="get">
                <input type="hidden" name="p" value="search">
                <div class="input-group bg-grey" style="width: 40vw;font-family: 'Montserrat Light'">
                    <input name="keywords" type="text" class="form-control form-control-sm pl-2 bg-grey text-white" placeholder="Rechercher une recette">
                    <div class="input-group-append">
                        <button class="btn p-0 bg-grey" type="submit"><i class="material-icons px-2" style="font-size:15px;color:white;">search</i></button>
                    </div>
                </div>
            </form>
            <?php
                if(!isset($_SESSION['admin'])) $_SESSION['admin'] = 'non';
                if ($_SESSION['admin'] == 'oui') {
                    echo '<a id="statutNavBar" href="index.php?p=admin" ><i class="material-icons" style="font-size: 20px;vertical-align: -3px">lock</i></a>';
                }
            ?>
            <?php
            if(!isset($_SESSION['login'])) $_SESSION['login'] = 'non';
            if ($_SESSION['login'] == 'oui') {
                echo '<a id="pseudoNavBar" href="index.php?p=profile">
                        <span class="profilbadge d-none d-sm-block"><i class="material-icons pr-1" style="font-size:15px;color:white;vertical-align: -2px">person</i>'.$_SESSION["nom_ut"].'</span>
                        <span class="profilbadgeXS d-block d-sm-none"><i class="material-icons" style="font-size:15px;color:white;vertical-align: -2px">person</i></span>
                      </a>';
            }
            ?>
            <button class="navbar-toggler collapsed ml-1 p-0" type="button" data-toggle="collapse" data-target="#navbarHeader" style="border-color: white">
                <span class="navbar-toggler-icon p-0" style="font-size: 15px"></span>
            </button>
        </div>
    </div>
</header>

<!-- onclick sur connexion -->

<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content">
            <div class="modal-header px-2 pt-3 pb-1">
                <h4 class="modal-title pl-3">Connexion</h4>
                <button class="btn p-0 bg-grey" style="background: #232323" type="button" data-dismiss="modal"><i class="material-icons px-2 pt-1 bg-grey" style="font-size:20px">close</i></button>
            </div>
            <div class="modal-body py-0">
                <form action="index.php" method="post">
                    <input type="hidden" name="p" value="connexion">
                    <input name="login" type="text" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Identifiant">
                    <input name="password" type="password" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Mot de passe">
                    <button type="submit" class="btn btn-sm w-100">Se connecter</button>
                </form>
            </div>
            <div class="modal-footer pt-1">
                <button type="submit" class="btn btn-sm w-100"  data-toggle="modal" data-target="#myModalVerifPseudoAndMail" data-dismiss="modal">Mot de passe oublié</button>
            </div>
        </div>
    </div>
</div>

<!-- modal oubli mdp pseudo -->

<div class="modal fade" id="myModalVerifPseudoAndMail">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content">
            <div class="modal-header px-2 pt-3 pb-1">
                <h4 class="modal-title pl-2">Vérification de l'adresse email</h4>
                <button class="btn p-0 bg-grey" style="background: #232323" type="button" data-dismiss="modal"><i class="material-icons px-2 pt-1 bg-grey" style="font-size:20px">close</i></button>
            </div>
            <div class="modal-body py-0">
                <form action="index.php" method="post">
                    <input type="hidden" name="p" value="mail">
                    <input name="pseudo" type="text" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Votre pseudo"/>
                    <input name="mail" type="text" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Mail du compte"/>
                    <button data-toggle="modal" data-target="#myModalVerifCode" class="btn btn-success">Continuer</button>
                </form><br />
            </div>
        </div>
    </div>
</div>

</html>