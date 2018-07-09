/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserSalaryService.java
 * CreateTime: 2018/07/09 11:55:00
 * LastModifiedDate : 18-7-9 上午11:54
 */

package com.qst.human_resources.service;

import com.qst.human_resources.dto.UserSalaryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserSalaryService
{
    /**
     * 获取所有用户的工资信息
     *
     * @return 用户工资类的list
     */
    List<UserSalaryDTO> selectAllUsersSalaryInfo();

    /**
     * 根据用户名, 可选月份来获取用户的工资信息
     *
     * @param record 用户工资类
     * @return 用户工资类的list
     */
    List<UserSalaryDTO> selectByUserNameIncludeMonth(UserSalaryDTO record);

    /**
     * 想用户工资表中插入用户的工资信息
     *
     * @param record 用户工资类
     * @return 是否插入成功
     */
    boolean insertUserSalary(UserSalaryDTO record);
}
