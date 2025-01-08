package zhku.zzy.tblikemall.Servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zhku.zzy.tblikemall.Service.OrderService;

import java.io.IOException;

@WebServlet("/OrderUpdateServlet")
public class OrderUpdateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String action = request.getParameter("action");
        OrderService orderService = new OrderService();
        if(action.equals("删除")){
            orderService.orderDelete(orderId);
            try {
                response.sendRedirect("ShowOrdersServlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(action.equals("支付")){
            action="待发货";
        }
        else if(action.equals("发货")){
            action="待收货";
        }
        else if(action.equals("收货")){
            action="已完成";
        }
        orderService.orderUpdateStatus(action, orderId);
        try {
            response.sendRedirect("ShowOrdersServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        this.doGet(request, response);
    }
}
