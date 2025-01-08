<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/2
  Time: 上午12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人信息</title>
</head>
<body>
    <p>个人信息修改界面</p>
    <hr>
    <form action="UpdateUserServlet" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password"><br><br>
        <%
            if(request.getParameter("selectedname") != null){
        %>
                <input type="hidden" name="selectedname" value="<%= request.getParameter("selectedname") %>">
        <%}else{%>
                <input type="hidden" name="selectedname" value="<%= session.getAttribute("username") %>">
        <%}%>
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
