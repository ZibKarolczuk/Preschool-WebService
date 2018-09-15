$(document).ready(function () {

    // DELETE ELEMENT REQUEST CONFIRM OPTION

    $('td input#deleteChild').on("click", function () {
        confirmDelete("/user/");
    });

    $('td input#deleteUser').on("click", function () {
        confirmDelete("/admin/user/");
    });

    $('td input#deleteGroup').on("click", function () {
        confirmDelete("/admin/group/");
    });

    function getContextPath() {
        return $('#contextPathHolder').attr('data-contextPath');
    }

    function confirmDelete(address) {
        var txt;
        var r = confirm("Kontynuować proces usunięcia? Dane zostaną bezpowrotnie utracone!");
        if (r == false) {
            window.location.pathname = getContextPath().concat(address);
        }
    }


    // var dateShort = $('#datepicker').datepicker().val().substring(0, 10);

    $('#datepicker').datepicker({
        showAnim: "fold",
        dateFormat: 'dd.mm.yy'
    });

    // $('#datepicker').datepicker({
    //     startDay: 1,
    //     showAnim: "fold",
    //     dateFormat: "yy-mm-dd",
    //     // altField: ".date_alternate",
    //     // altFormat: "yy-mm-dd"
    // });


    $('[data-toggle=confirmation]').confirmation({
        rootSelector: '[data-toggle=confirmation]',
        // other options
    });

})