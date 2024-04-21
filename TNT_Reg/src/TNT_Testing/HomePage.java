package TNT_Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
//import org.testng.annotations.Test;


public class HomePage {

String logged_user,Welcome;
WebDriver driver;

	
	//Constructor that will be automatically called as soon as the object of the class is created
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Locator for logged in User Details, welcome, shop floor option
	By Welcome_Screen = By.xpath("//div[@class='loginDisplay']");
	By LoginUser_Details  = By.xpath("//*[@id='HeadLoginView_HeadLoginName']");
	By Shop_Floor  = By.xpath("//*[contains(text(),'Shop Floor')]");
	
	public void welcomeMSG() throws InterruptedException {
			
			Assert.assertTrue(driver.getPageSource().contains("Welcome"), "HOME PAGE NOT DISPLAY");
			Reporter.log( "User Logged In Successfully In APP");
			
		}
	
	public void Logged_in_User() throws InterruptedException {
		
		//wait until the details are visible.
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(LoginUser_Details));
		
		logged_user = driver.findElement(LoginUser_Details).getText();
		Assert.assertEquals(logged_user, logged_user,"LOGGED IS USER DETAILS DOES NOT DISPLAYED");
		Reporter.log("Logged In User Is " + logged_user);
		
		}
	
	public void clickOnShopFloor() {	
		
		//Hover mouse pointer on Shop Floor Menu Item
		Actions action = new Actions(driver);
		WebElement webelement = driver.findElement(Shop_Floor);
		action.moveToElement(webelement);
		action.perform();
		
	}
	
	
	
	
	

}
