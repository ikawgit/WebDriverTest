package com.selenium.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.common.basic.SetUp;
import com.selenium.common.business.Logging;
import com.selenium.init.*;

public class CreateUser extends Before {
	
	private Map<String, String> envdata = new HashMap<String, String>();
	private List<Map<String, String>> accountdatas = new ArrayList<Map<String, String>>();
	private WebDriver wd = SetUp.getWEBDRIVER();
	
	CreateUser(){
		super();
		String filepath = SetUp.getDIR()+"\\src\\test\\java\\data\\CreateUser.xlsx";	
		ReadExcelUtil excelReader = new ReadExcelUtil(filepath);  
		try {
			List<Map<String, String>> envdatas = excelReader.getColTitleData("环境信息",3);
			for (Map<String, String> data :envdatas) {		
				if (data.get("环境名称").equals(SetUp.getTESTENV())) {
					envdata = data;
					break;
				}
			}
			accountdatas = excelReader.getRowTitleData("用户信息");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createMain() {
		wd.get(envdata.get("url"));
		wd.findElement(By.linkText("close")).click();
		
		for (Map<String, String> accountdata : accountdatas) {
			create(accountdata);
		}
		
		wd.quit();
	}
	
	public void create(Map<String, String> accountdata) {

		wd.findElement(By.cssSelector("#loginBox > li.create_now")).click();	
		if (accountdata.get("登录账号") != "") {
			wd.findElement(By.id("account2")).sendKeys(accountdata.get("登录账号"));
		}
		if (accountdata.get("登录密码") != "") {
			wd.findElement(By.id("password")).sendKeys(accountdata.get("登录密码"));
			wd.findElement(By.id("qurenPasswrod")).sendKeys(accountdata.get("登录密码"));
		}
		if (accountdata.get("真实姓名") != "") {
			wd.findElement(By.id("realName")).sendKeys(accountdata.get("真实姓名"));
		}
		if (accountdata.get("昵称") != "") {
			wd.findElement(By.id("nickname")).sendKeys(accountdata.get("昵称"));
		}
		if (accountdata.get("英文姓名") != "") {
			wd.findElement(By.id("englishName")).sendKeys(accountdata.get("英文姓名"));
		}
		if (accountdata.get("生日") != "") {
			wd.findElement(By.id("birthday")).sendKeys(accountdata.get("生日"));
		}
		if (accountdata.get("国家") != "") {
			wd.findElement(By.id("country")).sendKeys(accountdata.get("国家"));
		}
		if (accountdata.get("证件号码") != "") {
			List<WebElement> ids = wd.findElements(By.name("idPassportNoType"));
			if (accountdata.get("证件号码").equals("身份证")) {
				for (WebElement id : ids) {
					if (id.getText().equals("身份证")) {
						id.click();
					}
				}
			}
			else {
				for (WebElement id : ids) {
					if (id.getText().equals("护照")) {
						id.click();
					}
				}
			}
			wd.findElement(By.id("idPassportNo")).sendKeys(accountdata.get("证件号码"));
		}
		if (accountdata.get("手机号码") != "") {
			wd.findElement(By.id("phone")).sendKeys(accountdata.get("手机号码"));
		}
		wd.findElement(By.name("MultiPwd")).click();
		if (accountdata.get("取款密码") != "") {
			wd.findElement(By.id("drawMoneyPwd1")).sendKeys(accountdata.get("取款密码").substring(0, 1));
			wd.findElement(By.id("drawMoneyPwd2")).sendKeys(accountdata.get("取款密码").substring(1, 2));
			wd.findElement(By.id("drawMoneyPwd3")).sendKeys(accountdata.get("取款密码").substring(2, 3));
			wd.findElement(By.id("drawMoneyPwd1")).sendKeys(accountdata.get("取款密码").substring(3, 4));
		}
		else {
			wd.findElement(By.id("drawMoneyPwd1")).sendKeys("0");
			wd.findElement(By.id("drawMoneyPwd2")).sendKeys("0");
			wd.findElement(By.id("drawMoneyPwd3")).sendKeys("0");
			wd.findElement(By.id("drawMoneyPwd1")).sendKeys("0");
		}
		if (accountdata.get("QQ") != "") {
			wd.findElement(By.id("qq")).sendKeys(accountdata.get("QQ"));
		}
		if (accountdata.get("邮箱") != "") {
			wd.findElement(By.id("email")).sendKeys(accountdata.get("邮箱"));
		}
		wd.findElement(By.id("zhuceYm")).sendKeys("0000");
		wd.findElement(By.id("submitInfo")).click();
		Alert alertbox = wd.switchTo().alert();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alertbox.accept();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alertbox.accept();
		
		wd.findElement(By.id("cboxClose")).click();

		Logging logging = new Logging();
		logging.logoutMember();
	}
}
