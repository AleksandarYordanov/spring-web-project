!function (n) {
    "use strict";

    function t() {
        this.$body = n("body")
    }

    t.prototype.init = function () {



        Dropzone.autoDiscover = !1, n('[data-plugin="dropzone"]').each(function () {
            var t = n(this).attr("action"), i = n(this).data("previewsContainer"), e = {url: t};
            i && (e.previewsContainer = i);

            var o = n(this).data("uploadPreviewTemplate");
            o && (e.previewTemplate = n(o).html());
            n(this).dropzone(e)

            console.log(e.previewTemplate)
        })
    }, n.FileUpload = new t, n.FileUpload.Constructor = t
}(window.jQuery), function () {
    "use strict";
    window.jQuery.FileUpload.init()
}();