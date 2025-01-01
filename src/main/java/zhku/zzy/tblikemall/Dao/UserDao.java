package zhku.zzy.tblikemall.Dao;

import zhku.zzy.tblikemall.Util.Util;
import zhku.zzy.tblikemall.Entity.User;

import java.util.*;

public class UserDao {
    public boolean isValid(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        Util util = new Util();
        List<Object> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        Object[] obj = util.query(sql, params, 4);
        return obj != null;
    }



    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        Util util = new Util();
        List<Object[]> objs = util.queryList(sql,null,4);
        for(Object[] obj : objs){
            User user = new User();
            user.setUserid(Integer.parseInt(obj[0].toString()));
            user.setUsername(obj[1].toString());
            user.setPassword(obj[2].toString());
            user.setRole(obj[3].toString());
            users.add(user);
        }
        return users;
    }

    public User findByName(String name) {
        String sql = "SELECT * FROM users WHERE username=?";
        Util util = new Util();
        List<Object> params = new ArrayList<>();
        params.add(name);
        Object[] obj = util.query(sql, params, 4);
        if (obj == null) {
            return null; // 或者抛出一个异常，取决于业务需求
        }
        User user = new User();
        user.setUserid(Integer.parseInt(obj[0].toString()));
        user.setUsername(obj[1].toString());
        user.setPassword(obj[2].toString());
        user.setRole(obj[3].toString());
        return user;
    }


    public int userAdd(User user){
        String sql = "insert into users(name,password,age) values(?,?,?)";
        List params = new ArrayList();
        params.add(user.getUsername());
        params.add(user.getPassword());
        params.add(user.getRole());
        Util util = new Util();
        return util.executeUpdate(sql,params);
    }

    public int userUpdate(String name,String password,String role,int id){
        String sql = "update users set name=?,password=?,age=? where userid=?";
        List params = new ArrayList();
        params.add(name);
        params.add(password);
        params.add(role);
        params.add(id);
        Util util = new Util();
        return util.executeUpdate(sql,params);
    }

    public int userDelete(String name){
        String sql = "delete from users where name=?";
        List params = new ArrayList();
        params.add(name);
        Util util = new Util();
        return util.executeUpdate(sql,params);
    }
}
