var url = new URL(window.location.href);
var id = url.searchParams.get("id");
console.log(id);

$.get({
    url: "/unauth/car/" + id,
    success: function(response) {
        $(".device-id").text(response.id);
        $(".device-manufacturer").text(response.manufacturer_name);
        $(".device-model-name").text(response.model_name);
        $(".device-model-number").text(response.model_number);
        $(".sar_head_value").text(response.sar_head_value);
        $(".sar_body_worn_value").text(response.sar_body_worn_value);
    }
});