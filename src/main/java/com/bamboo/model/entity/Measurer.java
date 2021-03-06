/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bamboo.model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alexander
 */
public class Measurer {

    private int id;
    private String number;
    private Date installationDate;
    private int sap;
    private int status;

    public Measurer() {
    }

    public Measurer(int id, String number, Date installationDate, int sap, int status) {
        this.id = id;
        this.number = number;
        this.installationDate = installationDate;
        this.sap = sap;
        this.status = status;
    }

    public Measurer(int id, String number, String installationDate, int sap, int status) throws ParseException {
        this.id = id;
        this.number = number;
        toDate(installationDate);
        this.sap = sap;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }

    public void setInstallationDate(String installationDate) throws ParseException {
        toDate(installationDate);
    }

    public int getSap() {
        return sap;
    }

    public void setSap(int sap) {
        this.sap = sap;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private void toDate(String dateOfIssue) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.installationDate = sdf.parse(dateOfIssue);
        } catch (ParseException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "Measurer{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", installationDate=" + installationDate +
                ", sap=" + sap +
                ", status=" + status +
                '}';
    }
}
