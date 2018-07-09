/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserService.java
 * CreateTime: 2018/07/09 11:55:18
 * LastModifiedDate : 18-7-9 上午11:55
 */

package com.qst.human_resources.service;

import com.qst.human_resources.dto.UserDTO;

public interface UserService
{
    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return 用户存在则返回true
     */
    boolean isUserExist(String username);

    /**
     * 通过用户名获取用户类
     *
     * @param username 用户名
     * @return 用户类
     */
    UserDTO getUserInfoByUsername(String username);

    /**
     * 向数据库中插入用户登录数据
     *
     * @param record 用户类
     * @return 更新成功则返回true
     */
    boolean insertUser(UserDTO record);

    /**
     * 更新用户登录信息
     *
     * @param record 用户类
     * @return 插入成功则返回true
     */
    boolean updateUser(UserDTO record);
}
