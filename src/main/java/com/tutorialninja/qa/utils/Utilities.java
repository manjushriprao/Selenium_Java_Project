package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=5;

	public static String generateEmailwithTimeStamp() {
		Date date = new Date();
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "tester"+timestamp+"@gmail.com";
	}
	
	public static Object[][] getTestData(String sheetName) {
		
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\Tutorialninja_testdata.xlsx");
		XSSFWorkbook workbook = null;
		try {
		FileInputStream fis= new FileInputStream(f);
		workbook= new XSSFWorkbook(fis);
		}catch(Exception e) {
			
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		int columnCount=sheet.getRow(0).getLastCellNum();//get any of the row and get the last cell number
		
		Object[][] data= new Object[rowCount][columnCount];
		
		for(int i=0;i<rowCount;i++) {
			XSSFRow r= sheet.getRow(i+1);
			
			for(int j=0;j<columnCount;j++) {
				XSSFCell cell = r.getCell(j);
				CellType celltype=cell.getCellType();
				
				switch(celltype) {
				
				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				
				}
			}	
		}
		return data;
	}
	public static String takeScreenshot(WebDriver driver,String testName)
	{
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destScreenshotPath= System.getProperty("user.dir")+"\\Screenshot\\"+testName+".png";
		try {
			FileHandler.copy( srcScreenshot, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destScreenshotPath;
	}
}
