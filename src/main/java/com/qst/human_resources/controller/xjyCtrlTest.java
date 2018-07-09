/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : xjyCtrlTest.java
 * CreateTime: 2018/07/09 10:51:02
 * LastModifiedDate : 18-7-9 上午10:51
 */

package com.qst.human_resources.controller;

import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.mapper.UserMapper;
import com.qst.human_resources.mapper.UserSalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class xjyCtrlTest
{
    public static void main(String[] args)
    {
        List<Integer> lists = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            lists.add(i + 1);
        }

        System.err.println(lists.size());

        //注意这个地方
        lists.removeIf(integer -> !(integer % 3 == 0));

        for (int item : lists)
        {
            System.out.println(item);
        }
    }

    @Autowired
    private UserMapper mapper;

//    @RequestMapping("/xjy")
//    @ResponseBody
//    public List<UserSalaryDTO> test(){
//        UserSalaryDTO model = new UserSalaryDTO();
//        model.setUsername("123");
//        model.setMonth(1);
//        return mapper.selectByUserNameIncludeMonth(model);
//    }

    @RequestMapping("/xjy1")
    @ResponseBody
    public UserDTO test()
    {
        UserDTO model = new UserDTO();
        model.setUsername("123");
        return mapper.selectByPrimaryKey(model.getUsername());
    }

}
