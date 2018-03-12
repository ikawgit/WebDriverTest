package com.selenium.testcases;

import java.util.List;

import com.selenium.common.basic.SetUp;
import com.selenium.init.Before;
import com.selenium.init.ExcelUtil;
import com.selenium.common.business.Logging;

public class AddMerchants extends Before {
		
	AddMerchants() {
		super();
		// TODO Auto-generated constructor stub
	}

	ExcelUtil poi = new ExcelUtil();
	//private List<List<String>> envdata = poi.read(SetUp.getDIR()+"\\src\\test\\java\\data\\addMerchants.xlsx", "环境信息");
	//private List<List<String>> merdata = poi.read(SetUp.getDIR()+"\\src\\test\\java\\data\\addMerchants.xlsx", "商户信息");
	//private List<List<String>> adddata = poi.read(SetUp.getDIR()+"\\src\\test\\java\\data\\addMerchants.xlsx", "商户添加");

	public void Add(){			
		//Logging.loginAgent("http://uat2gpoa119.bwt18.com/agent/agent", "zonghe", "qwe123");
		//Logging.logoutAgent();
		//Logging.loginControl("http://uat2gpoc.bwt18.com/", "root", "HAIzeiwang9631", "1");
		//Logging.logoutControl();
		Logging.loginMember("http://lucas.sit1gpom.bwt18.com/", "lucas1230", "qwe123");
		Logging.logoutMember();
	}

}
