package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.OrderDao;
import zhku.zzy.tblikemall.Entity.Order;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    public List<Order> findAllByUser(int userid){
        return orderDao.findAllByUser(userid);
    }

    public int orderAdd(Order order){
        return orderDao.orderAdd(order);
    }

    public int orderUpdateStatus(String status,int orderid){
        return orderDao.orderUpdateStatus(status,orderid);
    }

    public int orderDelete(int orderid){
        return orderDao.orderDelete(orderid);
    }

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public int count(int productid,String status){
        return orderDao.count(productid,status);
    }
}
