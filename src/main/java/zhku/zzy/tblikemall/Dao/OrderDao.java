package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Cart;
import zhku.zzy.tblikemall.Entity.Order;
import zhku.zzy.tblikemall.Util.Util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public List<Order> findAll(int userid){
        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders where userid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(userid);
        List<Object[]> objs = util.queryList(sql,params,6);
        for(Object[] obj : objs){
            Order order = new Order();
            order.setOrderid((Integer)obj[0]);
            order.setUserid((Integer)obj[1]);
            order.setProductid((Integer)obj[2]);
            order.setQuantity((Integer)obj[3]);
            order.setStatus(obj[4].toString());
            order.setCreateat((Timestamp) obj[5]);
            orders.add(order);
        }
        return orders;
    }

    public int orderAdd(Order order){
        String sql = "insert into orders(userid,productid,quantity,status,createat) values(?,?,?,?,?)";
        Util util = new Util();
        List params = new ArrayList();
        params.add(order.getUserid());
        params.add(order.getProductid());
        params.add(order.getQuantity());
        params.add(order.getStatus());
        params.add(order.getCreateat());
        return util.executeUpdate(sql,params);
    }
}
