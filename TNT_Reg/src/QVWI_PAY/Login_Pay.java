package QVWI_PAY;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Pay {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "E:\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://za.qvwipay.com/control_panel/web/");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		
		// This is the Login ID and Password to Login into site.
		driver.findElement(By.id("username")).sendKeys("superadmin");
		driver.findElement(By.id("password")).sendKeys("CXchJjaZ8aUr");
		
		driver.findElement(By.id("btnlogin")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Hello,')]")));
		
		
		
		driver.findElement(By.xpath("//*[contains(text(),'Data Bank')]")).click(); // Click on Data bank option 
		
		driver.findElement(By.xpath("//*[contains(text(),'Customer Manager')]")).click(); 
		
		driver.findElement(By.xpath("//*[contains(text(),'Add Customer')]")).click();
		
		//Thread.sleep(300);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Customer')]")));
		
			
		driver.findElement(By.xpath("//button[contains(text(),'Add Customer')]")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'modal-title white'  and @id = 'myModalLabel35' and contains(text(),'Add Customer')]")));
		
		
		driver.findElement(By.name("first_name")).sendKeys("Lalit_2");  //First Name
		driver.findElement(By.name("last_name")).sendKeys("Last_2"); //Last Name
		
		driver.findElement(By.xpath("//*[@class = \"select2-selection select2-selection--single\" ]")).click(); //Selecting Gender
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("Male");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.name("email")).sendKeys("Lalit_2@gmail.com"); //Enter Email
		
		//Thread.sleep(200);
		
		driver.findElement(By.id("select2-signup_code-container")).click(); //Selecting Country Code
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("SOUTH AFRICA (+27)");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("mobile")).sendKeys("786126789");
		
		driver.findElement(By.id("otp_button")).click(); //Click Generate OTP 
		
		WebElement element = driver.findElement(By.id("email_error"));
		wait.until(ExpectedConditions.textToBePresentInElement(element, "OTP has been sent.Ref #"));
		
		driver.findElement(By.id("otp")).sendKeys("123456"); //Enter OTP 
		//Thread.sleep(100);
		
		driver.findElement(By.id("select2-country-container")).click(); //Selecting Country 
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("SOUTH AFRICA");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("id_document_number")).sendKeys("abc123456"); //Enter SA ID  
		//Thread.sleep(200);
		driver.findElement(By.id("id_expiry_date")).click(); //Enter SA ID Expiry Date 
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[@class = 'picker__select--year' ]")).click();
		driver.findElement(By.xpath("//*[@class = 'picker__select--year' ]")).sendKeys("2024");
		driver.findElement(By.xpath("//*[@class = 'picker__day picker__day--infocus' and contains(text(),'28')]")).click();
		
		
		driver.findElement(By.id("selfie_upload")).sendKeys("E:\\Neo Tech\\Task\\07 Aug 2023\\Rajen_Label.jpeg"); 	//Upload Selfie
		driver.findElement(By.id("civil_id_file")).sendKeys("E:\\Neo Tech\\Task\\07 Aug 2023\\Rajen_Label.jpeg");	//Upload SA ID Front Page
		driver.findElement(By.id("civil_id_file_back")).sendKeys("E:\\Neo Tech\\Task\\07 Aug 2023\\Rajen_Label.jpeg"); //Upload SA ID Back Page
		driver.findElement(By.id("visa_file")).sendKeys("E:\\Neo Tech\\Task\\07 Aug 2023\\Rajen_Label.jpeg"); //Upload SARS ID  
		
		Thread.sleep(1000);
		driver.findElement(By.id("select2-segment-qw-container")).click(); //Selecting Country 
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("Test");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.id("password")).sendKeys("Lalit123"); // Enter Password
		driver.findElement(By.id("confirm_password")).sendKeys("Lalit123"); // Enter Confirm Password
		driver.findElement(By.id("pin")).sendKeys("1234"); // Enter Pin
		driver.findElement(By.id("confirm_pin")).sendKeys("1234"); // Enter Confirm Pin
		
		//driver.findElement(By.id("submit_b")).click(); */
		
		/*driver.findElement(By.xpath("//*[contains(text(),' Accounts ')]")).click(); // Click on Data bank option
		
		driver.findElement(By.xpath("//*[contains(text(),' ZAR ')]")).click(); // Click on Data bank option
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Transfer To Wallet')]")));
		
		driver.findElement(By.xpath("//*[contains(text(),'Transfer To Wallet')]")).click(); // Click on Data bank option
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerid")));
		
		driver.findElement(By.name("customerid")).sendKeys("9972907766");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twamount")));
		
		driver.findElement(By.id("twamount")).sendKeys("10");
		
		driver.findElement(By.id("twremark")).sendKeys("Test Transfer");
		
		driver.findElement(By.id("mybuttonwtw")).click(); */
		
	}

}
