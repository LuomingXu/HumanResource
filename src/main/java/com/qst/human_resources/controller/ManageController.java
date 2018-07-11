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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//                System.out.println(attendance.toString());
            }
        }

//        if(user==null){
//            List<UserDTO> userLists=userService.get
//        }

        UserDTO userFound = userService.getUserInfoByUsername(user.getUsername());
        //查询为空则直接返回null
        if(userFound==null){
            return userInfoDetailLists;
        }
//        System.out.println(userFound.toString());
//        attendance = attendanceService.getLatestInfoByUsername(user.getUsername());

        UserSalaryDTO userSalary = new UserSalaryDTO();
        userSalary.setUsername(user.getUsername());
        userSalary.setMonth(dateC.getMonth()+1);


        if(salaryService.selectByUserNameIncludeMonth(userSalary).size()>0){
            userSalary =  salaryService.selectByUserNameIncludeMonth(userSalary).get(0);
        }

        UserInfoDetail userInfoDetail = new UserInfoDetail();

        userInfoDetail.setUser(userFound);
        userInfoDetail.setAttendance(attendance);
        userInfoDetail.setUserSalary(userSalary);
        userInfoDetailLists.add(userInfoDetail);

//        System.out.println(userInfoDetail.toString());

        return userInfoDetailLists;
    }


    /**
     * 管理user的salary和出勤情况
     * @param attendance
     * @param userSalary
     * @param dateStr
     * @return
     * @throws ParseException
     */

    @RequestMapping(value = "/updateUserDeInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(AttendanceDTO attendance,
                              UserSalaryDTO userSalary,
                              String dateStr) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        attendance.setDate(date);
        userSalary.setMonth(date.getMonth()+1);

        //若是这个月的薪水已存在则不再进行插入
        if(salaryService.selectByUserNameIncludeMonth(userSalary).size()==0){
            salaryService.insertUserSalary(userSalary);
        }



        if(attendanceService.updateAttendanceInfo(attendance)){
            return "处理成功,请返回上一页面";
        }else {
            return "处理失败,请返回上一页面";
        }
//        System.out.println("date: "+date);
//        System.out.println(userSalary.toString());
//        System.out.println(dateStr);

//        return true;
    }


    @RequestMapping(value = "/createUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public String createUserInfo(AttendanceDTO attendance,
                                  UserSalaryDTO userSalary,
                                  UserDTO user) throws ParseException, InvalidKeySpecException, NoSuchAlgorithmException {

        user.setAuthority("normal");
        //默认密码为123456
        user.setPassword(PwdUtil.createHash("123456"));

        //对于时间的处理
        Date tempDate = attendance.getDate();
        Date date = new Date(tempDate.getYear(), tempDate.getMonth(), tempDate.getDate());
        userSalary.setMonth(attendance.getDate().getMonth()+1);
        attendance.setDate(date);

        //已存在用户则不能插入
        if(!userService.isUserExist(user.getUsername())){
            userService.insertUser(user);
        }

        boolean attendanceInsert = attendanceService.insertAttendanceInfo(attendance);
        boolean salaryInsert = salaryService.insertUserSalary(userSalary);

        if(attendanceInsert&&salaryInsert){
            return "处理成功,请返回上一页面";
        }else {
            return "处理失败,请返回上一页面";
        }
//        return true;
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
