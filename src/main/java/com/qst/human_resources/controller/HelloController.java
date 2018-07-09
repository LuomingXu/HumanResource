package com.qst.human_resources.controller;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "index";
    }

    @Test
    public void testJunit(){
        System.out.println("success junit");
    }

}
