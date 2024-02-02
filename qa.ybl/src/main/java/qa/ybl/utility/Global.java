package qa.ybl.utility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import qa.ybl.logging.Logging;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

public class Global {
	public Logging log;
	
	public void TakeScreenShot(WebDriver driver, String Filepath, String name) {
		log = new Logging();
		try {
			String Snapshot = Filepath+"\\"+"TS_"+name+".png";
			WebDriver aug = new Augmenter().augment(driver);
			TakesScreenshot sc = ((TakesScreenshot) aug);
			File file = sc.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(Snapshot));
            log.Loginfo("++++++++++SNAPSHOT IS TAKEN++++++++++");
            log.Loginfo("++++++++++File path and Name is: ++++++++++"+Snapshot);
		}catch(Exception e) {
			log.Logerror("Global.TakeScreenShot(WebDriver driver, String Filepath, String name)"+"\n"+e);
		}
	}
	
	public Object[][] Getdata(String filepath,String sheetname) {
		log = new Logging();
		Object[][] data = null;
		try {
			File file = new File(filepath);
			FileInputStream fs = new FileInputStream(file);
			
			XSSFWorkbook wb = new XSSFWorkbook(fs);
			XSSFSheet sheet = wb.getSheet(sheetname);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i=0;i<sheet.getLastRowNum();i++) {
				int cellnum = sheet.getRow(0).getLastCellNum()-1;
				for(int j=0;j<cellnum;j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			wb.close();
			fs.close();
		}catch(Exception e) {
			log.Logerror("Global.Getdata(String filepath,String sheetname)"+"\n"+e);
		}
		return data;
	}
	
	public void Writeresult(String filepath, String sheetname,String result, int rowValue) {
		log = new Logging();
		log.Loginfo("INSIDE THE WRITERESULT");
		try {
			File file = new File(filepath);
			FileInputStream fs = new FileInputStream(file);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);
//			int rowcount = sheet.getLastRowNum();
			//log.Loginfo("ROW is: "+rowValue);
				int cellnum = sheet.getRow(rowValue).getLastCellNum();
				log.Loginfo("Last Cell Num is: "+cellnum);
				XSSFCell cell = sheet.getRow(rowValue).createCell(cellnum);
				if(cell.getStringCellValue().isEmpty()) {
					//log.Loginfo("ROW WHILE WRITING is: "+rowValue);
					//log.Loginfo("CELL WHILE WRITING is: "+cellnum);
					cell.setCellValue(result);
					log.Loginfo("Updated the Result to a file");
				}
			
			FileOutputStream fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		}catch(Exception e) {
			log.Logerror("Global.Writeresult(String filepath, String sheetname,String result, int rowValue)"+"\n"+e);
		}
	}
	
	public String handlePopUp(WebDriver driver) {
		String text = null;
		log = new Logging();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			text = al.getText();
			al.accept();
		}catch(Exception e) {
			log.Logerror("Global.handlePopUp()"+"\n"+e);
		}
		return text;
	}
	
	public String Alert(WebDriver driver) {
		Global gl = new Global();
		log = new Logging();
		String message= null;
		try {
			log.Loginfo("Inside the Alert Method");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			//driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.alertIsPresent());
			Alert al = driver.switchTo().alert();
			message = al.getText();
			al.accept();
			log.Loginfo("Alert message is: "+message);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(Exception e) {
			log.Loginfo("Global.Alert(WebDriver driver)"+"\n"+e);
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				//driver.switchTo().defaultContent();
				Alert al = wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert();
				message = driver.switchTo().alert().getText();
				al.accept();
				log.Loginfo("\n"+"Alert message is: "+message);
			}catch(Exception f) {
				log.Logerror("Global.Alert(WebDriver driver).catch()"+"\n"+f);
			}
		}
		return message;
	}
	
	public String NewWindow(WebDriver driver,String Action) {
		String message = null;
		log = new Logging();
		log.Loginfo("Action which is passed to NewWindow() is: "+Action);
		Global gl= new Global();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			String Parent_window = driver.getWindowHandle();
			Set<String> New_window = driver.getWindowHandles();
			Iterator<String> i = New_window.iterator();
			while(i.hasNext()) {
				String Child_window = i.next();
				if(!Parent_window.equalsIgnoreCase(Child_window)) {
					log.Loginfo("Switched to New Window");
					driver.switchTo().window(Child_window);
					driver.manage().window().maximize();
					switch(Action) {
					
					case "approve":
						try {
							WebElement approve = driver.findElement(By.xpath("//*[@id='grdFile_ctl02_rdbARI_0']"));
							approve.click();
							log.Loginfo("Approve radio button is clicked");
							WebElement comment = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_txtComments']")));
							comment.clear();
							log.Loginfo("Comment text box is cleared");
							comment.sendKeys("Approved");
							log.Loginfo("Comment text box is entered");
							WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_btnSave']")));
							gl.buttonClick(driver, save);
							message = gl.Alert(driver);
						}catch(Exception e) {
							log.Loginfo("While Approving in swith case"+"\n"+e);
						}
						driver.switchTo().window(Parent_window);
						break;
					case "reject":
						try {
							WebElement reject = driver.findElement(By.xpath("//*[@id='grdFile_ctl02_rdbARI_1']"));
							reject.click();
							log.Loginfo("reject radio button is clicked");
							WebElement comment = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_txtComments']")));
							comment.clear();
							log.Loginfo("Comment text box is cleared");
							comment.sendKeys("Rejected");
							log.Loginfo("Comment text box is entered");
							WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_btnSave']")));
							gl.buttonClick(driver, save);
							message = gl.Alert(driver);
						}catch(Exception e) {
							log.Loginfo("While rejecting in swith case"+"\n"+e);
						}
						driver.switchTo().window(Parent_window);
						break;
					case "ignore":
						try {
							WebElement ignore = driver.findElement(By.xpath("//*[@id='grdFile_ctl02_rdbARI_2']"));
							ignore.click();
							log.Loginfo("ignore radio button is clicked");
							WebElement comment = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_txtComments']")));
							comment.clear();
							log.Loginfo("Comment text box is cleared");
							comment.sendKeys("Ignored");
							log.Loginfo("Comment text box is entered");
							WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='grdFile_ctl02_btnSave']")));
							gl.buttonClick(driver, save);
							message = gl.Alert(driver);
						}catch(Exception e) {
							log.Loginfo("While Ignoring in swith case"+"\n"+e);
						}
						driver.switchTo().window(Parent_window);
						break;
					}
				}
			}
		}catch(Exception e) {
			log.Logerror("Global.NewWindow(WebDriver driver,String Action)"+"\n"+e);
		}
		return message;
	}
	
	public WebDriver SwitchToFrame(WebDriver driver, WebElement Frame) {
		log = new Logging();
		WebDriver driver1 = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			driver.switchTo().defaultContent();
			driver1 = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Frame));
		}catch(Exception e) {
			log.Logerror("Global.SwitchToFrame()"+"\n"+e);
		}
		return driver1;
	}
	
	public void buttonClick(WebDriver driver, WebElement element) {
		log = new Logging();
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", element);
//			driver.manage().timeouts().implicitlyWait(10, Duration.ofSeconds(10));
		}catch(Exception e) {
			log.Logerror("Global.buttonClick(WebDriver driver, WebElement element)"+"\n"+e);
		}
	}
	
	
//	public void Writeresult(String filepath, String sheetname,String result) {
//		System.out.println("INSIDE THE WRITERESULT");
//		try {
//			File file = new File(filepath);
//			FileInputStream fs = new FileInputStream(file);
//			
//			XSSFWorkbook workbook = new XSSFWorkbook(fs);
//			XSSFSheet sheet = workbook.getSheet(sheetname);
//			int rowcount = sheet.getLastRowNum();
//			for(int row=1;row<=rowcount;row++) {
//				//XSSFRow row1 = sheet.getRow(row);
//				//int cellnum = row1.getLastCellNum();
//				System.out.println("ROWCOUNT is: "+rowcount);
//				System.out.println("ROW is: "+rowcount);
//				int cellnum = sheet.getRow(row).getLastCellNum();
//				XSSFCell cell = sheet.getRow(row).createCell(cellnum);
//				if(cell.getStringCellValue().isEmpty()) {
//					System.out.println("ROW WHILE WRITING is: "+row);
//					System.out.println("CELL WHILE WRITING is: "+cellnum);
//					cell.setCellValue(result);
//					break;
//				}
//			}
//			FileOutputStream fos = new FileOutputStream(filepath);
//			workbook.write(fos);
//			fos.close();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void selectDropdownValue(WebElement element, String text) {
		log = new Logging();
		try {
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(text);
			log.Loginfo("Selected the Drop Down Value");
		} catch (Exception e) {
//			((JavascriptExecutor)driver).executeAsyncScript("arguments[0].scrollIntoView(true);", element);
			log.Logerror("Global.selectDropdownValue(WebElement element, String text)"+"\n"+e);
		}
	}
	public void doubleClick(WebDriver driver, WebElement element) {
		try {
			Actions act = new Actions(driver);
			act.doubleClick(element).build().perform();
		} catch (Exception e) {
			log.Logerror("Global.doubleClick(Driver driver, WebElement element)"+"\n"+e);
		}
	}
	
}
