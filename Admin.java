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
