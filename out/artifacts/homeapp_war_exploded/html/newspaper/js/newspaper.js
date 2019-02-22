$(function () {
    $("#checkAllNews").on("change", function () {
        if ($(this).prop("checked") === true) {
            $("input[name=chkNew]").prop("checked", true);
        } else {
            $("input[name=chkNew]").prop("checked", false);
        }
    });
    $(".btnShowAddNew").on("click",function () {
        let frm = $(".frm-add-new");
        if(frm.hasClass("hidden") === true){
            frm.removeClass("hidden");
        } else{
            frm.addClass("hidden");
        }
    });
});
$(document).on("click", "#btnDelNew", function () {
    let newIds = [];
    $('input[name=chkNew]').each(function () {
        if ($(this).prop("checked") === true) {
            newIds.push($(this).val());
        }
    });
    if (newIds.length <= 0) {
        alert("Chọn báo bạn muốn xóa!");
    } else {
        let data = {"newIds": newIds};
        if (confirm("Bạn chắc chắn muốn xóa" + newIds.length + " báo đã chọn không?")) {
            $.ajax({
                type: "POST",
                url: "/quan-tri-bao/xoa-bao",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(data),
                success: function (resp) {
                    alert("Xóa báo thành công!");
                    location.href = window.location;
                },
                error: function (e) {
                    console.log(e);
                }
            });
        }
    }
});

function validateSearch() {
    let keyword = $("#keyword").val();
    if (keyword === '') {
        alert("Nhập từ khóa tìm kiếm");
        return false;
    }
    return true;
}

$(document).on("click", "#btnAddNew", function () {
    let newName = $("#newName").val();
    let alias = $("#alias").val();

    if (validate(newName, alias)) {
        let data = {"newName": newName, "alias": alias};
        $.ajax({
            type: "POST",
            url: "/quan-tri-bao/them-bao",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (resp) {
                location.href = window.location;
            },
            error: function (e) {
                console.log(e);
            }
        });
    }
});

function validate(newName, alias) {
    let b = false;
    let errName = $("#errNewName");
    let errAlias = $("#errNewAlias");
    $('span[id^="err"]').text("");
    if (newName === '') {
        errName.text("Tên báo không được bỏ trống!");
        b = true;
    }
    if (alias === '') {
        errAlias.text("Tên viết tắt không được bỏ trống");
        b = true;
    }
    return !b;
}