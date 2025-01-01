package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.CartDao;
import zhku.zzy.tblikemall.Dao.UserDao;
import zhku.zzy.tblikemall.Entity.Cart;

import java.util.List;

public class CartService {
    CartDao cartDao = new CartDao();
    public int cartAdd(Cart cart){
        return cartDao.cartAdd(cart);
    }

    public List<Cart> findAll(int userid){
        return cartDao.findAll(userid);
    }
}
