package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/allListServlet")
public class allListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        UserService userService = new UserService();
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        try {
            request.getRequestDispatcher("allList.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
