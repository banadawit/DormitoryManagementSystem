package Dormitory;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

interface AdminPage {
    void registerNewStudent();

    void addProcter();

    public void removeStudent(String name);

    void DisplayStudentList();

    void displayBuildingDetail(String name);
}
// using interface
interface IdGenerator {
    int generateId();
}
public class Admin extends PersonInfo implements AdminPage, Constants {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dormitory";
    private static final String USERNAME = "ayana";
    private static final String PASSWORD = "ayu10upme";

    private Connection connection;

    static String name = "a";
    static String psd = "a";
    public void AdminLog() throws InterruptedException, IOException {
        Scanner read = new Scanner(System.in);
        System.out.print("\u001b[38;5;208m");
        System.out.println("\t\t-----***WELL COME TO ADMIN PAGE***-------");
        System.out.println("\t--------------------------------------------------");
        System.out.print("\u001b[0m");
        System.out.print("\t\t\tLogin to your account here: \n\t\t\tEnter your user name: ");
        String name = "";
        String psd = "";
        try (read) {

            try {
                name = read.next();

            } catch (Exception e) {
                System.out.println("error happen" + e.getMessage());
            }
            System.out.print("Enter password: ");
            psd = read.next();

            if (login(name, psd)) {
                System.out.println("you are logged in as admin");
                // navigation
                int ch = 0;
                while (ch != 5) {
                    System.out.println("\t\tCHOOSE WHAT TO DO");
                    System.out.println(
                            "\t1. About student:"
                                    + "\n\t2. About proctor: "
                                    + "\n\t3. About Building to system:"
                                    + "\n\t4. exit");
                    ch = read.nextInt();
                    switch (ch) {
                        case 1:
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            Student.navbar();
                            break;
                        case 2:
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            Proctor.navbar();
                            break;
                        case 3:
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            Buildings.navbar();
                            break;

                        case 4:
                            break;
                        default:
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            System.out.println("unkown input!!");
                            break;
                    }
                }
            }
        }

    }

    boolean login(String user_Name, String password) {
        if ((user_Name.equals(name) && password.equals(psd))) {
            System.out.println("login success!!");
            return true;
        } else {
            System.out.println("The userUSER_name or password entered was not correct!");
            return false;
        }
    }
    public Admin() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            createTables();  // Call method to create tables during initialization
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            Statement statement = connection.createStatement();

            // Create students table
            String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "gender VARCHAR(1)," +
                    "type VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createStudentsTable);

            // Create proctors table
            String createProctorsTable = "CREATE TABLE IF NOT EXISTS proctors (" +
                    "id INT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "gender VARCHAR(1)," +
                    "type VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createProctorsTable);

            // Create dorm_rooms table
            String createDormRoomsTable = "CREATE TABLE IF NOT EXISTS dorm_rooms (" +
                    "student_name VARCHAR(255)," +
                    "room_name VARCHAR(255)" +
                    ")";
            statement.executeUpdate(createDormRoomsTable);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudent(String name) {
        try {
            String sql = "DELETE FROM students WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Dormitory.Student removed successfully");
                } else {
                    System.out.println("Dormitory.Student not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerNewStudent() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter student information here: ");
        System.out.print("Enter Name: ");
        String name = read.nextLine();
        System.out.print("Gender M/F: ");
        String gender = read.next();
        System.out.println("Enter the id of student");
        int id = read.nextInt();

        try {
            String sql = "INSERT INTO students (name, gender, id, type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, gender);
                preparedStatement.setInt(3, id);
                preparedStatement.setString(4, String.valueOf(STUDENT));
                preparedStatement.executeUpdate();
            }

            // Insert the student into the dorm_rooms table
//            Dorm_Room.insertStudentToDorm(connection, name, dormName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void tringAdd() {
        // This method is kept as is since it's just adding data for testing purposes
    }

    @Override
    public void addProcter() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter Proctor information here: ");
        System.out.print("Enter Name: ");
        String name = read.nextLine();
        System.out.print("Gender M/F: ");
        String gender = read.next();
        System.out.println("Enter the id of proctor");
        int id = read.nextInt();
        try {
            String sql = "INSERT INTO proctors (name, gender, id, type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, gender);
                preparedStatement.setInt(3,id);
                preparedStatement.setString(4, String.valueOf(ADMIN));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void DisplayStudentList() {
        try {
            String sql = "SELECT * FROM students";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        System.out.println("Name: " + resultSet.getString("name"));
                        System.out.println("Gender: " + resultSet.getString("gender"));
                        System.out.println("ID: " + resultSet.getInt("id"));
                        System.out.println("Type: " + resultSet.getString("type"));
                        System.out.println("------------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayBuildingDetail(String name) {
        Buildings b = new Buildings();
        // Assuming this method needs to be completed
    }
}
class tyring {
    public static void main(String[] args) throws InterruptedException, IOException {
        Admin a = new Admin();
        Buildings.adBuildingW();//used to create table automatically on our mysql
        Buildings.adBuildingM();//used to create table automatically on our mysql
        Buildings.createZoneTable();//used to create table automatically on our mysql
//        Dorm_Room .addRoom("dorrm");
        DormMaterial.defualDormWithMaterial();//used to create the dorm material
        a.tringAdd();
        a.AdminLog();
    }
}
