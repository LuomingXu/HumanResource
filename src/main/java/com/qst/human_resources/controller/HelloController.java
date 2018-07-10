package com.qst.human_resources.controller;

import com.qst.human_resources.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 09:36
 */
@Controller
public class HelloController {


    @RequestMapping("/")
    public String test(){
        return "console";
    }

    @Test
    public void testJunit(){
        System.out.println("success junit");
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public List<UserDTO> testJquer(){


        List<UserDTO> list = new ArrayList<>();
        UserDTO user1 = new UserDTO();
        UserDTO user2 = new UserDTO();
        user1.setUsername("syun");
        user1.setPassword("123456");

        user2.setUsername("syun2");
        user2.setPassword("569456");
        list.add(user1);
        list.add(user2);

        return list;
    }



}
