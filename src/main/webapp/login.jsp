<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录页面</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px; /* 增加窗口宽度 */
        }
        .login-container h2 {
            text-align: center;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }
        .form-group label {
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group input[type="password"],
        .form-group select {
            width: 100%; /* 使输入框宽度适应容器 */
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .form-actions {
            display: flex;
            justify-content: space-between;
            width: 100%;
        }
        .form-actions input[type="submit"],
        .form-actions input[type="reset"] {
            width: 48%; /* 调整按钮宽度 */
            padding: 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .form-actions input[type="submit"] {
            background-color: #4CAF50;
            color: white;
        }
        .form-actions input[type="reset"] {
            background-color: #f44336;
            color: white;
        }
        .form-actions input[type="submit"]:hover,
        .form-actions input[type="reset"]:hover {
            opacity: 0.8;
        }
        .register-link {
            text-align: center; /* 居中对齐超链接文本 */
            margin-top: 20px;
        }
        .register-link a {
            color: gray;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>登录账号</h2>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="role">身份:</label>
            <select id="role" name="role">
                <option value="admin">管理员</option>
                <option value="shopkeeper">店家</option>
                <option value="customer">顾客</option>
            </select>
        </div>
        <div class="form-actions">
            <input type="submit" value="登录">
            <input type="reset" value="重置">
        </div>
    </form>
    <div class="register-link">
        <a href="register.jsp">还没有账号？去注册</a>
    </div>
</div>
</body>
</html>


