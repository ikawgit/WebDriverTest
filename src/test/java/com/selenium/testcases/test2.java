package com.selenium.testcases;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

class test {
	
	public test() {
		System.out.println("1类无参数构造函数");
	}
	
	public test(int a) {
		System.out.println("1类有参数构造函数");
	}
	


}

class test1 extends test {
	 public test1() {
		 System.out.println("2类无参数构造函数");
	 }
}

public class test2 extends test1 {
	
	public test2() {
		System.out.println("3类无参数构造函数");
	}

	public void xx() {
		System.out.println("xx");
	}
	
	public static void main(String[] args) {
		test2 t = new test2();
		t.xx();
	}
	

}