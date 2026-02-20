import java.sql.*;

public class OrderDAO {

    public static void placeOrder(int userId, double total) {
        String sql = "INSERT INTO orders(user_id,total,status) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setDouble(2, total);
            ps.setString(3, "Order Placed");
            ps.executeUpdate();

            System.out.println("✅ Order Placed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewOrders(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📦 Your Orders:");
            while (rs.next()) {
                System.out.println(
                        "Order #" + rs.getInt("id") +
                        " | ₹" + rs.getDouble("total") +
                        " | " + rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}