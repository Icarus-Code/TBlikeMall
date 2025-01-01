<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登出</title>
</head>
<body>
<%
    session.removeAttribute("username");
    response.sendRedirect("ShowProductsServlet");
%>
</body>
</html>
