/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : xjyCtrlTest.java
 * CreateTime: 2018/07/09 10:51:02
 * LastModifiedDate : 18-7-9 上午10:51
 */

package com.qst.human_resources.controller;

import com.qst.human_resources.dto.AttendanceDTO;
import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.mapper.UserMapper;
import com.qst.human_resources.mapper.UserSalaryMapper;
import com.qst.human_resources.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class xjyCtrlTest
{
//    private static void remove(List<Integer> lists)
//    {
//        lists.removeIf(item -> item % 3 == 0);
//    }
//
//    public static void main(String[] args)
//    {
//        List<Integer> lists = new ArrayList<>();
//        for (int i = 0; i < 100; i++)
//        {
//            lists.add(i + 1);
//        }
//
//        System.err.println(lists.size());
//
//        remove(lists);
//
//        System.out.println(lists.size());
//
//
//        UserDTO user = new UserDTO();
//        user.setAuthority(UserDTO.authority.admin.toString());
//
//    }

    @Autowired
    private AttendanceService service;

//    @RequestMapping("/xjy")
//    @ResponseBody
//    public List<UserSalaryDTO> test(){
//        UserSalaryDTO model = new UserSalaryDTO();
//        model.setUsername("123");
//        model.setMonth(1);
//        return mapper.selectByUserNameIncludeMonth(model);
//    }

    @RequestMapping("/xjy")
    @ResponseBody
    public List<Double> test()
    {
        List<Double> temp = new ArrayList<>();

        AttendanceDTO model = new AttendanceDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(2018,8,9);
        Date date = cal.getTime();
        System.err.println(date);
        sdf.format(date);
        System.err.println(date);

        if (service.getRateByDate(date,AttendanceDTO.dateChoice.month) == null)
        {
            temp.add(-1.0);
            temp.add(-1.0);

            return temp;
        }
        return service.getRateByDate(date,AttendanceDTO.dateChoice.month);
    }

}
