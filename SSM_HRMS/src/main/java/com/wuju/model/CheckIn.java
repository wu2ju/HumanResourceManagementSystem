package com.wuju.model;

import java.sql.Timestamp;

public class CheckIn {
    private Integer ciId;
    private Timestamp ciAttendtime;
    private Timestamp ciClosetime;
    private Employee employee;

    public CheckIn() {
    }

    public CheckIn(Timestamp ciAttendtime, Timestamp ciClosetime, Employee employee) {
        this.ciAttendtime = ciAttendtime;
        this.ciClosetime = ciClosetime;
        this.employee = employee;
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public Timestamp getCiAttendtime() {
        return ciAttendtime;
    }

    public void setCiAttendtime(Timestamp ciAttendtime) {
        this.ciAttendtime = ciAttendtime;
    }

    public Timestamp getCiClosetime() {
        return ciClosetime;
    }

    public void setCiClosetime(Timestamp ciClosetime) {
        this.ciClosetime = ciClosetime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "ciId=" + ciId +
                ", ciAttendtime=" + ciAttendtime +
                ", ciClosetime=" + ciClosetime +
                ", employee=" + employee +
                '}';
    }
}
