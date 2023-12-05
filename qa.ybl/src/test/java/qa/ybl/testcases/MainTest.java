package qa.ybl.testcases;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.*;

import qa.ybl.base.Base;
import qa.ybl.logging.Logging;
import qa.ybl.pages.UploadPage;
import qa.ybl.utility.Global;

public class MainTest extends Base{

	public String pass = prop.getProperty("passsnapshot");
	public String Fail = prop.getProperty("failsnapshot");
	public  String datafile = prop.getProperty("datafilepath");
	public String sheetname = prop.getProperty("usheetname");
	public  UploadPage up;
	public  Global global;
	public Logging log;
	
	public MainTest() throws Exception{
		super();
	}
	
	@DataProvider
	public Object[][] getdata(){
		Object[][] data = null;
		try {
			global = new Global();
			data = global.Getdata(datafile, sheetname);
			log = new Logging();
			log.Loginfo(Arrays.deepToString(data));
		}catch(Exception e) {
			log.Logerror("Get Data"+"\n"+e);
		}
		return data;
	}
	
	@BeforeMethod
	public void Setup() {
		log = new Logging();
		try {
			Initialization();
		}catch(Exception e) {
			log.Logerror("Setup() from MainTest "+"\n"+e);
		}
	}
	
	@Test(priority=1,enabled=true)
	public void UploadFile(String TSID,String Description,String TestFlag, String Username, String Password,String Captcha,String Menu,
			String Filepath, String Filename,String Expectedresult, String ActualResult) {
		 String actual, batchid = null, res="NA";
		 int rowValue = Integer.valueOf(TSID);
		 global = new Global();
		try {
			up = new UploadPage();
			actual = up.UploadFile(TestFlag,Menu, Username, Password,Captcha, Filepath, Filename);
			if (actual.contains(Expectedresult)) {
				batchid = up.Getuploaddetails(actual, Filename);
				if(!batchid.equals(res)) {
					log.Loginfo("Inside if condition in test");
					global.Writeresult(datafile, sheetname, "PASS "+actual+"AND Batch ID is: "+batchid, rowValue);
					global.TakeScreenShot(driver, pass, TSID);
					Assert.assertTrue(true);
				}else {
					log.Loginfo("Inside else condition in test");
					global.Writeresult(datafile, sheetname, "PASS "+actual, rowValue);
					global.TakeScreenShot(driver, pass, TSID);
					Assert.assertTrue(true);
				}
			}else if(actual.contains("Skipped")){
				global.Writeresult(datafile, sheetname, actual, rowValue);
				Assert.assertTrue(true);
				driver.close();
			}else {
				global.Writeresult(datafile, sheetname, "FAIL "+actual, rowValue);
				global.TakeScreenShot(driver, Fail, TSID);
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			log.Logerror("FROM UploadFile Test Case:"+"\n"+e);
		}
	}

	
	@AfterMethod
	public void Teardown() {
		log = new Logging();
		try {
			up = new UploadPage();
			up.Teardown();
		}catch(Exception e) {
			log.Logerror("Error in TearDown Method in Test "+e);
		}
	}
}
