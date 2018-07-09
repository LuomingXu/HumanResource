/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserSalaryDTO.java
 * CreateTime: 2018/07/09 10:40:53
 * LastModifiedDate : 18-7-9 上午10:40
 */

package com.qst.human_resources.dto;

import java.math.BigDecimal;

public class UserSalaryDTO {
    private Integer id;

    private String username;

    private String month;

    private BigDecimal salary;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "UserSalaryDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", month='" + month + '\'' +
                ", salary=" + salary +
                '}';
    }
}