/*Add property for string*/
String.prototype.replaceAll = function (arrayKeyValue) {
    var _this = this;
    arrayKeyValue.forEach(function (KeyValue) {
        _this = _this.replace(new RegExp('{{' + KeyValue.key + '}}', 'g'), KeyValue.value);
    });
    return _this;
};
/*function as document ready*/
$(function () {
    /*event for click css*/
    $("#method").click(function () {
        let divExtraProp = $(".extra-prop");
        if ($(this).prop("checked") === true) {
            divExtraProp.removeClass("css-mth");
        } else {
            divExtraProp.addClass("css-mth")
        }
    });

    /*check all link in list link*/
    $("#checkAllLinks").on("change", function () {
        if ($(this).prop("checked") === true) {
            $("input[name=chkLink]").prop("checked", true);
        } else {
            $("input[name=chkLink]").prop("checked", false);
        }
    });
    /*load newspaper for add link and edit link*/
    let newHideId = $("#newIdHide").val();
    if (newHideId === "") {
        loadNewToSelect(-1);
    } else {
        loadNewToSelect(parseInt(newHideId));
    }

    /*event for click modal add newspaper*/
    let $showModalAddNew = $('#showModalAddNew'),
        $addNewDialog = $('#confirm-addNew');

    $showModalAddNew.on('click', function () {
        $addNewDialog[0].showModal();
    });

    $('#cancel').on('click', function () {
        $addNewDialog[0].close();
    });
    /*Active tag input jquery*/
    $("#categoryCrawl").tagsInput();
});

/*Validate for add extra property*/
function validate(cssTag, cssClass) {
    let isTrue = false;
    const errorTag = $("#cssTagError");
    const errorClass = $("#cssClassError");
    if (cssClass === "") {
        errorClass.text("Class không được bỏ trống");
        isTrue = true;
    }
    if (cssTag === "") {
        errorTag.text("Tag không được bỏ trống");
        isTrue = true;
    }
    return !isTrue;
}

/*Button submit  for add extra*/
$(document).on("click", "#btnSubmit", function () {
    const cssTag = $("#cssTag").val();
    const cssClass = $("#cssClass").val();
    if (validate(cssTag, cssClass) === true) {
        let d = new Date();
        const cssId = d.getTime();
        let data = {extraId: cssId, tag: cssTag, clazz: cssClass};
        let val = data.extraId + "&" + data.tag + "&" + data.clazz;
        let tmp = extra_template(data, val);
        $("#table-prop").append(tmp);
        resetData();
    }
});

/*Template for table extra*/
function extra_template(params, value) {
    const {extraId, tag, clazz} = params;
    const val = value;
    const str = '<tr id="{{extraId}}">' +
        "    <td><input type='checkbox' name='extra' id='extra' value='{{value}}' class='chk-extra' checked></td>" +
        '    <td>{{tag}}</td>' +
        '    <td>{{clazz}}</td>' +
        '    <td><a class="btn btn-danger btn-xs btnDelExtra" id="{{extraId}}">Xóa</a></td>' +
        '</tr>';
    return str.replaceAll([
        {key: 'extraId', value: extraId},
        {key: 'tag', value: tag},
        {key: 'clazz', value: clazz},
        {key: 'value', value: val}
    ]);
}

/*button delete extra on add link click*/
$(document).on("click", ".btnDelExtra", function () {
    let extraId = $(this).attr("id");
    if (confirm("Bạn có chắc chắn muốn xóa thuộc tính này không?")) {
        $("#" + extraId).remove();
    }
});
/*button add link to db*/
$(document).on("click", ".btnAddLink", function () {
    /*Remove all error log*/
    $('span[class^="err"]').text("");
    const url = $("#url").val();
    let linkVal = $("#linkId").val();
    let linkId = 0;
    if (linkVal !== "") {
        linkId = parseInt(linkVal);
    }
    let channelId = $("#channelId").val();
    console.log(channelId);
    let xPath = $("#xPath").val();
    let active = -1;
    if($("#active").prop("checked") === true){
        active = 1;
    } else{
        active = 0;
    }
    console.log(active);
    /*const newspaper = $("#newspaper").val();*/
    let category = $("input[name=category]:checked").val();
    let cateCrawl = $("#categoryCrawl").val();
    $('input:checkbox.chk-cate').each(function () {
        if ($(this).prop("checked") === true) {
            categories.push($(this).val());
        }
    });
    let timeCrawl = parseInt($("#timeCrawl").val(), 0);
    let tagCrawl = $("#tagCrawl").val();
    const method = $("#method");
    let type = method.prop("checked") === true ? method.val() : 0;
    let extras = [];
    $('input:checkbox.chk-extra').each(function () {
        if ($(this).prop("checked") === true) {
            extras.push($(this).val());
        }
    });
    if (validate_link(url, channelId, tagCrawl, category, timeCrawl, type, extras) === true) {
        let data = {
            "linkId": linkId,
            "url": url,
            "category": category,
            "cateCrawl": cateCrawl,
            "timeCrawl": timeCrawl,
            "tagCrawl": tagCrawl,
            "type": type,
            "extras": extras,
            "xPath": xPath,
            "channelId": channelId,
            "active" : active
        };
        $.ajax({
            url: '/quan-tri-link/them-moi-link',
            type: "POST",
            contentType: "application/json;charset=utf-8",
            data: JSON.stringify(data),
            success: function (resp) {
                const obj = JSON.parse(resp);
                const err = obj.hasOwnProperty("err");
                if (err === true) {
                    $("#errorData").text(obj.err);
                }
                else {
                    console.log(obj);
                    location.href = window.history.back();
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
});

/*reset data after add extra*/
function resetData() {
    $("#cssClass").val("");
    $("#cssTag").val("");
}

/*function validate when add link*/
function validate_link(url, channelId, tagCrawl, category, timeCrawl, type, extras) {
    let isTrue = false;
    const errorUrl = $("#errUrl");
    /*const errorNewspaper = $("#errNewspaper");*/
    const errorCategories = $("#errCategories");
    const errorTimeCrawl = $("#errTimeCrawl");
    const errorTagCrawl = $("#errTagCrawl");
    const errorTypeCrawl = $("#errTypeCrawl");
    const errorChannelId = $("#errChannelId");
    if (url === "") {
        errorUrl.text("Url không được để trống");
        isTrue = true;
    }
    /*if (newspaper === "") {
        errorNewspaper.text("Báo không được để trống");
        isTrue = true;
    }*/
    if (typeof category === "undefined") {
        errorCategories.text("Chọn chuyên mục cho link");
        isTrue = true;
    }
    if (channelId <= 0) {
        errorChannelId.text("ChannelId phải lớn hơn 0");
        isTrue = true;
    }
    if (tagCrawl === "") {
        errorTagCrawl.text("Tag lấy tin không được bỏ trống!");
        isTrue = true;
    }
    if (timeCrawl <= 0) {
        errorTimeCrawl.text("Thời gian lấy tin phải lớn hơn 0");
        isTrue = true;
    }
    if (type !== 0) {
        if (extras.length <= 0) {
            errorTypeCrawl.text("Thông tin css không được bỏ trống");
            isTrue = true;
        }
    }

    return !isTrue;
}

/*function for button add newspaper*/
$(document).on("click", "#btnAddNew", function () {
    let newName = $("#newName").val();
    let errorNewName = $("#errNewName");
    let errorAlias = $("#errAliasName");
    let alias = $("#aliasName").val();
    errorNewName.text("");
    errorAlias.text("");
    if (newName === "") {
        errorNewName.text("Tên báo không được bỏ trống!");
    }
    else {
        if (alias === "") {
            errorAlias.text("Tên viết tắt báo không được bỏ trống!");
        } else {
            let data = {"newName": newName, "alias": alias};
            $.ajax({
                url: '/quan-tri-bao/them-bao',
                type: "POST",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(data),
                success: function (resp) {
                    let data = JSON.parse(resp);
                    $("#cancel").click();
                    loadNewToSelect(data.id);
                    resetNew();
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }
    }
});

/*function load data to select form add link*/
function loadNewToSelect(newId) {
    let $selectNew = $("#newspaper");
    $selectNew.empty();
    $selectNew.append('<option value="" selected>Chọn báo</option>');
    $.ajax({
        type: "GET",
        url: "/quan-tri-bao/api/danh-sach-bao",
        contentType: "application/json;charset=utf-8",
        success: function (resp) {
            resp.forEach(function (ele) {
                let option = template_option(ele);
                const newspaper = $("#newspaper");
                newspaper.append(option);
                if (newId >= 0) {
                    newspaper.val(newId);
                }
            });
        },
        error: function (error) {
            console.log(error);
        }
    });
}

/*function reset after add newspaper*/
function resetNew() {
    $("#newName").val("");
    $("#aliasName").val("");
    $("#newId").val("");
}

/*template for option of select newspaper*/
function template_option(params) {
    const {id, name, alias} = params;
    const str = '<option value="{{id}}">{{name}}</option>';
    return str.replaceAll([
        {key: 'id', value: id},
        {key: 'name', value: name}
    ]);
}

/*event for button delete link on list link*/
$(document).on("click", "#btnDelLink", function () {
    let linkIds = [];
    $('input:checkbox.chk-check-link').each(function () {
        if ($(this).prop("checked") === true) {
            linkIds.push($(this).val());
        }
    });

    if (linkIds.length <= 0) {
        alert("Chọn link mà bạn muốn xóa!");
    } else {
        if (confirm('Bạn chắc chắn muốn xóa ' + linkIds.length + " đã chọn không?")) {
            let data = {"linkIds": linkIds};
            $.ajax({
                type: "POST",
                url: "/quan-tri-link/xoa-link",
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(data),
                success: function (resp) {
                    console.log("location" + window.location);
                    location.href = window.location;
                },
                error: function (xhr) {
                    console.log(xhr);
                }
            });
        }
    }
});

$(document).on("click","#btnStatus", function () {
    let linkIds = [];
    $('input:checkbox.chk-check-link').each(function () {
        if ($(this).prop("checked") === true) {
            linkIds.push($(this).val());
        }
    });
    if(linkIds.length <=0){
        alert("Chọn link bạn muốn thay đổi trạng thái");
    } else{
        if("Bạn có chắc chắn muốn đổi trạng thái " + linkIds.length + " link đã chọn không?"){
            let data = {"linkIds" : linkIds};
            $.ajax({
                type : "POST",
                url : "/quan-tri-link/updateStatus",
                contentType : "application/json;charset=utf-8",
                data: JSON.stringify(data),
                success: function (resp) {
                    console.log("location" + window.location);
                    location.href = window.location;
                },
                error: function (xhr) {
                    console.log(xhr);
                }
            });
        }
    }
});