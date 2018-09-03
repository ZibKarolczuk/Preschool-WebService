$(document).ready(function () {

    // DELETE ELEMENT REQUEST CONFIRM OPTION

    $('td input#deleteChild').on("click", function () {
        confirmDelete("http://localhost:8080/user/");
    });

    $('td input#deleteUser').on("click", function () {
        confirmDelete("http://localhost:8080/admin/user/");
    });

    $('td input#deleteGroup').on("click", function () {
        confirmDelete("http://localhost:8080/admin/group/");
    });

    function confirmDelete(address) {
        var txt;
        var r = confirm("Kontynuować proces usunięcia? Dane zostaną bezpowrotnie utracone!");
        if (r == false) {
            window.location.href = address;
        }
    }

    $('[data-toggle=confirmation]').confirmation({
        rootSelector: '[data-toggle=confirmation]',
        // other options
    });

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