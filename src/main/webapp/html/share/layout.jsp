<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="<c:url value="/static/favicon.ico"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
    <link rel="stylesheet" href="<c:url value="/static/css/dialog.css"/>"/>
    <script src="<c:url value="/static/js/jquery.min.js"/>"></script>
    <script src="<c:url value="/static/js/jquery.tagsinput.js"/>"></script>
</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <tiles:insertAttribute name="menu"/>
        </div>
        <div class="top_nav">
            <tiles:insertAttribute name="header"/>
        </div>
        <div class="right_col">
            <div class="row">
                <tiles:insertAttribute name="body"/>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
<dialog id="confirm-addNew" class="site-dialog">
    <header class="dialog-header">
        <h1>Thêm báo</h1>
    </header>
    <div class="dialog-content">
        <div class="form-group">
            <label for="newName">Tên báo</label>
            <input type="text" class="form-control" id="newName"
                   placeholder="Nhập tên báo" name="newName">
            <span class="error" id="errNewName"></span>
        </div>
        <div class="form-group">
            <label for="aliasName">Tên viết tắt</label>
            <input type="text" class="form-control" id="aliasName"
                   placeholder="Nhập tên viết tắt" name="aliasName">
            <span class="error" id="errAliasName"></span>
        </div>
    </div>
    <div class="btn-group pull-right">
        <button class="btn btn-danger" id="btnAddNew">Thêm</button>
        <button class="btn btn-cancel" id="cancel">Đóng</button>
    </div>
</dialog>
<c:forEach var="js" items="${javascripts}">
    <script src="${js}"></script>
</c:forEach>
</body>
</html>
