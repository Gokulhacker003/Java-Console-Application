
import Notes.Note;

import java.util.ArrayList;

public class Admin_Action {
    public static Admin login(ArrayList<Admin> admin) {
        Admin ad = new Admin();
        System.out.println("Enter ID:");
        String aid = ATM_OP.getSc().nextLine();
        int attempt=0;
        for (Admin admin1:admin) {
            while (attempt < 3) {
                if (admin1.getAdminId().equals(aid)) {
                    System.out.println("Enter Your Password:");
                    String pass = ATM_OP.getSc().nextLine();
                    if (admin1.getAdminPass().equals(pass)) {
                        ad=admin1;
                        break;
                    } else {
                        ++attempt;
                        System.out.println("Entered Password is wrong");
                        if (attempt == 3) {
                            System.out.println("Your Account is locked");
                        }
                    }
                } else {
                    System.out.println("Admin not found");
                    ad = new Admin();
                    break;
                }
            }
        }
        return ad;
    }

    public static void addUser() {
        System.out.println("Enter customer ID:");
        String uid = ATM_OP.getSc().nextLine();
        boolean userExists = false;
        for (Customer customer : ATM_OP.getCustList()) {
            if (customer.getID().equals(uid)) {
                userExists = true;
                break;
            }
        }
        if (userExists) {
            System.out.println("User already exists.");
        } else {
            System.out.println("Enter customer password:");
            String pass = ATM_OP.getSc().nextLine();
            System.out.println("Enter customer name:");
            String name = ATM_OP.getSc().nextLine();
            Customer newCustomer = new Customer(uid, pass, name);
            ATM_OP.addCustomer(newCustomer);
            System.out.println("Enter the initial amount");
            Customer_Action.depositAmt(newCustomer);

            System.out.println("Customer added successfully!");
        }
    }


    public static void deleteUser(String id) {
        Customer customer = ATM_OP.getCusID(id);
        if (customer != null) {
            ATM_OP.getCustList().remove(customer);
            ATM_OP.setATMBalance(ATM_OP.getATMBalance()-customer.getBalance());
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public static void viewAllAccounts() {
        ArrayList<Customer> customers = ATM_OP.getCustList();
        if (customers.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Customer Account Details:");
                System.out.println("ID: " + customer.getID());
                System.out.println("Name: " + customer.getName());
                System.out.println("-------------------------------");
            }
        }
    }
    public static void viewAllTransactions() {
        ArrayList<Customer> customers = ATM_OP.getCustList();
        if (customers.isEmpty()) {
            System.out.println("No User found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Transaction History for " + customer.getName() + ":");
                ArrayList<Transaction> transactions = customer.getTransHistory();
                if (transactions.isEmpty()) {
                    System.out.println("No transactions found.");
                } else {
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction);
                    }
                }
                System.out.println("-------------------------------");
            }
        }
    }
    public static void addATMoney(Admin admin){
        System.out.println("Enter the Deposit Amount");
        long Amount=Long.parseLong(ATM_OP.getSc().nextLine());
        System.out.println("2000₹ ="); 
        int Rs2000_count =Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("500₹ =");
        int Rs500_count =Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("200₹ =");
        int Rs200_count =Integer.parseInt(ATM_OP.getSc().nextLine());
        System.out.println("100₹ =");
        int Rs100_count =Integer.parseInt(ATM_OP.getSc().nextLine());
        long total_amt=(Long.parseLong("2000")*Rs2000_count)+(Long.parseLong("500")*Rs500_count)+(Long.parseLong("200")*Rs200_count)+(Long.parseLong("100")*Rs100_count);
        if(total_amt==Amount){
            for (Note note:ATM_OP.getNotes()) {
                String notes = note.getNote();
                switch (notes) {
                    case "2000":
                        note.setCount(note.getCount()+Rs2000_count);
                        break;
                    case "500":
                        note.setCount(note.getCount()+Rs500_count);
                        break;
                    case "200":
                        note.setCount(note.getCount()+Rs200_count);
                        break;
                    case "100":
                        note.setCount(note.getCount()+Rs100_count);
                        break;
                }
            }

            ATM_OP.setATMBalance(ATM_OP.getATMBalance() + Amount);
            addAdTransaction(new Transaction(admin.getAdminId(), "Deposit", Amount, ATM_OP.getATMBalance()));
            System.out.println("Your amount is deposited successfully");
        }
        else{
            System.out.println("Please Check the Denomination.");
        }
    }
    public static void addAdTransaction(Transaction trans){
        Admin.getAdTransaction().add(trans);
    }

}
