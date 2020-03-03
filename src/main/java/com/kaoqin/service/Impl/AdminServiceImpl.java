package com.kaoqin.service.Impl;

import com.kaoqin.mapper.AdminMapper;
import com.kaoqin.model.Admin;
import com.kaoqin.model.Clock;
import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;
import com.kaoqin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<User> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public Admin loginCheck(String username, String password) {
        return adminMapper.loginCheck(username, password);
    }

    @Override
    public void updatePassword(String username, String password) {
        adminMapper.updatePassword(username, password);
    }

    @Override
    public List<Clock> clockList() {
        return adminMapper.clockList();
    }

    @Override
    public List<Clock> signList() {
        return adminMapper.signList();
    }

    @Override
    public List<Clock> unsignList() {
        return adminMapper.unsignList();
    }

    @Override
    public List<Clock> leaveList() {
        return adminMapper.leaveList();
    }

    @Override
    public List<Clock> dateClockList(String timeString) {
        return adminMapper.dateClockList(timeString);
    }

    @Override
    public void insertUser(String username, String className) {
        adminMapper.insertUser(username, className);
    }

    @Override
    public List<UserInfo> userList() {
        return adminMapper.userList();
    }

}
