package QVWI_PAY;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AOI_Automation {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "E:\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://172.20.10.102:86/Account/Login.aspx");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		By AOI = By.xpath("//a[@class='level2 dynamic'][text()='(SMD) AOI Test']"); // Locator for (SMD) AOI Test in Shop
		// Floor Menu Item
		By Shop_Floor = By.xpath("//*[contains(text(),'Shop Floor')]");
		By ScanPCB = By.id("MainContent_PCBSerialTextBox"); // XPath for the SCAN PCB Serial Number.
		By Message = By.id("MainContent_MessageLabel"); // XPath for the SCAN PCB Serial Number
		By Production_Line = By.id("MainContent_ProdLineDropDownList"); // XPath for the Production Line
		By Passed = By.id("MainContent_SelectedResult_0");// XPath for the Select Result Pass
		By Failed = By.id("MainContent_SelectedResult_1");// XPath for the Select Result Fail
		By LoginBtn = By.id("MainContent_LoginUser_LoginButton");
		By UserName = By.id("MainContent_LoginUser_UserName");
		By Password = By.id("MainContent_LoginUser_Password");
		By Login = By.id("HeadLoginView_HeadLoginStatus");
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		
		
		driver.findElement(Login).click();
		Thread.sleep(1000);
		driver.findElement(UserName).click();
		driver.findElement(UserName).sendKeys("testengdev");
		Thread.sleep(1000);
		driver.findElement(Password).click();
		driver.findElement(Password).sendKeys("Password1");
		Thread.sleep(500);
		driver.findElement(LoginBtn).click();
		
		Actions action = new Actions(driver);
		WebElement webelement = driver.findElement(Shop_Floor);
		action.moveToElement(webelement);
		action.perform();
		Thread.sleep(1000);
		driver.findElement(AOI).click();
		
		Thread.sleep(1000);
		driver.findElement(Production_Line).click();
		driver.findElement(Production_Line).sendKeys("SMD_LINE_3");
		Thread.sleep(500);
		driver.findElement(Production_Line).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		
		List<String[]> testData = readCSV("E:\\Neo Tech\\Data_Driven\\New_AOI.csv");

        // Iterate through test data and execute tests
        for (String[] data : testData) {
            String pcb_number = data[0];
            

           performLoginTest(driver, pcb_number);
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

    public static void performLoginTest(WebDriver driver, String pcb_num) {
        // Find login page elements and perform actions using Selenium WebDriver
    	
    	//WebDriverWait wait = new WebDriverWait(driver,30);
    	driver.findElement(By.id("MainContent_PCBSerialTextBox")).sendKeys(pcb_num);
    	driver.findElement(By.id("MainContent_PCBSerialTextBox")).sendKeys(Keys.ENTER);
    }

}
