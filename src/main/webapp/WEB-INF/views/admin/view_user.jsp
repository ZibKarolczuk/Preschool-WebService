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
    <link id="contextPathHolder" data-contextPath="${pageContext.request.contextPath}"/>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf" %>
<div class="container-fluid">

    <h2>Lista użytkowników</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Opiekunowi prawni</th>
            <th>Dane kontaktowe</th>
            <th>Przypisane dzieci</th>
            <th>Akcje na użytkowniku</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ud" items="${userDetailsList}">
            <tr>

                <td>
                        ${ud.name} ${ud.surname}
                        ${(ud.name2.trim().length() gt 0 or ud.surname2.trim().length() gt 0) ?
                                "<br>".concat(ud.name2).concat(" ").concat(ud.surname2) : ''}
                </td>

                <td>
                        ${(ud.addressStreet.trim().length() gt 0 or ud.addressCity.trim().length() gt 0) ?
                                ud.addressStreet.concat(", ").concat(ud.addressPostCode).concat(" ").concat(ud.addressCity).concat("<br>") : '' }
                    <i>
                        e-mail:
                        <b>${ud.email}</b> ${ud.phone.trim().length() gt 0 ? "/ tel: <b>".concat(ud.phone).concat("</b>") : ''}
                            ${(ud.email2.trim().length() gt 0 or ud.phone2.trim().length() gt 0) ? "<br>" : '' }
                            ${ud.email2.trim().length() gt 0 ? "e-mail: <b>".concat(ud.email2).concat("</b>") : ''}
                            ${(ud.email2.trim().length() gt 0 and ud.phone2.trim().length() gt 0) ? " / " : ''}
                            ${ud.phone2.trim().length() gt 0 ? "tel: <b>".concat(ud.phone2).concat("</b>") : ''}
                    </i>
                </td>

                <td>
                    <c:forEach var="child" items="${childs}">
                        ${child.userDetails.id eq ud.id ?
                        child.name.concat(" ").concat(child.surname).concat(" (<b>").concat(child.childGroup.groupName).concat("</b>), ") : ''}
                        <%--${child.name} ${child.surname} (<b>${child.childGroup.groupName}</b>),--%>
                    </c:forEach>
                </td>

                <td>

                    <%--<c:out value="${pageContext.request.contextPath}" />--%>

                    <c:url var="myurl" value="/admin/user/email/${ud.id}" context="${webContext}"/>
                    <input type="button" class="btn btn-primary" value="Wyślij e-mail"
                           onclick="location.href = '${myurl}';">
                    <c:url var="myurl" value="/admin/user/delete/${ud.id}" context="${webContext}"/>
                    <input id="deleteUser" type="button" class="btn btn-danger" value="Usuń użytkownika"
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
