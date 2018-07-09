/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserSalaryMapper.java
 * CreateTime: 2018/07/09 10:42:03
 * LastModifiedDate : 18-7-9 上午10:40
 */

package com.qst.human_resources.mapper;

import com.qst.human_resources.dto.UserSalaryDTO;

public interface UserSalaryMapper
{
    int insertSelective(UserSalaryDTO record);

    UserSalaryDTO selectByPrimaryKey(Integer id);
}