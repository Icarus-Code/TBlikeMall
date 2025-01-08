package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.OrderDao;
import zhku.zzy.tblikemall.Dao.ProductDao;
import zhku.zzy.tblikemall.Entity.Product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    ProductDao productDao = new ProductDao();
    public List<Product> findAll() {
        return productDao.findAll();
    }

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public Product findProductById(int productid) {
        return productDao.findProductById(productid);
    }

    public void deleteProduct(int productid){
        productDao.deleteProduct(productid);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }

    public List<Product> searchProducts(String keyword){
        return productDao.searchProducts(keyword);
    }

    public List<Product> findAllSortedByCompletedOrders() {
        OrderDao orderDao = new OrderDao();
        List<Product> products = productDao.findAll(); // 获取所有产品
        for (Product product : products) {
            int completedOrderCount = orderDao.count(product.getProductid(), "已完成");
            product.setCompletedOrderCount(completedOrderCount); // 设置每个产品的已完成订单数量
        }
        // 根据已完成订单数量进行排序
        return products.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getCompletedOrderCount(), p1.getCompletedOrderCount()))
                .collect(Collectors.toList());
    }
}
