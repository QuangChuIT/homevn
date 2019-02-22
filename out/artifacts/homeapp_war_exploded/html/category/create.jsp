<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="">
    <div class="clearfix"></div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Thêm chuyên mục
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false"><i class="fa fa-wrench"></i></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Cấu hình 1</a>
                                </li>
                                <li><a href="#">Cấu hình 2</a>
                                </li>
                            </ul>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal form-label-left" action="<c:url value="/quan-tri-chuyen-muc/them-moi"/>"
                          method="post" novalidate>
                        <span class="section">Thông tin chuyên mục</span>
                        <div class="item form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 error">
                                <c:if test="${not empty message}">
                                    ${message}
                                </c:if>
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Tên chuyên mục <span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="name" class="form-control col-md-7 col-xs-12"
                                       data-validate-length-range="6" data-validate-words="2" name="name"
                                       placeholder="e.g Tin công nghệ" required="required" type="text">
                            </div>
                        </div>
                        <div class="item form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12">Chuyên mục con<span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="tags" name="tags" type="text" data-ro
                                       class="tags form-control col-md-7 col-xs-12"
                                       value=""/>
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-3">
                                <button id="send" type="submit" class="btn btn-success">Thêm</button>
                                <a href="<c:url value="/quan-tri-chuyen-muc"/>" type="button" class="btn btn-primary">Danh
                                    sách</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div
            >
        </div>
    </div>
</div>

