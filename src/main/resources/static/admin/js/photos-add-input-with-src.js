$('.my-form').submit(function(){

    $('.new-image').each(function () {
        var value =  $(this).children('img').attr('src');
        $(this).children('input').val(value)
    })



});