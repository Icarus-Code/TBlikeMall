package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Order;
import zhku.zzy.tblikemall.Util.Util;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public List<Order> findAll(){
        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders";
        Util util = new Util();
        List<Object[]> objs = util.queryList(sql,null,6);
        for(Object[] obj : objs){
            Order order = new Order();
            order.setOrderid((Integer)obj[0]);
            order.setUserid((Integer)obj[1]);
            order.setProductid((Integer)obj[2]);
            order.setQuantity((Integer)obj[3]);
            order.setStatus(obj[4].toString());
            order.setCreateat(Timestamp.valueOf((LocalDateTime) obj[5]));
            orders.add(order);
        }
        return orders;
    }

    public List<Order> findAllByUser(int userid){
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
            order.setCreateat(Timestamp.valueOf((LocalDateTime) obj[5]));
            orders.add(order);
        }
        return orders;
    }

    public List<Order> findByProductid(int productid){
        List<Order> orders = new ArrayList<>();
        String sql = "select * from orders where productid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(productid);
        List<Object[]> objs = util.queryList(sql,params,6);
        for(Object[] obj : objs){
            Order order = new Order();
            order.setOrderid((Integer)obj[0]);
            order.setUserid((Integer)obj[1]);
            order.setProductid((Integer)obj[2]);
            order.setQuantity((Integer)obj[3]);
            order.setStatus(obj[4].toString());
            order.setCreateat(Timestamp.valueOf((LocalDateTime) obj[5]));
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

    public int orderUpdateStatus(String status,int orderid){
        String sql = "update orders set status = ? where orderid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(status);
        params.add(orderid);
        return util.executeUpdate(sql,params);
    }

    public int orderDelete(int orderid){
        String sql = "delete from orders where orderid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(orderid);
        return util.executeUpdate(sql,params);
    }

    public int count(int productid , String status){
        String sql = "select count(*) from orders where productid = ? and status = ?";
        Util util = new Util();
        Connection connection = util.getConn();
        try (
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productid);
            statement.setString(2, status);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
