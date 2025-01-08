package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.ProductService;

import java.io.IOException;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int productid = Integer.parseInt(request.getParameter("productid"));
        ProductService productService = new ProductService();
        productService.deleteProduct(productid);
        try {
            response.sendRedirect("ShowProductsServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
