/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserDTOMapper.java
 * CreateTime: 2018/07/09 10:47:05
 * LastModifiedDate : 18-7-9 上午10:45
 */

package com.qst.human_resources.mapper;

import com.qst.human_resources.dto.UserDTO;

public interface UserDTOMapper
{
    int insertSelective(UserDTO record);

    UserDTO selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(UserDTO record);
}