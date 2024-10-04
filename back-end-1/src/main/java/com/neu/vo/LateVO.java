package com.neu.vo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LateVO {
    private String studentid;
    private String lateDate;
    private String recordTime;
    private String reason;

    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getLateDate() {
        return lateDate;
    }
    public void setLateDate(Date lateDate) {
        DateFormat pattern = new SimpleDateFormat("YYYY-MM-dd");
        this.lateDate=pattern.format(lateDate);
    }

    public String getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}
