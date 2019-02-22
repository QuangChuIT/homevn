<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="lst-link-title">
    <h3>Danh sách liên kết</h3>
</div>
<div class="link-action">
    <div class="col-md-9 col-sm-6 col-xs-12">
        <a href="<c:url value="/quan-tri-link/them-link"/>" class="btn btn-success"><i class="fa fa-plus"></i> Thêm link</a>
        <a class="btn btn-danger" id="btnDelLink"><i class="fa fa-remove"></i> Xóa link</a>
        <a href="<c:url value="/quan-tri-link/danh-sach"/>" class="btn btn-primary"><i class="fa fa-list"></i> Hiển thị
            tất cả</a>
        <a class="btn btn-info" id="btnStatus"><i class="fa fa-adn"></i> Đổi trạng thái</a>
    </div>
    <div class="col-md-3 col-sm-6 col-xs-12 search-container">
        <form action="<c:url value="/quan-tri-link/tim-kiem"/>" method="get" class="form-horizontal">
            <input type="text" placeholder="Search.." class="form-control" name="keyword">
            <button class="btn btn-danger" type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
</div>
<div class="lst-link-content">
    <c:choose>
        <c:when test="${links.size() > 0}">
            <table class="table table-striped table-hover table-bordered" id="table-links">
                <tr>
                    <th><input type="checkbox" name="chkAllLinks" id="checkAllLinks" title="Đánh dấu tất cả link"/></th>
                    <th>Liên kết</th>
                    <th>Chuyên mục</th>
                    <th>Phương thức</th>
                    <th>Tag lấy tin</th>
                    <th class="text-center">Trạng thái</th>
                </tr>
                <c:forEach var="link" items="${links}">
                    <tr>
                        <td>
                            <input type="checkbox" name="chkLink" class="chk-check-link" value="${link.linkId}"
                                   title="Chọn link ${link.url}">
                        </td>
                        <td>
                            <a href="<c:url value="/quan-tri-link/${link.category.alias}/${link.linkId}"/>">${link.url}</a>
                        </td>
                        <td>
                            <a href="<c:url value="/quan-tri-chuyen-muc/${link.category.alias}"/>">${link.category.name}</a>
                        </td>
                        <td>${link.property.type}</td>
                        <td>${link.property.tag}</td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${link.active == '1'}">
                                    <span>active</span>
                                </c:when>
                                <c:otherwise>
                                    <span> not active</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <div class="error">Không có kết qủa nào.</div>
        </c:otherwise>
    </c:choose>
</div>
<div class="custom-pagination text-center">
    <ul>
        <%--Pre button--%>
        <c:choose>
            <c:when test="${page == 0}">
                <c:url value="/quan-tri-link/danh-sach" var="prev"/>
            </c:when>
            <c:otherwise>
                <c:url value="/quan-tri-link/danh-sach" var="prev">
                    <c:param name="page" value="${page -1}"/>
                </c:url>
            </c:otherwise>
        </c:choose>
        <c:if test="${page >= 1}">
            <li><a class="btn-page" href="${prev}">Prev</a></li>
        </c:if>
        <c:choose>
            <c:when test="${(total/20) == 0}">
                <c:set var="num" value="${total/20 }"/>
            </c:when>
            <c:otherwise>
                <c:set var="num" value="${(total + 1)/20}"/>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="1" end="${num}" varStatus="loop">
            <c:url value="/quan-tri-link/danh-sach" var="url">
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
        <c:set var="temp" value="0"/>
        <c:url value="/quan-tri-link/danh-sach" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 < num}">
            <li><a class="btn-page" href="${next}"><i class="next"></i>Next</a></li>
        </c:if>
    </ul>
</div>

