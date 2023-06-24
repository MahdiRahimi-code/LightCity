package org.example;

import org.example.defualtSystem.BankTurnover;
import org.example.models.BankAccount;
import org.example.models.Job;
import org.example.models.User;

import java.sql.*;
import java.text.SimpleDateFormat;


public class Database {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Database credentials

    private static Connection conn;

    public static void ReadData(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lightcity", "root", "M13831383mR");
            Statement stmt = conn.createStatement();

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

            ResultSet bankAccount = stmt.executeQuery(("SELECT * FROM bankaccount"));
            while(bankAccount.next()){
                String dateString = bankAccount.getString("LastChangeDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = (Date) dateFormat.parse(dateString);
                Data.bankAccounts.add(new BankAccount(bankAccount.getString("Owner"), bankAccount.getString("Password"), bankAccount.getFloat("Money"), date));
            }

            ResultSet bankTurnOver = stmt.executeQuery("SELECT * FROM bankturnover");
            while(bankTurnOver.next()){
                Data.bankTurnovers.add(new BankTurnover(bankTurnOver.getFloat("Withdraw"), bankTurnOver.getFloat("Deposit")));
            }

//            ResultSet character = stmt.executeQuery("SELECT * FROM character");
//            while (character.next()){
//                BankAccount bankAccount1 = null;
//                int bai = character.getInt("BankAccountID");
//                for (BankAccount ba : Data.bankAccounts){
//                    if (ba.getBankAccountID()==bai)
//                        bankAccount1=ba;
//                        break;
//                }
//                Data.characters.add(new Character(character.getString("UserInfo"), bankAccount1, ))
//            }


            conn.close();
        } catch (Exception exp) {
            System.out.println("Database Exception : \n" + exp.toString());
            System.exit(0);
        }
    }

    public static void WriteData() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lightcity", "root", "M13831383mR");

            Statement statement = conn.createStatement();

            statement.execute("USE lightcity");

            statement.executeUpdate("DELETE FROM user");

            for (User u : Data.users){
                String sqlQuery = "INSERT INTO user VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, u.getUserID());
                preparedStatement.setString(2, u.getUsername());
                preparedStatement.setString(3, u.getPassword());

            }


            conn.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//    public Database() throws SQLException {
//
//    }
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
