import java.util.ArrayList;

public class Customer {
    private String uid;
    private String pass;
    private String name;
    private double balance;
    private static ArrayList<String> transHistory;

    public Customer(String ID, String pass, String name) {
        this.uid = ID;
        this.pass = pass;
        this.name = name;
        this.balance = 0.0;
        transHistory = new ArrayList<>();
    }

    public Customer() {

    }


    public String getID () {
        return uid;
    }

    public String getPassword () {
        return pass;
    }

    public void setPassword (String pass){
        this.pass = pass;
    }

    public String getName () {
        return name;
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance ( double balance){
        this.balance = balance;
    }


    public static ArrayList<String> getTransHistory () {
        return transHistory;
    }



}