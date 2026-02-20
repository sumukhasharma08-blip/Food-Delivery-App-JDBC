import java.sql.*;

public class UserDAO {

    public static void register(String name, String email, String password) {
        String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();

            System.out.println("✅ User Registered Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int login(String email, String password) {
        String sql = "SELECT id FROM users WHERE email=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt("id");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}