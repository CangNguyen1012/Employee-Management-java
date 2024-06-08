import java.sql.*;
import javax.swing.*;

public class EmployeeData {

    public static Connection ConnectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\bruno\\Downloads\\eclipse-java-2022-03-R-win32-x86_64\\eclipse\\workspace\\JavaProgramming\\Lab8EmployeeManagement\\employee.db");
            JOptionPane.showMessageDialog(null, "Connection Made");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection Error");
            return null;
        }
    }

    public static void dropTables(Connection conn) {
        String dropSQL = "DROP TABLE IF EXISTS employee";
        try (PreparedStatement pst = conn.prepareStatement(dropSQL)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables(Connection conn) {
        String createSQL = "CREATE TABLE employee ("
                + "ID INT PRIMARY KEY,"
                + "First_Name VARCHAR(50),"
                + "Last_Name VARCHAR(50),"
                + "Gender VARCHAR(10),"
                + "BOD DATE,"
                + "Address VARCHAR(100),"
                + "Phone VARCHAR(15),"
                + "Email VARCHAR(50)"
                + ")";
        try (PreparedStatement pst = conn.prepareStatement(createSQL)) {
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDefaultData(Connection conn) {
        String insertSQL = "INSERT INTO employee (ID, First_Name, Last_Name, Gender, BOD, Address, Phone, Email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(insertSQL)) {
            pst.setInt(1, 1);
            pst.setString(2, "John");
            pst.setString(3, "Doe");
            pst.setString(4, "Male");
            pst.setDate(5, java.sql.Date.valueOf("1990-01-01"));
            pst.setString(6, "123 Main St");
            pst.setString(7, "555-5555");
            pst.setString(8, "john.doe@example.com");
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = ConnectDB();
        if (conn != null) {
            try {
                dropTables(conn);
                createTables(conn);
                insertDefaultData(conn);
                JOptionPane.showMessageDialog(null, "Database reset completed successfully.");
            } finally {
                try {
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database.");
        }
    }
}
