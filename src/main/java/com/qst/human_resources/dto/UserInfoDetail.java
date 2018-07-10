package com.qst.human_resources.dto;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 16:26
 */
public class UserInfoDetail {

    private UserDTO user;

    private UserSalaryDTO userSalary;

    private AttendanceDTO attendance;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserSalaryDTO getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(UserSalaryDTO userSalary) {
        this.userSalary = userSalary;
    }

    public AttendanceDTO getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceDTO attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "UserInfoDetail{" +
                "user=" + user +
                ", userSalary=" + userSalary +
                ", attendance=" + attendance +
                '}';
    }
}
