public class Order {
    private User user;
    private ShoppingCart shoppingCart;
    private double totalAmount;
    private boolean paymentStatus;

    public Order(User user, ShoppingCart shoppingCart) {
        this.user = user;
        this.shoppingCart = shoppingCart;
        this.totalAmount = shoppingCart.getTotalPrice();
        this.paymentStatus = false;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    public void processPayment(PaymentGateway paymentGateway) {
        paymentStatus = paymentGateway.processPayment(totalAmount);
    }

    @Override
    public String toString() {
        return "Order [user=" + user.getUsername() + ", totalAmount=" + totalAmount + ", paymentStatus=" + paymentStatus + "]";
    }
}

