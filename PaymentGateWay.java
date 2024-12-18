public class PaymentGateway {
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + "...");
        return true;  // Always returns true for simplicity
    }
}

