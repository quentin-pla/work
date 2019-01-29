<div class="modal fade" id="myModalAjoutPhotoRecette">
    <div class="modal-dialog modal-dialog-centered modal-md">
        <div class="modal-content">
            <div class="modal-header px-2 pt-3 pb-1">
                <h4 class="modal-title pl-3 mb-3">Sélectionner une image</h4>
                <button class="btn p-0 bg-grey" style="background: #232323" type="button" data-dismiss="modal"><i class="material-icons px-2 pt-1 bg-grey" style="font-size:20px">close</i></button>
            </div>
            <div class="modal-body py-0">
                <div class="dropzone">
                    <div class="info text-center h-100"></div>
                </div>
                <input type="hidden" id="p" value="chgPhotoRecette"/>
                <input type="hidden" id="id" value=<?php echo $recette[0]->ID; ?>>
                <script type="text/javascript" src="../../../../public/imgurAPI/js/imgur.js"></script>
                <script type="module" src="../../../../public/imgurAPI/js/upload.js"></script>
            </div>
            <div class="modal-footer pt-1"></div>
        </div>
    </div>
</div>