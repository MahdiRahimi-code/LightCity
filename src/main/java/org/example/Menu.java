package org.example;

import org.example.models.User;

import java.util.Scanner;

public class Menu {
    private static Game game = new Game();
    private static Scanner scanner = new Scanner(System.in);
    public static void showMenu(){
       System.out.println("1 : continue game");
       System.out.println("2 : start new game");
       System.out.println("3 : join server");
       System.out.println("4 : exit");
       String next = scanner.next();

        if (next.equals("1")) {
           game.continueGame(loginMenu());
       }else if(next.equals("2")){
           game.startGame(loginMenu());
       }else if (next.equals("3")){
           joinServer();
       }else if (next.equals("4"))
           System.exit(0);
    }

    public static void mainMenu(){
        //1 : Go to
        //2 : process location
        //3 : Dashboard
        //4 : life
        //5 : Exit
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

    public static void main(String[] args) {
            showMenu();
    }
}
