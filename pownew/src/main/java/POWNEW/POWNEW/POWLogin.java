package POWNEW.POWNEW;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POWLogin {
	WebDriver driver;
	
	By email = By.cssSelector("input[placeholder^='Email']");
	By password= By.cssSelector("input[placeholder^='Password']");
	By login= By.cssSelector("button#loginbutton");
	
	POWLogin(WebDriver driver){
		this.driver=driver;
	}
	
	public void login(){
		setEmail();
		setPassword();
		driver.findElement(login).click();
		
	}
	
	public void setEmail(){
		driver.findElement(email).click();
		driver.findElement(email).sendKeys("pow_a32@yahoo.com");
	}
	public void setPassword(){
		driver.findElement(password).click();
		driver.findElement(password).sendKeys("powadmin");
	}


}
