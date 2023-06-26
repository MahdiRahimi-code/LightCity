package org.example.models;

import org.example.Data;
import org.example.Database;
import org.example.Menu;
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
    private static int ID = 0;
    private int characterID;
    private Property inPosition;

    public Character(User userInfo, BankAccount account, Life life, Job job, ArrayList<Property> properties,
            Property inPosition) {
        this.userInfo = userInfo;
        this.account = account;
        this.life = life;
        this.job = job;
        this.properties = properties;
        this.inPosition = inPosition;
        ID++;
        characterID = ID;
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

    public void gotToLocation(Property destination) {
        if (destination == null)
            return;
        inPosition = destination;
        Database.WriteData();
    }

    @Override
    public void positionProcessing(Property property) {
        System.out.println("You are In : ");
        Property chProperty = this.inPosition;
        chProperty.toString();
        Scanner input = new Scanner((System.in));

        if (chProperty.getOwner() != null){
            System.out.println("This Property Belongs To : "+chProperty.getOwner());
            System.out.printf("\tPrice : %f", chProperty.getPrice());
            System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
            int awnser = input.nextInt();
            while (true){
                if (awnser==1){
                    Data.municipalities.get(0).sellProperty(chProperty, this);
                    break;
                } else if (awnser == 2) {
                    Menu.userMenu(this.userInfo);
                    break;
                }else {
                    System.out.println("Incorrect Input");
                }
            }

            Menu.userMenu(this.userInfo);
        }

        else if(chProperty.getOwner().equals(this)){
            System.out.println("This is your Property");
            System.out.printf("\tPrice : %f", chProperty.getPrice());
            System.out.println("Do You Want To Sell It ? (1:YES/2:NO)");
            int awnser1 = input.nextInt();
            while (true){
                if (awnser1==1){
                    Data.municipalities.get(0).sellOwnProperty(chProperty, this);
                    break;
                } else if (awnser1 == 2) {
                    Menu.userMenu(this.userInfo);
                    break;
                }else {
                    System.out.println("Incorrect Input");
                }
            }

            Menu.userMenu(this.userInfo);
        }

        else {
            System.out.println("You can Buy This Property");
            System.out.printf("\tPrice : %f", chProperty.getPrice());
            System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
            int awnser2 = input.nextInt();
            while (true){
                if (awnser2==1){
                    Data.municipalities.get(0).buyProperty(chProperty, this);
                    break;
                } else if (awnser2 == 2) {
                    Menu.userMenu(this.userInfo);
                    break;
                }else {
                    System.out.println("Incorrect Input");
                }
            }
        }
    }

    @Override
    public void positionProcessing(Industry industry) {

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
