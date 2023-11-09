package qa.ybl.utility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.*;

public class Global {
	
	public void TakeScreenShot(WebDriver driver, String Filepath, String name) {
		
		try {
			TakesScreenshot sc = ((TakesScreenshot) driver);
			File file = sc.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file,new File(Filepath+"\\"+name+".png"));
            System.out.println("++++++++++SNAPSHOT IS TAKEN++++++++++");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("-----^^^^^-----UNABLE TO TAKE SCREENSHOT IN TakeScreenShot()-----^^^^^-----");
		}
	}
	
	public Object[][] Getdata(String filepath,String sheetname) {
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
			e.printStackTrace();
		}
		return data;
	}
	
	public void Writeresult(String filepath, String sheetname,String result, int rowValue) {
		System.out.println("INSIDE THE WRITERESULT");
		try {
			File file = new File(filepath);
			FileInputStream fs = new FileInputStream(file);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);
//			int rowcount = sheet.getLastRowNum();
				System.out.println("ROW is: "+rowValue);
				int cellnum = sheet.getRow(rowValue).getLastCellNum();
				XSSFCell cell = sheet.getRow(rowValue).createCell(cellnum);
				if(cell.getStringCellValue().isEmpty()) {
					System.out.println("ROW WHILE WRITING is: "+rowValue);
					System.out.println("CELL WHILE WRITING is: "+cellnum);
					cell.setCellValue(result);
				}
			
			FileOutputStream fos = new FileOutputStream(filepath);
			workbook.write(fos);
			fos.close();
		}catch(Exception e) {
			e.printStackTrace();
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
}
