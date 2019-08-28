package com.wuju.model;

import java.util.List;

public class Position {
    private Integer pId;
    private String pName;
    private double pSalary;
    private String pLocation;
    private String pExperience;
    private String pEducation;
    private String pIntroduction;
    private String pRequest;
    private Department department;
    private List<Employee> employees;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public double getpSalary() {
        return pSalary;
    }

    public void setpSalary(double pSalary) {
        this.pSalary = pSalary;
    }

    public String getpLocation() {
        return pLocation;
    }

    public void setpLocation(String pLocation) {
        this.pLocation = pLocation;
    }

    public String getpExperience() {
        return pExperience;
    }

    public void setpExperience(String pExperience) {
        this.pExperience = pExperience;
    }

    public String getpEducation() {
        return pEducation;
    }

    public void setpEducation(String pEducation) {
        this.pEducation = pEducation;
    }

    public String getpIntroduction() {
        return pIntroduction;
    }

    public void setpIntroduction(String pIntroduction) {
        this.pIntroduction = pIntroduction;
    }

    public String getpRequest() {
        return pRequest;
    }

    public void setpRequest(String pRequest) {
        this.pRequest = pRequest;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Position{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pSalary=" + pSalary +
                ", pLocation='" + pLocation + '\'' +
                ", pExperience='" + pExperience + '\'' +
                ", pEducation='" + pEducation + '\'' +
                ", pIntroduction='" + pIntroduction + '\'' +
                ", pRequest='" + pRequest + '\'' +
                ", department=" + department +
                '}';
    }
}
