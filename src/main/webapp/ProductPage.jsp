<%--
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
        body { font-family: Arial, sans-serif; }
        .product-detail { width: 80%; margin: 20px auto; text-align: center; }
        .product-image { width: 200px; height: 200px; }
        .quantity-selector { margin: 20px 0; }
        button { padding: 10px 20px; margin: 5px; }
    </style>
</head>
<body>
<div class="product-detail">
    <%

    %>
    <img src="data:image/jpeg;base64,<%= product.getImageBase64() %>" alt="商品图片" class="product-image">
    <h2><%= product.getName() %></h2>
    <p>描述: <%= product.getDescription() %></p>
    <p>价格: ¥<%= product.getPrice() %></p>
    <p>店铺: <%= product.getStoreName() %></p>
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

    <button onclick="addToCart()">加入购物车</button>
    <button onclick="buyNow()">立即购买</button>
</div>
</body>
</html>
