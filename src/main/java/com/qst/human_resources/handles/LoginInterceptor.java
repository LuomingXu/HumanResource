package com.qst.human_resources.handles;

//import org.apache.catalina.User;
import com.qst.human_resources.dto.UserDTO;
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
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        System.out.println("preHandle");
        if(null==user){
            System.out.println("用户不存在");
            response.sendRedirect("toLogin");
//            System.out.println(log.getName()+user.getUsername());
            flag = false;
        }else {
            System.out.println("用户存在");
            System.out.println(log.getName()+user.getUsername());
            flag = true;
        }

        return flag;
    }




}
