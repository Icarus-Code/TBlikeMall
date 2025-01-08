<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/3
  Time: 上午3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="zhku.zzy.tblikemall.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>简易电商界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #333;
            color: white;
            overflow: hidden;
            height: 50px; /* Full height */
        }
        .navbar a {
            padding: 15px 20px;
            float: left;
            display: block;
            color: white;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .content {
            margin-left: 200px; /* Same as width of the sidenav */
        }
        .search-box {
            padding: 20px;
            display: flex;
            align-items: center;
        }
        .search-box input[type="text"] {
            flex: 1;
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .search-box button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }
        .search-box button:hover {
            opacity: 0.8;
        }
        .products {
            display: flex;
            flex-wrap: wrap;
            padding: 0 20px;
        }
        .product {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            border: 1px solid #ddd;
            width: calc(25% - 40px);
            margin: 20px;
            padding: 20px;
            box-sizing: border-box;
        }
        /* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 600px) {
            .navbar a {
                float: none;
                display: block;
            }
            .content {
                margin-left: 0;
            }
            .product {
                width: calc(100% - 40px);
            }
        }

        .button-style {
            background-color: #4CAF50; /* 按钮背景色 */
            color: white; /* 文字颜色 */
            padding: 10px 20px; /* 内边距 */
            text-align: center; /* 文字居中 */
            text-decoration: none; /* 去除下划线 */
            display: inline-block; /* 使链接并排显示 */
            margin: 5px; /* 外边距，增加按钮之间的空间 */
            border-radius: 5px; /* 圆角边框 */
            transition: background-color 0.3s; /* 平滑背景色变化 */
        }

        .button-container {
            display: flex; /* 使用flex布局 */
            justify-content: space-between; /* 两端对齐，按钮之间有空间 */
            margin-top: 10px; /* 与上方内容的间隔 */
        }
    </style>
</head>
<body>

<div class="navbar">
    <a href="ShowProductsServlet">首页</a>
    <a href="addProduct.jsp">商品上架</a>
    <a href="ShowOrdersServlet">店铺订单</a>
    <a href="ProfileServlet">店铺和个人信息</a>
    <span style="text-align: right">欢迎用户:<%
        if(session.getAttribute("username") == null)out.print("游客");
        else out.print(session.getAttribute("username"));
    %></span>
</div>

<div class="content">
    <div class="products">
        <h1>我的商品</h1>
        <%
            List<Product> productList = (List<Product>) request.getAttribute("Products");
            for (Product product : productList) {
        %>
        <div class="product">
            <img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(product.getProductimage())) %>" alt="Product Image" width="100">
            <p>名称:<%= product.getProductname() %></p>
            <p>描述:<%= product.getDescription() %></p>
            <p>价格:<%= product.getPrice() %></p>
            <div class="button-container">
                <a href="ShowEditProductServlet?productid=<%= product.getProductid()%>" class="button-style">编辑</a>
                <a href="DeleteProductServlet?productid=<%= product.getProductid()%>" class="button-style">删除</a>
            </div>
        </div>
        <% } %>
    </div>
</div>

</body>
</html>