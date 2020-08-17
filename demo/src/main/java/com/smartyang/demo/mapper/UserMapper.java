package com.smartyang.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartyang.demo.common.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @version V1.0
 * @title: UserMapper
 * @description:
 * @author: weike
 * @date: 2020/8/17 10:16
 * @remark: 修改内容
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUserListByBatch(@Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    void insertUser(User user);

    Long countUser();

    void insertUsers(@Param("users") List<User> users);
}
