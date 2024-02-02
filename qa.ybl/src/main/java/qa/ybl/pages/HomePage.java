package qa.ybl.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.ybl.base.*;

public class HomePage extends Base{
	public static WebDriverWait wait;

	@FindBy(xpath = "//*[@id='frame1']")
	WebElement Frame;
	
	@FindBy(xpath = "//*[@id='WebUserControl1_SearchText']")
	WebElement Searchbox;
	
	@FindBy(xpath = "//*[@id='Button1']")
	WebElement Gobutton;
	
	public HomePage() throws Exception{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public void GetintoTransaction(String UImenu, WebElement frame) {
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame)));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			Searchbox.click();
			Searchbox.sendKeys(UImenu);
			Gobutton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void GetintoTransaction(String UImenu, String frame) {
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame)));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			Searchbox.click();
			Searchbox.sendKeys(UImenu);
			Gobutton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
