import Notes.Note;

import java.util.ArrayList;

public class Customer_Action {
    public static Account login(ArrayList<Account> Acc) { // Customer login
        Customer custom=new Customer();// Create Empty Customer
        System.out.println("Enter Your ID:");
        String id = ATM_OP.getSc().nextLine(); // getting id from user
        int attempt =0; //It stores the password attempt
        for(Account acc :Acc) {//Its is getting the Account details use loop
            if(acc instanceof Customer) {//It Checks the index of Acc is Customer
                while (attempt < 3) {//if password is wrong runs Until the 3 attempt
                    if (acc.getId().equals(id)) {//It checks Input id and user id
                        System.out.println("Enter Your Password:");
                        String pass = ATM_OP.getSc().nextLine();// It gets the pass
                        if (acc.getPass().equals(pass)) {//It checks the Password is right
                            custom = (Customer) acc;// it right customer account the
                            break;
                        } else {
                            ++attempt;
                            System.out.println("Entered Password is wrong");
                            if (attempt == 3) {
                                System.out.println("Your Account is locked");
                                custom = null;// if 3 attempt is completed it the null Customer
                            }
                        }
                    } else {

                        return custom;
                    }
                }
            }
        }
        return custom;
    }
    public static void changePassword(String uid, String oldPassword) { // it is Used to Change the password of Customer
        Customer customer = (Customer) ATM_OP.getAccID(uid);
        if (customer != null) {
            if (customer.getPass().equals(oldPassword)) {
                System.out.println("Enter your new password:");
                String newPassword = ATM_OP.getSc().nextLine();
                if(!customer.getPass().equals(newPassword)) {
                    customer.setPass(newPassword);
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
        cus.getTransactions().add(trans);
    }

    public static void depositAmt (Customer customer){
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

            customer.setBalance(customer.getBalance() + Amount);
            addTransaction(new Transaction(customer.getId(), "Deposit", Amount, customer.getBalance()), customer);
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
                addTransaction(new Transaction(customer.getId(), "Withdrawal", finalAmt, customer.getBalance()), customer);
                break;
            }
            else{
                System.out.println("There is no denomination! Reenter the amount");
                break;
            }
        }
        for (Note note:ATM_OP.getNotes()){
            System.out.printf("Note:%S Count%d \n",note.getNote(),note.getCount());
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
