<%@ page import="zhku.zzy.tblikemall.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="zhku.zzy.tblikemall.Entity.Product" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.math.BigDecimal" %><%--
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
    <a href="ShowProductsServlet">返回首页</a>
    <h1>我的订单</h1>
    <div class="order-list">
        <%
            // 从请求属性中获取订单项列表
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (orders != null) {
                for (int i = 0;i< orders.size();i++) {
                    Order order = orders.get(i);
                    Product product = products.get(i);
        %>
        <div class="order-item">
            <div class="order-item-info">
                <div>
                    <%
                        if(product != null){
                    %>
                    <img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(product.getProductimage())) %>" alt="Product Image" width="50px">
                    <p class="order-item-name"><%= product.getProductname() %></p>
                    <p>单价: ￥<%= product.getPrice() %></p>
                    <%}
                    else{%>
                        <p>商品已下架</p>
                    <%}%>
                    <p>数量: <%= order.getQuantity() %></p>
                    <p>
                        状态:
                        <%
                            String status = order.getStatus();
                            String color = "";
                            if ("待支付".equals(status)) {
                                color = "red";
                            } else if ("待发货".equals(status)) {
                                color = "orange";
                            } else if ("待收货".equals(status)) {
                                color = "blue";
                            } else if ("已完成".equals(status)) {
                                color = "green";
                            } else {
                                color = "black"; // 默认颜色为黑色
                            }
                        %>
                        <span style="color: <%= color %>"><%= status %></span>
                    </p>
                    <p class="order-item-total">总金额: ￥<%
                        // 将int转换为BigDecimal进行乘法运算
                        BigDecimal quantityAsBigDecimal = new BigDecimal(order.getQuantity());
                        BigDecimal totalPrice = product.getPrice().multiply(quantityAsBigDecimal);
                        out.print(totalPrice.toPlainString());
                    %></p>
                </div>
            </div>
            <%if(session.getAttribute("role").toString().equals("customer")){
                if(order.getStatus().toString().equals("待支付")){%>
                    <a href="OrderUpdateServlet?orderId=<%= order.getOrderid() %>&action=支付" class="payment-button">支付</a>
                    <a href="OrderUpdateServlet?orderId=<%= order.getOrderid() %>&action=删除" class="payment-button">删除</a>
                <%}%>
                <%if(order.getStatus().toString().equals("待收货")){%>
                    <a href="OrderUpdateServlet?orderId=<%= order.getOrderid() %>&action=收货" class="payment-button">收货</a>
                <%}%>
                <%if(order.getStatus().toString().equals("已完成")){%>
                    <a href="addComment.jsp?productid=<%= order.getProductid() %>&action=去评价" class="payment-button">去评价</a>
                <%}%>
            <%}%>

            <%if(session.getAttribute("role").toString().equals("shopkeeper")){
                if(order.getStatus().toString().equals("待发货")){%>
                    <a href="OrderUpdateServlet?orderId=<%= order.getOrderid() %>&action=发货" class="payment-button">发货</a>
                <%}%>
            <%}%>

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
