/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : xjyCtrlTest.java
 * CreateTime: 2018/07/09 10:51:02
 * LastModifiedDate : 18-7-9 上午10:51
 */

package com.qst.human_resources.controller;

import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.mapper.UserSalaryDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class xjyCtrlTest
{
    @Autowired
    private UserSalaryDTOMapper mapper;

    @RequestMapping("/xjy")
    @ResponseBody
    public UserSalaryDTO test(){
        return mapper.selectByPrimaryKey(1);
    }

}
