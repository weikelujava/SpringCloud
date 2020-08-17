package com.smartyang.demo.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 *
 * @version V1.0
 * @title: User
 * @description:
 * @author: weike
 * @date: 2020/8/17 10:14
 * @remark: 修改内容
 */
@TableName(value = "user")
public class User implements Serializable {


    private static final long serialVersionUID = -2499321342733070943L;
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long id;
    private String userName;
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
