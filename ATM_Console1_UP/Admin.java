// Admin class to manage admin details and actions
public class Admin extends Account {


    // Constructor to initialize an admin with ID, password, and name
    public Admin(String ID, String PASS, String NAME) {
        super(ID, PASS, NAME);
    }

    // Default constructor
    public Admin() {
        super();
    }
}
