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
        // Get Bank Property from municipality
        bankSystem = new Bank(new Property(new float[] { 12, 32 }, new float[] { 42, 32 }, root), root);
        FastFoodShop fastFoodShop = new FastFoodShop("foodShop",
                new Property(new float[] { 12, 32 }, new float[] { 42, 0 }, root), root, null);
        stockMarket = new StockMarket();
        Data.industries.add(bankSystem);
        Data.industries.add(fastFoodShop);
        Data.banks.add(bankSystem);
        Data.fastFoodShops.add(fastFoodShop);
        Data.municipalities.add(municipality);
        Data.stockMarkets.add(stockMarket);
        stockMarket.startMarketSimulation();
    }

    public City(ArrayList<Character> characters, Bank bank, Municipality municipality, StockMarket stockMarket, Character root){
        this.stockMarket=stockMarket;
        this.bankSystem=bank;
        this.root=root;
        this.municipality=municipality;
        this.characters=characters;
    }

    @Override
    public void joinCharacter(User userinfo) {
        BankAccount newAccount = bankSystem.newAccount(userinfo.getUsername(), userinfo.getPassword());
        ArrayList<Property> properties = new ArrayList<>();
        ArrayList<Food> foods= new ArrayList<>();
        Character character = new Character(userinfo, newAccount, new Life(), null, properties, null, foods);
        character.gotToLocation(Menu.searchByID(1));
        character.getAccount().deposit(character, 10);
        Data.characters.add(character);
        stockMarket.registerUser(userinfo.getUsername(), 2.0f);
        characters.add(character);
        beginGame(character);
    }

    @Override
    public void getCityDetail() {
        String players = Arrays.toString(characters.toArray());
    }

    /**
     * Begin Game function generate a new thread for each character ,<b > DO NOT
     * CHANGE THIS FUNCTION STRUCTURE</b> ,
     *
     */
    private void beginGame(Character character) {
        Thread thread = new Thread(() -> {
            try {
                Menu.userMenu(character.getUserInfo());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}