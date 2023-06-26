package org.example.models;

import org.example.Data;
import org.example.defualtSystem.Life;
import org.example.interfaces.CharacterInterface;

import java.util.ArrayList;

public class Character implements CharacterInterface {
    private User userInfo;
    private BankAccount account;
    private Life life;
    private Job job;
    private ArrayList<Property> properties;
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
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    @Override
    public void positionProcessing() {

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
}
