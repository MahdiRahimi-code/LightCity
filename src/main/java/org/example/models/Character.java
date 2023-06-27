package org.example.models;

import org.example.Data;
import org.example.Database;
import org.example.Menu;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.FastFoodShop;
import org.example.defualtSystem.Life;
import org.example.interfaces.CharacterInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class Character implements CharacterInterface {
    private User userInfo;
    private BankAccount account;
    private Life life;
    private Job job;
    public ArrayList<Property> properties;
    public ArrayList<Food> foods;
    private static int ID = 0;
    private int characterID;
    private Property inPosition;

    public Character(User userInfo, BankAccount account, Life life, Job job, ArrayList<Property> properties, Property inPosition) {
        this.userInfo = userInfo;
        this.account = account;
        this.life = life;
        this.job = job;
        this.properties = properties;
        this.inPosition = inPosition;
        properties = new ArrayList<>();
        foods = new ArrayList<>();
        ID++;
        characterID=ID;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void gotToLocation(Property destination){
        if(destination==null)return;
        inPosition = destination;
        Database.WriteData();
    }

    @Override
    public void positionProcessing() {
        Property chPosition = this.inPosition;
        Scanner input = new Scanner((System.in));

        if (chPosition.getCoordinate()[0]>=42 && chPosition.getCoordinate()[0]<=54){   //it is in industry

            if (chPosition.getCoordinate()[1]>=32 && chPosition.getCoordinate()[1]<=64){   // it is in bank
                Bank bank = Data.banks.get(0);
                System.out.println("You Are IN : " + bank.getTitle());

                //some Bank options
                //employee
                //see account

            }

            else{    // it is in shop
                FastFoodShop fastFoodShop = Data.fastFoodShops.get(0);
                System.out.println("You Are IN : " + fastFoodShop.getTitle());

                System.out.println("Options : (0 to back)");
                System.out.println("1 : Sign as Employee");
                System.out.println("2 : Buy Food");
                int answer = input.nextInt();
                while (true){
                    if (answer==1){
                        System.out.println("You want to Work As an Employee in : " + fastFoodShop.getTitle());
                        System.out.println("Here Is The Income : " + fastFoodShop.getEmployeeIncome());
                        System.out.println("Are You Sure ? (1:Yes/2:No/3:Back)");
                        int select = input.nextInt();

                        while (true){
                            if (select==1){
                                fastFoodShop.getEmployee().add(new Employee(this.userInfo.getUsername(), fastFoodShop,
                                        fastFoodShop.getEmployeeIncome(), this.account));
                                System.out.println("You Have Successfully Registered As Employee");
                                Menu.userMenu(this.userInfo);
                                Database.WriteData();
                                break;

                            }
                            else if (select == 2) {
                                this.positionProcessing();
                                break;

                            }
                            else if (select == 3) {
                                Menu.userMenu(this.getUserInfo());
                                break;

                            }
                            else{
                                System.out.println("Enter Correct Value");
                            }
                        }
                        break;

                    } else if (answer == 2) {
                        Data.fastFoodShops.get(0).buyFood(this);
                        this.positionProcessing();
                        break;

                    }else {
                        System.out.println("Enter correct value");
                    }
                }
            }
        }

        else {   // it is in property
            System.out.println("You are In : ");
            chPosition.toString();

            if (chPosition.getOwner() != null) {
                System.out.println("This Property Belongs To : " + chPosition.getOwner());
                System.out.printf("\tPrice : %f", chPosition.getPrice());
                System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
                int awnser = input.nextInt();
                while (true) {
                    if (awnser == 1) {
                        Data.municipalities.get(0).buyProperty(chPosition, this, chPosition.getOwner());
                        break;
                    } else if (awnser == 2) {
                        Menu.userMenu(this.userInfo);
                        break;
                    } else {
                        System.out.println("Incorrect Input");
                    }
                }

                Menu.userMenu(this.userInfo);
            } else if (chPosition.getOwner().equals(this)) {
                System.out.println("This is your Property");
                System.out.printf("\tPrice : %f", chPosition.getPrice());
                System.out.println("Do You Want To Sell It ? (1:YES/2:NO)");
                int awnser1 = input.nextInt();
                while (true) {
                    if (awnser1 == 1) {
                        Data.municipalities.get(0).sellProperty(chPosition, this);
                        break;
                    } else if (awnser1 == 2) {
                        Menu.userMenu(this.userInfo);
                        break;
                    } else {
                        System.out.println("Incorrect Input");
                    }
                }

                Menu.userMenu(this.userInfo);
            } else {
                System.out.println("You can Buy This Property");
                System.out.printf("\tPrice : %f", chPosition.getPrice());
                System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
                int awnser2 = input.nextInt();
                while (true) {
                    if (awnser2 == 1) {
                        Data.municipalities.get(0).buyProperty(chPosition, this, null);
                        break;
                    } else if (awnser2 == 2) {
                        Menu.userMenu(this.userInfo);
                        break;
                    } else {
                        System.out.println("Incorrect Input");
                    }
                }
            }
        }
    }

    public static Character searchCharacterByUser(User user){
        Character character = null;
        for (Character x : Data.characters) {
            if(x.getUserInfo().equals(user)){
                character = x;           //return character of user
                break;
            }
        }
        return character;
    }

    public int getCharacterID() {
        return characterID;
    }

    public Property getInPosition() {
        return inPosition;
    }
}