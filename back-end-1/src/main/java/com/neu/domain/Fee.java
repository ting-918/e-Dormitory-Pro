package com.neu.domain;

import java.sql.Date;
import java.util.Random;

public class Fee {
    private String did;
    private String bid;
    private Date feeDate;
    private float electricFee;
    private float waterFee;
    private int status;
    public Fee(){}
    public Fee(String did, Date feeDate, int status) {
        this.did = did;
        this.feeDate = feeDate;
        //水費和電費分別設為兩個隨機數
        double b1 = (new Random().nextInt(3)+1);
        double b2 = (new Random().nextInt(3)+1);
        double eletric = Math.pow(10, b1) * new Random().nextDouble();
        double water = Math.pow(10, b2) * new Random().nextDouble();
        this.electricFee = (float)(Math.round(eletric*100))/100;
        this.waterFee = (float)(Math.round(water*100))/100;
        this.status = status;
    }

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public Date getFeeDate() {
        return feeDate;
    }
    public void setFeeDate(Date feeDate) { this.feeDate=feeDate; }

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
