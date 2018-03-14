package com.selenium.common.basic;

import org.openqa.selenium.WebDriver;

public class SetUp {
	
	public static String TESTENV;
	public static String PLATFORM	 	= "member";
	public static WebDriver WEBDRIVER	= null;
	public static String NODE;
	public static String BROWSER;
	public static String URL;
	public static String DIR;
	public static String DATANAME;

	public static String getTESTENV() {
		return TESTENV;
	}
	public static void setTESTENV(String tESTENV) {
		TESTENV = tESTENV;
	}
	public static String getPLATFORM() {
		return PLATFORM;
	}
	public static void setPLATFORM(String pLATFORM) {
		PLATFORM = pLATFORM;
	}
	public static WebDriver getWEBDRIVER() {
		return WEBDRIVER;
	}
	public static void setWEBDRIVER(WebDriver webdriver) {
		WEBDRIVER = webdriver;
	}
	public static String getNODE() {
		return NODE;
	}
	public static void setNODE(String nODE) {
		NODE = nODE;
	}
	public static String getBROWSER() {
		return BROWSER;
	}
	public static void setBROWSER(String bROWSER) {
		BROWSER = bROWSER;
	}
	public static String getURL() {
		return URL;
	}
	public static void setURL(String uRL) {
		URL = uRL;
	}
	public static String getDIR() {
		return DIR;
	}
	public static void setDIR(String dIR) {
		DIR = dIR;
	}
	public static String getDATANAME() {
		return DATANAME;
	}
	public static void setDATANAME(String dATANAME) {
		DATANAME = dATANAME;
	}	

}
