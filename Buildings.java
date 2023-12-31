package Dormitory;



import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Buildings implements Constants {
    // you have to insert your real database name
    // and also your user name in terms of user_name
    // and also your password in terms of user_password

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dormitory";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "aman1928;;,.";



    public Buildings() {
        defaults();
    }

    public void defaults() {
        addBuildingW("A3");
        addBuildingW("A1");
        addBuildingW("A2");
        addBuildingM("B0");
        addBuildingM("B2");
        addBuildingM("B1");
        addZone("female");
        addZone("male");
    }
// this is adding building table
    public static void addBuildingW(String name) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            createBuildingTable("buildingW");
            String zone_type = "Female";
            String insertQuery = "INSERT INTO buildingW (zone_type,building_name) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, zone_type);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBuildingM(String name) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            createBuildingTable("buildingM");
            String zone_type = "Male";
            String insertQuery = "INSERT INTO buildingM (zone_type ,building_name) VALUES (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, zone_type);
                preparedStatement.setString(2, name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addZone(String type) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            createZoneTable();
            String insertQuery = "INSERT INTO zone (zone_type, building_type) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, type);
                preparedStatement.setString(2, (type.equalsIgnoreCase("female") ? BUILDING_F : BUILDING_M));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DisplayBuilding() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM buildingW UNION SELECT * FROM buildingM";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        String buildingName = resultSet.getString("building_name");
                        System.out.println("\t\t" + buildingName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void DisplayBuildingWithProctor() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM proctors";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        String buildingName = resultSet.getString("building_name");
                        String proctorName = resultSet.getString("proctor_name");
                        System.out.println("\t\t" + buildingName + " | " + proctorName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static Set<String> proct = new HashSet<>();

    static void getProctroNameMen() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM proctors WHERE gender = 'M'";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        String proctorName = resultSet.getString("proctor_name");
                        proct.add(proctorName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getProctroNameWomen() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String selectQuery = "SELECT * FROM proctors WHERE gender != 'M'";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    while (resultSet.next()) {
                        String proctorName = resultSet.getString("proctor_name");
                        proct.add(proctorName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addProcterToBuilding(String name) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            getProctroNameWomen();
            addProctorToBuilding(connection, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//connecting database
    private static void addProctorToBuilding(Connection connection, String buildingName) {
        try {
            for (String proctor : proct) {
                String insertQuery = "INSERT INTO proctors (building_name, proctor_name) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, buildingName);
                    preparedStatement.setString(2, proctor);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void navbar() throws InterruptedException, IOException {
        System.out.println("\u001B[35m");
        System.out.println("\t---****WELL COME TO BUILDING SYSTEM****---");
        System.out.println("\u001B[0m");
        System.out.println("\t\t \uD83D\uDC491. Add new building ");
        System.out.println("\t\t \uD83D\uDC492. Add new  Zone ");
        System.out.println("\t\t \uD83D\uDC493. display building available ");
        System.out.println("\t\t \uD83D\uDC494. display Proctor with their assigned Building");
        System.out.println("\t\t \uD83D\uDC495. Add new proctor to Building ");
        System.out.println("\t\t \uD83D\uDC496 or any key to back to homePage ");
        System.out.println("\t\t \uD83D\uDC497.exit ");

        Scanner read = new Scanner(System.in);
        int ch = read.nextInt();
        Switcher(ch);
    }

    static public void displayBuildingDetail(String name) throws InterruptedException, IOException {
        System.out.println("\t\t\t WELL COME->\t\t\t HERE ARE ABOUT DORMTORY ");
        System.out.println("\t\t press any key you want to navigate in:");
        navbar();
    }

    static void Switcher(int ch) throws InterruptedException, IOException {
        Scanner read = new Scanner(System.in);
        switch (ch) {

            case 1:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.out.print("\t Enter Building Name: ");
                String bd = read.nextLine();

                //bellow method used to create new table in database dormitory
                // when new building is added because that building needs some
                // information to store
                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                    String tableName = bd + "_table"; // You can modify the table name as needed
                    String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                            "dorm_name VARCHAR(255)," +
                            "locker_num INT," +
                            "bed_num INT," +
                            "table_num INT" +
                            ")";

                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(createTableQuery);
                        System.out.println("Table created successfully!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println(
                        "\t\t Add the new building to Zone "
                                + "\t\t\n Available zone is Female and Male:" +
                                 " \n\t\t Enter one of them");
                String reads = read.next();
                if (reads.equalsIgnoreCase("Female")) {
                    addBuildingW(bd);
                    addZone(BUILDING_F);
                } else if (reads.equalsIgnoreCase("Male")) {
                    addBuildingM(bd);
                    addZone(BUILDING_M);
                } else
                    navbar();
                break;
            case 2:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.out.println("\t\t are you sure to create new zone? \n if so: Enter Zone name");
                String newZone = read.nextLine();
                addZone(newZone);
                navbar();
                break;

            case 3:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                DisplayBuilding();
                navbar();
                break;
            case 4:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                DisplayBuildingWithProctor();
                navbar();
                break;

            case 5:
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                System.out.println("\t\t Enter the building name to add proctors you have in data");
                String Bd = read.next();
                addProcterToBuilding(Bd);
                System.out.println("done");
                navbar();
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }

    private static void createBuildingTable(String tableName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    "building_name VARCHAR(255) PRIMARY KEY" +
                    ")";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createZoneTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS zone (" +
                    "zone_type VARCHAR(255) ," +
                    "building_type VARCHAR(255)" +
                    ")";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void adBuildingM() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS BuildingM (" +
                    "zone_type VARCHAR(255) ," +
                    "building_name VARCHAR(255)" +
                    ")";


            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void adBuildingW() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS buildingw (" +
                    "zone_type VARCHAR(255) ," +
                    "building_name VARCHAR(255)" +
                    ")";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



class tryout implements Constants {
    public static void main(String[] args) throws InterruptedException, IOException {
        Buildings.adBuildingW();
        Buildings.adBuildingM();
        Buildings.createZoneTable();
        Buildings bd = new Buildings();
        Dormitory.Admin.tringAdd();
        bd.addProcterToBuilding("B1");
        bd.addProcterToBuilding("A2");
        bd.addProcterToBuilding("A1");
        bd.addProcterToBuilding("B2");
        // Buildings.DisplayBuildingWithProctor();
        Buildings.displayBuildingDetail("A");
    }
}

