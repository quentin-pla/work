<?php include 'header.php'; ?>
<body>
<div id="demo" class="carousel slide" data-ride="carousel">
    <div class="row w-100 d-none d-sm-block">
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
            <li data-target="#demo" data-slide-to="3"></li>
        </ul>
    </div>
    <div class="carousel-inner">
        <div class="h-100 justify-content-center carousel-item active">
            <img src="https://i.imgur.com/tHKaOvo.jpg" alt="background">
            <div class="carousel-caption text-left text-nowrap py-0" style="padding-left: 5vw">
                <h1>Populaire</h1>
                <h3>Recettes en-dessous.</h3>
            </div>
        </div>
        <div onclick="location.href='index.php?p=search&keywords=burger';" class="h-100 justify-content-center carousel-item pointer">
            <img src="https://i.imgur.com/amftsTv.jpg" alt="hamburger">
            <div class="carousel-caption text-left text-nowrap py-0" style="padding-left: 5vw">
                <h1>Burgers</h1>
                <h3>Découvrez nos recettes.</h3>
            </div>
        </div>
        <div onclick="location.href='index.php?p=search&keywords=poivron';" class="h-100 justify-content-center carousel-item pointer">
            <img src="https://i.imgur.com/fxja6yT.jpg" alt="poivron">
            <div class="carousel-caption text-left text-nowrap py-0" style="padding-left: 8vw">
                <h1>Poivrons</h1>
                <h3>Découvrez nos recettes.</h3>
            </div>
        </div>
        <div onclick="location.href='index.php?p=search&keywords=tomate';" class="h-100 justify-content-center carousel-item pointer">
            <img src="https://i.imgur.com/Lch7WDs.jpg" alt="tomate">
            <div class="carousel-caption text-left text-nowrap py-0" style="padding-left: 10vw">
                <h1>Tomates</h1>
                <h3>Découvrez nos recettes.</h3>
            </div>
        </div>
    </div>
    <a class="carousel-control-prev" href="#demo" data-slide="prev">
        <span class="carousel-control-prev-icon"></span>
    </a>
    <a class="carousel-control-next" href="#demo" data-slide="next">
        <span class="carousel-control-next-icon"></span>
    </a>
</div>
<div class="d-flex flex-column text-white bg-grey" style="padding: 2vw 0vw 5vw 0vw">
    <h2 id="titlepage" class="text-center" style="margin-bottom: 2vw"><?= $title; ?></h2>
    <div style="position:absolute; padding-top:10px">
        <div id="icihaut" style="top:10px"></div>
    </div>
    <div class="bg-lightgrey" style="border-radius: 0vw; box-shadow: 0px -10px 20px rgba(0,0,0,0.2),0px 10px 20px rgba(0,0,0,0.2);padding: 0 45px 3vw 15px; margin-bottom: 3%;">
        <div class="row">
            <?= $content; ?>
        </div>
    </div>
</div>

</body>
</html>


