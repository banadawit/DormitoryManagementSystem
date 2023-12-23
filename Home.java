package Dormitory;



import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        try {
            String ty = "";
            Scanner read = new Scanner(System.in);
            System.out.println("==========WELCOME TO DORMITORY MANAGEMENT SYSTEM");
            System.out.println("Enter what type of user you are:");
            System.out.println("\t1 Admin\n\t2 Proctor\n\t3 Student");
            int ch = read.nextInt();
            if (ch == 1) {
                Admin a = new Admin();
                a.tringAdd();
                a.AdminLog();
                // a.DisplayStudentList();
            } else if (ch == 3) {
                Student s = new Student();
                s.studentPage();
            } else if (ch == 2) {
                Proctor p = new Proctor();
                p.navbar();
            } else {
                System.out.println("Wrong input");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

