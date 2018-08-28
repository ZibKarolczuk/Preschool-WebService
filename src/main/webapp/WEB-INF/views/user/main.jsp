<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 14.08.18
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="/WEB-INF/views/fragment/headConfig.jspf"%>
    <title>Stacyjkowo - ${user.username}</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerUser.jspf" %>
<div class="container-fluid">
<h2>Witaj użytkowniku ${user.username}!</h2>

<br>
<h3>Dane kontaktowe</h3>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Login użytkownika</th>
        <th>Imię i nazwisko</th>
        <th>Adres zamieszkania</th>
        <th>Adres email</th>
        <th>Telefon</th>
        <th>Akcje</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${user.username}</td>
        <td>${user.userDetails.name} ${user.userDetails.surname}</td>
        <td>${user.userDetails.addressStreet}<br>
            ${user.userDetails.addressPostCode} ${user.userDetails.addressCity}</td>
        <td><b>${user.email}</b></td>
        <td>${user.userDetails.phone}</td>
        <td><input type="button" class="btn btn-secondary" value="Edytuj dane" onclick="location.href = '/user/update';"></td>
    </tr>
    </tbody>
</table>

<br> <hr>

<br>

<h3>Przypisane dzieci</h3>

<table class="table table-striped">
<%--<table class="table table-striped">--%>
    <thead>

    <th>Imię i nazwisko dziecka</th>
    <th>Grupa przedszkolna</th>
    <%--<th>Data urodzenia</th>--%>
    <th>Upoważnienie do odbioru dziecka</th>
    <th>Uwagi dotyczące diety</th>
    <th>Przyjmowane lekarstwa / suplementy</th>
    <th>Inne</th>
    <th><input type="button" class="btn btn-primary" value="Dodaj dziecko" onclick="location.href = '/user/child/add';"> </th>

    </thead>
    <tbody>
    <c:forEach var="child" items="${childs}">
        <tr>
            <td>${child.name} ${child.surname}</td>
            <td>${child.childGroup.groupName}</td>
            <%--<td>${child.birthhday}</td>--%>
            <td>${child.specialAllowedPickUp}</td>
            <td>${child.specialInfoDiet}</td>
            <td>${child.specialInfoMedication}</td>
            <td>${child.specialInfoOther}</td>
            <td>
                <input type="button" class="btn btn-secondary" value="Edytuj" onclick="location.href = '/user/child/edit/${child.id}';">
                <input type="button" class="btn btn-danger" value="Usuń" onclick="location.href = '/user/child/delete/${child.id}';">

            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>

<br>

<%@include file="/WEB-INF/views/fragment/footer.jspf" %>
</div>
</body>
</html>
