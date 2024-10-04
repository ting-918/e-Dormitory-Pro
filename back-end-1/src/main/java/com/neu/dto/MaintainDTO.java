package com.neu.dto;

import java.sql.Date;

public class MaintainDTO {
    private int id;
    private String did;
    private String item;
    private String reason;
    private String phone;

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
}
