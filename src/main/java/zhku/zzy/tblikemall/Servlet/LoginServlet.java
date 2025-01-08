package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.UserService;
import zhku.zzy.tblikemall.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        if (username != null && password != null) {
            if (username.equals("") || password.equals("")) {
                errorMessage = "请输入有效信息";
                request.setAttribute("errorMessage", errorMessage);
                try {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (userService.isValid(username, password, role)) {
                        User user = userService.findByName(username);
                        request.getSession().setAttribute("username", user.getUsername());
                        request.getSession().setAttribute("role", user.getRole());
                        handleRedirect(request, response, user.getRole()); // Handle redirection based on user role
                    } else {
                        errorMessage = "用户名或密码错误";
                        request.setAttribute("errorMessage", errorMessage);
                        try {
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        } catch (ServletException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    private void handleRedirect(HttpServletRequest request, HttpServletResponse response, String role) {
        try {
            switch (role) {
                case "admin":
                    response.sendRedirect("allListServlet");
                    break;
                case "customer":
                    response.sendRedirect("ShowProductsServlet");
                    break;
                case "shopkeeper":
                    response.sendRedirect("ShowProductsServlet");
                    break;
                default:
                    response.sendRedirect("login.jsp"); // Redirect to login if role is unknown
                    break;
            }
        } catch (IOException e) {
            logger.error("Error redirecting to appropriate home page", e);
        }
    }
}
