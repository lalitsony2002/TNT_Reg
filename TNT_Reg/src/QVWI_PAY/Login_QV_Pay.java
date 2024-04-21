package QVWI_PAY;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import TNT_Testing.HomePage;
import TNT_Testing.LogOut;
import TNT_Testing.LoginPage;
import TNT_Testing.SF_AOI_Test;
import TNT_Testing.SF_PCB_Label_Print;

public class Login_QV_Pay {
	
	WebDriver driver;
	LoginPage login;
	HomePage homepage;
	LogOut logout;
	SF_PCB_Label_Print PCBLablePrint;
	SF_AOI_Test AOI_Class;

	static String PCB_Number; // we can use this variable everywhere.

	// public static void main(String[] args) throws InterruptedException {

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://za.qvwipay.com/control_panel/web/");
		// Local driver.get("http://localhost:44523/mes_vs2012/Account/Login.aspx");
		driver.manage().window().maximize();
	}


}
