package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Cart;
import zhku.zzy.tblikemall.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class CartDao {
    public List<Cart> findAll(int userid){
        List<Cart> carts = new ArrayList<>();
        String sql = "select * from cart where userid = ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(userid);
        List<Object[]> objs = util.queryList(sql,params,4);
        for(Object[] obj : objs){
            Cart cart = new Cart();
            cart.setCartid(Integer.parseInt(obj[0].toString()));
            cart.setUserid(Integer.parseInt(obj[1].toString()));
            cart.setProductid(Integer.parseInt(obj[2].toString()));
            cart.setQuantity(Integer.parseInt(obj[3].toString()));
            carts.add(cart);
        }
        return carts;
    }

    public int cartAdd(Cart cart){
        String sql = "insert into cart(userid,productid,quantity) values(?,?,?)";
        List params = new ArrayList();
        params.add(cart.getUserid());
        params.add(cart.getProductid());
        params.add(cart.getQuantity());
        Util util = new Util();
        return util.executeUpdate(sql,params);
    }


    public int cartDelete(int cartid){
        String sql = "delete from cart where cartid=?";
        List params = new ArrayList();
        params.add(cartid);
        Util util = new Util();
        return util.executeUpdate(sql,params);
    }
}
