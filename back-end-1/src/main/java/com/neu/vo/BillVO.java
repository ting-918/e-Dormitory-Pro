package com.neu.vo;

import java.util.Calendar;

public class BillVO {
    private String studentid;
    private String year;
    private int price;
    private int status;
    private String name;
    private String classes;

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }
}
