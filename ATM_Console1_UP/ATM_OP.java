import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;
import ListofNotes.*;

// Class for ATM operations
public class ATM_OP {
    // Balance of the ATM
    private static long balance;

    // List of admins
    private static ArrayList<Account> AccDT=new ArrayList<>();

    // List of currency notes
    private static ArrayList<Note> notes = new ArrayList<>();


    // Scanner for user input
    private static final Scanner sc = new Scanner(System.in);

    // Getter for admin list

    // Getter for note list
    public static ArrayList<Note> getNotes() {
        return notes;
    }

    // Add a customer to the list
    public static void addCustomer(Account cust) {
        ATM_OP.AccDT.add(cust);
    }

    // Getter for customer list
    public static ArrayList<Account> getAccDT() {
        return AccDT;
    }


    // Setter for note list
    public static void setNotes(ArrayList<Note> notes) {
        ATM_OP.notes = notes;
    }

    // Find customer by ID it will return the right Customer
    public static Account getAccID(String id) {
        for (Account accDT:getAccDT()) {
            if (accDT.getId().equals(id)) {
                return accDT;
            }
        }
        return null;
    }

    // Getter for scanner
    public static Scanner getSc() {
        return sc;
    }

    // Getter for ATM balance
    public static long getATMBalance() {
        return balance;
    }

    // Setter for ATM balance
    public static void setATMBalance(long balance) {
        ATM_OP.balance = balance;
    }

    // Start the ATM operations
    public static void start() throws CloneNotSupportedException {
        // Initialize admins and notes
        getAccDT().add(new Admin("admin", "admin123", "Gokul"));
        notes.add(new Two_Thousand("2000", 0));
        notes.add(new Five_Hundred("500", 0));
        notes.add(new Two_Hundred("200", 0));
        notes.add(new Hundred("100", 0));
        boolean run = true;

        // Main menu loop
        while (run) {
            try {
                // Display menu
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.println("Enter choice:");

                // Get user choice
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: // Admin login and operations
                        Admin admin =(Admin) Admin_Action.login(getAccDT());
                        if (getAccDT().contains(admin)) {
                            System.out.println("Admin Successfully login!");
                            System.out.println("Welcome " + admin.getName());
                            adminOps(admin);
                        }
                        break;
                    case 2: // Customer login and operations
                        Customer customer =(Customer) Customer_Action.login(getAccDT());
                        if (getAccDT().contains(customer)) {
                            System.out.println("User Successfully login!");
                            System.out.println("Welcome " + customer.getName());
                            userOps(customer.getId());
                        } else if (customer != null) System.out.println("User not found");
                        break;
                    case 3: // Exit the program
                        System.out.println("Exiting...");
                        run = false;
                        break;
                    default: // Invalid choice
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Admin operations menu
    public static void adminOps(Admin admin) {
        boolean runAdmin = true;
        while (runAdmin) {
            try {
                // Display admin menu
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. View Transactions");
                System.out.println("4. View Accounts");
                System.out.println("5. ATM Inventory");
                System.out.println("6. Exit");
                System.out.println("Enter choice:");

                // Get admin choice
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
                        Admin_Action.viewAllTransactions();
                        break;
                    case 4: // View all accounts
                        System.out.println("Viewing all accounts...");
                        Admin_Action.viewAllAccounts();
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
                for (Account Acc_cus: getAccDT()) {
                    if (Acc_cus instanceof Customer){
                        Customer cust =(Customer) Acc_cus;
                        if (cust.getId().equals(uid)) {
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
                                    if (!cust.getTransactions().isEmpty()) {
                                        System.out.println("Transaction history:");
                                        for (Transaction transact : cust.getTransactions()) {
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
                // Display ATM inventory menu
                System.out.println("1. ATM Balance");
                System.out.println("2. ATM Deposit");
                System.out.println("3. View ATM Transaction History");
                System.out.println("4. Back to Admin Menu");
                System.out.println("Enter the choice:");

                // Get admin choice
                int choice = Integer.parseInt(getSc().nextLine());
                switch (choice) {
                    case 1: // Check ATM balance
                        System.out.println("ATM Balance:" + getATMBalance());
                        break;
                    case 2: // Deposit money into ATM
                        Admin_Action.addATMoney(admin);
                        break;
                    case 3: // View ATM transaction history
                        if (admin.getTransactions().isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            for (Transaction transaction : admin.getTransactions()) {
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
