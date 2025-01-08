package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;

@WebServlet("/UpdateShopServlet")
public class UpdateShopServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String shopname = request.getParameter("shopname");
        String description = request.getParameter("description");
        String errorMessage = null;

        if (shopname.equals("") || description.equals("")) {
            errorMessage = "请输入完整信息";
            request.setAttribute("errorMessage", errorMessage);
            try {
                request.getRequestDispatcher("editProfile.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            ShopService shopService = new ShopService();
            UserService userService = new UserService();
            int userid = userService.findByName(request.getSession().getAttribute("username").toString()).getUserid();
            shopService.shopUpdate(shopname,description,userid);
            try {
                response.sendRedirect("ProfileServlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
