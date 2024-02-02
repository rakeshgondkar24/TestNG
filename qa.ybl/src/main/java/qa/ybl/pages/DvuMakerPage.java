package qa.ybl.pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.ybl.base.Base;
import qa.ybl.utility.Global;

public class DvuMakerPage extends Base {

	public LoginPage lp;
	public HomePage home;
	public Global gl;
	
	
	@FindBy (xpath = "//iframe[@id='IFRAME1']")
	WebElement frame;
	
	@FindBy (xpath="/html[1]/body[1]/div[1]/table[1]/tbody[1]/tr[3]/td[2]/select[1]")
	WebElement bcBtn;

	@FindBy (xpath = "//select[@id=\"ddlBCBranchList\"]")
	WebElement bcBranch;
	@FindBy (xpath = "//select[@id=\"ddlExtGroupidList\"]")
	WebElement extGroupID;
	@FindBy (xpath = "//input[@id=\"btnSearch\"]")
	WebElement searchBtn;
	@FindBy (xpath = "//table[@id=\"WebGrid\"]")
	WebElement table;

	String frame1 = "//*[@id='frame1']";
	String statusID = "txtstatus";

 
	public DvuMakerPage() throws Exception {
		super();
		PageFactory.initElements(driver, this);
		lp = new LoginPage();
		gl = new Global();
	}


	@SuppressWarnings("deprecation")
	public void GetintoDvuMaker(String Username, String Password,String Captcha, String Menu,String BC,
			String BcBranch, String ExtGroupId) {
		try {
			String result = lp.HomePage(Username, Password, Captcha);
			home = new HomePage();
			home.GetintoTransaction(Menu, frame1);
			log.Loginfo("Get into dvu maker page");
			driver.switchTo().defaultContent();
			log.Loginfo("Getting into the frame");
			if (frame.isDisplayed()) {
			    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			    log.Logerror("Inside IFRAME");
			    log.Loginfo("Clicking BC btn");
			    gl.selectDropdownValue(bcBtn, BC);
			    gl.selectDropdownValue(bcBranch, BcBranch);
			    gl.selectDropdownValue(extGroupID, ExtGroupId);
			    gl.doubleClick(driver, searchBtn);
			    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    List<WebElement> records = driver.findElements(By.xpath("//table[@id='WebGrid']/tbody/tr"));
			    if (records.size() > 0) {
					for (WebElement record : records) {
						log.Loginfo("Inside for loop");
						List<WebElement> cells = record.findElements(By.tagName("td"));
 
						if (!cells.isEmpty()) {
							log.Loginfo("getting values");
							String externalCustomerId = cells.get(0).getText();
							String customerName = cells.get(1).getText();
							String barcode = cells.get(2).getText();
							String loanEligibleAmount = cells.get(3).getText();
							String loanAmount = cells.get(4).getText();
							String applicationStatus = cells.get(5).findElement(By.id(statusID)).getAttribute("value");
							log.Loginfo("---------------------------------------------------------");
							log.Loginfo("External Customer ID: " + externalCustomerId);
							log.Loginfo("Customer Name: " + customerName);
							log.Loginfo("Barcode: " + barcode);
							log.Loginfo("Loan Eligible Amount: " + loanEligibleAmount);
							log.Loginfo("Loan Amount: " + loanAmount);
							log.Loginfo("Application Status: " + applicationStatus);
						}
 
					} 
				}else {
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
 
			System.out.println("Result of GetintoDvuMaker"+ result);
		} catch (Exception e){
			log.Loginfo(""+e);
		}
	}
}
