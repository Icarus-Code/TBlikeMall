package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String errorMessage = null;

        if(username != null && password != null) {
            if(username.equals("") || password.equals("")){
                errorMessage = "请输入有效信息";
                request.setAttribute("errorMessage", errorMessage);
                try {
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                UserService userService = new UserService();
                try {
                    if (userService.isValid(username, password, role)) {
                        request.getSession().setAttribute("username", username);
                        response.sendRedirect("ShowProductsServlet");
                    } else {
                        errorMessage = "用户名或密码错误";
                        request.setAttribute("errorMessage", errorMessage);
                        try {
                            request.getRequestDispatcher("login.jsp").forward(request,response);
                        } catch (ServletException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
