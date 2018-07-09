/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserSalaryServiceImpl.java
 * CreateTime: 2018/07/09 11:53:59
 * LastModifiedDate : 18-7-9 上午11:53
 */

package com.qst.human_resources.service.impl;

import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.mapper.UserSalaryMapper;
import com.qst.human_resources.service.UserSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSalaryServiceImpl implements UserSalaryService
{
    @Autowired
    private UserSalaryMapper mapper;

    @Override
    public List<UserSalaryDTO> selectAllUsersSalaryInfo()
    {
        return mapper.selectAll();
    }

    @Override
    public List<UserSalaryDTO> selectByUserNameIncludeMonth(UserSalaryDTO record)
    {
        return mapper.selectByUserNameIncludeMonth(record);
    }

    @Override
    public boolean addUserSalary(UserSalaryDTO record)
    {
        return mapper.insertSelective(record) > 0;
    }
}
