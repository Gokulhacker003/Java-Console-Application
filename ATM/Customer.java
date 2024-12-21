import java.util.ArrayList;

public class Customer{
    private String Cus_ID;
    private String Cus_pass;
    private String Cus_Name;
    public Customer(String New_Cus_ID, String New_Cus_pass, String New_Cus_name){
        this.Cus_ID=New_Cus_ID;
        this.Cus_Name=New_Cus_name;
        this.Cus_pass=New_Cus_pass;
    }

    public Customer() {
    }

    public String getCus_ID() {
        return Cus_ID;
    }

    public String getCus_Name() {
        return Cus_Name;
    }
    public String getCus_pass(){
        return Cus_pass;
    }
}
