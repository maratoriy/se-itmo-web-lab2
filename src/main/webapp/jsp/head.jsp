<%@ page contentType="text/html;charset=UTF-8"%>
<head>
    <meta charset="utf-8">
    <meta name="author" content="${bundle['header.author']}">
    <meta name="description" content="${bundle['head.description']}">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>${requestScope['title']}</title>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
</head>