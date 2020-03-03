package com.kaoqin.mapper;

import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;
import org.springframework.stereotype.Repository;


@Repository("UserMapper")
public interface UserMapper {

    // 登录
    public User loginCheck(String username, String password);

    // 根据用户名查找用户
    public User findByUsername(String username);

    // 根据用户名查询是否签到
    public int clockCheck(String username);

    // 根据用户名维护用户信息
    public void addUserInfo(UserInfo user);

    // 添加签到表用户
    public void addClock(String username);

    // 请假
    public void askLeave(String username, String reason);

    // 签到
    public void clock(String username);

    // 修改密码
    public void updatePwd(String username, String newPwd);
}
