package qa.ybl.testcases;

import qa.ybl.base.*;
import qa.ybl.logging.Logging;

import java.util.Arrays;
import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.*;
import qa.ybl.pages.*;
import qa.ybl.utility.*;

public class TestUpload extends Base{

	public  String datafile = prop.getProperty("datafilepath");
	public String sheetname = prop.getProperty("usheetname");
	public  UploadPage up;
	public  Global global;
	public  String Pdestfile = prop.getProperty("passsnapshot");
    public  String Fdestfile = prop.getProperty("failsnapshot");
	
	public TestUpload() throws Exception {
		super();
	}
	
	@DataProvider
	public Object[][] getuploaddata(){
		Object[][] data = null;
		try {
			global = new Global();
			data = global.Getdata(datafile, sheetname);
			log = new Logging();
			log.Loginfo(Arrays.deepToString(data));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@BeforeMethod
	public void Setup() throws AWTException {
		Initialization();
	}
	
	@Test(dataProvider = "getuploaddata")
	public void Upload_File(String TSID,String Description,String TestFlag, String Username, String Password,String Captcha,String Menu,
			String Filepath, String Filename,String Expectedresult, String ActualResult) {
		 String actual = null;
		 int rowValue = Integer.valueOf(TSID);
		try {
			up = new UploadPage();
			actual = up.UploadFile(TestFlag,Menu, Username, Password,Captcha, Filepath, Filename);
			System.out.println("Result of the uploaded file is: "+actual);
			String exp = Expectedresult;
			try {
				if(actual.contains(Expectedresult)) {
					global = new Global();
					global.Writeresult(datafile, sheetname, "PASS "+actual, rowValue);
					global.TakeScreenShot(driver, Pdestfile, TSID);
					Assert.assertTrue(true);
				}else {
					global.Writeresult(datafile, sheetname, "FAIL "+actual, rowValue);
					global.TakeScreenShot(driver, Fdestfile, TSID);
					Assert.assertTrue(false);
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("^^^^^^^^^^-----THIS IS FROM WRITING THE RESULT TO EXCEL-----^^^^^^^^^^");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider = "getuploaddata")
	public void Upload_Files(String TSID,String Description,String TestFlag, String Username, String Password,String Captcha,String Menu,
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
					global.TakeScreenShot(driver, Pdestfile, TSID);
					Assert.assertTrue(true);
				}else {
					log.Loginfo("Inside else condition in test");
					global.Writeresult(datafile, sheetname, "PASS "+actual, rowValue);
					global.TakeScreenShot(driver, Pdestfile, TSID);
					Assert.assertTrue(true);
				}
			}else {
				global.Writeresult(datafile, sheetname, "FAIL "+actual, rowValue);
				global.TakeScreenShot(driver, Fdestfile, TSID);
				Assert.assertTrue(false);
			}
		}catch(Exception e) {
			log.Logerror("FROM Upload_Files Test Case:"+e);
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
