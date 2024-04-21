package QVWI_PAY;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Add_Customer_Data_Driven {

	public static void main(String[] args) {
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
		
		
		//driver.findElement(By.id("submit_b")).click(); */
		
		List<String[]> testData = readCSV("E:\\Neo Tech\\Data_Driven\\AddCustomer.csv");

        // Iterate through test data and execute tests
        for (String[] data : testData) {
             String first_Name = data[0];
             String last_Name = data[1];
             String gender = data[2];
             String email = data[3];
             String mobile_Number = data[4];
             String sa_ID = data[5];
             String upload_Selfie = data[6];
             String upload_SA_ID_Front = data[7];
             String upload_SA_ID_Back = data[8];
             String password = data[9];
             String confirm_Password  = data[10];
             String pin = data[11];
             String confirm_Pin = data[12];
           
           performLoginTest(driver, first_Name, last_Name, gender,email,mobile_Number ,sa_ID ,upload_Selfie ,upload_SA_ID_Front,
        		   upload_SA_ID_Back ,password,confirm_Password,pin,confirm_Pin);
        }

    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void performLoginTest(WebDriver driver, String first_Name, String last_Name, String gender,String email,String mobile_Number,
    		String sa_ID,String upload_Selfie,String upload_SA_ID_Front,String upload_SA_ID_Back,String password,String confirm_Password,String pin,String confirm_Pin) {
        
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Customer')]")));
		driver.findElement(By.xpath("//button[contains(text(),'Add Customer')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'modal-title white'  and @id = 'myModalLabel35' and contains(text(),'Add Customer')]")));
		
    	
    	
    	driver.findElement(By.name("first_name")).click();
    	driver.findElement(By.name("first_name")).sendKeys(first_Name);
    	driver.findElement(By.name("last_name")).sendKeys(last_Name);
    	
    	driver.findElement(By.xpath("//*[@class = \"select2-selection select2-selection--single\" ]")).click(); //Selecting Gender
    	driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(gender);
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.name("email")).sendKeys(email); 
    	
    	    	
		driver.findElement(By.id("select2-signup_code-container")).click(); //Selecting Country Code
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("SOUTH AFRICA (+27)");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		
		driver.findElement(By.id("mobile")).sendKeys(mobile_Number);
		
		driver.findElement(By.id("otp_button")).click(); //Click Generate OTP 
		WebElement element = driver.findElement(By.id("email_error"));
		wait.until(ExpectedConditions.textToBePresentInElement(element, "OTP has been sent.Ref #"));
		
		
		driver.findElement(By.id("otp")).sendKeys("123456"); //Enter OTP 
		
		
		driver.findElement(By.id("select2-country-container")).click(); //Selecting Country 
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys("SOUTH AFRICA");
		driver.findElement(By.xpath("//*[@class = \"select2-search__field\"]")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id("id_document_number")).sendKeys(sa_ID); //Enter SA ID  
		
		//Thread.sleep(200);
		driver.findElement(By.id("id_expiry_date")).click(); //Enter SA ID Expiry Date 
		//Thread.sleep(200);
		driver.findElement(By.xpath("//*[@class = 'picker__select--year' ]")).click();
		driver.findElement(By.xpath("//*[@class = 'picker__select--year' ]")).sendKeys("2024");
		driver.findElement(By.xpath("//*[@class = 'picker__day picker__day--infocus' and contains(text(),'28')]")).click();
		
		driver.findElement(By.id("selfie_upload")).sendKeys(upload_Selfie); //Upload Selfie
		driver.findElement(By.id("civil_id_file")).sendKeys(upload_SA_ID_Front);	//Upload SA ID Front Page
		driver.findElement(By.id("civil_id_file_back")).sendKeys(upload_SA_ID_Back); //Upload SA ID Back Page
		
		
		driver.findElement(By.id("password")).sendKeys(password); // Enter Password
		driver.findElement(By.id("confirm_password")).sendKeys(confirm_Password); // Enter Confirm Password
		driver.findElement(By.id("pin")).sendKeys(pin); // Enter Pin
		driver.findElement(By.id("confirm_pin")).sendKeys(confirm_Pin); // Enter Confirm Pin
		
		driver.findElement(By.id("submit_b")).click();
		
      
        
    }

		
		
		
		
		
		
		
		
		
		
		
		

	}


