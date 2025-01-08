package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Shop;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String errorMessage = null;

        if (username.equals("") || password.equals("")) {
            errorMessage = "请输入完整信息";
            request.setAttribute("errorMessage", errorMessage);
            try {
                request.getRequestDispatcher("editProfile.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UserService userService = new UserService();
            User userInstance = userService.findByName(username);
            if (userInstance != null) {
                errorMessage = "用户名已存在";
                request.setAttribute("errorMessage", errorMessage);
                try {
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                ServletContext context = getServletContext();
                String imagePath = context.getRealPath("/WEB-INF/用户默认头像/默认头像.png");
                byte[] imageByte = null;
                File file = new File(imagePath);
                try (FileInputStream fis = new FileInputStream(file)) {
                    imageByte = new byte[(int) file.length()];
                    fis.read(imageByte);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setUserimage(imageByte);
                userService.userAdd(user);
                int userid = userService.findByName(username).getUserid();
                if(role.equals("shopkeeper")){
                    Shop shop = new Shop();
                    shop.setUserid(userid);
                    shop.setShopname("待编辑");
                    shop.setDescription("待编辑");
                    ShopService shopService = new ShopService();
                    shopService.shopAdd(shop);
                }
                try {
                    response.sendRedirect("accountOperateSuccess.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
