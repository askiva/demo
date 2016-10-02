package POWNEW.POWNEW;

import org.testng.annotations.BeforeClass;

import utils.DataReader;

public class ReadExcel {
	
	String filePath = "C:\\Users\\Me\\workspace\\pownew\\TestData\\pownewlinks.xlsx";
	int row;
	int col;
	int sheetNo;
	@BeforeClass
	public void readExcelSheet(){
		row=7;
		col=3;
		sheetNo=1;
		DataReader dataReader = new DataReader(filePath);
		
		dataReader.getDataTypeofVariable(sheetNo,row, col);

	}
	
}
