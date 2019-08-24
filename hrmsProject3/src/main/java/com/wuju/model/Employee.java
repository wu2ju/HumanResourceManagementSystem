package com.wuju.model;

import oracle.sql.DATE;

public class Employee {
    private Integer eId;
    private String eAccount;
    private String ePassword;
    private Integer eType;
    private String eName;
    private String eSex;
    private DATE eBirthday;
    private String ePhone;
    private String eEmail;
    private Integer eState;
    private Integer p_id;

    public Employee() {
    }

    public Employee(String eAccount, String ePassword, Integer eType) {
        this.eAccount = eAccount;
        this.ePassword = ePassword;
        this.eType = eType;
    }

    public Employee(String eAccount, String ePassword) {
        this.eAccount = eAccount;
        this.ePassword = ePassword;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteAccount() {
        return eAccount;
    }

    public void seteAccount(String eAccount) {
        this.eAccount = eAccount;
    }

    public String getePassword() {
        return ePassword;
    }

    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }

    public Integer geteType() {
        return eType;
    }

    public void seteType(Integer eType) {
        this.eType = eType;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteSex() {
        return eSex;
    }

    public void seteSex(String eSex) {
        this.eSex = eSex;
    }

    public DATE geteBirthday() {
        return eBirthday;
    }

    public void seteBirthday(DATE eBirthday) {
        this.eBirthday = eBirthday;
    }

    public String getePhone() {
        return ePhone;
    }

    public void setePhone(String ePhone) {
        this.ePhone = ePhone;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }

    public Integer geteState() {
        return eState;
    }

    public void seteState(Integer eState) {
        this.eState = eState;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", eAccount='" + eAccount + '\'' +
                ", ePassword='" + ePassword + '\'' +
                ", eType=" + eType +
                ", eName='" + eName + '\'' +
                ", eSex='" + eSex + '\'' +
                ", eBirthday=" + eBirthday +
                ", ePhone='" + ePhone + '\'' +
                ", eEmail='" + eEmail + '\'' +
                ", eState=" + eState +
                ", p_id=" + p_id +
                '}';
    }
}
