$(function () {

    console.log('aqui');
    

    // Watcher dos links da sidebar, muda a cor
    $('.nav-item').click(function () {
        $.each($('.nav-item'), function (indexInArray, valueOfElement) {
            $(valueOfElement).removeClass('li-active');
        });

        $(this).addClass('li-active');
    });

    $(window).resize(function () {
        if ($(window).width() > 820) {
            $('#sidebar').removeClass('collapse');
        }
    });

    if($('#pageTitleId') === null) {
        let idLiNav = '#' + $(this).text();
        $(idLiNav).addClass('li-active');
    }
    

});