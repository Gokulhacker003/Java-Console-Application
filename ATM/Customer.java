import java.util.ArrayList;
import java.util.Scanner;

public class Customer{
    private static String CusID;
    private static String CusPass;
    private static String CusName;
    private static final Scanner scanner=new Scanner(System.in);
    private ArrayList<Customer> ACus=new ArrayList<>();
    public Customer(String cusID, String cusPass, String cusName) {
        CusID = cusID;
        CusPass = cusPass;
        CusName = cusName;
    }
    public Customer(){
    }
    public static void CusCheck(String CU_ID,String CU_PASS){
        if(CU_ID.equals(CusID)){
            int attempt=0;
            while (attempt<3){
                attempt++;
                if (CU_PASS.equals((CusPass))) {
                    System.out.println("Your are Login Successfully!");
                    System.out.printf("Welcome %s!%n", "Cus_Name");
                    Customer C =new Customer();
                    System.out.println();
                    break;
                } else{
                    System.out.printf("%d No of Attempts\n",attempt);
                    System.out.println("Your Password is wrong Please enter your correct Password");
                    System.out.println("Enter Your Password:");
                    CU_PASS=scanner.nextLine();
                    if(attempt==3){
                        System.out.println("You try to login 3 times So \n \t Please Try to Login Later");
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Sorry! User ID or Password is wrong");
        }
    }
    @Override
    public String toString() {
        return String.format("Customer ID: %s, Customer Password: %s, Customer Name: %s", CusID, CusPass, CusName);
    }

    public void setACus(Customer ACus) {
        this.ACus.add(ACus);
    }

    public ArrayList<Customer> getACus() {
        return ACus;
    }
}
