<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 12.08.18
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="/WEB-INF/views/fragment/headConfig.jspf" %>
    <title>Stacyjkowo - nowy profil</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf" %>
<div class="container-fluid">

    <div class="container">

        <h2>Dodaj nowego użytkownika</h2>
        <br>

        <form:form method="post" modelAttribute="user">

            <div class="form-group row">
                <form:label path="username" class="col-sm-2 col-form-label">Login</form:label>
                <div class="col-sm-6">
                    <form:input path="username" class="form-control" placeholder="np. jkowal"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="userDetails.email" class="col-sm-2 col-form-label">Email</form:label>
                <div class="col-sm-6">
                    <form:input path="userDetails.email" class="form-control"
                                placeholder="jan.kowalski@yahoo.com"></form:input>
                    <form:errors path="userDetails.email"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <div class="offset-sm-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Utwórz nowe konto</button>
                </div>
            </div>

        </form:form>
        <br>

        <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
    </div>
</div>
</body>
</html>
