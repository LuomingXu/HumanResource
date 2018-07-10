package com.qst.human_resources.handles;

import org.apache.catalina.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 13:55
 */


public class LoginInterceptor implements HandlerInterceptor {
    Logger log = Logger.getLogger(String.valueOf(LoginInterceptor.class));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        boolean flag=true;
        User user = (User) request.getSession().getAttribute("user");

        if(null==user){
            response.sendRedirect("login");
            flag = false;
        }else {
            System.out.println(log.getName()+user.getUsername());
            flag = true;
        }

        return flag;
    }




}
