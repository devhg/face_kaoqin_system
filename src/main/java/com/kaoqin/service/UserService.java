package com.kaoqin.service;


import com.kaoqin.model.Clock;
import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;


public interface UserService {
    public User findByUsername(String username);

    public User loginCheck(String username, String password);

    public int clockCheck(String username);

    public void addUserInfo(UserInfo user);

    public void addClock(String username);

    public void askLeave(String username, String reason);

    public void clock(String username);

    public void updatePwd(String username, String newPwd);


}
