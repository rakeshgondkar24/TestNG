package qa.ybl.pages;

import org.openqa.selenium.support.PageFactory;

import qa.ybl.base.*;
import qa.ybl.pages.*;
import qa.ybl.utility.Global;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class UploadPage extends Base{
	public Global global;
	public  HomePage home;
	public  LoginPage login;
	public String frame1 = "//*[@id='frame1']";
	public String frame2 = "//*[@id='IFRAME1']";
	public  WebDriverWait wait;
	public  String Fdestfile = prop.getProperty("failsnapshot");
	
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
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtFromDt']")
	WebElement FromDate;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtToDt']")
	WebElement ToDate;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_btnSearch']")
	WebElement Searchbutton;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_grdRecordDetails']")
	List<WebElement> UploadDetails;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl02_lblbatchid']")
	WebElement Batchid;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl02_btnDownloadReport']")
	WebElement Download;

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
					if (Uploadresult.isDisplayed()) {
						resultmessage = Uploadresult.getText();
					}else {
						global = new Global();
						resultmessage = global.Alert(driver);
					}
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
			result = login.HomePage(Username, Password,Captcha);
			String loginresul="MainNavigation";
			if (result.contains(loginresul)) {
				try {
					driver.switchTo().defaultContent();
					wait = new WebDriverWait(driver, Duration.ofSeconds(20));
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame1)));
					Searchbox.sendKeys(menu);
					Gobutton.click();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("$$$$$$$$$$~~WHILE GETTING INTO UPLOAD TRANSACTION~~$$$$$$$$$$");
				}
				try {
					UploadPage up = new UploadPage();
					result = up.Upload(filepath, filename);
				} catch (Exception e) {

				} 
			}else {
				System.out.println("Upload File method condition Failed taking the snapshot");
				driver.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void Getuploaddetails(String result,String Filename) {
		String exp = "File Upload is in Queue";
		String batchid = null;
		try {
			if(result.contains(exp)) {
				System.out.println("Result inside Getuploaddetails is: "+result);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//				driver.switchTo().defaultContent();
//				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
				try {
					driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
					wait.until(ExpectedConditions.elementToBeClickable(Searchbutton));
					System.out.println("Is Search button displaying: "+Searchbutton.isDisplayed());
					System.out.println("Is Search button displaying: "+Searchbutton.isEnabled());
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click()", Searchbutton);
					System.out.println("######Search button is clicked########");
					try {
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
						//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='ctl00_TxnContentPage_grdRecordDetails']")));
						List<WebElement> elements = UploadDetails;
						int rowcount = elements.size();
						System.out.println("Table row count is: "+rowcount);
						for(int i=0;i<=rowcount;i++) {
							//System.out.println("Values of WebTables: "+elements.get(i).getText());
							elements.get(i).getText();
							if(elements.get(i).getText().equalsIgnoreCase(Filename)) {
								System.out.println("Values of WebTables: "+elements.get(i).getText());
								batchid = Batchid.getText();
								break;
							}
						}
						System.out.println("Batch ID is: "+batchid);
					}catch(IndexOutOfBoundsException e) {
						List<WebElement> elements = UploadDetails;
						int rowcount = elements.size();
						for(int i=0;i<=rowcount;i++) {
							elements.get(i).getText();
							if(elements.get(i).getText().equalsIgnoreCase(Filename)) {
								System.out.println("Values of WebTables: "+elements.get(i).getText());
								batchid = Batchid.getText();
								break;
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ClickSearch() {
		try {
			
		}catch(Exception e) {
			
		}
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
			driver.close();
		}
	}
}
