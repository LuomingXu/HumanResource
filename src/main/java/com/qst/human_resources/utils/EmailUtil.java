/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : EmailUtil.java
 * CreateTime: 2018/07/09 11:52:19
 * LastModifiedDate : 18-7-9 上午11:52
 */

package com.qst.human_resources.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
import java.util.Random;

public class EmailUtil
{
    public static String sendVerifyCode(String mailAddress)
    {
        //创建邮件发送服务器
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("xlm46566696@163.com");
        mailSender.setPassword("xlm123");

        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.timeout", 5000);
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.timeout", "5000");
        mailSender.setJavaMailProperties(javaMailProperties);

        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xlm46566696@163.com");
        message.setTo(mailAddress);
        message.setSubject("qst人力资源账户, 忘记密码验证");

        //随机生成一个6位验证码
        Random rd = new Random(1000);
        String number = String.valueOf(rd.nextLong()).substring(1, 7);
        String msg = "验证码为: " + number;
        message.setText(msg);

        //发送邮件
        mailSender.send(message);

        return number;
    }
}
