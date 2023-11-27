package org.example.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadPropertiesFile {


    public static HashMap<String, String> readProperties() {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        HashMap<String, String> map = new HashMap<>();
        try {
            // 加载属性文件
            fileInputStream = new FileInputStream("../common.properties");
            properties.load(fileInputStream);
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                map.put(key, value);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

}
