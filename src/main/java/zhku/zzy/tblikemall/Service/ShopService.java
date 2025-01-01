package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.ShopDao;
import zhku.zzy.tblikemall.Entity.Shop;

public class ShopService {
    ShopDao shopDao = new ShopDao();
    public Shop findShopById(int shopid){
        return shopDao.findShopById(shopid);
    }
}
