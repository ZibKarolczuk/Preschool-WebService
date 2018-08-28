<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  Child: zbigniew
  Date: 13.08.18
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="/WEB-INF/views/fragment/head_config.jspf"%>
    <title>Admin - lista grup</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/header.jspf" %>

<div class="container-fluid">


<h2>Lista wiadomości przychodzących</h2>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Otrzymano od</th>
        <th>Tytuł</th>
        <th>Wiadomość</th>
        <th>Data</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="message" items="${messageList}">
        <tr>
            <td>${message.sendFrom}</td>
            <td>${message.title}</td>
            <td>${message.message}</td>
            <td>Data tutaj</td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<%@include file="/WEB-INF/views/fragment/footer.jspf" %>
</div>
</body>
</html>
