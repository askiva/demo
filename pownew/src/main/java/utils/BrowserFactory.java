/**
 * 
 */
package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author Me
 *
 */
public class BrowserFactory {
	
	static WebDriver driver;
	
	public static WebDriver startBrowser(String browserName,String url){
		
		if(browserName.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("chrome")){
				String path = System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", path+"\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("test-type");
				driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("ie")){
					driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

}
