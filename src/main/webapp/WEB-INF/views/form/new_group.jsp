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
    <title>Web Service (form group)</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf" %>
<div class="container-fluid">


    <h2>${title}</h2>
    <br>

    <form:form method="post" modelAttribute="childGroup">
        <div>
            <form:label path="groupName">Nazwa grupy</form:label>
            <form:input path="groupName"></form:input>
            <input class="btn btn-success" type="submit" value="Zapisz">
        </div>

    </form:form>
    <br>

    <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
</div>
</body>
</html>
