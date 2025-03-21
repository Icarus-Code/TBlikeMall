package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = null;

        if (username.equals("") || password.equals("")) {
            errorMessage = "请输入完整信息";
            request.setAttribute("errorMessage", errorMessage);
            try {
                request.getRequestDispatcher("editProfile.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            UserService userService = new UserService();
            User userInstance = userService.findByName(username);
            if (userInstance != null) {
                errorMessage = "用户名已存在";
                request.setAttribute("errorMessage", errorMessage);
                try {
                    request.getRequestDispatcher("editProfile.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                userService.userUpdate(username,password,userService.findByName(request.getParameter("selectedname")).getUserid());
                try {
                    response.sendRedirect("accountOperateSuccess.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
