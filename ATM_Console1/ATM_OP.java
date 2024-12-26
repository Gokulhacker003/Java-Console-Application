import java.util.ArrayList;
import java.util.Scanner;

public class ATM_OP {
    private static ArrayList<Customer> custList = new ArrayList<>();
    private int[] deno = {2000, 500, 200, 100};
    private int[] deno_count = new int[4];
    private double ATM_balance;
    private static Scanner sc = new Scanner(System.in);

    public double getATM_balance() {
        return ATM_balance;
    }

    public void setATM_balance(double ATM_balance) {
        this.ATM_balance = ATM_balance;
    }
    public static void addCustomer(Customer cust) {
        ATM_OP.custList.add(cust);
    }

    public static ArrayList<Customer> getCustList() {
        return custList;
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

    public int getDeno(int i) {
        return deno[i];
    }

    public int[] getDeno() {
        return deno;
    }

    public int[] getDeno_count() {
        return deno_count;
    }

    public void setDeno_count(int[] deno_count) {
        this.deno_count = deno_count;
    }

    public static void start() {
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
                        System.out.println("Welcome "+customer.getID());
                        userOps(customer.getID());
                    }
                    else if(customer==null){
                        System.out.println("ID Not found");
                    }
                    else{
                        System.out.println("Your Account is locked");
                    }
                    break;
                case 2:
                    System.out.println("Enter ID:");
                    String aid = sc.nextLine();
                    System.out.println("Enter Password:");
                    String apass = sc.nextLine();
                    int status = Admin_Action.login(aid, apass);
                    if (status == 1) {
                        adminOps();
                    } else if (status == -1) {
                        System.out.println("wrong password");
                    }
                    else System.out.println("user no found");
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

    public static void adminOps() {
        Admin_Action Admin_Action = new Admin_Action();
        boolean runAdmin = true;
        while (runAdmin) {
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. View Transactions");
            System.out.println("4. View Accounts");
            System.out.println("5. Exit");
            System.out.println("Enter choice:");
            int choice = Integer.parseInt(ATM_OP.getSc().nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Adding customer...");
                    Admin_Action.addUser();
                    break;
                case 2:
                    System.out.println("Deleting customer...");
                    System.out.println("Enter the customer id to Delete");
                    String User = ATM_OP.getSc().nextLine();
                    Admin_Action.deleteUser(User);
                    break;
                case 3:
                    System.out.println("Viewing all transactions...");
                    Admin_Action.viewAllAccounts();
                    break;
                case 4:
                    System.out.println("Viewing all accounts...");
                    Admin_Action.viewAllTransactions();
                    break;
                case 5:
                    System.out.println("Exiting admin ops...");
                    runAdmin = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void userOps(String uid) {
        boolean runUser = true;
        while (runUser) {
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. View Transactions");
            System.out.println("5. Change Password");
            System.out.println("6.Exit");
            System.out.println("Enter choice:");
            int choice = Integer.parseInt(ATM_OP.getSc().nextLine());

            for (Customer cust : custList) {
                if (cust.getID().equals(uid)) {
                    switch (choice) {
                        case 1:
                            System.out.println("Balance: " + cust.getBalance());
                            break;
                        case 2:
                            System.out.println("Enter withdrawal amount:");
                            double withdrawAmt = Double.parseDouble(ATM_OP.getSc().nextLine());
                            Customer_Action.withdrawAmt(withdrawAmt,cust);
                            break;
                        case 3:
                            System.out.println("Enter Deposit Amount");
                            double depo = Double.parseDouble(ATM_OP.getSc().nextLine());
                            Customer_Action.depositAmt(depo,cust);
                            break;
                        case 4:
                            ArrayList<String> trans = Customer.getTransHistory();
                            if (!trans.isEmpty()) {
                                System.out.println("Transaction history:");
                                trans.forEach(System.out::println);
                            } else {
                                System.out.println("No transactions found.");
                            }
                            break;
                        case 5:
                            System.out.println("Enter your current password:");
                            String currentPassword = ATM_OP.getSc().nextLine();
                            System.out.println("Enter your new password:");
                            String newPassword = ATM_OP.getSc().nextLine();
                            Customer_Action.changePassword(uid, currentPassword, newPassword);  // Change password method
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
}