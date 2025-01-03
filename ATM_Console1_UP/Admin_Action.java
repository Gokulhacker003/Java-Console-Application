import Notes.Note;
import java.util.ArrayList;

public class Admin_Action {

    // Method for admin login
    public static Admin login(ArrayList<Admin> adminList) {
        Admin loggedInAdmin = new Admin();//
        System.out.println("Enter ID:");
        String adminId = ATM_OP.getSc().nextLine();// Is get ID from Console 
        int attempts = 0; // it store an Attempt of Password

        for (Admin admin : adminList) { // it is used to Check the admin ID & Pass
            while (attempts < 3) {// It is used to to check the attempt
                if (admin.getAdminId().equals(adminId)) {//it check admin id
                    System.out.println("Enter Your Password:");
                    String password = ATM_OP.getSc().nextLine();
                    if (admin.getAdminPass().equals(password)) {//
                        loggedInAdmin = admin;
                        return loggedInAdmin;
                    } else {
                        attempts++;
                        System.out.println("Incorrect Password.");
                        if (attempts == 3) {
                            System.out.println("Your Account is Locked.");
                        }
                    }
                } else {
                    System.out.println("Admin Not Found.");
                    return new Admin();
                }
            }
        }
        return loggedInAdmin;
    }

    // Method to add a new user
    public static void addUser() {
        System.out.println("Enter Customer ID:");
        String userId = ATM_OP.getSc().nextLine();
        boolean userExists = false;

        for (Customer customer : ATM_OP.getCustList()) {
            if (customer.getID().equals(userId)) {
                userExists = true;
                break;
            }
        }

        if (userExists) {
            System.out.println("User Already Exists.");
        } else {
            System.out.println("Enter Customer Password:");
            String password = ATM_OP.getSc().nextLine();
            System.out.println("Enter Customer Name:");
            String name = ATM_OP.getSc().nextLine();

            Customer newCustomer = new Customer(userId, password, name);
            ATM_OP.addCustomer(newCustomer);

            System.out.println("Enter the Initial Amount:");
            Customer_Action.depositAmt(newCustomer);

            System.out.println("Customer Added Successfully!");
        }
    }

    // Method to delete a user
    public static void deleteUser(String userId) {
        Customer customer = ATM_OP.getCusID(userId);
        if (customer != null) {
            ATM_OP.getCustList().remove(customer);
            ATM_OP.setATMBalance(ATM_OP.getATMBalance() - customer.getBalance());
            System.out.println("Customer Deleted Successfully.");
        } else {
            System.out.println("Customer Not Found.");
        }
    }

    // Method to view all customer accounts
    public static void viewAllAccounts() {
        ArrayList<Customer> customers = ATM_OP.getCustList();
        if (customers.isEmpty()) {
            System.out.println("No Accounts Found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Customer Account Details:");
                System.out.println("ID: " + customer.getID());
                System.out.println("Name: " + customer.getName());
                System.out.println("-------------------------------");
            }
        }
    }

    // Method to view all transactions
    public static void viewAllTransactions() {
        ArrayList<Customer> customers = ATM_OP.getCustList();
        if (customers.isEmpty()) {
            System.out.println("No Users Found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Transaction History for " + customer.getName() + ":");
                ArrayList<Transaction> transactions = customer.getTransHistory();
                if (transactions.isEmpty()) {
                    System.out.println("No Transactions Found.");
                } else {
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                }
                System.out.println("-------------------------------");
            }
        }
    }

    // Method to add money to the ATM
    public static void addATMoney(Admin admin) {
        System.out.println("Enter the Deposit Amount:");
        long amount = Long.parseLong(ATM_OP.getSc().nextLine());
        System.out.println("2000₹ =");
        int Rs2000Count = Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("500₹ =");
        int Rs500Count = Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("200₹ =");
        int Rs200Count = Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("100₹ =");
        int Rs100Count = Integer.parseInt(ATM_OP.getSc().nextLine());

        long totalAmount = (2000L * Rs2000Count) + (500L * Rs500Count) + (200L * Rs200Count) + (100L * Rs100Count);

        if (totalAmount == amount) {
            for (Note note : ATM_OP.getNotes()) {
                switch (note.getNote()) {
                    case "2000" -> note.setCount(note.getCount() + Rs2000Count);
                    case "500" -> note.setCount(note.getCount() + Rs500Count);
                    case "200" -> note.setCount(note.getCount() + Rs200Count);
                    case "100" -> note.setCount(note.getCount() + Rs100Count);
                }
            }
            ATM_OP.setATMBalance(ATM_OP.getATMBalance() + amount);
            addAdTransaction(new Transaction(admin.getAdminId(), "Deposit", amount, ATM_OP.getATMBalance()));
            System.out.println("Amount Deposited Successfully!");
        } else {
            System.out.println("Please Check the Denomination.");
        }
    }

    // Method to add a transaction to the admin's transaction list
    public static void addAdTransaction(Transaction transaction) {
        Admin.getAdTransaction().add(transaction);
    }
}
