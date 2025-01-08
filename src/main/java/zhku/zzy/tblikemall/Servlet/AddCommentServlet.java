package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.Comment;
import zhku.zzy.tblikemall.Service.CommentService;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;

@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getSession().getAttribute("username").toString();
        UserService userService = new UserService();
        int userid = userService.findByName(username).getUserid();
        int productid = Integer.parseInt(request.getParameter("productid"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String text = request.getParameter("comment");
        Comment comment = new Comment();
        comment.setUserid(userid);
        comment.setProductid(productid);
        comment.setRate(rating);
        comment.setComment(text);
        CommentService commentService = new CommentService();
        commentService.commentAdd(comment);
        request.setAttribute("productid", productid);
        try {
            request.getRequestDispatcher("addCommentSuccess.jsp").forward(request,response);
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
