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
    <title>Web Service (form student)</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerUser.jspf" %>
<div class="container-fluid">

    <div class="container">

        <h2>${title}</h2>
        <br>

        <form:form method="post" modelAttribute="child">

            <form:hidden path="id"></form:hidden>

            <div class="form-group row">
                <form:label path="name" class="col-sm-2 col-form-label">Imię</form:label>
                <div class="col-sm-6">
                    <form:input path="name" class="form-control" placeholder="Jaś"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="name" class="col-sm-2 col-form-label">Nazwisko</form:label>
                <div class="col-sm-6">
                    <form:input path="surname" class="form-control" placeholder="Kowalski"></form:input>
                    <form:errors path="surname"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="birthday" class="col-sm-2 col-form-label">Data urodzenia</form:label>
                <div class="col-sm-6">
                    <form:input path="birthday" class="form-control" placeholder="dd.mm.yyyy" id="datepicker"></form:input>
                    <form:errors path="birthday"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="specialAllowedPickUp"
                            class="col-sm-2 col-form-label">Upoważnienie do odbioru</form:label>
                <div class="col-sm-6">
                    <form:input path="specialAllowedPickUp" class="form-control"
                                placeholder="np. Jan Kowalski (ojciec) Janina Nowak (babcia)"></form:input>
                    <form:errors path="specialAllowedPickUp"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="specialInfoDiet"
                            class="col-sm-2 col-form-label">Przeciwskazania żywieniowe</form:label>
                <div class="col-sm-6">
                    <form:input path="specialInfoDiet" class="form-control"
                                placeholder="np. uczulenie na białka zwierzęce"></form:input>
                    <form:errors path="specialInfoDiet"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="specialInfoMedication"
                            class="col-sm-2 col-form-label">Przypisane lekarstwa</form:label>
                <div class="col-sm-6">
                    <form:input path="specialInfoMedication" class="form-control"
                                placeholder="np. lek na astmę, glukoza po obiedzie"></form:input>
                    <form:errors path="specialInfoMedication"></form:errors>
                </div>
            </div>

            <div class="form-group row">
                <form:label path="specialInfoOther"
                            class="col-sm-2 col-form-label">Inne uwagi</form:label>
                <div class="col-sm-6">
                    <form:input path="specialInfoOther" class="form-control"
                                placeholder="np. zwracać uwagę aby dziecko się nie garbiło"></form:input>
                    <form:errors path="specialInfoOther"></form:errors>
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
</div>
</body>
</html>
