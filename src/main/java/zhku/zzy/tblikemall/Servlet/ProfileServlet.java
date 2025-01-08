package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Shop;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("username") == null){
            try {
                response.sendRedirect("login.jsp");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String username = request.getSession().getAttribute("username").toString();
        String role = request.getSession().getAttribute("role").toString();
        UserService userService = new UserService();
        User user = userService.findByName(username);
        if(role.equals("shopkeeper")){
            ShopService shopService = new ShopService();
            Shop shop = shopService.findShopByUser(user.getUserid());
            request.setAttribute("shop", shop);
        }
        request.setAttribute("user", user);
        try {
            request.getRequestDispatcher("profile.jsp").forward(request,response);
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
