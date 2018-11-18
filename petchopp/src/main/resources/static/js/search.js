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
    function enviarForm(e) {

        let getUrl = window.location.origin;
        console.log(getUrl);

        let procura = $("#formProcuraId").serialize();
        let filtros = $("#formFiltrosId").serialize();
        let resultado = {
            procura: procura,
            filtros: filtros
        };
        console.log(resultado);

        $.ajax({
            type: "get",
            url: getUrl + "/rest/formulario",
            data: resultado,
            success: function (response) {
                console.log(response);
            }
        });
    }

    $("#formProcuraId").on("submit", enviarForm(event));



    // $("#formProcuraId", "#formFiltrosId").on("submit", function (event) {
    //     event.preventDefault();
    //     console.log($(this).serialize());
    // });




});