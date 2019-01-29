<div class="d-flex w-100 justify-content-center" style="margin-bottom:2%">
    <form action="index.php" method="post">
        <input type="hidden" name="p" value="majPas">
        <input style="text-align: center" name="pas">
        <button>Mettre le pas de la pagination à jour</button>
    </form>
</div>
<div class="container">
    <ul id="onglet" class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#Recettes">Recettes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#Utilisateurs">Utilisateurs</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#Ingrédients">Ingrédients</a>
        </li>
    </ul>

    <div id="pageAdmin" class="tab-content py-2">
        <div id="Recettes" class="container tab-pane table-responsive active">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Titre</th>
                    <th>Auteur</th>
                    <th>Burns</th>
                    <th>Convives</th>
                    <th>Description courte</th>
                    <th>Statut</th>
                    <th></th>
                </tr>
                <?php foreach ($recettes as $rec):?>
                    <tr>
                        <td><?php echo $rec->ID; ?></td>
                        <td><img src="<?php echo $rec->URL_IMAGE; ?>"></td>
                        <td><?php echo $rec->NOM_RE; ?></td>
                        <td><?php echo $rec->NOM_UT; ?></td>
                        <td><?php echo $rec->NBBURN; ?></td>
                        <td><?php echo $rec->NB_CONV; ?></td>
                        <td><?php echo $rec->DESC_C; ?></td>
                        <td>
                            <?php
                                if($rec->STATUT == 0)echo "Brouillon";
                                else if($rec->STATUT == 1)echo "Privée";
                                else if($rec->STATUT == 2)echo "Publique";
                            ?>
                        </td>
                        <td>
                            <form action="index.php" method="post">
                                <input type="hidden" name="p" value="modifierRecette">
                                <input type="hidden" name="id" value=<?php echo $rec->ID; ?>>
                                <button type="submit" class="btn btn-sm bg-white"><i class="material-icons" style="font-size:20px;color:#232323;vertical-align: -3px">edit</i></button>
                            </form>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </div>

        <div id="Utilisateurs" class="container tab-pane table-responsive fade">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Avatar</th>
                    <th>Pseudo</th>
                    <th>Adresse mail</th>
                    <th>Compte</th>
                    <th></th>
                </tr>
                <?php foreach ($utilisateurs as $ut):?>
                    <tr>
                        <td><?php echo $ut->ID_UT; ?></td>
                        <td><img src="<?php echo $ut->AVATAR; ?>"></td>
                        <td><?php echo $ut->NOM_UT; ?></td>
                        <td><?php echo $ut->MAIL; ?></td>
                        <td>
                            <?php
                                if($ut->STATUT == "A")echo "Administrateur";
                                else if($ut->STATUT == "M")echo "Utilisateur";
                             ?>
                        </td>
                        <td>
                            <form action="index.php" method="post">
                                <input type="hidden" name="p" value="deleteUt">
                                <input type="hidden" name="id" value="<?php echo $ut->ID_UT; ?>">
                                <button type="submit" class="btn btn-sm bg-white"><i class="material-icons" style="font-size:20px;color:#232323;vertical-align: -3px">delete</i></button>
                            </form>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </div>

        <div id="Ingrédients" class="container tab-pane table-responsive fade">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Nom</th>
                    <th>Auteur</th>
                    <th>Description</th>
                    <th></th>
                </tr>
                <?php foreach ($ingredients as $ing): ?>
                    <tr>
                        <td><?php echo $ing->ID_INGR; ?></td>
                        <td><img src="<?php echo $ing->URL_IMAGE; ?>"></td>
                        <td><?php echo $ing->NOM_INGR; ?></td>
                        <td><?php echo $ing->NOM_UT; ?></td>
                        <td><?php echo $ing->DESCR_INGR; ?></td>
                        <td>
                            <form action="index.php" method="post">
                                <input type="hidden" name="p" value="modIngredient">
                                <input type="hidden" name="id_ingr" value="<?php echo $ing->ID_INGR; ?>">
                                <button type="submit" class="btn btn-sm bg-white"><i class="material-icons" style="font-size:20px;color:#232323;vertical-align: -3px">edit</i></button>
                            </form>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </div>
    </div>
</div>