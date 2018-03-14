package com.selenium.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.selenium.common.basic.SetUp;
import com.selenium.common.basic.DriverFactory;

public class Before {
	
	protected Before() {
		
		//设置项目路径
		File directory = new File("");
		try {
			SetUp.setDIR(directory.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
        Properties properties=new Properties();  
        try {
			properties.load(new FileInputStream(SetUp.getDIR()+"\\src\\test\\java\\data\\config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SetUp.setTESTENV(properties.getProperty("TESTENV"));
        SetUp.setBROWSER(properties.getProperty("BROWSER"));
        
        DriverFactory df = new DriverFactory();
        SetUp.setWEBDRIVER(df.createDriver(properties.getProperty("NODE"), SetUp.getBROWSER()));
        //SetUp.setWEBDRIVER(df.createDriver());
        
	}
	
}
