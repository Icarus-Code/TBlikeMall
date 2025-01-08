package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.CartService;

import java.io.IOException;

@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int cartid = Integer.parseInt(request.getParameter("cartid"));
        CartService cartService = new CartService();
        cartService.cartDelete(cartid);
        try {
            response.sendRedirect("ShowCartServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
