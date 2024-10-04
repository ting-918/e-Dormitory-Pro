package com.neu.vo;

public class CheckOutVO {
    private String studentid;
    private String reason;
    private int status;
    private String name;
    private String classes;

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }
}
