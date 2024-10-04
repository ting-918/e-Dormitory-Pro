package com.neu.domain;

import java.util.Calendar;

public class Bill {
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
    public void setYear(String year) {
        if(year == null || year == "") {
            Calendar calendar = Calendar.getInstance();
            if(calendar.get(Calendar.MONTH)+1 < 7)
                this.year = String.valueOf(calendar.get(Calendar.YEAR)-1);
            else
                this.year = String.valueOf(calendar.get(Calendar.YEAR));
        }
        else {
            this.year = year;
        }
    }
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
