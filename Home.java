package Dormitory;



import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        try {
            String ty = ""; // Variable to store user type (unused)
            Scanner read = new Scanner(System.in);

            System.out.print("\u001B[31m");
            System.out.print("\u001B[1m");
            System.out.println("\t\t----*****WELCOME TO DORMITORY MANAGEMENT SYSTEM****------");
            System.out.println("\t-------------------------------------------------------------------");
            System.out.print("\u001B[0m");
            System.out.print("\u001B[0m");
            System.out.println("\t\t\t\tEnter What Type of user are you Choose \n\t\t\t\t\t\uD83D\uDC491. Admin\n" +
                                                                                 "\t\t\t\t\t\uD83D\uDC492. Proctor" +
                                                                                  "\n\t\t\t\t\t\uD83D\uDC493. student");

            int ch = read.nextInt();
            if (ch == 1) {
                Admin a = new Admin();
                a.tringAdd();
                a.AdminLog();
            }
            else if (ch == 2) {
                Proctor p = new Proctor();
                p.navbar();
            }
            else if (ch == 3) {
                Student s = new Student();
                s.navbar();
            }
            else {
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
    


