<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="font" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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


    <h2>Lista grup</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Grupa</th>
            <th>Przypisani uczniowie</th>
            <th>Akcje</th>
            <%--<th><input type="button" class="btn btn-warning" value="Dodaj nową grupę" onclick="location.href = '/admin/group/new';"></th>--%>
        </tr>
        </thead>
        <tbody>


        <%--<form method="post">--%>
        <%--<c:forEach var="info" items="${childGroupList}">--%>
        <%--<input type="checkbox" name="sendTo" value="${info.id}">Wiadomość do opiekunów grupy "<b>${info.groupName}</b>"<br>--%>
        <%--</c:forEach>--%>
        <%--<input type="submit" value="Wyślij do zaznaczonych">--%>
        <%--</form>--%>

        <%--<hr>--%>

        <c:forEach var="cg" items="${childGroupList}">
            <c:set var="send_email" value=""></c:set>
            <%--<c:set var="delete_group" value="<a href=\"/admin/group/${cg.id}/delete\">Usuń grupę</a>"></c:set>--%>

            <c:set var="delete_group"
                   value="<input type=\"button\" class=\"btn btn-danger\" value=\"Usuń grupę\" onclick=\"location.href = '/admin/group/${cg.id}/delete';\">"></c:set>


            <tr>
                <td>${cg.groupName}</td>
                <td>
                    <c:forEach var="sc" items="${sortedChilds}">
                        ${cg.id == sc.childGroup.id ? sc.name.concat(" ").concat(sc.surname).concat("<br>") : ''}
                        <c:choose>
                            <c:when test="${cg.id eq sc.childGroup.id}">
                                <c:set var="send_email"
                                       value="<input type=\"button\" class=\"btn btn-primary\" value=\"Wyślij e-mail\" onclick=\"location.href = '/admin/group/${cg.id}/message';\">"></c:set>
                                <c:set var="delete_group" value=""></c:set>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </td>
                <td>
                        <%--<a href="/admin/group/${cg.id}/edit">Edytuj grupę</a><br>--%>
                    <input type="button" class="btn btn-secondary" value="Edytuj grupę"
                           onclick="location.href = '/admin/group/${cg.id}/edit';">
                        ${send_email}
                        ${delete_group}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <br>

    <%--<div><h4><a href="/admin/group/new">Dodaj nową grupę</a></h4></div>--%>
    <div><input type="button" class="btn btn-outline-secondary" value="Dodaj nową grupę" onclick="location.href = '/admin/group/new';"></div>
    <br>

    <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
</div>
</body>
</html>
