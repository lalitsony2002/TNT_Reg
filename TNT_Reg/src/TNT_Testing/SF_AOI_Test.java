package TNT_Testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import TNT_Testing.HomePage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SF_AOI_Test {

	// static String value;
	WebDriver driver;
	String error = null;

	public SF_AOI_Test(WebDriver driver) {
		this.driver = driver;
	}

	By AOI = By.xpath("//a[@class='level2 dynamic'][text()='(SMD) AOI Test']"); // Locator for (SMD) AOI Test in Shop
																				// Floor Menu Item
	By Shop_Floor = By.xpath("//*[contains(text(),'Shop Floor')]");
	By ScanPCB = By.id("MainContent_PCBSerialTextBox"); // XPath for the SCAN PCB Serial Number.
	By Message = By.id("MainContent_MessageLabel"); // XPath for the SCAN PCB Serial Number
	By Production_Line = By.id("MainContent_ProdLineDropDownList"); // XPath for the Production Line
	By Passed = By.id("MainContent_SelectedResult_0");// XPath for the Select Result Pass
	By Failed = By.id("MainContent_SelectedResult_1");// XPath for the Select Result Fail

	public static ResultSet sqlStatus() throws ClassNotFoundException, SQLException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Local Database setting final String url =
		// "jdbc:sqlserver://DESKTOP-C6M5A7U\\SQLEXPRESS:1433;database=stb_production;authenticationScheme=NTLM;integratedSecurity=false;trustServerCertificate=true";
		final String url = "jdbc:sqlserver://172.20.10.149\\PRODUCTION:1433;database=stb_production;authenticationScheme=NTLM;integratedSecurity=false;trustServerCertificate=true";
		// Local User final String user ="Lalit.Soni" ;
		// Local Password final String password ="Password123" ;
		final String user = "sa";
		final String password = "Sql2012PM";

		Connection connection = DriverManager.getConnection(url, user, password);

		Statement st = connection.createStatement();
		String Sql = "select id_status,timestamp from production_event where pcb_num = '" + Login_TC1.PCB_Number + "'";
		ResultSet rs = st.executeQuery(Sql);

		return rs;

		/*
		 * System.out.println("Contents of the table"); while (rs.next()) {
		 * System.out.print(rs.getString("id_status") + "\t\t" + rs.getString("TimeStamp"));
		 * 
		 * }
		 */
	}

	public void aoiTestClick() throws InterruptedException, ClassNotFoundException, SQLException {

		HomePage homepage1 = new HomePage(driver);
		homepage1.clickOnShopFloor();

		Thread.sleep(1000);
		driver.findElement(AOI).click();
		scanPCBTextBox();
	}

	public void scanPCBTextBox() throws InterruptedException, ClassNotFoundException, SQLException {

		Thread.sleep(1000);
		driver.findElement(ScanPCB).click();
		driver.findElement(ScanPCB).sendKeys(Login_TC1.PCB_Number);
		//driver.findElement(ScanPCB).sendKeys("123456000005");
		driver.findElement(ScanPCB).sendKeys(Keys.ENTER);
		driver.findElement(ScanPCB).clear();
		Thread.sleep(1000);
		error = driver.findElement(Message).getText();

		if (!error.equals(null)) {
			errorCode(error);
		}

	}

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
			Thread.sleep(500);
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
	


}


