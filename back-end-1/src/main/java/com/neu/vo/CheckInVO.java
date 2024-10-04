package com.neu.vo;

public class CheckInVO {
    private String studentid;
    private String did;
    private int status;
    private String name;
    private String classes;

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }
}
