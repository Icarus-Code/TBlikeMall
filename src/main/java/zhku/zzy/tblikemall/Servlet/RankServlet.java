package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/RankServlet")
public class RankServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        ProductService productService = new ProductService();
        List<Product> sortedProducts = productService.findAllSortedByCompletedOrders();
        request.setAttribute("sortedProducts", sortedProducts);

        try {
            request.getRequestDispatcher("Rank.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
