package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Shop;
import zhku.zzy.tblikemall.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class ShopDao {
    public Shop findShopByUser(int userid){
        String sql = "select * from shops where userid=?";
        Shop shop = new Shop();
        Util util = new Util();
        List params = new ArrayList();
        params.add(userid);
        Object[] obj = util.query(sql,params,4);
        if (obj != null) {
            shop.setShopid(Integer.parseInt(obj[0].toString()));
            shop.setUserid(Integer.parseInt(obj[1].toString()));
            shop.setShopname(obj[2].toString());
            shop.setDescription(obj[3].toString());
            return shop;
        }
        return null;
    }

    public Shop findShopById(int shopid){
        String sql = "select * from shops where shopid=?";
        Shop shop = new Shop();
        Util util = new Util();
        List params = new ArrayList();
        params.add(shopid);
        Object[] obj = util.query(sql,params,4);
        if (obj != null) {
            shop.setShopid(Integer.parseInt(obj[0].toString()));
            shop.setUserid(Integer.parseInt(obj[1].toString()));
            shop.setShopname(obj[2].toString());
            shop.setDescription(obj[3].toString());
            return shop;
        }
        return null;
    }

    public int shopUpdate(String shopname,String description,int userid){
        String sql = "update shops set shopname=?,description=? where userid=?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(shopname);
        params.add(description);
        params.add(userid);
        return util.executeUpdate(sql,params);
    }

    public void shopAdd(Shop shop){
        String sql = "insert into shops(userid,shopname,description) values(?,?,?)";
        Util util = new Util();
        List params = new ArrayList();
        params.add(shop.getUserid());
        params.add(shop.getShopname());
        params.add(shop.getDescription());
        util.executeUpdate(sql,params);
    }
}
