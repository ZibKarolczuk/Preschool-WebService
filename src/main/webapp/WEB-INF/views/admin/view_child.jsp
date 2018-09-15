<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  Child: zbigniew
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
    <h2>Lista uczniów</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Imię i nazwisko dziecka</th>
            <th>Grupa przedszkolna</th>
            <th>Opiekun prawny</th>
            <th>Upoważnienie do odbioru dziecka</th>
            <th>Wszelkie uwagi</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="child" items="${childList}">

            <tr>
                <td>
                        ${child.name} ${child.surname} <br>
                                ${child.birthday eq null ? '' : child.birthday.toLocaleString().substring(0, 10)}
                </td>

                <td>

                    <form method="post">
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="hidden" name="childId" value="${child.id}">
                        <select class="btn btn-primary" name="groupId">
                            <option value="0">Nie przypisano</option>
                            <c:forEach var="gr" items="${groups}">
                                <option value="${gr.id}" ${gr.id eq child.childGroup.id ? 'selected' : ''}>${gr.groupName}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" class="btn btn-success" value="Zapisz">
                    </form>

                </td>

                <td>
                        ${child.userDetails.name} ${child.userDetails.surname}
                        ${(child.userDetails.name2.trim().length() gt 0 or child.userDetails.surname2.trim().length() gt 0) ?
                                "<br>".concat(child.userDetails.name2).concat(" ").concat(child.userDetails.surname2) : ''}
                </td>
                <td>${child.specialAllowedPickUp}</td>

                <td>
                        ${child.specialInfoDiet.trim().length() gt 0 ? "Dieta: ".concat(child.specialInfoDiet).concat("<br>") : ''}
                        ${child.specialInfoMedication.trim().length() gt 0 ? "Lekarstwa: ".concat(child.specialInfoMedication).concat("<br>") : ''}
                        ${child.specialInfoOther.trim().length() gt 0 ? "Inne uwagi: ".concat(child.specialInfoOther) : ''}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
</div>
</body>
</html>
