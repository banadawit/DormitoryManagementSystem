package Dormitory;

import java.sql.*;
import java.util.*;

class Dorm_Room extends Buildings {
    public static boolean rooms;
    static int studcounter;
    static int bedcounter = 0;
    String roomName;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dormitory";
    private static final String USERNAME = "user_name";
    private static final String PASSWORD = "password";

    public static void addRoom(String rm) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS dorm_rooms (" +
                    "room_name VARCHAR(255) PRIMARY KEY," +
                    "building_name VARCHAR(255)" +
                    ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
            System.out.println("please enter the building you want to add the dorm");


            String insertRoomQuery = "INSERT INTO dorm_rooms (room_name, building_name) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertRoomQuery)) {
                preparedStatement.setString(1, rm);
                preparedStatement.setString(2, isInbluilding(rm));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addtoBuilding(String buildingName) {
        // No need for this method as we manage building and rooms separately in tables.
    }


    public static String isInbluilding(String roomName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectBuildingQuery = "SELECT building_name FROM dorm_rooms WHERE room_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectBuildingQuery)) {
                preparedStatement.setString(1, roomName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("building_name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "not found";
    }

    public static int addStudentToDorm(String dormName, String stud) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectDormQuery = "SELECT * FROM dorm_rooms WHERE room_name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectDormQuery)) {
                preparedStatement.setString(1, dormName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String buildingName = resultSet.getString("building_name");
                        addRoom(dormName);
                        addtoBuilding(buildingName);
                        studcounter++;
                        return studcounter;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studcounter;
    }

    class Bed extends DormMaterial {
        // No need for this class as we use tables for storage.
    }
}

public class Dorm_Access {
    public static void main(String[] args) {

        Dorm_Room room1 = new Dorm_Room();
        room1.roomName = "room1";
        Dorm_Room room2 = new Dorm_Room();
        room2.roomName = "room2";
        Dorm_Room roomB = new Dorm_Room();
        roomB.roomName = "roomB";
        Dorm_Room roomC = new Dorm_Room();
        roomC.roomName = "roomC";
        Dorm_Room.addRoom(room2.roomName);//used to give the room name
        Dorm_Room.addRoom(roomC.roomName);//used to give the room name
        Dorm_Room.addRoom(room1.roomName);//used to give the room name
        Dorm_Room.addRoom(roomB.roomName);//used to give the room name
        System.out.println(Dorm_Room.rooms);

//        Student[] stud = {};
//        for (int i = 0; i < stud.length; i++) {
//            // stud[i].addStudent(stud[i].getUserName());
//        }

//        DormMaterial d = new DormMaterial();
//        d.displayBed();
//        d.removeMaterial("TABLE", 1, "roomC");
//        d.displayBed();
//        d.addMaterial("TABLE", 1, roomC.roomName);
//        System.out.println();
//        d.displayBed();
    }
}

