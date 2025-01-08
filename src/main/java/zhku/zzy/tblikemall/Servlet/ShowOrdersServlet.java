package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Order;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Entity.Shop;
import zhku.zzy.tblikemall.Service.OrderService;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/ShowOrdersServlet")
public class ShowOrdersServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("username") == null){
            try {
                response.sendRedirect("login.jsp");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UserService userService = new UserService();
        String username = request.getSession().getAttribute("username").toString();
        String role = request.getSession().getAttribute("role").toString();
        int userid = userService.findByName(username).getUserid();
        OrderService orderService = new OrderService();
        if(role.equals("customer")){
            List<Order> orders = orderService.findAllByUser(userid);
            List<Product> products = new ArrayList<>();
            ProductService productService = new ProductService();
            for(Order order : orders){
                Product product = productService.findProductById(order.getProductid());
                products.add(product);
            }
            request.setAttribute("orders", orders);
            request.setAttribute("products", products);
            try {
                request.getRequestDispatcher("showOrders.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(role.equals("shopkeeper")){
            ShopService shopService = new ShopService();
            ProductService productService = new ProductService();
            int shopid = shopService.findShopByUser(userid).getShopid();
            List<Order> orders = orderService.findAll();
            List<Product> products = new ArrayList<>();
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                int productid = order.getProductid();
                Product product = productService.findProductById(productid);
                if (product.getShopid() != shopid) {
                    iterator.remove(); // 删除不符合条件的元素
                }
            }
            for(Order order : orders){
                Product product = productService.findProductById(order.getProductid());
                products.add(product);
            }
            request.setAttribute("orders", orders);
            request.setAttribute("products", products);
            try {
                request.getRequestDispatcher("showOrders.jsp").forward(request,response);
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
