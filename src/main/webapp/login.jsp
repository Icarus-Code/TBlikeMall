<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h2>登录账号</h2>
    <form action="LoginServlet" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password"><br><br>
        <input type="radio" id="customer" name="role" value="customer" checked>
        <label for="customer">顾客</label><br>
        <input type="radio" id="shopkeeper" name="role" value="shopkeeper">
        <label for="shopkeeper">店家</label><br>
        <input type="radio" id="admin" name="role" value="admin">
        <label for="admin">管理员</label><br>
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
    <a href="register.jsp"><button>注册</button></a>
    <%
        if (request.getAttribute("errorMessage") != null) {
            out.println("<p style='color:red;'>" + request.getAttribute("errorMessage") + "</p>");
        }
    %>
</body>
</html>