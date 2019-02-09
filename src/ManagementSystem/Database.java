/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Spark
 */
public class Database {

    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String CONNECTION = "jdbc:sqlite:StudentInformation.db";
    public static final String CREATE_TABLE1 = "CREATE TABLE if not exists StudentInformation("
            + "fullname,phone,DOB,gender,emailID,course,city,centre,semesterNO,studentID,registrationNo)";
    public static final String CREATE_TABLE2 = "CREATE TABLE if not exists StudentEnquiries("
            + "dateOfEnq,fullname,DOB,gender,phone,emailID,educationLevel,employmentStatus,school)";
    public static final String CREATE_TABLE3 = "CREATE TABLE if not exists EmployeeInformation("
            + "fullname,DOB,gender,phone,emailID,qualifications,iTSkills,maritalStatus,account)";
    public static final String CREATE_TABLE4 = "CREATE TABLE if not exists PaymentInformation("
            + "fullname,admissionStatus,amountPaid,paymentStatus,course)";
    static Connection con = null;
    static Statement stmt = null;

    public static void connect() {

        try {
            Class.forName(JDBC_DRIVER);
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            con = DriverManager.getConnection(CONNECTION, config.toProperties());
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {

        }

    }

    public static void createTable() {
        try {
            connect();
            stmt.executeUpdate(CREATE_TABLE4);
            System.out.println("Table Created Succesfully!");
        } catch (SQLException e) {

        }
    }

    public static int insertData(String sql) {
        int val = 0;
        try {
            connect();
            val = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return val;
    }

    public static ResultSet executeQuery(String sql) {
        connect();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {

        }
        return rs;
    }

    public static void main(String[] args) {
        createTable();
    }

}
