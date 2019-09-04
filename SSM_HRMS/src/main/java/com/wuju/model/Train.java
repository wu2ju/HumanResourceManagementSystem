package com.wuju.model;

import java.sql.Timestamp;
import java.util.List;

public class Train {
    private Integer trId;
    private String trTheme;
    private String trContent;
    private Timestamp trBegin;
    private Timestamp trEnd;
    private int trState;
    private Timestamp trRelease;
    private List<Employee> employees;

    public Train() {
    }

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public String getTrTheme() {
        return trTheme;
    }

    public void setTrTheme(String trTheme) {
        this.trTheme = trTheme;
    }

    public String getTrContent() {
        return trContent;
    }

    public void setTrContent(String trContent) {
        this.trContent = trContent;
    }

    public Timestamp getTrBegin() {
        return trBegin;
    }

    public void setTrBegin(Timestamp trBegin) {
        this.trBegin = trBegin;
    }

    public Timestamp getTrEnd() {
        return trEnd;
    }

    public void setTrEnd(Timestamp trEnd) {
        this.trEnd = trEnd;
    }

    public int getTrState() {
        return trState;
    }

    public void setTrState(int trState) {
        this.trState = trState;
    }

    public Timestamp getTrRelease() {
        return trRelease;
    }

    public void setTrRelease(Timestamp trRelease) {
        this.trRelease = trRelease;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trId=" + trId +
                ", trTheme='" + trTheme + '\'' +
                ", trContent='" + trContent + '\'' +
                ", trBegin=" + trBegin +
                ", trEnd=" + trEnd +
                ", trState=" + trState +
                ", trRelease=" + trRelease +
                ", employees=" + employees +
                '}';
    }
}
