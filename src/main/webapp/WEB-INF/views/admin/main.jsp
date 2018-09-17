<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zbigniew
  Date: 14.08.18
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@include file="/WEB-INF/views/fragment/headConfig.jspf"%>
    <title>Web Service, Admin</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerAdmin.jspf"%>

<div class="container-fluid">

<div>
<h2>Witaj na stronie administratora</h2>
<h4>Jeżeli znalazłeś się tutaj przypadkowo, natychmiast się wyloguj i powiadom o tym administratora.</h4>
</div>
<br>

<%@include file="/WEB-INF/views/fragment/footer.jspf"%>
</div>
</body>
</html>
