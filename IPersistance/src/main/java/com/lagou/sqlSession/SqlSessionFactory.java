package com.lagou.sqlSession;

import com.lagou.utils.ConnectionUtil;
import com.lagou.utils.XmlAnalyseUtil;
import java.sql.ResultSet;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSessionFactory {

    private static final String configPath = "IPersistance/src/main/resources/sqlMapConfig.xml";


    private static Map<String, Map<String,String>> getSqlSessionMap() throws DocumentException {
        String mapperPath = null;
        Map<String, Map<String, String>> mapperMap = new HashMap<>();
        List<Element> configList = XmlAnalyseUtil.getXMLElements(configPath, "property");
        for (Element element : configList) {
            if("sqlMapper".equals(element.attributeValue("name"))) {
                mapperPath = element.attributeValue("value");
            }
        }
        List<Element> mapperList = XmlAnalyseUtil.getXMLElements(mapperPath,"mapper");
        String namespace = mapperList.get(0).attributeValue("namespace");
        mapperList = XmlAnalyseUtil.getXMLElements(mapperPath,"select");
        for (Element element : mapperList) {
            Map<String, String> sqlSessionMap = new HashMap<>();
            sqlSessionMap.put("id",element.attributeValue("id"));
            sqlSessionMap.put("parameterType",element.attributeValue("parameterType"));
            sqlSessionMap.put("resultType",element.attributeValue("resultType"));
            sqlSessionMap.put("sql",element.getStringValue().trim());
            mapperMap.put(namespace + "." + element.attributeValue("id"),sqlSessionMap);
        }
        return mapperMap;
    }

    public static <T> T executeSqlSession(String statementId, T param) throws DocumentException, SQLException, IllegalAccessException {

        Map<String,Map<String, String>> mapperMap = getSqlSessionMap();
        Map<String,String> sqlSessionMap = mapperMap.get(statementId);
        //获取preparedStatement
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlSessionMap.get("sql"));
        //获取参数
        Field[] fields = param.getClass().getDeclaredFields();
        int index = 1;
        for (Field field : fields) {
            field.setAccessible(true);
            ps.setObject(index,field.get(param));
            index++;
        }
        index = 1;
        int i = 0;
        ResultSet rs = ps.executeQuery();
        //映射结果
        while(rs.next()) {
            fields[i].set(param,rs.getObject(index));
        }
        connection.close();
        return param;
    }

}
