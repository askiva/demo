package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataReader {

	WebDriver driver = null;
	static int rows = 0;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public DataReader(String filePath) {

		try {
			File xlsFile = new File(filePath);
			FileInputStream fis = new FileInputStream(xlsFile);
			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Return the Celltype
	public static int getCellType(int sheetNo, int row, int col) {
		//System.out.println("in getCellType reading sheet" + sheetNo + " row= " + row + " col= " + col);
		return workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getCellType();
	}

	public static String getStringValue(int sheetNo, int row, int col) {
		return workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getStringCellValue();
	}

	public static int getIntergerValue(int sheetNo, int row, int col) {
		return (int) workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getNumericCellValue();
	}

	public static boolean getBooleanValue(int sheetNo, int row, int col) {
		return workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getBooleanCellValue();
	}

	public static float getFloatValue(int sheetNo, int row, int col) {
		return (float) workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getNumericCellValue();
	}

	public Date getDateValue(int sheetNo, int row, int col) {
		return workbook.getSheetAt(sheetNo).getRow(row).getCell(col).getDateCellValue();
	}

	public String getDataTypeofVariable(int sheetNo, int row, int col) {
		String defaultValue = "none";
		int returnDataType = getCellType(sheetNo, row, col);
		switch (returnDataType) {
		case 0:
			int num = getIntergerValue(sheetNo, row, col);
			defaultValue = Integer.toString(num); 
			break;
		case 1:
			defaultValue = getStringValue(sheetNo, row, col);
			break;
		case 3:
			defaultValue = "null";
			break;
		case 4:
			boolean b1=getBooleanValue(sheetNo, row, col);
			defaultValue=Boolean.toString(b1);
			break;
		default:
			defaultValue ="none";
		}
		return defaultValue;
	}

	public int getRowCount(int sheetIndex) {
		return workbook.getSheetAt(sheetIndex).getLastRowNum()+1;
	}

	public int getSheetCount() {
		return workbook.getNumberOfSheets();
	}

	public int getActiveSheet() {
		return workbook.getActiveSheetIndex();
	}

	public int getLastRowIndex(int sheetNumber) {
		return workbook.getSheetAt(sheetNumber).getLastRowNum();
	}

	public int getLastColIndex(int sheetNumber, int rowNum) {
		return workbook.getSheetAt(sheetNumber).getRow(rowNum).getLastCellNum();
	}

}
