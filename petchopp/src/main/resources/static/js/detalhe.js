$(document).ready(function () {

    if($(window).width() > 1200 && $("#detalhesId").css("display") == "none") {
        $("#detalhesId").toggle();
    }

    $("#detalhesToggle").click(function () {
        $("#detalhesId").toggle(700);
    })
    

});