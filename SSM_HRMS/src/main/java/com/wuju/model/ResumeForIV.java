package com.wuju.model;

import java.sql.Date;

public class ResumeForIV {
    private Integer rId;
    private String rName;
    private String rSex;
    private Date rBirthday;
    private Date rWorktime;
    private String rSchool;
    private String rEducation;
    private String rMajor;
    private Date rAdmission;
    private Date rGraduation;
    private String rSchoolexp;
    private String rCompany;
    private String rJob;
    private String rAbility;
    private Date rEntry;
    private Date rQuit;
    private String rHopejob;
    private double rSalary;
    private String rIndustry;
    private String rLocation;
    private String rFeature;
    private User user;
    private int rState;

    public ResumeForIV() {
    }

    public ResumeForIV(Integer rId) {
        this.rId = rId;
    }

    public ResumeForIV(String rName, String rSex, Date rBirthday, Date rWorktime, String rSchool, String rEducation, String rMajor, Date rAdmission, Date rGraduation, String rSchoolexp, String rCompany, String rJob, String rAbility, Date rEntry, Date rQuit, String rHopejob, double rSalary, String rIndustry, String rLocation, String rFeature, User user) {
        this.rName = rName;
        this.rSex = rSex;
        this.rBirthday = rBirthday;
        this.rWorktime = rWorktime;
        this.rSchool = rSchool;
        this.rEducation = rEducation;
        this.rMajor = rMajor;
        this.rAdmission = rAdmission;
        this.rGraduation = rGraduation;
        this.rSchoolexp = rSchoolexp;
        this.rCompany = rCompany;
        this.rJob = rJob;
        this.rAbility = rAbility;
        this.rEntry = rEntry;
        this.rQuit = rQuit;
        this.rHopejob = rHopejob;
        this.rSalary = rSalary;
        this.rIndustry = rIndustry;
        this.rLocation = rLocation;
        this.rFeature = rFeature;
        this.user = user;
    }

    public ResumeForIV(User user) {
        this.user = user;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrSex() {
        return rSex;
    }

    public void setrSex(String rSex) {
        this.rSex = rSex;
    }

    public Date getrBirthday() {
        return rBirthday;
    }

    public void setrBirthday(Date rBirthday) {
        this.rBirthday = rBirthday;
    }

    public Date getrWorktime() {
        return rWorktime;
    }

    public void setrWorktime(Date rWorktime) {
        this.rWorktime = rWorktime;
    }

    public String getrSchool() {
        return rSchool;
    }

    public void setrSchool(String rSchool) {
        this.rSchool = rSchool;
    }

    public String getrEducation() {
        return rEducation;
    }

    public void setrEducation(String rEducation) {
        this.rEducation = rEducation;
    }

    public String getrMajor() {
        return rMajor;
    }

    public void setrMajor(String rMajor) {
        this.rMajor = rMajor;
    }

    public Date getrAdmission() {
        return rAdmission;
    }

    public void setrAdmission(Date rAdmission) {
        this.rAdmission = rAdmission;
    }

    public Date getrGraduation() {
        return rGraduation;
    }

    public void setrGraduation(Date rGraduation) {
        this.rGraduation = rGraduation;
    }

    public String getrSchoolexp() {
        return rSchoolexp;
    }

    public void setrSchoolexp(String rSchoolexp) {
        this.rSchoolexp = rSchoolexp;
    }

    public String getrCompany() {
        return rCompany;
    }

    public void setrCompany(String rCompany) {
        this.rCompany = rCompany;
    }

    public String getrJob() {
        return rJob;
    }

    public void setrJob(String rJob) {
        this.rJob = rJob;
    }

    public String getrAbility() {
        return rAbility;
    }

    public void setrAbility(String rAbility) {
        this.rAbility = rAbility;
    }

    public Date getrEntry() {
        return rEntry;
    }

    public void setrEntry(Date rEntry) {
        this.rEntry = rEntry;
    }

    public Date getrQuit() {
        return rQuit;
    }

    public void setrQuit(Date rQuit) {
        this.rQuit = rQuit;
    }

    public String getrHopejob() {
        return rHopejob;
    }

    public void setrHopejob(String rHopejob) {
        this.rHopejob = rHopejob;
    }

    public double getrSalary() {
        return rSalary;
    }

    public void setrSalary(double rSalary) {
        this.rSalary = rSalary;
    }

    public String getrIndustry() {
        return rIndustry;
    }

    public void setrIndustry(String rIndustry) {
        this.rIndustry = rIndustry;
    }

    public String getrLocation() {
        return rLocation;
    }

    public void setrLocation(String rLocation) {
        this.rLocation = rLocation;
    }

    public String getrFeature() {
        return rFeature;
    }

    public void setrFeature(String rFeature) {
        this.rFeature = rFeature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getrState() {
        return rState;
    }

    public void setrState(int rState) {
        this.rState = rState;
    }

    @Override
    public String toString() {
        return "ResumeForIV{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                ", rSex='" + rSex + '\'' +
                ", rBirthday=" + rBirthday +
                ", rWorktime=" + rWorktime +
                ", rSchool='" + rSchool + '\'' +
                ", rEducation='" + rEducation + '\'' +
                ", rMajor='" + rMajor + '\'' +
                ", rAdmission=" + rAdmission +
                ", rGraduation=" + rGraduation +
                ", rSchoolexp='" + rSchoolexp + '\'' +
                ", rCompany='" + rCompany + '\'' +
                ", rJob='" + rJob + '\'' +
                ", rAbility='" + rAbility + '\'' +
                ", rEntry=" + rEntry +
                ", rQuit=" + rQuit +
                ", rHopejob='" + rHopejob + '\'' +
                ", rSalary=" + rSalary +
                ", rIndustry='" + rIndustry + '\'' +
                ", rLocation='" + rLocation + '\'' +
                ", rFeature='" + rFeature + '\'' +
                ", user=" + user +
                ", rState=" + rState +
                '}';
    }
}
