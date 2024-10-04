package com.neu.vo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FeeVO {
    private String did;
    private String feeDate;
    private float electricFee;
    private float waterFee;
    private int status;

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }

    public String getFeeDate() {
        return feeDate;
    }
    public void setFeeDate(Date feeDate) {
        DateFormat pattern = new SimpleDateFormat("YYYY-MM-dd");
        this.feeDate=pattern.format(feeDate);
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
