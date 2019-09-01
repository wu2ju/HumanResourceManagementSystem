package com.wuju.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Interview {
    private Integer itId;
    private Recruit recruit;
    private ResumeForIV resumeForIV;
    private Date itDeliveryResume;
    private int itState;
    private Timestamp itTime;
    private String itAddress;
    private Employee employee;
    private User user;

    public Interview() {
    }

    public Interview(Recruit recruit, ResumeForIV resumeForIV, Date itDeliveryResume, int itState, Timestamp itTime, String itAddress, Employee employee, User user) {
        this.recruit = recruit;
        this.resumeForIV = resumeForIV;
        this.itDeliveryResume = itDeliveryResume;
        this.itState = itState;
        this.itTime = itTime;
        this.itAddress = itAddress;
        this.employee = employee;
        this.user = user;
    }

    public Integer getItId() {
        return itId;
    }

    public void setItId(Integer itId) {
        this.itId = itId;
    }

    public Recruit getRecruit() {
        return recruit;
    }

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
    }

    public ResumeForIV getResumeForIV() {
        return resumeForIV;
    }

    public void setResumeForIV(ResumeForIV resumeForIV) {
        this.resumeForIV = resumeForIV;
    }

    public Date getItDeliveryResume() {
        return itDeliveryResume;
    }

    public void setItDeliveryResume(Date itDeliveryResume) {
        this.itDeliveryResume = itDeliveryResume;
    }

    public int getItState() {
        return itState;
    }

    public void setItState(int itState) {
        this.itState = itState;
    }

    public Timestamp getItTime() {
        return itTime;
    }

    public void setItTime(Timestamp itTime) {
        this.itTime = itTime;
    }

    public String getItAddress() {
        return itAddress;
    }

    public void setItAddress(String itAddress) {
        this.itAddress = itAddress;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "itId=" + itId +
                ", recruit=" + recruit +
                ", resumeForIV=" + resumeForIV +
                ", itDeliveryResume=" + itDeliveryResume +
                ", itState=" + itState +
                ", itTime=" + itTime +
                ", itAddress='" + itAddress + '\'' +
                ", employee=" + employee +
                ", user=" + user +
                '}';
    }
}
