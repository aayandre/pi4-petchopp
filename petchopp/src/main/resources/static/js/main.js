'use strict';

$.ajax({
    type: 'get',
    url: window.location.origin + '/rest/tipos',
    data: 'data',
    dataType: 'json',
    success: function (response) {
        createElementTipo(response);
    }
});

function createElementTipo(response) {

    $.each(response, function (i, tipo) {
        let templateTipoLi = $('#tipoLiId').clone();

        templateTipoLi.attr('style', 'display: visible');
        templateTipoLi.find('.nav-link').attr('style', 'display: visible');

        templateTipoLi.find('.nav-link').attr('href', window.location.origin + '/produto/tipos/' + tipo.descricao);
        templateTipoLi.find('.nav-link').attr('id', tipo.idTipo);
        templateTipoLi.find('.nav-link').text(tipo.nomeView);

        templateTipoLi.appendTo('#navBottomTipos');
    });

};

$(document).ready(function () {

    console.log("Achou o local do js")

});