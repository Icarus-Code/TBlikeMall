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
    </style>
</head>
<body>

<div class="navbar">
    <a href="#home">首页</a>
    <a href="cart.jsp">购物车</a>
    <a href="#orders">个人订单</a>
    <a href="IdentifyServlet">个人信息</a>
    <span style="text-align: right">欢迎用户:<%
        if(session.getAttribute("username") == null)out.print("游客");
        else out.print(session.getAttribute("username"));
    %></span>
</div>

<div class="content">
    <div class="search-box">
        <form action="SearchServlet" method="get">
            <input type="text" name="q" placeholder="搜索商品...">
            <button type="submit">搜索</button>
        </form>
    </div>
    <div class="products">
        <%
            List<Product> productList = (List<Product>) request.getAttribute("Products");
            for (Product product : productList) {
        %>
        <div class="product">
            <img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(product.getProductimage())) %>" alt="Product Image" width="100">
            <p>名称:<%= product.getProductname() %></p>
            <p>描述:<%= product.getDescription() %></p>
            <p>价格:<%= product.getPrice() %></p>
        </div>
        <% } %>
    </div>
</div>

</body>
</html>