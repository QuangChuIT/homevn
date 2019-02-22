<%@ page import="com.home.app.model.Link" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Link link = (Link) request.getAttribute("link");
%>
<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_content">
            <form class="form-horizontal form-label-left">
                <span class="section">Thông Tin Chung</span>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="error" id="errorData">
                        </div>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="hidden" name="linkId" value="${link.linkId}" id="linkId">
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="url">Url <span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="url" class="form-control col-md-7 col-xs-12" data-validate-length-range="6"
                               data-validate-words="2" value="${link.url}" name="url"
                               placeholder="www.website.com" type="url">
                        <span class="error" id="errUrl"></span>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Mục lấy tin:<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <%
                            StringBuilder cateCrawl = new StringBuilder();
                            List<String> cateCrawls = link.getCategoriesCrawl();
                            if (cateCrawls != null) {
                                for (String item : cateCrawls) {
                                    cateCrawl.append(item);
                                    cateCrawl.append(",");
                                }
                            }
                        %>
                        <input id="categoryCrawl" name="categoryCrawl" type="text" data-ro
                               class="tags form-control col-md-7 col-xs-12"
                               value="<%=cateCrawl.toString()%>" title=""/>
                    </div>
                </div>
                <%-- <div class="item form-group">
                     <label class="control-label col-md-3 col-sm-3 col-xs-12" for="newspaper">Tên báo <span
                             class="required">*</span>
                     </label>
                     <div class="col-md-6 col-sm-6 col-xs-12">
                         <div class="float-left">
                             <input type="hidden" id="newIdHide" value="${link.newspaper.id}"/>
                             <select class="form-control" name="newspaper" id="newspaper">
                             </select>
                         </div>
                         <div class="float-right">
                             <a class="btn btn-success" id="showModalAddNew"> + </a>
                         </div>
                         <span class="error" id="errNewspaper"></span>
                     </div>
                 </div>--%>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Chuyên mục<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="block-cate">
                            <ul class="list-cate">
                                <c:forEach var="cate" items="${categories}">
                                    <li class="col-md-6">
                                        <c:choose>
                                            <c:when test="${cate.id == link.category.id}">
                                                <input type="radio" checked name="category" class="rdo-cate"
                                                       value="${cate.id}"
                                                       title=""> ${cate.name}
                                            </c:when>
                                            <c:otherwise>
                                                <input type="radio" name="category" class="rdo-cate"
                                                       value="${cate.id}"
                                                       title=""> ${cate.name}
                                            </c:otherwise>
                                        </c:choose>

                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <span class="error" id="errCategories"></span>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="timeCrawl">Thời gian<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="number" value="${link.timeCrawl}" id="timeCrawl" name="timeCrawl"
                               data-validate-minmax="0,100" class="form-control col-md-7 col-xs-12">
                        <span class="error" id="errTimeCrawl"></span>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="xPath">Path<span
                            class="required"></span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <textarea rows="17" class="form-control col-md-7 col-xs-12" name="xPath"
                                  id="xPath">${link.property.xPath}</textarea>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="channelId">ChannelId<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="number" value="${link.channelId}" id="channelId" name="channelId"
                               data-validate-minmax="0,100" class="form-control col-md-7 col-xs-12">
                        <span class="error" id="errChannelId"></span>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="active">Active <span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <c:choose>
                            <c:when test="${link.active == 1}">
                                <input type="checkbox" id="active" name="active"
                                       checked>
                                <span class="error" id="errActive"></span>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" id="active" name="active">
                                <span class="error" id="errActive"></span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <span class="section">Cấu hình</span>

                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagCrawl">Tag lấy tin<span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="tagCrawl" name="tagCrawl" value="${link.property.tag}"
                               placeholder="Mặc định thẻ p" class="form-control col-md-7 col-xs-12">
                        <span class="error" id="errTagCrawl"></span>
                    </div>
                </div>
                <div class="item form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="method">Phương thức <span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12 check-css">
                        <c:choose>
                            <c:when test="${link.property.type == 'css'}">
                                <input id="method" checked type="checkbox" value="1" name="method"> CSS<br/>
                            </c:when>
                            <c:otherwise>
                                <input id="method" type="checkbox" value="1" name="method"> CSS<br/>
                            </c:otherwise>
                        </c:choose>
                        <span class="error" id="errTypeCrawl"></span>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${link.property.type == 'css'}">
                        <c:set var="isCss" value=""/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="isCss" value="css-mth"/>
                    </c:otherwise>
                </c:choose>
                <div class="item form-group extra-prop ${isCss}">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12 frm-css">Thông số <span
                            class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="form-add-css">
                            <div class="form-group">
                                <label for="cssTag">Tag:</label>
                                <input type="text" class="form-control" id="cssTag"
                                       placeholder="Enter css tag" name="cssTag">
                                <span class="error" id="cssTagError"></span>
                            </div>
                            <div class="form-group">
                                <label for="cssClass">Class:</label>
                                <input type="text" class="form-control" id="cssClass" placeholder="Enter css class"
                                       name="cssClass">
                                <span class="error" id="cssClassError"></span>
                            </div>
                            <a class="btn btn-sm btn-info" id="btnSubmit">Thêm</a>
                        </div>
                        <table class="table table-striped table-css" id="table-prop">
                            <c:choose>
                                <c:when test="${link.property.css != null}">
                                    <c:forEach var="css" items="${link.property.css}">
                                        <tr id="${css.extraId}">
                                            <td>
                                                <c:set var="cssProp"
                                                       value="${css.extraId}&${css.tag}&${css.clazz}"/>
                                                <input type="checkbox" name="extra" id="extra" value="${cssProp}"
                                                       class="chk-extra" checked title="">
                                            </td>
                                            <td>${css.tag}</td>
                                            <td>${css.clazz}</td>
                                            <td>
                                                <a class="btn btn-danger btn-xs btnDelExtra" id="${css.extraId}">Xóa</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </table>
                        <input type="hidden" name="cssProp" id="cssProp">
                    </div>
                </div>
                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-md-offset-3">
                        <a class="btn btn-success btnAddLink">Cập nhật</a>
                        <a href="<c:url value="/quan-tri-link/danh-sach"/>" class="btn btn-primary">Danh sách</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <dialog id="confirm-addNew" class="site-dialog">
        <header class="dialog-header">
            <h1>Thêm báo</h1>
        </header>
        <div class="dialog-content">
            <div class="form-group">
                <label for="cssTag">Tên báo</label>
                <input type="text" class="form-control" id="newName"
                       placeholder="Nhập tên báo" name="newName">
                <span class="error" id="errNewName"></span>
            </div>
            <div class="form-group">
                <label for="cssTag">Tên viết tắt</label>
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
</div>
