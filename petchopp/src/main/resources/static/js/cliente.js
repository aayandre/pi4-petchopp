$(document).ready(function () {
    $("#rg").mask('00.000.000-0');
    $("#cpf").mask('000.000.000-00');
    $("#cel").mask('(00) 00000-0000');
    $("#tel").mask('(00) 0000-0000');
});
/* 
  EXIBE A SENHA 
*/

$(document).ready(function () {
    $('.pass_show').append('<span class="ptxt">Show</span>');
});
$(document).on('click', '.pass_show .ptxt', function () {

    $(this).text($(this).text() == "Show" ? "Hide" : "Show");

    $(this).prev().attr('type', function (index, attr) { return attr == 'password' ? 'text' : 'password'; });

}); 
/*
REMOVE A MASCARA AO CLICAR EM SUBMIT
*/ 
$(".formCli").submit(function(){
    $("#rg").unmask();
    $("#cpf").unmask();
    $("#cel").unmask();
    $("#tel").unmask();
});