$(function () {
    // Watcher dos links da sidebar, muda a cor
    if (!($('#li-activator').text() == "")) {
        $('#' + $('#li-activator').text()).addClass('li-active');
    }

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
});