package com.circleboom.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * reads the properties file prod_configuration.properties
 */
public class ConfigurationReader {
    private static Properties properties;

    static {

        try {
            String configFileName = "./%s_configuration.properties"; //mvn clean test -DTestEnvironment=staging -DScreenSize=1280_720 or mvn clean test -DTestEnvironment=test -DScreenSize=1280_720
            String EnvironmentName = System.getProperty("TestEnvironment");
            configFileName = String.format(configFileName, EnvironmentName);
            FileInputStream fileInputStream = new FileInputStream(new File(configFileName));
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }
}