package com.kaoqin.service.Impl;


import com.kaoqin.mapper.UserMapper;
import com.kaoqin.model.Clock;
import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;
import com.kaoqin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional // 配置事务
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User loginCheck(String username, String password) {
        return userMapper.loginCheck(username, password);
    }

    @Override
    public int clockCheck(String username) {
        return userMapper.clockCheck(username);
    }

    @Override
    public void addUserInfo(UserInfo user) {
        userMapper.addUserInfo(user);
    }

    @Override
    public void addClock(String username) {
        userMapper.addClock(username);
    }

    @Override
    public void askLeave(String username, String reason) {
        userMapper.askLeave(username, reason);
    }

    @Override
    public void clock(String username) {
        userMapper.clock(username);
    }

    @Override
    public void updatePwd(String username, String newPwd) {
        userMapper.updatePwd(username, newPwd);
    }
}
