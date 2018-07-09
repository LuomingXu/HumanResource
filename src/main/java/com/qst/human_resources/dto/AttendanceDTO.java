/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : AttendanceDTO.java
 * CreateTime: 2018/07/09 10:32:07
 * LastModifiedDate : 18-7-9 上午10:31
 */

package com.qst.human_resources.dto;

import java.util.Date;

public class AttendanceDTO
{
    public enum dateChoice
    {
        year,
        month,
        day
    }

    private Integer id;

    private String username;

    private Date date;

    private Integer islate;

    private Integer isabsenteeism;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIslate() {
        return islate;
    }

    public void setIslate(Integer islate) {
        this.islate = islate;
    }

    public Integer getIsabsenteeism() {
        return isabsenteeism;
    }

    public void setIsabsenteeism(Integer isabsenteeism) {
        this.isabsenteeism = isabsenteeism;
    }

    @Override
    public String toString()
    {
        return "AttendanceDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", islate=" + islate +
                ", isabsenteeism=" + isabsenteeism +
                '}';
    }
}