$(function () {


    $('.tipoTag').on('change', function () {

        let cardParent = $(this).parent().parent().parent().parent().parent();
        

        let radios = cardParent.find($('input[type="radio"]:not(:checked)'));
        

        $.each(radios, function (indexInArray, valueOfElement) {
            let card = $(valueOfElement).parent().parent().parent().parent();
            
            $.each(card.find($('input:checked')), function (indexInArray, valueOfElement) {
                
                $(valueOfElement).prop('checked', false);
            });
        });

    })


});