import Notes.Note;
import java.util.ArrayList;

public class Admin_Action {

    // Method for admin login
    public static Account login(ArrayList<Account> adminList) {
        Admin loggedInAdmin=null; // It is User to store the Admin Object address
        System.out.println("Enter ID:");
        String adminId = ATM_OP.getSc().nextLine();
        int attempts = 0;//it is stores the no of Attempt

        for (Account adminAcc : adminList) {// It is get the Arraylist Account from Account 
            if (adminAcc instanceof Admin){// it check the Account of object is Admin or not 
                while (attempts < 3) {// if it is admin it check the no of attempt is 3
                    if (adminAcc.getId().equals(adminId)) {// It check the admin ID is Right it will get the password 
                        System.out.println("Enter Your Password:");
                        String password = ATM_OP.getSc().nextLine();
                        if (adminAcc.getPass().equals(password)) {//It check the admin password is right it will retrun the loggedInAdmin as Admin 
                            loggedInAdmin = (Admin) adminAcc;
                            break;
                        } else {// else password is wrong it will retrun retry the password 
                            attempts++;// increasing the attempt
                            System.out.println("Incorrect Password.");
                            if (attempts == 3) {//if attempt is 3 that account will locked
                                System.out.println("Your Account is Locked.");
                            }
                        }
                    } else {//If there is Adin is not found it will retrun the new admin 
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
        boolean userExists = false;//It is used to store the User is exists or not in boolean format

        for (Account customerAcc : ATM_OP.getAccDT()) {// It is used to get the Objects stored in Account ArrayList
            if (customerAcc instanceof Customer){//It check those Object are Customer's Object or not
                if (customerAcc.getId().equals(userId)) {//It check the Customers ID is exists or not
                    userExists = true;//if exists reassign the boolen value is true
                    break;
                }
            }
        }

        if (userExists) {// If that boolean value is true it display that User Already exists
            System.out.println("User Already Exists.");
        } else {// If not exists then go to add the User
            System.out.println("Enter Customer Password:");
            String password = ATM_OP.getSc().nextLine();
            System.out.println("Enter Customer Name:");
            String name = ATM_OP.getSc().nextLine();

            Account newCustomer = new Customer(userId, password, name);// It is Where store for add Account this is an Implicit conversion
            ATM_OP.addCustomer(newCustomer);//it call the add Customer method and pass the argument is newCustomer

            System.out.println("Enter the Initial Amount:");
            Customer_Action.depositAmt((Customer) newCustomer);//it is pass an argument newCustomer for store the store the balance in this customer and explicitly convert the Account to customer

            System.out.println("Customer Added Successfully!");
        }
    }

    // Method to delete a user
    public static void deleteUser(String userId) {
        {
            Customer customer = (Customer) ATM_OP.getAccID(userId);//it store to check the customer and explicitly convert the Account to customer
            if (customer != null) {// it check the customer is not equal to null
                ATM_OP.getAccDT().remove(customer);// it is removing the customer's Account
                ATM_OP.setATMBalance(ATM_OP.getATMBalance() - customer.getBalance());// It is where to reassetting the value after removing the customer account
                System.out.println("Customer Deleted Successfully.");
            } else {//if customer is null it will retrun the Customer not found
                System.out.println("Customer Not Found.");
            }
        }
    }

    // Method to view all customer accounts
    public static void viewAllAccounts() {
        for(Account acc:ATM_OP.getAccDT()){// It is used to get the Objects stored in Account ArrayList
            if (acc instanceof Customer customers) {//It check those Object are Customer's Object or not
                System.out.println("Customer Account Details:");
                System.out.println("ID: " + customers.getId());
                System.out.println("Name: " + customers.getName());
                System.out.println("-------------------------------");
                }
            else System.out.println("No customer account");

        }
    }

    // Method to view all transactions
    public static void viewAllTransactions() {// It is used to get the Objects stored in Account ArrayList
        for (Account accCus : ATM_OP.getAccDT()){
            if (!(accCus instanceof Customer)&&!(accCus instanceof Admin)) {//It check those Object are Customer's Object or not
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
        // getting all all denomination Count
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
