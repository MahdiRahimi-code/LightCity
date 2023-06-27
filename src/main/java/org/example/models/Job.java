package org.example.models;

public class Job {
    private String title;
    private float income;
    private int industryId;
    private static int ID = 0;
    private int JobID;

    /**
     * @param title      : Industry title
     * @param income     : industry The monthly income of its employees
     * @param industryId : industry id
     */
    public Job(String title, float income, int industryId) {
        this.title = title;
        this.income = income;
        this.industryId = industryId;
        ID++;
        JobID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public int getJobID() {
        return JobID;
    }
}
