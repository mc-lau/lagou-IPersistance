package com.lagou.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;

public class XmlAnalyseUtil {

    public static List<Element> getXMLElements(String configPath, String node) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(configPath);
        Element root = document.getRootElement();
        return root.selectNodes("//" + node);
    }
}
