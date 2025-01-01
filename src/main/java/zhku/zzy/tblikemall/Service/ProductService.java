package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.ProductDao;
import zhku.zzy.tblikemall.Entity.Product;

import java.util.*;

public class ProductService {
    ProductDao productDao = new ProductDao();
    public List<Product> findAll() {
        return productDao.findAll();
    }

    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }

    public Product findProductById(int productid) {
        return productDao.findProductById(productid);
    }
}
