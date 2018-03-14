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
import com.selenium.init.Before;
import com.selenium.init.ReadExcelUtil;
import com.selenium.common.business.Logging;

public class AddMerchants extends Before {
	
	private Map<String, String> envdata = new HashMap<String, String>();
	private List<Map<String, String>> merdatas = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> adddatas = new ArrayList<Map<String, String>>();
	private WebDriver wd = SetUp.getWEBDRIVER();
		
	AddMerchants() {
		super();
		String filepath = SetUp.getDIR()+"\\src\\test\\java\\data\\addMerchants.xlsx";
		ReadExcelUtil excelReader = new ReadExcelUtil(filepath);  
		try {
			List<Map<String, String>> envdatas = excelReader.getColTitleData("环境信息",7);
			for (Map<String, String> data :envdatas) {		
				if (data.get("环境名称").equals(SetUp.getTESTENV())) {
					envdata = data;
					break;
				}
			}
			merdatas = excelReader.getRowTitleData("商户信息");
			adddatas = excelReader.getRowTitleData("商户添加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMain(){
		Logging logging = new Logging();
		logging.loginAgent(envdata.get("url"), envdata.get("username"), envdata.get("password"));
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Map<String, String> adddata :adddatas) {
			if (adddata.get("是否添加").equals("是")) {
				for (Map<String, String> merdata :merdatas) {
					if (merdata.get("商号名称").equals(adddata.get("添加商号"))) {
						Map<String, String> testdata = new HashMap<String, String>();
						testdata.putAll(adddata);
						testdata.putAll(merdata);	
						String addstatus = add(testdata);
						if (addstatus.equals("success")) {
							System.out.println("添加成功");
						}
						else {
							System.out.println(addstatus);
						}
					}
				}				
			}			
		}
			
		logging.logoutAgent();
		logging.browserQuit();
	}

	public String add (Map<String, String> data) {
		wd.switchTo().defaultContent();
		wd.switchTo().frame("mbody");
		wd.findElement(By.linkText("现金系统")).click();
		wd.findElement(By.linkText("支付平台设定")).click();
		
		wd.switchTo().defaultContent();
		wd.switchTo().frame("bottomFrame");
		wd.findElement(By.id("bankTypeText")).clear();
		wd.findElement(By.id("bankTypeText")).click();
		WebElement bdropdownlist = wd.findElement(By.name("bankType"));
		List<WebElement> lis = bdropdownlist.findElements(By.cssSelector("li"));
		boolean isfoundli = false;
		
		for (WebElement li :lis) {
			if (li.getText().equals(data.get("支付方式"))) {
				li.click();
				isfoundli = true;
				break;
			}
		}
		if (!isfoundli) {
			return "没有找到支付平台";
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		wd.findElement(By.id("addMerId")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		wd.findElement(By.id("paysystem_id1Text")).clear();
		wd.findElement(By.id("paysystem_id1Text")).click();
		WebElement pdropdownlist = wd.findElement(By.name("paysystem_id1"));
		List<WebElement> mers = pdropdownlist.findElements(By.cssSelector("li"));
		boolean isfoundmer = false;
		for (WebElement mer :mers) {
			if ((mer.getText().indexOf(data.get("商号名称")) != -1) && (mer.getText().indexOf("payment") != -1)) {
				mer.click();
				isfoundmer = true;
				break;
			}
		}
		if (!isfoundmer) {
			return "没有找到支付方式";
		}
		
		wd.findElement(By.id("merchantnoname_id1")).sendKeys(data.get("商号名称") + data.get("支付方式") + envdata.get("suffix"));
		wd.findElement(By.id("merchantno_id1")).sendKeys(data.get("商号"));
		wd.findElement(By.id("merchantkey_id1")).sendKeys(data.get("密钥"));
		wd.findElement(By.id("merchant_console1")).sendKeys(data.get("终端号"));
		wd.findElement(By.id("domain_pay1")).sendKeys(envdata.get("paydomain"));
		
		//层级(如果有全选就选全选，否则选未分层)
		try {
			wd.findElement(By.id("adSelectAll")).click();
		} catch (Exception e) {
			List<WebElement> checkboxs = wd.findElements(By.name("adLevel"));
			checkboxs.get(0).click();
		}
		
		//停用金
		wd.findElement(By.id("input_maxsum_id1")).sendKeys("999");
		
		//手机端展示
		try {
			wd.findElement(By.id("isMobileShow1")).click();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		//最高限额
		try {
			WebElement maxlimit = wd.findElement(By.id("quota1"));
			maxlimit.clear();
			maxlimit.sendKeys("999999999");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		wd.findElement(By.xpath("/html/body/div[8]/div[3]/div/button[1]/span")).click();
		Alert alertbox1 = wd.switchTo().alert();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alertbox1.accept();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (alertbox1.getText().equals("添加成功!")) {
			alertbox1.accept();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wd.findElement(By.linkText("停用")).click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<WebElement> enbs = wd.findElements(By.xpath("//*[@id=\"searchForm\"]/table/tbody/tr[*]/td[2]"));
			for (int i=0; i < enbs.size(); i++) {
				WebElement enb = enbs.get(enbs.size()-1-i);
				if (enb.getText().indexOf(data.get("商号名称") + data.get("支付方式")) != -1) {
					wd.findElement(By.xpath("//*[@id=\"searchForm\"]/table/tbody/tr[" + ((enbs.size()-i) + "") + "]/td[7]/a[1]")).click();
					wd.findElement(By.className("ui-button-text")).click();
					Alert alertbox2 = wd.switchTo().alert();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					alertbox2.accept();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					alertbox2.accept();
					break;
				}
			}
			String listtable = "银行列表点卡列表手机支付列表";
			if (listtable.indexOf(data.get("支付方式")) != -1) {
				wd.findElement(By.id("merchantNo_id")).click();
				WebElement merline = wd.findElement(By.id("MerchantBAN1_id"));
				List<WebElement> merths = merline.findElements(By.tagName("th"));
				for (int j=0; j < enbs.size(); j++) {
					WebElement merth = merths.get(merths.size()-1-j);
					if (merth.getText().equals(data.get("商号名称") + data.get("支付方式"))) {
						wd.findElement(By.xpath("//*[@id=\"myMt_id\"]/tr[1]/td[" + ((merths.size()-j) + "") + "]/a[1]"));
						Alert alertbox3 = wd.switchTo().alert();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						alertbox3.accept();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						alertbox3.accept();
						break;
					}
				}
			}
			return "success";
		}
		else {
			String msg = alertbox1.getText();
			alertbox1.accept();
			return msg;
		}
	}	
}