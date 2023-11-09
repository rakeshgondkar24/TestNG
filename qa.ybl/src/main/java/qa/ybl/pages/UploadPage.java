package qa.ybl.pages;

import org.openqa.selenium.support.PageFactory;

import qa.ybl.base.*;
import qa.ybl.pages.*;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class UploadPage extends Base{
	public static HomePage home;
	public static LoginPage login;
	public String frame1 = "//*[@id='frame1']";
	public String frame2 = "//*[@id='IFRAME1']";
	public static WebDriverWait wait;
	
	@FindBy(xpath = "//*[@id='IFRAME1']")
	WebElement Frame;
	
	@FindBy(xpath = "//*[@id='WebUserControl1_SearchText']")
	WebElement Searchbox;
	
	@FindBy(xpath = "//*[@id='Button1']")
	WebElement Gobutton;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_File_upload']")
	WebElement Choosefile;
	
	@FindBy(xpath = "//input[@id='ctl00_TxnContentPage_Btn_Submit']")
	WebElement Submitbutton;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_lbl_txt']")
	WebElement Uploadresult;

	public UploadPage() throws Exception{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public String Upload(String filepath, String filename) {
		String resultmessage = null;
		try {
			String File = filepath+"\\"+filename;
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame2)));
			try {
				System.out.println("******SELECTED FILE IS: "+File+"******");
				Choosefile.sendKeys(File);
				try {
					/*driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame2)));
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ctl00_TxnContentPage_Btn_Submit']")));
					Submitbutton.click();*/
					wait.until(ExpectedConditions.elementToBeClickable(Submitbutton));
					JavascriptExecutor executor = (JavascriptExecutor) driver;
					executor.executeScript("arguments[0].click();", Submitbutton);
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("~~~~~WHEN CLICKING THE SUBMIT BUTTON~~~~~");
				}
				try {
					driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame2)));
					resultmessage = Uploadresult.getText();
				}catch(Exception e) {
					e.printStackTrace();
					System.out.println("~~~~~WHEN READING THE RESULT MESSAGE~~~~~");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("~~~~~~~~~~WHEN UPLOADING THE FILE & SUBMITTING THE FILE~~~~~~~~~~");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("~~~~~~~WHIE SWITTCHING TO FRAME TO UPLOAD THE FILE~~~~~~~");
		}
		return resultmessage;
	}
	
	public String UploadFile(String menu, String Username, String Password,String Captcha, String filepath, String filename) {
		String result = null;
		try {
			login = new LoginPage();
			login.HomePage(Username, Password,Captcha);
			try {
				driver.switchTo().defaultContent();
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame1)));
				Searchbox.sendKeys(menu);
				Gobutton.click();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("$$$$$$$$$$~~WHILE GETTING INTO UPLOAD TRANSACTION~~$$$$$$$$$$");
			}
			try {
				UploadPage up = new UploadPage();
				result = up.Upload(filepath, filename);
			}catch(Exception e) {
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void Teardown() {
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='frame1']")));
			//driver.switchTo().frame(Frame);
			Searchbox.click();
			Searchbox.sendKeys("GNLO");
			Gobutton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
