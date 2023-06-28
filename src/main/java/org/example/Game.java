package org.example;

import org.example.interfaces.GameInterface;
import org.example.models.Character;
import org.example.models.City;
import org.example.models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Game implements GameInterface {

    // Check Data from Database or file to see There is city or not
    private City city;

    @Override
    public void continueGame(User user) {
        Database.ReadData();
        boolean isFound = false;
        for (User i : Data.users) {
            if (i.getUsername().compareTo(user.getUsername()) == 0
                    && i.getPassword().compareTo(user.getPassword()) == 0) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            System.out.println("User found");
            if (Data.cities.isEmpty()) {
                System.out.println("No city is created...Generating new city");
                generateNewCity();
                city.joinCharacter(user);
                Data.cities.add(city);
                Database.dataWrite();
            }
        } else {
            System.out.println("User not found");
            Menu.showMenu();
        }
    }

    /**
     * Create new city and Generate new Character
     * 
     * @param user : User information contain username, password
     */
    @Override
    public void startGame(User user) {
        Data.users.add(user);
        generateNewCity();
        Data.cities.add(city);
        city.joinCharacter(user);
        Database.WriteData();
    }

    /**
     * @param ip   Server ip address / example : 127.0.0.1
     * @param port Server open port for specific ip address
     */
    @Override
    public void joinServer(String ip, int port) {

    }

    @Override
    public void generateNewCity() {
        city = new City();
    }

}