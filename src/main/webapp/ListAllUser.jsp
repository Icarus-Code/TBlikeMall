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
        .action-buttons a {
            text-decoration: none;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            margin-right: 5px;
        }
        .edit-button {
            background-color: #4CAF50; /* Green */
        }
        .delete-button {
            background-color: #f44336; /* Red */
        }
    </style>
</head>
<body>
<h1>用户管理</h1>
<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Role</th>
        <th>Actions</th>
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
        <td class="action-buttons">
            <a href="editUser.jsp?userId=<%= user.getUserid() %>" class="edit-button">Edit</a> |
            <a href="deleteUser.jsp?userId=<%= user.getUserid() %>" class="delete-button">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
<br/>
<a href="addUser.jsp" class="edit-button">Add New User</a>
</body>
</html>
