package qa.ybl.pages;

import org.openqa.selenium.support.PageFactory;

import qa.ybl.base.*;
import qa.ybl.logging.Logging;
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
	public Logging log;
	
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
	WebElement UploadDetails;
	
	@FindBy(xpath = "//*[@id=\"ctl00_TxnContentPage_grdRecordDetails\"]/tbody/tr")
	List<WebElement> UploadDetailsrow;
	
	@FindBy(xpath = "//*[@id=\"ctl00_TxnContentPage_grdRecordDetails\"]/tbody/tr[2]/td")
	List<WebElement> UploadDetailscol;
	
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
		log = new Logging();
		try {
			String File = filepath+"\\"+filename;
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame2)));
			try {
				log.Loginfo("******SELECTED FILE IS: "+File+"******");
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
					log.Logerror("~~~~~WHEN CLICKING THE SUBMIT BUTTON~~~~~"+"\n"+e);
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
					log.Logerror("~~~~~WHEN READING THE RESULT MESSAGE~~~~~"+"\n"+e);
				}
			}catch(Exception e) {
				log.Logerror("~~~~~~~~~~WHEN UPLOADING THE FILE & SUBMITTING THE FILE~~~~~~~~~~"+"\n"+e);
			}
		}catch(Exception e) {
			log.Logerror("~~~~~~~WHIE SWITTCHING TO FRAME TO UPLOAD THE FILE~~~~~~~"+"\n"+e);
		}
		return resultmessage;
	}
	
	public String UploadFile(String menu, String Username, String Password,String Captcha, String filepath, String filename) {
		String result = null;
		log = new Logging();
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
					log.Logerror("$$$$$$$$$$~~WHILE GETTING INTO UPLOAD TRANSACTION~~$$$$$$$$$$"+"\n"+e);
				}
				try {
					UploadPage up = new UploadPage();
					result = up.Upload(filepath, filename);
				} catch (Exception e) {
                        
				} 
			}else {
				log.Logerror("Upload File method condition Failed taking the snapshot");
				driver.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
			log.Logerror("UploadFile() method condition Failed"+e);
		}
		return result;
	}
	
	public String Getuploaddetails(String result,String Filename) {
		String exp = "File Upload is in Queue";
		String batchid = null, btn, file, File, bat = null;
		log = new Logging();
		try {
			log.Loginfo("Result is: "+result);
			if(result.contains(exp)) {
				log.Loginfo("Result inside Getuploaddetails is: "+result);
				driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//				driver.switchTo().defaultContent();
//				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame2));
				try {
					driver.switchTo().defaultContent();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
					wait.until(ExpectedConditions.elementToBeClickable(Searchbutton));
//					System.out.println("Is Search button displaying: "+Searchbutton.isDisplayed());
//					System.out.println("Is Search button displaying: "+Searchbutton.isEnabled());
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click()", Searchbutton);
					log.Loginfo("######Search button is clicked########");
					try {
						driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
						driver.switchTo().defaultContent();
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
						//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='ctl00_TxnContentPage_grdRecordDetails']")));
						List<WebElement> rowelements = UploadDetailsrow;
						List<WebElement> colelements = UploadDetailscol;
						int rowcount = rowelements.size();
						int colcount = colelements.size();
//						System.out.println("Table row count is: "+rowcount);
//						System.out.println("Table col count is: "+colcount);
						try {
							outerloop:
							for(int i=2;i<=rowcount;i++) {
								for(int j=1;j<=colcount;j++) {
									btn = "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl0"+i+"_btnDownloadReport']"; 
									file= "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl0"+i+"_lblFileName']";
									bat = "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl0"+i+"_lblbatchid']";
									File = driver.findElement(By.xpath(file)).getText();
									batchid = driver.findElement(By.xpath(bat)).getText();
//									System.out.println("BatchID found is: "+batchid);
									if(File.equalsIgnoreCase(Filename)) {
										while(batchid.equalsIgnoreCase("NA")) {
											executor.executeScript("arguments[0].click()", Searchbutton);
											driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
											driver.switchTo().defaultContent();
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
											//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='ctl00_TxnContentPage_grdRecordDetails']")));
											File = driver.findElement(By.xpath(file)).getText();
											batchid = driver.findElement(By.xpath(bat)).getText();
										}
										boolean button = driver.findElement(By.xpath(btn)).isEnabled();
										if(button) {
											driver.findElement(By.xpath(btn)).click();
											batchid = batchid+"Record is Fail Please check downloads";
										}
										log.Loginfo("Batch ID is: "+batchid);
										break outerloop;
									}
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							log.Logerror(""+e);
						}
					}catch(IndexOutOfBoundsException e) {
						log.Logerror(""+e);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.Logerror(""+e);
				}
				
			}else {
				batchid="NA";
			}
		}catch(Exception e) {
			log.Logerror(""+e);
		}
		return batchid;
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
			log.Loginfo("*****Logged out from Application*****");
			driver.close();
			log.Loginfo("*****Successfuly Closed the Browser*****");
		}catch(Exception e) {
			log.Logerror("Error While performing Teardown method"+e);
			driver.close();
			log.Loginfo("*****Closed the Browser With Error*****");
		}
	}
}
