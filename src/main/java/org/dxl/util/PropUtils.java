package org.dxl.util;

import java.io.IOException;
import java.util.Properties;

/**
 * * @projectName launcher2
 * * @title PropUtils
 * * @package org.dxl.util
 * * @description  properties文件读取工具 
 * * @author IT_CREAT     
 * * @date  2020 2020/8/31/031 23:41  
 * * @version 1.0
 */
public class PropUtils {
    public static final String FILE_PATH = "/download-setting.properties";

    /**
     * 通过传入的路径及key，获得对应的值
     *
     * @param path 文件路径
     * @param key  键
     * @return 值
     */
    public static String getValue(String path, String key) {
        Properties properties = new Properties();
        try {
            properties.load(PropUtils.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException("File Read Failed...", e);
        }
        return properties.getProperty(key);
    }

    /**
     * 通过key直接获取对应的值
     *
     * @param key 键
     * @return 值
     */
    public static String getValue(String key) {
        Properties properties = new Properties();
        try {
            properties.load(PropUtils.class.getClassLoader().getResourceAsStream(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("File Read Failed...", e);
        }
        return properties.getProperty(key);
    }
}
