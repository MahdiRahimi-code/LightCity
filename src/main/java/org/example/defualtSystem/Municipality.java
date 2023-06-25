package org.example.defualtSystem;

import org.example.Data;
import org.example.interfaces.MunicipalityInterface;
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
    public Property buyProperty() {
        return null;
    }

    @Override
    public void sellProperty(Property property) {

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
