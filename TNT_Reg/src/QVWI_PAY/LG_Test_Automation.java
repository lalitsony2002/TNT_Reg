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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LG_Test_Automation {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "E:\\Chrome_Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://172.20.10.102:86/Account/Login.aspx");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		
		By Shop_Floor = By.xpath("//*[contains(text(),'Shop Floor')]");
		By Model_Type = By.id("MainContent_ModelDropDownList"); // XPath for the model type.
		By Test = By.id("MainContent_WorkstationDropDownList"); // XPath for the workstation.
		By Station = By.id("MainContent_StationIDDropDownList"); // XPath for the Station ID.
		By PCB_Enter = By.id("MainContent_PCBSerialTextBox");// XPath for the Serial Box.
		By LG_Test = By.xpath("//a[@class='level2 dynamic'][text()='(SMT/FID) LG Test']"); // Locator for (SMD) AOI Test in Shop
		By Pass_btn = By.id("MainContent_PassImageButton");// XPath for the Select Result Pass
		By Fail_btn = By.id("MainContent_FailImageButton");// XPath for the Select Result Fail
		
		

		Actions action = new Actions(driver);
		WebElement webelement = driver.findElement(Shop_Floor);
		action.moveToElement(webelement);
		action.perform();
		Thread.sleep(1000);
		driver.findElement(LG_Test).click();
		
		Thread.sleep(1000);
		driver.findElement(Model_Type).click();
		driver.findElement(Model_Type).sendKeys("55NANO776RA");
		
		Thread.sleep(1000);
		driver.findElement(Model_Type).click();
		driver.findElement(Test).sendKeys("LG-Key Download");
		
		Thread.sleep(1000);
		driver.findElement(Station).click();
		driver.findElement(Station).sendKeys("BON01");
		driver.findElement(Station).click();
		
		//Thread.sleep(1000);
		//driver.findElement(PCB_Enter).sendKeys("AC3OT777777000024");
		//driver.findElement(PCB_Enter).sendKeys(Keys.ENTER);
		
		
		
		
		
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
    	
    	
    	
    	driver.findElement(By.id("MainContent_PassImageButton")).sendKeys(Keys.ENTER);
    	
		
		
    }
		
	}


