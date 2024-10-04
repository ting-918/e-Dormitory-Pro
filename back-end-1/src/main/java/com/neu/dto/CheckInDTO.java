package com.neu.dto;

public class CheckInDTO {
    private String studentid;
    private String did;
    private int status;

    public String getStudentid() {
        return studentid;
    }
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getDid() {
        return did;
    }
    public void setDid(String did) {
        this.did = did;
    }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
