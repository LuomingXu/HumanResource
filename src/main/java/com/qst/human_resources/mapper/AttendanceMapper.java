/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : AttendanceMapper.java
 * CreateTime: 2018/07/09 10:31:45
 * LastModifiedDate : 18-7-9 上午10:31
 */

package com.qst.human_resources.mapper;

import com.qst.human_resources.dto.AttendanceDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapper
{
    int insertSelective(AttendanceDTO record);

    List<AttendanceDTO> selectAll();

    List<AttendanceDTO> selectByUserName(@Param("username")String username);

    AttendanceDTO selectLatestByUserName(@Param("username") String username);

    List<AttendanceDTO> selectAllByDate(@Param("date")Date date);

    int updateByUsernameAndDate(AttendanceDTO record);
}