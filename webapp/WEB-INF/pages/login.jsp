<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>login</title>
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
    <link rel="stylesheet" href="<c:url value="/css/style-login.css"/>">
</head>
<body>
<div class="container log">
    <div class="well">
        <legend>авторизация</legend>
        <h class="log-error">${error}</h>
        <form:form id="formLogin" action="./login" method="post">
            <input id="log" type="text" class="form-control log-field" name="username" value="" autofocus required
                   placeholder="username">
            <input type="password" class="form-control log-field" name="password" required placeholder="password">
            <div class="btn-group">
                <a href="<c:url value="/"/>" class="btn log-btn"><i class="fa fa-arrow-left"></i>
                    на главную</a>
                <input type="submit" class="btn active log-btn" value="войти"/>
            </div>
        </form:form>
    </div>
</div>
<script src="<c:url value="/js/jquery.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script>
    $(document).ready(function () {
        $('#log').keyup(function () {
            var $this = $(this);
            if ($this.val().length > 20)
                $this.val($this.val().substr(0, 20));
        });
    });
</script>
</body>
</html>