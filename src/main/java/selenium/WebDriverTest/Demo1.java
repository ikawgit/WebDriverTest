package selenium.WebDriverTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo1 {
	
	public void test(){
		System.setProperty("webdriver.chrome.driver", "D://soft//chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.baidu.com");
		
	}

}
