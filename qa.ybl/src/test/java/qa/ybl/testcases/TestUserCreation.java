package qa.ybl.testcases;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.*;

import qa.ybl.base.Base;
import qa.ybl.logging.Logging;
import qa.ybl.pages.*;
import qa.ybl.utility.Global;

public class TestUserCreation extends Base {
	protected Logging log;
	protected LoginPage login;
	protected String datafile = prop.getProperty("datafilepath");
	protected String sheetname = prop.getProperty("userSheetname");
	protected  String Pdestfile = prop.getProperty("passsnapshot");
	protected  String Fdestfile = prop.getProperty("failsnapshot");
	protected Global gl;
	protected UserCreationPage uc;
	protected HomePage home;

	public TestUserCreation() throws Exception {
		super();
	}
	
	@DataProvider
	public Object getUserCreationData() {
		log = new Logging();
		gl = new Global();
		Object[][] data = gl.Getdata(datafile, sheetname);
		log.Loginfo(Arrays.deepToString(data));
		return data;
	}
	
	@BeforeTest
	public void setup() throws AWTException{
		Initialization();
	}
	
	
	@Test(dataProvider="getUserCreationData")
	public void testCreateUser(String TSID,String TestDescription,String TestFlag,String Username,String lPassword,String Captcha,String Menu,String Profile,String Entity1,String Entity2,String OperatorCode,String Title,
			String UserName,String LoginName,String Password,String ConfirmPassword,String Status,String ExpectedResult,
			String ActualResult) {
		String actual = null;
		log = new Logging();
		gl = new Global();
		int row = Integer.valueOf(TSID);
		try {
			log.Loginfo("Row Number is: "+row);
			uc = new UserCreationPage();
			actual = uc.createUser(Menu,TestFlag,Username,lPassword,Captcha, Profile, Entity1, Entity2, OperatorCode, Title, UserName, LoginName, Password, ConfirmPassword, Status);
//			String expected= ExpectedResult; 
//			if(actual.equalsIgnoreCase(expected)) {
//				log.Loginfo("Inside Assert True");
//				gl.Writeresult(datafile, sheetname, actual, row);
//				uc.Teardown();
//				Assert.assertTrue(true);
//			}else {
//				log.Loginfo("Inside Assert False");
//				gl.Writeresult(datafile, sheetname, actual, row);
//				uc.Teardown();
//				Assert.fail();
//			}
//			home = new HomePage();
//			home.GetintoTransaction(Menu, uc.Frame);
			gl.Writeresult(datafile, sheetname, actual, row);
			uc.Teardown();
		}catch(Exception e) {
			log.Logerror("TestUserCreation.testCreateUser()"+"\n"+e);
		}
	}
	
	
	@AfterTest
	public void Teardown() {
		log = new Logging();
		log.Loginfo("Inside the Test TearDown");
		try {
			uc = new UserCreationPage();
			log.Loginfo("Calling the TearDown Method");
			uc.Teardown();
		}catch(Exception e) {
			log.Logerror("TestUserCreation.Teardown()"+"\n"+e);
		}
	}
}
