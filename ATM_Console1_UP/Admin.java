import java.util.ArrayList;

public class Admin {
    private String id;
    private String pass;
    private String name;
    private static ArrayList<Transaction> AdTransaction=new ArrayList<>();


    public Admin(String ID,String PASS,String NAME){
        this.id=ID;
        this.pass=PASS;
        this.name=NAME;
    }

    public Admin() {

    }

    public String getAdminId() {
        return this.id;
    }

    public String getAdminPass() {

        return this.pass;
    }

    public String getAdminName() {
        return this.name;
    }

    public static ArrayList<Transaction> getAdTransaction() {
        return AdTransaction;
    }
}