package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.UserService;

@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        UserService userService = new UserService();
        String username = request.getSession().getAttribute("username").toString();
        int userid = userService.findByName(username).getUserid();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
