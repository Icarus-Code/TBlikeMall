<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/2
  Time: 上午6:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="zhku.zzy.tblikemall.Entity.Cart" %>
<%@ page import="zhku.zzy.tblikemall.Entity.Product" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车页面</title>
</head>
<body>
<h2>我的购物车</h2>
<form action="PurchaseServlet" method="post">
    <table border="1">
        <tr>
            <th>选择</th>
            <th>图片</th>
            <th>商品名称</th>
            <th>价格</th>
            <th>数量</th>
            <th>小计</th>
        </tr>
        <%
            List<Cart> carts = (List<Cart>) request.getAttribute("carts");
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (carts != null && products != null) {
                for (int i = 0; i < carts.size(); i++) {
                    Cart cart = carts.get(i);
                    Product product = products.get(i); %>
        <tr>
            <td><input type="checkbox" name="selectedItems" value="<%= cart.getCartid() %>"></td>
            <td><img src="data:image/jpeg;base64,<%= new String(Base64.getEncoder().encode(product.getProductimage())) %>" alt="Product Image" width="50px"></td>
            <td><%= product.getProductname() %></td>
            <td><%= product.getPrice() %></td>
            <td><%= cart.getQuantity() %></td>
            <td><%
                // 将int转换为BigDecimal进行乘法运算
                BigDecimal quantityAsBigDecimal = new BigDecimal(cart.getQuantity());
                BigDecimal totalPrice = product.getPrice().multiply(quantityAsBigDecimal);
                out.print(totalPrice.toPlainString());
            %></td>
        </tr>
        <%      }
        }
        %>
    </table>
    <input type="submit" value="立即购买">
</form>
</body>
</html>