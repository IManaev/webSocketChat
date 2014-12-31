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

<script type="text/x-handlebars-template" id="wsChat-main-region-public-chat-window-online-user">
        <a class="pull-left" href="#">
            <img class="media-object" data-src="holder.js/64x64" alt="64x64" style="width: 50px; height: 50px;" src="">
        </a>
        <div class="media-body">
            <h5 class="media-heading">{{username}}</h5>
        </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-public-chat-window-online-user-container">
    <div id="online-chat-users-container">
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-public-chat-window-message">
    <div class="msg-wrap">
        <div class="media msg ">
            <div class="media-body">
                <small class="pull-right time"><i class="fa fa-clock-o"></i>{{time}}</small>
                <h5 class="media-heading">{{username}}</h5>
                <small class="col-lg-10">{{content}}</small>
            </div>
        </div>
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-public-chat-window-message-container">
    <div id="chat-window-messagebox-container">
    </div>
</script>

<script type="text/x-handlebars-template" id="wsChat-main-region-public-chat-window">
    <div class="row">
        <div class="conversation-wrap col-lg-3" id="online-chat-users">
            <%--put wsChat-main-region-public-chat-window-online-user here--%>
        </div>
        <div class="message-wrap col-lg-8">
            <div id="chat-window-messagebox">
                <%--put wsChat-main-region-public-chat-window-message here--%>
            </div>
            <div class="send-wrap">
                <textarea class="form-control send-message" id="chatText" rows="3" placeholder="Write a reply..."></textarea>
            </div>
            <div class="btn-panel">
                <span class=" col-lg-3 btn   send-message-btn " role="button" id="attachFile"><i
                        class="fa fa-cloud-upload"></i> Add Files</span>
                <span class=" col-lg-4 text-right btn   send-message-btn pull-right" role="button"
                   id="sendMessageButton"><i class="fa fa-plus"></i> Send Message</span>
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
        <span> <i class="fa fa-circle fa-on"></i> {{username}}</span>
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
    wsChat.onlineUsers = new wsChat.ChatWindow.InitialModel();
    wsChat.start();
</script>
</body>
</html>
