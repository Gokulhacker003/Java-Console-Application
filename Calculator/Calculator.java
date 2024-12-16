import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Operation op = new Operation();
        Scanner sc = new Scanner(System.in);
        int choose;
        int nofdigit;
        double num1, num2, num3, numCheck;
        boolean run = true;

        while (run) {
            System.out.println("1.Addition");
            System.out.println("2.Subtraction");
            System.out.println("3.Multiplication");
            System.out.println("4.Division");
            System.out.println("5.Exit");
            System.out.println("Enter the Choose:");
            choose = sc.nextInt();
            if (choose == 5) {
                run = false;
                System.out.println("Exiting...");
                continue;
            }
            System.out.println("1.Two Digits");
            System.out.println("2.Three Digits");
            System.out.println("Enter the no of Digits:");
            nofdigit = sc.nextInt();
            if (nofdigit == 1) {
                Operation.Return nums = op.getTwoNumbers();
                num1 = nums.a();
                num2 = nums.b();

                switch (choose) {
                    case 1 -> {
                        numCheck = op.add(num1, num2);
                        System.out.print("Sum = ");
                        op.ValueCheck(numCheck);
                    }
                    case 2 -> {
                        numCheck = op.sub(num1, num2);
                        System.out.print("Difference = ");
                        op.ValueCheck((numCheck));
                    }
                    case 3 -> {
                        numCheck = op.mul(num1, num2);
                        System.out.print("Product = ");
                        op.ValueCheck(numCheck);
                    }
                    case 4 -> {
                        if (num2 != 0) {
                            numCheck = op.div(num1, num2);
                            System.out.print("Quotient = ");
                            op.ValueCheck(numCheck);
                        } else
                            System.out.println("Division by zero is not allowed!");

                    }
                    default -> System.out.println("Invalid Choose");
                }
            }
            else if (nofdigit == 2) {
                Operation.Return1 nums = op.getThreeNumbers();
                num1 = nums.a();
                num2 = nums.b();
                num3 = nums.c();
                switch (choose) {
                    case 1 -> {
                        numCheck = op.add(num1, num2, num3);
                        System.out.print("Sum = ");
                        op.ValueCheck(numCheck);
                    }
                    case 2 -> {
                        numCheck = op.sub(num1, num2, num3);
                        System.out.print("Difference = ");
                        op.ValueCheck(numCheck);
                    }
                    case 3 -> {
                        numCheck = op.mul(num1, num2, num3);
                        System.out.print("Product = ");
                        op.ValueCheck(numCheck);
                    }
                    case 4 -> {
                        if (num2 != 0 && num3 != 0) {
                            numCheck = op.div(num1, num2, num3);
                            System.out.println("Quotient = ");
                            op.ValueCheck(numCheck);
                        } else
                            System.out.println("Division by zero is not allowed!");
                    }
                    default -> System.out.println("Invalid Choose");
                }
            } else System.out.println("Invalid digits");
        }
    }
}