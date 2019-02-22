/*Keep scroll page when refresh*/
$(window).scroll(function () {
    sessionStorage.scrollTop = $(this).scrollTop();
});

$(document).ready(function () {
    if (sessionStorage.scrollTop !== "undefined") {
        $(window).scrollTop(sessionStorage.scrollTop);
    }
});

$(function () {
    /*event for click modal add newspaper*/
    let $showModalAddNew = $('#showModalAddNew'),
        $addNewDialog = $('#confirm-addNew');

    $showModalAddNew.on('click', function () {
        $addNewDialog[0].showModal();
    });

    $('#cancel').on('click', function () {
        $addNewDialog[0].close();
    });
});