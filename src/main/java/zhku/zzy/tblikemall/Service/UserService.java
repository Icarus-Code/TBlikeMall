package zhku.zzy.tblikemall.Service;

import zhku.zzy.tblikemall.Dao.UserDao;
import zhku.zzy.tblikemall.Entity.User;

import java.util.List;

public class UserService {
    UserDao userDao = null;
    public UserService() {
        userDao = new UserDao();
    }

    public Boolean isValid(String username, String password, String role) {
        return userDao.isValid(username, password, role);
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public void userAdd(User user) {
        userDao.userAdd(user);
    }

    public User findByName(String name){
        return userDao.findByName(name);
    }

    public int userUpdate(String name,String password,int id){
        return userDao.userUpdate(name,password,id);
    }

    public int userDelete(String name){
        return userDao.userDelete(name);
    }

    public User findByUserid(int userid){
        return userDao.findByUserid(userid);
    }

    public void updateUserImage(User user){
        userDao.updateUserImage(user);
    }
}
