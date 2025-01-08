<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/8
  Time: 上午8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="zhku.zzy.tblikemall.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>销量排行榜</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
<h2>商品销量排行</h2>
<table>
    <tr>
        <th>产品名称</th>
        <th>已完成订单数量</th>
    </tr>
    <%
        List<Product> sortedProducts = (List<Product>) request.getAttribute("sortedProducts");
        for (Product product : sortedProducts) {
    %>
    <tr>
        <td><%= product.getProductname() %></td>
        <td><%= product.getCompletedOrderCount() %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>