<%@ page import="zhku.zzy.tblikemall.Entity.Order" %><%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/2
  Time: 下午11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>个人订单界面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .order-item {
            border-bottom: 1px solid #eee;
            padding: 10px 0;
        }
        .order-item:last-child {
            border-bottom: none;
        }
        .order-item img {
            width: 100px;
            height: 100px;
            object-fit: cover;
            margin-right: 20px;
        }
        .order-item-info {
            display: flex;
            align-items: center;
        }
        .order-item-name, .order-item-quantity, .order-item-total {
            margin: 0;
            padding: 0;
            font-size: 16px;
        }
        .payment-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 0;
            cursor: pointer;
            border-radius: 5px;
        }
        .payment-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>我的订单</h1>
    <div class="order-list">
        <%
            // 从请求属性中获取订单项列表
            List<Order> orders = (List<Order>) request.getAttribute("orderItems");
            if (orderItems != null) {
                for (OrderItem item : orderItems) {
        %>
        <div class="order-item">
            <div class="order-item-info">
                <img src="<%= item.getImageUrl() %>" alt="Product Image">
                <div>
                    <p class="order-item-name"><%= item.getName() %></p>
                    <p>数量: <%= item.getQuantity() %></p>
                    <p>单价: ￥<%= item.getPrice() %></p>
                    <p class="order-item-total">总金额: ￥<%= (item.getQuantity() * item.getPrice()) %></p>
                </div>
            </div>
            <a href="payServlet?orderId=<%= item.getId() %>" class="payment-button">支付</a>
        </div>
        <%
            }
        } else {
        %>
        <p>您当前没有订单。</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
