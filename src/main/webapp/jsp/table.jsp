<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="results" scope="application" type="java.util.List"/>
<tr>
    <th>${bundle['table.reqTime']}</th>
    <th>${bundle['table.execTime']}</th>
    <th>${bundle['table.data']} (x,y,r)</th>
    <th>${bundle['table.result']}</th>
</tr>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    SimpleDateFormat dateFormatWithTime = new SimpleDateFormat("EEEE dd MMM yyyy", ((ResourceBundle)session.getAttribute("bundle")).getLocale());
    session.setAttribute("dateFormat", dateFormatWithTime);
%>

<c:forEach var="result" items="${results}">
    <tr>
        <c:set var="localizedResult" value="result.type.${result.result}"/>
        <td>${dateFormat.format(result.currTime)}</td>
        <td>${result.execTime} ${bundle['result.time']}</td>
        <td>${result.coordinates}</td>
        <td>${bundle.getString(localizedResult)}</td>
    </tr>
</c:forEach>