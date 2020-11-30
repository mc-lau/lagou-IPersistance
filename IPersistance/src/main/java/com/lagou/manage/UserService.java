package com.lagou.manage;

import com.lagou.dao.UserMapper;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSessionFactory;

public class UserService {

    public static void main(String[] args) {
        UserMapper userMapper = SqlSessionFactory.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user = userMapper.getUserListByCondition(user);
        System.out.println(user);
    }
}
