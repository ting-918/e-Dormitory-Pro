package com.neu.dto;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FeeDTO {
    private String did;
    private Date feeDate;
    private float electricFee;
    private float waterFee;
    private int status;

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }

    public Date getFeeDate() {
        return feeDate;
    }
    public void setFeeDate(String feeDate) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(feeDate);
        this.feeDate=sqlDate;
    }

    public float getElectricFee() { return electricFee; }
    public void setElectricFee(float electricFee) { this.electricFee = electricFee; }

    public float getWaterFee() { return waterFee; }
    public void setWaterFee(float waterFee) { this.waterFee = waterFee; }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
