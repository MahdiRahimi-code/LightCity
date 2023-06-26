package org.example;

import org.example.models.Character;
import org.example.models.Industry;
import org.example.models.User;
import org.example.models.Property;

import java.util.Scanner;

public class Menu {
    private static Game game = new Game();
    private static Scanner scanner = new Scanner(System.in);
    public static void showMenu(){
        mainMenu();
       String next = scanner.next();
        if (next.equals("1")) {
           game.continueGame(loginMenu());
       }else if(next.equals("2")){
           game.startGame(loginMenu());
       }else if (next.equals("3")){
           joinServer();
       }else if (next.equals("4"))
           System.exit(0);

        userMenu(loginMenu());
    }

    public static void userMenu(User user){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 : Go to");
        System.out.println("2 : Process Location");
        System.out.println("3 : Dashboard");
        System.out.println("4 : Life");
        System.out.println("5 : exit");
        int awnser = scanner.nextInt();

        if (awnser == 1){
            goTo(user);
        } else if (awnser == 2) {

        } else if (awnser == 3) {

        } else if (awnser == 4) {

        } else if (awnser == 5) {
            showMenu();
        }
    }



    public static void mainMenu(){
        System.out.println("1 : continue game");
        System.out.println("2 : start new game");
        System.out.println("3 : join server");
        System.out.println("4 : exit");
    }

    public static User loginMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username : ");
        String username = scanner.next();
        System.out.println("Enter your password : ");
        String password = scanner.next();

        User user = new User(username, password);
        return user;
    }

    private static void joinServer(){
        System.out.print("Enter Server Ip Address :");
        String ip = scanner.next();
        System.out.print("Enter Server Port :");
        int port = scanner.nextInt();
        game.joinServer(ip,port);
    }

    private static void goTo(User user){
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        Character character = Character.searchCharacterByUser(user);
        if (character == null){
            System.out.println("Character is not found");
            userMenu(user);
        }

        System.out.println("1 : Go to Property By ID");
        System.out.println("2 : Go To Property By Location");
        System.out.println("3 : Go To Property By Industry Tilte");
        System.out.println("4 : Exit");
        int awnser = scanner.nextInt();

        if (awnser==1){
            while (true) {
                System.out.println("Enter ID : (0 to back)");
                int id = scanner.nextInt();

                if (id == 0) {
                    goTo(user);
                    break;
                }

                if (searchByID(id) == null) {
                    System.out.println("No Property Found");
                } else {
                    character.gotToLocation(searchByID(id));
                    character.positionProcessing(searchByID(id));
                    break;
                }
            }
        }

        else if (awnser == 2) {
            while (true) {
                System.out.println("Enter Location Coordinates : (-1 to back)");
                System.out.println("Coordinate X : ");
                float coordinateX = input.nextFloat();
                if (coordinateX==-1){
                    goTo(user);
                    break;
                }
                System.out.println("Coordinate Y : ");
                float coordinateY = input.nextFloat();

                if (searchByLocation(coordinateX, coordinateY) == null){
                    System.out.println("No Property Found");
                }
                else{
                    character.gotToLocation(searchByLocation(coordinateX, coordinateY));
                    character.positionProcessing(searchByLocation(coordinateX, coordinateY));
                    break;
                }
            }
        }

        else if (awnser == 3) {
            while (true) {
                System.out.println("Enter Industry Title : (b to back)");
                String title = input2.next();

                if (title.compareTo("b") == 0){
                    goTo(user);
                    break;
                }

                if (searchByTitle(title) == null){
                    System.out.println("No Industry Found");
                }
                else{
                character.gotToLocation(searchByTitle(title));

                }
            }
        }

        else{
            userMenu(user);
        }


    }

    private static Industry searchByTitle(String title){
        Industry k = null;
        for (Industry x : Data.industries) {
            if(x.getTitle().equals(title)){
                k = x;
                break;
            }
        }
        return k;
    }

    private static Property searchByID(int id){
        Property k = null ;
        for (Property x : Data.properties) {
            if (x.getPropertyID() == id){
                k = x;
                break;
            }
        }
        return k;
    }

    private static Property searchByLocation(float x , float y){
        Property k = null;
        for (Property w : Data.properties) {
            if (x<=w.getCoordinate()[0] && w.getCoordinate()[0]+w.getScales()[0]>=x && y<=w.getCoordinate()[1] && w.getScales()[1]+w.getCoordinate()[1]>=y){
                k = w;
                break;
            }
        }
        if (k == null){
            for (Industry i : Data.industries){
                if (x<=i.getCoordinate()[0] && i.getCoordinate()[0]+i.getScales()[0]>=x && y<=i.getCoordinate()[1] && i.getScales()[1]+i.getCoordinate()[1]>=y){
                    k = i;
                    break;
                }
            }
        }
        return k;

    }

    public static void main(String[] args) {
            showMenu();
    }
}