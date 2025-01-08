package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet("/ShowProductsServlet")
public class ShowProductsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Object username = request.getSession().getAttribute("username");
        Object role = request.getSession().getAttribute("role");
        ProductService productService = new ProductService();
        List<Product> Products = productService.findAll();
        if(role == null || role.toString().equals("customer")){
            request.setAttribute("Products", Products);
            try {
                request.getRequestDispatcher("customerHome.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(role.equals("shopkeeper")){
            UserService userService = new UserService();
            ShopService shopService = new ShopService();
            int userid = userService.findByName(username.toString()).getUserid();
            int shopid = shopService.findShopByUser(userid).getShopid();
            Iterator<Product> iterator = Products.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getShopid() != shopid) {
                    iterator.remove(); // 删除不符合条件的元素
                }
            }
            request.setAttribute("Products", Products);
            try {
                request.getRequestDispatcher("shopkeeperHome.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
