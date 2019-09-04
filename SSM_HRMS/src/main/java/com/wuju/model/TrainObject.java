package com.wuju.model;

public class TrainObject {
    private Integer toId;
    private Employee employee;
    private Train train;
    private int toState;

    public TrainObject() {
    }

    public TrainObject(Employee employee, Train train, int toState) {
        this.employee = employee;
        this.train = train;
        this.toState = toState;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getToState() {
        return toState;
    }

    public void setToState(int toState) {
        this.toState = toState;
    }

    @Override
    public String toString() {
        return "TrainObject{" +
                "toId=" + toId +
                ", employee=" + employee +
                ", train=" + train +
                ", toState=" + toState +
                '}';
    }
}
