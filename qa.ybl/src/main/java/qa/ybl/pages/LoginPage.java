package qa.ybl.pages;

import qa.ybl.base.*;
import qa.ybl.logging.Logging;
import qa.ybl.utility.Global;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends Base{

	public Global global;
	
	public static WebDriverWait wait;
	
	public Logging log;

	@FindBy(xpath = "//*[@id='txtLogin']")
	WebElement Username;
	
	@FindBy(xpath = "//*[@id='txtPassword']")
	WebElement Password;
	
	@FindBy(xpath = "//*[@id='txtCatpcha']")
	WebElement Captcha;
	
	@FindBy(xpath = "//*[@id='ImgBtnLogin']")
	WebElement Loginbtn;
	
	@FindBy(xpath = "//*[@id='frame1']")
	WebElement Frame;
	
	@FindBy(xpath = "//*[@id='WebUserControl1_SearchText']")
	WebElement Searchbox;
	
	@FindBy(xpath = "//*[@id='Button1']")
	WebElement Gobutton;
	
	@FindBy(xpath = "//*[@id='lblMessage']")
	WebElement Apperror;
	
	public LoginPage() throws Exception{
		super();
		PageFactory.initElements(driver, this);
	}
	
	public Boolean UsernameField() {
		Boolean val,result=false;
		log = new Logging();
		try {
			val = Username.isDisplayed();
			if(val) {
				Username.click();
				result= Username.isEnabled();
				if(result) {
					result = true;
				}else {
					System.out.println("Username Text field is Displayed but not ENABLED");
				}
			}else {
				System.out.println("Username Text field is Not Displaying");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Boolean PasswordField() {
		Boolean val,result=false;
		try {
			val = Password.isDisplayed();
			if(val) {
				Password.click();
				result= Password.isEnabled();
				if(result) {
					result = true;
				}else {
					System.out.println("Password Text field is Displayed but not ENABLED");
				}
			}else {
				System.out.println("Password Text field is Not Displaying");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Boolean CaptchaField() {
		Boolean val,result=false;
		try {
			val = Captcha.isDisplayed();
			if(val) {
				Captcha.click();
				result= Captcha.isEnabled();
				if(result) {
					result = true;
				}else {
					System.out.println("Captcha Text field is Displayed but not ENABLED");
				}
			}else {
				System.out.println("Captcha Text field is Not Displaying");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Boolean LoginbtnField() {
		Boolean val,result=false;
		try {
			val = Loginbtn.isDisplayed();
			if(val) {
				result= Loginbtn.isEnabled();
				if(result) {
					result = true;
				}else {
					System.out.println("Login Button is Displayed but not ENABLED");
				}
			}else {
				System.out.println("Login Button is Not Displaying");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String HomePage(String username, String password,String captcha) {
		String title = null;
		try {
			Username.sendKeys(username);
			Password.sendKeys(password);
			Captcha.sendKeys(captcha);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Loginbtn.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			title = driver.getTitle();
			try {
				String expected="MainNavigation";
				if(title.equalsIgnoreCase(expected)) {
					title = expected;
				}else if(Apperror.isDisplayed()){
					title = Apperror.getText();
				}else {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					global = new Global();
					title = global.Alert(driver);
				}
			}catch(Exception e) {
				log.Logerror("Expected result condition: "+"\n"+e);
			}
		}catch(Exception e) {
			log.Logerror("ERROR WHILE TRYING TO LOGIN BY PROVIDING USERNAME & PASSWORD"+"\n"+e);
		}
		return title;
	}
	
	public void Teardown() {
		try {
				driver.switchTo().defaultContent();
				wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
