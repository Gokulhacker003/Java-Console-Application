import java.util.ArrayList;

public class Admin_Action {
    public static int login(String aid, String apass) {
        int status = 0;
        if ((aid.equals(Admin.getAdminId()))&&(apass.equals(Admin.getAdminPass()))) {
                System.out.println("Welcome " + Admin.getAdminName());
                System.out.println("Admin login successful!");
                status = 1;
        } else if(aid.equals(Admin.getAdminId())&&!(apass.equals(Admin.getAdminPass()))){
                status = -1;
        }
        return status;
    }

    public void addUser() {
        System.out.println("Enter customer ID:");
        String uname = ATM_OP.getSc().nextLine();
        System.out.println("Enter customer password:");
        String pass = ATM_OP.getSc().nextLine();
        System.out.println("Enter customer name:");
        String name = ATM_OP.getSc().nextLine();
        Customer newCustomer = new Customer(uname, pass, name);
        ATM_OP.addCustomer(newCustomer);
        System.out.println("Enter The Initial Amount:");
        double deposit =Double.parseDouble(ATM_OP.getSc().nextLine());
        int i = ATM_OP.getCustList().indexOf(newCustomer);
        Customer_Action.depositAmt(deposit,ATM_OP.getCustList().get(i));
        System.out.println("Customer added successfully!");
    }

    public void deleteUser(String id) {
        Customer customer = ATM_OP.getCusID(id);
        if (customer != null) {
            ATM_OP.getCustList().remove(customer);
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void viewAllTransactions() {
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
    public void viewAllAccounts() {
        ArrayList<Customer> customers = ATM_OP.getCustList();
        if (customers.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Customer customer : customers) {
                System.out.println("Transaction History for " + customer.getName() + ":");
                ArrayList<String> transactions = customer.getTransHistory();
                if (transactions.isEmpty()) {
                    System.out.println("No transactions found.");
                } else {
                    for (String transaction : transactions) {
                        System.out.println(transaction);
                    }
                }
                System.out.println("-------------------------------");
            }
        }
    }
}
