package com.neu.vo;

public class DormitoryVO {
    private String did;
    private String bid;
    private String door;
    private int remain;
    private int amount;
    private int gender;

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getDoor() {
        return door;
    }
    public void setDoor(String door) {
        this.door = door;
    }

    public int getRemain() {
        return remain;
    }
    public void setRemain(int remain) {
        this.remain = remain;
    }

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
}
