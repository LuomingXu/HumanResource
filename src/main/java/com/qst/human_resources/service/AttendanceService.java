/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : AttendanceService.java
 * CreateTime: 2018/07/09 11:54:47
 * LastModifiedDate : 18-7-9 上午11:54
 */

package com.qst.human_resources.service;

import com.qst.human_resources.dto.AttendanceDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AttendanceService
{
    /**
     * 根据日期来获得这个日期的迟到, 出勤率
     *
     * @param date 日期
     * @return 0-迟到率, 1-出勤率
     */
    List<Double> getRateByDate(Date date);

    /**
     * 获取迟到率, 出勤率
     * 如果没有传入date, 则传回这个用户的整个迟到, 出勤率
     *
     * @param username 用户名
     * @param date 日期
     * @return 0-迟到率, 1-出勤率
     */
    List<Double> getRateByUsername(String username, Date date);

    /**
     * 获取最新的迟到, 出勤数据
     *
     * @param username 用户名
     * @return 出勤表类
     */
    AttendanceDTO getLatestInfoByUsername(String username);
}
