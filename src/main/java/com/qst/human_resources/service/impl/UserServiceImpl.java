/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserServiceImpl.java
 * CreateTime: 2018/07/09 11:54:02
 * LastModifiedDate : 18-7-9 上午11:54
 */

package com.qst.human_resources.service.impl;

import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.mapper.UserMapper;
import com.qst.human_resources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper mapper;

    @Override
    public boolean isUserExist(String username)
    {
        return mapper.selectByPrimaryKey(username) != null;
    }

    @Override
    public UserDTO getUserInfoByUsername(String username)
    {
        return mapper.selectByPrimaryKey(username);
    }

    @Override
    public boolean insertUser(UserDTO record)
    {
        return mapper.insertSelective(record) > 0;
    }

    @Override
    public boolean updateUser(UserDTO record)
    {
        return mapper.updateByPrimaryKeySelective(record) > 0;
    }
}
