package Dormitory;



import java.io.IOException;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) throws InterruptedException, IOException {
        String ty = "";
        Scanner read = new Scanner(System.in);
        System.out.println("==========WELCOME TO DORMITORY MANAGEMENT SYSTEM");
        System.out.println("enter what type of user are you. choose \n \t1 Admin \2 Proctor \t3 tudent");
        int ch = read.nextInt();
        if (ch == 1) {
            Admin a = new Admin();
            a.tringAdd();
            // a.DisplayStudentList();
            // a.removeStudent("A");
            // a.DisplayStudentList();
            a.AdminLog();
            // a.DisplayStudentList();
        } else if (ch == 3) {
            Student s = new Student();
            s.studentPage();
        } else if (ch == 2) {

            Proctor p = new Proctor();
            p.navbar();
        } else
            System.out.println("wrong input");
    }
}

