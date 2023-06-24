package org.example;

import org.example.models.Industry;
import org.example.models.Job;
import org.example.models.User;

import java.sql.*;

public class Database {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Database credentials

    private Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lightcity", "root", "M13831383mR");
    Statement stmt = conn.createStatement();

    public void ReadData(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            stmt.execute("USE lightcity");

            ResultSet user = stmt.executeQuery("SELECT * FROM user");
            while (user.next()){
                Data.users.add(new User(user.getString("Username"), user.getString("Password")));
            }

            ResultSet job = stmt.executeQuery("SELECT * FROM job");
            while(job.next()){
                int i = job.getInt("IndustryID");
                String id = Integer.toString(i);
                Data.jobs.add(new Job(job.getString("Title"), job.getFloat("Income"), id));
            }
































        } catch (Exception exp) {
            System.out.println("Database Exception : \n" + exp.toString());
            System.exit(0);
        }
    }

    public Database() throws SQLException {

    }
    //    Tables

    /**
     * Users
     */

//    private void createTables() {
////        query example
//        String query = "CREATE TABLE IF NOT EXISTS Users (username varchar(255) primary key ,password varchar(255));" +
//                "CREATE TABLE IF NOT EXISTS ....";
//        try {
//            Statement stmt = conn.createStatement();
//           if(stmt.execute(query)){
//
//           }else
//               System.out.println("An error accord during operation");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    public void loginGame(User user) {
        try {
            Statement stmt = conn.createStatement();
            String query = "";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
//               Check
            }
        } catch (Exception exception) {
        }

    }

    public void registerGame(User user) {
    }

}
