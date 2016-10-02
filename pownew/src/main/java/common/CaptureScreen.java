package common;

import java.io.File;
import java.nio.file.Path;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class CaptureScreen {
	public String printScreenShot(WebDriver driver,String fileName) throws Exception{
		long dateTime = System.currentTimeMillis();
		
		  File outFile = new File("./Reports/Current/screenshot/"+fileName+dateTime+".png");
		  TakesScreenshot sshot = (TakesScreenshot)driver;
		  File inputFile = sshot.getScreenshotAs(OutputType.FILE);
		  Files.copy(inputFile, outFile);
		  return outFile.getPath();
	
		  
		
	}

	public void printScreenShot(WebDriver driver) throws Exception{
		
		long dateTime = System.currentTimeMillis();
		
			File outFile = new File("./screenshot/"+dateTime+".png");
		    TakesScreenshot sshot = (TakesScreenshot)driver;
		    File inputFile = sshot.getScreenshotAs(OutputType.FILE);
		    Files.copy(inputFile, outFile);
		
		  
		
	}
}
