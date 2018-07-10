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
     * 即, 通过年月日来过滤获取到的dto, 计算出符合前台
     * 要求的迟到出勤率
     *
     * @param date       日期
     * @param dateChoice 选择年, 月, 日
     * @return 返回的list中index-0是迟到率, index-1是出勤率
     */
    List<Double> getRateByDate(Date date, AttendanceDTO.dateChoice dateChoice);

    /**
     * 根据日期来获得这个日期的迟到, 出勤率
     * 即, 通过年月日来过滤获取到的dto, 计算出符合前台
     * 要求的迟到出勤率
     *
     * @param username   用户名
     * @param date       日期
     * @param dateChoice 选择年, 月, 日
     * @return 返回的list中index-0是迟到率, index-1是出勤率
     */
    List<Double> getRateByUsername(String username, Date date, AttendanceDTO.dateChoice dateChoice);

    /**
     * 获取最新的迟到, 出勤数据
     *
     * @param username 用户名
     * @return 出勤表类
     */
    AttendanceDTO getLatestInfoByUsername(String username);

    boolean insertAttendanceInfo(AttendanceDTO record);

    /**
     * 根据用户名和日期来更新用户的考勤信息
     *
     * @param record 必须包含用户名和日期信息
     * @return 更新是否成功
     */
    boolean updateAttendanceInfo(AttendanceDTO record);
}
