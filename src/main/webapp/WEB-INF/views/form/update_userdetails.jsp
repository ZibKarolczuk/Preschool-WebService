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

<%@include file="/WEB-INF/views/fragment/headerUser.jspf" %>
<div class="container-fluid">

    <div class="container">
        <h2>Zaktualizuj dane personalne</h2>
        <br>

        <form:form method="post" modelAttribute="userdetails">

            <form:hidden path="id"></form:hidden>

            <div class="form-group row">
                <form:label path="name" class="col-sm-2 col-form-label">Imię</form:label>
                <div class="col-sm-6">
                    <form:input path="name" class="form-control" placeholder="Jan"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="surname" class="col-sm-2 col-form-label">Nazwisko</form:label>
                <div class="col-sm-6">
                    <form:input path="surname" class="form-control" placeholder="Kowalski"></form:input>
                    <form:errors path="surname"></form:errors>
                </div>
            </div>

            <%--<div class="form-group row">--%>
                <%--<form:label path="email" class="col-sm-2 col-form-label">Email</form:label>--%>
                <%--<div class="col-sm-6">--%>
                    <%--<form:input path="email" class="form-control" placeholder="jan.kowalski@yahoo.com"></form:input>--%>
                    <%--<form:errors path="email"></form:errors>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="form-group row">
                <form:label path="phone" class="col-sm-2 col-form-label">Telefon</form:label>
                <div class="col-sm-6">
                    <form:input path="phone" class="form-control" placeholder="+48 690 573 226"></form:input>
                    <form:errors path="phone"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="addressStreet" class="col-sm-2 col-form-label">Ulica</form:label>
                <div class="col-sm-6">
                    <form:input path="addressStreet" class="form-control"
                                placeholder="gen. Władysława Sikorskiego 9/11"></form:input>
                    <form:errors path="addressStreet"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="addressPostCode" class="col-sm-2 col-form-label">Kod pocztowy</form:label>
                <div class="col-sm-6">
                    <form:input path="addressPostCode" class="form-control" placeholder="32-191"></form:input>
                    <form:errors path="addressPostCode"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="addressCity" class="col-sm-2 col-form-label">Miejscowość</form:label>
                <div class="col-sm-6">
                    <form:input path="addressCity" class="form-control" placeholder="Zabierzów"></form:input>
                    <form:errors path="addressCity"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <div class="offset-sm-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                </div>
            </div>
        </form:form>

        <br>
        <%@include file="/WEB-INF/views/fragment/footer.jspf" %>

    </div>
</body>
</html>
