package TNT_Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class LogOut {
	
	WebDriver driver;
	
		//Constructor that will be automatically called as soon as the object of the class is created
			public LogOut(WebDriver driver) {
				this.driver=driver;
			}
	
						By Logout  = By.xpath("//div[@class='loginDisplay']//a[@id='HeadLoginView_HeadLoginStatus' and contains(., 'Log Out')]");
						By Status = By.id("HeadLoginView_HeadLoginStatus");
						
			
			public void logOut() throws InterruptedException {
				
						driver.findElement(Logout).click();
						
						Assert.assertTrue(driver.getPageSource().contains("Log In"));
						
						Reporter.log("User Successfully Logout From the APP ");
						
						
			}
			
			
	

}
