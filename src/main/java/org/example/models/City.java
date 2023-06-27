package org.example.models;

import org.example.Data;
import org.example.Database;
import org.example.Menu;
import org.example.defualtSystem.*;
import org.example.interfaces.CityInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class City implements CityInterface {
    private final ArrayList<Character> characters;
    private final Bank bankSystem;
    private final Municipality municipality;

    private final StockMarket stockMarket;
    private Character root;

    public City() {
        characters = new ArrayList<>();
        municipality = new Municipality();
//        Get Bank Property from municipality
        bankSystem = new Bank(new Property(new float[]{12, 32}, new float[]{42, 32}, root), root);
        FastFoodShop fastFoodShop = new FastFoodShop("foodShop", new Property(new float[]{12, 32}, new float[]{42, 0}, root), root);
        stockMarket = new StockMarket();
        Data.banks.add(bankSystem);
        Data.fastFoodShops.add(fastFoodShop);
        Data.municipalities.add(municipality);
        Data.stockMarkets.add(stockMarket);
        Database.WriteData();
        stockMarket.startMarketSimulation();
    }

    @Override
    public void joinCharacter(User userinfo) {
        BankAccount newAccount = bankSystem.newAccount(userinfo.getUsername(), userinfo.getPassword());
        Character character = new Character(userinfo, newAccount, new Life(), null, null, null);
        character.gotToLocation(Menu.searchByID(1));
        Data.characters.add(character);
        characters.add(character);
        Database.WriteData();
        beginGame(character);
    }

    @Override
    public void getCityDetail() {
        String players = Arrays.toString(characters.toArray());
    }


    /**
     * Begin Game function generate a new thread for each character ,<b > DO NOT CHANGE THIS FUNCTION STRUCTURE</b> ,
     *
     * */
    private void beginGame(Character character) {
        Thread thread = new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Show Menu");
                    switch (scanner.next()) {
                        case "2"-> System.out.println("Do Something");
                        case "3"-> System.out.println("Do Something");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
