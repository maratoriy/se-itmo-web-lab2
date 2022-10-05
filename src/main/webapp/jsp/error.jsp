<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="bundle" scope="session" type="java.util.ResourceBundle"/>
<%
    int error = (Integer) request.getAttribute("error");
    String title = String.format("%s %s", bundle.getString("titles.error"), error);
    String message = String.format("%s (%s)", bundle.getString("error." + error), error);
    request.setAttribute("title", title);
%>
<html lang="ru">
<%@ include file="/jsp/head.jsp" %>
<body>
<%@ include file="header.jsp" %>
<div class="content light-theme background">

    <h2 class="error-label"><%=message%>
    </h2>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
