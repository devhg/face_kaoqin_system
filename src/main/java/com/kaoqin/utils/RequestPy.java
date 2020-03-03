package com.kaoqin.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RequestPy {
    public static String train(String username, String className, String method) {
        BufferedReader br = null;
        String result = "";
        String Url = "http://127.0.0.1:5000/" + method;
        try {
            String params = String.format("className=%s&stuId=%s", URLEncoder.encode(className, "utf-8"),
                    URLEncoder.encode(username, "utf-8"));

            URL url = new URL(Url + "?" + params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.connect();

            int resCode = connection.getResponseCode(); //获取响应码 =200

            String message = connection.getResponseMessage(); //获取响应消息 =OK

            System.out.println("getResponseCode resCode =" + resCode);
            System.out.println("getResponseMessage message =" + message);

            if (200 == connection.getResponseCode()) {
                if (connection.getContentLength() > 0) {
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        result += "\n" + line;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String getResult(String username, String base) {
        BufferedReader br = null;
        String result = "";
        String Url = null;
        try {
            Url = "http://127.0.0.1:5000/myface?username=" + URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            String params = URLEncoder.encode("images", "UTF-8") + "=" + URLEncoder.encode(base, "UTF-8");
            URL url = new URL(Url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // base64的字符串过长  只能通过网络流写
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();

            connection.connect();

            int resCode = connection.getResponseCode(); //获取响应码 =200

            String message = connection.getResponseMessage(); //获取响应消息 =OK

            System.out.println("getResponseCode resCode =" + resCode);
            System.out.println("getResponseMessage message =" + message);

            if (200 == connection.getResponseCode()) {
                if (connection.getContentLength() > 0) {
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        result += "\n" + line;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
