<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="bundle" scope="session" type="java.util.ResourceBundle"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="ru">
<%
    request.setAttribute("title", "Web.Lab#2");
%>
<%@ include file="/jsp/head.jsp" %>
<body>
<%@ include file="/jsp/header.jsp" %>
<div class="content light-theme background">
    <div id="graph__wrapper" class="content__block">
        <canvas id="graph"></canvas>
    </div>
    <div id="request-form__wrapper" class="content__block">
        <form id="request-form" class="savable-form" method="POST" action="${pageContext.request.contextPath}">
            <div class="input-group button-group row-flex">
                <h3 id="x-label" class="input-group__header">X</h3>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="-3">-3</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="-2">-2</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="-1">-1</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="0">0</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="1">1</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="2">2</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="3">3</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="4">4</button>
                <button type="button" name="x" class="button-group__button value-button light-theme accent" value="5">5</button>
                <input name="x" class="value-button__input save-value" type="hidden">
            </div>
            <div class="input-group column-flex">
                <h3 id="y-label" class="input-group__header">Y</h3>
                <div class="text_field">
                    <input id="text_field-1" type="text" name="y" class="text_field_input save-value"
                           placeholder="[-5;5]">
                </div>
            </div>
            <div class="input-group button-group row-flex">
                <h3 id="r-label" class="input-group__header">R</h3>
                <button type="button" name="r" class="button-group__button value-button light-theme accent" value="1">1</button>
                <button type="button" name="r" class="button-group__button value-button light-theme accent" value="2">2</button>
                <button type="button" name="r" class="button-group__button value-button light-theme accent" value="3">3</button>
                <button type="button" name="r" class="button-group__button value-button light-theme accent" value="4">4</button>
                <button type="button" name="r" class="button-group__button value-button light-theme accent" value="5">5</button>
                <input name="r" default="R" value="R" class="value-button__input save-value" type="hidden">
            </div>
            <div class="input-group row-flex">
                <input type="submit" id="send-request" class="submit-button" value="${bundle['form.submit']}">
                <input type="button" class="reset-form submit-button" value="${bundle['form.reset']}">
                <input type="button" id="clear-request" class="submit-button" value="${bundle['form.clear']}">
            </div>
        </form>
    </div>
    <div id="history__wrapper" class="content__block">
    <table id="history" class="table light-theme">
        <%@ include file="jsp/table.jsp" %>
    </table>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>