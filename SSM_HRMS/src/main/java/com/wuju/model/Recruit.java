package com.wuju.model;

import java.sql.Date;

public class Recruit {
    private Integer rcId;
    private Date rcRelease;
    private Date rcDeadline;
    private Integer rcState;
    private Date rcBackout;
    private Position position;
    private Department department;

    public Recruit() {
    }

    public Recruit(Integer rcId) {
        this.rcId = rcId;
    }

    public Integer getRcId() {
        return rcId;
    }

    public void setRcId(Integer rcId) {
        this.rcId = rcId;
    }

    public Date getRcRelease() {
        return rcRelease;
    }

    public void setRcRelease(Date rcRelease) {
        this.rcRelease = rcRelease;
    }

    public Date getRcDeadline() {
        return rcDeadline;
    }

    public void setRcDeadline(Date rcDeadline) {
        this.rcDeadline = rcDeadline;
    }

    public Integer getRcState() {
        return rcState;
    }

    public void setRcState(Integer rcState) {
        this.rcState = rcState;
    }

    public Date getRcBackout() {
        return rcBackout;
    }

    public void setRcBackout(Date rcBackout) {
        this.rcBackout = rcBackout;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Recruit{" +
                "rcId=" + rcId +
                ", rcRelease=" + rcRelease +
                ", rcDeadline=" + rcDeadline +
                ", rcState=" + rcState +
                ", rcBackout=" + rcBackout +
                ", position=" + position +
                ", department=" + department +
                '}';
    }
}
