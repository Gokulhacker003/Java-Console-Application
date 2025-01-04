import java.util.ArrayList;

public class Account {
    private String id; //ID
    private String pass; // password
    private String name; // name
    private ArrayList<Transaction> Transactions = new ArrayList<>(); //transaction history

    protected Account(String ID, String PASS, String NAME) {
        this.id = ID;
        this.pass = PASS;
        this.name = NAME;
    }
    protected Account(){

    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }
    public void setPass(String newpass){
        pass=newpass;
    }
}
