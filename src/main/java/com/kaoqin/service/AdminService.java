package com.kaoqin.service;

import com.kaoqin.model.Admin;
import com.kaoqin.model.Clock;
import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;

import java.util.List;

public interface AdminService {
    public List<User> findAll();

    public Admin loginCheck(String username, String password);

    public void updatePassword(String username, String password);

    public List<Clock> clockList();

    public List<Clock> signList();

    public List<Clock> unsignList();

    public List<Clock> leaveList();

    public List<Clock> dateClockList(String timeString);

    public void insertUser(String username, String className);

    public List<UserInfo> userList();

}
