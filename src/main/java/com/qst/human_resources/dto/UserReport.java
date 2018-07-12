/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserReport.java
 * CreateTime: 2018/07/11 20:09:30
 * LastModifiedDate : 18-7-11 下午8:09
 */

package com.qst.human_resources.dto;

import java.math.BigDecimal;

public class UserReport
{
    private String username;
    private BigDecimal salary;
    private Integer month;
    private Double late;
    private Double attendance;
    private String mail;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public BigDecimal getSalary()
    {
        return salary;
    }

    public void setSalary(BigDecimal salary)
    {
        this.salary = salary;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Double getLate()
    {
        return late;
    }

    public void setLate(Double late)
    {
        this.late = late;
    }

    public Double getAttendance()
    {
        return attendance;
    }

    public void setAttendance(Double attendance)
    {
        this.attendance = attendance;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @Override
    public String toString()
    {
        return "UserReport{" +
                "username='" + username + '\'' +
                ", salary=" + salary +
                ", month=" + month +
                ", late=" + late +
                ", attendance=" + attendance +
                ", mail='" + mail + '\'' +
                '}';
    }
}
