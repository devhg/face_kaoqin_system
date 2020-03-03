package com.kaoqin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaoqin.model.User;
import com.kaoqin.model.UserInfo;
import com.kaoqin.service.UserService;
import com.kaoqin.utils.TrainRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Controller
@Transactional
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login")
    @ResponseBody
    public String login(@RequestBody String param) {
        Map<String, Object> map = new HashMap<>();

        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");
        String password = (String) json.get("password");

        User user = userService.loginCheck(username, password);

        System.out.println("登录跳转" + user);
        if (user != null) {
            map.put("status", 200);
            return JSON.toJSONString(map);
        }

        map.put("status", -200);
        return JSON.toJSONString(map);
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public String updatePassword(HttpSession session, HttpServletRequest req, HttpServletResponse resp, @RequestBody String param) {
        Map<String, Object> map = new HashMap<>();
        JSONObject json = JSON.parseObject(param);
        System.out.println(json);

        String code = (String) json.get("checkCode"); // 验证码
        String username = (String) json.get("username"); // 用户名
        String newPwd = (String) json.get("newPwd"); // 新密码
        String oriPwd = (String) json.get("oriPwd"); // 原来密码

        String checkCode = (String) session.getAttribute("checkCode_session");

        map.put("status", 200);
        try {
            if (checkCode != null && code.equalsIgnoreCase(checkCode)) {

                User user = userService.findByUsername(username);
                if (user.getPassword().equals(oriPwd)) {
                    userService.updatePwd(username, newPwd);
                    session.removeAttribute("checkCode_session");
                    map.put("msg", "修改成功，请重新登录。");
                } else {
                    map.put("msg", "原密码错误！");
                    map.put("status", -200);
                }
            } else {
                map.put("msg", "验证错误！");
                map.put("status", -200);
            }
        } catch (Exception e) {
            map.put("msg", "错误！");
            map.put("status", -200);
        }
        return JSONObject.toJSONString(map);
    }

    // 直接请求（要求安装jackson）
    // 将ajax  传入的data对象 附给param  再通过fastjson解析

    @ResponseBody
    @RequestMapping(value = "isClock")
    public String isClock(@RequestBody String param) {
        Map<String, Object> map = new HashMap<>();

        JSONObject json = JSON.parseObject(param);
        String username = (String) json.get("username");

        int result = userService.clockCheck(username);

        map.put("status", result);
        return JSON.toJSONString(map);
    }


    // 请假表单接口 测试通过
    @RequestMapping("askLeave")
    @ResponseBody
    public String askLeave(@RequestBody String param) {
        JSONObject json = JSON.parseObject(param);
        Map<String, Object> map = new HashMap<>();

        String username = (String) json.get("username");
        String reason = (String) json.get("reason");
        userService.askLeave(username, reason);

        System.out.println("请假成功");
        map.put("msg", "请假成功");
        map.put("status", 200);
        return JSON.toJSONString(map);
    }

    // 信息维护 问题表单 测试成功
    @ResponseBody
    @RequestMapping(value = "addInfo")
    public String addInfo(@RequestBody UserInfo user) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("addtrain"+user);
        map.put("status", 200);

        userService.addUserInfo(user);
        userService.addClock(user.getUsername());
        // 线程池
        User addUser = userService.findByUsername(user.getUsername());

        ExecutorService es = Executors.newFixedThreadPool(1);
        es.submit(new TrainRunnable(addUser));

        map.put("msg", "信息维护成功");
        return JSON.toJSONString(map);
    }
}
