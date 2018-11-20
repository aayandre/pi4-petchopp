"use strict";

$(document).ready(function () {

    // Filtros START

    // Mostra os filtros se a janela estiver a X width
    if ($(window).width() > 576 && $("#listaFiltrosId").css("display") == "none") {
        $("#listaFiltrosId").toggle();
    };

    // Botão de mostrar filtros
    $("#filtrosToggleId").click(function () {
        if ($(window).width() > 576) {
            $("#resultadosId").toggleClass("col-md-12");
            $("#listaFiltrosId").toggle();
        } else {
            $("#listaFiltrosId").toggle(500);
            setTimeout(function () {
                $("#resultadosId").toggleClass("col-md-12");
            }, 650)
        }
    });

    // Lista filtros do server
    $.getJSON(window.location.origin + "/rest/filtros",
        function (data, textStatus, jqXHR) {
            console.log(data);
        }
    );


    // Filtros END

    // Procura START

    // Watcher no botão da barra de pesquisa
    $("#searchBtn").click(function () {
        $("#formProcuraId").submit();
    })

    // Função do submit no formulario de procura
    $("#formProcuraId").submit(function (event) {
        // cancela o submit normal do form
        event.preventDefault();

        // cria o caminho correto para o endpoint
        let getUrl = window.location.origin + "/rest/formulario";

        // pega o valor que o usuario digitou no input de pesquisa
        let procura = $("#formProcuraId").serializeArray()[0].value;

        // instanciando o array de filtros
        let filtrosArray = {
            tipos: [],
            tags: []
        };

        // popula o array de filtros com os valores de cada checkbox
        $.each($("#formFiltrosId #tiposId :input:checked"), function (i, item) {
            filtrosArray.tipos[i] = item.value;
        });
        $.each($("#formFiltrosId #tagsId :input:checked"), function (i, item) {
            filtrosArray.tags[i] = item.value;
        });

        // cria o objeto que será enviado para o controller
        let formularioSearch = {
            "procura": procura,
            "filtros": filtrosArray
        };

        // chamada do endpoint
        $.ajax({
            type: "post",
            url: getUrl,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(formularioSearch),
            success: function (response) {
                console.log(response);
            }
        });
    })
    // Procura END
});