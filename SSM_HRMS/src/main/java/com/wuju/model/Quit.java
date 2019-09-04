package com.wuju.model;

import java.sql.Date;

public class Quit {
    private Integer qtId;
    private Date qtTime;
    private String qtReason;
    private Employee employee;

    public Quit() {
    }

    public Quit(Date qtTime, String qtReason, Employee employee) {
        this.qtTime = qtTime;
        this.qtReason = qtReason;
        this.employee = employee;
    }

    public Integer getQtId() {
        return qtId;
    }

    public void setQtId(Integer qtId) {
        this.qtId = qtId;
    }

    public Date getQtTime() {
        return qtTime;
    }

    public void setQtTime(Date qtTime) {
        this.qtTime = qtTime;
    }

    public String getQtReason() {
        return qtReason;
    }

    public void setQtReason(String qtReason) {
        this.qtReason = qtReason;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Quit{" +
                "qtId=" + qtId +
                ", qtTime=" + qtTime +
                ", qtReason='" + qtReason + '\'' +
                ", employee=" + employee +
                '}';
    }
}
