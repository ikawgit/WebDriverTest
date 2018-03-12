package selenium.WebDriverTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo1 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\workspace\\WebDriverTest\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.baidu.com");
	}
	
}
