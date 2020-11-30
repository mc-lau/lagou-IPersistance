package com.lagou.utils;


import org.dom4j.DocumentException;
import org.dom4j.Element;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionUtil {

    private static final String configPath = "IPersistance/src/main/resources/sqlMapConfig.xml";

    public static Connection getConnection() throws DocumentException, SQLException {
        Map<String, String> propertyMap = new HashMap<>();
        List<Element> nodeList = XmlAnalyseUtil.getXMLElements(configPath,"property");
        for (Element element : nodeList) {
            propertyMap.put(element.attributeValue("name"), element.attributeValue("value"));
        }

        return DriverManager.getConnection(propertyMap.get("jdbcUrl"),propertyMap.get("username"),
                propertyMap.get("password"));

    }
}
