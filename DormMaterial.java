package Dormitory;
import java.sql.*;
public class DormMaterial extends Dorm_Room implements Constants {

    // you have to insert your real database name
    // and also your user name in terms of user_name
    // and also your password in terms of user_password
<<<<<<< HEAD
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dormitory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aman1928;;,.";
=======
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ur_database";
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "user_password";

>>>>>>> 3a26ae6ea4d79c49ec076590d36c3a33efb50272
    private static int bedNum = 5;
    private static int lockerNum = 5;
    private static int tableNum = 2;

    DormMaterial() {
        defualDormWithMaterial();
    }
//inserting  data
    public static void defualDormWithMaterial() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS dorm_materials (" +
                    "room_name VARCHAR(255) PRIMARY KEY," +
                    "bed_num INT," +
                    "locker_num INT," +
                    "table_num INT" +
                    ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }

            if (rooms) { // Assuming `rooms` is a boolean indicating room availability
                insertMaterial(connection, "DefaultRoom", bedNum, lockerNum, tableNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void removeMaterial(String type, int num, String room) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            switch (type) {
                case Locker: {
                    lockerNum -= num;
                    break;
                }
                case TABLE: {
                    tableNum -= num;
                    break;
                }
                case BED: {
                    bedNum -= num;
                    break;
                }
                default:
                    System.out.println("No such type material here to remove");
            }
            updateMaterial(connection, room, bedNum, lockerNum, tableNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // giving condition with case

    public static void addMaterial(String type, int num, String room) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            switch (type) {
                case Locker: {
                    lockerNum += num;
                    break;
                }
                case TABLE: {
                    tableNum += num;
                    break;
                }
                case BED: {
                    bedNum += num;
                    break;
                }
                default:
                    System.out.println("No such type material here");
            }
            updateMaterial(connection, room, bedNum, lockerNum, tableNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //updating material

    private static void updateMaterial(Connection connection, String room, int bedNum, int lockerNum, int tableNum) {
        try {
            String updateQuery = "UPDATE dorm_materials SET bed_num = ?, locker_num = ?, table_num = ? WHERE room_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, bedNum);
                preparedStatement.setInt(2, lockerNum);
                preparedStatement.setInt(3, tableNum);
                preparedStatement.setString(4, room);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertMaterial(Connection connection, String room, int bedNum, int lockerNum, int tableNum) {
        try {
            String insertQuery = "INSERT INTO dorm_materials (room_name, bed_num, locker_num, table_num) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, room);
                preparedStatement.setInt(2, bedNum);
                preparedStatement.setInt(3, lockerNum);
                preparedStatement.setInt(4, tableNum);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkAvailableMaterial(String roomName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectMaterialQuery = "SELECT * FROM dorm_materials WHERE room_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectMaterialQuery)) {
                preparedStatement.setString(1, roomName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int availableBed = resultSet.getInt("bed_num");
                        int availableLocker = resultSet.getInt("locker_num");
                        int availableTable = resultSet.getInt("table_num");
                        System.out.println("Number of available bed is: " + availableBed +
                                " Number of available locker is: " + availableLocker +
                                " Number of available table is: " + availableTable);
                    } else {
                        System.out.println("Room not found in the database.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void displayBed() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectMaterialQuery = "SELECT * FROM dorm_materials";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectMaterialQuery)) {
                    while (resultSet.next()) {
                        String roomName = resultSet.getString("room_name");
                        int availableBed = resultSet.getInt("bed_num");
                        int availableLocker = resultSet.getInt("locker_num");
                        int availableTable = resultSet.getInt("table_num");
                        System.out.println(roomName + " | freeBed: " + availableBed +
                                " free locker: " + availableLocker + " free table: " + availableTable);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

