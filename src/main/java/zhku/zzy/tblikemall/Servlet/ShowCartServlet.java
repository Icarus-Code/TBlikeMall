package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Cart;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Service.CartService;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getSession().getAttribute("username").toString();
        UserService userService = new UserService();
        int userid = userService.findByName(username).getUserid();
        CartService cartService = new CartService();
        List<Cart> carts = cartService.findAll(userid);
        List<Product> products = new ArrayList<>();
        ProductService productService = new ProductService();
        for(Cart cart : carts){
            Product product = productService.findProductById(cart.getProductid());
            products.add(product);
        }
        request.setAttribute("carts", carts);
        request.setAttribute("products", products);
        System.out.println("Carts size: " + carts.size());
        System.out.println("Products size: " + products.size());

        try {
            request.getRequestDispatcher("showCart.jsp").forward(request,response);
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
