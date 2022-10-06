<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="bundle" scope="session" type="java.util.ResourceBundle"/>
<c:set var="statusCode" scope="request" value="<%=response.getStatus()%>"/>
<html lang="ru">
<c:set var="title" scope="request" value="${bundle['titles.error']} ${statusCode}"/>
<%@ include file="/jsp/head.jsp" %>
<body>
<%@ include file="header.jsp" %>
<div class="content light-theme background">

    <c:set var="localizedError" value="error.${statusCode}"/>
    <h2 class="error-label">${bundle.getString(localizedError)} (${statusCode})</h2>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
