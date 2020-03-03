package com.kaoqin.utils;

import com.kaoqin.model.User;
import com.kaoqin.service.AdminService;

import java.util.List;

/**
 * 统计每天的签到情况
 */
public class StatRunnable implements Runnable {

    private AdminService adminService;

    public StatRunnable(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run() {
        List<User> all = adminService.findAll();
        System.out.println(all);
    }
}
