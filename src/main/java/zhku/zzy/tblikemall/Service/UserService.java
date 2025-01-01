package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.UserDao;
import zhku.zzy.tblikemall.Entity.User;

import java.util.List;

public class UserService {
    UserDao userDao = null;
    public UserService() {
        userDao = new UserDao();
    }

    public Boolean isValid(String username, String password) {
        return userDao.isValid(username, password);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public int userAdd(User user) {
        return userDao.userAdd(user);
    }

    public User findByName(String name){
        return userDao.findByName(name);
    }

    public int userUpdate(String name,String password,String role,int id){
        return userDao.userUpdate(name,password,role,id);
    }

    public int userDelete(String name){
        return userDao.userDelete(name);
    }
}
