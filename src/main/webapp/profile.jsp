<%@ page import="zhku.zzy.tblikemall.Entity.User" %>
<%@ page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>个人信息</title>
    <style>
        /* 添加一些基本的样式 */
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: auto; text-align: center; }
        .avatar { width: 100px; height: 100px; border-radius: 50%; }
        .options { margin-top: 20px; }
        .options a { margin: 0 10px; text-decoration: none; color: blue; }
    </style>
</head>
<body>
<div class="container">
    <%
        User user = (User)request.getAttribute("user");
    %>
    <img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(user.getUserimage())) %>" alt="User Image" width="100" class="avatar">
    <h2><%= user.getUsername() %></h2>
    <div class="options">
        <a href="editProfile.jsp">修改个人信息</a>
        <a href="logout.jsp">退出登录</a>
    </div>
</div>
</body>
</html>