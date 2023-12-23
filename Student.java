package Dormitory;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Student extends PersonInfo implements Constants {
    static int studcounter;
    int nextId = 1;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dormitory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "banned1234";

    static {
        // Creating dorm_rooms table during initialization
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS dorm_rooms (" +
                    "student_name VARCHAR(255)," +
                    "building_name VARCHAR(255)" +
                    ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String whereIsmyDorm(String studName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String query = "SELECT building FROM dorm_rooms WHERE student_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return "You are in " + resultSet.getString("building") +
                                Dorm_Room.isInbluilding(resultSet.getString("building"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Not found";
    }

    Student(String username, String gender, int id, int type) {
        setUserName(username);
        setType(type);
        setId(id);
        setGender(gender);
    }

    public Student() {

    }
//selecting data
    public static void addToDorm() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String getStudentsQuery = "SELECT student_name FROM dorm_rooms";
            try (PreparedStatement getStudentsStatement = connection.prepareStatement(getStudentsQuery);
                 ResultSet studentsResultSet = getStudentsStatement.executeQuery()) {

                int index = 0;
                while (studentsResultSet.next()) {
                    String studentName = studentsResultSet.getString("student_name");

                    if (studcounter < 5) {
                        String dormName = getDormInfo(connection, index);
                        studcounter = Dorm_Room.addStudentToDorm(dormName, studentName);
                        insertStudent(connection, studentName, dormName);
                    } else if (getTotalRoomCount(connection) > index) {
                        String dormName = getDormInfo(connection, index);
                        studcounter = Dorm_Room.addStudentToDorm(dormName, studentName);
                        insertStudent(connection, studentName, dormName);
                        index++;
                    } else {
                        System.out.println("No more room");
                    }
                }

                System.out.println("\t\t==========Dormitory.Student to dorm============ \n\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getTotalRoomCount(Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM dorm_materials";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }


// Rest of the methods...


    private static String getDormInfo(Connection connection, int index) throws SQLException {
        String query = "SELECT room_name FROM dorm_materials LIMIT 1 OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, index);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("room_name");
                }
            }
        }
        return "";
    }

//    private static int getStudentCountInDorm(Connection connection, String dormName) throws SQLException {
//        String query = "SELECT COUNT(*) FROM dorm_rooms WHERE building = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, dormName);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt(1);
//                }
//            }
//        }
//        return 0;
//    }




    public static void navbar() throws InterruptedException, IOException {
        Scanner read = new Scanner(System.in);
        System.out.println("\t===========welcome to student part===============\n\n");
        System.out.println("\t\t here are your  navigatio");
        System.out.println("\t1. add new student:"
                + "\n\t2.Remove some student"
                + "\n\t3. search students dormF"
                + "\n\t4.See lists of all Dormitory.Student ");
        int ch = read.nextInt();
        switch (ch) {
            case 1:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                (new Admin()).registerNewStudent();
                break;
            case 2:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                String name = "";
                System.out.print("Enter student name to remove: ");
                (new Admin()).removeStudent(read.next());
                break;
            case 3:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                String srch = "";
                System.out.print("Enter name to search: ");
                String n = read.nextLine();
                srch = read.nextLine();
                String e = whereIsmyDorm(srch);
                System.out.println(e);
                break;
            case 4:
                // new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                (new Admin()).DisplayStudentList();
                break;
            default:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.out.println("you entered wrong input");
                break;
        }
    }


    static void studentPage() {
        System.out.println("Hello student! Here you can search for your dorm.");
        Scanner read = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = read.nextLine();
        System.out.println(whereIsmyDorm(username));
    }

    private static void insertStudent(Connection connection, String studentName, String building) throws SQLException {
        String query = "INSERT INTO dorm_rooms (student_name, building) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentName);
            preparedStatement.setString(2, building);
            preparedStatement.executeUpdate();
        }
    }
}

