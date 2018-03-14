/*******************************************************************************************************************
类名：DriverFactory
功能: 
参数：
返回：
历史记录： [2018-03-01]Ikaw-������ʼ�汾
'*******************************************************************************************************************/

package com.selenium.common.business;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.selenium.common.basic.SetUp;

public class Logging {
	
	private WebDriver wd = SetUp.getWEBDRIVER();
		
	public void loginControl(String url, String username, String password, String db) {
		if (url.indexOf("http") == -1)
		{
			url = "http://" + url;
		}
		wd.get(url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		wd.findElement(By.xpath("//*[@id=\"platform\"]/option[" + db + "]"));
		wd.findElement(By.id("account")).sendKeys(username);
		wd.findElement(By.id("password")).sendKeys(password);
		wd.findElement(By.xpath("/html/body/map/area[1]")).click();
		wd.findElement(By.id("validCode")).sendKeys("000000");
		wd.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/div[2]/div[2]/input")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try
		{
			Alert alertbox = wd.switchTo().alert();
			alertbox.accept();
			Thread.sleep(6000);
		} catch (Exception e)
		{
		}
	}
	
	public void logoutControl() {
		wd.switchTo().defaultContent();
		wd.switchTo().frame("topFrame");
		try
		{
			wd.findElement(By.linkText("注销登出")).click();
		} catch (Exception e)
		{
			System.out.println("未登录");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alertbox = wd.switchTo().alert();
		alertbox.accept();
	}
	
	public void loginAgent(String url, String username, String password) {	
		if (url.indexOf("http") == -1)
		{
			url = "http://" + url;
		}
		if (url.indexOf("agent") == -1)
		{
			url = url + "/agent";
		}
		
		wd.get(url);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		wd.findElement(By.id("account")).sendKeys(username);
		wd.findElement(By.id("passwd")).sendKeys(password);
		wd.findElement(By.id("rmNum")).sendKeys("0000");
		try
		{
			wd.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[7]/img")).click();
		} catch (Exception e) {
			wd.findElement(By.xpath("//*[@id=\"body\"]/div/div[2]/input[2]")).click();
			}
		try
		{
			wd.findElement(By.linkText("×")).click();
			Thread.sleep(6000);
		} catch (Exception e)
		{
		}	
	}
	
	public void logoutAgent() {
		wd.switchTo().defaultContent();
		wd.switchTo().frame("mbody");
		try
		{
			wd.findElement(By.linkText("登出")).click();
		} catch (Exception e)
		{
			System.out.println("未登录");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alertbox = wd.switchTo().alert();
		alertbox.accept();
	}
	
	public void loginMember(String url, String username, String password) {
		if (url.indexOf("http") == -1)
		{
			url = "http://" + url;
		}
		wd.get(url);
		wd.findElement(By.xpath("//*[@id=\"loginBox\"]/li[1]")).click();
		wd.findElement(By.id("hd_account_tc")).sendKeys(username);
		wd.findElement(By.id("hd_passwd_tc")).sendKeys(password);
		wd.findElement(By.linkText("登录")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wd.findElement(By.linkText("我同意")).click();

	}

	public void logoutMember() {
		
		try
		{
		WebElement btn = wd.findElement(By.xpath("//*[@id=\"loginBox\"]/li[3]/p"));
		Actions action = new Actions(wd);
		action.moveToElement(btn).perform();
		wd.findElement(By.xpath("//*[@id=\"wrap\"]/dl/dd[1]/ul/li[7]")).click();
		} catch (Exception e)
		{
			System.out.println("未登录");
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alert alertbox = wd.switchTo().alert();
		alertbox.accept();
		
	}
	
	public void browserQuit() {
		wd.quit();
	}
}
