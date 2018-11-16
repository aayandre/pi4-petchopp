$(document).ready(function () {
    var sideToggle = false;
    $('#sidebarCollapse').on('click', function () {
        // open or close navbar
        $('#sidebar').toggle(650);
        // if(!sideToggle) {
        //     $('#sidebar').hide( 600 , function() {
        //         sideToggle = true;
        //     });
        // } else {
        //     $('#sidebar').show( 600 , function() {
        //         sideToggle = false;
        //     });
        // }
        // close dropdowns
        $('.collapse.in').toggleClass('in');
        // and also adjust aria-expanded attributes we use for the open/closed arrows
        // in our CSS
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });

});

$('#myCarousel').carousel({
    interval: 4000
});