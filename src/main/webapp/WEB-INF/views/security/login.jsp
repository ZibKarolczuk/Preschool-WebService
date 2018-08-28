<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <%@ include file="jspf/head_config.jspf" %>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <style>
        body {
            background-color: #66ABE2
        }
    </style>


</head>
<body>

<c:url value="/resources/logo.png" var="logo"/>


<%--<%@ include file="jspf/main_menu.jspf"%>--%>
<%--<p class='error'>${msg}</p>--%>
<%--<form method="post">--%>
<%--<p>--%>
<%--Username <input type="text" name="username" placeholder="" />--%>
<%--</p>--%>
<%--<p>--%>
<%--Password <input type="password" name="password" placeholder="" />--%>
<%--</p>--%>
<%--<p>--%>
<%--<input type="hidden" name="${_csrf.parameterName}"--%>
<%--value="${_csrf.token}" />--%>
<%--<input type="submit" />--%>
<%--</p>--%>
<%--</form>--%>

<br>
<div class="container">
    <%--<img src="${logo}" alt="Girl in a jacket" width="500" height="600">--%>
    <%--<img src="<c:url value="${logo}"/>"/>--%>
    <div class="row">
        <div class='col-md-3'></div>
        <div class="col-md-6">
            <div class="login-box well">
                <%--<%@ include file="jspf/header.jspf"%>--%>
                <%@ include file="jspf/main_menu.jspf" %>
                <p class='error'>${msg}</p>
                <form:form method="post">

                    <div class="form-group">
                        <label id="username">username</label><br>
                        <input type="text" name="username" placeholder="" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label id="password">password</label><br>
                        <input type="password" name="password" placeholder="" class="form-control"/>
                    </div>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>

                    <button type="submit">Login to account</button>

                </form:form>

            </div>
            <%@ include file="jspf/footer.jspf" %>
        </div>
        <div class='col-md-3'></div>
    </div>
</div>
</body>
</html>