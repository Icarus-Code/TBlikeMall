<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/8
  Time: 上午4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>评论成功！</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f9;
        }
        .success-message {
            text-align: center;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .success-message h2 {
            color: #4CAF50;
        }
        .success-message p {
            margin: 20px 0;
        }
        .success-message a {
            display: block;
            width: 100px;
            padding: 10px;
            margin: 10px auto;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
        }
        .success-message a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="success-message">
    <h2>成功提交评论！</h2>
    <p>感谢你的评论反馈.</p>
    <a href="ShowOrdersServlet">返回订单页面</a>
    <a href="ShowAproductServlet?productid=<%= request.getAttribute("productid")%>" class="button-style">查看评论</a>
</div>
</body>
</html>