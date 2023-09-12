package com.baeldung.deep;


public class Profession implements Cloneable{
    private String jobTitle;
    private double salary;
    private Company company;
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public double getSalary() {return salary;}
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public Profession(String jobTitle, double salary, Company company){
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.company = company;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        Profession professionCopy =  (Profession) super.clone();
        professionCopy.setCompany((Company) this.company.clone());
        return professionCopy;
    }
    // standard getters and setters
}

