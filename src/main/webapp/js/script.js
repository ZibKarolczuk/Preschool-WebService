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

    $('#datepicker').datepicker({
        showAnim: "fold",
        dateFormat: 'dd.mm.yy'
    });

    function checkLenght(){
        return "Liczba znaków " + $('#sms')[0].value.length + "/160";
    }

    function messageComment(){
        $('p.sms_counter').text(checkLenght());
    }

    function chopMessage(){
        if ($('#sms')[0].value.length > 160) {
            var chopMessage = $('#sms')[0].value.substring(0, 160);
            $('#sms')[0].value = chopMessage;
        }
    }

    $('#sms').on("input", "", function () {
        chopMessage();
        messageComment();
    })

    $('[data-toggle=confirmation]').confirmation({
        rootSelector: '[data-toggle=confirmation]',
        // other options
    });

})