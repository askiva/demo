package POWNEW.POWNEW;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.CaptureScreen;
import utils.BrowserFactory;

public class VerifyLogin {
	WebDriver driver;

	
	@BeforeMethod
	public void setBrowser(){
		driver=BrowserFactory.startBrowser("chrome","http://104.238.103.200:8080/POWNew/startadmin.do");
	}
	
	@Test
	public void verifyLogin(){
		POWLogin obj= new POWLogin(driver);
		obj.login();
		
		String title=driver.getTitle();
		System.out.println("Check -"+title);
		Assert.assertEquals(driver.getTitle(), "Some Text");
	}
	
	@AfterMethod
	public void printResult(ITestResult result){
		//CaptureScreen printObj = new CaptureScreen();
		System.out.println("After Each Test "+result.getName());
		
		
	}
}
