import java.util.Scanner;

public class Operation {
    private final Scanner sc=new Scanner(System.in);
    record Return(double a, double b) {}
    record Return1(double a, double b,double c) {}
    public double add(double a, double b){
        return a+b;
    }
    public double add(double a,double b, double c){
        return a+b+c;
    }
    public double sub(double a, double b){
        return a-b;
    }
    public double sub(double a,double b, double c){
        return a-b-c;
    }
    public double mul(double a,double b, double c){
        return a*b*c;
    }
    public double div(double a,double b, double c){
        double d= a/b;
        return d/c;
    }
    public double mul(double a, double b){
        return a*b;
    }
    public double div(double a, double b){
        return a/b;
    }
    public Return getTwoNumbers() {
        System.out.print("Enter the first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter the second number: ");
        double b = sc.nextDouble();
        return new Return(a, b);
    }
    public Return1 getThreeNumbers() {
        System.out.print("Enter the first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter the second number: ");
        double b = sc.nextDouble();
        System.out.print("Enter the third number: ");
        double c = sc.nextDouble();
        return new Return1(a, b, c);
    }
    public void ValueCheck(double a){
        if(a%1==0){
            System.out.println((long)(a));
        }
        else{
            System.out.println((a));
        }
    }
}