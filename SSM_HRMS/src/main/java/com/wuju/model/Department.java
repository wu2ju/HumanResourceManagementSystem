package com.wuju.model;

import java.sql.Date;
import java.util.List;

public class Department {
    private Integer dpId;
    private String dpName;
    private Date dpEstablish;
    private Company company;
    private List<Position> positions;

    public Department() {
    }

    public Department(Integer dpId) {
        this.dpId = dpId;
    }

    public Date getDpEstablish() {
        return dpEstablish;
    }

    public void setDpEstablish(Date dpEstablish) {
        this.dpEstablish = dpEstablish;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Department{" +
                "dpId=" + dpId +
                ", dpName='" + dpName + '\'' +
                ", dpEstablish=" + dpEstablish +
                ", company=" + company +
                '}';
    }
}
