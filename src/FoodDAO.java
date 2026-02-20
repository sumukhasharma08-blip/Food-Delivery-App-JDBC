import java.sql.*;

public class FoodDAO {

    public static void addFood(int restId, String name, double price) {
        String sql = "INSERT INTO food_items(restaurant_id,name,price) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restId);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewMenu(int restId) {
        String sql = "SELECT * FROM food_items WHERE restaurant_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n🍽️ Menu:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ". " +
                        rs.getString("name") + " ₹" +
                        rs.getDouble("price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}