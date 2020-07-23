$(document).ready(function(){
    var $categoryPicker =  $(".category-picker");
    var $categoryPickerTable =  $(".category-picker-table");
    var $categoryButtonShow = $(".category-picker-button-show")
    var $categoryInput = $(".category-picker-input")
    var $categoryTable =  $(".category-table");
    var $categoryTableRemoveButton = $(".category-table-remove-item");

    $categoryPicker.hide();

    $categoryButtonShow.click(function(){
        $categoryPicker.fadeIn();
    })


    $categoryInput.on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".category-picker-table tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });



    // Hide any picker when it or the input field loses focus.
    $(document).on('mouseup', function (e) {

        if (!$categoryPicker.is(e.target) && !$categoryButtonShow.is(e.target) && !$(e.target).parents().hasClass('category-picker')) {
            $categoryPicker.fadeOut(200);
        }
    });

    let i = 0;
    $categoryPickerTable.children().on('click', function (e){
        console.log(e.target);
        console.log("prtessed"+i);
        i++;
        let tr ;
        if($(e.target).is("tr")){
            tr =  $(e.target).html()
        }else{
            var  $tr =  $(e.target).closest('tr');
            tr =$(e.target).closest('tr').html()
            var $value = $tr.find(".value")

        }
        $categoryTable.children('tr:last').after('<tr>'+tr+`<input type="hidden" name="${$value.attr('name')}"  value="${$value.html()}"/>`+'<td><a class="action-icon category-table-remove-item"> <i class="mdi mdi-delete"></i></a></td></tr>')

        $(".category-table-remove-item").on('click',function (e){
            console.log(e);
            $(e.target).closest('tr').remove();
        })
    })

    $(".category-table-remove-item").on('click',function (e){
        console.log(e);
        $(e.target).closest('tr').remove();
    })


});