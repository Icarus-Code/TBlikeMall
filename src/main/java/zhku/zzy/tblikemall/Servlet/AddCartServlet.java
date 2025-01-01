package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Cart;
import zhku.zzy.tblikemall.Service.CartService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int productid = Integer.parseInt(request.getParameter("productid"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        UserService userService = new UserService();
        int userid = userService.findByName(request.getSession().getAttribute("username").toString()).getUserid();
        Cart cart = new Cart();
        cart.setProductid(productid);
        cart.setQuantity(quantity);
        cart.setUserid(userid);
        CartService cartService = new CartService();
        cartService.cartAdd(cart);
        try {
            response.sendRedirect("addCartSuccess.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
