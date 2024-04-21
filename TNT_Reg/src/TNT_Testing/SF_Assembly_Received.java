package TNT_Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SF_Assembly_Received {

	WebDriver driver;
	String error;

	public SF_Assembly_Received(WebDriver driver) {
		this.driver = driver;
	}
	// Locator for (FID) Assembly Received in Shop Floor Menu Item
	By Assembly_Received = By.xpath("//a[@class='level2 dynamic'][text()='(FID) Assembly Received']"); 
	By ScanPCB_Serial = By.id("MainContent_PCBSerialTextBox"); // XPath for the SCAN PCB Serial Number.

	public void assemblyReceivedClick() throws InterruptedException {

		LogOut logout1 = new LogOut(driver);
		logout1.logOut();

		HomePage homepage1 = new HomePage(driver);
		homepage1.clickOnShopFloor();

		Thread.sleep(1000);
		driver.findElement(Assembly_Received).click();
		enterPCB();

	}

	public void enterPCB() throws InterruptedException {

		Thread.sleep(1000);
		driver.findElement(ScanPCB_Serial).sendKeys(Login_TC1.PCB_Number);
		Thread.sleep(1000);
		driver.findElement(ScanPCB_Serial).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		enterWrongPCB();

	}

	public void enterWrongPCB() throws InterruptedException {

		Thread.sleep(1000);
		driver.findElement(ScanPCB_Serial).sendKeys("ABCDEF000001");
		Thread.sleep(1000);
		driver.findElement(ScanPCB_Serial).sendKeys(Keys.ENTER);
		error = driver.findElement(ScanPCB_Serial).getText();
		error.equals("ERROR, EVENT WAS NOT UPDATED.");
		{
			System.out.println("Test Case Passed ");
		}
	}

}
