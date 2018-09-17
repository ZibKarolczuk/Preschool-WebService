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
    <title>Web Service, Not Authorized</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerNotAuthorized.jspf" %>

<%-------%>

<div class="container-fluid">

    <div class="container">
        <h2>Unauthorized request</h2>
        <hr>
        <h5>Access denied, you have no permission to view this content.</h5>
        <br>
        <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
    </div>
</div>

</body>
</html>
