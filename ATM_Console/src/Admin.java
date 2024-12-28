import java.util.ArrayList;

public class Admin {
    private static final String id = "admin";
    private static final String pass = "admin123";
    private static final String name ="Gokul";
    private static ArrayList<Transaction> AdTransaction=new ArrayList<>();


    public static String getAdminId() {
        return id;
    }

    public static String getAdminPass() {
        return pass;
    }

    public static String getAdminName() {
        return name;
    }

    public static ArrayList<Transaction> getAdTransaction() {
        return AdTransaction;
    }
}