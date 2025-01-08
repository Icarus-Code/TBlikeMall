<%--
  Created by IntelliJ IDEA.
  User: Spreps
  Date: 2025/1/8
  Time: 上午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Comment</title>
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
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 500px; /* 增加宽度 */
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 8px;
        }
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        .form-group input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            border: none;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #4cae4c;
        }
        #star-rating .star {
            font-size: 24px;
            color: #ccc;
            cursor: pointer;
            transition: color 0.3s; /* 平滑颜色变化 */
        }
        #star-rating .star:hover,
        #star-rating .star.selected {
            color: gold;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const stars = document.querySelectorAll('#star-rating .star');
            const ratingInput = document.getElementById('rating');

            stars.forEach(star => {
                star.addEventListener('click', function() {
                    const value = this.getAttribute('data-value');
                    ratingInput.value = value;
                    // 清除之前的选中状态
                    stars.forEach(s => s.classList.remove('selected'));
                    // 设置新的选中状态
                    for (let i = 0; i < stars.length; i++) {
                        if (i < value) {
                            stars[i].classList.add('selected');
                        } else {
                            stars[i].classList.remove('selected');
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="form-container">
    <h2>评价界面</h2>
    <form action="AddCommentServlet" method="post">
        <input type="hidden" name="productid" value="<%= request.getParameter("productid")%>">
        <div class="form-group">
            <label for="rating">评分:</label>
            <div id="star-rating">
                <span class="star" data-value="1">★</span>
                <span class="star" data-value="2">★</span>
                <span class="star" data-value="3">★</span>
                <span class="star" data-value="4">★</span>
                <span class="star" data-value="5">★</span>
                <input type="hidden" id="rating" name="rating">
            </div>
        </div>
        <div class="form-group">
            <label for="comment">写下你的评论:</label>
            <textarea id="comment" name="comment" required></textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="提交评论">
        </div>
    </form>
</div>
</body>
</html>