/*******************************************************************************************************************
������DriverFactory
����: ��ȡ�����ļ������������ļ���ʼ����Ӧ�������
����: 
����ֵ: Driver
��ʷ��¼: [2018-03-01]Ikaw-������ʼ�汾
'*******************************************************************************************************************/

package com.selenium.common.basic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {
	
	private WebDriver driver;
	
	public WebDriver createDriver(String nodeURL, String Broswer) {

		DesiredCapabilities capability;
        if(Broswer.toLowerCase().equals("chrome")){
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            //capability.setVersion("62");
       }else if (Broswer.toLowerCase().equals("firefox")) {
            capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
       }else {
            capability = DesiredCapabilities.internetExplorer();
           // 避免IE安全设置里，各个域的安全级别不一致导致的错误  
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
       }
      
       capability.setPlatform(Platform.WINDOWS);
       nodeURL = nodeURL+"/wd/hub";
       try {
           this.driver = new RemoteWebDriver(new URL(nodeURL), capability);
       } catch (MalformedURLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       
       this.driver.manage().window().maximize();
       this.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       return this.driver;      
	}
	
	public WebDriver createDriver(String Broswer) {
		
		if(Broswer.toLowerCase().equals("chrome")){
			System.setProperty("webdriver.chrome.driver", SetUp.getDIR()+"\\src\\test\\java\\tools\\drivers\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}else if (Broswer.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", SetUp.getDIR()+"\\src\\test\\java\\tools\\drivers\\geckodriver.exe");
			this.driver = new FirefoxDriver();
		}else {
			System.setProperty("webdriver.ie.driver", SetUp.getDIR()+"\\src\\test\\java\\tools\\drivers\\IEDriverServer.exe");
		}
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return this.driver;
	}
	
	public WebDriver createDriver() {
		this.driver = this.createDriver("chrome");
		return this.driver;
	}
	
	public void quit() {
		driver.quit();
	}
}
