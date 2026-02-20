import java.sql.*;

public class RestaurantDAO {

    public static void addRestaurant(String name, String location) {
        String sql = "INSERT INTO restaurants(name,location) VALUES(?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, location);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewRestaurants() {
        String sql = "SELECT * FROM restaurants";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n🏪 Restaurants:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + ". " +
                        rs.getString("name") + " - " +
                        rs.getString("location")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}