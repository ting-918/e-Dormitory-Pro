package com.neu.domain;

public class CheckOut {
    private String studentid;
    private String reason;
    private int status;

    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
