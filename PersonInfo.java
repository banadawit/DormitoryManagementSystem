package Dormitory;

public class PersonInfo {
    private String USER_NAME;
    static int ID = 1000;
    private int TYPE;
    private String PASSWORD;
    private String GENDER;

    public void setGender(String Gender) {
        this.GENDER = Gender;
    }

    public String getGender() {
        return this.GENDER;
    }

    public void setType(int type) {
        this.TYPE = type;
    }

    public int getType() {
        return TYPE;
    }

    public static void setId(int ID) {
        PersonInfo.ID = ID;
    }

    public static int getId() {
        return PersonInfo.ID;
    }

    public void setUserName(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUserName() {
        return USER_NAME;
    }

    public String getPassword(int type) {
        return this.PASSWORD;
    }

    public void setPassword(String Password) {
        this.PASSWORD = Password;
    }

//    public static int GenereteID() {
//        int id = PersonInfo.getId();
//        ID = ID+1;
//        return ++id;
//
//    }
}

