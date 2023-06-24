package org.example.models;

public class Job {
    private String title;
    private float income;
    private String industryId;
    private static int ID = 0;
    private int JobID ;

    /**
     * @param title : Industry title
     * @param income : industry The monthly income of its employees
     * @param industryId : industry id
     * */
    public Job(String title, float income, String industryId) {
        this.title = title;
        this.income = income;
        this.industryId = industryId;
        ID++;
        JobID=ID;
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

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public int getJobID() {
        return JobID;
    }
}
