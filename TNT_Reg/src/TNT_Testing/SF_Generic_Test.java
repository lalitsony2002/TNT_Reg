package TNT_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SF_Generic_Test {
	
	// static String value;
	WebDriver driver;
	String error = null;

	public SF_Generic_Test(WebDriver driver) {
		this.driver = driver;
	}

	By Generic_Test = By.xpath("//a[@class='level2 dynamic'][text()='(SMD / FID) Generic Test']"); // Locator for (SMD/FID) GENERIC Test in Shop Floor Menu Item
	//By Shop_Floor = By.xpath("//*[contains(text(),'Shop Floor')]");
	By Workstation = By.name("ctl00$MainContent$WorkstationDropDownList"); // XPath for the Workstation.
	By Station = By.id("MainContent_StationIDDropDownList"); // XPath for the Station
	By PCB_Scan = By.name("ctl00$MainContent$PCBSerialTextBox"); // XPath for the PCB Scan Box.
	By Fail_btn = By.name("ctl00$MainContent$FailImageButton"); // XPath for the Fail Button.
	By Failure_Code = By.id("MainContent_FailureCodeDropDownList"); // XPath for the Failure Code.
	
	By User_Name = By.id("MainContent_LoginUser_UserName"); // XPath for the 2010 APP User Name testengdev
	By Password = By.id("MainContent_LoginUser_Password"); // XPath for the 2010 APP Password Password1
	By Login_Button = By.id("MainContent_LoginUser_LoginButton"); // XPath for the 2010 APP Login Button
	
	By Repair  = By.xpath("//*[contains(text(),'Repairs')]");
	
	
	
	
	public void genericTestClick() throws InterruptedException {

		HomePage homepage1 = new HomePage(driver);
		homepage1.clickOnShopFloor();

		Thread.sleep(1000);
		driver.findElement(Generic_Test).click();
		selectWorkstation();
	}

	
	public void selectWorkstation() throws InterruptedException{

		Thread.sleep(1000);
		driver.findElement(Workstation).click();
		driver.findElement(Workstation).sendKeys("PCBA Test");
		Thread.sleep(500);
		driver.findElement(Workstation).sendKeys(Keys.ENTER);
		selectStationID();
	}

	public void selectStationID() throws InterruptedException{

		Thread.sleep(500);
		driver.findElement(Station).click();
		driver.findElement(Station).sendKeys("BON01");
		Thread.sleep(500);
		driver.findElement(Station).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		enterScan();
	}
	
	public void enterScan() throws InterruptedException{

		driver.findElement(PCB_Scan).sendKeys("123456000292");
		Thread.sleep(500);
		driver.findElement(PCB_Scan).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		failBTN();
	}

	public void failBTN() throws InterruptedException{
		
		Thread.sleep(500);
		driver.findElement(Fail_btn).click();
		failCode();
		
	}
	
	public void failCode() throws InterruptedException{
		
		Thread.sleep(500);
		driver.findElement(Failure_Code).click();
		driver.findElement(Failure_Code).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Failure_Code).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Failure_Code).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Failure_Code).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		repairApp_Login();
	}
	

public void repairApp_Login() throws InterruptedException{
		
		driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL + "t");
		driver.get("http://localhost:53160/WebTnT_1/Account/Login.aspx");
		Thread.sleep(500);
		driver.findElement(User_Name).sendKeys("testengdev");
		Thread.sleep(500);
		driver.findElement(Password).sendKeys("Password1");
		Thread.sleep(500);
		driver.findElement(Login_Button).click();
		Thread.sleep(500);
		clickRepair();
	}
	
	
public void clickRepair() throws InterruptedException{
	
	//Hover mouse pointer on Repair Menu Item
	Actions action = new Actions(driver);
	WebElement webelement = driver.findElement(Repair);
	action.moveToElement(webelement);
	action.perform();
}


	/*	
	
	public void selectProductionLine() throws InterruptedException, ClassNotFoundException, SQLException {

		Thread.sleep(1000);
		driver.findElement(Production_Line).click();
		driver.findElement(Production_Line).sendKeys("SMD_LINE_3");
		Thread.sleep(500);
		driver.findElement(Production_Line).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		scanPCBTextBox();
	}

	public void selectResultFailed() throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(Failed).click();
		Thread.sleep(500);
		driver.findElement(ScanPCB).click();
		String pcb = Login_TC1.PCB_Number;
		int previous_pcb = Integer.parseInt(pcb);
		previous_pcb = previous_pcb - 1;
		String pcb_num = Integer.toString(previous_pcb);
		driver.findElement(ScanPCB).sendKeys(pcb_num);
		Thread.sleep(1500);
		
		//driver.findElement(ScanPCB).sendKeys(Keys.ENTER);
		//driver.findElement(ScanPCB).clear();
		//Thread.sleep(1000);
		

	}

	public void errorCode(String error) throws ClassNotFoundException, SQLException, InterruptedException {

		if (error.equals("ERROR, PLEASE LOGIN TO PROCEED!"))
		// if ( driver.getPageSource().contains("ERROR, PLEASE LOGIN TO PROCEED!"))
		{
			LoginPage LoginPage1 = new LoginPage(driver);
			LoginPage1.login();
			Thread.sleep(1000);
			aoiTestClick();
		}
		// if ( driver.getPageSource().contains("ERROR, SELECT PRODUCTION LINE"))
		if (error.equals("ERROR, SELECT PRODUCTION LINE")) {
			Thread.sleep(1000);
			selectProductionLine();

		}

		if (error.substring(0, 8).equals("SUCCESS,")) {
			System.out.println("SUCCESS,Passed AOI, Move to Assembly Recieved");
			ResultSet rs = sqlStatus();
			while (rs.next()) {
				System.out.print(rs.getString("id_status") + "\t\t" + rs.getString("TimeStamp"));

			}

			String str = error.substring(20);
			// System.out.println(error);
			// System.out.println(str);
			if (str.equals("AOI Test Passed IS NOT ALLOWED")) {
				System.out.println("Please enter correct PCB number.");
			}

		}}
	





*/


}

	
	
	
	


