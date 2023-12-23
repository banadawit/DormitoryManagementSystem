package Dormitory;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Student extends PersonInfo implements Constants {
    static int studcounter;
    int nextId = 1;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/DORMITORY";
    private static final String USERNAME = "ayana";
    private static final String PASSWORD = "ayu10upme";

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

