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

    <script>
        <c:set var="webContext"  value="${pageContext.request.contextPath}" />
    </script>

    <script src="${webContext}/js/script_sms.js" type="text/javascript"></script>
    <title>Stacyjkowo - SMS</title>
</head>
<body>

<%@include file="/WEB-INF/views/fragment/headerUser.jspf" %>
<div class="container-fluid">

    <div class="container">
        <h2>SMS nie może zostać dostarczony</h2>
        <h3>Funkcja będzie dostępna po wykupieniu usługi <a
                href="https://www.smsapi.pl/?gclid=EAIaIQobChMIr-OFyNy43QIV7bXtCh1GNQ-6EAAYAyAAEgJkbfD_BwE"><b>SMS
            API</b></a></h3>
        <br>
        <br>

        <%@include file="/WEB-INF/views/fragment/footer.jspf" %>
    </div>
</div>
</body>
</html>