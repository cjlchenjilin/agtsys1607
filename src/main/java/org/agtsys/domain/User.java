package org.agtsys.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.agtsys.util.MD5;
import org.agtsys.validate.LoginValidateGroup;
import org.hibernate.validator.constraints.NotEmpty;

public class User implements Serializable{
    private Long id;
    
    @NotEmpty(message="{user.usercode.isnull}",groups={LoginValidateGroup.class})
    private String usercode;

    private String username;
    
    @NotEmpty(message="{user.password.isnull}",groups={LoginValidateGroup.class})
    @Min(value=36,message="{user.password.length}")
    private String userpassword;

    private Date creationtime;

    private Date lastlogintime;

    private String createdby;

    private Date lastupdatetime;

    private Integer isstart;

    private Long roleid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null || userpassword.trim().length()==0? null : MD5.md5encode( userpassword.trim());
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}