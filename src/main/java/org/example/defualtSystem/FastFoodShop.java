package org.example.defualtSystem;

import org.example.Database;
import org.example.Menu;
import org.example.models.Character;
import org.example.models.Food;
import org.example.models.Industry;
import org.example.models.Property;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FastFoodShop extends Industry {

    /**
     * Industry type example (Business)
     * */
    private static final float INCOME = 0.3f;
    private static final float EMPLOYEE_INCOME = 0.02f;
    private ArrayList<Food> foods;

    public float getEmployeeIncome(){
        return EMPLOYEE_INCOME;
    }

    public FastFoodShop(String title, Property property, Character character) {
        super(title, property, character,EMPLOYEE_INCOME);
        foods = new ArrayList<>();
        generateFood();
    }

    public void buyFood(Character character){
        Scanner input = new Scanner(System.in);
        for (int i=0;i<5;i++){
            System.out.printf("%d : %s  -  Price : 0.01$", i+1, foods.get(i).getTitle());
        }
        while (true) {
            System.out.println("Your choice : (0 to back)\n");
            int answer = input.nextInt();

            while (true){
                int x=-1;
                if (answer==0){
                    Menu.userMenu(character.getUserInfo());
                    break;
                } else if (answer > 0 && answer < 6) {
                    //cost the money
                    character.foods.add(foods.get(answer-1));
                    foods.remove(answer-1);
                    Database.WriteData();
                    System.out.println("You bought Food");
                    Menu.userMenu(character.getUserInfo());
                }
                else{
                    System.out.println("Enter correct value");
                }
            }
        }

    }

    public void generateFood(){
        String[] foodTitles = {"Burger", "doubleBurger", "Pizza", "hamMeat", "Sausages"};
        for (int i=0;i<10;i++){
            foods.add(new Food(foodTitles[i], 0.4f, 0.4f));
        }
    }


}
