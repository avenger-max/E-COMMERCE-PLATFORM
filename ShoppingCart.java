import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(Product product) {
        cartItems.add(product);
        System.out.println(product.getName() + " added to your cart.");
    }

    public void removeProduct(Product product) {
        if (cartItems.remove(product)) {
            System.out.println(product.getName() + " removed from your cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your cart:");
            cartItems.forEach(item -> System.out.println(item));
        }
    }

    public double getTotalPrice() {
        return cartItems.stream().mapToDouble(Product::getPrice).sum();
    }
}

