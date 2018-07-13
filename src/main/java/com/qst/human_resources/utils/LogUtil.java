/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : LogUtil.java
 * CreateTime: 2018/07/13 13:38:40
 * LastModifiedDate : 18-7-13 下午1:38
 */

package com.qst.human_resources.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LogUtil
{
    private static String filePath = String.format("%s\\qstLog.md"
            , System.getProperty("user.dir"));

    public static void LogWriteIn(String log)
    {
        File file = new File(filePath);
        BufferedWriter bw = null;
        try
        {
            if (file.exists())
            {
                bw = new BufferedWriter(new FileWriter(file, true));
                bw.newLine();
                bw.write(log);
            }
            else
            {
                if (file.createNewFile())
                {
                    bw = new BufferedWriter(new FileWriter(file, true));
                    bw.newLine();
                    bw.write(log);
                }
                else
                {
                    System.err.println("create file error");
                }
            }
            if (bw != null)
            {
                bw.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
