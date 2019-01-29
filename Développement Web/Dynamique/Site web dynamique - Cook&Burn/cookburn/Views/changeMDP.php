<div class="container" id="mdp">
    <h2>Changement de mot de passe</h2>
    <form action="index.php" method="post">
            <input type="hidden" name="p" value="changeMDP">
            <input type="hidden" name="id_ut" value="<?php echo $info[0];?>">
            <input type="hidden" name="code" value="<?php echo $info[1];?>">

        <div class="input-group">
            <input name="nouveau1" type="password" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Nouveau Mot de Passe">
        </div>
        <div class="input-group">
            <input name="nouveau2" type="password" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Confirmation Nouveau Mot de Passe">
        </div>
        <button type="submit" class="btn btn-sm w-80" id="btnEnvoie">Envoyer</button>
    </form>
</div>
