package com.neu.dto;

import java.util.Calendar;

public class BillDTO {
    private String studentid;
    private String year;
    private int price;
    private int status;
    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) { this.year = year; }
    public int getPrice() { return price; }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
