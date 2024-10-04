package com.neu.domain;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Late {
    private String studentid;
    private Date lateDate;
    private String recordTime;
    private String reason;

    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public Date getLateDate() {
        return lateDate;
    }
    public void setLateDate(Date lateDate) { this.lateDate=lateDate; }

    public String getRecordTime() {
        return recordTime;
    }
    public void setRecordTime() {
        //獲取當前時間,並轉為sqlDate形式
        Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        DateFormat pattern = new SimpleDateFormat("HH:mm:ss");
        this.recordTime = pattern.format(date);
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

}
