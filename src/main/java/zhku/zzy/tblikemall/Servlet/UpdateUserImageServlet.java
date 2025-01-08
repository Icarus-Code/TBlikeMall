package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Service.UserService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/UpdateUserImageServlet")
@MultipartConfig
public class UpdateUserImageServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String username = request.getSession().getAttribute("username").toString();
        UserService userService = new UserService();
        User user = new User();

        // 获取上传的文件
        Part filePart = null;
        try {
            filePart = request.getPart("avatar");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = null;

        // 读取文件内容到 byte 数组
        if (filePart != null && filePart.getSize() > 0) {
            InputStream inputStream = null;
            try {
                inputStream = filePart.getInputStream();
                imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        user.setUserid(userService.findByName(username).getUserid());
        user.setUserimage(imageBytes);
        userService.updateUserImage(user);

        try {
            response.sendRedirect("ProfileServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
