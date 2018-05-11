function checkAuthenticationStatus() {
    $.get({
        url: "/auth/login",
        success: function () {
            $("#login-menu-item").hide();
        },
        error: function () {
            $("#logout-menu-item").hide();
        }
    });
}

$("#login-button").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();

    $.post({
        url: "/auth/login",
        // dołączamy nagłówek z danymi autoryzacji
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(username + ":" + password));
        },
        success: function () {
            $("#login-menu-item").hide();
            $("#logout-menu-item").show();
        },
        error: function () {
            alert("Incorrect Login!");
        }
    });
});


$("#logout-button").click(function () {
    $.post({
        url: "/auth/login",
        // dołączamy nagłówek z błędnymi danymi autoryzacji - pusta nazwa użytkownika i hasło (wylogowujemy się)
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(":"));
        },
        success: function () {
            alert("Incorrect Logout");
        },
        // oczekujemy błędu - braku autentykacji
        error: function () {
            $("#login-menu-item").show();
            $("#logout-menu-item").hide();
        }
    });
});

checkAuthenticationStatus();