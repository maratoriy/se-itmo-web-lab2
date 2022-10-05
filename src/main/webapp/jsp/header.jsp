<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="languages" scope="application" class="com.moratorium.locales.ApplicationLanguagesContainer"/>
<header class="header light-theme primary">
    <div class="header__inner">
        <a href="${pageContext.request.contextPath}/" class="logo-container">
            <h1>WEB.<span>LAB2</span></h1>
        </a>
        <div class="contact">
            <h1>${bundle['header.author']}<span>${bundle['header.var']} 21700</span></h1>
            <form id="change-language-form" method="post">
                <select id="lang-switch" name="language">
                    <c:set var="bundleLocale" value="<%=bundle.getLocale()%>"/>
                    <c:forEach var="locale" items="${languages}">
                        <option value="${locale.toString()}" ${locale.equals(bundleLocale) ? "selected" : ""}>
                            ${bundle.getString(locale.toString())}
                        </option>>
                    </c:forEach>
                </select>
            </form>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
    </div>
</header>
