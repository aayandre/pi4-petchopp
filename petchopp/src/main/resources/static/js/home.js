$(function () {

    $.ajax({
        type: "get",
        url: window.location.origin + "/produtorest/produtoshome",
        data: 'data',
        dataType: 'json',
        success: function (response) {
            console.log(response);
            addProdutosHome(response)
        }
    });

    function addProdutosHome(homeProdutos) {

        let tipo = {};
        let produtos = [];


        $.each(homeProdutos, function (i, item) {
            tipo = item.tipo;

            $.each(item.produtos, function (i2, produto) {

                let produtoView = {
                    codigo: produto.codigo,
                    preco: produto.preco,
                    descricao: produto.descricao,
                    nome: produto.nome,
                    urlImagem: window.location.origin + '/uploads/' + produto.urlImagem
                };
                produtos.push(produtoView);
            });
            createProdutosHomeElements(tipo, produtos)
        });

    }

    function createProdutosHomeElements(tipo, produtos) {

        let templateDiv = $('#homeProdsTemplate').clone();
        templateDiv.attr('style', 'display: visible');

        templateDiv.find('#tipoTitulo').text(tipo.descricao)
        // templateDiv.find('#tipoTitulo').attr('style', 'display: visible');

        $.each(produtos, function (i, produto) {

            let template = $('#produtoTemplateHome').clone();

            template.attr('style', 'display: visible');
            template.find('.card-img-top').attr('src', produto.urlImagem);
            template.find('.card-title').text('R$ ' + parseFloat(produto.preco).toFixed(2));
            template.find('.card-text').text(produto.nome);
            template.find('.btn').attr('href', window.location.origin + '/produto/' + produto.codigo);

            template.appendTo(templateDiv);
        })

        templateDiv.appendTo('#produtosListaTiposId');
    }

});