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
    $.ajax({
        type: "get",
        url: window.location.origin + "/rest/filtros",
        data: "data",
        dataType: "json",
        success: function (response) {
            addTipos(response.tipos);
            addTags(response.tags);
        }
    });

    // Popula os tipos
    function addTipos(tipos) {

        $.each(tipos, function (i, item) {

            let label = $("<label/>", {
                class: "form-check"
            });

            let input = $("<input/>", {
                name: "check",
                class: "form-check-input",
                type: "checkbox"
            });

            let span = $("<span/>", {
                class: "form-check-label"
            });

            // add ao input o id do tipo atual 
            input.attr("id", item.idTipo);
            label.append(input);

            // add ao span a descricao do tipo atual
            span.text(item.descricao);
            label.append(span);

            // add a label a div de tipos
            $("#tiposId").append(label);
        });
    };

    // Popula tags 
    function addTags(tags) {

        $.each(tags, function (i, item) {

            let label = $("<label/>", {
                class: "form-check"
            });

            let input = $("<input/>", {
                name: "check",
                class: "form-check-input",
                type: "checkbox"
            });

            let span = $("<span/>", {
                class: "form-check-label"
            });

            // add ao input o id da tag atual 
            input.attr("id", item.idTags);
            label.append(input);

            // add ao span o nome da tag atual
            span.text(item.nome);
            label.append(span);

            // add a label a div de tags
            $("#tagsId").append(label);
        });
    }


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
            let tipo = {
                idTipo: "",
                nome: "",
                descricao: ""
            };
            tipo.idTipo = item.id;
            filtrosArray.tipos[i] = tipo;
        });
        $.each($("#formFiltrosId #tagsId :input:checked"), function (i, item) {
            let tag = {
                idTag: "",
                nome: "",
                idtipo: ""
            };
            tag.idTag = item.id;
            filtrosArray.tags[i] = tag;
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