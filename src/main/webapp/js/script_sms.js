$(document).ready(function () {

    function checkLenght(){
        return "Liczba znaków " + $('textarea')[0].value.length + "/160";
    }

    function messageComment(){
        $('p.sms_counter').text(checkLenght());
    }

    function chopMessage(){
        if ($('textarea')[0].value.length > 160) {
            var chopMessage = $('textarea')[0].value.substring(0, 160);
            $('textarea')[0].value = chopMessage;
        }
    }

    $('div.sms').on("input", "", function () {
        chopMessage();
        messageComment();
    })





    // function deleteUser(idUser) {
    //     sendGenericRequest("http://localhost:8080/admin/user/delete/"+idUser,
    //         "POST",
    //         undefined,
    //         function (books) {
    //             console.log("Działa! :)");
    //             var renderingPointIDs = $("#test-test");
    //             renderIdList(renderingPointIDs, books);
    //         });
    // }
    //
    // function sendPostDeleteUser() {
    //     $.ajax({
    //         type: "POST",
    //         url: "http://localhost:8080/admin/user/delete/"+idUser,
    //         data: {json:JSON.stringify(data)  },
    //         success: success
    //     });
    // }

    // JACKSON GENERIC

    // function sendGenericRequest(url, method, data, successHandler) {
    //     $.ajax({
    //         url: url,
    //         type: method,
    //         data: data === undefined ? "" : JSON.stringify(data),
    //         dataType: "json",
    //         contentType: "application/json; charset=utf-8",
    //     }).done(function (data) {
    //         if (successHandler !== undefined) {
    //             successHandler(data);
    //         }
    //     }).fail(function (xhr, status, errorThrown) {
    //         console.log("BŁĄD!", xhr, status, errorThrown);
    //     });
    // }

})