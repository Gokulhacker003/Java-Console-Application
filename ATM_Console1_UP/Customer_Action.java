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
        Customer customer = (Customer) ATM_OP.getAccID(uid);//it is Getting the id of customer object
        if (customer != null) {// it check the customer null 
            if (customer.getPass().equals(oldPassword)) {// It check the current password is right or wrong
                System.out.println("Enter your new password:");
                String newPassword = ATM_OP.getSc().nextLine();
                if(!customer.getPass().equals(newPassword)) {//It checks the new password is old password or not
                    customer.setPass(newPassword);// it is set the new password in arraylist of Account
                    System.out.println("Password changed successfully!");
                }
                else{// if it is old password it retrun the this is Your old password 
                    System.out.println("New password is same as Old password");
                }
            } else {// if old password is incorrect the retrun your old password is wrong
                System.out.println("Old password is incorrect.");
            }
        } else {// if user id is not found
            System.out.println("Customer not found.");
        }
    }
    // Used to add Transaction
    public static void addTransaction(Transaction trans,Customer cus){
        cus.getTransactions().add(trans);
    }
    // it is deposit the user amount
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
        // here get the note's count
        long total_amt=(Long.parseLong("2000")*Rs2000_count)+(Long.parseLong("500")*Rs500_count)+(Long.parseLong("200")*Rs200_count)+(Long.parseLong("100")*Rs100_count);
        if (total_amt==Amount) {// it check the amount is equal to the denomination's amount
            for (Note note:ATM_OP.getNotes()) {// it is used to get the notes object of storede in arraylist
                String notes = note.getNote();// it is store to check the note
                switch (notes) {// it chekc the not and add count
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

            customer.setBalance(customer.getBalance() + Amount);// it is add the amount in customer balance
            addTransaction(new Transaction(customer.getId(), "Deposit", Amount, customer.getBalance()), customer);// it add in transaction history
            ATM_OP.setATMBalance((ATM_OP.getATMBalance() + Amount));// It is used to set the latest balance of ATM after the adding customer deposit
        }
        else{
            System.out.println("Please Check the Denomination.");// if denomination is wrong 
        }
    }

    public static void withdrawAmt(Customer customer)throws CloneNotSupportedException{// It is throw an excetion for clone not supported for sone class
        long amt = Long.parseLong(ATM_OP.getSc().nextLine());// it is store the withdrawl amount
        long finalAmt=amt;//It is used to final amount
        ArrayList<String> Note_tr=new ArrayList<>();// it is used to display the Note's transaction
        ArrayList<Note> Note_Dp=new ArrayList<>();// it is used to clone the Note's ArrayList
        for (Note note:ATM_OP.getNotes()){// it used to get the note's object in Arraylist
            Note_Dp.add(note.clone());// It is used to add the clone the the orginal notes arraylist
        }
        while(amt!=0){// it is where the amount is != 0
            for (Note note:Note_Dp){// it is used to get the Note to from cloned arraylist object of Note
                String notes= note.getNote();// It is used to store the note 
                switch (notes){// it where checks for the notes 
                    case "2000","500","200","100":// it is notes with the notes name
                        amt=(long) perform_Withdrawal(amt,Note_tr,note);// this method made an calculation and retrun the final amount
                        break;
                }
            }
            if(amt==0) { // if final amount is 0 it will enter the this statement 
                ATM_OP.setNotes(Note_Dp);// it is set the the cloned arraylist with the orginal arraylist
                customer.setBalance(customer.getBalance()-finalAmt);//Reasignning the customers balance 
                ATM_OP.setATMBalance(ATM_OP.getATMBalance()-finalAmt);//Reasignning the ATM balance 
                for (String notes:Note_tr){// Display the Notes Transaction
                    System.out.println("* "+notes); 
                }
                addTransaction(new Transaction(customer.getId(), "Withdrawal", finalAmt, customer.getBalance()), customer);//Add the trnsaction with customers account
                break;
            }
            else{
                System.out.println("There is no denomination! Reenter the amount");
                break;
            }
        }
        for (Note note:ATM_OP.getNotes()){// it display the balance notes in the list
            System.out.printf("Note:%S Count%d \n",note.getNote(),note.getCount());
        }
    }

     public static double perform_Withdrawal(double Amount,ArrayList<String> note_Tr,Note note){
        long count =(long) (Amount/Long.parseLong(note.getNote()));
        if (Long.parseLong(note.getNote())<=Amount&& note.getCount()>0){
            if (count<= note.getCount()) {
                Amount = Amount - (count * Long.parseLong(note.getNote()));
                note.setCount(note.getCount() - count);
                note_Tr.add("You got " + note.getNote() + " count " + count);
            }
            else {
                Amount = Amount - (note.getCount() * Long.parseLong(note.getNote()));
                note.setCount(0);
                note_Tr.add("You got " + note.getNote() + " count " + count);
            }
            return Amount;
        }
        return Amount;
    }
}
