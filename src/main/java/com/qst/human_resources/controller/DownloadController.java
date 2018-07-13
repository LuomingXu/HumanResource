/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : DownloadController.java
 * CreateTime: 2018/07/12 08:28:22
 * LastModifiedDate : 18-7-12 上午8:28
 */

package com.qst.human_resources.controller;

import com.qst.human_resources.utils.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class DownloadController
{
    @ResponseBody
    @RequestMapping("/downloadExcel")
    public String downloadExcelReport(HttpServletResponse response)
    {
        LogUtil.LogWriteIn("start download excel...");
        String filePath = String.format("%s\\UserReport.xlsx"
                , System.getProperty("user.dir"));

        try
        {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader(
                    "Content-Disposition",
                    "attachment;fileName=" + "UserReport.xlsx");

            OutputStream os = response.getOutputStream();

            byte[] buffer = new byte[2048];

            try (FileInputStream fis = new FileInputStream(filePath);
                 BufferedInputStream bis = new BufferedInputStream(fis))
            {
                int i = bis.read(buffer);
                while (i != -1)
                {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.err.println("buffer done");
                LogUtil.LogWriteIn("buffer donw");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("excel done");
        LogUtil.LogWriteIn("excel done");

        return null;
    }
}
