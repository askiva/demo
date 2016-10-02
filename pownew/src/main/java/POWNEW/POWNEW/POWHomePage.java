package POWNEW.POWNEW;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POWHomePage {
	WebDriver driver;
	By link = By.cssSelector("div.mt-card-content");
			
	POWHomePage(WebDriver driver){
		this.driver=driver;
	}
	
 public void powHomePageLink(){
	 List <WebElement> elem = driver.findElements(link);
}

	

}
