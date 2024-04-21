package TNT_Testing;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SF_PCB_Label_Print {

	WebDriver driver;
	String TargetQTY, PrintedQTY, CurrentBarcode, QuantityVerified, Product, Str, Str1;
	String Error = "NoError";
	WebElement element;
	int QTY_Target, QTY_Printed;
	int QTY_Remaining = 0;
	int QTY_Verify = 0;

	//String Prod_List[] = new String[20];

	// Constructor that will be automatically called as soon as the object of the
	

	public SF_PCB_Label_Print(WebDriver driver) {
		this.driver = driver;
	}

	By PCB = By.xpath("//a[@class='level2 dynamic'][text()='(SMD) PCB Label Print Station']"); // Locator for (SMD) PCB Label Print Station in Shop Floor Menu
	By Printerclk = By.id("MainContent_PrinterDropDownList"); // XPath for the Printer option.
	By Productslt = By.id("MainContent_ModelDropDownList"); // XPath for the Modal or Product Select.
	By Jobslt = By.id("MainContent_JobNoDropDownList"); // XPath for the Job or Order Number Select.
	By JobQTY = By.id("MainContent_JobQtyTextBox"); // XPath for the Job or Order Number Select.
	By Printed_QTY = By.id("MainContent_PrintedQtyTextBox"); // XPath for the Printed Quantity Till Now.
	By EnterQTY_Print = By.id("MainContent_QtyToPrintTextBox"); // XPath for the Enter the Quantity.
	By Click_Printbtn = By.id("MainContent_ImageButton"); // XPath for Click on Print Button.
	By Error_MSG = By.id("MainContent_MessageLabel"); // XPath for Error Message.
	By Current_Barcode = By.id("MainContent_CurrentPrintedBarcodeTextBox"); // XPath for Current Generated Barcode .
	By Verified_Quantity = By.id("MainContent_CurrentPrintedQtyTextBox"); // XPath for Verified Quantity Box .
	By Scanned_Barcode = By.id("MainContent_PcbLabelTextBox"); // XPath for Scanned Barcode.

	// Method to click on the PCB Label Print Station Option.

	public void clickPrintLabelOption() throws InterruptedException {
	
		driver.findElement(PCB).click();
		
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.getPageSource().contains("PCB LABEL PRINT AND VERIFICATION STATION"));
		
		Reporter.log("PCB Label Print and Verification Station Screen Opened ");

	}

	// Method to select Printer from the list

	/*public void selectPrinter() throws InterruptedException {

		driver.findElement(Printerclk).click();
		Thread.sleep(500);
		driver.findElement(Printerclk).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Printerclk).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Printerclk).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Printerclk).sendKeys(Keys.ENTER);
		Thread.sleep(500);

		WebElement t = driver.findElement(Printerclk);

		Select select = new Select(t);

		WebElement webelement = select.getFirstSelectedOption();
		String selectedprinter = webelement.getText();

		Assert.assertNotEquals("ZT230-200dpi ZPL-Virutal", selectedprinter);
		Reporter.log("Printer Is Selected As " + selectedprinter);

	}*/
	
	// Method to select Product from the list

	public void selectProduct() throws InterruptedException {
		driver.findElement(Productslt).click();
		Thread.sleep(1000);
		//driver.findElement(Productslt).sendKeys(Keys.DOWN);
		driver.findElement(Productslt).sendKeys("OTT LEAP S2");
		//Thread.sleep(500);
		//driver.findElement(Productslt).sendKeys(Keys.DOWN);
		Thread.sleep(500);
		driver.findElement(Productslt).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		
		WebElement t = driver.findElement(Productslt);

		Select select = new Select(t);

		WebElement webelement = select.getFirstSelectedOption();
		String selectedproduct = webelement.getText();

		Assert.assertNotEquals("Product", selectedproduct);
		Reporter.log("Product Is Selected As " + selectedproduct);
		
	}
	// Method to Get total target quantity

	public void jobQTY() throws InterruptedException {

		Thread.sleep(500);
		element = driver.findElement(JobQTY);
		TargetQTY = element.getAttribute("value");
		//System.out.println("Total Order Quantities are " + TargetQTY);
	}
	// Method to Get total printed quantity

	public void printedQTY() throws InterruptedException {
		element = driver.findElement(Printed_QTY);
		PrintedQTY = element.getAttribute("value");
		System.out.println("Total Printed Quantities are " + PrintedQTY);
	}

	// Method to Select the order from the list

	public void selectOrder() throws InterruptedException {

		Thread.sleep(2000);
		driver.findElement(Jobslt).click();
		Thread.sleep(1000);
		driver.findElement(Jobslt).sendKeys("999002T");
		driver.findElement(Jobslt).sendKeys(Keys.ENTER);
		
		
		WebElement t = driver.findElement(Jobslt);

		Select select = new Select(t);

		WebElement webelement = select.getFirstSelectedOption();
		String selectedorder = webelement.getText();

		Assert.assertNotEquals("Select Order", selectedorder);
		Reporter.log("Selected Order Is " + selectedorder);
		
	}

	public void enterQtyToPrint(int quantity) throws InterruptedException {

		if (quantity > 1000) {
			String Quantity = Integer.toString(quantity);
			driver.findElement(EnterQTY_Print).click();
			Thread.sleep(500);
			driver.findElement(EnterQTY_Print).sendKeys(Quantity);
			clickPrint();

			Error = driver.findElement(Error_MSG).getText();

			Assert.assertEquals("MAX 1000 LABELS CAN BE PRINTED AT ONCE!", Error);
			Reporter.log("PCB Quantity Is More Than 1000 " +quantity);
		}

		if (quantity < 1000) {
			jobQTY();
			int Tqty = Integer.parseInt(TargetQTY);
			if (quantity <= Tqty) {
				String Quantity = Integer.toString(quantity);
				driver.findElement(EnterQTY_Print).clear();
				Thread.sleep(500);
				driver.findElement(EnterQTY_Print).sendKeys(Quantity);
				clickPrint();
				Assert.assertNotEquals(" ", CurrentBarcode);
				Reporter.log("Input Quantity Printer. "+quantity);
			}
		}
		
		
	}

	public void clickPrint() throws InterruptedException {

		driver.findElement(Click_Printbtn).click();
		
		}


	public void currentBarcode() throws InterruptedException, ClassNotFoundException, SQLException {

		Thread.sleep(1000);
		CurrentBarcode = driver.findElement(Current_Barcode).getAttribute("value");
		
		//System.out.println(CurrentBarcode);
		
		Assert.assertNotEquals(" ", CurrentBarcode);
		Reporter.log("Current Barcode Is Generated" +CurrentBarcode);
	
	}

	public void scannedBarcode() throws InterruptedException {

		CurrentBarcode = driver.findElement(Current_Barcode).getAttribute("value");
		
		Login_TC1.PCB_Number = CurrentBarcode;
		Thread.sleep(1000);
		driver.findElement(Scanned_Barcode).sendKeys(CurrentBarcode);
		Thread.sleep(3000);
		driver.findElement(Scanned_Barcode).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		String CurrentBarcode1 = driver.findElement(Current_Barcode).getAttribute("value");

		Assert.assertNotEquals(CurrentBarcode, CurrentBarcode1);
		Reporter.log("Barcode Verified. " + CurrentBarcode1);
		//Reporter.log(CurrentBarcode);

	}

	
}
