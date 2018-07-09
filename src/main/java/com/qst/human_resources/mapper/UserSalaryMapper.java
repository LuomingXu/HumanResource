/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserSalaryMapper.java
 * CreateTime: 2018/07/09 11:41:30
 * LastModifiedDate : 18-7-9 上午11:40
 */

package com.qst.human_resources.mapper;

import com.qst.human_resources.dto.UserSalaryDTO;

import java.util.List;

public interface UserSalaryMapper
{
    int insertSelective(UserSalaryDTO record);

    List<UserSalaryDTO> selectAll();

    List<UserSalaryDTO> selectByUserNameIncludeMonth(UserSalaryDTO record);
}