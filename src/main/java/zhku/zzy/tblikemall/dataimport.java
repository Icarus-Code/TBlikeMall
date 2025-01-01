package zhku.zzy.tblikemall;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class dataimport {
    public static void main(String[] args) {
        String sql = "UPDATE products SET productimage = ? WHERE productid = 1";
        String imagePath = "E:/桌面/hutao.jpg";
        FileInputStream fis = null;
        byte[] imageData = null;
        try {
            fis = new FileInputStream(imagePath);
            byte[] bytes = new byte[(int) new java.io.File(imagePath).length()];
            imageData = bytes;
            fis.read(bytes);
            fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TBlikeMall", "root", "123456");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBytes(1, imageData);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
