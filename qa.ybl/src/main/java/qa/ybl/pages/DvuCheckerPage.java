package qa.ybl.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.ybl.base.Base;
import qa.ybl.utility.Global;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DvuCheckerPage extends Base {

	LoginPage lp;
	public HomePage home;
	public Global gl;
	public String datafile = prop.getProperty("datafilepath");
	public String sheetname = prop.getProperty("dvucheckersheetname");
	
	@FindBy (xpath = "//iframe[@id='IFRAME1']")
	WebElement frame;
	@FindBy (xpath = "//select[@id=\"ddlBCList\"]")
	WebElement bc;
	@FindBy (xpath = "//select[@id=\"ddlBCBranchList\"]")
	WebElement bcBranch;
	@FindBy (xpath = "//select[@id=\"ddlExtGroupidList\"]")
	WebElement extGroupID;
	@FindBy (xpath = "//input[@id=\"btnSearch\"]")
	WebElement searchBtn;
	@FindBy (xpath = "//table[@id=\"WebGrid\"]")
	WebElement table;
	String frame1 = "//*[@id='frame1']";
	String commentsID = "txtremarks";

	public DvuCheckerPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
		lp = new LoginPage();
		gl = new Global();
	}
	public void GetintoDvuChecker(String Username, String Password,String Captcha,
			String Menu, String BC,String BcBranch,String ExtGroupId) {
		String result = lp.HomePage(Username, Password, Captcha);
		try {
			home = new HomePage();
			home.GetintoTransaction(Menu, frame1);
			driver.switchTo().defaultContent();
			if (frame.isDisplayed()) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			    log.Logerror("Inside IFRAME");
			    gl.selectDropdownValue(bc, BC);
			    gl.selectDropdownValue(bcBranch, BcBranch);
			    gl.selectDropdownValue(extGroupID, ExtGroupId);
			    gl.doubleClick(driver, searchBtn);
			    List<WebElement> records = driver.findElements(By.xpath("//table[@id='WebGrid']/tbody/tr"));
			    log.Loginfo("Printing the values");
			    if (records.size() > 0) {
			    	log.Loginfo("getting values");
					for (WebElement row : records) {
						List<WebElement> cells = row.findElements(By.tagName("td"));
						if (!cells.isEmpty()) {
							WebElement selectCenterLeaderRadio = cells.get(0).findElement(By.tagName("input"));
							boolean isCenterLeaderSelected = selectCenterLeaderRadio.isSelected();
							WebElement approve = cells.get(1).findElement(By.tagName("input"));
							boolean isApproved = approve.isSelected();
							WebElement reject = cells.get(2).findElement(By.tagName("input"));
							boolean isRejected = reject.isSelected();
							WebElement sendbackToMaker = cells.get(3).findElement(By.tagName("input"));
							boolean isSendbackToMaker = sendbackToMaker.isSelected();
							WebElement sendbackToBCA = cells.get(4).findElement(By.tagName("input"));
							boolean isSendbackToBCA = sendbackToBCA.isSelected();
							WebElement ignore = cells.get(5).findElement(By.tagName("input"));
							boolean isIgnore = ignore.isSelected();
							String externalCustomerId = cells.get(6).getText();
							String customerName = cells.get(7).getText();
							String barcode = cells.get(8).getText();
							String loanEligibleAmount = cells.get(9).getText();
							String loanAmount = cells.get(10).getText();
							String status = cells.get(11).getText();
							String comments = cells.get(12).findElement(By.id(commentsID)).getAttribute("value");
							String isCcrExpire = cells.get(13).getText();
							WebElement reTriggerCB = cells.get(14).findElement(By.tagName("Button"));
 
							log.Loginfo("---------------------------------------------------------");
							if (isCenterLeaderSelected) {
								log.Loginfo("Select Center Leader: Checker");
							} else {
								log.Loginfo("Select Center Leader: Un-Checker");
							}
							if (isApproved) {
								log.Loginfo("Approved: Checked");
							} else if (isRejected) {
								log.Loginfo("Rejected: Checked");
							}
							if (isSendbackToMaker) {
								log.Loginfo("Sendback To Maker: Checked");
							} else {
								log.Loginfo("Sendback To Maker: Un-Checked");
							}
							if (isSendbackToBCA) {
								log.Loginfo("Sendback To BCA: Checked");
							} else {
								log.Loginfo("Sendback To BCA: Un-Checked");
							}
							if (isIgnore) {
								log.Loginfo("Ignore: Checked");
							} else {
								log.Loginfo("Ignore: Un-Checked");
							}
							log.Loginfo("External Customer ID: " + externalCustomerId);
							log.Loginfo("Customer Name: " + customerName);
							log.Loginfo("Barcode: " + barcode);
							log.Loginfo("Loan Eligible Amount: " + loanEligibleAmount);
							log.Loginfo("Loan Amount: " + loanAmount);
							log.Loginfo("Status: " + status);
							log.Loginfo("Comments: " + comments);
							log.Loginfo("Is CCR Expire: " + isCcrExpire);
							if (reTriggerCB.isEnabled()) {
								log.Loginfo("Re-Trigger CB is Enabled: " + reTriggerCB.getText());
							} else {
								log.Loginfo("Re-Trigger CB is Disabled: " + reTriggerCB.getText());
							}
						}
					} 
				} else {
					try {
		                Alert alert = driver.switchTo().alert();
		                String alertMessage = alert.getText();
		                log.Loginfo("Alert message: " + alertMessage);
		                alert.accept();

		            } catch (Exception e) {
		                log.Loginfo("Error handling alert: " + e.getMessage());
		            }
				}
			} else {
			    log.Logerror("IFRAME1 not found in the DOM");
			}
		} catch (Exception e) {
			log.Logerror("DvuChekerPage.GetintoDvuChecker()"+"\n"+e);
		}
	}
}
