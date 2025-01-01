package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Entity.User;
import zhku.zzy.tblikemall.Util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        String sql = "select * from products";
        Util util = new Util();
        List<Object[]> objs = util.queryList(sql,null,7);
        for(Object[] obj : objs){
            Product product = new Product();
            product.setProductid(Integer.parseInt(obj[0].toString()));
            product.setProductname(obj[1].toString());
            product.setDescription(obj[2].toString());
            product.setPrice(new BigDecimal(obj[3].toString()));
            product.setStock(Integer.parseInt(obj[4].toString()));
            product.setShopid(Integer.parseInt(obj[5].toString()));
            product.setProductimage((byte[])obj[6]);
            products.add(product);
        }
        return products;
    }

    public int addProduct(Product product){
        String sql = "insert into products values(?,?,?,?,?,?)";
        Util util = new Util();
        List params = new ArrayList();
        params.add(product.getProductid());
        params.add(product.getProductname());
        params.add(product.getDescription());
        params.add(product.getPrice());
        params.add(product.getStock());
        params.add(product.getShopid());
        params.add(product.getProductimage());
        return util.executeUpdate(sql,params);
    }
}
