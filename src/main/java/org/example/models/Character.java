package org.example.models;

import org.example.Data;
import org.example.Database;
import org.example.Menu;
import org.example.defualtSystem.Bank;
import org.example.defualtSystem.FastFoodShop;
import org.example.defualtSystem.Life;
import org.example.interfaces.CharacterInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class Character implements CharacterInterface {
    private User userInfo;
    private BankAccount account;
    private Life life;
    private Job job;
    public ArrayList<Property> properties = new ArrayList<>();
    public ArrayList<Food> foods = new ArrayList<>();
    private static int ID = 0;
    private int characterID;
    private Property inPosition;

    public Character(User userInfo, BankAccount account, Life life, Job job, ArrayList<Property> properties,
            Property inPosition) {
        this.userInfo = userInfo;
        this.account = account;
        this.life = life;
        this.job = job;
        this.properties = properties;
        this.inPosition = inPosition;
        ID++;
        characterID = ID;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public Life getLife() {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void gotToLocation(Property destination) {
        if (destination == null)
            return;
        inPosition = destination;
    }

    @Override
    public void positionProcessing() {
        Property chPosition = this.inPosition;
        Scanner input = new Scanner((System.in));

        if (chPosition.getCoordinate()[0] >= 42 && chPosition.getCoordinate()[0] <= 54) { // it is in industry

            if (chPosition.getCoordinate()[1] >= 32 && chPosition.getCoordinate()[1] <= 64) { // it is in bank
                Scanner scanner = new Scanner(System.in);
                Bank bank = Data.banks.get(0);
                System.out.println("You Are In : " + bank.getTitle());

                System.out.println("1 : Sign in AS Employee");
                System.out.println("2 : See Your Bank Account");
                System.out.println("3 : See Stock Market Account Detail");
                System.out.println("4 : Deposit Stock Market Money");
                System.out.println("5 : Withdraw Stock Market Money");
                System.out.println("6 : Back");

                while (true) {
                    System.out.println("Your choice : ");
                    int result = scanner.nextInt();
                    if (result == 1) {
                        System.out.println("You want to Work As an Employee in : " + bank.getTitle());
                        System.out.println("Here Is The Income : " + bank.getBaseEmpSalary());
                        System.out.println("Are You Sure ? (1:Yes/2:No/3:Back)");
                        int select = input.nextInt();

                        while (true) {
                            if (select == 1) {
                                bank.registerAsEmp(this);
                                System.out.println("You Have Successfully Registered As Employee");
                                Menu.userMenu(this.userInfo);
                                break;

                            } else if (select == 2) {
                                this.positionProcessing();
                                break;

                            } else if (select == 3) {
                                Menu.userMenu(this.getUserInfo());
                                break;

                            } else {
                                System.out.println("Enter Correct Value");
                            }
                        }
                        break;
                    }
                    else if (result == 2) {
                        System.out.println(this.getAccount().toString());
                        Menu.userMenu(this.userInfo);
                        break;
                    }
                    else if (result == 3) {
                        if (Data.stockMarkets.get(0).getDetail(this.userInfo.getUsername()) == 0){
                            System.out.println("No Account Found");
                            Menu.userMenu(this.userInfo);
                            break;
                        }
                        else {
                            System.out.println("Your Amount : " + Data.stockMarkets.get(0).getDetail(this.userInfo.getUsername()));
                            break;
                        }

                    }
                    else if (result == 4) {
                        Scanner floatInput = new Scanner(System.in);
                        System.out.println("Amount : ");
                        float money = floatInput.nextFloat();

                        if (this.account.withdraw(this, money)){
                            Data.stockMarkets.get(0).depositCapital(this.userInfo.getUsername(), money);
                            System.out.println("Money Withdraw For Stock Market Successful");
                            Menu.userMenu(this.userInfo);
                            break;
                        }
                        else{
                            System.out.println("Not Enough Money");
                            Menu.userMenu(this.userInfo);
                            break;
                        }
                    }
                    else if (result == 5) {
                        Scanner floatInput = new Scanner(System.in);
                        System.out.println("Amount : ");
                        float money = floatInput.nextFloat();

                        if (Data.stockMarkets.get(0).withdrawCapital(this.userInfo.getUsername()) == 0.0){
                            System.out.println("User Not Found");
                            Menu.userMenu(this.userInfo);
                            break;
                        }
                        else{
                            this.account.deposit(this, money);
                            System.out.println("Money Deposit to Bank Account Successful");
                            Menu.userMenu(this.userInfo);
                            break;
                        }

                    } else if (result == 6) {
                        this.positionProcessing();
                        break;
                    } else {
                        System.out.println("Enter Correct Value");
                    }
                }
            }

            else {                           // it is in shop
                FastFoodShop fastFoodShop = Data.fastFoodShops.get(0);
                System.out.println("You Are IN : " + fastFoodShop.getTitle());
                System.out.println("Options : (0 to back)");
                System.out.println("1 : Sign as Employee");
                System.out.println("2 : Buy Food");
                int answer = input.nextInt();
                while (true) {
                    if (answer == 1) {
                        System.out.println("You want to Work As an Employee in : " + fastFoodShop.getTitle());
                        System.out.println("Here Is The Income : " + fastFoodShop.getEmployeeIncome());
                        System.out.println("Are You Sure ? (1:Yes/2:No/3:Back)");
                        int select = input.nextInt();

                        while (true) {
                            if (select == 1) {
                                fastFoodShop.getEmployee().add(new Employee(this.userInfo.getUsername(), fastFoodShop,
                                        fastFoodShop.getEmployeeIncome(), this.account));
                                System.out.println("You Have Successfully Registered As Employee");
                                this.job = new Job("ShopEmployee", fastFoodShop.getEmployeeIncome(), fastFoodShop.getIndustryID());
                                Data.jobs.add(job);
                                Menu.userMenu(this.userInfo);
                                break;

                            } else if (select == 2) {
                                this.positionProcessing();
                                break;

                            } else if (select == 3) {
                                Menu.userMenu(this.getUserInfo());
                                break;

                            } else {
                                System.out.println("Enter Correct Value");
                            }
                        }
                        break;

                    } else if (answer == 2) {
                        Data.fastFoodShops.get(0).buyFood(this);
                        this.positionProcessing();
                        break;

                    } else {
                        System.out.println("Enter correct value");
                    }
                }
            }
        }

        else { // it is in property
            System.out.println("You are In : ");
            System.out.println("Property ID : " + chPosition.getPropertyID());

            if (chPosition.getOwner() != null) {
                  if (chPosition.getOwner().equals(this)) {
                    System.out.println("This is your Property");
                    System.out.printf("\tPrice : %f\n", chPosition.getPrice());
                    System.out.println("Do You Want To Sell It ? (1:YES/2:NO)");
                    int awnser1 = input.nextInt();
                    while (true) {
                        if (awnser1 == 1) {
                            Data.municipalities.get(0).sellProperty(chPosition, this);
                            break;
                        } else if (awnser1 == 2) {
                            Menu.userMenu(this.userInfo);
                            break;
                        } else {
                            System.out.println("Incorrect Input");
                        }
                    }

                    Menu.userMenu(this.userInfo);
                }
                  else {
                      System.out.println("This Property Belongs To : " + chPosition.getOwner().getUserInfo().getUsername());
                      System.out.printf("\tPrice : %f\n", chPosition.getPrice());
                      System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
                      int awnsers = input.nextInt();
                      while (true) {
                          if (awnsers == 1) {
                              Data.municipalities.get(0).buyProperty(chPosition, this, chPosition.getOwner());
                              break;
                          } else if (awnsers == 2) {
                              this.positionProcessing();
                              break;
                          } else {
                              System.out.println("Incorrect Input");
                          }
                      }

                      Menu.userMenu(this.userInfo);
                  }
            } else if (chPosition.getOwner() == null){
                System.out.println("You can Buy This Property");
                System.out.printf("\tPrice : %f\n", chPosition.getPrice());
                System.out.println("Do You Want To Buy It ? (1:YES/2:NO)");
                int awnser2 = input.nextInt();
                while (true) {
                    if (awnser2 == 1) {
                        Data.municipalities.get(0).buyProperty(chPosition, this, null);
                        break;
                    } else if (awnser2 == 2) {
                        Menu.userMenu(this.userInfo);
                        break;
                    } else {
                        System.out.println("Incorrect Input");
                    }
                }
            }
        }
    }

    public static Character searchCharacterByUser(User user) {
        Character character = null;
        for (Character x : Data.characters) {
            if (x.getUserInfo().equals(user)) {
                character = x; // return character of user
                break;
            }
        }
        return character;
    }

    public int getCharacterID() {
        return characterID;
    }

    public Property getInPosition() {
        return inPosition;
    }
}