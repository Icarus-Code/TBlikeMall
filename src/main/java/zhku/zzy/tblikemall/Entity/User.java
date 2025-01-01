package zhku.zzy.tblikemall.Entity;

public class User {
    private int userid;
    private String username;
    private String password;
    private String role;
    private byte[] userimage;

    public byte[] getUserimage() {
        return userimage;
    }

    public void setUserimage(byte[] userimage) {
        this.userimage = userimage;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
