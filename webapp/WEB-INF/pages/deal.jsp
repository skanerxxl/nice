<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>deal</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet"
          href="<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"/>">
    <!--[if lt IE 9]>
    <script src="<c:url value='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'/>"></script>
    <script src="<c:url value='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'/>"></script>
    <![endif]-->
    <link rel="shortcut icon" href="<c:url value="/img/favicon.ico"/>" type="image/x-icon">
    <link rel="stylesheet" href="<c:url value="/css/style-body.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-cap.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-navigation.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-breadcrumb.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-basket.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-hr.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-modal.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-media.css"/>">
</head>
<body>
<%-- шапка --%>
<div class="container-fluid menu-cap">
    <div class="btn-group btn-group-sm pull-right menu">
        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/logout"/>" class="btn active btn-cap">
                <i class="fa fa-sign-out" aria-hidden="true"></i> выход
            </a>
        </sec:authorize>
    </div>
</div>

<%-- панель навигации --%>
<nav class="navbar navbar-default navigation">
    <div class="container-fluid">
        <%-- брэнд --%>
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/img/shopp.jpg"/>" height="120"
                                                                   width="120"></a>
        </div>
        <%-- меню навигации --%>
        <ul class="nav navbar-nav consumer">
        </ul>
    </div>
</nav>

<%-- хлебные крошки --%>
<div class="container container-bc">
    <ol class="breadcrumb">
        <li class="li-bc"><a href="<c:url value="/"/>" class="a-bc"><i class="fa fa-home"></i></a></li>
        <%--<li class="li-bc"><a class="a-bc" onclick="history.back()" style="cursor: pointer">в сделки</a></li>--%>
        <li><a href="<c:url value="/admin/deals_list"/>" class="a-bc">назад</a></li>
        <li class="active li-bca">сделка: <c:out value="${deal.name}"/></li>
    </ol>
</div>

<%-- линия --%>
<hr>

<%-- одна сделка --%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6 col-deal">
            <div class="thumbnail">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <tr class="bas">
                            <td><p class="deal">имя:</p></td>
                            <td><p class="deal"><c:out value="${deal.name}"/></p></td>
                        </tr>
                        <tr class="bas">
                            <td><p class="deal">телефон:</p></td>
                            <td><p class="deal"><c:out value="${deal.phone}"/></p></td>
                        </tr>
                        <c:if test="${deal.email ne ''}">
                            <tr class="bas">
                                <td><p class="deal">email:</p></td>
                                <td><p class="deal"><c:out value="${deal.email}"/></p></td>
                            </tr>
                        </c:if>
                        <tr class="bas">
                            <td><p class="deal">перевозчик:</p></td>
                            <td><p class="deal"><c:out value="${deal.addressOfDelivery.carrier}"/></p></td>
                        </tr>
                        <tr class="bas">
                            <td><p class="deal">город:</p></td>
                            <td><p class="deal"><c:out value="${deal.addressOfDelivery.city}"/></p></td>
                        </tr>
                        <tr class="bas">
                            <td><p class="deal">ном.склада:</p></td>
                            <td><p class="deal"><c:out value="${deal.addressOfDelivery.numberOffice}"/></p></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-deal">
            <div class="thumbnail">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <caption>
                            <h3>сделки</h3>
                        </caption>
                        <c:set var="existMyOrder" value="${0}"/>
                        <c:forEach items="${deal.myOrders}" var="myOrder">
                            <c:if test="${myOrder.exist}">
                                <tr class="bas">
                                    <td><img height="40" width="40" src="<c:url value="/photo/${myOrder.photoKey}"/>"/>
                                    </td>
                                    <td><p class="deal"><c:out value="${myOrder.name}"/></p></td>
                                    <td>
                                        <a href="<c:url value="/open_product/${myOrder.productKey}/${'deal'}"/>"
                                           class="btn active btn-p">открыть</a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!myOrder.exist}">
                                <tr class="bas">
                                    <td><img height="60" width="60" src="<c:url value="/img/err.jpg"/>" alt="!!!"/></td>
                                    <td><p class="deal">товар больше не существует</p></td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- линия --%>
<hr>
<br>

<%-- вкладки подвал --%>
<div class="container footer">
    <div class="tabs">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-1" data-toggle="tab">О нас</a></li>
            <li><a href="#tab-2" data-toggle="tab">Доставка и оплата</a></li>
            <li><a href="#tab-3" data-toggle="tab">вкладка3</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="tab-1"><p>Nice Shop - интернет-магазин стильной одежды для всей
                семьи.<br> Широкий выбор актуальных моделей из натуральных тканей по очень демократичным ценам</p></div>
            <div class="tab-pane fade" id="tab-2"><p>Если заказ оформлен сегодня -
                заказ будет обработан и отправлен на следующий рабочий день <br>любой удобной для вас почтовой службой
            </p>
            </div>
            <div class="tab-pane fade" id="tab-3"><p>Вкладка с текстом3</p></div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>
