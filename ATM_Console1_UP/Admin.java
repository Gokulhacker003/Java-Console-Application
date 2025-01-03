import java.util.ArrayList;

// Admin class to manage admin details and actions
public class Admin {
    private String id; // Admin ID
    private String pass; // Admin password
    private String name; // Admin name
    private static ArrayList<Transaction> AdTransaction = new ArrayList<>(); // Admin transaction history

    // Constructor to initialize an admin with ID, password, and name
    public Admin(String ID, String PASS, String NAME) {
        this.id = ID;
        this.pass = PASS;
        this.name = NAME;
    }

    // redeclare constructor
    public Admin() {
    }

    public String getAdminId() {
        return this.id;
    }

    public String getAdminPass() {
        return this.pass;
    }

    public String getAdminName() {
        return this.name;
    }

    public static ArrayList<Transaction> getAdTransaction() {
        return AdTransaction;
    }
}
