import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String passwordHash;
    private String email;

    // In a real system, you'd use a secure hash and salt for passwords.
    private static Map<String, User> usersDatabase = new HashMap<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.passwordHash = password; // Simplified for this example
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public static boolean authenticate(String username, String password) {
        User user = usersDatabase.get(username);
        return user != null && user.getPasswordHash().equals(password);
    }

    public static void register(String username, String password, String email) {
        if (usersDatabase.containsKey(username)) {
            System.out.println("User already exists.");
        } else {
            usersDatabase.put(username, new User(username, password, email));
            System.out.println("User registered successfully.");
        }
    }
}

