$("#output").html("<b>tekst</b>");

$("#login").click(function() {
    console.log("Button pressed");

    var username = $("#username").val();
    var password = $("#password").val();

    console.log(username, password);

    $.post({
        url: "/auth/login",
        beforeSend: function (xhr) {
           xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + password));
        },
        success: function(response) {
           console.log("Login Successful!", response);
        },
        error: function() {
           console.log("Login Failed!");
        }
    })
});

$("#get-credentials").click(function() {
    $.get({
        url: "/unauth/credentials",
        success: function(response) {
            console.log(response);
        }
    });
});