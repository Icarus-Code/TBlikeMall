package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Service.ProductService;

import java.io.IOException;

@WebServlet("/ShowEditProductServlet")
public class ShowEditProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int productid = Integer.parseInt(request.getParameter("productid"));
        ProductService productService = new ProductService();
        Product product = productService.findProductById(productid);
        request.setAttribute("product", product);
        try {
            request.getRequestDispatcher("editProduct.jsp").forward(request,response);
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
