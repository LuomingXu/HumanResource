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

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 16:35
 */
@Controller
@SuppressWarnings({"all"})
public class ManageController {

    //用户基本表Service操作
    @Autowired
    private UserService userService;

    //考勤信息Service操作
    @Autowired
    private AttendanceService attendanceService;

    //用户薪水Service操作
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

        List<UserInfoDetail> userInfoDetailLists = new ArrayList<>();
        List<UserDTO> userLists = new ArrayList<>();
        List<AttendanceDTO> attendanceListBydate = attendanceMapper.selectAllByDate(dateC);

        AttendanceDTO attendance = new AttendanceDTO();

        for (AttendanceDTO temp : attendanceListBydate) {
            if(temp.getUsername().equals(user.getUsername())){
                attendance = temp;
            }
        }

        UserDTO userFound = userService.getUserInfoByUsername(user.getUsername());
        //查询为空则直接返回null
        if(userFound==null){
            return userInfoDetailLists;
        }

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
                             String dateStr,
                             HttpServletRequest request) throws ParseException {


        UserDTO presentUser = (UserDTO) request.getSession().getAttribute("user");

        if(presentUser!=null){
            presentUser=userService.getUserInfoByUsername(presentUser.getUsername());
            if(presentUser.getAuthority().equals("normal")){
                return "你不是管理员,无权操作";
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        attendance.setDate(date);
        userSalary.setMonth(date.getMonth()+1);

        //若是这个月的薪水已存在则不再进行插入
        if(salaryService.selectByUserNameIncludeMonth(userSalary).size()==0){
            salaryService.insertUserSalary(userSalary);
        }

        //返回给页面的信息
        if(attendanceService.updateAttendanceInfo(attendance)){
            return "处理成功,请返回上一页面";
        }else {
            return "处理失败,请返回上一页面";
        }


    }

    /**
     * 管理员进行用户的信息创建
     * @param attendance
     * @param userSalary
     * @param user
     * @param request
     * @return
     * @throws ParseException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    @RequestMapping(value = "/createUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public String createUserInfo(AttendanceDTO attendance,
                                  UserSalaryDTO userSalary,
                                  UserDTO user,
                                 HttpServletRequest request) throws ParseException, InvalidKeySpecException, NoSuchAlgorithmException {

        UserDTO presentUser = (UserDTO) request.getSession().getAttribute("user");

        if(presentUser!=null){
            presentUser=userService.getUserInfoByUsername(presentUser.getUsername());
            if(presentUser.getAuthority().equals("normal")){
                return "你不是管理员,无权操作";
            }
        }

        user.setAuthority("normal");
        //默认密码为123456
        user.setPassword(PwdUtil.createHash("123456"));

        //对于时间的处理
        Date tempDate = attendance.getDate();
        Date date = new Date(tempDate.getYear(), tempDate.getMonth(), tempDate.getDate());
        userSalary.setMonth(attendance.getDate().getMonth()+1);
        attendance.setDate(date);

        //已存在用户则不能插入,不采用异常处理
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
    }


    /**
     * 得到出勤信息
     * @param username
     * @param dateStr
     * @return
     * @throws ParseException
     */
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


    /**
     * 退出当前用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/shutdown",method = RequestMethod.POST)
    public Map<String, Object> shutdown(HttpServletRequest request){

        Map<String, Object> map = new HashMap<>();
        request.getSession().removeAttribute("user");
        map.put("result", "1");
        return map;
    }







}
