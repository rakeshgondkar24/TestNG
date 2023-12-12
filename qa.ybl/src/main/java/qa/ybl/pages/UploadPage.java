package qa.ybl.pages;

import org.openqa.selenium.support.PageFactory;

import qa.ybl.base.*;
import qa.ybl.logging.Logging;
import qa.ybl.pages.*;
import qa.ybl.utility.Global;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	public Select sc;
	
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
	
	@FindBy(xpath = "//*[@id='ddlWorkflowType']")
	WebElement WorkFlowType;
	
	@FindBy(xpath = "//*[@id='btnGetRec']")
	WebElement WorkFlowSearchButton;
	
	@FindBy(xpath = "//*[@id='grdCustomer']/tbody/tr")
	List<WebElement> ApproveDetailsrow;
	
	@FindBy(xpath = "//*[@id='grdCustomer']/tbody/tr/td")
	List<WebElement> ApproveDetailscol;

	public UploadPage() throws Exception{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public String Upload(String filepath, String filename) {
		Global gl = new Global();
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
//					JavascriptExecutor executor = (JavascriptExecutor) driver;
//					executor.executeScript("arguments[0].click();", Submitbutton);
					gl.buttonClick(driver, Submitbutton);
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
				resultmessage="File is not available";
			}
		}catch(Exception e) {
			log.Logerror("~~~~~~~WHIE SWITTCHING TO FRAME TO UPLOAD THE FILE~~~~~~~"+"\n"+e);
		}
		return resultmessage;
	}
	
//	public String UploadFile(String TestFlag,String menu, String Username, String Password,String Captcha, String filepath, String filename) {
//		String result = null;
//		log = new Logging();
//		try {
//			login = new LoginPage();
//			result = login.HomePage(Username, Password,Captcha);
//			String loginresul="MainNavigation";
//			if (result.contains(loginresul)) {
//				try {
//					driver.switchTo().defaultContent();
//					wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame1)));
//					Searchbox.sendKeys(menu);
//					Gobutton.click();
//				} catch (Exception e) {
//					log.Logerror("$$$$$$$$$$~~WHILE GETTING INTO UPLOAD TRANSACTION~~$$$$$$$$$$"+"\n"+e);
//				}
//				try {
//					UploadPage up = new UploadPage();
//					result = up.Upload(filepath, filename);
//				} catch (Exception e) {
//                        
//				} 
//			}else {
//				log.Logerror("Upload File method condition Failed taking the snapshot");
//				driver.close();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//			log.Logerror("UploadFile() method condition Failed"+e);
//		}
//		return result;
//	}
	
	public String UploadFile(String TestFlag,String menu, String Username, String Password,String Captcha, String filepath, String filename) {
		String result = null;
		log = new Logging();
		log.Loginfo("Test Flag is: "+TestFlag);
		if (TestFlag.equalsIgnoreCase("D")) {
			try {
				login = new LoginPage();
				result = login.HomePage(Username, Password, Captcha);
				String loginresul = "MainNavigation";
				if (result.contains(loginresul)) {
					try {
						driver.switchTo().defaultContent();
						wait = new WebDriverWait(driver, Duration.ofSeconds(20));
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame1)));
						Searchbox.sendKeys(menu);
						Gobutton.click();
					} catch (Exception e) {
						log.Logerror("$$$$$$$$$$~~WHILE GETTING INTO UPLOAD TRANSACTION~~$$$$$$$$$$" + "\n" + e);
					}
					try {
						UploadPage up = new UploadPage();
						result = up.Upload(filepath, filename);
					} catch (Exception e) {

					}
				} else {
					log.Logerror("Upload File method condition Failed taking the snapshot");
					driver.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.Logerror("UploadFile() method condition Failed" + e);
			} 
		}else {
			log.Loginfo("Test Skipped");
			result = "Test Case Skipped";
		}
		return result;
	}
	
	public String Getuploaddetails(String result,String Filename) {
		Global gl = new Global();
		String exp = "File Upload is in Queue";
		String batchid = null, btn, file, File, bat, status, Status = null;
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
//					JavascriptExecutor executor = (JavascriptExecutor)driver;
//					executor.executeScript("arguments[0].click()", Searchbutton);
					gl.buttonClick(driver, Searchbutton);
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
									status = "//*[@id='ctl00_TxnContentPage_grdRecordDetails_ctl0"+i+"_lblStatus']";
									File = driver.findElement(By.xpath(file)).getText();
									batchid = driver.findElement(By.xpath(bat)).getText();
//									System.out.println("BatchID found is: "+batchid);
									if(File.equalsIgnoreCase(Filename)) {
										while(batchid.equalsIgnoreCase("NA")) {
//											executor.executeScript("arguments[0].click()", Searchbutton);
											gl.buttonClick(driver, Searchbutton);
											driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
											driver.switchTo().defaultContent();
											wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
											//wait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='ctl00_TxnContentPage_grdRecordDetails']")));
											File = driver.findElement(By.xpath(file)).getText();
											batchid = driver.findElement(By.xpath(bat)).getText();
											Status = driver.findElement(By.xpath(status)).getText();
											log.Loginfo("Status of File is: "+Status);
											if(Status.contains("File failed due to parsing errors")) {
												log.Loginfo("FAIL: "+Status);
												batchid = "NA"+" File failed due to parsing errors";
												break;
											}
										}
										boolean button = driver.findElement(By.xpath(btn)).isEnabled();
										if(button) {
											driver.findElement(By.xpath(btn)).click();
											batchid = batchid+"Record is Fail Please check downloads";
											log.Loginfo(Filename+" Record is Fail Please check downloads");
										}
										log.Loginfo("Batch ID is: "+batchid);
										break outerloop;
									}
								}
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							log.Logerror("UploadPage.Getuploaddetails().Outerloop"+"\n"+e);
						}
					}catch(IndexOutOfBoundsException e) {
						log.Logerror("UploadPage.Getuploaddetails().TryBlock"+"\n"+e);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.Logerror("UploadPage.Getuploaddetails().TryJavaScript"+"\n"+e);
				}
				
			}else {
				batchid="NA";
			}
		}catch(Exception e) {
			log.Logerror("UploadPage.Getuploaddetails()"+"\n"+e);
		}
		return batchid;
	}
	
	public String GetApproveDetails(String Filename,String WorkflowType,String Action) {
		String message = null;
		global = new Global();
		log = new Logging();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			//global.SwitchToFrame(driver, frame2);
			driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame2)));
			sc =new Select(WorkFlowType);
			sc.selectByVisibleText(WorkflowType);
//			JavascriptExecutor je = (JavascriptExecutor) driver;
//			je.executeScript("arguments[0].click()", WorkFlowSearchButton);
			global.buttonClick(driver, WorkFlowSearchButton);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			outerloop:
			for(int i =2;i<=ApproveDetailsrow.size();i++) {
				for(int j=2;j<=7;j++) {
					String val = "//*[@id='grdCustomer']/tbody/tr["+i+"]/td["+j+"]";
					String res = driver.findElement(By.xpath(val)).getText();
					if(res.equalsIgnoreCase(Filename)) {
						log.Loginfo("Value of table is: "+res);
						String bat="//*[@id='grdCustomer_ctl0"+i+"_lnkBtn']";
						WebElement batchid = driver.findElement(By.xpath(bat));
//						JavascriptExecutor executor = (JavascriptExecutor) driver;
//						executor.executeScript("arguments[0].click()", batchid);
						global.buttonClick(driver, batchid);
						try {
							log.Loginfo("Action which is selected is: "+Action);
							message = global.NewWindow(driver, Action);
							break outerloop;
						} catch (Exception e) {
							log.Logerror("Handling the new window: "+"\n"+e);
						}
					}
				}
			}
		}catch(Exception e) {
			log.Logerror("UploadPage.GetApproveDetails(): "+"\n"+e);
		}
		return message;
	}
	
	public String ApproveFile(String TestFlag,String menu, String Username, String Password,String Captcha, String filename,String Action) {
		String result=null;
		Action = Action.toLowerCase();
		log = new Logging();
		log.Loginfo("Test Flag is:"+TestFlag);
		if (TestFlag.equalsIgnoreCase("D")) {
			try {
				login = new LoginPage();
				result = login.HomePage(Username, Password, Captcha);
				String res = "Main";
				if (result.contains(res)) {
					try {
						driver.switchTo().defaultContent();
						wait = new WebDriverWait(driver, Duration.ofSeconds(20));
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(frame1)));
						Searchbox.sendKeys(menu);
						Gobutton.click();
					} catch (Exception e) {
						log.Logerror("$$$$$$$$$$~~WHILE GETTING INTO Inbox TRANSACTION~~$$$$$$$$$$" + "\n" + e);
					}
					UploadPage up = new UploadPage();
					result = up.GetApproveDetails(filename,"File Upload - Disbursal",Action);
				} else {
					log.Logerror("Approve File method condition Failed taking the snapshot");
					driver.close();
				}
			} catch (Exception e) {
				log.Logerror("UploadPage.ApproveFile()"+"\n"+e);
			} 
		}else {
			log.Loginfo("Test Skipped");
			result = "Test Case Skipped";
		}
		return result;
	}
	
 	public void Teardown() {
		log = new Logging();
		try {
			driver.switchTo().defaultContent();
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='frame1']")));
			if (wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='frame1']"))) != null) {
				//driver.switchTo().frame(Frame);
				Searchbox.click();
				Searchbox.sendKeys("GNLO");
				Gobutton.click();
				log.Loginfo("*****Logged out from Application*****");
				log.Loginfo("*****Successfuly Closed the Browser*****");
				driver.close();
			}else if (wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='frame1']"))) == null){
				log.Loginfo("*****Successfuly Closed the Browser*****");
				driver.close();
			}
		}catch(Exception e) {
			log.Logerror("UploadPage.Teardown()"+"\n"+e);
			driver.close();
			log.Loginfo("*****Closed the Browser With Error*****");
		}
	}
}
