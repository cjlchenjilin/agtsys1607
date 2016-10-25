package org.agtsys.domain;

import java.util.Date;

public class Logs {
    private Long id;

    private Long userid;

    private String username;

    private String operateinfo;

    private Date operatedatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOperateinfo() {
        return operateinfo;
    }

    public void setOperateinfo(String operateinfo) {
        this.operateinfo = operateinfo == null ? null : operateinfo.trim();
    }

    public Date getOperatedatetime() {
        return operatedatetime;
    }

    public void setOperatedatetime(Date operatedatetime) {
        this.operatedatetime = operatedatetime;
    }
}