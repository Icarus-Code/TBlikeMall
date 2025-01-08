package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getSession().getAttribute("username").toString();
        UserService userService = new UserService();
        ShopService shopService = new ShopService();
        int shopid = shopService.findShopByUser(userService.findByName(username).getUserid()).getShopid();
        // 获取表单字段
        String productName = null;
        String description = null;
        BigDecimal price = null;
        int stock = 0;
        byte[] imageBytes = null;

        // 获取所有表单项
        Collection<Part> parts = null;
        try {
            parts = request.getParts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        for (Part part : parts) {
            String partName = part.getName();
            if (partName.equals("productname")) {
                productName = request.getParameter(partName);
            } else if (partName.equals("description")) {
                description = request.getParameter(partName);
            } else if (partName.equals("price")) {
                String priceStr = request.getParameter(partName);
                price = new BigDecimal(priceStr);
            } else if (partName.equals("stock")) {
                String stockStr = request.getParameter(partName);
                stock = Integer.parseInt(stockStr);
            } else if (partName.equals("productimage")) {
                try {
                    imageBytes = part.getInputStream().readAllBytes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 创建 Product 对象并设置属性
        Product product = new Product();
        product.setProductname(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setShopid(shopid);
        product.setProductimage(imageBytes); // 设置图片的 byte 数组

        // 使用 ProductService 保存商品信息到数据库
        ProductService productService = new ProductService();
        productService.addProduct(product);

        // 重定向到商品列表页面或其他页面
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
