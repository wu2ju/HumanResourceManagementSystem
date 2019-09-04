package com.wuju.model;

import java.sql.Timestamp;

public class RewardPunish {
    private Integer rpId;
    private Timestamp rpTime;
    private String rpReason;
    private Employee employee;
    private int rpRecord;
    private double rpMoney;
    // 记录是因为什么产生的奖惩，1 表示是因为打卡

    public RewardPunish() {
    }

    public RewardPunish(Timestamp rpTime, String rpReason, Employee employee, int rpRecord, double rpMoney) {
        this.rpTime = rpTime;
        this.rpReason = rpReason;
        this.employee = employee;
        this.rpRecord = rpRecord;
        this.rpMoney = rpMoney;
    }

    public Integer getRpId() {
        return rpId;
    }

    public void setRpId(Integer rpId) {
        this.rpId = rpId;
    }

    public Timestamp getRpTime() {
        return rpTime;
    }

    public void setRpTime(Timestamp rpTime) {
        this.rpTime = rpTime;
    }

    public String getRpReason() {
        return rpReason;
    }

    public void setRpReason(String rpReason) {
        this.rpReason = rpReason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRpRecord() {
        return rpRecord;
    }

    public void setRpRecord(int rpRecord) {
        this.rpRecord = rpRecord;
    }

    public double getRpMoney() {
        return rpMoney;
    }

    public void setRpMoney(double rpMoney) {
        this.rpMoney = rpMoney;
    }

    @Override
    public String toString() {
        return "RewardPunish{" +
                "rpId=" + rpId +
                ", rpTime=" + rpTime +
                ", rpReason='" + rpReason + '\'' +
                ", employee=" + employee +
                ", rpRecord=" + rpRecord +
                ", rpMoney=" + rpMoney +
                '}';
    }
}
