package org.example.interfaces;

import org.example.models.Character;
import org.example.models.Property;

public interface MunicipalityInterface {

//    Buy and sell property
    void buyProperty(Property property, Character buyer, Character seller);
    void sellProperty(Property property, Character character);
    void showProperties();
}
