package TNT_Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;




public class LoginPage extends Login_TC1 {
	
	String result,error;
	//String error = "NoError";
	int count;

	WebDriver driver;
	//Constructor that will be automatically called as soon as the object of the class is created
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Locator for User_Name, Password and login button
	By LoginBtn = By.id("MainContent_LoginUser_LoginButton");
	By UserName = By.id("MainContent_LoginUser_UserName");
	By Password = By.id("MainContent_LoginUser_Password");
	By Password_Blank = By.id("MainContent_LoginUser_LoginUserValidationSummary");
	By Login = By.id("HeadLoginView_HeadLoginStatus");
	
	// Entering User Name and Password.
	
	
	public void enterUser() throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(UserName).clear();
		driver.findElement(UserName).sendKeys("testAdmin");
		Thread.sleep(1000);
		
	}
	
	public void enterPassword() throws InterruptedException {
		driver.findElement(Password).click();
		driver.findElement(Password).sendKeys("Password234");
		Thread.sleep(1000);
	}

	public void loginWithoutUserAndPassword() {
		
		clickLogin();
		
		error = driver.findElement(Password_Blank).getText();
		
		Assert.assertTrue((error.contains("User Name is required."))&& (error.contains("Password is required.")));
		
		Reporter.log("Without USER ID and PASSWORD Not able to login into APP, " +error);
		
	}
		
	public void loginWithoutPassword() throws InterruptedException {
		
		enterUser();
		clickLogin();
		error = driver.findElement(Password_Blank).getText();
		Assert.assertEquals(error, "Password is required.");
		
		Reporter.log("Without PASSWORD Not able to login into APP, " +error);
		
	}
		
	public void clickLogin() 
		{
			driver.findElement(LoginBtn).click();
			
		}
	
	
	public void login() throws InterruptedException 
	{
		driver.findElement(Login).click();
		Thread.sleep(1000);
		driver.findElement(UserName).click();
		driver.findElement(UserName).sendKeys("testAdmin");
		Thread.sleep(1000);
		driver.findElement(Password).click();
		driver.findElement(Password).sendKeys("Password234");
		Thread.sleep(500);
		clickLogin();
	}
		
	
}