package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.OrderDao;
import zhku.zzy.tblikemall.Entity.Order;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    public List<Order> findAll(int userid){
        return orderDao.findAll(userid);
    }

    public int orderAdd(Order order){
        return orderDao.orderAdd(order);
    }
}
