package qa.ybl.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.ybl.logging.Logging;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Base {
	public static WebDriver driver;
	public static Properties prop;
	public Logging log;
	
	public Base() throws Exception{
		try {
			prop = new Properties();
			String File = "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\Practice\\20101\\qa.ybl\\src\\main\\java\\qa\\ybl\\property\\config.properties";
			FileInputStream file = new FileInputStream(File);
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
//	public void Initialization() throws AWTException {
//		System.out.println("---------*****LAUNCHING THE BROWSER*****---------");
//		System.setProperty("webdriver.edge.driver", "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\Drivers\\msedgedriver.exe");
//		EdgeOptions opt = new EdgeOptions();
//		opt.addArguments("--guest");
//		driver = new EdgeDriver(opt);
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		System.out.println("---------*****LOADING THE YBL APPLICATION*****---------");
//		driver.get(prop.getProperty("url"));
//		try {
//			WebElement advance = driver.findElement(By.xpath("//button[@id='details-button']"));
//			advance.click();
//			WebElement cont = driver.findElement(By.xpath("//*[text()='Continue to 10.0.0.5 (unsafe)']"));
//			cont.click();
//			System.out.println("---------*****SUCCESSFULLY LAUNCHED THE YBL APPLICATION*****---------");
//			//String URL = driver.getCurrentUrl();
//				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//				try {
//					Robot rb = new Robot();
//					rb.keyPress(KeyEvent.VK_ENTER);
//					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//					rb.keyPress(KeyEvent.VK_ENTER);
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println("^^^^^^^^*****ROBOT CLASS ERROR*****^^^^^^^^");
//				} 
//		}catch(Exception e) {
//			System.out.println("^^^^^^^^*****INITIALIZATION OF BROWSER ERROR*****^^^^^^^^");
//			e.printStackTrace();
//		}
//	}
	
	public void Initialization() {
		log = new Logging();
		log.Loginfo("---------*****LAUNCHING THE BROWSER*****---------");
		//System.out.println("---------*****LAUNCHING THE BROWSER*****---------");
		System.setProperty("webdriver.edge.driver", "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\Drivers\\msedgedriver.exe");
		EdgeOptions opt = new EdgeOptions();
		opt.addArguments("--guest");
		driver = new EdgeDriver(opt);
		driver.manage().deleteAllCookies();
		//driver.manage().window().maximize();
		log.Loginfo("---------*****LOADING THE YBL APPLICATION*****---------");
		//System.out.println("---------*****LOADING THE YBL APPLICATION*****---------");
		driver.get(prop.getProperty("url"));
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement advance = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='details-button']")));
			advance.click();
			WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Continue to 10.0.0.5 (unsafe)']")));
			cont.click();
			log.Loginfo("---------*****SUCCESSFULLY LAUNCHED THE YBL APPLICATION*****---------");
			//System.out.println("---------*****SUCCESSFULLY LAUNCHED THE YBL APPLICATION*****---------");
			//String URL = driver.getCurrentUrl();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				try {
					Robot rb = new Robot();
					rb.keyPress(KeyEvent.VK_ENTER);
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					rb.keyPress(KeyEvent.VK_ENTER);
				} catch (Exception e) {
					//e.printStackTrace();
					log.Logerror("^^^^^^^^*****ROBOT CLASS ERROR*****^^^^^^^^"+e);
					//System.out.println("^^^^^^^^*****ROBOT CLASS ERROR*****^^^^^^^^");
				} 
		}catch(Exception e) {
			log.Logerror("^^^^^^^^*****INITIALIZATION OF BROWSER ERROR*****^^^^^^^^"+"\n"+e);
			//System.out.println("^^^^^^^^*****INITIALIZATION OF BROWSER ERROR*****^^^^^^^^");
			//e.printStackTrace();
		}
	}

}
