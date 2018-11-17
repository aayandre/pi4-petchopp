$(document).ready(function () {
    $('#btn-details').click(function () {
        // console.log($('#order').find('#details-order'));
        // $('#order').find('#details-order').toggle(700);
        console.log($('#btn-details').parent().parent().parent().parent().find('#details-order'));
        $('#btn-details').parent().parent().parent().parent().find('#details-order').toggle(700);
    });

    $('#btn-details2').click(function () {
        // console.log($('#order').find('#details-order'));
        // $('#order').find('#details-order').toggle(700);
        console.log($('#btn-details2').parent().parent().parent().parent().find('#details-order'));
        $('#btn-details2').parent().parent().parent().parent().find('#details-order').toggle(700);
    });
});