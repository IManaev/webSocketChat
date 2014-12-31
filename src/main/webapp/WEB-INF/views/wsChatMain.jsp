<%--
  Created by IntelliJ IDEA.
  User: manaev
  Date: 30.12.14
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<html>
<head>
    <title>wsChat</title>
</head>
<body>

<%--simple security cred--%>
<div style="display: none">
    <form action="<c:url value='j_spring_security_logout' />" method="post" id="logoutForm">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>

<nav class="navbar navbar-inverse" role="navigation" id="navigation-panel">
    <div class="container-fluid">
        <div class='navbar-header'>
            <span class='navbar-brand'>wsChat</span>
        </div>
        <div class='collapse navbar-collapse'>
            <ul class='nav navbar-nav'>
            </ul>
            <ul class='nav navbar-nav navbar-right'>
                <li>
                    <p class="navbar-text"><i class="fa fa-user"></i> ${user.username} </p>
                </li>
                <li>
                    <p id="logout-href" class='h-cursor-pointer navbar-text nav-p-hover'
                       onclick="$('#logoutForm').submit()"><i class="fa fa-sign-out"/></i>
                        Logout</p>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div id="main-region" class="container-fluid">
</div>

<script type="text/x-handlebars-template" id="wsChat-main-region-layout">
    <div class="row">
        <div class="col-md-3 col-xs-3 col-sm-3 col-lg-3 .col-md-offset-0 .col-xs-offset-0 .col-sm-offset-0 .col-lg-offset-0"
             id="wsLeft">
        </div>
        <div class="col-md-9 col-xs-9 col-sm-9 col-lg-9" id="wsRight">
        </div>
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-menu">
    <div class="container-fluid">
        <div class="row" id="contacts">
        </div>
        <div class="row" id="music">
        </div>
        <div class="row" id="videos">
        </div>
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-menu-contacts">
    <div class="panel panel-default">
        <div class="panel-heading">
            Contacts
        </div>
        <div class="panel-body" id="contacts-list">
        </div>
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-menu-contacts-contact">
    <div class="list-group-item">
        <span>{{username}}</span>
    </div>
</script>

<script type="text/javascript">
    wsChat.user = new wsChat.MainRegion.User({
        username: '${user.username}',
        id: '${user.id}',
        eMail: '${user.eMail}',
        contacts: JSON.parse('${contacts}')
    });
    console.log(wsChat.user)
    wsChat.start();
</script>
</body>
</html>
