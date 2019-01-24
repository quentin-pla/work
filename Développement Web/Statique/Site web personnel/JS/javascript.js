/*DEMARRAGE PAGE*/

window.onload = function() {
    checkCookie();
    placegpu();
    whatpage();
    changecss();
}

/*#############*/


/*FAIRE FLASH APPAREIL PHOTO + CHANGEMENT STYLE*/

function changecss() {
    var link = document.getElementById('feuillecss');
        link.setAttribute('href', getCookie("stylepage"));
}

function Flash() {
    document.getElementById("ChangerStyleCSS").setAttribute("src", "Images/Camera+flash.png");
    document.getElementById("flashemulate").style.width = "100%";
    document.getElementById("flashemulate").style.height = "100%";
    document.getElementById("flashemulate").style.opacity = "1";
    setTimeout(function () {
        if(getCookie("stylepage") === "CSS/style.css")
        {
            setCookie("stylepage", "CSS/variance.css");
        }
        else if(getCookie("stylepage") === "CSS/variance.css")
        {
            setCookie("stylepage", "CSS/style.css");
        }
        document.getElementById("flashemulate").style.width = "0%";
        document.getElementById("flashemulate").style.height = "0%";
        document.getElementById("flashemulate").style.opacity = "0";
        location.reload();
    }, 150);
}

/*###########################*/


/*ENVOYER MAIL*/

function mail() 
{
    window.location = "mailto:quentin-pla@hotmail.fr?subject=WIM G4-A";
}

/*#############*/


/*BOUTON RETOUR EN HAUT*/

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
        document.getElementById("return-top").style.display = "block";
    } else {
        document.getElementById("return-top").style.display = "none";
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

/*#######################*/


/*DEMARRAGE/ARRET GPU*/

function startgpu() {
    var on = document.getElementById("gogpu");

    if(on.alt === "off"){
        on.alt = "on";
            document.getElementById("ventilo1").style.animation = "rotating 1s linear infinite";
            document.getElementById("ventilo2").style.animation = "rotating 1s linear infinite";
            document.getElementById("ventilo3").style.animation = "rotating 1s linear infinite";
            document.getElementById("ventilo4").style.animation = "rotating 1s linear infinite";
            document.getElementById("ventilo5").style.animation = "rotating 1s linear infinite";
            document.getElementById("ventilo6").style.animation = "rotating 1s linear infinite";
    }
    else if(on.alt === "on"){
        on.alt = "off";
        document.getElementById("ventilo1").style.animation = "none";
        document.getElementById("ventilo2").style.animation = "none";
        document.getElementById("ventilo3").style.animation = "none";
        document.getElementById("ventilo4").style.animation = "none";
        document.getElementById("ventilo5").style.animation = "none";
        document.getElementById("ventilo6").style.animation = "none";
    }
}

/*###########################*/


/*PLACEMENT GPU MILIEU PAGE*/

function placegpu() {
    var screenheight = window.innerHeight;
    var gpu = document.getElementsByClassName("firstgpu");
    var gpu2 = document.getElementsByClassName("secondgpu");
    gpu[0].style.top = (((screenheight+100)/2)-316.5) + "px";
    gpu2[0].style.top = (((screenheight+100)/2)-316.5) + "px";
}

/*############################*/


/*COOKIE STYLE CSS*/

function setCookie(cname,cvalue) {
    document.cookie = cname + "=" + cvalue + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie() {
    var css=getCookie("stylepage");
    if (css == "") {
       css = "CSS/style.css";
       if (css != "" && css != null) {
           setCookie("stylepage", css);
       }
    }
}

/*##########################*/


/*ADAPTATIONS EN FONTION PAGE*/

function whatpage() {
    var page = window.location.href;
    console.log(page);
    var divpos = document.getElementsByClassName("whatpage");
    var block = document.getElementsByClassName("blockbutton");
    var blocmid = document.getElementsByClassName("blocmid");
    var c_blocmid = document.getElementsByClassName("c_blocmid");
    if(page.indexOf("index.html")>=0){
        divpos[0].style.width = "103.5px";
        divpos[0].style.right = "492px";
        block[0].style.width = "103.5px";
        block[0].style.right = "492px";
    }
    else if(page.indexOf("telechargement.html")>=0){
        console.log("ok");
        divpos[0].style.width = "192.1px";
        divpos[0].style.right = "289px";
        block[0].style.width = "192.1px";
        block[0].style.right = "289px";
    }
    else if(page.indexOf("contact.html")>=0){
        console.log("ok");
        divpos[0].style.width = "109px";
        divpos[0].style.right = "170px";
        block[0].style.width = "109px";
        block[0].style.right = "170px";
        c_blocmid[0].style.height = "100%";
    }
}

/*################################*/
