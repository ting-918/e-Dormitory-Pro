package com.neu.dto;

public class ApplyDTO {
    private int type;
    private String studentid;
    private String reason;
    private int status;

    public int getType() { return type; }
    public void setType(int type) {this.type = type; }

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
