package com.qst.human_resources.controller;

import com.qst.human_resources.dto.AttendanceDTO;
import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.dto.UserInfoDetail;
import com.qst.human_resources.dto.UserSalaryDTO;
import com.qst.human_resources.mapper.AttendanceMapper;
import com.qst.human_resources.service.AttendanceService;
import com.qst.human_resources.service.UserSalaryService;
import com.qst.human_resources.service.UserService;
import com.qst.human_resources.utils.PwdUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 16:35
 */
@Controller
@SuppressWarnings({"all"})
public class ManageController {


    @Autowired
    private UserService userService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserSalaryService salaryService;

    @Autowired
    private AttendanceMapper attendanceMapper;


    /**
     * 进行查询,根据日期和username
     * @return
     */
    @RequestMapping(value = "/searchInfo",method = RequestMethod.POST)
    @ResponseBody
    public List<UserInfoDetail> testJquer(UserDTO user, String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateC = dateFormat.parse(date);
//        System.out.println(user.toString()+date);

        List<UserInfoDetail> userInfoDetailLists = new ArrayList<>();

        List<UserDTO> userLists = new ArrayList<>();

        List<AttendanceDTO> attendanceListBydate = attendanceMapper.selectAllByDate(dateC);

        AttendanceDTO attendance = new AttendanceDTO();

        for (AttendanceDTO temp : attendanceListBydate) {
            if(temp.getUsername().equals(user.getUsername())){
                attendance = temp;
                System.out.println(attendance.toString());
            }
        }

//        if(user==null){
//            List<UserDTO> userLists=userService.get
//        }

        UserDTO userFound = userService.getUserInfoByUsername(user.getUsername());
//        attendance = attendanceService.getLatestInfoByUsername(user.getUsername());

        UserSalaryDTO userSalary = new UserSalaryDTO();
        userSalary.setUsername(user.getUsername());
//        userSalary.setMonth(date.getMonth());


        if(salaryService.selectByUserNameIncludeMonth(userSalary).size()>0){
            userSalary =  salaryService.selectByUserNameIncludeMonth(userSalary).get(0);
        }


        UserInfoDetail userInfoDetail = new UserInfoDetail();

        userInfoDetail.setUser(userFound);
        userInfoDetail.setAttendance(attendance);
        userInfoDetail.setUserSalary(userSalary);
        userInfoDetailLists.add(userInfoDetail);

        System.out.println(userInfoDetail.toString());
        return userInfoDetailLists;
    }


    @RequestMapping(value = "/updateUserDeInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateUser(AttendanceDTO attendance,
                              UserSalaryDTO userSalary,
                              String dateStr) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        attendance.setDate(date);
        userSalary.setMonth(date.getMonth()+1);


        attendanceService.updateAttendanceInfo(attendance);
        salaryService.insertUserSalary(userSalary);



//        System.out.println("date: "+date);
        System.out.println(attendance.toString());
//        System.out.println(userSalary.toString());
//        System.out.println(dateStr);

        return true;
    }


    @RequestMapping(value = "/createUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public boolean createUserInfo(AttendanceDTO attendance,
                                  UserSalaryDTO userSalary,
                                  UserDTO user) throws ParseException, InvalidKeySpecException, NoSuchAlgorithmException {

        user.setAuthority("normal");
        //默认密码为123456
        user.setPassword(PwdUtil.createHash("123456"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dateFormat.parse(dateStr);
//        attendance.setDate(date);
        userSalary.setMonth(attendance.getDate().getMonth()+1);
        userService.insertUser(user);
        attendanceService.insertAttendanceInfo(attendance);
        salaryService.insertUserSalary(userSalary);
        return true;
    }


    @RequestMapping(value = "/getWork",method = RequestMethod.POST)
    @ResponseBody
    public List<Double> getWork(String username,
                                @RequestParam(required = false) String dateStr) throws ParseException {

        System.out.print(username + "  " + dateStr);
        System.out.print("text");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        return attendanceService.getRateByUsername(username, date, AttendanceDTO.dateChoice.month);

    }






}
