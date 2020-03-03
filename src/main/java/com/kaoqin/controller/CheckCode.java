package com.kaoqin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Controller
public class CheckCode {

    @RequestMapping("cc")
    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setCharacterEncoding("utf-8");
        // 创建验证码图片对象
        int width = 200;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 美化图片
        Graphics g = image.getGraphics();
        Font font = new Font("Microsoft YaHei", Font.BOLD, 30); // 创建字体对象
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height); // 填充背景颜色
        g.setFont(font); // 设置字体

        g.setColor(Color.BLUE);
        g.drawRect(0, 0, width - 1, height - 1); // 画上边框 主题边框默认1px宽

        // 随机生成字母数字
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        int len = str.length();
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            char ch = str.charAt(random.nextInt(len));
            sb.append(ch);
            g.drawString("" + ch, random.nextInt(5) + i * 35, random.nextInt(40) + 35);
        }
        String checkCode = sb.toString();
        req.getSession().setAttribute("checkCode_session", checkCode);

        // 画干扰线
        g.setColor(Color.green);
        g.drawLine(3, 44, 188, 60);
        g.drawLine(20, 20, 150, 67);
        g.drawLine(5, 88, 199, 60);
        // 写进网页
        ImageIO.write(image, "png", resp.getOutputStream());

    }

}
