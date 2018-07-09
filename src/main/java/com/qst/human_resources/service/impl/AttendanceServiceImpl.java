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

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AttendanceServiceImpl implements AttendanceService
{
    @Autowired
    private AttendanceMapper mapper;

    private List<Double> calculateRate(List<AttendanceDTO> models)
    {
        List<Double> rate = new ArrayList<>();

        int count = 0;
        int late = 0;
        int attendance = 0;

        for (AttendanceDTO item : models)
        {
            count++;
            if (item.getIslate() == 1)
            {
                late++;
            }
            if (item.getIsabsenteeism() == 0)
            {
                attendance++;
            }
        }

        if (count == 0)
        {
            return null;
        }

        rate.add((double) (late / count));
        rate.add((double) (attendance / count));

        return rate;
    }

    private void removeModelsIfItIsNotMatchDate
            (List<AttendanceDTO> models, Date date, AttendanceDTO.dateChoice dateChoice)
    {
        SimpleDateFormat sdf;

        switch (dateChoice)
        {
            case year:
                sdf = new SimpleDateFormat("yyyy");
                models.removeIf(item ->
                        !sdf.format(item.getDate()).equals(sdf.format(date))
                );
                break;
            case month:
                sdf = new SimpleDateFormat("yyyy-MM");
                models.removeIf(item ->
                        !sdf.format(item.getDate()).equals(sdf.format(date))
                );
                break;
            case day:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                models.removeIf(item ->
                        !sdf.format(item.getDate()).equals(sdf.format(date))
                );
                break;
        }
    }

    @Override
    public List<Double> getRateByDate(Date date, AttendanceDTO.dateChoice dateChoice)
    {
        List<AttendanceDTO> models = mapper.selectAll();
        List<Double> rate;

        if (models == null)
        {
            System.err.println("can not get data by this date");
            return null;
        }
        else
        {
            removeModelsIfItIsNotMatchDate(models, date, dateChoice);

            rate = calculateRate(models);
        }

        return rate;
    }

    @Override
    public List<Double> getRateByUsername(String username, Date date, AttendanceDTO.dateChoice dateChoice)
    {
        List<AttendanceDTO> models = mapper.selectByUserName(username);
        List<Double> rate;

        if (models == null)
        {
            System.err.println("can not get data by this date");
            return null;
        }
        else
        {
            if (date == null)
            {
                rate = calculateRate(models);
                return rate;
            }

            removeModelsIfItIsNotMatchDate(models, date, dateChoice);

            rate = calculateRate(models);
        }

        return rate;
    }

    @Override
    public AttendanceDTO getLatestInfoByUsername(String username)
    {
        return mapper.selectLatestByUserName(username);
    }

    @Override
    public boolean insertAttendanceInfo(AttendanceDTO record)
    {
        return mapper.insertSelective(record) > 0;
    }
}
