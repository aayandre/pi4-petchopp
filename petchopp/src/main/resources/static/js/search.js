"use strict";

$(document).ready(function () {

    // Filtros START
    if ($(window).width() > 700 && $("#listaFiltrosId").css("display") == "none") {
        $("#listaFiltrosId").toggle();
        // $("#resultadosId").toggleClass("col-md-12");

    }

    $("#filtrosToggleId").click(function () {
        if ($(window).width() > 700) {
            $("#resultadosId").toggleClass("col-md-12");
            $("#listaFiltrosId").toggle();
        } else {
            $("#listaFiltrosId").toggle(500);
            setTimeout(function () {
                $("#resultadosId").toggleClass("col-md-12");
            }, 650)
        }


    })
    // Filtros END

    // Procura START
    $("#searchBtn").click(function () {
        $("#formProcuraId").submit();
    })

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


    // $("#formProcuraId").on("submit", enviarForm(event));

    // function enviarForm(e) {

    //     let getUrl = window.location.origin;
    //     console.log(getUrl);

    //     let procura = $("#formProcuraId").serialize();
    //     let filtros = $("#formFiltrosId").serialize();
    //     let resultado = {
    //         procura: procura,
    //         filtros: filtros
    //     };
    //     console.log(resultado);

    //     $.ajax({
    //         type: "post",
    //         url: getUrl + "/rest/formulario",
    //         dataType: "json",
    //         data: resultado,
    //         success: function (response) {
    //             console.log(response);
    //         }
    //     });
    // }


    // $("#formProcuraId", "#formFiltrosId").on("submit", function (event) {
    //     event.preventDefault();
    //     console.log($(this).serialize());
    // });

});