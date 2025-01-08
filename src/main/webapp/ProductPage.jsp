<%@ page import="zhku.zzy.tblikemall.Entity.Product" %>
<%@ page import="java.util.Base64" %>
<%@ page import="zhku.zzy.tblikemall.Entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="zhku.zzy.tblikemall.Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/2
  Time: 上午3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>商品详情</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .product-detail {
            width: 90%;
            max-width: 800px;
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .product-image {
            width: 100%;
            max-width: 300px;
            height: auto;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .back-home {
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #5cb85c;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .back-home:hover {
            background-color: #4cae4c;
        }
        .quantity-selector {
            margin: 20px 0;
        }
        button {
            padding: 10px 20px;
            margin: 5px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #4cae4c;
        }
        .comments {
            margin-top: 30px;
            text-align: left;
        }
        .comment {
            border-bottom: 1px solid #ddd;
            padding: 10px 0;
            display: flex;
            align-items: center;
        }
        .comment img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            margin-right: 15px;
        }
        .comment-content {
            flex-grow: 1;
        }
        .rating {
            display: flex;
        }
        .star {
            font-size: 1.5em;
            color: #ccc;
            margin-right: 5px;
        }
        .filled-star {
            color: gold;
        }

        .delete-button {
            background-color: #d9534f;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
            margin-left: 10px;
            text-decoration: none;
        }
        .delete-button:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
<div class="product-detail">
    <%
        Product product = (Product)request.getAttribute("product");
        String shopname = request.getAttribute("shopname").toString();
    %>
    <%if(product.getProductimage() != null){%>
    <img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(product.getProductimage())) %>" alt="Product Image" class="product-image">
    <%}%>

    <h2><%= product.getProductname() %></h2>
    <p>描述: <%= product.getDescription() %></p>
    <p>价格: ¥<%= product.getPrice() %></p>
    <p>店铺: <%= shopname %></p>
    <p>库存: <%= product.getStock() %></p>

    <div class="quantity-selector">
        <label for="quantity">选择数量:</label>
        <select id="quantity" name="quantity">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
    </div>

    <button onclick="redirectTo('AddCartServlet', '<%= product.getProductid() %>')">加入购物车</button>
    <button onclick="redirectTo('OrderCreateServlet', '<%= product.getProductid() %>')">立即购买</button>
    <a href="ShowProductsServlet" class="back-home">返回首页</a>

    <div class="comments">
        <%
            List<Comment> comments = (List<Comment>)request.getAttribute("comments");
            List<User> users = (List<User>)request.getAttribute("users");
        %>
        <h3>用户评论</h3>
        <%
            for(int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                User user = users.get(i);
        %>
        <div class="comment">
            <%if(user.getUserimage() != null){%><img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(user.getUserimage())) %>" alt="User Image" width="100" class="avatar"><%}%>
            <div class="comment-content">
                <p><strong><%= user.getUsername() %></strong></p>
                <div class="rating">
                    <% for (int j = 1; j <= 5; j++) { %>
                    <% if (j <= comment.getRate()) { %>
                    <span class="star filled-star">★</span>
                    <% } else { %>
                    <span class="star">☆</span>
                    <% } %>
                    <% } %>
                </div>
                <p><%= comment.getComment() %></p>
                <% if(session.getAttribute("username") != null && session.getAttribute("username").equals(user.getUsername())) { %>
                <a href="DeleteCommentServlet?commentid=<%= comment.getCommentid()%>&productid=<%= product.getProductid()%>" class="delete-button">删除</a>
                <% } %>
            </div>
        </div>
        <% } %>
    </div>

    <script>
        function redirectTo(servletName, productId) {
            var quantity = document.getElementById('quantity').value;
            window.location.href = servletName + '?productid=' + productId + '&quantity=' + quantity;
        }
    </script>
</div>
</body>
</html>