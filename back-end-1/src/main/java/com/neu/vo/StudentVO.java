package com.neu.vo;

public class StudentVO {
    private String studentid;
    private String did;
    private String name;
    private int gender;
    private String classes;
    private String pwd;
    private int status;
    private String bid;

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

}
