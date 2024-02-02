package qa.ybl.testcases;

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

	public TestUserCreation() throws Exception {
		super();
	}
	
	@DataProvider
	public Object getUserCreationData() {
		gl = new Global();
		Object[][] data = gl.Getdata(datafile, sheetname);
		return data;
	}
	
	@BeforeTest
	public void setup() {
		Initialization();
	}
	
	@Test(dataProvider="getUserCreationData")
	public void testCreateUser(String TSID,String TestDescription,String TestFlag,String Username,String lPassword,String Captcha,String Menu,String Profile,String Entity1,String Entity2,String OperatorCode,String Title,
			String UserName,String LoginName,String Password,String ConfirmPassword,String Status,String ExpectedResult,
			String Result) {
		String actual = null;
		log = new Logging();
		gl = new Global();
		int row = Integer.valueOf(TSID);
		try {
			uc = new UserCreationPage();
			uc.createUser(Menu,TestFlag,Username,lPassword,Captcha, Profile, Entity1, Entity2, OperatorCode, Title, UserName, LoginName, Password, ConfirmPassword, Status);
		}catch(Exception e) {
			log.Logerror("TestUserCreation.testCreateUser()"+"\n"+e);
		}
	}
}
