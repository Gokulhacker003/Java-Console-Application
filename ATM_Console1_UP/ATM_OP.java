import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM_OP {
    private static long balance;
    private static ArrayList<Customer> custList = new ArrayList<>();
    private static ArrayList<Note> notes = new ArrayList<>();
    private static ArrayList<Admin> adminsList = new ArrayList<>();
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

    public static void start() throws CloneNotSupportedException {
        getAdminsList().add(new Admin("admin", "admin123", "Gokul"));
        notes.add(new Two_Thousand("2000", 0));
        notes.add(new Five_Hundred("500", 0));
        notes.add(new Two_Hundred("200", 0));
        notes.add(new Hundred("100", 0));
        boolean run = true;
        while (run) {
            try {
                System.out.println("1. Admin");
                System.out.println("2. Customer");
                System.out.println("3. Exit");
                System.out.println("Enter choice:");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        Admin admin = Admin_Action.login(ATM_OP.getAdminsList());
                        if (getAdminsList().contains(admin)) {
                            System.out.println("Admin Successfully login!");
                            System.out.println("Welcome " + admin.getAdminName());
                            adminOps(admin);
                        }
                        break;
                    case 2:
                        Customer customer = Customer_Action.login(getCustList());
                        if (getCustList().contains(customer)) {
                            System.out.println("User Successfully login!");
                            System.out.println("Welcome " + customer.getName());
                            userOps(customer.getID());
                        } else if (customer != null) System.out.println("User not found");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void adminOps(Admin admin) {
        boolean runAdmin = true;
        while (runAdmin) {
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
                    case 1:
                        System.out.println("Adding customer...");
                        Admin_Action.addUser();
                        break;
                    case 2:
                        System.out.println("Deleting customer...");
                        System.out.println("Enter the customer id to Delete");
                        String User = getSc().nextLine();
                        Admin_Action.deleteUser(User);
                        break;
                    case 3:
                        System.out.println("Viewing all transactions...");
                        Admin_Action.viewAllTransactions();
                        break;
                    case 4:
                        System.out.println("Viewing all accounts...");
                        Admin_Action.viewAllAccounts();
                        break;
                    case 5:
                        ATM_Inventory(admin);
                        break;
                    case 6:
                        System.out.println("Exiting admin ops...");
                        runAdmin = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void userOps(String uid) throws CloneNotSupportedException {
        boolean runUser = true;
        while (runUser) {
            try {
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. View Transactions");
                System.out.println("5. Change Password");
                System.out.println("6. Exit");
                System.out.println("Enter choice:");
                int choice = Integer.parseInt(getSc().nextLine());
                for (Customer cust : custList) {
                    if (cust.getID().equals(uid)) {
                        switch (choice) {
                            case 1:
                                System.out.println("Balance: " + cust.getBalance());
                                break;
                            case 2:
                                System.out.println("Enter withdrawal amount:");

                                Customer_Action.withdrawAmt(cust);
                                break;
                            case 3:
                                System.out.println("Enter the Deposit Amount");
                                Customer_Action.depositAmt(cust);
                                break;
                            case 4:
                                if (!cust.getTransHistory().isEmpty()) {
                                    System.out.println("Transaction history:");
                                    for (Transaction transact : cust.getTransHistory()) {
                                        System.out.println(transact);
                                    }
                                } else {
                                    System.out.println("No transactions found.");
                                }
                                break;
                            case 5:
                                System.out.println("Enter your current password:");
                                String currentPassword = getSc().nextLine();
                                Customer_Action.changePassword(uid, currentPassword);  // Change password method
                                break;
                            case 6:
                                System.out.println("Exiting user ops...");
                                runUser = false;
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

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
                    case 1:
                        System.out.println("ATM Balance:" + getATMBalance());
                        break;
                    case 2:
                        Admin_Action.addATMoney(admin);
                        break;
                    case 3:
                        if (Admin.getAdTransaction().isEmpty()) {
                            System.out.println("No transactions found.");
                        } else {
                            for (Transaction transaction : Admin.getAdTransaction()) {
                                System.out.println(transaction);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Back.....");
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}
