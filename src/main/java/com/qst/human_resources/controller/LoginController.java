package com.qst.human_resources.controller;

import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.service.UserService;
import com.qst.human_resources.utils.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 12:57
 */
@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String test(){
        return "login";
    }


    @RequestMapping("/LoginTo")
    private String Login( UserDTO user){

        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        UserDTO user1= service.getUserInfoByUsername(user.getUsername());
        try {
            if(PwdUtil.validatePassword(user.getPassword(),user1.getPassword())){
                return "console";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return "Fail Login";
    }


    @RequestMapping("/createUser")
    @ResponseBody
    public String createUser(UserDTO user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        user.setPassword(PwdUtil.createHash(user.getPassword()));
        if(service.insertUser(user)){
            return "create success";
        }
        return "fail create";
    }


    @RequestMapping("/toCreateUser")
    public String toCreateUser(){
        return "createUser";
    }


}
