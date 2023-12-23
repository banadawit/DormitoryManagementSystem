package Dormitory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proctor extends PersonInfo implements Constants {

    Proctor(String name, String gender, int id, int type) {
        setGender(gender);
        setUserName(name);
        setType(type);
        setId(id);
    }

    public Proctor() {

    }

    public static void navbar() throws InterruptedException, IOException {

        System.out.print("\u001B[38;5;135m");
        System.out.println("\t---***Hello Welcome To Proctor Part: Here you are procter***---");
        System.out.print("\u001B[0m");
        System.out.println("   \t\t\tSet of chooses are below:\n");

        Scanner read = new Scanner(System.in);

        int n = 0;
        while (n != 7) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            System.out.println("\u001B[35m");
            System.out.println("\t\t---***Enter your your choose***--- ");
            System.out.println("    ===========================================");
            System.out.println("\u001B[0m");
            System.out.println(
                    "\t\t\uD83D\uDC491. Add material to room\n"
                            +"\t\t\uD83D\uDC492. Checking material availability\n"
                            + "\t\t\uD83D\uDC493. Remove material to room\n"
                            + "\t\t\uD83D\uDC494. Assign student from dorm\n");
                             + "\t\t\uD83D\uDC495 removing student from dorm\n");

            n = read.nextInt();
            switch (n) {
                case 1:
                    DormMaterial.displayBed();
                    break;
                case 2:
                    System.out.println(" choose type to be added\n "
                            + "\t1. Locker \n\t2.Bed \n\t3.table");
                    int ch = read.nextInt();
                    String type = "";
                    if (ch == 1)
                        type = Locker;
                    else if (ch == 2)
                        type = BED;
                    else if (ch == 3)
                        type = TABLE;
                    else
                        System.out.println("wrong input");
                    System.out.print("how many" + type + " Are you adding?");
                    int No = read.nextInt();
                    System.out.print("Enter dorm name");
                    String roomN = read.next();
                    DormMaterial.addMaterial(type, No, roomN);  //// have error
                    break;
//                case 3:
//                    break;
                case 3: {
                    System.out.println("\t what material are you removing? give name and type");
                    System.out.println("material can be removed are below \n" + "\n choose to type\n "
                            + "\t1. Locker \n\t2.Bed \n\t3.table");
                    ch = read.nextInt();
                    type = "";
                    if (ch == 1)
                        type = Locker;
                    else if (ch == 2)
                        type = BED;
                    else if (ch == 3)
                        type = TABLE;
                    else
                        System.out.println("wrong input");
                    System.out.print("how many" + type + " do you want to remove");
                    No = read.nextInt();
                    System.out.print("Enter dorm name");
                    roomN = read.next();
                    DormMaterial.removeMaterial(type, No, roomN);
                }
                break;
                case 4:
                    Student.addToDorm();
                    System.out.println("all student in list are added to dorm");
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("error input");
                    break;
            }

        }
    }
}

