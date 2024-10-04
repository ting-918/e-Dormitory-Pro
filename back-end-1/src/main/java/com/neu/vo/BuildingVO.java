package com.neu.vo;

public class BuildingVO {
    private String bid;
    private String pwd;
    private int amount;

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAmount() { return amount; }
    public void setAmount(Integer amount) {
        if(amount == null)
            this.amount = 0;
        else
            this.amount = amount;
    }
}
