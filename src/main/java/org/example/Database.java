package org.example;

import org.example.defualtSystem.*;
import org.example.models.*;
import org.example.models.Character;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Database {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // Database credentials

    private static Connection conn;

    public static void ReadData(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lightcity", "root", "qwerty");
            Statement stmt = conn.createStatement();

            stmt.execute("USE lightcity");


            ResultSet user = stmt.executeQuery("SELECT * FROM user");
            while (user.next()){
                Data.users.add(new User(user.getString("Username"), user.getString("Password")));
            }


            ResultSet bankTurnover = stmt.executeQuery("SELECT * FROM bankturnover");
            while (bankTurnover.next()){
                Data.bankTurnovers.add(new BankTurnover(bankTurnover.getFloat("Withdraw"), bankTurnover.getFloat("Deposit")));
            }


            ResultSet life = stmt.executeQuery("SELECT * FROM life");
            while (life.next()){
                Data.lives.add(new Life(life.getFloat("Food"), life.getFloat("Water"), life.getFloat("Sleep")));
            }


            ResultSet bankAccount = stmt.executeQuery("SELECT * FROM bankaccount");
            while (bankAccount.next()){
                String date = bankAccount.getString("LastChangeDate");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date lastChange = (Date) dateFormat.parse(date);
                Data.bankAccounts.add(new BankAccount(bankAccount.getString("Owner"), bankAccount.getString("Password"), bankAccount.getFloat("Money"), lastChange));
            }


            ResultSet food = stmt.executeQuery("SELECT * FROM food");
            while (food.next()){
                Data.foods.add(new Food(food.getString("Title"), food.getFloat("Water"), food.getFloat("Food")));
            }

            ResultSet job = stmt.executeQuery("SELECT * FROM job");
            while (job.next()){
                String title = job.getString("Title");
                Float income = job.getFloat("Income");
//                int industryID = job.getInt("IndustryID");

//                Data.jobs.add(new Job(title, income, industryID));
                Data.jobs.add(new Job(title, income, -1));
            }


            ResultSet character = stmt.executeQuery("SELECT * FROM `character`");
            while (character.next()){
                int userid = character.getInt("UserID");
                User user1 = searchUserByID(userid);

                int bankAccountID = character.getInt("BankAccountID");
                BankAccount bankAccount1 = searchBankAccountByID(bankAccountID);

                int lifeID = character.getInt("LifeID");
                Life life1 = searchLifeByID(lifeID);

//                int jobID = character.getInt("JobID");
//                Job job = searchJobByID(jobID);
//
//                String[] properties = character.getString("PropertiesID").split("@");
//                int propertiesID;
//                ArrayList<Property> chProperties = new ArrayList<>();
//                for (int i=0;i< properties.length;i++){
//                    propertiesID = Integer.parseInt(properties[i]);
//                    Property p = searchPropertyByID(propertiesID);
//                    chProperties.add(p);
//                }
//
//                int inPositionID = character.getInt("InPositionPropertyID");
//                Property inProperty = searchPropertyByID(inPositionID);

                String[] foods = character.getString("foodsID").split("@");
                int foodsID;
                ArrayList<Food> chFoods = new ArrayList<>();
                for (int i=0;i< foods.length;i++){
                    foodsID = Integer.parseInt(foods[i]);
                    Food f = searchFoodByID(foodsID);
                    chFoods.add(f);
                }

//                Data.characters.add(new Character(user1, bankAccount1, life1, job, chProperties, inProperty, chFoods));
                  Data.characters.add(new Character(user1, bankAccount1, life1, null, null, null, chFoods));
            }


            ResultSet property = stmt.executeQuery("SELECT * FROM property");
            while (property.next()){
                int ownerID = property.getInt("OwnerID");
                Character owner = null;
                if (ownerID!=0){
                    owner = searchCharacterByID(ownerID);
                }
                float[] coordinates = {property.getFloat("CoordinateX"), property.getFloat("CoordinateY")};
                float[] scales = {property.getFloat("Length"), property.getFloat("Width")};

                Property p = new Property(scales, coordinates, owner);
                Data.properties.add(p);
                owner.properties.add(p);
                owner.gotToLocation(p);
            }


            ResultSet fastfoodshop = stmt.executeQuery("SELECT * FROM fastfoodshop");
            while (fastfoodshop.next()){
                String title = fastfoodshop.getString("Title");

                int propertyID = fastfoodshop.getInt("PropertyID");
                Property property1 = searchPropertyByID(propertyID);

                String[] foods = fastfoodshop.getString("FoodsID").split("@");
                int foodsID;
                ArrayList<Food> shopFoods = new ArrayList<>();
                for (int i=0;i< foods.length;i++){
                    foodsID = Integer.parseInt(foods[i]);
                    Food f = searchFoodByID(foodsID);
                    shopFoods.add(f);
                }

                Data.fastFoodShops.add(new FastFoodShop(title, property1, null, shopFoods));
                Data.industries.add(new FastFoodShop(title, property1, null, shopFoods));
            }


            ResultSet bank = stmt.executeQuery("SELECT * FROM bank");
            while(bank.next()){
                int propertyID = bank.getInt("PropertyID");
                Property property1 = searchPropertyByID(propertyID);

                Character root = null;

//                String[] employees = bank.getString("EmployeesListID").split("@");
//                int employeesID;
//                ArrayList<Employee> bankEmployees = new ArrayList<>();
//                for (int i=0;i< employees.length;i++){
//                    employeesID = Integer.parseInt(employees[i]);
//                    Employee e = searchEmployeeByID(employeesID);
//                    bankEmployees.add(e);
//                }

//                Data.banks.add(new Bank(property1, root, bankEmployees));
                  Bank bank1 = new Bank(property1, root, null);
                  Data.banks.add(bank1);
                  Data.industries.add(bank1);
            }


            ResultSet employee = stmt.executeQuery("SELECT * FROM employee");
            while (employee.next()){
                String username = employee.getString("Username");
                Float baseSalary = employee.getFloat("BaseSalary");
//                Industry industry =searchIndustryByID(employee.getInt("IndustryID"));
                BankAccount bankAccount1 = searchBankAccountByID(employee.getInt("BankAccountID"));

//                Data.employees.add(new Employee(username, industry, baseSalary, bankAccount1));
                Data.employees.add(new Employee(username, null, baseSalary, bankAccount1));
            }


            Municipality municipality = new Municipality(Data.properties);

            City city = new City(Data.characters, Data.banks.get(0), municipality);

            Data.municipalities.add(municipality);
            Data.cities.add(city);

            conn.close();

        } catch (Exception exp) {
            System.out.println("Database Exception : \n" + exp.getMessage());
            System.exit(0);
        }
    }

    public static void dataWrite() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lightcity",
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

    private static User searchUserByID(int userid){
        for (User user : Data.users){
            if (user.getUserID() == userid){
                return user;
            }
        }
        return null;
    }

    private static BankAccount searchBankAccountByID(int bankAccountid){
        for (BankAccount b : Data.bankAccounts){
            if (b.getBankAccountID() == bankAccountid){
                return b;
            }
        }
        return null;
    }

    private static Life searchLifeByID(int lifeid){
        for (Life l : Data.lives){
            if (l.getLifeID() == lifeid){
                return l;
            }
        }
        return null;
    }

    private static Job searchJobByID(int jobid){
        for (Job j : Data.jobs){
            if (j.getJobID() == jobid){
                return j;
            }
        }
        return null;
    }

    private static Property searchPropertyByID(int propertyid){
        for (Property p : Data.properties){
            if (p.getPropertyID() == propertyid){
                return p;
            }
        }
        return null;
    }

    private static Food searchFoodByID(int foodid){
        for (Food f : Data.foods){
            if (f.getFoodID() == foodid){
                return f;
            }
        }
        return null;
    }

    private static Character searchCharacterByID(int characterid){
        for (Character c : Data.characters){
            if (c.getCharacterID() == characterid){
                return c;
            }
        }
        return null;
    }

    private static Employee searchEmployeeByID(int employeeID){
        for (Employee e : Data.employees){
            if (e.getEmployeeID() == employeeID){
                return e;
            }
        }
        return null;
    }

    private static Industry searchIndustryByID(int industryid){
        for (Industry i : Data.industries){
            if (i.getIndustryID() == industryid){
                return i;
            }
        }
        return null;
    }
}