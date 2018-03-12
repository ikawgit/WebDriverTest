package com.selenium.init;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	
    public static void main(String[] args) throws IOException {  
        Properties properties=new Properties();  
        properties.load(new FileInputStream("D:\\workspace\\WebDriverTest\\src\\test\\java\\data\\config.properties"));  
        System.out.println(properties.getProperty("BROWSER"));  
    }
}
