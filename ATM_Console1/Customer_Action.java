import java.util.ArrayList;

public class Customer_Action {
    public static Customer login(ArrayList<Customer> customers) {
        Customer custom=new Customer();
        System.out.println("Enter Your ID:");
        String id = ATM_OP.getSc().nextLine();
        int attempt =0;
        for (Customer cus :customers) {
            while (attempt<3){
                if(cus.getID().equals(id)){
                    System.out.println("Enter Your Password:");
                    String pass =ATM_OP.getSc().nextLine();
                    if (cus.getPassword().equals(pass)) {
                        return custom=cus;
                    }
                    else{
                        ++attempt;
                        System.out.println("Entered Password is wrong");
                        if (attempt == 3) {
                            return custom;
                        }
                    }
                }
                else{
                    System.out.println("User not found");
                    return custom=null;
                }
            }
        }
        return custom;
    }
    public static void changePassword(String uid, String oldPassword, String newPassword) {
        Customer customer = ATM_OP.getCusID(uid);
        if (customer != null) {
            if (customer.getPassword().equals(oldPassword)) {
                customer.setPassword(newPassword);
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Old password is incorrect.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }
    public static void addTransaction(String trans){
        Customer.getTransHistory().add(trans);
    }

    public static void depositAmt ( double amt,Customer customer){
        customer.setBalance(customer.getBalance()+amt);
        addTransaction("Deposited: " + amt + ", Balance: " + customer.getBalance());

    }

    public static void withdrawAmt(double amt,Customer customer) {
        if (amt % 100 != 0) {
            System.out.println("Invalid amount. Please enter multiples of 100.");
        }
        else if(amt > customer.getBalance()) {
            System.out.println("Insufficient funds.");
        }
        else {
            customer.setBalance(customer.getBalance()-amt);
            addTransaction("Withdrew: " + amt + ", Balance: " + customer.getBalance());

        }


    }
}