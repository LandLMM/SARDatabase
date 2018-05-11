$("#find-by-id").click(function() {
    var id = $("#device-id").val();

    $.get({
        url: "/unauth/device/" + id,
        success: function(response) {
            console.log(response);
        }
    });
});


$("#create").click(function() {
    var manufacturerName = $("#manufacturerName").val();
    var modelName = $("#modelName").val();
    var modelNumber = $("#modelNumber").val();
    var sarHeadValue = $("#sarHeadValue").val();
    var sarBodyWornValue = $("#sarBodyWornValue").val();

    var device = {
        manufacturerName: manufacturerName,
        modelName: modelName,
        modelNumber: modelNumber,
        sarHeadValue: sarHeadValue,
        sarBodyWornValue: sarBodyWornValue
    };

    $.post({
        url: "/auth/device",
        data: JSON.stringify(device),
        contentType: "application/json; charset=utf-8",
        success: function(response) {
            console.log(device);
        },
        error: function() {
            console.log("Device has not been created");
        }
    })
});