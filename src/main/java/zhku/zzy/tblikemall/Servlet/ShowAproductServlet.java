package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Comment;
import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.CommentService;
import zhku.zzy.tblikemall.Service.ProductService;
import zhku.zzy.tblikemall.Service.ShopService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShowAproductServlet")
public class ShowAproductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String productid = request.getParameter("productid").toString();
        ProductService productService = new ProductService();
        Product product = productService.findProductById(Integer.parseInt(productid));
        CommentService commentService = new CommentService();
        UserService userService = new UserService();
        List<Comment> comments = commentService.findByProductid(Integer.parseInt(productid));
        List<User> users = new ArrayList<>();
        for(Comment comment : comments){
            int userid = comment.getUserid();
            User user = userService.findByUserid(userid);
            users.add(user);
        }
        ShopService shopService = new ShopService();
        String shopname = shopService.findShopById(product.getShopid()).getShopname();
        request.setAttribute("comments",comments);
        request.setAttribute("users",users);
        request.setAttribute("product", product);
        request.setAttribute("shopname", shopname);
        try {
            request.getRequestDispatcher("ProductPage.jsp").forward(request,response);
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
