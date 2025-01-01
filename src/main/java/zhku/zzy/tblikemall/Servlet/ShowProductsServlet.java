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

@WebServlet("/ShowProductsServlet")
public class ShowProductsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        ProductService productService = new ProductService();
        List<Product> Products = productService.findAll();
        request.setAttribute("Products", Products);
        try {
            request.getRequestDispatcher("customerHome.jsp").forward(request,response);
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
