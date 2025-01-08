package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Cart;
import zhku.zzy.tblikemall.Entity.Order;
import zhku.zzy.tblikemall.Service.CartService;
import zhku.zzy.tblikemall.Service.OrderService;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/OrderCreateServlet")
public class OrderCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("username") == null){
            try {
                response.sendRedirect("login.jsp");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] cartids = request.getParameterValues("cartids");
        List<Cart> carts = new ArrayList<>();
        if(cartids == null){
            //处理页面中直接购买
            Cart cart = new Cart();
            int productid = Integer.parseInt(request.getParameter("productid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.setProductid(productid);
            cart.setQuantity(quantity);
            carts.add(cart);
        }
        else{
            //处理购物车购买
            CartService cartService = new CartService();
            ProductService productService = new ProductService();
            for(int i = 0; i < cartids.length; i++){
                Cart cart = cartService.findListByCartId(Integer.parseInt(cartids[i]));
                cart.setProductid(cart.getProductid());
                cart.setQuantity(cart.getQuantity());
                carts.add(cart);
                cartService.cartDelete(Integer.parseInt(cartids[i]));
            }
        }
        UserService userService = new UserService();
        int userid = userService.findByName(request.getSession().getAttribute("username").toString()).getUserid();
        Timestamp time = new Timestamp(new Date().getTime());
        for(Cart cart : carts){
            Order order = new Order();
            order.setUserid(userid);
            order.setProductid(cart.getProductid());
            order.setQuantity(cart.getQuantity());
            order.setStatus("待支付");
            order.setCreateat(time);
            OrderService orderService = new OrderService();
            orderService.orderAdd(order);
        }
        try {
            response.sendRedirect("payPage.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
