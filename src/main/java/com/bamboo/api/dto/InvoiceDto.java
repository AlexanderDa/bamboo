package com.bamboo.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDto {
    private int invoiceId;
    private int number;
    private Date dateOfIssue;
    private Double totalToPay;
    private boolean payed;
    private BeneficiaryDto beneficiary;
    private UserDto debtcollector;

    public InvoiceDto() {
    }

    public InvoiceDto(int invoiceId, int number, Date dateOfIssue, Double totalToPay, boolean payed, BeneficiaryDto beneficiary, UserDto debtcollector) {
        this.invoiceId = invoiceId;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.totalToPay = totalToPay;
        this.payed = payed;
        this.beneficiary = beneficiary;
        this.debtcollector = debtcollector;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Double getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(Double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public BeneficiaryDto getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDto beneficiary) {
        this.beneficiary = beneficiary;
    }

    public UserDto getDebtcollector() {
        return debtcollector;
    }

    public void setDebtcollector(UserDto debtcollector) {
        this.debtcollector = debtcollector;
    }
}
