package common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	Properties propFile ;
		
	
	public ReadConfig(String filePath){
		
		try{
			propFile=new Properties();	
			InputStream inputProperty = new FileInputStream(filePath);
			propFile.load(inputProperty);
		}catch( Exception e)
		{
		 e.printStackTrace();
		}
	}
	
	/*
	 * inputFile=./TestData/pownewlinks.xlsx
TestDataRoot=./TestData/
ReportsRoot=./Reports/Current/
POWTest=pownewlinks.xlsx:results.html
inputFile=rObj.readConfigValue("TestDataRoot",0);
	 */
	
	public String readKey(String propertyValue, int index ) throws Exception{
		String temp = propFile.getProperty(propertyValue).split(":")[index];
    	return temp;
	}
	public String readKey(String propertyValue) throws Exception{
		String filePath = propFile.getProperty(propertyValue);
		return filePath;
		}
	
	
	

}
