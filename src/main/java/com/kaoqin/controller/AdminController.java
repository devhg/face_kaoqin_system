package com.kaoqin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaoqin.model.*;
import com.kaoqin.service.AdminService;
import com.kaoqin.utils.StatRunnable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Controller
@Transactional
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("all")
    public void findAll() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        System.out.println("开启线程");
        scheduledExecutorService.scheduleAtFixedRate(new StatRunnable(adminService), 0, 2, TimeUnit.SECONDS);
    }

    @RequestMapping("login")
    @ResponseBody
    public Moder login(@RequestBody String param) {

        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");
        String password = (String) json.get("password");

        Admin admin = adminService.loginCheck(username, password);

        System.out.println("登录跳转" + " 用户： " + admin);

        Moder mode = new Moder();
        if (admin != null) {
            mode.setMode(true);

            return mode;
        } else {
            return mode;
        }
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public Moder updatePassword(@RequestBody String param) {

        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");
        String password = (String) json.get("password");

        adminService.updatePassword(username, password);

        Moder mode = new Moder();
        mode.setMode(true);

        return mode;
    }

    @RequestMapping("clockList")
    @ResponseBody
    public List<Clock> clockList() {
        List<Clock> clocklist = adminService.clockList();
        return clocklist;
    }

    @RequestMapping("signList")
    @ResponseBody
    public List<Clock> signList() {
        List<Clock> clocklist = adminService.signList();
        return clocklist;
    }

    @RequestMapping("unsignList")
    @ResponseBody
    public List<Clock> unsignList() {
        List<Clock> clocklist = adminService.unsignList();
        return clocklist;
    }

    @RequestMapping("leaveList")
    @ResponseBody
    public List<Clock> leaveList() {
        List<Clock> clocklist = adminService.leaveList();
        return clocklist;
    }

    @RequestMapping("dateClockList")
    @ResponseBody
    public List<Clock> dateClockList(@RequestBody String param){
        JSONObject json = JSON.parseObject(param);
        String timeString = (String) json.get("timeString");
        //timeString格式为%y-%c-%e 例如2019-9-3
        return adminService.dateClockList(param);
    }

    @RequestMapping("insertUser")
    @ResponseBody
    public Moder insertUser(@RequestBody String param){
        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");
        String classname = (String) json.get("className");
        adminService.insertUser(username, classname);
        Moder mode = new Moder();
        mode.setMode(true);
        return mode;
    }

    @RequestMapping("userList")
    @ResponseBody
    public List<UserInfo> userList(){
        return adminService.userList();
    }
}
