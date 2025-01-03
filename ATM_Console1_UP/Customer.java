import java.util.ArrayList;

// Customer class to represent customer details and actions
public class Customer {
    private String uid; // Unique customer ID
    private String pass; // Customer password
    private String name; // Customer name
    private long balance; // Customer account balance
    private ArrayList<Transaction> transHistory; // Customer transaction history

    // Constructor to initialize a customer with ID, password, and name
    public Customer(String ID, String pass, String name) {
        this.uid = ID;
        this.pass = pass;
        this.name = name;
        this.balance = 0; // Default balance is 0
        transHistory = new ArrayList<>(); // Initialize transaction history
    }

    // Redeclare constructor
    public Customer() {
    }

    public String getID() {
        return uid;
    }

    public String getPassword() {
        return pass;
    }

    public void setPassword(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransHistory() {
        return transHistory;
    }
}
