'use strict';

$(document).ready(function () {

    // --- Filtros START
    // Mostra os filtros se a janela estiver a X width
    if ($(window).width() > 576 && $('#listaFiltrosId').css('display') == 'none') {
        $('#listaFiltrosId').toggle();
    };

    // Botão de mostrar filtros
    $('#filtrosToggleId').click(function () {
        if ($(window).width() > 576) {
            $('#resultadosId').toggleClass('col-md-12');
        }
        $('#listaFiltrosId').toggle();
    });
    // --- Filtros END

})