package Dormitory;

/**
 * The PersonInfo class represents information about a person, including user details.
 */
public class PersonInfo {
    // Private fields to store user information
    private String USER_NAME;
    private static int ID = 1000; // Static ID initialized to 1000
    private int TYPE;
    private String PASSWORD;
    private String GENDER;

    /**
     * Sets the gender of the person.
     *
     * @param gender The gender to be set.
     */
    public void setGender(String gender) {
        this.GENDER = gender;
    }

    /**
     * Gets the gender of the person.
     *
     * @return The gender of the person.
     */
    public String getGender() {
        return this.GENDER;
    }

    /**
     * Sets the type of the person.
     *
     * @param type The type to be set.
     */
    public void setType(int type) {
        this.TYPE = type;
    }

    /**
     * Gets the type of the person.
     *
     * @return The type of the person.
     */
    public int getType() {
        return TYPE;
    }

    /**
     * Sets the ID for all instances of PersonInfo (static method).
     *
     * @param id The ID to be set.
     */
    public static void setId(int id) {
        PersonInfo.ID = id;
    }

    /**
     * Gets the common ID for all instances of PersonInfo (static method).
     *
     * @return The common ID for all instances.
     */
    public static int getId() {
        return PersonInfo.ID;
    }

    /**
     * Sets the username of the person.
     *
     * @param userName The username to be set.
     */
    public void setUserName(String userName) {
        this.USER_NAME = userName;
    }

    /**
     * Gets the username of the person.
     *
     * @return The username of the person.
     */
    public String getUserName() {
        return USER_NAME;
    }

    /**
     * Gets the password for the person based on the type (unused parameter).
     *
     * @param type The type of the person (unused).
     * @return The password of the person.
     */
    public String getPassword(int type) {
        return this.PASSWORD;
    }

    /**
     * Sets the password for the person.
     *
     * @param password The password to be set.
     */
    public void setPassword(String password) {
        this.PASSWORD = password;
    }
}
