package com.neu.dto;

public class BuildingDTO {
    private String bid;
    private String pwd;
    private int floor;
    private int room;
    private int bed;

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public int getRoom() { return room; }
    public void setRoom(int room) { this.room = room; }

    public int getBed() { return bed; }
    public void setBed(int bed) { this.bed = bed; }
}
