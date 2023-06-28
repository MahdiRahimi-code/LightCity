package org.example.models;

import java.util.Arrays;

public class Property {
    private float[] scales;
    private float[] coordinate;
    private Character owner;
    private static int ID = 0;
    private int PropertyID;
    private float price;

    public Property(float[] scales, float[] coordinate, Character owner) {
        this.scales = scales;
        this.coordinate = coordinate;
        this.owner = owner;
        price = (float) ((scales[0] * scales[1]) * 0.075);
        ID++;
        PropertyID = ID;
    }

    public float[] getScales() {
        return scales;
    }

    public void setScales(float[] scales) {
        this.scales = scales;
    }

    public Character getOwner() {
        return owner;
    }

    public void setOwner(Character owner) {
        this.owner = owner;
    }

    public float[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(float[] coordinate) {
        this.coordinate = coordinate;
    }

    public int getPropertyID() {
        return PropertyID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
