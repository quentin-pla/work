<div class="modal fade" id="myModalDeleteRecette">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content">
            <div class="modal-header px-2 pt-3 pb-1">
                <h4 class="modal-title pl-3">SUPPRIMER ?</h4>
                <button class="btn p-0 bg-grey" style="background: #232323" type="button" data-dismiss="modal"><i class="material-icons px-2 pt-1 bg-grey" style="font-size:20px">close</i></button>
            </div>
            <div class="modal-body py-0">
                <form action="index.php" method="post">
                    <input type="hidden" name="p" value="deleteRecette">
                    <input type="hidden" name="id" value=<?php echo $recette[0]->ID; ?>>
                    <button type="submit" class="btn btn-sm w-100">OUI</button>
                </form>
            </div>
            <div class="modal-footer pt-1"></div>
        </div>
    </div>
</div>