package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Shop;
import zhku.zzy.tblikemall.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class ShopDao {
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
}
