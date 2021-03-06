/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alexander
 */
public class Assigned {

    private int id;
    private int beneficiary;
    private int measurer;
    private Date assignmentDate;
    private String status;
    private double debt;

    public Assigned() {
    }

    public Assigned(int id,int beneficiary, int measurer, Date assignmentDate, String status, double debt) {
        this.id = id;
        this.beneficiary = beneficiary;
        this.measurer = measurer;
        this.assignmentDate = assignmentDate;
        this.status = status;
        this.debt = debt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(int beneficiary) {
        this.beneficiary = beneficiary;
    }

    public int getMeasurer() {
        return measurer;
    }

    public void setMeasurer(int measurer) {
        this.measurer = measurer;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "Assigned{" +
                "id=" + id +
                ", beneficiary=" + beneficiary +
                ", measurer=" + measurer +
                ", assignmentDate=" + assignmentDate +
                ", status='" + status + '\'' +
                ", debt=" + debt +
                '}';
    }
}
