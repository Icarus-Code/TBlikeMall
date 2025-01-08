package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.CommentService;

import java.io.IOException;

@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int commentid = Integer.parseInt(request.getParameter("commentid"));
        int productid = Integer.parseInt(request.getParameter("productid"));
        CommentService commentService = new CommentService();
        commentService.commentDelete(commentid);
        try {
            response.sendRedirect("ShowAproductServlet?productid=" + productid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
