package org.example.models;

public class Employee {
    private String username;
    private float baseSalary;
    private int level;
    private BankAccount bankAccount;
    private Industry industry;
    private static int ID = 0;
    private int employeeID;

    public Employee(String username, Industry industry, float baseSalary, BankAccount bankAccount) {
        this.username = username;
        this.baseSalary = baseSalary;
        this.industry = industry;
        this.level = 1;
        this.bankAccount = bankAccount;
        ID++;
        employeeID=ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void paySalary() {
        bankAccount.deposit(industry.getOwner(), level * baseSalary);
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
