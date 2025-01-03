// Import necessary packages
import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;

// Class for ATM operations
public class ATM_OP {
    // Balance of the ATM
    private static long balance;

    // List of customers
    private static ArrayList<Customer> custList = new ArrayList<>();

    // List of currency notes
    private static ArrayList<Note> notes = new ArrayList<>();

    // List of admins
    private static ArrayList<Admin> adminsList = new ArrayList<>();

    // Scanner for user input
    private static final Scanner sc = new Scanner(System.in);

    public static ArrayList<Admin> getAdminsList() {
        return adminsList;
    }

    public static void setAdminsList(ArrayList<Admin> adminsList) {
        ATM_OP.adminsList = adminsList;
    }

    public static ArrayList<Note> getNotes() {
        return notes;
    }

    // Add a customer to the list
    public static void addCustomer(Customer cust) {
        ATM_OP.custList.add(cust);
    }

    public static ArrayList<Customer> getCustList() {
        return custList;
    }

    public static void setNotes(ArrayList<Note> notes) {
        ATM_OP.notes = notes;
    }

    public static Customer getCusID(String id) {
        for (Customer customer : custList) {
            if (customer.getID().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    public static Scanner getSc() {
        return sc;
    }

    public static long getATMBalance() {
        return balance;
    }

    public static void setATMBalance(long balance) {
        ATM_OP.balance = balance;
    }
    
    static{
        // Initialize admins and notes with static initialixer
        adminsList.add(new Admin("admin", "admin123", "Gokul"));
        notes.add(new Two_Thousand("2000", 0));
        notes.add(new Five_Hundred("500", 0));
        notes.add(new Two_Hundred("200", 0));
        notes.add(new Hundred("100", 0));
    }
    // to start the ATM and cloneNotSupportedException for cloning not supported for some class
    public static void start() throws CloneNotSupportedException {

        boolean run = true;

        // Main menu loop
        while (run) {
            try {
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.println("Enter choice:");

                // Get user choice
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: // Admin login and operations
                        Admin admin = Admin_Action.login(ATM_OP.getAdminsList());// it check the user is valid it retrun the those admin's address
                        if (getAdminsList().contains(admin)) {//It check the retrun admin's address in admin arraylist
                            System.out.println("Admin Successfully login!");
                            System.out.println("Welcome " + admin.getAdminName());
                            adminOps(admin);//it call the admin operations
                        }
                        break;
                    case 2: // Customer login and operations
                        Customer customer = Customer_Action.login(getCustList());// it check the user is valid it retrun the those Customer's address
                        if (getCustList().contains(customer)) {//It check the retrun customer's address in customer arraylist
                            System.out.println("User Successfully login!");
                            System.out.println("Welcome " + customer.getName());
                            userOps(customer.getID());//it call the customer operations
                        } else if (customer != null&&!getCustList().contains(customer)) System.out.println("User not found");//it check there is Customer is not contians
                        break;
                    case 3: // Exit the program
                        System.out.println("Exiting...");
                        run = false;
                        break;
                    default: // Invalid choice
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {// If it is there is any exception with in numberformat 
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Admin operations menu
    public static void adminOps(Admin admin) {
        boolean runAdmin = true;
        while (runAdmin) {//Admin Menu loop
            try {
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. View Transactions");
                System.out.println("4. View Accounts");
                System.out.println("5. ATM Inventory");
                System.out.println("6. Exit");
                System.out.println("Enter choice:");

                int choice = Integer.parseInt(getSc().nextLine());
                switch (choice) {
                    case 1: // Add customer
                        System.out.println("Adding customer...");
                        Admin_Action.addUser();
                        break;
                    case 2: // Delete customer
                        System.out.println("Deleting customer...");
                        System.out.println("Enter the customer id to Delete");
                        String User = getSc().nextLine();
                        Admin_Action.deleteUser(User);
                        break;
                    case 3: // View all transactions
                        System.out.println("Viewing all transactions...");
                        Admin_Action.viewAllTransactions(); // it will show all transaction for Customer
                        break;
                    case 4: // View all accounts
                        System.out.println("Viewing all accounts...");
                        Admin_Action.viewAllAccounts();// It show the all Customer Accounts
                        break;
                    case 5: // ATM inventory management
                        ATM_Inventory(admin);
                        break;
                    case 6: // Exit admin operations
                        System.out.println("Exiting admin ops...");
                        runAdmin = false;
                        break;
                    default: // Invalid choice
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Customer operations menu
    public static void userOps(String uid) throws CloneNotSupportedException {
        boolean runUser = true;
        while (runUser) {
            try {
                // Display user menu
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. View Transactions");
                System.out.println("5. Change Password");
                System.out.println("6. Exit");
                System.out.println("Enter choice:");

                // Get user choice
                int choice = Integer.parseInt(getSc().nextLine());
                for (Customer cust : custList) {
                    if (cust.getID().equals(uid)) {
                        switch (choice) {
                            case 1: // Check balance
                                System.out.println("Balance: " + cust.getBalance());
                                break;
                            case 2: // Withdraw money
                                System.out.println("Enter withdrawal amount:");
                                Customer_Action.withdrawAmt(cust);
                                break;
                            case 3: // Deposit money
                                System.out.println("Enter the Deposit Amount");
                                Customer_Action.depositAmt(cust);
                                break;
                            case 4: // View transaction history
                                if (!cust.getTransHistory().isEmpty()) {
                                    System.out.println("Transaction history:");
                                    for (Transaction transact : cust.getTransHistory()) {
                                        System.out.println(transact);
                                    }
                                } else {
                                    System.out.println("No transactions found.");
                                }
                                break;
                            case 5: // Change password
                                System.out.println("Enter your current password:");
                                String currentPassword = getSc().nextLine();
                                Customer_Action.changePassword(uid, currentPassword);
                                break;
                            case 6: // Exit user operations
                                System.out.println("Exiting user ops...");
                                runUser = false;
                                break;
                            default: // Invalid choice
                                System.out.println("Invalid choice.");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // ATM inventory menu
    public static void ATM_Inventory(Admin admin) {
        boolean run = true;
        while (run) {
            try {
                System.out.println("1. ATM Balance");
                System.out.println("2. ATM Deposit");
                System.out.println("3. View ATM Transaction History");
                System.out.println("4. Back to Admin Menu");
                System.out.println("Enter the choice:");

                int choice = Integer.parseInt(getSc().nextLine());
                switch (choice) {
                    case 1: // Check ATM balance
                        System.out.println("ATM Balance:" + getATMBalance());
                        break;
                    case 2: // Deposit money into ATM
                        Admin_Action.addATMoney(admin);
                        break;
                    case 3: // View ATM transaction history
                        if (Admin.getAdTransaction().isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            for (Transaction transaction : Admin.getAdTransaction()) {
                                System.out.println(transaction);
                            }
                        }
                        break;
                    case 4: // Back to admin menu
                        System.out.println("Back.....");
                        run = false;
                        break;
                    default: // Invalid choice
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
