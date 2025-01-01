<%@ page import="java.util.List" %>
<%@ page import="zhku.zzy.tblikemall.Dao.UserDao" %>
<%@ page import="zhku.zzy.tblikemall.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>查询结果</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<script>
    function Update(name){
        window.location.href="update.jsp?name="+name;
    }
    function Delete(name){
        if(confirm("确定要删除吗？")){
            window.location.href="delete.jsp?name="+name;
        }
    }
</script>
<body>
<table>
    <h2>所有用户信息</h2><hr>
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>用户类型</th>
        <th>操作</th>
    </tr>
    <%
        List<User> results = (List<User>)request.getAttribute("users");
        if (results != null) {
            for (User user : results) {
    %>
    <tr>
        <td><%= user.getUserid()%></td>
        <td><%= user.getUsername()%></td>
        <td>
            <%
                if(user.getUsername().equals(session.getAttribute("username"))){
                    out.println(user.getPassword());
                }else{
                    out.println("******");
                }
            %>
        </td>
        <td><%= user.getRole()%></td>
        <td><button onclick="Update('<%= user.getUsername()%>')">修改</button>
            <button onclick="Delete('<%= user.getUsername()%>')">删除</button>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<button onclick="window.location.href='Operate.jsp'">返回</button>
</body>
</html>
