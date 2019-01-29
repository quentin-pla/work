<?php foreach ($recettes as $recette){
    echo '
    <div class="recetteProfil">
        <img src="' . imgRecette . '">
        <h3>$nom_re</h3>
        <p>$desc_c</p>
        <button >Consulter</button>
    </div>
    ';
}