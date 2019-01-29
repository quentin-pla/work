function switchButton(idBtn, idList) {

    // style du nouveau bouton actif et perte du style de l'ancien

    var ButtonActifs = document.getElementsByClassName('btnActifProfil');
    for (let btn of ButtonActifs) btn.setAttribute('class','btnSwitch row justify-content-center align-items-center py-2');
    document.getElementById(idBtn).setAttribute('class','btnSwitch btnActifProfil row justify-content-center align-items-center py-2');

    // affichage de la nouvelle liste active et disparition de l'ancienne

    var listActives = document.getElementsByClassName('listProfileActive');
    for (let list of listActives) list.setAttribute('class','listProfile');
    document.getElementById(idList).setAttribute('class','listProfile listProfileActive')
}