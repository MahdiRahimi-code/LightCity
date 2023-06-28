package org.example;

import org.example.defualtSystem.BankTurnover;
import org.example.models.BankAccount;
import org.example.models.Job;
import org.example.models.User;

import java.sql.*;
import java.text.SimpleDateFormat;


public class Database {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Database credentials

    private static Connection conn;

    public static void ReadData(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/lightcity", "root", "qwerty");
            Statement stmt = conn.createStatement();

            stmt.execute("USE lightcity");

            ResultSet user = stmt.executeQuery("SELECT * FROM user");
            while (user.next()){
                String userString = user.getString("Password");
                String[] parts = userString.split("@");
                Data.users.add(new User(user.getString("Username"), parts[0]));
            }

//            ResultSet job = stmt.executeQuery("SELECT * FROM job");
//            while(job.next()){
//                int i = job.getInt("IndustryID");
//                String id = Integer.toString(i);
//                Data.jobs.add(new Job(job.getString("Title"), job.getFloat("Income"), id));
//            }
//
//            ResultSet bankAccount = stmt.executeQuery(("SELECT * FROM bankaccount"));
//            while(bankAccount.next()){
//                String dateString = bankAccount.getString("LastChangeDate");
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                Date date = (Date) dateFormat.parse(dateString);
//                Data.bankAccounts.add(new BankAccount(bankAccount.getString("Owner"), bankAccount.getString("Password"), bankAccount.getFloat("Money"), date));
//            }
//
//            ResultSet bankTurnOver = stmt.executeQuery("SELECT * FROM bankturnover");
//            while(bankTurnOver.next()){
//                Data.bankTurnovers.add(new BankTurnover(bankTurnOver.getFloat("Withdraw"), bankTurnOver.getFloat("Deposit")));
//            }

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

    public static void dataWrite() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/lightcity",
                    "root", "qwerty");

            Statement statement = connection.createStatement();

            statement.execute("USE lightcity");

            String sql = "DELETE FROM user";
            statement.executeUpdate(sql);

            for (User i : Data.users) {

                String sqlQuery = "INSERT INTO user VALUES (?, ?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

                preparedStatement.setInt(1, i.getUserID());
                preparedStatement.setString(2, i.getUsername());
                preparedStatement.setString(3, i.getPassword());

                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}