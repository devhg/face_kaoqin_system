package com.kaoqin.mapper;

import com.kaoqin.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    public List<User> findAll();

    //adminLogin
    public Admin loginCheck(String username, String password);

    //editAdminPassword
    public void updatePassword(String username, String password);

    //showAllclockList
    public List<Clock> clockList();

    //signedList
    public List<Clock> signList();

    //unsignedList
    public List<Clock> unsignList();

    //LeaveReasonList
    public List<Clock> leaveList();

    //findListByTimeString
    public List<Clock> dateClockList(String timeString);

    //addOneUser
    public void insertUser(String username, String className);

    //allUserInfo
    public List<UserInfo> userList();
}
