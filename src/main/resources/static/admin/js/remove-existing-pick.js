$(document).ready(function() {
    var $removeButtons = $(".btn-remove-existing-pic");

    $removeButtons.on(`click`, function (e) {
        $(e.target).closest('.dz-image-preview').remove();
    })

});


