<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="zhku.zzy.tblikemall.Service.UserService, zhku.zzy.tblikemall.Entity.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List All Users</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>List of All Users</h1>
<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    <%
        UserService userService = new UserService();
        List<User> users = userService.findAll();
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getUserid() %></td>
        <td><%= user.getUsername() %></td>
        <td><%= user.getPassword() %></td>
        <td><%= user.getRole() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
