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
    <%@include file="/WEB-INF/views/fragment/headConfig.jspf" %>
    <title>Web Service, ${user.username}</title>
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
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
            <th>Imię i nazwisko opiekuna</th>
            <th>Adres zamieszkania</th>
            <th>Dane kontaktowe</th>
            <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                ${userDetails.name} ${userDetails.surname}
                ${(userDetails.name2.length() gt 0 or userDetails.surname2.length() gt 0) ?
                        "<br>".concat(userDetails.name2).concat(" ").concat(userDetails.surname2) : ''}
            </td>
            <td>${userDetails.addressStreet}<br>
                ${userDetails.addressPostCode} ${userDetails.addressCity}</td>
            <td>
                <i>
                    ${userDetails.email}<br>
                    ${userDetails.phone}

                    ${(userDetails.email2.length() gt 0 or userDetails.phone2.length() gt 0) ?
                            "<br>" : ''}
                    ${userDetails.email2.length() gt 0 ? "<br>".concat(userDetails.email2) : ''}
                    ${userDetails.phone2.length() gt 0 ? "<br>".concat(userDetails.phone2) : ''}
                </i>
            </td>
            <td>
                <c:url var="myurl" value="/user/update" context="${webContext}"/>
                <input type="button" class="btn btn-secondary" value="Edytuj dane"
                       onclick="location.href = '${myurl}';"></td>
        </tr>
        </tbody>
    </table>

    <br>
    <hr>

    <br>

    <h3>Przypisane dzieci</h3>

    <table class="table table-striped">
        <%--<table class="table table-striped">--%>
        <thead>

        <th>Dane dziecka</th>
        <th>Upoważnienie do odbioru</th>
        <th>Wszelkie uwagi</th>
        <th>
            <c:url var="myurl" value="/user/child/add" context="${webContext}"/>
            <input type="button" class="btn btn-primary" value="Dodaj dziecko"
                   onclick="location.href = '${myurl}';"></th>

        </thead>
        <tbody>
        <c:forEach var="child" items="${childs}">
            <tr>
                <td>
                        ${child.name} ${child.surname}<br>
                    Data urodzenia: ${child.birthday eq null ? '' : child.birthday.toLocaleString().substring(0, 10)} <br>
                    Grupa przedszolna: <b>${child.childGroup.groupName}</b>
                </td>
                    <%--<td>${child.birthhday}</td>--%>
                <td>${child.specialAllowedPickUp}</td>
                <td><i>Dieta: </i><b>${child.specialInfoDiet}</b><br>
                    <i>Lekarstwa: </i><b>${child.specialInfoMedication}</b><br>
                    <i>Inne uwagi: </i><b>${child.specialInfoOther}</b></td>
                <td>
                    <c:url var="myurl" value="/user/child/edit/${child.id}" context="${webContext}"/>
                    <input type="button" class="btn btn-secondary" value="Edytuj"
                           onclick="location.href = '${myurl}';">
                    <c:url var="myurl" value="/user/child/delete/${child.id}" context="${webContext}"/>
                    <input id="deleteChild" type="button" class="btn btn-danger" value="Usuń"
                           onclick="location.href = '${myurl}';">

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
