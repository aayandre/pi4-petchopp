'use strict';

$(document).ready(function () {

    let caminho = window.location.origin + '/produtorest/tipo/' + $('#tipo-idtipo').val();

    $.ajax({
        type: "get",
        url: caminho,
        dataType: "json",
        contentType: "application/json",
        data: "data",
        success: function (response) {
            listaProdutos(response)
        }
    });

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