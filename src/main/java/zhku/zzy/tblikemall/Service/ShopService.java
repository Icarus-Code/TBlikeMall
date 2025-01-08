package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.ShopDao;
import zhku.zzy.tblikemall.Entity.Shop;

public class ShopService {
    ShopDao shopDao = new ShopDao();
    public Shop findShopByUser(int userid){
        return shopDao.findShopByUser(userid);
    }

    public int shopUpdate(String shopname,String description,int userid){
        return shopDao.shopUpdate(shopname,description,userid);
    }

    public Shop findShopById(int shopid){
        return shopDao.findShopById(shopid);
    }

    public void shopAdd(Shop shop){
        shopDao.shopAdd(shop);
    }
}
