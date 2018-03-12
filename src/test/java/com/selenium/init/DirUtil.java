package com.selenium.init;

public class DirUtil {

	private static int originStackIndex = 2;

    public static String getFileName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getFileName();
    }

    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getClassName();
    }

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getMethodName();
    }

    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[originStackIndex].getLineNumber();
    }
	
}
