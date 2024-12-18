import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceApp {
    private static List<Product> productCatalog = new ArrayList<>();
    private static ShoppingCart shoppingCart = new ShoppingCart();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Populate the product catalog
        populateProductCatalog();

        // Register a test user (in a real application, this would be done through a UI or an API)
        User.register("alice", "password123", "alice@example.com");

        // Simulate the user login
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (User.authenticate(username, password)) {
            System.out.println("Login successful!");
            currentUser = new User(username, password, "alice@example.com");
            startShopping();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void populateProductCatalog() {
        productCatalog.add(new Product("1", "Laptop", "High-end laptop", 1200.0));
        productCatalog.add(new Product("2", "Smartphone", "Latest model smartphone", 800.0));
        productCatalog.add(new Product("3", "Headphones", "Noise-canceling headphones", 150.0));
    }

    private static void startShopping() {
        boolean shopping = true;
        while (shopping) {
            System.out.println("\nWelcome to the E-commerce Store!");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    shopping = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void viewProducts() {
        System.out.println("\nProduct Catalog:");
        productCatalog.forEach(product -> System.out.println(product));
    }

    private static void addProductToCart() {
        System.out.print("Enter product ID to add to cart: ");
        String productId = scanner.nextLine();
        Product product = findProductById(productId);
        if (product != null) {
            shoppingCart.addProduct(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void removeProductFromCart() {
        System.out.print("Enter product ID to remove from cart: ");
        String productId = scanner.nextLine();
        Product product = findProductById(productId);
        if (product != null) {
            shoppingCart.removeProduct(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void viewCart() {
        shoppingCart.displayCart();
    }

    private static void checkout() {
        Order order = new Order(currentUser, shoppingCart);
        System.out.println("\nProceeding to checkout...");
        System.out.println(order);

        PaymentGateway paymentGateway = new PaymentGateway();
        order.processPayment(paymentGateway);

        if (order.getPaymentStatus()) {
            System.out.println("Payment successful. Thank you for your purchase!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    private static Product findProductById(String productId) {
        return productCatalog.stream().filter(product -> product.getId().equals(productId)).findFirst().orElse(null);
    }
}

