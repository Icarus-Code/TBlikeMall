<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册页面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .register-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 350px; /* 统一宽度 */
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: inline-block;
            margin-top: 10px;
            color: #555;
        }
        input[type="text"], input[type="password"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="radio"] {
            margin-right: 5px;
        }
        .radio-group {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center; /* 居中对齐 */
        }
        .button-group {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        input[type="submit"], input[type="reset"] {
            flex: 1;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin: 0 5px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }
        input[type="reset"] {
            background-color: #f44336;
            color: white;
        }
        .footer-link {
            text-align: center;
            margin-top: 10px;
        }
        .footer-link a {
            color: #888;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>注册账号</h2>
    <form action="RegisterServlet" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username"><br><br>
        <label for="password">密码:</label>
        <input type="password" id="password" name="password"><br><br>
        <div class="radio-group">
            <input type="radio" id="customer" name="role" value="customer" checked>
            <label for="customer">顾客</label>
            <input type="radio" id="shopkeeper" name="role" value="shopkeeper">
            <label for="shopkeeper">店家</label>
        </div>
        <div class="button-group">
            <input type="submit" value="登录">
            <input type="reset" value="重置">
        </div>
    </form>
    <div class="footer-link">
        <a href="login.jsp">已有账号？去登录</a>
    </div>
</div>
</body>
</html>
