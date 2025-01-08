package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Entity.Product;
import zhku.zzy.tblikemall.Util.Util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productname LIKE ?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(keyword);
        List<Object[]> objs = util.queryList(sql,params,7);
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

    public void addProduct(Product product){
        String sql = "INSERT INTO products (productname, description, price, stock, shopid, productimage) VALUES (?, ?, ?, ?, ?, ?)";
        Util util = new Util();
        Connection connection = util.getConn();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductname());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setInt(5, product.getShopid());
            statement.setBytes(6, product.getProductimage());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product){
        String sql = "UPDATE products SET productname=?, description=?, price=?, stock=?, productimage=? WHERE productid=?";
        Util util = new Util();
        Connection connection = util.getConn();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductname());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getStock());
            statement.setBytes(5, product.getProductimage());
            statement.setInt(6, product.getProductid());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product findProductById(int productid){
        String sql = "select * from products where productid=?";
        Util util = new Util();
        Product product = new Product();
        List params = new ArrayList();
        params.add(productid);
        Object[] obj = util.query(sql,params,7);
        if(obj != null){
            product.setProductid(Integer.parseInt(obj[0].toString()));
            product.setProductname(obj[1].toString());
            product.setDescription(obj[2].toString());
            product.setPrice(new BigDecimal(obj[3].toString()));
            product.setStock(Integer.parseInt(obj[4].toString()));
            product.setShopid(Integer.parseInt(obj[5].toString()));
            product.setProductimage((byte[])obj[6]);
            return product;
        }
        return null;
    }

    public void deleteProduct(int productid){
        String sql = "delete from products where productid=?";
        Util util = new Util();
        List params = new ArrayList();
        params.add(productid);
        util.executeUpdate(sql,params);
    }
}
