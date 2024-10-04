package com.neu.dto;


public class StudentDTO implements Comparable<StudentDTO>{
    private String studentid;
    private String did;
    private String name;
    private int gender;
    private String classes;
    private String pwd;
    private int status;

    public String getStudentid() { return studentid; }
    public void setStudentid(String studentid) { this.studentid = studentid; }

    public String getDid() { return did; }
    public void setDid(String did) { this.did = did; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getGender() { return gender; }
    public void setGender(int gender) { this.gender = gender; }

    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    //重寫compareTo接口，實現按性別排序
    @Override
    public int compareTo(StudentDTO studentDTO) {
        return this.gender - studentDTO.getGender();
    }
}
