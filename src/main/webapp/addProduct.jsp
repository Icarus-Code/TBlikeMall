<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/7
  Time: 下午11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品上架</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            color: #666;
        }
        input[type="text"],
        input[type="number"],
        textarea,
        input[type="file"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            padding: 10px;
            background: #5cb85c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background: #4cae4c;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>商品上架</h2>
    <form action="AddProductServlet" method="post" enctype="multipart/form-data">
        <label for="productname">商品名称:</label>
        <input type="text" id="productname" name="productname" required>

        <label for="description">描述:</label>
        <textarea id="description" name="description" required></textarea>

        <label for="price">价格:</label>
        <input type="number" id="price" name="price" step="0.01" required>

        <label for="stock">库存:</label>
        <input type="number" id="stock" name="stock" required>

        <label for="productimage">商品图片:</label>
        <input type="file" id="productimage" name="productimage" required>

        <input type="submit" value="上架商品">
    </form>
</div>
</body>
</html>