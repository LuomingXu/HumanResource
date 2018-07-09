/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : AttendanceServiceImpl.java
 * CreateTime: 2018/07/09 11:53:56
 * LastModifiedDate : 18-7-9 上午11:53
 */

package com.qst.human_resources.service.impl;

import com.qst.human_resources.dto.AttendanceDTO;
import com.qst.human_resources.mapper.AttendanceMapper;
import com.qst.human_resources.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService
{
    @Autowired
    private AttendanceMapper mapper;

    @Override
    public AttendanceDTO getLatestInfoByUsername(String username)
    {
        return mapper.selectLatestByUserName(username);
    }

    @Override
    public double getRateOfLateByUsername(String username)
    {
        List<AttendanceDTO> models = mapper.selectByUserName(username);
        int count = 0;
        int late = 0;

        for (AttendanceDTO item : models)
        {
            count++;
            if (item.getIslate() == 1)
            {
                late++;
            }
        }
        return late / count;
    }

    @Override
    public double getRateOfLateByUsername(String username, int year)
    {
        return 0;
    }

    @Override
    public double getRateOfLateByUsername(String username, int year, int month)
    {
        return 0;
    }

    @Override
    public double getRateOfAttendanceByUsernmae(String username)
    {
        return 0;
    }

    @Override
    public double getRateOfAttendanceByUsernmae(String username, int year)
    {
        return 0;
    }

    @Override
    public double getRateOfAttendanceByUsernmae(String username, int year, int month)
    {
        return 0;
    }
}
