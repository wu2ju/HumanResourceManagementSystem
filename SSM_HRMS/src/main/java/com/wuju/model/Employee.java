package com.wuju.model;

import java.sql.Date;

public class Employee {
    private Integer eId;
    private String eAccount;
    private String ePassword;
    private Integer eType;
    private String eName;
    private String eSex;
    private Date eBirthday;
    private String ePhone;
    private String eEmail;
    private Integer eState;
    private Position position;
    private Date eEntry;
    private Date eBefull;

    public Employee() {
    }

    public Employee(String eAccount, String ePassword, Integer eType, String eName, String eSex, Date eBirthday, String ePhone, String eEmail, Integer eState, Position position, Date eEntry, Date eBefull) {
        this.eAccount = eAccount;
        this.ePassword = ePassword;
        this.eType = eType;
        this.eName = eName;
        this.eSex = eSex;
        this.eBirthday = eBirthday;
        this.ePhone = ePhone;
        this.eEmail = eEmail;
        this.eState = eState;
        this.position = position;
        this.eEntry = eEntry;
        this.eBefull = eBefull;
    }

    public Employee(Integer eId) {
        this.eId = eId;
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

    public Date geteBirthday() {
        return eBirthday;
    }

    public void seteBirthday(Date eBirthday) {
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date geteEntry() {
        return eEntry;
    }

    public void seteEntry(Date eEntry) {
        this.eEntry = eEntry;
    }

    public Date geteBefull() {
        return eBefull;
    }

    public void seteBefull(Date eBefull) {
        this.eBefull = eBefull;
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
                ", position=" + position +
                ", eEntry=" + eEntry +
                ", eBefull=" + eBefull +
                '}';
    }
}
