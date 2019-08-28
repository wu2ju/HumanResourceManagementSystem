package com.wuju.model;

import java.sql.Date;

public class Company {
    private Integer cpId;
    private String cpName;
    private String cpPhone;
    private String cpAddress;
    private Date cpEstablish;
    private int cpStaffsize;
    private String cpIndustry;
    private String cpIntroduction;


    public Company() {
    }

    public Company(Integer cpId) {
        this.cpId = cpId;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getCpPhone() {
        return cpPhone;
    }

    public void setCpPhone(String cpPhone) {
        this.cpPhone = cpPhone;
    }

    public String getCpAddress() {
        return cpAddress;
    }

    public void setCpAddress(String cpAddress) {
        this.cpAddress = cpAddress;
    }

    public Date getCpEstablish() {
        return cpEstablish;
    }

    public void setCpEstablish(Date cpEstablish) {
        this.cpEstablish = cpEstablish;
    }

    public int getCpStaffsize() {
        return cpStaffsize;
    }

    public void setCpStaffsize(int cpStaffsize) {
        this.cpStaffsize = cpStaffsize;
    }

    public String getCpIndustry() {
        return cpIndustry;
    }

    public void setCpIndustry(String cpIndustry) {
        this.cpIndustry = cpIndustry;
    }

    public String getCpIntroduction() {
        return cpIntroduction;
    }

    public void setCpIntroduction(String cpIntroduction) {
        this.cpIntroduction = cpIntroduction;
    }

    @Override
    public String toString() {
        return "Company{" +
                "cpId=" + cpId +
                ", cpName='" + cpName + '\'' +
                ", cpPhone='" + cpPhone + '\'' +
                ", cpAddress='" + cpAddress + '\'' +
                ", cpEstablish=" + cpEstablish +
                ", cpStaffsize=" + cpStaffsize +
                ", cpIndustry='" + cpIndustry + '\'' +
                ", cpIntroduction='" + cpIntroduction + '\'' +
                '}';
    }
}
