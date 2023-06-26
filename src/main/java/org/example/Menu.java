package org.example;

import org.example.models.Character;
import org.example.models.Food;
import org.example.models.Industry;
import org.example.models.User;
import org.example.models.Property;
import org.example.models.Job;
import org.example.defualtSystem.Life;

import java.util.Scanner;

public class Menu {
    private static Game game = new Game();
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        mainMenu();
        String next = scanner.next();
        if (next.equals("1")) {
            game.continueGame(loginMenu());
        } else if (next.equals("2")) {
            game.startGame(loginMenu());
        } else if (next.equals("3")) {
            joinServer();
        } else if (next.equals("4"))
            System.exit(0);

        userMenu(loginMenu());
    }

    public static void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 : Go to");
        System.out.println("2 : Process Location");
        System.out.println("3 : Dashboard");
        System.out.println("4 : Life");
        System.out.println("5 : Exit");
        int awnser = scanner.nextInt();

        if (awnser == 1) {
            goTo(user);
        } else if (awnser == 2) {

        } else if (awnser == 3) {
            Dashboard(user);
        } else if (awnser == 4) {
            Life(user);
        } else if (awnser == 5) {
            Exit(user);
        }
    }

    public static void mainMenu() {
        System.out.println("1 : continue game");
        System.out.println("2 : start new game");
        System.out.println("3 : join server");
        System.out.println("4 : exit");
    }

    public static User loginMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username : ");
        String username = scanner.next();
        System.out.println("Enter your password : ");
        String password = scanner.next();

        User user = new User(username, password);
        return user;
    }

    private static void joinServer() {
        System.out.print("Enter Server Ip Address :");
        String ip = scanner.next();
        System.out.print("Enter Server Port :");
        int port = scanner.nextInt();
        game.joinServer(ip, port);
    }

    private static void goTo(User user) {
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        Character character = Character.searchCharacterByUser(user);
        if (character == null) {
            System.out.println("Character is not found");
            userMenu(user);
        }

        ShowGoTo();

        int awnser = scanner.nextInt();

        if (awnser == 1) {
            System.out.println("Enter ID : ");
            int id = scanner.nextInt();
            character.gotToLocation(searchByID(id));
        } else if (awnser == 2) {
            System.out.println("Enter Location Coordinates : ");
            float coordinateX = input.nextFloat();
            float coordinateY = input.nextFloat();
            character.gotToLocation(searchByLocation(coordinateX, coordinateY));
        } else if (awnser == 3) {
            System.out.println("Enter Industry Title");
            String title = input2.next();
            character.gotToLocation(searchByTitle(title));
        } else {
            userMenu(user);
        }

    }

    //
    private static void ShowGoTo() {
        System.out.println("1 : Go to Property By ID");
        System.out.println("2 : Go To Property By Location");
        System.out.println("3 : Go To Property By Industry Tilte");
        System.out.println("4 : Exit");
    }

    private static Industry searchByTitle(String title) {
        Industry k = null;
        for (Industry x : Data.industries) {
            if (x.getTitle().equals(title)) {
                k = x;
                break;
            }
        }
        return k;
    }

    private static Property searchByID(int id) {
        Property k = null;
        for (Property x : Data.properties) {
            if (x.getPropertyID() == id) {
                k = x;
                break;
            }
        }
        return k;
    }

    private static Property searchByLocation(float x, float y) {
        Property k = null;
        for (Property w : Data.properties) {
            if (x <= w.getCoordinate()[0] && w.getCoordinate()[0] + w.getScales()[0] >= x && y <= w.getCoordinate()[1]
                    && w.getScales()[1] + w.getCoordinate()[1] >= y) {
                k = w;
                break;
            }
        }
        return k;
    }

    private static void Dashboard(User user) {
        Character character = Character.searchCharacterByUser(user);
        if (character == null) {
            System.out.println("Character is not found");
            userMenu(user);
        }
        MenuDashboard();
        Scanner scanner = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        String select = scanner.nextLine();
        if (select.equals("a")) {
            System.out.println(character.getJob());
        } else if (select.equals("b")) {
            ShowProperties();
            int select1 = in.nextInt();
            if (select1 == 1) {
                System.out.println(character.getProperties());
            } else if (select1 == 2) {

            } else if (select1 == 3) {

            } else if (select1 == 4) {

            }
        } else if (select.equals("c")) {
            Job k = null;
            for (Job i : Data.jobs) {
                if (i.equals(character.getJob())) {
                    i = k;
                    break;
                }
            }
            ShowEconomy();
            int select2 = scanner.nextInt();
            if (select2 == 1) {
                System.out.println(k.getIncome());
            } else if (select2 == 2) {
                System.out.println("Employee of" + k.getTitle());
            } else if (select2 == 3) {
            }
        }

    }

    private static void ShowProperties() {
        System.out.println("I : Show properties");
        System.out.println("II : Sell");
        System.out.println("III : Management");
        System.out.println("IV : Found Industry");
    }

    private static void MenuDashboard() {
        System.out.println("A : My job");
        System.out.println("B : Properties");
        System.out.println("C : Economy");
        System.out.println("D : Life");
        System.out.println("E : Exit");
    }

    private static void ShowEconomy() {
        System.out.println("I : Show Incomes");
        System.out.println("II : Show job detail");
        System.out.println("III : How can grow?");
    }

    private static void Life(User user) {
        Character character = Character.searchCharacterByUser(user);
        if (character == null) {
            System.out.println("Character is not found");
            userMenu(user);
        }
        Life k = character.getLife();
        ShowLife();
        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        if (select == 1) {
            System.out.println(k.getSleep() + "%" + k.getWater() + "%" + k.getFood() + "%");
        } else if (select == 2) {
            k.startConsuming();
        } else if (select == 3) {

        }

    }

    private static void ShowLife() {
        System.out.println("I : Life Detail");
        System.out.println("II : Sleep Function");
        System.out.println("III : Eat Function");
    }

    private static void Exit(User user) {
        System.out.println("Are you sure?");
        System.out.println("1 : YES \n 2 : No");
        int sure = scanner.nextInt();
        if (sure == 1) {
            showMenu();
        } else if (sure == 2) {
            userMenu(user);
        }
    }

    public static void main(String[] args) {
        showMenu();
    }
}