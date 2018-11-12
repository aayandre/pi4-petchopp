$(document).ready(function () {
  $("#nr-cartao-cred").mask('0000 0000 0000 0000');
  $("#venc-cartao-cred").mask('00/00');
  $("#nr-cartao-deb").mask('0000 0000 0000 0000');
  $("#venc-cartao-deb").mask('00/00');

  $('#typePayment-cred').click(function () {
    if ($('#typePayment-cred').is(':checked')) {

      $("#pag-info-cred").removeClass("box-pag-display");

      if (!$("#pag-info-deb").hasClass('box-pag-display')) {
        $("#pag-info-deb").addClass("box-pag-display");
      }

      if (!$("#pag-info-boleto").hasClass('box-pag-display')) {
        $("#pag-info-boleto").addClass("box-pag-display");
      }
    }
  });

  $('#typePayment-deb').click(function () {
    if ($('#typePayment-deb').is(':checked')) {

      $("#pag-info-deb").removeClass("box-pag-display");

      if (!$("#pag-info-cred").hasClass('box-pag-display')) {
        $("#pag-info-cred").addClass("box-pag-display");
      }

      if (!$("#pag-info-boleto").hasClass('box-pag-display')) {
        $("#pag-info-boleto").addClass("box-pag-display");
      }
    }
  });

  $('#typePayment-boleto').click(function () {
    if ($('#typePayment-boleto').is(':checked')) {

      $("#pag-info-boleto").removeClass("box-pag-display");

      if (!$("#pag-info-cred").hasClass('box-pag-display')) {
        $("#pag-info-cred").addClass("box-pag-display");
      }

      if (!$("#pag-info-deb").hasClass('box-pag-display')) {
        $("#pag-info-deb").addClass("box-pag-display");
      }
    }
  });
});

