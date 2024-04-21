package TNT_Testing;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import TNT_Testing.HomePage;

public class Login_TC1 {

	// private static final TimeUnit Seconds = null;
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
		//driver.get("http://localhost:44523/WebTnT_2015/Account/Login.aspx"); //This is the link E:\Neo Tech\Task\16 Jan 2023\Release2012_2\WebTnT_2015
		driver.get("http://172.20.10.102:86/Account/Login.aspx");
		// Local driver.get("http://localhost:44523/mes_vs2012/Account/Login.aspx");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void loginWithoutUserAndPassword() {
		login = new LoginPage(driver);
		login.loginWithoutUserAndPassword();
	}

	@Test(priority = 2)
	public void loginWithoutPassword() throws InterruptedException {
		login = new LoginPage(driver);
		login.loginWithoutPassword();
	}

	@Test(priority = 3)
	public void loginTest() throws InterruptedException {
		Thread.sleep(500);
		login = new LoginPage(driver);
		login.enterUser();
		login.enterPassword();
		login.clickLogin();
	}

	@Test(priority = 4)
	public void welcomeMessage() throws InterruptedException {
		homepage = new HomePage(driver);
		homepage.welcomeMSG();
	}

	@Test(priority = 5)
	public void loggedInUserDetails() throws InterruptedException {
		// Creating object of Home page
		homepage = new HomePage(driver);
		homepage.Logged_in_User();
	}

	@Test(priority = 6)
	public void clickingShopFloor() {
		// Creating object of Home page
		homepage = new HomePage(driver);
		homepage.clickOnShopFloor();
	}

	@Test(priority = 7)
	public void labelPrintObject() throws InterruptedException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.clickPrintLabelOption();
		}
	
	/*@Test(priority = 8)
	public void selectingPrinter() throws InterruptedException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.selectPrinter();
	}*/
	
	@Test(priority = 9)
	public void selectingProduct() throws InterruptedException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.selectProduct();
	}
	
	@Test(priority = 10)
	public void selectingOrder() throws InterruptedException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.selectOrder();
	}
	
	@Test(priority = 11)
	public void quantityPrinting() throws InterruptedException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.enterQtyToPrint(1);
	}

	@Test(priority = 12)
	public void generateBarcode() throws InterruptedException, ClassNotFoundException, SQLException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.currentBarcode();
	}
	@Test(priority = 13)
	public void VerifyBarcode() throws InterruptedException, ClassNotFoundException, SQLException {
		// Creating object of PCB Label Print Class
		PCBLablePrint = new SF_PCB_Label_Print(driver);
		PCBLablePrint.scannedBarcode();
	}
	
	@Test(priority = 14)
		public void logOutFromAPP() throws InterruptedException {
			// Creating object of Home page
			logout = new LogOut(driver);
			logout.logOut();
		}
	
	
	@Test(priority = 15)
	public void AoiTestClick() throws InterruptedException, ClassNotFoundException, SQLException {
		// Creating object of PCB Label Print Class
		AOI_Class = new SF_AOI_Test(driver);
		AOI_Class.aoiTestClick();
	}
	
	@Test(priority = 16)
		public void aoiObject() throws InterruptedException, ClassNotFoundException, SQLException {
			// Creating object of AOI Test Class
			SF_AOI_Test AOITest = new SF_AOI_Test(driver);
			AOITest.selectResultFailed();
			//Number format exception is coming in this function so need to check it again.
		}

	@Test()
	public void logoutObject() throws InterruptedException {
		LogOut logout = new LogOut(driver);
		logout.logOut();
	}

	
	@Test()
	public void sfAssemblyObject() throws InterruptedException {
		SF_Assembly_Received AR = new SF_Assembly_Received(driver);
		AR.assemblyReceivedClick();

	}
	
	@Test()
	public void clickONGenericTest() throws InterruptedException {
			SF_Generic_Test SGT = new SF_Generic_Test(driver);
			SGT.genericTestClick();

		}

	
}