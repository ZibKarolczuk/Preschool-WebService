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
    <title>Web Service (group message)</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf" %>

<%-------%>

<div class="container-fluid">

    <div class="container">
        <h2>Wyślij wiadomość zbiorczą do wielu opiekunów oraz wielu grup</h2>

        <br>
        <hr>

        <form:form method="post" modelAttribute="message">

            <div class="form-group row">
                <div class="col-sm-6">

                    <input type="hidden" name="groupTo" value="0">
                    <c:forEach var="group" items="${childGroupList}">

                        <c:set var="group_size" value="0"></c:set>
                        <c:forEach var="child" items="${childList}">
                            <c:choose>
                                <c:when test="${child.childGroup.id eq group.id}">
                                    <c:set var="group_size" value="${group_size+1}"></c:set>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${group_size > 0}">
                                <div class="checkbox">
                                    <input type="checkbox" name="groupTo"
                                           value="${group.id}" ${group.id eq groupSelected ? 'checked="checked"' : ''}>Wiadomość
                                    do
                                    opiekunów grupy "<b>${group.groupName}</b>"<br>
                                </div>
                            </c:when>
                        </c:choose>

                    </c:forEach>
                </div>
            </div>


            <div class="form-group row">
                <form:label path="title" class="col-sm-2 col-form-label">Tytuł wiadomości</form:label>
                <div class="col-sm-10">
                    <form:input path="title" class="form-control" placeholder="wpisz tytuł..."></form:input>
                    <form:errors path="title"></form:errors>
                </div>
            </div>

            <div class="form-group">
                <form:label path="message">Treść wiadomości</form:label>
                <form:textarea path="message" rows="15"
                               placeholder="wpisz treść wiadomości..." class="form-control"></form:textarea>
                <form:errors path="message"></form:errors>
            </div>

            <div class="form-group row">
                <div class="offset-sm-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Wyślij wiadomość</button>
                </div>
            </div>

        </form:form>

        <br>
        <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
    </div>
</div>

</body>
</html>
