package qa.ybl.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.ybl.base.Base;
import qa.ybl.logging.Logging;
import qa.ybl.utility.Global;

public class UserCreationPage extends Base{
	protected Logging log;
	protected Global gl;
	protected LoginPage login;
	protected HomePage home;
	protected UserCreationPage uc;
	protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	@FindBy(xpath = "//*[@id='frame1']")
	private WebElement Frame;
	
	@FindBy(xpath = "//*[@id='IFRAME1']")
	private WebElement frame;
	
	
	@FindBy(xpath = "//*[contains(text(), 'General Details')]")
	private WebElement generalDetails;
	
	@FindBy(xpath = "//*[contains(text(), 'Level Settings')]")
	private WebElement levelSetting;
	
	@FindBy(xpath = "//*[contains(text(), 'Other Details')]")
	private WebElement otherDetails;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_btnNew']")
	private WebElement newButton;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_btnSave']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_CheckBox2']")
	private WebElement profileAttached;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_DropDownList2']")
	private WebElement selectProfile;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtOperatorCode']")
	private WebElement operatorCode;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_ddlTitle']")
	private WebElement title;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtUserName']")
	private WebElement userName;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtLoaginName']")
	private WebElement loginName;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtPassword']")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_txtConfrmPsswd']")
	private WebElement confirmPassword;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_ddlBank']")
	private WebElement entityName1;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_ddlCmpnyname']")
	private WebElement entityName2;
	
	@FindBy(xpath = "//*[@id='ctl00_TxnContentPage_ddlActive']")
	private WebElement status;
	
	public UserCreationPage() throws Exception{
		super();
		PageFactory.initElements(driver, this);
	}
	
	private boolean isFrameDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
			res = frame.isDisplayed();
			if(res) {
				res = frame.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("frame is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("frame is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isFrameDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isNewButtonDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(newButton));
			res = newButton.isDisplayed();
			if(res) {
				res = newButton.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("New Button is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("New Button is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isNewButtonDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isGeneralDetailsDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(generalDetails));
			res = generalDetails.isDisplayed();
			if(res) {
				res = generalDetails.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("General Details Button is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("General Details Button is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isGeneralDetailsDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isLevelSettingDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(levelSetting));
			res = levelSetting.isDisplayed();
			if(res) {
				res = levelSetting.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Level Setting Button is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Level Setting Button is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isLevelSettingDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isOtherDetailsDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(otherDetails));
			res = otherDetails.isDisplayed();
			if(res) {
				res = otherDetails.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Other Details Button is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Other Details Button is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isOtherDetailsDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isProfileAttachedDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(profileAttached));
			res = profileAttached.isDisplayed();
			if(res) {
				res = profileAttached.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Profile Attached checkbox is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Profile Attached checkbox is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isProfileAttachedDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isSelectProfileDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(selectProfile));
			res = selectProfile.isDisplayed();
			if(res) {
				res = selectProfile.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Select Profile DropDown is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Select Profile DropDown is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isSelectProfileDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isOperatorCodeDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(operatorCode));
			res = operatorCode.isDisplayed();
			if(res) {
				res = operatorCode.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Operator Code Textbox is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Operator Code Textbox is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isOperatorCodeDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isTitleDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(title));
			res = title.isDisplayed();
			if(res) {
				res = title.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Title Drop Down is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Title Drop Down is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isTitleDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isUserNameDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(userName));
			res = userName.isDisplayed();
			if(res) {
				res = userName.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("User Name Text Field is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("User Name Text Field is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isUserNameDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isEntityName1Displayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(entityName1));
			res = entityName1.isDisplayed();
			if(res) {
				res = entityName1.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Entity Name1 Drop Down is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Entity Name1 Drop Down is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isEntityName1Displayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isEntityName2Displayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(entityName2));
			res = entityName2.isDisplayed();
			if(res) {
				res = entityName2.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Entity Name2 Drop Down is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Entity Name2 Drop Down is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isEntityName2Displayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isLoginNameDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(loginName));
			res = loginName.isDisplayed();
			if(res) {
				res = loginName.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("loginName Text Field is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("loginName Text Field is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isLoginNameDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isPasswordDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(password));
			res = password.isDisplayed();
			if(res) {
				res = password.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Password Text Field is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Password Text Field is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isPasswordDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isConfirmPasswordDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(confirmPassword));
			res = confirmPassword.isDisplayed();
			if(res) {
				res = confirmPassword.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Confirm Password Text Field is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Confirm Password Text Field is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isConfirmPasswordDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isStatusDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(status));
			res = status.isDisplayed();
			if(res) {
				res = status.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Status Drop Down is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Status Drop Down is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isStatusDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private boolean isSaveButtonDisplayed() {
		log = new Logging();
		boolean res = false;
		try {
			wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			res = saveButton.isDisplayed();
			if(res) {
				res = saveButton.isEnabled();
				if(res) {
					res = true;
				}else {
					log.Loginfo("Save button is not enabled in UserCreationPage");
				}
			}else {
				log.Loginfo("Save button is not displayed in UserCreationPage");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.isSaveButtonDisplayed()"+"\n"+e);
		}
		return res;
	}
	
	private void getIntoUserCreationPage() {
		String text = null;
		log = new Logging();
		gl = new Global();
		try {
//			uc = new UserCreationPage();
//			if(uc.isFrameDisplayed()) {
//				driver = gl.SwitchToFrame(driver, frame);
//				newButton.click();
//				text = gl.handlePopUp(driver);
//			}else {
//				log.Loginfo("Frame is not available in User Creation Page");
//			}
			driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='IFRAME1']")));
            newButton.click();
            text = gl.handlePopUp(driver);
//			driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='IFRAME1")));
			}catch(Exception e) {
			log.Logerror("UserCreationPage.getIntoUserCreationPage()"+"\n"+e);
		}
	}
	
	private void clickProfileAttached() {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isProfileAttachedDisplayed()) {
				profileAttached.click();
				log.Loginfo("Clicked the Profile Attached Checkbox");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.clickProfileAttached()"+"\n"+e);
		}
	}
	
	private void selectProfile(String Profile) {
		log = new Logging();
		gl = new Global();
		try{
			uc = new UserCreationPage();
			if(uc.isSelectProfileDisplayed()) {
				gl.selectDropdownValue(selectProfile, Profile);
				log.Loginfo("Selected the drop down");
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.selectProfile()"+"\n"+e);
		}
	}
	
	private void selectEntityOne(String Entity1) {
		log = new Logging();
		gl = new Global();
		try {
			uc = new UserCreationPage();
			if(uc.isEntityName1Displayed()) {
				gl.selectDropdownValue(entityName1, Entity1);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.selectEntityOne()"+"\n"+e);
		}
	}
	
	private void selectEntityTwo(String Entity2) {
		log = new Logging();
		gl = new Global();
		try {
			uc = new UserCreationPage();
			if(uc.isEntityName2Displayed()) {
				gl.selectDropdownValue(entityName2, Entity2);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.selectEntityTwo()"+"\n"+e);
		}
	}
	
	private void enterOperatorCode(String OperatorCode) {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isOperatorCodeDisplayed()) {
				operatorCode.sendKeys(OperatorCode);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.enterOperatorCode()"+"\n"+e);
		}
	}
	
	private void selectTitle(String Title) {
		log = new Logging();
		gl = new Global();
		try {
			uc = new UserCreationPage();
			if(uc.isTitleDisplayed()) {
				gl.selectDropdownValue(title, Title);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.selectTitle()"+"\n"+e);
		}
	}
	
	private void enterUserName(String UserName) {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isUserNameDisplayed()) {
				userName.sendKeys(UserName);
			}
		}catch(Exception e) {
			log.Logerror("UserCretionPage.enterUserName()"+"\n"+e);
		}
	}
	
	private void enterLoginName(String LoginName) {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isLoginNameDisplayed()) {
				loginName.sendKeys(LoginName);
			}
		}catch(Exception e) {
			log.Logerror("UserCretionPage.enterUserName()"+"\n"+e);
		}
	}
	
	private void enterPassword(String Password) {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isPasswordDisplayed()) {
				password.sendKeys(Password);
			}
		}catch(Exception e) {
			log.Logerror("UserCretionPage.enterUserName()"+"\n"+e);
		}
	}
	
	private void enterConfirmPassword(String ConfirmPassword) {
		log = new Logging();
		try {
			uc = new UserCreationPage();
			if(uc.isConfirmPasswordDisplayed()) {
				confirmPassword.sendKeys(ConfirmPassword);
			}
		}catch(Exception e) {
			log.Logerror("UserCretionPage.enterUserName()"+"\n"+e);
		}
	}
	
	private void selectStatus(String Status) {
		log = new Logging();
		gl = new Global();
		try {
			uc = new UserCreationPage();
			if(uc.isStatusDisplayed()) {
				gl.selectDropdownValue(status, Status);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.selectStatus()"+"\n"+e);
		}
	}
	
	protected String getIntoCreationPage(String Menu,String Username,String lPassword,String Captcha) {
		String actual = null;
		log = new Logging();
		try {
			login = new LoginPage();
			String res = "Main";
			actual = login.HomePage(Username, lPassword, Captcha);
			if(actual.contains(res)) {
				home = new HomePage();
				home.GetintoTransaction(Menu, Frame);
			}
		}catch(Exception e) {
			log.Logerror("UserCreationPage.getIntoCreationPage()"+"\n"+e);
		}
		return actual;
	}
	
	public String createUser(String Menu,String TestFlag,String Username,String lPassword,String Captcha,String Profile,String Entity1,String Entity2,String OperatorCode,String Title,
			String UserName,String LoginName,String Password,String ConfirmPassword,String Status) {
		String result=null;
		log = new Logging();
		gl = new Global();
		if (TestFlag.equalsIgnoreCase("D")) {
			try {
				uc = new UserCreationPage();
				String res = "Main";
				result = uc.getIntoCreationPage(Menu, Username, lPassword, Captcha);
				log.Loginfo("Result after login is: "+result);
				if (result.contains(res)) {
					uc.getIntoUserCreationPage();
					uc.clickProfileAttached();
					uc.selectProfile(Profile);
					uc.selectEntityOne(Entity1);
					uc.selectEntityTwo(Entity2);
					uc.enterOperatorCode(OperatorCode);
					uc.selectTitle(Title);
					uc.enterUserName(UserName);
					uc.enterLoginName(LoginName);
					uc.enterPassword(Password);
					uc.enterConfirmPassword(ConfirmPassword);
					uc.selectStatus(Status);
				}
			} catch (Exception e) {
				log.Logerror("UserCreationPage.createUser()" + "\n" + e);
			} 
		}else {
			result = "Test Skipped";
		}
		return result;
	}
}
