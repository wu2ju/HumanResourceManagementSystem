package com.wuju.model;

import java.sql.Date;

public class Salary {
    private Integer slId;
    private Double slBase;
    private Double slBonus;
    private Double slRp;
    private Double slSecurity;
    private Date slDate;
    private Integer slState;
    private Double slTotal;
    private Double slReal;
    private Employee employee;

    public Salary() {
    }

    public Salary(Double slBase, Double slBonus, Double slRp, Double slSecurity, Date slDate, Integer slState, Double slTotal, Double slReal, Employee employee) {
        this.slBase = slBase;
        this.slBonus = slBonus;
        this.slRp = slRp;
        this.slSecurity = slSecurity;
        this.slDate = slDate;
        this.slState = slState;
        this.slTotal = slTotal;
        this.slReal = slReal;
        this.employee = employee;
    }

    public Integer getSlId() {
        return slId;
    }

    public void setSlId(Integer slId) {
        this.slId = slId;
    }

    public Double getSlBase() {
        return slBase;
    }

    public void setSlBase(Double slBase) {
        this.slBase = slBase;
    }

    public Double getSlBonus() {
        return slBonus;
    }

    public void setSlBonus(Double slBonus) {
        this.slBonus = slBonus;
    }

    public Double getSlRp() {
        return slRp;
    }

    public void setSlRp(Double slRp) {
        this.slRp = slRp;
    }

    public Double getSlSecurity() {
        return slSecurity;
    }

    public void setSlSecurity(Double slSecurity) {
        this.slSecurity = slSecurity;
    }

    public Date getSlDate() {
        return slDate;
    }

    public void setSlDate(Date slDate) {
        this.slDate = slDate;
    }

    public Integer getSlState() {
        return slState;
    }

    public void setSlState(Integer slState) {
        this.slState = slState;
    }

    public Double getSlTotal() {
        return slTotal;
    }

    public void setSlTotal(Double slTotal) {
        this.slTotal = slTotal;
    }

    public Double getSlReal() {
        return slReal;
    }

    public void setSlReal(Double slReal) {
        this.slReal = slReal;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "slId=" + slId +
                ", slBase=" + slBase +
                ", slBonus=" + slBonus +
                ", slRp=" + slRp +
                ", slSecurity=" + slSecurity +
                ", slDate=" + slDate +
                ", slState=" + slState +
                ", slTotal=" + slTotal +
                ", slReal=" + slReal +
                ", employee=" + employee +
                '}';
    }
}
