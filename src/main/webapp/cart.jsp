<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>购物车</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        #cart {
            max-width: 600px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .product {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .product:last-child {
            border-bottom: none;
        }
        .product span {
            flex: 1;
        }
        .product input[type="number"] {
            width: 60px;
            text-align: center;
        }
        .total {
            text-align: right;
            margin-top: 20px;
            font-size: 1.2em;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>购物车</h1>
<div id="cart">
    <%
        // 模拟一些假数据
        String[] productNames = {"acerPC", "DellLaptop", "AppleiPhone", "SamsungTV", "SonyCamera"};
        BigDecimal[] prices = {new BigDecimal("7500"), new BigDecimal("5500"), new BigDecimal("9999"), new BigDecimal("4500"), new BigDecimal("3500")};
        int[] quantities = {1, 1, 1, 1, 1}; // 默认数量为1

        for (int i = 0; i < productNames.length; i++) {
    %>
    <div class="product">
        <span><%= productNames[i] %></span>
        <span>价格: <%= prices[i] %></span>
        <input type="number" value="<%= quantities[i] %>" min="1" data-price="<%= prices[i] %>" onchange="updateTotal()">
        <span>总计: <span class="subtotal" data-price="<%= prices[i] %>"><%= prices[i].multiply(new BigDecimal(quantities[i])) %></span></span>
    </div>
    <% } %>
</div>
<div class="total">
    总计金额: <span id="totalAmount">0</span>
</div>

<script>
    function updateTotal() {
        let total = 0;
        document.querySelectorAll('.product').forEach(item => {
            const quantity = item.querySelector('input[type="number"]').value;
            const price = parseFloat(item.querySelector('input[type="number"]').dataset.price);
            const subtotal = quantity * price;
            item.querySelector('.subtotal').textContent = subtotal.toFixed(2);
            total += subtotal;
        });
        document.getElementById('totalAmount').textContent = total.toFixed(2);
    }
</script>
</body>
</html>
