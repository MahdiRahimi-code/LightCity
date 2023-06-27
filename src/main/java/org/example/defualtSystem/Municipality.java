package org.example.defualtSystem;

import org.example.Data;
import org.example.Database;
import org.example.Menu;
import org.example.interfaces.MunicipalityInterface;
import org.example.models.Character;
import org.example.models.Property;

import java.util.ArrayList;

public class Municipality implements MunicipalityInterface {
    private ArrayList<Property> properties;

    public  Municipality(){
        generateProperties();
    }

    private void generateProperties() {
        for (int i=0;i<7;i++) {
            for (int j = 0; j < 4; j++) {
                Property property = new Property(new float[]{7, 14}, new float[]{7*i,16*j},null);
                properties.add(property);
                Data.properties.add(property);
            }
        }
    }

    @Override
    public void buyProperty(Property property, Character buyer, Character seller) {
        if (seller == null) {
            if (buyer.getAccount().withdraw(buyer, (120/100)*property.getPrice())) {
                System.out.println("\tYou Bought This property Successfully");
                property.setOwner(buyer);
                property.setPrice((120/100)*property.getPrice());
                buyer.properties.add(property);
                Database.WriteData();
                Menu.userMenu(buyer.getUserInfo());
            } else {
                System.out.println("Not Enough Money");
                Menu.userMenu(buyer.getUserInfo());
            }
        }
        else{
            seller = property.getOwner();
            if (buyer.getAccount().withdraw(buyer, (120/100)*property.getPrice())){
                System.out.println("\tYou Bought This property Successfully From : "+ seller.getUserInfo().getUsername());
                property.setOwner(buyer);
                property.setPrice((120/100)*property.getPrice());
                seller.properties.remove(property);
                buyer.properties.add(property);
                Database.WriteData();
                Menu.userMenu(buyer.getUserInfo());
            }
            else{
                System.out.println("Not Enough Money");
                Menu.userMenu(buyer.getUserInfo());
            }
        }
    }

    @Override
    public void sellProperty(Property property, Character character) {
        System.out.println("\tYou Sold This property Successfully");
        character.getAccount().deposit(character, property.getPrice());
        property.setOwner(null);
        character.properties.remove(property);
        Database.WriteData();
        Menu.userMenu(character.getUserInfo());
    }

    @Override
    public void showProperties() {
        for (Property p : properties){
            System.out.println("ID : " + p.getPropertyID());
            System.out.println("owner : "+ p.getOwner());
            System.out.println("scales : " + p.getScales());
            System.out.println("coordinates : "+ p.getCoordinate());
        }
    }
}
