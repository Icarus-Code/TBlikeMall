<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/2
  Time: 下午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>支付页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #333;
        }
        .payment-options {
            margin-top: 20px;
        }
        .payment-options button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .payment-options button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>订单创建成功</h2>
    <div class="payment-options">
        <button onclick="location.href='ShowProductsServlet';">稍后再说</button>
        <button onclick="location.href='ShowOrdersServlet';">查看订单</button>
    </div>
</div>
</body>
</html>