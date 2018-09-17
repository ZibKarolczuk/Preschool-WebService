$(document).ready(function () {

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
        console.log("test");
        chopMessage();
        messageComment();
    })

})