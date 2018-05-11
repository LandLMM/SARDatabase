$("#create-button").click(function() {
    var brand = $("#brand").val();
    var speed = $("#speed").val();

    var colors = [];
    var $colors = $("#selected-colors .alert:not(#color-template) strong");
    for(var i = 0; i < $colors.length; i++) {
        colors.push($($colors[i]).text());
    }

    var car = {
        brand: brand,
        speed: speed,
        colors: colors
    };

    $.post({
        url: "/unauth/car",
        data: JSON.stringify(car),
        contentType: "application/json; charset=utf-8",
        success: function(response) {
            clearValidationErrors();
            fillTable();
        },
        error: function(xhr) {
            handleValidationError(xhr.responseJSON);
        }
    })
    return false;
});

$("#update-button").click(function() {
    var brand = $("#brand").val();
    var speed = $("#speed").val();
    // $("#update-button") to to samo, co $(this), bo przycisk z id update-button został kliknięty
    var id = $("#update-button").data("car-id");

    var car = {
        brand: brand,
        speed: speed
    };

    $.ajax({
        url: "/unauth/car/" + id,
        method: "PUT",
        data: JSON.stringify(car),
        contentType: "application/json; charset=utf-8",
        success: function(response) {
            resetForm();
            clearValidationErrors();
            fillTable();
        },
        error: function(xhr) {
            handleValidationError(xhr.responseJSON);
        }
    })
    return false;
});

$("#cancel-button").click(function() {
    resetForm();
    return false;
});

$("#add-color").click(function() {
    var color = $("#colors").val();
    addColorElement(color);
    return false;
});

function addColorElement(color) {
    var $color = $("#color-template").clone();
    $color.removeAttr("id")
        .removeClass("d-none");
    $color.find("strong").text(color);
    $("#selected-colors").append($color);
}


function resetForm() {
    $("#create-button").show();
    $("#update-button").hide();
    $("#cancel-button").hide();
    $("#speed").val("");
    $("#brand").val("");
}

function clearValidationErrors() {
    $(".form-control").removeClass("is-invalid")
        .removeClass("is-valid");
}

function handleValidationError(fieldValidationErrors) {
    clearValidationErrors();
    for(var i = 0; i < fieldValidationErrors.length; i++) {
        var fieldValidationError = fieldValidationErrors[i];
        var $field = $("#" + fieldValidationError.field);
        $field.addClass("is-invalid");
        $field.siblings(".invalid-feedback").text(fieldValidationError.message);
    }
    $(".form-control:not(.is-invalid)").addClass("is-valid");
}

function fillTable() {
    $("#car-table tbody tr:not(#row-template)").remove();
    $.get({
        url: "/unauth/car",
        success: function(response) {
            $rowTemplate = $("#row-template");
            for(var i = 0; i < response.length; i++) {
                var car = response[i];
                var $row = $rowTemplate.clone();
                $row.removeAttr("id")
                    .removeClass("d-none");

                var $carId = $row.find(".car-id");
                $carId.text(car.id);
                var hrefAttr = $carId.attr("href");
                $carId.attr("href", hrefAttr + "?id=" + car.id);

                $row.find(".car-edit").data("car", car);
                $row.find(".car-edit").click(function() {
                    // $(this) - element, który został kliknięty
                    var car = $(this).data("car");
                    $("#brand").val(car.brand);
                    $("#speed").val(car.speed);
                    $("#selected-colors .alert:not(#color-template)").remove();
                    for(var i = 0; i < car.colors.length; i++) {
                        var color = car.colors[i];
                        addColorElement(color);
                    }
                    $("#create-button").hide();
                    $("#update-button").show();
                    $("#cancel-button").show();
                    $("#update-button").data("car-id", car.id);
                });

                $row.find(".car-delete").data("car-id", car.id);
                $row.find(".car-delete").click(function() {
                    var id = $(this).data("car-id");
                    showDeleteModal(id);
                });

                $row.find(".car-brand").text(car.brand);
                $row.find(".car-speed").text(car.speed);
                $("#car-table tbody").append($row);
            }
        }
    })
}

function showDeleteModal(id) {
    $("#delete-modal").modal("show");

    $("#delete-confirm-button").off("click");
    $("#delete-confirm-button").click(function() {
        $.ajax({
            url: "/unauth/car/" + id,
            method: "DELETE",
            success: function(car) {
                console.log("Usunięto obiekt: ", car);
                fillTable();
                resetForm();
                $("#delete-modal").modal("hide");
            }
        });
    });
}

fillTable();
