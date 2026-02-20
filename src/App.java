import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int userId = -1;

        while (true) {
            System.out.println("\nFeasto");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Restaurants");
            System.out.println("4. View Menu");
            System.out.println("5. Place Order");
            System.out.println("6. View My Orders");
            System.out.println("0. Exit");

            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1 -> {
                    System.out.print("Name: ");
                    String n = sc.nextLine();
                    System.out.print("Email: ");
                    String e = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    UserDAO.register(n, e, p);
                }

                case 2 -> {
                    System.out.print("Email: ");
                    String e = sc.nextLine();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    userId = UserDAO.login(e, p);
                    System.out.println(userId > 0 ? "✅ Login Success" : "❌ Login Failed");
                }

                case 3 -> RestaurantDAO.viewRestaurants();

                case 4 -> {
                    System.out.print("Restaurant ID: ");
                    FoodDAO.viewMenu(sc.nextInt());
                }

                case 5 -> {
                    if (userId == -1) {
                        System.out.println("❌ Please login first");
                        break;
                    }
                    System.out.print("Enter total amount: ");
                    OrderDAO.placeOrder(userId, sc.nextDouble());
                }

                case 6 -> {
                    if (userId == -1) {
                        System.out.println("❌ Login required");
                        break;
                    }
                    OrderDAO.viewOrders(userId);
                }

                case 0 -> {
                    System.out.println("👋 Thank you for using Smokit!");
                    System.exit(0);
                }
            }
        }
    }
}