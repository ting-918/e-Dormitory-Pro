package com.neu.dto;

import java.sql.Date;

public class LateDTO {

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
    public void setLateDate(String lateDate) {
        Date sqlDate = Date.valueOf(lateDate);
        this.lateDate=sqlDate;
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
