<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="cate-title">
        <h2>Danh sách chuyên mục</h2>
        <hr>
    </div>

    <form action="<c:url value="/quan-tri-chuyen-muc/delete"/>" method="post">
        <div class="cate-action">
            <a href="<c:url value="/quan-tri-chuyen-muc/them-chuyen-muc"/>" class="btn btn-info">Thêm chuyên mục</a>
        </div>
        <table class="table table-bordered table-striped table-hover" id="table-cate">
            <tr>
                <th>Tên chuyên mục</th>
                <th>Bí danh</th>
                <th>Chuyên mục con</th>
                <th></th>
            </tr>
            <c:forEach var="cate" items="${categories}">
                <tr>
                    <td><a class="cate-title"
                           href="<c:url value="/quan-tri-chuyen-muc/${cate.alias}"/>">${cate.name}</a></td>
                    <td>${cate.alias}</td>
                    <td>
                        <c:choose>
                            <c:when test="${cate.categories.size() >0}">
                                <c:forEach var="child" items="${cate.categories}">
                                    <div><a class="cate-title"
                                            href="/quan-tri-chuyen-muc/${child.alias}">${child.name}</a></div>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </td>
                    <td><a href="/quan-tri-chuyen-muc/xoa-chuyen-muc/${cate.id}" class="btn btn-danger btn-xs"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa chuyên mục đã chọn không?')"><i
                            class="fa fa-remove"></i> Xóa</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>