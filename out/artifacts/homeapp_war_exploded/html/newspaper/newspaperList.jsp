<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set value="/quan-tri-bao" var="contextUrl"/>
<h1>Danh sách báo</h1>
<div class="ln_solid"></div>
<div class="new-action">
    <div class="col-md-8 col-sm-6 col-xs-12">
        <button class="btn btn-success btnShowAddNew"><i class="glyphicon glyphicon-pencil"></i> Thêm báo</button>
        <button class="btn btn-danger" id="btnDelNew"><i class="glyphicon glyphicon-remove-circle"></i> Xóa</button>
        <a href="${contextUrl}/danh-sach-bao" class="btn btn-success">Hiển thị tất cả</a>
    </div>
    <div class="col-md-4 col-sm-6 col-xs-12">
        <form id="frmSearchNew" action="<c:url value="/quan-tri-bao/danh-sach-bao"/>" method="get"
              class="form-horizontal">
            <input type="text" class="form-control" id="keyword" name="keyword" placeholder="Search" value="" title="">
            <button class="btn btn-info btn-search" type="submit" onclick="return validateSearch()"><i
                    class="fa fa-search"></i></button>
        </form>
    </div>
</div>
<div class="col-md-12 col-xs-12 col-sm-12">
    <div class="frm-add-new hidden">
        <form class="form-horizontal" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="newName">Tên báo:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="newName" placeholder="Enter newspaper's name" name="newName">
                    <span class="error" id="errNewName"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="alias">Tên viết tắt:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="alias" placeholder="Enter alias name" name="alias">
                    <span class="error" id="errNewAlias"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <a role="button" id="btnAddNew" class="btn btn-info">Thêm</a>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="new-content">
    <table class="table table-striped table-hover table-bordered">
        <tr>
            <th><input type="checkbox" name="checkAllNews" id="checkAllNews" title="Chọn tất cả báo"/></th>
            <th>Tên báo</th>
            <th>Tên viết tắt</th>
        </tr>
        <c:forEach var="news" items="${newspapers}">
            <tr>
                <td><input type="checkbox" name="chkNew" id="chkNew" class="chk-new" value="${news.id}"
                           title="${news.name}"></td>
                <td><a href="javascript:;">${news.name}</a></td>
                <td>${news.alias}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="custom-pagination text-center">
    <ul>
        <%--Pre button--%>
        <c:choose>
            <c:when test="${page == 0}">
                <c:url value="/quan-tri-bao/danh-sach-bao" var="prev"/>
            </c:when>
            <c:otherwise>
                <c:url value="/quan-tri-bao/danh-sach-bao" var="prev">
                    <c:param name="page" value="${page -1}"/>
                </c:url>
            </c:otherwise>
        </c:choose>
        <c:if test="${page >= 1}">
            <li><a class="btn-page" href="${prev}">Prev</a></li>
        </c:if>
        <c:choose>
            <c:when test="${(total/8) == 0}">
                <c:set var="num" value="${total/8 }"/>
            </c:when>
            <c:otherwise>
                <c:set var="num" value="${(total + 1)/8}"/>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="1" end="${num}" varStatus="loop">
            <c:url value="/quan-tri-bao/danh-sach-bao" var="url">
                <c:param name="page" value="${loop.index}"/>
            </c:url>
            <c:choose>
                <c:when test="${page == loop.index}">
                    <li><span class="btn-page-select">${loop.index}</span></li>
                </c:when>
                <c:otherwise>
                    <li class="arrow"><a class="btn-page" href="${url}">${loop.index}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/quan-tri-bao/danh-sach-bao" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 < num}">
            <li><a class="btn-page" href="${next}"><i class="next"></i>Next</a></li>
        </c:if>
    </ul>
</div>
