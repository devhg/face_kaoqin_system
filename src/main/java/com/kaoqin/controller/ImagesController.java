package com.kaoqin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kaoqin.model.User;
import com.kaoqin.service.UserService;
import com.kaoqin.utils.RequestPy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("img")
public class ImagesController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "faceLogin", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String faceLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", -200);
        try {
            String base = request.getParameter("base");
            String username = request.getParameter("username");
            System.out.println(username);
            System.out.println(base);

            System.out.println("正在请求py识别接口");
            String result = RequestPy.getResult(username, base);
            JSONObject json = JSONObject.parseObject(result/*"待解析的json字符串"*/);

            System.out.println("识别中的" + json);

            int status = (int) json.get("status");
            if (status == 200) {
                map.put("status", status);
                userService.clock(username);
                map.put("msg", "签到成功！");
            } else {
                map.put("msg", "签到失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "签到失败！");
        }

        return JSON.toJSONString(map);
    }


    // 信息维护上传图片
    @RequestMapping(value = "upload", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String uploadImg(@RequestParam(value = "file") MultipartFile file, HttpServletRequest req) {
        Map<String, Object> map = new HashMap<>();
        String param = req.getParameter("param"); // 传过来的参数 照片的用户信息及编号
        JSONObject json = JSON.parseObject(param); // 参数转为json

        String username = (String) json.get("username"); // 用户名
        String cnt = (String) json.get("cnt"); // 照片编号

        User user = userService.findByUsername(username);// 用户信息 用来获取classname


        try {
            String oriFilename = file.getOriginalFilename();
            // 获取文件后缀
            String fileType = oriFilename.substring(oriFilename.lastIndexOf(".") + 1, oriFilename.length()).toLowerCase();
            // 存储路径
            String basePath = "F:\\imgDataSet\\" + user.getClassName() + "\\" + user.getUsername() + "\\userInfo";
            // 保存的文件名字
            String saveName = String.valueOf(username + "_" + cnt + "." + fileType);

            File dst = new File(basePath, saveName);

            if (!dst.getParentFile().exists()) {
                dst.mkdirs();
            }
            file.transferTo(dst); // 写入本地

            map.put("msg", "图片上传成功！");
            map.put("status", 200);

        } catch (IOException e) {
            map.put("status", -200);
            map.put("msg", "图片上传失败！");
        }

        System.out.println("图片上传成功！");

        return JSON.toJSONString(map);

    }

}