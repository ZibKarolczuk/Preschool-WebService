<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 13.08.18
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="/WEB-INF/views/fragment/headConfig.jspf" %>
    <title>Admin - lista grup</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf" %>
<div class="container-fluid">


    <h2>Lista użytkowników</h2>

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
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.username}</td>
                <td>${user.userDetails.name} ${user.userDetails.surname}</td>
                <td>${user.userDetails.addressStreet}<br>
                        ${user.userDetails.addressPostCode} ${user.userDetails.addressCity}</td>
                <td>${user.email}</td>
                <td>${user.userDetails.phone}</td>
                <td><input type="button" class="btn btn-primary" value="Wyślij e-mail" onclick="location.href = '/admin/user/email/${user.id}';">
                    <input type="button" class="btn btn-danger" value="Usuń użytkownika" onclick="location.href = '/admin/user/delete/${user.id}';"
                    <%--<a href="/admin/user/delete/${user.id}">Usuń</a>--%>
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
