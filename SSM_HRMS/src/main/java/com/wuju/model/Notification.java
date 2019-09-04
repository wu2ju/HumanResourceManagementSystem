package com.wuju.model;

public class Notification {
    private Integer ntId;
    private String ntAccount;
    private String ntPassword;
    private User user;
    private int ntState;

    public Notification() {
    }

    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    public String getNtAccount() {
        return ntAccount;
    }

    public void setNtAccount(String ntAccount) {
        this.ntAccount = ntAccount;
    }

    public String getNtPassword() {
        return ntPassword;
    }

    public void setNtPassword(String ntPassword) {
        this.ntPassword = ntPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNtState() {
        return ntState;
    }

    public void setNtState(int ntState) {
        this.ntState = ntState;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "ntId=" + ntId +
                ", ntAccount='" + ntAccount + '\'' +
                ", ntPassword='" + ntPassword + '\'' +
                ", user=" + user +
                ", ntState=" + ntState +
                '}';
    }
}
