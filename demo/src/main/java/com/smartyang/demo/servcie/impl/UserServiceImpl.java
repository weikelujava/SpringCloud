package com.smartyang.demo.servcie.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smartyang.demo.common.model.User;
import com.smartyang.demo.mapper.UserMapper;
import com.smartyang.demo.servcie.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @version V1.0
 * @title: UserServiceImpl
 * @description:
 * @author: weike
 * @date: 2020/8/17 10:13
 * @remark: 修改内容
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Long countUser() {
        return baseMapper.countUser();
    }

    @Override
    public List<User> getUserListByBatch(Integer startRow, Integer pageSize) {
        return baseMapper.getUserListByBatch(startRow,pageSize);
    }

    @Override
    public void saveUser(User user) {
        baseMapper.insertUser(user);
    }

    @Override
    public void saveUsers(List<User> users) {
        baseMapper.insertUsers(users);
    }
}
