package com.neu.vo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MaintainVO {
    private int id;
    private String did;
    private String item;
    private String reason;
    private String phone;
    private String applytime;
    private String handletime;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDid() { return did; }
    public void setDid(String did) { this.did=did; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item=item; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason=reason; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone=phone; }

    public String getApplytime() { return applytime; }
    public void setApplytime(Date applytime) {
        DateFormat pattern = new SimpleDateFormat("YYYY-MM-dd");
        this.applytime=pattern.format(applytime);
    }

    public String getHandletime() { return handletime; }
    public void setHandletime(Date handletime) {
        DateFormat pattern = new SimpleDateFormat("YYYY-MM-dd");
        this.handletime=pattern.format(handletime);
    }
}
