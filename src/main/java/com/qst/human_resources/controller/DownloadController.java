/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : DownloadController.java
 * CreateTime: 2018/07/12 08:28:22
 * LastModifiedDate : 18-7-12 上午8:28
 */

package com.qst.human_resources.controller;

import com.qst.human_resources.dto.AttendanceDTO;
import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.dto.UserReport;
import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.service.AttendanceService;
import com.qst.human_resources.service.UserSalaryService;
import com.qst.human_resources.service.UserService;
import com.qst.human_resources.utils.ExcelExportUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DownloadController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserSalaryService userSalaryService;

    @Autowired
    private AttendanceService attendanceService;

    @ResponseBody
    @RequestMapping("/downloadExcel")
    public String downloadExcelReport(HttpServletResponse response)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        UserReport userReport;
        List<UserReport> reportLists = new ArrayList<>();
        List<UserDTO> userLists;
        String temp;

        //获取所有用户
        userLists = userService.getAllUsers();

        //获取数据, 创建workbook
        try
        {
            for (UserDTO userDTO : userLists)
            {
                //1-12月
                for (int month = 1; month < 13; month++)
                {
                    userReport = new UserReport(userDTO.getUsername());

                    //设置查询日期, 主要是月份的变化
                    temp = String.format("2018-%s-1", month);
                    Date date = sdf.parse(temp);

                    //获得一个用户在一个月内的考勤率
                    List<Double> rate = attendanceService.getRateByUsername(userDTO.getUsername(), date, AttendanceDTO.dateChoice.month);

                    //如果没有获得到, 直接继续
                    if (rate == null)
                    {
                        continue;
                    }

                    //将获取到的rate保留5位小数
                    userReport.setLate(BigDecimal.valueOf(rate.get(0)).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());
                    userReport.setAttendance(BigDecimal.valueOf(rate.get(1)).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());

                    //从数据库获取salary的数据
                    UserSalaryDTO userSalaryDTO = new UserSalaryDTO();
                    userSalaryDTO.setUsername(userDTO.getUsername());
                    userSalaryDTO.setMonth(month);
                    userSalaryDTO = userSalaryService.selectByUserNameIncludeMonth(userSalaryDTO).get(0);

                    //将获取到的salary信息存入UserReport
                    userReport.setSalary(userSalaryDTO.getSalary());
                    userReport.setMonth(month);
                    userReport.setMail(userDTO.getEmail());

                    //存入list中
                    reportLists.add(userReport);
                }
            }

            //生成excel表
            XSSFWorkbook wb = ExcelExportUtil.exportReport(reportLists);

            System.err.println("responsing...");

            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader(
                    "Content-Disposition",
                    "attachment;fileName=" + "UserReport.xlsx");

            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);

            System.err.println("done");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
