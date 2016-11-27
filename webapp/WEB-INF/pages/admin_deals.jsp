<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin_deals</title>
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
            <a href="/logout" class="btn active btn-cap">
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
            <c:if test="${size ne 0}">
                <h3 class="info">сделки</h3>
            </c:if>
            <c:if test="${size eq 0}">
                <h3 class="info">сделок нет</h3>
            </c:if>
        </ul>
    </div>
</nav>

<%-- хлебные крошки --%>
<div class="container container-bc">
    <ol class="breadcrumb">
        <li class="li-bc"><a href="<c:url value="/"/>" class="a-bc"><i class="fa fa-home"></i></a></li>
        <li class="active li-bca">сделки</li>
    </ol>
</div>

<%-- линия --%>
<hr>

<%-- сделки --%>
<div class="table-responsive">
    <c:if test="${size ne 0}">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><p class="basket">имя</p></th>
                <th><p class="basket">email</p></th>
                <th><p class="basket">телефон</p></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dealsList}" var="deal">
                <tr>
                    <td><p class="basket"><c:out value="${deal.name}"/></p></td>
                    <td><p class="basket"><c:out value="${deal.email}"/></p></td>
                    <td><p class="basket"><c:out value="${deal.phone}"/></p></td>
                    <td><a href="<c:url value="/admin/open_deal/${deal.id}"/>" class="btn active btn-p">открыть</a></td>
                    <td><a href="<c:url value="/admin/delete_deal/${deal.id}"/>" class="btn active btn-dg">удалить</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${size eq 0}">
        <br>
        <br>
    </c:if>
</div>

<%-- линия --%>
<hr>
<br>

<%-- вкладки подвал --%>
<div class="container footer">
    <div class="tabs">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#tab-1" data-toggle="tab"><p class="p_footer_top">composition</p></a></li>
            <li><a href="#tab-2" data-toggle="tab"><p class="p_footer_top">my resume</p></a></li>
            <li><a href="#tab-3" data-toggle="tab"><p class="p_footer_top">faq</p></a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade in active" id="tab-1">
                <table class="table table-hover">
                    <tr>
                        <td><p class="p_footer">java</p></td>
                        <td><p class="descr">строго типизированный объектно-ориентированный язык программирования,
                            состовляет ядро данного приложения.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">apache maven</p></td>
                        <td><p class="descr">отвечает за сборку данного приложения, фреймворк предназначен для
                            автоматизации сборки проектов на основе описания их структуры в файлах на языке POM (Project
                            Object Model), являющемся подмножеством XML.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">spring IOC(Inversion of control)</p></td>
                        <td><p class="descr">предоставляет службы,
                            через которые компоненты могут получать доступ к своим зависимостям, и службы для
                            взаимодействия с зависимостями в течение их времени жизни.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">spring MVC(Model-View-Controller)</p></td>
                        <td><p class="descr">схема использования
                            нескольких шаблонов проектирования, с помощью
                            которых модель данных приложения, пользовательский
                            интерфейс и взаимодействие с пользователем разделены
                            на три отдельных компонента таким образом, чтобы
                            модификация одного из компонентов оказывала
                            минимальное воздействие на остальные.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">spring security</p></td>
                        <td><p class="descr">Java/JavaEE framework, предоставляющий механизмы построения систем
                            аутентификации и авторизации, а также другие возможности обеспечения безопасности для
                            корпоративных приложений, созданных с помощью Spring Framework.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">mySQL</p></td>
                        <td><p class="descr">свободная реляционная система управления базами данных.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">java persistence API(JPA)</p></td>
                        <td><p class="descr">интерфейс предоставляющий возможность сохранять в удобном виде Java-объекты
                            в базе данных. JPA реализует концепцию ORM(Object-Relational Mapping).</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">hibernate</p></td>
                        <td><p class="descr"> библиотека для языка программирования Java, предназначенная для решения
                            задач объектно-реляционного отображения (ORM), одна из популярных реализаций интерфейса
                            JPA</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">Java Persistence Query Language(JPQL)</p></td>
                        <td><p class="descr">используется для написания запросов к сущностям, хранящимся в реляционной
                            базе данных.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">JavaServer Pages Standard Tag Library(JSTL)</p></td>
                        <td><p class="descr">стандартная библиотека тегов JSP, является альтернативой такому виду
                            встроенной в JSP логики, как скриплеты.</p></td>
                    </tr>
                    <tr>
                        <td><p class="p_footer">bootstrap</p></td>
                        <td><p class="descr">свободный набор инструментов, включает в себя HTML и CSS шаблоны оформления
                            для типографики, веб-форм, кнопок, меток, блоков навигации и прочих компонентов
                            веб-интерфейса, включая JavaScript-расширения</p></td>
                    </tr>
                </table>
            </div>
            <div class="tab-pane fade" id="tab-2">
                <p class="descr">
                    <table>
                        <tr>
                            <td>
                <p class="p_footer">Ф.И.О.</p></td>
                <td><p class="descr">Круглов Константин Сергеевич</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">дата рождения</p></td>
                    <td><p class="descr">25.01.1985</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">место проживания</p></td>
                    <td><p class="descr">г.Фастов, Киевская область. Готов к переезду</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">телефон</p></td>
                    <td><p class="descr"> 38050-512-70-13</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">e-mail</p></td>
                    <td><p class="descr"><i class="fa fa-envelope-o" aria-hidden="true"></i> skanerxxl@rambler.ru</p>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">социальные сети</p></td>
                    <td><a class="descr" href="<c:url value="https://vk.com/kryglovkonstantin"/>"><i class="fa fa-vk"
                                                                                                     aria-hidden="true"></i>
                        VK</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">GitHub</p></td>
                    <td><p class="descr">********</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">желаемая должность</p></td>
                    <td><p class="descr">Junior Java developer</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">о себе</p></td>
                    <td><p class="descr">Ищу возможность работать Java-разработчиком. Уверен, что смогу
                        принести пользу команде.
                        Мои преимущества: стрессоустойчивость, быстрая обучаемость.</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">языки</p></td>
                    <td><p class="descr">Русский: родной<br>Украинский: родной<br>Английский: Elementary</p></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">образование</p></td>
                    <td><a class="descr" href="<c:url value="https://prog.kiev.ua/"/>"><i class="fa fa-coffee"
                                                                                          aria-hidden="true"></i> Курсы
                        по JAVA: Прог.Киев</a></td>
                </tr>
                <tr>
                    <td>
                        <p class="p_footer">skills</p></td>
                    <td><p class="descr">язык программирования: Java<br>
                    </p></td>
                </tr>
                <br>
                <br>
                <%--<tr>--%>
                <%--<td>--%>
                <%--<p class="p_footer"></p></td>--%>
                <%--<td><p class="descr"></p></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td>--%>
                <%--<p class="p_footer"></p></td>--%>
                <%--<td><p class="descr"></p></td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td>--%>
                <%--<p class="p_footer"></p></td>--%>
                <%--<td><p class="descr"></p></td>--%>
                <%--</tr>--%>


                </table>
                </p>
            </div>
            <div class="tab-pane fade" id="tab-3"><p class="descr">Для входа под ролью USER просто зарегистрируйтесь
                используя любой
                логин\пароль.<br>Для входа под ролью ADMIN используйте логин: admin, пароль: admin,<br>просьба, не
                удаляйте
                элементы, которые были созданы не вами.<br>Данное приложение не используется в коммерческих целях.</p>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>
