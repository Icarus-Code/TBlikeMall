<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/3
  Time: 上午3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改店铺信息</title>
</head>
<body>
<p>店铺信息修改界面</p>
<hr>
<form action="UpdateShopServlet" method="post">
    <label for="shopname">店铺名:</label>
    <input type="text" id="shopname" name="shopname"><br><br>
    <label for="description">描述:</label>
    <input type="text" id="description" name="description"><br><br>
    <input type="submit" value="修改">
    <input type="reset" value="重置">
</form>
<%
    if (request.getAttribute("errorMessage") != null) {
        out.println("<p style='color:red;'>" + request.getAttribute("errorMessage") + "</p>");
    }
%>
</body>
</html>