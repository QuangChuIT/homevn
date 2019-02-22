"use strict";

$('#tags').tagsInput();

function formOnSubmit() {
    if ($("input:checked").length <= 0) {
        alert("Chọn chuyên mục bạn muốn xóa");
        return false;
    }
    else {
        return confirm("Bạn có chắc chắn muốn xóa các chuyên mục đã chọn?");
    }
}

$("#checkAll").on("change", function () {
    if ($(this).prop("checked") == true) {
        $("input[name=ids]").prop("checked", true);
    } else {
        $("input[name=ids]").prop("checked", false);
    }
});