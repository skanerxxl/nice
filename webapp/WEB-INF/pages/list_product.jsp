<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>list_product</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.min.css"/>">
    <link rel="stylesheet" media="screen" href="<c:url value="/css/jasny-bootstrap.min.css"/>">
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
    <link rel="stylesheet" href="<c:url value="/css/style-hr.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-footer.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-modal.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-media.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/style-list.css"/>">
</head>
<body>
<%-- шапка --%>
<div class="container-fluid menu-cap">
    <c:if test="${error ne ''}">
        <h class="btn error"><c:out value="${error}"/></h>
    </c:if>
    <div class="btn-group btn-group-sm pull-right menu">
        <sec:authorize access="!isAuthenticated()">
            <a href="#" class="btn active btn-cap" data-toggle="modal" data-target="#modal-reg">регистрация</a>
            <a href="<c:url value="/login"/>" class="btn active btn-cap">
                <i class="fa fa-sign-in" aria-hidden="true"></i> вход
            </a>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            <a href="<c:url value="/user/basket_deals_list/${0}/${'l_p'}"/>"
               class="btn active btn-cap">${pageContext.request.userPrincipal.name} корзина</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/admin/deals_list"/>"
               class="btn active btn-cap">${pageContext.request.userPrincipal.name} сделки</a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a href="<c:url value="/logout"/>" class="btn active btn-cap">
                <i class="fa fa-sign-out" aria-hidden="true"></i> выход
            </a>
        </sec:authorize>
        <sec:authorize access="!hasRole('ADMIN')">
            <a id="btn-contacts"
               class="btn active btn-cap"
               tabindex="0"
               data-toggle="popover"
               title="(050)-512-70-13"
               data-content="
                   <a href='<c:url value="https://vk.com/kryglovkonstantin"/>' title='вконтакте'>
                   <span class='fa-stack fa-2x'>
                   <i class='fa fa-circle-thin fa-stack-2x' style='color: #424242'></i>
                   <i class='fa fa-vk fa-stack-1x' aria-hidden='true' style='color: #499dc0;'></i>
                   </span>
                   </a>

                   <a href='<c:url value="https://ok.ru/"/>' title='одноклассники'>
                   <span class='fa-stack fa-2x'>
                   <i class='fa fa-circle-thin fa-stack-2x' style='color: #424242'></i>
                   <i class='fa fa-odnoklassniki fa-stack-1x' aria-hidden='true' style='color: orangered;'></i>
                   </span>
                   </a>"
               data-delay="180">контакты</a>
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
            <sec:authorize access="hasRole('ADMIN')">
                <table>
                    <tr>
                        <td><p class="info">пользователь: </p></td>
                        <td><h3 class="info"><c:out value="${pageContext.request.userPrincipal.name}"/></h3></td>
                    </tr>
                </table>
            </sec:authorize>
            <c:forEach items="${listOfConsumerCategory}" var="cc">
                <sec:authorize access="!hasRole('ADMIN')">
                    <li><a href="<c:url value="/product_category/${cc.id}/${cc.nameConsumerCategory}"/>">
                            ${cc.nameConsumerCategory}</a>
                    </li>
                </sec:authorize>
            </c:forEach>
        </ul>
    </div>
</nav>

<%-- хлебные крошки --%>
<div class="container container-bc">
    <ol class="breadcrumb">
        <li class="li-bc"><a href="<c:url value="/"/>" class="a-bc"><i class="fa fa-home"></i></a></li>
        <c:if test="${page ne 'basket' && page ne 'user_deals'}">
            <li class="li-bc"><a href="<c:url value="/product_category/${idCC}/${nameCC}"/>" class="a-bc">${nameCC}</a>
            </li>
            <li class="active li-bca"><c:out value="${namePC}"/></li>
        </c:if>
        <c:if test="${page eq 'basket'}">
            <li class="li-bc"><a onclick="history.back()" class="a-bc" style="cursor: pointer">назад</a></li>
            <li class="active li-bca"><c:out value="${product.name}"/></li>
        </c:if>
        <c:if test="${page eq 'user_deals'}">
            <li class="li-bc"><a onclick="history.back()" class="a-bc" style="cursor: pointer">назад</a></li>
            <li class="active li-bca"><c:out value="${product.name}"/></li>
        </c:if>
    </ol>
</div>

<%-- лист продуктов --%>
<div class="container list">
    <div class="row list-pr-mas" data-columns>
        <sec:authorize access="hasRole('ADMIN')">
            <%-- класс item для сетки masonry --%>
            <div class="item">
                <div class="thumbnail">
                    <img class="photo_list" src="<c:url value="/img/new.jpg"/>" width="400"/>
                    <div class="caption">
                        <h3><span class="badge">new</span>продукт</h3>
                        <p>создать новый товар</p>
                        <a href="" class="btn active btn-p" data-toggle="modal" data-target="#modal-new_pr">создать
                            <i class="fa fa-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </sec:authorize>
        <c:forEach items="${listOfProduct}" var="pr">
            <div class="item">
                <div class="thumbnail">
                    <img class="photo_list" src="<c:url value="/photo/${pr.photo.id}"/>" alt="изображение отсутствует"
                         width="400"/>
                    <div class="caption">
                        <h3><c:out value="${pr.name}"/></h3>
                        <sec:authorize access="!hasRole('ADMIN')">
                            <a href="<c:url value="/open_product/${pr.id}/${'list_product'}"/>"
                               class="btn active btn-p">открыть
                                <i class="fa fa-arrow-right"></i></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <div class="btn-group drop_catgr">
                                <a href="#" class="btn active btn-p dropdown-toggle" data-toggle="dropdown">
                                    меню<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="<c:url value="/open_product/${pr.id}/${'list_product'}"/>">открыть</a>
                                    </li>
                                    <li class="divider"></li>
                                    <li><a href="<c:url value="/admin/change_delete_product/${pr.id}"/>"
                                           data-toggle="modal" data-target="#modal-del_pr">удалить</a></li>
                                </ul>
                            </div>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
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

<%-- модальное окно - регистрация --%>
<div class="modal fade" id="modal-reg">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    <i class="fa fa-close"></i>
                </button>
                <h4 class="modal-title">регистрация</h4>
            </div>
            <div class="modal-body">
                <form roles="form" class="form-horizontal" action="<c:url value="/registration"/>" method="post">
                    <input id="in-reg" type="text" class="form-control" name="username" required placeholder="логин">
                    <input type="password" class="form-control" name="password" required placeholder="пароль">
                    <div class="btn-group modal-group">
                        <input type="reset" class="btn active btn-d" data-dismiss="modal" value="отмена"/>
                        <input type="reset" class="btn active btn-s" value="сброс"/>
                        <input type="submit" class="btn active btn-p" value="готово"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%-- модальное окно - создать товар --%>
<div class="modal fade" id="modal-new_pr">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    <i class="fa fa-close"></i>
                </button>
                <h4 class="modal-title">создать новый товар</h4>
            </div>
            <div class="modal-body">
                <form roles="form" enctype="multipart/form-data" class="form-horizontal"
                      action="<c:url value="/admin/new_product"/>" method="post">
                    <input id="in-new_pr" type="text" class="form-control" name="name" required placeholder="имя">
                    <input type="text" class="form-control" name="size" placeholder="размер">
                    <input type="text" class="form-control" name="season" placeholder="сезон">
                    <input type="text" class="form-control" name="color" placeholder="цвет">
                    <input type="text" class="form-control" name="zastezhka" placeholder="застёжка">
                    <input type="text" class="form-control" name="length" placeholder="длина">
                    <input type="text" class="form-control" name="sleeve" placeholder="рукав">
                    <input type="text" class="form-control" name="material" placeholder="материал">
                    <input type="text" class="form-control" name="description" placeholder="описание">
                    <%--инпут для изображения--%>
                    <div class="fileinput fileinput-new inpput-img" data-provides="fileinput">
                        <div class="fileinput-new thumbnail">
                            <img data-src="holder.js/100%x100%"><i class="fa fa-photo fa-3x text-legend"></i>
                        </div>
                        <div class="fileinput-preview fileinput-exists thumbnail">
                        </div>
                        <div>
                            <span class="btn active btn-file"><span class="fileinput-new">выбрать изображение</span>
                            <span class="fileinput-exists">изменить</span><input type="file" name="photo"></span>
                        </div>
                    </div>
                    <input type="password" class="form-control" name="command" required
                           placeholder="введите пароль администратора">
                    <div class="btn-group modal-group">
                        <input type="reset" class="btn active btn-d" data-dismiss="modal" value="отмена"/>
                        <input type="reset" class="btn active btn-s" value=" сброс "/>
                        <input type="submit" class="btn active btn-p" value="готово"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%-- модальное окно - удалить товар --%>
<div class="modal fade" id="modal-del_pr">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">
                    <i class="fa fa-close"></i>
                </button>
                <h4 class="modal-title del-title">внимание!</h4>
            </div>
            <div class="modal-body">
                <p class="modal-title">вы действительно хотите удалить этот товар</p>
                <form roles="form" class="form-horizontal" action="<c:url value="/admin/delete_product"/>"
                      method="post">
                    <input id="in-del_pr" type="password" class="form-control" name="command" required
                           placeholder="введите пароль администратора">
                    <div class="btn-group modal-group">
                        <input type="reset" class="btn active btn-d" data-dismiss="modal" value="отмена"/>
                        <input type="reset" class="btn active btn-s" value=" сброс "/>
                        <input type="submit" class="btn active btn-dg" value="удалить">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/jasny-bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/salvattore.min.js"/>"></script>
<script>
    $(document).ready(function () {
        $('#btn-contacts').popover({
            html: true,
            placement: 'bottom',
            trigger: 'focus'
        });

        $('#in-reg').keyup(function () {
            var $this = $(this);
            if ($this.val().length > 20)
                $this.val($this.val().substr(0, 20));
        });

        $('#in-new_pr').keyup(function () {
            var $this = $(this);
            if ($this.val().length > 10)
                $this.val($this.val().substr(0, 10));
        });

        $('#modal-reg').on('shown.bs.modal', function () {
            $('#in-reg').focus();
        });

        $('#modal-new_pr').on('shown.bs.modal', function () {
            $('#in-new_pr').focus();
        });

        $('#modal-del_pr').on('shown.bs.modal', function () {
            $('#in-del_pr').focus();
        });
    });
</script>
</body>
</html>
