package Dormitory;



import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
        try {
            String ty = ""; // Variable to store user type (unused)
            Scanner read = new Scanner(System.in);
            System.out.println("==========WELCOME TO DORMITORY MANAGEMENT SYSTEM");
            System.out.println("Enter what type of user you are:");
            System.out.println("\t1 Admin\n\t2 Proctor\n\t3 Student");
            int ch = read.nextInt(); // Read user input
            if (ch == 1) { // If user is an admin
                Admin a = new Admin(); // Create Admin object
                a.tringAdd(); // Call method to add something (not clear from code)
                a.AdminLog(); // Call method for admin login (not clear from code)
                // a.DisplayStudentList(); // Display student list (commented out)
            } else if (ch == 3) { // If user is a student
                Student s = new Student(); // Create Student object
                s.studentPage(); // Call studentPage method
            } else if (ch == 2) { // If user is a proctor
                Proctor p = new Proctor(); // Create Proctor object
                p.navbar(); // Call navbar method
            } else {
                System.out.println("Wrong input"); // Invalid user input
            }
        } catch (InputMismatchException e) { // Catch input mismatch exception
            System.out.println("Invalid input. Please enter a valid choice.");
        } catch (IOException e) { // Catch IO exception
            System.out.println("An error occurred while reading input: " + e.getMessage());
        } catch (Exception e) { // Catch any other exception
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
    


