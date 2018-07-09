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

@Service
public interface AttendanceService
{
    /**
     * 获取最新的迟到出勤数据
     *
     * @param username 用户名
     * @return 出勤表类
     */
    AttendanceDTO getLatestInfoByUsername(String username);

    /**
     * 获取一个用户所有时间的迟到率
     *
     * @param username 用户名
     * @return 迟到率
     */
    double getRateOfLateByUsername(String username);

    double getRateOfLateByUsername(String username, int year);

    double getRateOfLateByUsername(String username, int year, int month);

    /**
     * 获取一个用户所有时间的出勤率
     *
     * @param username 用户名
     * @return 出勤率
     */
    double getRateOfAttendanceByUsernmae(String username);

    double getRateOfAttendanceByUsernmae(String username, int year);

    double getRateOfAttendanceByUsernmae(String username, int year, int month);

}
