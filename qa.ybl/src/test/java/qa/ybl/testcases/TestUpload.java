package qa.ybl.testcases;

import qa.ybl.base.*;

import java.awt.AWTException;
import org.testng.annotations.*;
import qa.ybl.pages.*;
import qa.ybl.utility.*;

public class TestUpload extends Base{

	public static String filepath = "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL";
	public static String datafile = "E:\\Rakesh\\Automation\\New folder\\Automation\\YBL\\uploaddata.xlsx";
	public static UploadPage up;
	public static Global global;
	
	public TestUpload() throws Exception {
		super();
	}
	
	@DataProvider
	public Object[][] getuploaddata(){
		Object[][] data = null;
		try {
			global = new Global();
			data = global.Getdata(datafile, "Sheet1");
			
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
	public void Upload_File(String Menu, String Username,String Password,String Captcha, String filepath, String filename,String res) {
		 res = null;
		try {
			up = new UploadPage();
			res = up.UploadFile(Menu, Username, Password,Captcha, filepath, filename);
			System.out.println("Result of the uploaded file is: "+res);
			try {
				global = new Global();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("^^^^^^^^^^-----THIS IS FROM WRITING THE RESULT TO EXCEL-----^^^^^^^^^^");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void Teardown() {
		try {
			up = new UploadPage();
			up.Teardown();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
