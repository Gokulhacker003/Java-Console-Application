import Notes.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM_OP {
    private static long balance;
    private static ArrayList<Customer> custList = new ArrayList<>();
    private static ArrayList<Note> notes=new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static ArrayList<Note> getNotes(){
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
        notes.add(new Two_Thousand("2000",0));
        notes.add(new Five_Hundred("500",0));
        notes.add(new Two_Hundred("200",0));
        notes.add(new Hundred("100",0));
        Admin_Action adminact = new Admin_Action();
        boolean run = true;
        Scanner sc = new Scanner(System.in);
        while (run) {
            System.out.println("1. Customer");
            System.out.println("2. Admin");
            System.out.println("3. Exit");
            System.out.println("Enter choice:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    Customer customer =Customer_Action.login(getCustList());
                    if (getCustList().contains(customer)) {
                        System.out.println("User Successfully login!");
                        System.out.println("Welcome "+customer.getID());
                        userOps(customer.getID());
                    }
                    else if(customer!=null) System.out.println("User not found");
                    break;
                case 2:
                    System.out.println("Enter ID:");
                    String aid = sc.nextLine();
                    Admin_Action action=Admin_Action.login(aid,adminact);
                    if(action.equals(adminact))
                    {
                        System.out.println("Welcome "+Admin.getAdminName());
                        adminOps(adminact);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void adminOps(Admin_Action adminAction) {
        boolean runAdmin = true;
        while (runAdmin) {
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
                    adminAction.addUser();
                    break;
                case 2:
                    System.out.println("Deleting customer...");
                    System.out.println("Enter the customer id to Delete");
                    String User = getSc().nextLine();
                    adminAction.deleteUser(User);
                    break;
                case 3:
                    System.out.println("Viewing all transactions...");
                    adminAction.viewAllTransactions();
                    break;
                case 4:
                    System.out.println("Viewing all accounts...");
                    adminAction.viewAllAccounts();
                    break;
                case 5:
                    ATM_Inventory();
                    break;
                case 6:
                    System.out.println("Exiting admin ops...");
                    runAdmin = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void userOps(String uid) throws CloneNotSupportedException {
        boolean runUser = true;
        while (runUser) {

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
                                for (Transaction transact:cust.getTransHistory()) {
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
        }
    }
    public static void ATM_Inventory(){
        boolean run=true;
        while(run){
            System.out.println("1. ATM Balance");
            System.out.println("2. ATM Deposit");
            System.out.println("3. View ATM Transaction History");
            System.out.println("4. Back to Admin Menu");
            System.out.println("Enter the choice:");
            int choice=Integer.parseInt(getSc().nextLine());
            switch (choice){
                case 1:
                    System.out.println("ATM Balance:"+getATMBalance());
                    break;
                case 2:
                    Admin_Action.addATMoney();
                    break;
                case 3:
                    if (Admin.getAdTransaction().isEmpty()) {
                        System.out.println("No transactions found.");
                    }
                    else{
                        for (Transaction transaction : Admin.getAdTransaction()) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Back.....");
                    run=false;
                    break;
            }
        }
    }
    public static void Calculate(long Amount){

    }

}