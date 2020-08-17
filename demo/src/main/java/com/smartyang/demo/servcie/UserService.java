package com.smartyang.demo.servcie;

import com.smartyang.demo.common.model.User;

import java.util.List;

/**
 *
 * @version V1.0
 * @title: UserService
 * @description:
 * @author: weike
 * @date: 2020/8/17 10:13
 * @remark: 修改内容
 */

public interface UserService {


    Long countUser();

    List<User> getUserListByBatch(Integer startRow, Integer pageSize);

    void saveUser(User user);

    void saveUsers(List<User> users);
}
