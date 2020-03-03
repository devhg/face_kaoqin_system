package com.kaoqin.test;

import com.kaoqin.model.Admin;
import com.kaoqin.model.Clock;
import com.kaoqin.service.AdminService;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Excel {
    public static void main(String[] args) throws IOException {

//        File xlsfile = new File("/Users/qxqzx/Desktop/jk1806.xlsx");
////         获取工作薄
//        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream(xlsfile));
//        // 获取工作表
//        XSSFSheet sheet = sheets.getSheetAt(0);
//
//        int rows = sheet.getPhysicalNumberOfRows();
//        for (int i = 0; i < rows; i++) {
//            XSSFRow row = sheet.getRow(i);
//            XSSFCell cell = row.getCell(0);
//            XSSFCell cell2 = row.getCell(1);
//            System.out.println(cell + "    " + cell2);
//        }
        // 延迟周期线程池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(() -> System.out.println("hello world"), 0, 1, TimeUnit.SECONDS);
        // 定时任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        }, 0, 1000);
    }

    @Test
    public void test() {
        Date date = new Date();
        SimpleDateFormat hh = new SimpleDateFormat("mm");
        System.out.println(hh.format(date));


        System.out.println(date.getHours());
    }
    @Test
    public void testAdminLogin(){
        String dateString = "2019-9-22";
    }

}
