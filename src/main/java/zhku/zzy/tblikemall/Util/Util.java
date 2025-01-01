package zhku.zzy.tblikemall.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Util {
    public Connection conn =null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    private static String propFileName = "/db.properties";
    private static Properties prop = new Properties();
    private static String driver = null;
    private static String url = null;
    private static String userName = null;
    private static String password = null;

    public Util(){
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream(propFileName);
            prop.load(in);
        }catch (IOException e) {
            e.printStackTrace();
        }

        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        userName = "&user=" + prop.getProperty("userName");
        password = "&password=" + prop.getProperty("password");
        System.out.println(driver+url+userName+password);
    }

    public Connection getConn(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url+userName+password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if(conn==null) System.out.println("数据库连接出错");
        else System.out.println("连接成功");
        return conn;
    }

    public PreparedStatement setPs(String sql, List params) throws SQLException {
        conn = getConn();
        ps = conn.prepareStatement(sql);
        if(params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i+1, params.get(i));
            }
        }
        return ps;
    }

    public void closeAll(){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int executeUpdate(String sql,List params){
        try{
            ps = setPs(sql,params);
            return ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return 0;
    }

    public Object[] query(String sql,List params,int col){
        try {
            ps = setPs(sql, params);
            rs = ps.executeQuery();
            Object[] objects = new Object[col];
            if(rs.next()) {
                for (int i = 0; i < col; i++) {
                    objects[i] = rs.getObject(i+1);
                }
                return objects;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return null;
    }

    public List<Object[]> queryList(String sql, List param,int col){
        try {
            ps = setPs(sql, param);
            rs = ps.executeQuery();
            List<Object[]> obj = new ArrayList<Object[]>();
            while(rs.next()) {
                Object[] objects = new Object[col];
                for (int i = 0; i < col; i++) {
                    objects[i] = rs.getObject(i + 1);
                }
                obj.add(objects);
            }
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return null;
    }
}
