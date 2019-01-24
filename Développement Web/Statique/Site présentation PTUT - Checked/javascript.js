window.onscroll = function() {scrollFunction()};

function scrollFunction() {
    if (document.body.scrollTop > 400 || document.documentElement.scrollTop > 400) {
        document.getElementById("return-top").style.display = "block";
    } else {
        document.getElementById("return-top").style.display = "none";
    }
}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

function screensize() {

	if(window.innerWidth < 1400){
		var box1 = document.getElementsByClassName("boutons");
		box1[0].style.width = "465px";
	}

	if(window.innerWidth < 1200){
		var box2 = document.getElementsByClassName("images");
		box2[0].style.width = "450px";
	}
}

function equipe() {
	var box = document.getElementsByClassName("equipe");
		box[0].style.height = (window.innerHeight - 80) + "px";

	if(window.innerWidth < 1425){
		var box3 = document.getElementsByClassName("photos");
		box3[0].style.width = "855px";
		box3[0].style.height = "500px";
	}
}

function contact() {
	var box = document.getElementsByClassName("contact");
		box[0].style.height = (window.innerHeight - 80) + "px";
}

function mail() 
{
    window.location = "mailto:quentin-pla@hotmail.fr?subject=Checked [Projet tuteuré G4-E3]";
}

function hovera_in(obj) {
	var objet = obj.id;

	if (objet === "a_projet"){
		document.getElementById("hovera").style.right = "500px";
		document.getElementById("hovera").style.width = "108.25px";
	}
	else if (objet === "a_equipe"){
		document.getElementById("hovera").style.right = "382px";
		document.getElementById("hovera").style.width = "106px";
	}
	else if (objet === "a_contact"){
		document.getElementById("hovera").style.right = "240px";
		document.getElementById("hovera").style.width = "128.8px";
	}

	document.getElementById("hovera").style.height = "70px";
}

function hovera_out(obj) {
	document.getElementById("hovera").style.height = "0px";
}