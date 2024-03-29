package qa.ybl.testcases;

import qa.ybl.base.*;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.*;
import qa.ybl.pages.*;
import qa.ybl.utility.*;
import java.util.Arrays;


public class TestLogin extends Base{
	public static LoginPage login;
	public Global global;
	public String datafile = prop.getProperty("datafilepath");
	public String sheetname = prop.getProperty("lsheetname");
	public  String Pdestfile = prop.getProperty("passsnapshot");
    public  String Fdestfile = prop.getProperty("failsnapshot");	
    
	public TestLogin() throws Exception{
		super();
	}
	
	@DataProvider
	public Object[][] logindetails(){
		global = new Global();
		Object[][] data = global.Getdata(datafile, sheetname);
		System.out.println(Arrays.deepToString(data));
		return data;
	}
	
	
	@BeforeTest
	public void setup() throws AWTException {
		Initialization();
	}

	@Test(priority=1,enabled=false)
	public void Username_Feild() {
		try {
			global = new Global();
			login = new LoginPage();
			Boolean username = login.UsernameField();
			if(username) {
				global.TakeScreenShot(driver, Pdestfile, "UsernameField");
				Assert.assertTrue(true);
				driver.close();
				//driver.close();
			}else {
				global.TakeScreenShot(driver, Fdestfile, "UsernameField");
				Assert.assertTrue(false);
				driver.close();
			}
		}catch(Exception e) {
			log.Logerror("!!!!!-----Username_Feild()-----!!!!!"+"\n"+e);
		}
	}
	
	@Test(priority=2,enabled=false)
	public void Password_Feild() {
		try {
			login = new LoginPage();
			global = new Global();
			Boolean password = login.PasswordField();
			if(password) {
				global.TakeScreenShot(driver, Pdestfile, "PasswordField");
				Assert.assertTrue(true);
				driver.close();
			}else {
				global.TakeScreenShot(driver, Fdestfile, "PasswordField");
				Assert.assertTrue(false);
				driver.close();
			}
		}catch(Exception e) {
			log.Logerror("!!!!!-----Password_Feild()-----!!!!!"+"\n"+e);
		}
	}
	
	@Test(priority=3,enabled=false)
	public void Captcha_Feild() {
		try {
			login = new LoginPage();
			global = new Global();
			Boolean captcha = login.CaptchaField();
			if(captcha) {
				global.TakeScreenShot(driver, Pdestfile, "CaptchaField");
				Assert.assertTrue(true);
				driver.close();
			}else {
				global.TakeScreenShot(driver, Fdestfile, "CaptchaField");
				Assert.assertTrue(false);
				driver.close();
			}
		}catch(Exception e) {
			log.Logerror("!!!!!-----Captcha_Feild()-----!!!!!"+"\n"+e);
		}
	}
	
	@Test(priority=4,enabled=false)
	public void Login_Button() {
		try {
			login = new LoginPage();
			Boolean loginbutton = login.LoginbtnField();
			if(loginbutton) {
				global = new Global();
				global.TakeScreenShot(driver, Pdestfile, "LoginButton");
				Assert.assertTrue(true);
				driver.close();
			}else {
				global.TakeScreenShot(driver, Fdestfile, "LoginButton");
				Assert.assertTrue(false);
				driver.close();
			}
		}catch(Exception e) {
			log.Logerror("!!!!!-----Login_Button()-----!!!!!"+"\n"+e);
		}
	}
	
	@Test(priority=5, dataProvider="logindetails")
	public void Login_to_app(String TSID,String Description,String Username,String Password,String Captcha,
			String Expected,String Result) {
		try {
			login = new LoginPage();
			int rowValue = Integer.valueOf(TSID);
			System.out.println(rowValue);
			String actual = login.HomePage(Username, Password, Captcha);
			String expected = Expected;
			log.Loginfo("Expected result is: "+Expected);
			if(actual.contains(expected)) {
				try {
					global = new Global();
					global.TakeScreenShot(driver, Pdestfile, TSID);
					global.Writeresult(datafile, sheetname, "PASS"+" "+actual, rowValue);
				}catch(Exception e) {
					log.Logerror("*****-----UNABLE TO TAKE SNAPSHOT IN LoginPage()-----*****"+"\n"+e);
				}
				login.Teardown();
				Assert.assertTrue(true);
			}else {
				try {
					global = new Global();
					global.TakeScreenShot(driver, Fdestfile, TSID);
					global.Writeresult(datafile, sheetname, "FAIL"+" "+actual, rowValue);
				}catch(Exception e) {
					log.Logerror("*****-----UNABLE TO TAKE SNAPSHOT IN LoginPage()-----*****"+"\n"+e);
				}
				log.Logerror("*********************Actual Title of the Home is: "+actual.toUpperCase()+"*********************");
				driver.close();
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			log.Logerror(""+e);
		}
	}
	
	@AfterTest
	public void quitdriver() {
		driver.close();
	}
	
//	@Test(priority=5, dataProvider="logindetails")
//	public void Login_to_app(String TSID,String Username,String Password,String Captcha,String Result) {
//		try {
//			login = new LoginPage();
//			String actual = login.HomePage(Username, Password, Captcha);
//			String expected = "MainNavigation";
//			if(actual.equalsIgnoreCase(expected)) {
//				try {
//					global = new Global();
//					global.TakeScreenShot(driver, "Pdestfile", "HomePage");
//					global.Writeresult(datafile, "LoginResult", "PASS"+" "+actual);
//				}catch(Exception e) {
//					e.printStackTrace();
//					System.out.println("*****-----UNABLE TO TAKE SNAPSHOT IN LoginPage()-----*****");
//				}
//				login.Teardown();
//				driver.close();
//				Assert.assertTrue(true);
//			}else {
//				try {
//					global = new Global();
//					global.TakeScreenShot(driver, "Fdestfile", "HomePage");
//					global.Writeresult(datafile, "LoginResult", "FAIL"+" "+actual);
//				}catch(Exception e) {
//					e.printStackTrace();
//					System.out.println("*****-----UNABLE TO TAKE SNAPSHOT IN LoginPage()-----*****");
//				}
//				System.out.println("*********************Actual Title of the Home is: "+actual.toUpperCase()+"*********************");
//				Assert.assertTrue(false);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
