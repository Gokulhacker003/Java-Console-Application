import java.util.Scanner;

public class ATM_AP {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        while(Admin.run){
            System.out.println("1.Customer");
            System.out.println("2.Admin");
            System.out.println("3.Exit");
            System.out.println("Enter Your Choice:");
            int choice= Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the Your ID:");
                    String Input_id = scanner.nextLine();
                    System.out.println("Enter Your Password:");
                    String Input_password = scanner.nextLine();
                    Customer.CusCheck(Input_id, Input_password);
                }
                case 2 -> {
                    System.out.println("Enter the Your ID:");
                    String Input_id = scanner.nextLine();
                    System.out.println("Enter Your Password:");
                    String Input_password = scanner.nextLine();
                    Admin.CheckAdmin(Input_id, Input_password);
                }
                case 3 -> {
                    Admin.run = false;
                    System.out.println("Exiting");
                }
                default -> System.out.println("Invaild choice!");
            }
        }
    }
}
