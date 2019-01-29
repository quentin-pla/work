<?php include 'header.php'; ?>

    <body>
        <div id="inBody" class="d-flex flex-column text-white bg-grey" style="padding: 2vw 0vw 2vw 0vw">
            <h2 id="titlepage" class="text-center" style="margin-bottom: 2vw"><?= $title; ?></h2>
            <div class="bg-lightgrey" style="border-radius: 0vw; box-shadow: 0px -10px 20px rgba(0,0,0,0.2),0px 10px 20px rgba(0,0,0,0.2);padding: 30px 15px 30px 15px; margin-bottom: 1.5%;">
                <div class="row">
                    <?= $content; ?>
                </div>
            </div>
        </div>
    </body>
</html>


