package com.lagou.manage;

import com.lagou.dao.UserMapper;
import com.lagou.dao.UserMapperImpl;
import com.lagou.pojo.User;

public class UserService {

    public static void main(String[] args) {
        UserMapper userMapper = new UserMapperImpl();
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user = userMapper.getUserListByCondition(user);
        System.out.println(user);
    }

}
