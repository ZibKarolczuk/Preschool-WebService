<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <%@ include file="jspf/head_config.jspf" %>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <style>
        body {background-color: #66ABE2}
    </style>

</head>
<body>

<br>

<div class="container">
    <div class="row">
        <div class='col-md-3'></div>
        <div class="col-md-6">
            <div class="login-box well">
                <%--<%@ include file="jspf/header.jspf"%>--%>
                <%@ include file="jspf/main_menu.jspf" %>
                <p class='error'>${msg}</p>
                <form:form modelAttribute="user" method="post" enctype="utf8">
                    <div class="form-group">
                        <form:label path="username">username</form:label>
                        <form:input path="username" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="email">email</form:label>
                        <form:input type="email" path="email" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="password">password</form:label>
                        <form:password path="password" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <form:label path="matchingPassword">matchingPassword</form:label>
                        <form:password path="matchingPassword" class="form-control"/>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div>
                        <form:errors path="*"/>
                    </div>
                    <button type="submit">Register new user</button>
                </form:form>

            </div>
            <%@ include file="jspf/footer.jspf" %>
        </div>
        <div class='col-md-3'></div>

    </div>

</div>


</body>
</html>