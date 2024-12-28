import Notes.Note;

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
                            System.out.println("Your Account is locked");
                            return custom=null;
                        }
                    }
                }
                else{

                    return custom;
                }
            }
        }
        return custom;
    }
    public static void changePassword(String uid, String oldPassword) {
        Customer customer = ATM_OP.getCusID(uid);
        if (customer != null) {
            if (customer.getPassword().equals(oldPassword)) {
                System.out.println("Enter your new password:");
                String newPassword = ATM_OP.getSc().nextLine();
                if(!customer.getPassword().equals(newPassword)) {
                    customer.setPassword(newPassword);
                    System.out.println("Password changed successfully!");
                }
                else{
                    System.out.println("New password is same as Old password");
                }
            } else {
                System.out.println("Old password is incorrect.");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }
    public static void addTransaction(Transaction trans,Customer cus){
        cus.getTransHistory().add(trans);
    }

    public static void depositAmt ( Customer customer){
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
        if (total_amt==Amount) {
            for (Note note:ATM_OP.getNotes()) {
                String notes = note.getNote();
                switch (notes) {
                    case "2000":
                        note.setCount(Rs2000_count);
                        break;
                    case "500":
                        note.setCount(Rs500_count);
                        break;
                    case "200":
                        note.setCount(Rs200_count);
                        break;
                    case "100":
                        note.setCount(Rs100_count);
                        break;
                }
            }
            customer.setBalance(customer.getBalance() + Amount);
            addTransaction(new Transaction(customer.getID(), "Deposit", Amount, customer.getBalance()), customer);
            ATM_OP.setATMBalance((ATM_OP.getATMBalance() + Amount));
        }
        else{
            System.out.println("Please Check the Denomination.");
        }
    }

    public static void withdrawAmt(Customer customer)throws CloneNotSupportedException{
        long amt = Long.parseLong(ATM_OP.getSc().nextLine());
        long finalAmt=amt;
        ArrayList<String> Note_tr=new ArrayList<>();
        ArrayList<Note> Note_Dp=new ArrayList<>();
        for (Note note:ATM_OP.getNotes()){
            Note_Dp.add(note.clone());
        }
        while(amt!=0){
            for (Note note:Note_Dp){
                String notes= note.getNote();
                switch (notes){
                    case "2000","500","200","100":
                        amt=(long) perform_Withdrawal(amt,Note_tr,note);
                        break;
                }
            }
            if(amt==0) {
                ATM_OP.setNotes(Note_Dp);
                customer.setBalance(customer.getBalance()-finalAmt);
                ATM_OP.setATMBalance(ATM_OP.getATMBalance()-finalAmt);
                for (String notes:Note_tr){
                    System.out.println("* "+notes);
                }
                addTransaction(new Transaction(customer.getID(), "Withdrawal", amt, customer.getBalance()), customer);
                break;
            }
            else{
                System.out.println("There is no denomination! Reenter the amount");
                break;
            }
        }
    }

    public static double perform_Withdrawal(double Amount,ArrayList<String> note_Tr,Note note){
        long count =(long) (Amount/Long.parseLong(note.getNote()));
        if (Long.parseLong(note.getNote())<=Amount&&count<= note.getCount()){
            Amount=Amount-(count* Long.parseLong(note.getNote()));
            note.setCount(note.getCount()-count);
            note_Tr.add("You got "+note.getNote()+" count "+count);
            return Amount;
        }
        return Amount;
    }
}