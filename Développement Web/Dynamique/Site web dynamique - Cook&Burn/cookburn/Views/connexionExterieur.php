<div class="container d-flex justify-content-center align-items-center">
    <form class="formExterieur" action="index.php" method="post">
        <input type="hidden" name="p" value="connexion2">
        <input type="hidden" name="id" value="<?php echo $info[0]; ?>">
        <div class="input-group">
            <input name="login" type="text" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Identifiant">
        </div>
        <div class="input-group">
            <input name="password" type="password" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Mot de Passe">
        </div>
        <div class="d-flex justify-content-center mb-0" >
            <button style="background: #208e07;padding-left: 12px;padding-right: 12px; color: white" type="submit" class=" btn btn-sm">Se connecter</button>
        </div>
    </form>
</div>
<script type="text/javascript">alert("Vous devez être connecté pour voir le contenue de cette recette.")</script>