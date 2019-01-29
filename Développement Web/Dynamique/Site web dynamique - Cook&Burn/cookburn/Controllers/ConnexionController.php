<?php

namespace Cookburn\Controllers;

use Cookburn\Models\Connexion;
use Cookburn\Models\Recuperation;
use Cookburn\Models\Utilisateur;
use Core\Controllers\Controller;


class ConnexionController extends Controller
{
    /**
     * Verifie que les données saisie par l'utilisateur sont correctes et modifie la variable $_SESSION en conséquence
     * @return bool
     */
    public function TryConnexion(){
        $connexionfail = false;
        if($_POST['login'] == null){
            $_SESSION['id_ut'] = null;      // id de l'utilisateur
            $_SESSION['nom_ut'] = null;     // nom de l'utilisateur
            $_SESSION['login'] = 'non';     // 'oui' = l'utilisateur est connecté       'non' = l'utilisateur n'est pas connecté
            $_SESSION['admin'] = 'non';     // 'oui' = l'utilisateur connecté est admin      'non' = l'utilisateur connecté n'est pas admin
        }
        else {
            $connexion = new Connexion();
            $connexion = $connexion->tryConnexion();

            if (password_verify($_POST['password'], $connexion->MDP)) {
                $_SESSION['id_ut'] = $connexion->ID_UT;
                $_SESSION['nom_ut'] = $_POST['login'];
                $_SESSION['login'] = 'oui';
                $this->tryAdmin();
            } else {
                $_SESSION['id_ut'] = null;
                $_SESSION['nom_ut'] = null;
                $_SESSION['login'] = 'non';
                $_SESSION['admin'] = 'non';
                $connexionfail = true;
            }
        }
        return $connexionfail;
    }

    public function TryConnexion2(){
        $this->TryConnexion();
        if($_SESSION['login'] == 'oui'){
            $recette_controller = new RecetteController();
            $recette_controller->showRecette2();
        }
        else{
            $info = [$_POST['id']];
            $this->render('default','connexionExterieur','Connexion',compact('info'));
            echo '<script type="text/javascript">alert("Connexion refusé.")</script>';
        }
    }

    /**
     * Sert à afficher une page de connexion quand un utilisateur essaye de voir une recette mais qu'il n'a pas le droit de la voir car il n'est pas connecté
     * @param $id
     */
    public function affichageConnexionExterieur($id){
        $info = [$id];
        $this->render('default','connexionExterieur','Connexion',compact('info'));
    }
    /**
     * Verifie si l'utilisateur qui est en train de se connectéc est admin ou non
     */
    public function  tryAdmin() {
        $connexion = new Connexion();
        $connexion = $connexion->tryAdmin();
        if ($connexion == null) {
            $_SESSION['admin'] = 'non';
        } else {
            $_SESSION['admin'] = 'oui';
        }
    }

    /**
     * Envoie un mail à l'utilisateur qui a oublié son mot de passe
     */
    public function envoieCode()
    {
        if (!(empty($_POST['pseudo'])) && !(empty($_POST['mail']))) {
            $ut = new Utilisateur();
            $ut = $ut->getUtByMailAndName();
            if (!empty($ut)) {

                $recuperation = new Recuperation();
                $recuperation = $recuperation->getByUt($ut->ID_UT);
                if (!empty($recuperation)) {
                    $recuperation = new Recuperation();
                    $recuperation->deleteByUt($ut->ID_UT);
                }

                $mail = htmlspecialchars($ut->MAIL);
                $chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
                $code = '';
                for ($i = 0; $i < 30; $i++) {
                    $code .= $chars[rand(0, strlen($chars) - 1)];
                }

                $recuperation = new Recuperation();
                $recuperation->insertCodeAndUt($ut->ID_UT, $code);

                $subject = "Changement de mot de passe";
                $header = "MIME-Version: 1.0\r\n";
                $header .= 'From:"Cookburn.tk"' . "\n";
                $header .= 'Content-Type:text/html; charset="utf-8"' . "\n";
                $header .= 'Content-Transfer-Encoding: 8bit';
                $message = '
                                     <html>
                                     <head>
                                        <meta charset="utf-8" />
                                       <title>Récupération de mot de passe</title><br /><br />
                                     </head>
                                     <body>
                                       <font color="#303030";>
                                         <div align="center">
                                           <table width="800px">
                                             <tr>
                                               <td>
                                                 <p align="center">Bonjour <b>' . $ut->NOM_UT . '</b>,</p><br /><br />
                                                 <p>Voici votre code de récupération: <b>' . $code . '</b></p>
                                                 <p>A bientôt sur <a href="https://cookburn.tk/public/index.php?p=affichageChangeMDP&id_ut='. $ut->ID_UT .'&code='. $code .' ">Cookburn</a></p><br /><br />
                                               </td>
                                             </tr>
                                             <tr>
                                               <td align="center">
                                                 <font size="2">
                                                   Ceci est un email automatique, merci de ne pas y répondre.
                                                 </font>
                                               </td>
                                             </tr>
                                           </table>
                                         </div>
                                       </font>
                                     </body>
                                     </html>
                                     ';
                mail($mail, $subject, $message, $header);

                $recette_controller = new RecetteController();
                $recette_controller->showAllDesc('Recettes à la une');
                echo '<script type="text/javascript">alert("Un code de vérification vous a été envoyé par mail.")</script>';
            } else {
                $recette_controller = new RecetteController();
                $recette_controller->showAllDesc('Recettes à la une');
                echo '<script type="text/javascript">alert("Ces informations ne sont pas valide")</script>';
            }
        } else {
            $recette_controller = new RecetteController();
            $recette_controller->showAllDesc('Recettes à la une');
            echo '<script type="text/javascript">alert("Veuillez entrer votre pseudo et votre mail svp")</script>';
        }

    }


    /**
     * Affichage d'une page ou l'utilisateur pourra rentrer son nouveau mot de passe (suite à un oublie)
     */
    public function affichageChangeMDP(){
        $title = "";
        $verif_req = new Recuperation();
        $verif_req = $verif_req->getCodeByUtAndCode();
        if ($verif_req == true) {
            $info = [$_GET['id_ut'],$_GET['code']];
            $this->render('default','changeMDP',$title,compact('info'));
        } else {
            $recette_controller = new RecetteController();
            $recette_controller->showAllDesc('Recettes à la une');
            echo '<script type="text/javascript">alert("Token ou ID_UT invalide")</script>';
        }
    }

    /**
     * Verifie que le token est correct est que les 2 mot de passe entré soit les même
     */
    public function changeMDP()
    {
        $verif_req = new Recuperation();
        $verif_req = $verif_req->getCodeByUtAndCode2();
        if ($verif_req == true) {
            if ($_POST['nouveau1'] == $_POST['nouveau2']) {
                $utilisateur = new Utilisateur();
                $utilisateur->updateMDP2($_POST['nouveau1'], $_POST['id_ut']);

                $ut = new Recuperation();
                $ut = $ut->getCodeByUt();

                $deleteCode = new Recuperation();
                $deleteCode->deleteCodeAndUt($_POST['id_ut'], $ut->CODE);

                $recette_controller = new RecetteController();
                $recette_controller->showAllDesc('Recettes à la une');
                echo '<script type="text/javascript">alert("Mot de passe changé !")</script>';
            } else {
                $this->affichageChangeMDP();
                echo '<script type="text/javascript">alert("Le mot de passe de confirmation n\'est pas le même que celui rentré !")</script>';
            }
        }
        else{
            $recette_controller = new RecetteController();
            $recette_controller->showAllDesc('Recettes à la une');
            echo '<script type="text/javascript">alert("Token ou ID_UT invalide")</script>';
        }
    }
}

