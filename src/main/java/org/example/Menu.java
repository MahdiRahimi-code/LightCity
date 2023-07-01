package org.example;

import org.example.models.Character;
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
    }

    public static void userMenu(User user) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 : Go to");
        System.out.println("2 : Process Location");
        System.out.println("3 : Dashboard");
        System.out.println("4 : Life");
        System.out.println("5 : Exit");
        int awnser = scanner.nextInt();

        while (true) {
            if (awnser == 1) {
                goTo(user);
                break;
            } else if (awnser == 2) {
                Character character = Character.searchCharacterByUser(user);
                character.positionProcessing();
                break;
            } else if (awnser == 3) {
                Dashboard(user);
                break;
            } else if (awnser == 4) {
                Life(user);
                break;
            } else if (awnser == 5) {
                Exit(user);
                break;
            } else {
                System.out.println("Enter correct Value");
            }
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
                    character.positionProcessing();
                    break;
                }
            }
        }

        else if (awnser == 2) {
            while (true) {
                System.out.println("Enter Location Coordinates : (-1 to back)");
                System.out.println("Coordinate X : ");
                float coordinateX = input.nextFloat();
                if (coordinateX == -1) {
                    goTo(user);
                    break;
                }
                System.out.println("Coordinate Y : ");
                float coordinateY = input.nextFloat();
                if (coordinateX == -1) {
                    goTo(user);
                    break;
                }

                if (searchByLocation(coordinateX, coordinateY) == null) {
                    System.out.println("No Property Found");
                } else {
                    character.gotToLocation(searchByLocation(coordinateX, coordinateY));
                    character.positionProcessing();
                    break;
                }
            }
        }

        else if (awnser == 3) {
            while (true) {
                System.out.println("\t\tIndustries : Bank / foodShop");
                System.out.println("\tplease care How They Are Written");
                System.out.println("Enter Industry Title : (b to back)");
                String title = input2.next();

                if (title.compareTo("b") == 0) {
                    goTo(user);
                    break;
                }

                if (searchByTitle(title) == null) {
                    System.out.println("No Industry Found");
                } else {
                    character.gotToLocation(searchByTitle(title));
                    character.positionProcessing();
                    break;
                }
            }
        }

        else {
            userMenu(user);
        }

    }

    private static void ShowGoTo() {
        System.out.println("1 : Go to Property By ID");
        System.out.println("2 : Go To Property By Location");
        System.out.println("3 : Go To Property By Industry Tilte");
        System.out.println("4 : Exit");
    }

    public static Industry searchByTitle(String title) {
        Industry k = null;
        for (Industry x : Data.industries) {
            if (x.getTitle().equals(title)) {
                k = x;
                break;
            }
        }
        return k;
    }

    public static Property searchByID(int id) {
        Property k = null;
        for (Property x : Data.properties) {
            if (x.getPropertyID() == id) {
                k = x;
                break;
            }
        }
        return k;
    }

    public static Property searchByLocation(float x, float y) {
        Property k = null;
        for (Property w : Data.properties) {
            if (x >= w.getCoordinate()[0] && w.getCoordinate()[0] + w.getScales()[0] >= x && y >= w.getCoordinate()[1]
                    && w.getScales()[1] + w.getCoordinate()[1] >= y) {
                k = w;
                break;
            }
        }
        if (k == null) {
            for (Industry i : Data.industries) {
                if (x >= i.getCoordinate()[0] && i.getCoordinate()[0] + i.getScales()[0] >= x && y >= i.getCoordinate()[1]
                        && i.getScales()[1] + i.getCoordinate()[1] >= y) {
                    k = i;
                    break;
                }
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
            if (character.getJob() == null){
                System.out.println("You dont have any job");
                Dashboard(user);
            }
            else {
                System.out.println("\t\tJob : " + character.getJob().getTitle());
                System.out.println("\tIncome : " + character.getJob().getIncome());
                Dashboard(user);
            }
        } else if (select.equals("b")) {
            ShowProperties();
            int select1 = in.nextInt();
            if (select1 == 1) {
                if (character.properties.size() == 0) {
                    System.out.println("You dont have any property");
                    Dashboard(user);
                } else {
                    System.out.println("Your Properties : ");
                    for (Property p : character.properties) {
                        System.out.printf("ID : %d  /  coordinates : {x:%f , y:%f}", p.getPropertyID(), p.getCoordinate()[0], p.getCoordinate()[1]);
                    }
                    Dashboard(user);
                }
            }else if (select1 == 2) {
                goTo(user);
            } else if (select1 == 3) {
                userMenu(user);
            }
        } else if (select.equals("c")) {
            System.out.println("Your money amount : " + character.getAccount().getMoney() + "$");
            Job k = null;
            for (Job i : Data.jobs) {
                if (i.equals(character.getJob())) {
                    k = i;
                    break;
                }
            }
            if (k != null) {
                ShowEconomy();
                int select2 = scanner.nextInt();
                if (select2 == 1) {
                    System.out.println(k.getIncome());
                    Dashboard(user);
                } else if (select2 == 2) {
                    System.out.println("Job : " + k.getTitle());
                    Dashboard(user);
                }
            } else {
                System.out.println("you don't have any job bro");
                System.out.println("Do you want to find a job?");
                System.out.println("1:YES \n 2:NO");
                int select3 = scanner.nextInt();
                if (select3 == 1) {
                    for (Industry x : Data.industries) {
                        System.out.println(x.getTitle());
                    }
                    while (true) {
                        Scanner input = new Scanner(System.in);
                        System.out.println("Enter the title of the Industry you want to work for : (b : back)");
                        String work = input.next();
                        if (searchByTitle(work) != null) {
                            character.gotToLocation(searchByTitle(work));
                            character.positionProcessing();
                            Dashboard(user);
                            break;
                        } else if (work.equals("b")) {
                            Dashboard(user);
                            break;
                        }
                        else {
                            System.out.println("Wrong Job Name");
                        }
                    }
                } else if (select3 == 2) {
                    System.out.println("idiot");
                    userMenu(user);
                }

            }

        } else if (select.equals("d")) {
            System.out.println("Food Percentage : " + character.getLife().getFood());
            System.out.println("Liquid Percentage : " + character.getLife().getWater());
            System.out.println("Sleep Percentage : " + character.getLife().getSleep());
            Dashboard(user);
        } else if (select.equals("e")){
            userMenu(user);
        }

    }

    private static void ShowProperties() {
        System.out.println("I : Show properties");
        System.out.println("II : Found Industry");
        System.out.println("III : Exit");
    }

    private static void MenuDashboard() {
        System.out.println("a : My job");
        System.out.println("b : Properties");
        System.out.println("c : Economy");
        System.out.println("d : Life");
        System.out.println("e : Exit");
    }

    private static void ShowEconomy() {
        System.out.println("I : Show Incomes");
        System.out.println("II : Show job detail");
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
            System.out.println("sleep : " + k.getSleep() + "%" + "\tWater : " + k.getWater() + "%" + "\tFood : " + k.getFood() + "%");
            userMenu(user);

        } else if (select == 2) {
            if (character.foods.size() > 0) {
                k.foodConsumption(character.foods.get(0));
                character.foods.remove(0);
                System.out.println("You Ate food");
                userMenu(user);
            } else {
                System.out.println("You don't have enough foods");
                System.out.println("Would you like to go buy some food?");
                System.out.println("1.YES \n 2.NO");
                int ar = scanner.nextInt();
                if (ar == 1) {
                    character.gotToLocation(Menu.searchByTitle("foodShop"));
                    character.positionProcessing();
                } else if (ar == 2) {
                    System.out.println("Are you sure?");
                    System.out.println("1.YES \n 2.NO");
                    int ar1 = scanner.nextInt();
                    if (ar1 == 1) {
                        userMenu(user);
                    } else {
                        Life(user);
                    }
                }
            }

        }

    }

    private static void ShowLife() {
        System.out.println("I : Life Detail");
        System.out.println("II : Eat Function");
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