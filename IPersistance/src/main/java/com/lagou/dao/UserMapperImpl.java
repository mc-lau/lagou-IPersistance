package com.lagou.dao;

import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSessionFactory;

public class UserMapperImpl implements UserMapper {

    private final static String statementId = "user.selectUser";

    @Override
    public User getUserListByCondition(User user) {
        try {
            user = SqlSessionFactory.executeSqlSession(statementId, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}

