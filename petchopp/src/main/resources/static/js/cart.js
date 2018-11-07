$(document).ready(function() {

  function calculaSubTotal() {
    var total = 0.00;
    // Pega todos os elementos da quantidade e
    // multiplica pelo valor individual
    $('input[name^=quantity]').each(function() {
      // Valor individual do produto
      let produtovalor = $(this).parent().parent().parent().find('#produtoPrecoId');
      let valor = parseFloat(produtovalor.attr('value'));
      // let quantidadeHidden = $(this).closest('#quantityHidden');
      // console.log(quantidadeHidden);

      // Multiplicação
      valor *= $(this).val();
      total += valor;
      console.log(total);

      // Elemento do subtotal sendo escrito
      let produtoSubtotal = $(this).parent().parent().parent().find('#subTotalId');
      produtoSubtotal.val(valor.toFixed(2));

      // quantidadeHidden.val($(this).val());
    })

    // Elemento do total sendo escrito
    let carrinhoTotal = $("#carrinhoTotalId")
    let carrinhoTotalHidden = $("#carrinhoTotalHidden")

    carrinhoTotalHidden.val(total.toFixed(4));
    carrinhoTotal.text('R$ ' + total.toFixed(2));

  };

  calculaSubTotal();


  $('.quantity-right-plus').click(function(e) {

    // Stop acting like a button
    e.preventDefault();

    // Get the field element
    let quantidadeInput = $(this).parent().parent().parent().find("#quantity");
    let quantidadeHidden = $(this).parent().parent().parent().find("#quantityHidden");
    // Parse into int
    let quantity = parseInt(quantidadeInput.val());
    // Set the value
    quantidadeInput.val(quantity + 1);
    quantidadeHidden.val(quantity + 1);
    calculaSubTotal();

  });

  $('.quantity-left-minus').click(function(e) {
    // Stop acting like a button
    e.preventDefault();
    // Get the field element
    let quantidadeInput = $(this).parent().parent().parent().find("#quantity");
    let quantidadeHidden = $(this).parent().parent().parent().find("#quantityHidden");
    // Parse into int
    let quantity = parseInt(quantidadeInput.val());

    // Increment
    if (quantity > 1) {
      quantidadeInput.val(quantity - 1);
      quantidadeHidden.val(quantity - 1);
      calculaSubTotal();
    }

  });

});
