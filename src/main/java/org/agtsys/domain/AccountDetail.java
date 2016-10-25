package org.agtsys.domain;

import java.util.Date;

public class AccountDetail {
    private Long id;

    private Long userid;

    private Long detailtype;

    private String detailtypename;

    private Double money;

    private Double accountmoney;

    private String memo;

    private Date detaildatetime;

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

    public Long getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(Long detailtype) {
        this.detailtype = detailtype;
    }

    public String getDetailtypename() {
        return detailtypename;
    }

    public void setDetailtypename(String detailtypename) {
        this.detailtypename = detailtypename == null ? null : detailtypename.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getAccountmoney() {
        return accountmoney;
    }

    public void setAccountmoney(Double accountmoney) {
        this.accountmoney = accountmoney;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getDetaildatetime() {
        return detaildatetime;
    }

    public void setDetaildatetime(Date detaildatetime) {
        this.detaildatetime = detaildatetime;
    }
}