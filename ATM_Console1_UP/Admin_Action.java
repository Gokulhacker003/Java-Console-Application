import Notes.Note;
import java.util.ArrayList;

public class Admin_Action {

    // Method for admin login
    public static Account login(ArrayList<Account> adminList) {
        Admin loggedInAdmin=null;
        System.out.println("Enter ID:");
        String adminId = ATM_OP.getSc().nextLine();
        int attempts = 0;

        for (Account adminAcc : adminList) {
            if (adminAcc instanceof Admin){
                while (attempts < 3) {
                    if (adminAcc.getId().equals(adminId)) {
                        System.out.println("Enter Your Password:");
                        String password = ATM_OP.getSc().nextLine();
                        if (adminAcc.getPass().equals(password)) {
                            loggedInAdmin = (Admin) adminAcc;
                            break;
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
        }
        return loggedInAdmin;
    }

    // Method to add a new user
    public static void addUser() {
        System.out.println("Enter Customer ID:");
        String userId = ATM_OP.getSc().nextLine();
        boolean userExists = false;

        for (Account customerAcc : ATM_OP.getAccDT()) {
            if (customerAcc instanceof Customer){
                if (customerAcc.getId().equals(userId)) {
                    userExists = true;
                    break;
                }
            }
        }

        if (userExists) {
            System.out.println("User Already Exists.");
        } else {
            System.out.println("Enter Customer Password:");
            String password = ATM_OP.getSc().nextLine();
            System.out.println("Enter Customer Name:");
            String name = ATM_OP.getSc().nextLine();

            Account newCustomer = new Customer(userId, password, name);
            ATM_OP.addCustomer(newCustomer);

            System.out.println("Enter the Initial Amount:");
            Customer_Action.depositAmt((Customer) newCustomer);

            System.out.println("Customer Added Successfully!");
        }
    }

    // Method to delete a user
    public static void deleteUser(String userId) {
        {
            Customer customer = (Customer) ATM_OP.getAccID(userId);
            if (customer != null) {
                ATM_OP.getAccDT().remove(customer);
                ATM_OP.setATMBalance(ATM_OP.getATMBalance() - customer.getBalance());
                System.out.println("Customer Deleted Successfully.");
            } else {
                System.out.println("Customer Not Found.");
            }
        }
    }

    // Method to view all customer accounts
    public static void viewAllAccounts() {
    for(Account acc:ATM_OP.getAccDT()){
        if (acc instanceof Customer customers) {
            System.out.println("Customer Account Details:");
            System.out.println("ID: " + customers.getId());
            System.out.println("Name: " + customers.getName());
            System.out.println("-------------------------------");
            }
        else if (!(acc instanceof Customer)&&(acc instanceof Admin)) {
            System.out.println();
        }
    }
    }

    // Method to view all transactions
    public static void viewAllTransactions() {
        for (Account accCus : ATM_OP.getAccDT()){
            if (!(accCus instanceof Customer)&&!(accCus instanceof Admin)) {
                System.out.println("No Users Found.");
            } else if(accCus instanceof Customer customer) {
                {
                    System.out.println("Transaction History for " + accCus.getName() + ":");
                    ArrayList<Transaction> transactions =customer.getTransactions();
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
            addAdTransaction(new Transaction(admin.getId(), "Deposit", amount, ATM_OP.getATMBalance()),admin);
            System.out.println("Amount Deposited Successfully!");
        } else {
            System.out.println("Please Check the Denomination.");
        }
    }

    // Method to add a transaction to the admin's transaction list
    public static void addAdTransaction(Transaction transaction,Admin admin) {
        admin.getTransactions().add(transaction);
    }
}
