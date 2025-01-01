package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.UserService;
import zhku.zzy.tblikemall.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (userService.isValid(username, password)) {
            User user = userService.findByName(username);
            request.getSession().setAttribute("identity", user); // Store user identity in session
            handleRedirect(request, response, user); // Handle redirection based on user role
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirect back to login with error message
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    private void handleRedirect(HttpServletRequest request, HttpServletResponse response, User user) {
        if (user == null) {
            try {
                response.sendRedirect("login.jsp"); // Redirect to login if user is null
            } catch (IOException e) {
                logger.error("Error redirecting to login page", e);
            }
            return;
        }

        String role = user.getRole();
        if (role == null || role.isEmpty()) {
            try {
                response.sendRedirect("login.jsp"); // Redirect to login if role is unknown or empty
            } catch (IOException e) {
                logger.error("Error redirecting to login page", e);
            }
            return;
        }

        try {
            switch (role) {
                case "admin":
                    response.sendRedirect("customerHome.jsp");
                    break;
                case "customer":
                    response.sendRedirect("customerHome.jsp");
                    break;
                case "shopkeeper":
                    response.sendRedirect("customerHome.jsp");
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
