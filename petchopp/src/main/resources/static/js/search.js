'use strict';

// Lista filtros do server
$.ajax({
    type: 'get',
    url: window.location.origin + '/rest/filtros',
    data: 'data',
    dataType: 'json',
    success: function (response) {
        addTipos(response.tipos);
        addTags(response.tags);
    }
});

// Popula os tipos
function addTipos(tipos) {

    $.each(tipos, function (i, item) {

        let label = $('<label/>', {
            class: 'form-check'
        });

        let input = $('<input/>', {
            name: 'check',
            class: 'form-check-input',
            type: 'checkbox'
        });

        let span = $('<span/>', {
            class: 'form-check-label'
        });

        // add ao input o id do tipo atual 
        input.attr('id', item.idTipo);
        label.append(input);

        // add ao span a descricao do tipo atual
        span.text(item.descricao);
        label.append(span);

        // add a label a div de tipos
        $('#tiposId').append(label);
    });

    $('#tiposId').find('.form-check-input').on('change', function () {
        $('#formProcuraId').submit();
    });
};

// Popula tags 
function addTags(tags) {

    $.each(tags, function (i, item) {

        let label = $('<label/>', {
            class: 'form-check'
        });

        let input = $('<input/>', {
            name: 'check',
            class: 'form-check-input',
            type: 'checkbox'
        });

        let span = $('<span/>', {
            class: 'form-check-label'
        });

        // add ao input o id da tag atual 
        input.attr('id', item.idTags);
        input.val(item.nome)
        label.append(input);

        // add ao span o nome da tag atual
        span.text(item.nomeView);
        label.append(span);

        // add a label a div de tags
        $('#tagsId').append(label);
    });

    $('#tagsId').find('.form-check-input').on('change', function () {
        $('#formProcuraId').submit();
    });
}


$(document).ready(function () {

    // Altera o valor do span ao carregar
    $('#valor-max').text(parseFloat($('#preco-max').val()).toFixed(2));

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

    // Watcher de alterações do preço
    $('#preco-min').on('input change', function () {
        let valorMin = parseFloat($(this).val()).toFixed(2)
        $('#valor-min').text(valorMin)
    })
    $('#preco-max').on('input change', function () {
        let valorMin = parseFloat($(this).val()).toFixed(2)
        $('#valor-max').text(valorMin)
    })

    // --- Filtros END

    // --- Procura START

    // Watcher no botão da barra de pesquisa
    $('#searchBtn').click(function () {
        $('#formProcuraId').submit();
    });

    // Função do submit no formulario de procura
    $('#formProcuraId').submit(function (event) {
        // cancela o submit normal do form
        event.preventDefault();

        // cria o caminho correto para o endpoint
        let getUrl = window.location.origin + '/produtorest/formulario';

        // pega o valor que o usuario digitou no input de pesquisa
        let procura = $('#formProcuraId').serializeArray()[0].value;

        // instanciando o array de filtros
        let filtrosArray = {
            tipos: [],
            tags: []
        };

        // popula o array de filtros com os valores de cada checkbox
        $.each($('#formFiltrosId #tiposId :input:checked'), function (i, item) {
            let tipo = {
                idTipo: '',
                nome: '',
                descricao: ''
            };
            tipo.idTipo = item.id;
            filtrosArray.tipos[i] = tipo;
        });
        $.each($('#formFiltrosId #tagsId :input:checked'), function (i, item) {
            let tag = {
                idTag: 1,
                nome: '',
                idtipo: 1
            };
            tag.idTag = item.id;
            tag.nome = $(item).val();
            filtrosArray.tags.push(tag);
        });

        // cria o objeto que será enviado para o controller
        let formularioSearch = {
            procura: procura,
            filtros: filtrosArray
        };

        // chamada do endpoint
        $.ajax({
            type: 'post',
            url: getUrl,
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formularioSearch),
            success: function (response) {
                listaProdutos(response);
            }
        });

        // Construcao dos produtos
        var produtos = [];

        function listaProdutos(lista) {

            $.each(lista, function (i, produto) {
                let produtoView = {
                    codigo: produto.codigo,
                    preco: produto.preco,
                    descricao: produto.descricao,
                    nome: produto.nome,
                    urlImagem: window.location.origin + '/uploads/' + produto.urlImagem
                };
                produtos[i] = produtoView;
            });
            createProdutoElement(produtos)
        }

        function createProdutoElement(lista) {

            $('#produtosId').html('');

            $.each(lista, function (i, produto) {

                let template = $('#produtoTemplate').clone();

                template.attr('style', 'display: visible');
                template.find('.card-img-top').attr('src', produto.urlImagem);
                template.find('.card-title').text('R$ ' + parseFloat(produto.preco).toFixed(2));
                template.find('.card-text').text(produto.nome);
                template.find('.btn').attr('href', window.location.origin + '/produto/' + produto.codigo);

                template.appendTo('#produtosId');
            })


        }
    })
    // --- Procura END
});