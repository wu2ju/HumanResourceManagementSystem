package com.wuju.model;

public class Department {
    private Integer dpId;
    private String dpName;
    private Company company;

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

    @Override
    public String toString() {
        return "Department{" +
                "dpId=" + dpId +
                ", dpName='" + dpName + '\'' +
                ", company=" + company +
                '}';
    }
}
