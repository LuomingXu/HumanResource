/*
 * Copyright (c) 2018
 * Author : Luoming Xu
 * Project Name : human_resources
 * File Name : UserDTO.java
 * CreateTime: 2018/07/09 10:48:26
 * LastModifiedDate : 18-7-9 上午10:45
 */

package com.qst.human_resources.dto;

import java.util.Date;

public class UserDTO {

    public enum authority
    {
        admin,
        normal
    }
    private String username;

    private String password;

    private String authority;

    private String email;

    private Date createtime;

    private Integer isDel;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", email='" + email + '\'' +
                ", createtime=" + createtime +
                ", isDel=" + isDel +
                '}';
    }
}