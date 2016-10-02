package POWNEW.POWNEW;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.CaptureScreen;
import common.CompareString;
import common.ReadConfig;
import utils.BrowserFactory;
import utils.DataReader;

public class VerifyExcel {
	WebDriver driver; 
	String inputFile;
	ExtentReports report = null;
	ExtentTest logger = null;
	DataReader xlsx;
	int rowCount[]={0,0};
	int sheetCount;
	int rowNum[]={0,0};
	
	public void verifySetUp()throws Exception{
		/*InputStream inputProperty = new FileInputStream("./Config/common.properties");
		propFile.load(inputProperty);
		*/
		ReadConfig rObj = new ReadConfig("./Config/common.properties");
		String inputFile1=rObj.readKey("TestDataRoot");
		System.out.println("Check "+inputFile1);
		String temp=rObj.readKey("POWTest",0);
		inputFile=inputFile1.concat(temp);
		System.out.println("Check2 "+temp);
		
		/*String temp = propFile.getProperty("POWTest").split(":")[0];
		inputFile = propFile.getProperty("TestDataRoot").concat(temp);
		*/
		//inputFile="./TestData/pownewlinks.xlsx";
		xlsx=new DataReader(inputFile);
		/*rowCount[0]=xlsx.getRowCount(0);
		rowCount[1]=xlsx.getRowCount(1);
		sheetCount=xlsx.getSheetCount();
		System.out.println("Total Sheets= "+sheetCount);
		System.out.println("Total Rows 0 = "+rowCount[0]);
		System.out.println("Total Rows 1 = "+rowCount[1]);
		*/
	}
	
	
	@BeforeClass
	public void setup() throws Exception {
		String reportFile="./Reports/Current/results.html";
		report=new ExtentReports(reportFile);

		verifySetUp();
		driver=BrowserFactory.startBrowser("chrome","http://104.238.103.200:8080/POWNew/");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.cssSelector("a[href$='/startadmin.do']")).click();
		try {
			Thread.sleep(500);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("email")).click();
		driver.findElement(By.id("email")).sendKeys("pow_a32@yahoo.com");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("powadmin");
		driver.findElement(By.id("loginbutton")).click();
		
	}
	
	@DataProvider(name="links")
	public Object[][] dataProvider401() {
		Object[][] temp=null;
		int sheetNum=0;
		int rows=xlsx.getLastRowIndex(sheetNum);
		int cols=3;
		int startRowIndex=1;
		temp=new Object[rows][cols];
		for(int i=0;i<rows;i++)
		{
			
			temp[i][0]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 0);
			temp[i][1]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 1);
			temp[i][2]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 2);
		}
		return temp;
	}
	
	@DataProvider(name="Secondlinks")
	public Object[][] dataProvider402() {
		Object[][] temp1=null;
		int sheetNum=1;
		int rows=xlsx.getLastRowIndex(sheetNum);
		//System.out.println("check point "+rows);
		int cols=4;
		int startRowIndex=2;
		
		temp1=new Object[rows-1][cols];
		for(int i=0;i<rows-1;i++)
		{
			temp1[i][0]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 0);
			temp1[i][1]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 1);
			temp1[i][2]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 2);
			temp1[i][3]=xlsx.getDataTypeofVariable(sheetNum,startRowIndex+i, 3);
			
		}
		return temp1;
	}
	
	//@Test(dataProvider="Secondlinks")
	public void verifyLinks(String tcnum, String linkText, String verifyKey,String pageSource) {
		/*
		System.out.println("--");
		System.out.print("tcnum ="+tcnum);
		System.out.print("==|==link ="+linkText);
		System.out.print("==|==key ="+verifyKey);
		System.out.print("==|==key ="+pageSource);
		System.out.println("--");
		
		*/
	}

	
	@Test(dataProvider="links")
	public void verifyLinks(String tcnum, String linkText, String verifyKey) {
		System.out.println("--");
		System.out.print("tcnum ="+tcnum);
		System.out.print("==|==link ="+linkText);
		System.out.print("==|==key ="+verifyKey);
		System.out.println("--");
				
		
		driver.findElement(By.linkText(linkText)).click();
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("page-title")));
				
		String someVal=driver.findElement(By.className("page-title")).getText();
		String actualTitle=driver.getTitle();
		Assert.assertTrue(CompareString.compareText(actualTitle,verifyKey));
		logger=report.startTest("TC001_VerifyValidLogin");
		logger.log(LogStatus.INFO, "Test Case Started");
		logger.log(LogStatus.INFO, "Input Condition: Valid Login Credentials provided");
		
	}

	
	@AfterMethod
	public void assertEachTest(ITestResult result) throws Exception{
		CaptureScreen printObj = new CaptureScreen();
		System.out.println("After Each Test "+result.getName());
		System.out.println("After Each Test "+result.isSuccess());
			
		String spath;
		if(result.getStatus()==ITestResult.FAILURE){
			
			//System.out.println("Print Failure-"+result.getStatus()+result.getName());
		spath= printObj.printScreenShot(driver,result.getName());
		System.out.println("spath "+spath);
		}else{
			spath= printObj.printScreenShot(driver,result.getName());
			System.out.println("spath "+spath);
		}
		//String sshot = logger.addScreenCapture("C:\\Users\\Me\\workspace\\pownew\\screenshot\\output_screen899.png");
		//String sshot = logger.addScreenCapture("./screenshot/output_screen899.png");
		String sshot = logger.addScreenCapture("../../"+spath);
		logger.log(LogStatus.FAIL, "logs:");
		logger.log(LogStatus.PASS, "logs:",sshot);
		
		
		/*String errMessage=result.getThrowable().getMessage();
		logger.log(LogStatus.FAIL, errMessage);
		 	*/
		
		
		report.endTest(logger);
		report.flush();
		
	}
	
	@AfterClass
	public void tearDown() {
		//System.out.println("Tear Down Here !!");
		driver.close();
	}

}