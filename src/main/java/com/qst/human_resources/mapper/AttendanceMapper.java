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

public interface AttendanceMapper
{
    int insert(AttendanceDTO record);

    int insertSelective(AttendanceDTO record);

    AttendanceDTO selectByPrimaryKey(Integer id);
}