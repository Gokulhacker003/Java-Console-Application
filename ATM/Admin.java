import java.util.ArrayList;
import java.util.Scanner;

public class Admin{
    private static String ADname="Gokul";
    private static String AD_Id="Gokul@121";
    private static String AD_pass="Gokul@2715";
    public static boolean run =true;
    private static Scanner scanner =new Scanner(System.in);
    private ArrayList<Customer> customArray =new ArrayList<>();

    public static void CheckAdmin(String In_Id,String In_pass){
        if(In_Id.equals(AD_Id)){
            int attempt=1;
            while (attempt<=3){
                if (In_pass.equals((AD_pass))) {
                    System.out.println("Your are Login Successfully!");
                    System.out.printf("Welcome %s!%n", ADname);
                    Admin admin = new Admin();
                    admin.ADLogin();
                    break;
                } else {
                    System.out.println("Your Password is wrong Please enter your correct Password");
                    System.out.println("Enter Your Password:");
                    In_pass=scanner.nextLine();
                    attempt++;
                    if(attempt==3){
                        System.out.printf("%d No of Attempts",attempt);
                        System.out.println("You try to login More the 3 times so \n \t Please Try to Login Later");
                    }
                    else if (attempt<3){
                        System.out.printf("%d No of Attempts\n",attempt);
                    }
                }
            }
        }
        else{
            System.out.println("Sorry! Admin ID or Password is wrong");
        }
    }
    public void ADLogin(){
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
                    customArray.add(Add_user());
                }
                case 2->{
                    System.out.println("Deleting User");
                }
                case 3->{
                    System.out.println("View All Transaction");
                }
                case 4->{
                        System.out.println("Customer Account:");
                        this.ViewArray(customArray);
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
    public Customer Add_user()
    {
        System.out.println("Enter Customer ID:");
        String cus_ID=scanner.nextLine();
        System.out.println("Enter Customer Password:");
        String cus_pass=scanner.nextLine();
        System.out.println("Enter Customer Name:");
        String cus_name=scanner.nextLine();
        Customer customer=new Customer(cus_ID,cus_pass,cus_name);
        System.out.println("Customer is created");
        return customer;
    }
    public void ViewArray(ArrayList<Customer> customers)
    {
        customers =customArray;
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
