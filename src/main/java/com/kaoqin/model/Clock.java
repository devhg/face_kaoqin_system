package com.kaoqin.model;

import java.util.Date;

public class Clock {
    private String username; // 用户名
    private Date date; // 时间
    private int isClock; // 是否签到
    private String reason; // 请假原因

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIsClock() {
        return isClock;
    }

    public void setIsClock(int isClock) {
        this.isClock = isClock;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Clock{" +
                "username='" + username + '\'' +
                ", date=" + date +
                ", Clock=" + isClock +
                ", reason='" + reason + '\'' +
                '}';
    }
}
