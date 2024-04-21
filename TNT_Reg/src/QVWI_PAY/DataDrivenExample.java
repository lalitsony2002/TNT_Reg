package QVWI_PAY;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDrivenExample {

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
		
		driver.findElement(By.xpath("//*[contains(text(),' Accounts ')]")).click(); // Click on Data bank option
		
		driver.findElement(By.xpath("//*[contains(text(),' ZAR ')]")).click(); // Click on Data bank option
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Transfer To Wallet')]")));
		
		driver.findElement(By.xpath("//*[contains(text(),'Transfer To Wallet')]")).click(); // Click on Data bank option
		
		
		List<String[]> testData = readCSV("E:\\Neo Tech\\Data_Driven\\Report.csv");

        // Iterate through test data and execute tests
        for (String[] data : testData) {
            String mobile_number = data[0];
            String amount = data[1];
            String remark = data[2];

           performLoginTest(driver, mobile_number, amount, remark);
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

    public static void performLoginTest(WebDriver driver, String username, String password, String remark) {
        // Find login page elements and perform actions using Selenium WebDriver
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	driver.findElement(By.name("customerid")).sendKeys(username);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twamount")));
    	driver.findElement(By.id("twamount")).sendKeys(password);
        driver.findElement(By.id("twremark")).sendKeys(remark);
        driver.findElement(By.id("mybuttonwtw")).click();

        
    }

		
		

	}


