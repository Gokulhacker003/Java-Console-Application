import java.util.ArrayList;
import java.util.Scanner;

public class Admin{
    private static String ADname="Gokul";
    private static String AD_Id="Gokul@121";
    private static String AD_pass="Gokul@2715";
    public static boolean run =true;
    private static Scanner scanner =new Scanner(System.in);
    private ArrayList<Customer> AC=new ArrayList<>();
    public static void CheckAdmin(String In_Id,String In_pass){
        if(In_Id.equals(AD_Id)){
            int attempt=0;
            while (attempt<3){
                attempt++;
                if (In_pass.equals((AD_pass))) {
                    System.out.println("Your are Login Successfully!");
                    System.out.printf("Welcome %s!%n", ADname);
                    Admin admin = new Admin();
                    admin.ADLogin();
                    break;
                } else{
                    System.out.printf("%d No of Attempts\n",attempt);
                    System.out.println("Your Password is wrong Please enter your correct Password");
                    System.out.println("Enter Your Password:");
                    In_pass=scanner.nextLine();
                    if(attempt==3){
                        System.out.println("You try to login 3 times So \n \t Please Try to Login Later");
                        break;
                    }
                }
            }
        }
        else{
            System.out.println("Sorry! Admin ID or Password is wrong");
        }
    }
    public void ADLogin(){
        Customer AddCust=null;
        boolean runAD=true;
        while(runAD){
            System.out.println("1.Add User Account");
            System.out.println("2.Delete User Account");
            System.out.println("3.View all Transaction");
            System.out.println("4.View all Account");
            System.out.println("5.Exit");
            System.out.println("Enter the Choice:");
                int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1->{
                    System.out.println("Creating Customer");
                    Add_user();
                }
                case 2->{
                    System.out.println("Deleting User");
                }
                case 3->{
                    System.out.println("View All Transaction");
                }
                case 4->{
                        System.out.println("Customer Account:");
                    System.out.println("\nAll Customers:");
                    for (Customer customer :this.AC) {
                        System.out.println(customer);
                    }
                    }

                case 5->{
                    System.out.println("Exiting User");
                    runAD =false;
                }
                default ->{
                    System.out.println("Invaild Choice ");
                }
            }
        }
    }

    private void Add_user() {
        System.out.println("Enter the Customer ID:");
        String ID =scanner.nextLine();
        System.out.println("Enter the Customer Pass:");
        String Pass =scanner.nextLine();
        System.out.println("Enter the Customer Name:");
        String Name =scanner.nextLine();
        Customer cus =new Customer(ID,Pass,Name);
        AC.add(cus);
        System.out.println("\nAll Customers:");
        for (Customer customer : AC) {
            System.out.println(customer);
        }
    }
}
