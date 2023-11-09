package qa.ybl.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Base {
	public static WebDriver driver;
	public static Properties prop;
	
	public Base() throws Exception{
		prop = new Properties();
		String File = "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\Practice\\20101\\qa.ybl\\src\\main\\java\\qa\\ybl\\property\\config.properties";
		FileInputStream file = new FileInputStream(File);
		prop.load(file);
	}
	
	public void Initialization() throws AWTException {
		System.out.println("---------*****LAUNCHING THE BROWSER*****---------");
		System.setProperty("webdriver.edge.driver", "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\Drivers\\msedgedriver.exe");
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--remote-allow-origins=*");
		driver = new EdgeDriver(opt);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		System.out.println("---------*****LOADING THE YBL APPLICATION*****---------");
		driver.get(prop.getProperty("url"));
		try {
			WebElement advance = driver.findElement(By.xpath("//button[@id='details-button']"));
			advance.click();
			WebElement cont = driver.findElement(By.xpath("//*[text()='Continue to 10.0.0.5 (unsafe)']"));
			cont.click();
			System.out.println("---------*****SUCCESSFULLY LAUNCHED THE YBL APPLICATION*****---------");
			try {
				Robot rb = new Robot();
				rb.keyPress(KeyEvent.VK_ENTER);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				rb.keyPress(KeyEvent.VK_ENTER);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("^^^^^^^^*****ROBOT CLASS ERROR*****^^^^^^^^");
			}
		}catch(Exception e) {
			System.out.println("^^^^^^^^*****INITIALIZATION OF BROWSER ERROR*****^^^^^^^^");
			e.printStackTrace();
		}
	}

}
