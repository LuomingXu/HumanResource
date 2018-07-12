/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : ExcelExportUtil.java
 * CreateTime: 2018/07/11 20:03:21
 * LastModifiedDate : 18-7-11 下午8:44
 */

package com.qst.human_resources.utils;

import com.qst.human_resources.dto.UserReport;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;

public class ExcelExportUtil
{
    /**
     * 生成需要的xlsx模板
     *
     * @param param 想要生成的模板里的列名
     * @return xlsx表
     */
    public static XSSFWorkbook exportReport(List<UserReport> param) throws Exception
    {
        if (param.size() < 1)
        {
            throw new Exception("param 参数的size不可小于1");
        }

        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();

        //创建样式
        XSSFCellStyle style = workBook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //创建字体
        XSSFFont font = workBook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);

        //创建工作表
        XSSFSheet sheet = workBook.createSheet("Your Table");

        //创建第一行
        XSSFRow row = sheet.createRow(0);

        System.err.println("start excel");
        //生成模板
        int i = 0;
        XSSFCell cell = row.createCell(i++);
        cell.setCellValue("用户名");
        cell = row.createCell(i++);
        cell.setCellValue("月份");
        cell = row.createCell(i++);
        cell.setCellValue("工资");
        cell = row.createCell(i++);
        cell.setCellValue("迟到率");
        cell = row.createCell(i++);
        cell.setCellValue("出勤率");
        cell = row.createCell(i);
        cell.setCellValue("邮箱");

        i = 1;
        for (UserReport item : param)
        {
            row = sheet.createRow(i++);
            int j = 0;
            cell=row.createCell(j++);
            cell.setCellValue(item.getUsername());
            cell = row.createCell(j++);
            cell.setCellValue(item.getMonth());
            cell = row.createCell(j++);
            cell.setCellValue(item.getSalary().doubleValue());
            cell = row.createCell(j++);
            cell.setCellValue(item.getLate()*100+"%");
            cell = row.createCell(j++);
            cell.setCellValue(item.getAttendance()*100+"%");
            cell = row.createCell(j);
            cell.setCellValue(item.getMail());

            cell.setCellStyle(style);
            int width = 30;
            sheet.setColumnWidth(i++, 256 * width + 184);
        }

        return workBook;
    }
}
