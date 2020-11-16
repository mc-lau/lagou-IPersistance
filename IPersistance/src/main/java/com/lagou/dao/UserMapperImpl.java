package com.lagou.dao;

import com.lagou.dao.UserMapper;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.utils.XmlAnalyseUtil;
import org.dom4j.Element;

import java.util.List;

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

