<div class="modal fade" id="myModalUpNbConvive">
    <div class="modal-dialog modal-dialog-centered modal-sm">
        <div class="modal-content">
            <div class="modal-header px-2 pt-3 pb-1">
                <h4 class="modal-title pl-3">Nombre de convive</h4>
                <button class="btn p-0 bg-grey" style="background: #232323" type="button" data-dismiss="modal"><i class="material-icons px-2 pt-1 bg-grey" style="font-size:20px">close</i></button>
            </div>
            <div class="modal-body py-0">
                <form action="index.php" method="post">
                    <input type="hidden" name="p" value="upNbConvive">
                    <input type="hidden" name="id" value=<?php echo $recette[0]->ID; ?>>
                    <input name="nbConv" type="number" class="form-control form-control-sm pl-2 pt-1 mb-1" placeholder="Nombre de convive">
                    <button type="submit" class="btn btn-sm w-100">Modifier</button>
                </form>
            </div>
            <div class="modal-footer pt-1"></div>
        </div>
    </div>
</div>