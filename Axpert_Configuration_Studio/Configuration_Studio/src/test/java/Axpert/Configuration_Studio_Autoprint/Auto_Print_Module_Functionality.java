package Axpert.Configuration_Studio_Autoprint;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Axpert.Configuration_Studio.Launch;
import Axpert.Configuration_Studio_Fastprint.UserLogin;

public class Auto_Print_Module_Functionality extends Launch{

	@Test (enabled=false)
//	@Test
	public void Button_click_verification() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP01 Checking and clicking the new button")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='dvRefreshParamIcon']")).click();
		WaitForLoadingIconDisappear();
		WebElement button_click = driver.findElement(By.xpath("//div[@id='iconsNewNew']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
//		button_click.click();
//		WaitForLoadingIconDisappear();
		Boolean isSelected = button_click.isSelected();
		Thread.sleep(2000);
		if(isSelected == true)
		{	
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario Passed");
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(1).getCell(6).setCellValue("New button should get selected");
			wb.getSheetAt(0).getRow(1).getCell(7).setCellValue("New button is selected");
			wb.getSheetAt(0).getRow(1).getCell(8).setCellValue("Pass");
			wb.getSheetAt(0).getRow(1).getCell(9);
			wb.write(fos);
			wb.close();
			System.out.println("Passed");
		}
		else
		{
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario Failed due to User is not able to select the New button" );
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(1).getCell(6).setCellValue("New button should get selected");
			wb.getSheetAt(0).getRow(1).getCell(7).setCellValue("New button is not selected");
			wb.getSheetAt(0).getRow(1).getCell(8).setCellValue("Fail");
			wb.getSheetAt(0).getRow(1).getCell(9).setCellValue("User is not able to select the New button");
			wb.write(fos);
			wb.close();
		}
	}
	
	@Test (priority=1)
	public void Auto_print_creation_page_Open() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_AP02 Checking the Auto print creation page is opening or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement button_click = driver.findElement(By.xpath("//div[@id='iconsNewNew']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		WaitForLoadingIconDisappear();
		WebElement Autoprint_page = driver.findElement(By.xpath("//div[@id='dvlayout']/div/div/div/h1[1]"));
		String actual = Autoprint_page.getText();
		System.out.println(actual);
		String Screenshot2= screenshot("TC_AP02 Checking the Auto print creation page is opening or not.Png");
		String Expected = "Auto print";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 2 Passed");
			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(2, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 2 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(2, actual, Expected, e);
		}
	}

	@Test (priority=2)
	public void Without_Selecting_Form() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_AP03 Checking the submiting without selecting Form")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WebElement Submit = driver.findElement(By.xpath("//a[@id='btn17']"));
		Submit.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot3 = screenshot("TC_AP03 Checking the submiting without selecting Form.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Form cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 3 Passed");
			test.addScreenCaptureFromPath(Screenshot3);
			excelpass(3, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 3 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot3);
			excelfail(3, Toastmessage, ExpectedToast, e);
		}
	}
    
	@Test(priority=3)
	public void Checking_Without_Created_Fastprint_form() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP04 Check if the Print Form Name is listed in Print name without creating a fastprint")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.click();
		search.sendKeys("Salary Strucutures-(salst)");
		WebElement srchreslt = driver.findElement(By.xpath("//li[contains(text(),'Salary Strucutures-(salst)')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srchreslt));
		srchreslt.click();
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-printdoc000F1-container']")).click();
		WebElement result = driver.findElement(By.xpath("//li[@aria-live='assertive']"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected ="No results found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 4 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(4, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 4 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(4, actual, Expected, e);
		}
	}
	
	@Test(priority=5)
	public void Checking_With_Created_Fastprint_form() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP05 Check in Auto prints print name, the created fastprint Form Name is listed or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WebElement new_btn = driver.findElement(By.xpath("//a[@title='new']"));
		new_btn.click();
		WebElement cnfrm = driver.findElement(By.xpath("//button[@title='Confirm']"));
		cnfrm.click();
		WaitForLoadingIconDisappear();
//		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
//		search.click();
//		search.sendKeys("Purchase Order Form-(purch)");
		WebElement srchreslt = driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srchreslt));
		srchreslt.click();
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-printdoc000F1-container']")).click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.click();
		Thread.sleep(2000);
		search1.sendKeys("defaulttempwithdsrce");
		WebElement result = driver.findElement(By.xpath("//li[contains(text(),'defaulttempwithdsrce')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected ="defaulttempwithdsrce";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 5 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(5, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 5 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(5, actual, Expected, e);
		}
	}
	
	@Test(priority=6)
	public void Checking_Without_Condition_Autoprint() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP06 Check in runtime without condition the autoprint loaded record is displayed or not as expected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
//		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
//		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
//		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
//		Autoprint.click();
//		WaitForLoadingIconDisappear();
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		Thread.sleep(2000);
		WebElement cnfrm = driver.findElement(By.xpath("//button[@title='Confirm']"));
		cnfrm.click();
		WaitForLoadingIconDisappear();
		WebElement ID = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		ID.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-printdoc000F1-container']")).click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.click();
		search1.sendKeys("defaulttempwithdsrce");
		driver.findElement(By.xpath("//li[contains(text(),'defaulttempwithdsrce')]")).click();
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
		srch.click();
		srch.click();
		srch.sendKeys("Purchase Order Form");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot6 = screenshot("TC_AP06 Check in runtime without condition the autoprint loaded record is displayed or not as expected.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+attribute19+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 6 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(6, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 6 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(6, actual, Expected, e);
		}
	}
	
	@Test(priority=7)
	public void Checking_With_Condition_Equalto() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP07 Check in runtime with condition Equal to the autoprint loaded record is displayed or not as expected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//a[@id='btn13']")).click();
//		Thread.sleep(2000);
//		WebElement cnfrm = driver.findElement(By.xpath("//button[@title='Confirm']"));
//		cnfrm.click();
//		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'apps')])[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='w-100 h-100']")));
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-variableR0-container']")).click();
		WebElement varsearch = driver.findElement(By.xpath("//input[@type='search']"));
		varsearch.click();
		varsearch.sendKeys("Quantity-(nquantity)");
		driver.findElement(By.xpath("//li[contains(text(),'Quantity-(nquantity)')]")).click();
		WebElement Operator = driver.findElement(By.xpath("//span[@aria-labelledby='select2-operatorR0-container']"));
		Operator.click();
		WebElement opersearch = driver.findElement(By.xpath("//input[@type='search']"));
		opersearch.click();
		opersearch.sendKeys("Equal to");
		driver.findElement(By.xpath("//li[contains(text(),'Equal to')]")).click();
		WebElement Value = driver.findElement(By.xpath("//input[@name='svalue']"));
		Value.sendKeys("2");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Ok = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		Ok.click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2a = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2a.get(1));
	    driver.close();
	    Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot7 = screenshot("TC_AP07 Check in runtime with condition Equal to the autoprint loaded record is displayed or not as expected.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+attribute19+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 7 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(7, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 7 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(7, actual, Expected, e);
		}
	}
	
	@Test(priority=8)
	public void Checking_With_Condition_Not_Equalto() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP08 Check in runtime with condition Not equal to the autoprint loaded record is displayed or not as expected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'apps')])[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='w-100 h-100']")));
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-variableR0-container']")).click();
		WebElement varsearch = driver.findElement(By.xpath("//input[@type='search']"));
		varsearch.click();
		varsearch.sendKeys("Quantity-(nquantity)");
		driver.findElement(By.xpath("//li[contains(text(),'Quantity-(nquantity)')]")).click();
		WebElement Operator = driver.findElement(By.xpath("//span[@aria-labelledby='select2-operatorR0-container']"));
		Operator.click();
		WebElement opersearch = driver.findElement(By.xpath("//input[@type='search']"));
		opersearch.click();
		opersearch.sendKeys("Not equal to");
		driver.findElement(By.xpath("//li[contains(text(),'Not equal to')]")).click();
		WebElement Value = driver.findElement(By.xpath("//input[@name='svalue']"));
		Value.clear();
		Value.sendKeys("1");
		String condtion = Value.getAttribute("value");
		System.out.println(condtion);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Ok = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		Ok.click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2a = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2a.get(1));
	    driver.close();
	    Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot8 = screenshot("TC_AP08 Check in runtime with condition Not equal to the autoprint loaded record is displayed or not as expected.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+condtion+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 8 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(8, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 8 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(8, actual, Expected, e);
		}
	}
	
	@Test(priority=9)
	public void Checking_With_Condition_Greater_than() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP09 Check in runtime with condition Greater than the autoprint loaded record is displayed or not as expected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'apps')])[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='w-100 h-100']")));
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-variableR0-container']")).click();
		WebElement varsearch = driver.findElement(By.xpath("//input[@type='search']"));
		varsearch.click();
		varsearch.sendKeys("Quantity-(nquantity)");
		driver.findElement(By.xpath("//li[contains(text(),'Quantity-(nquantity)')]")).click();
		WebElement Operator = driver.findElement(By.xpath("//span[@aria-labelledby='select2-operatorR0-container']"));
		Operator.click();
		WebElement opersearch = driver.findElement(By.xpath("//input[@type='search']"));
		opersearch.click();
		opersearch.sendKeys("Greater than");
		driver.findElement(By.xpath("//li[contains(text(),'Greater than')]")).click();
		WebElement Value = driver.findElement(By.xpath("//input[@name='svalue']"));
		Value.clear();
		Value.sendKeys("10");
		String condtion = Value.getAttribute("value");
		System.out.println(condtion);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Ok = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		Ok.click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2a = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2a.get(1));
	    driver.close();
	    Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot9 = screenshot("TC_AP09 Check in runtime with condition Greater than the autoprint loaded record is displayed or not as expected.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+condtion+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 9 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(9, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 9 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(9, actual, Expected, e);
		}
	}
	
	@Test(priority=10)
	public void Checking_With_Condition_Lesser_than() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP10 Check in runtime with condition Lesser than the autoprint loaded record is displayed or not as expected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'apps')])[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='w-100 h-100']")));
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-variableR0-container']")).click();
		WebElement varsearch = driver.findElement(By.xpath("//input[@type='search']"));
		varsearch.click();
		varsearch.sendKeys("Quantity-(nquantity)");
		driver.findElement(By.xpath("//li[contains(text(),'Quantity-(nquantity)')]")).click();
		WebElement Operator = driver.findElement(By.xpath("//span[@aria-labelledby='select2-operatorR0-container']"));
		Operator.click();
		WebElement opersearch = driver.findElement(By.xpath("//input[@type='search']"));
		opersearch.click();
		opersearch.sendKeys("Lesser than");
		driver.findElement(By.xpath("//li[contains(text(),'Lesser than')]")).click();
		WebElement Value = driver.findElement(By.xpath("//input[@name='svalue']"));
		Value.clear();
		Value.sendKeys("0");
		String condtion = Value.getAttribute("value");
		System.out.println(condtion);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Ok = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		Ok.click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2a = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2a.get(1));
	    driver.close();
	    Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot10 = screenshot("TC_AP10 Check in runtime with condition Lesser than the autoprint loaded record is displayed or not as expected.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+condtion+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 10 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(10, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 10 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(10, actual, Expected, e);
		}
	}
	
	@Test(priority=11)
	public void enabledDirect_print() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP11 Checking after enabled Direct print the PDF print viewer is populating or not in runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
	//  login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		WaitForLoadingIconDisappear();
		 // Find the checkbox element
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='directprint000F1']"));

     // Check if the checkbox is currently enabled
        if (!checkbox.isSelected()) {
            // If checkbox is disabled, perform an action to enable it (e.g., click it)
            checkbox.click();
        }
		driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
	    Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
	    Robot robot = new Robot();
	    for (int i = 0; i < 3; i++) {
            // Scroll up by 1000 pixels
	    	 robot.keyPress(KeyEvent.VK_TAB);
            try {
                // Add a delay to see the scrolling action
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    Thread.sleep(2000);
	    for (int i = 0; i < 3; i++) {
            // Scroll up by 1000 pixels
	    	 robot.keyPress(KeyEvent.VK_UP);
            try {
                // Add a delay to see the scrolling action
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    Thread.sleep(2000);
	    for (int i = 0; i < 5; i++) {
            // Scroll up by 1000 pixels
		    robot.keyPress(KeyEvent.VK_TAB);
            try {
                // Add a delay to see the scrolling action
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    for (int i = 0; i < 2; i++) {
            // Scroll up by 1000 pixels
		    robot.keyPress(KeyEvent.VK_ENTER);
            try {
                // Add a delay to see the scrolling action
            	Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\index.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot11 = screenshot("TC_AP11 Checking after enabled Direct print the PDF print viewer is populating or not in runtime.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+attribute19+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 11 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(11, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 11 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(11, actual, Expected, e);
		}
	}
	
	@Test(priority=12)
	public void Modify_Auto_print() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP12 Checking and the user can modify the created Auto Print using ID Hyperlink")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("(//span[contains(text(),'apps')])[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='w-100 h-100']")));
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-variableR0-container']")).click();
		WebElement varsearch = driver.findElement(By.xpath("//input[@type='search']"));
		varsearch.click();
		varsearch.sendKeys("Quantity-(nquantity)");
		driver.findElement(By.xpath("//li[contains(text(),'Quantity-(nquantity)')]")).click();
		WebElement Operator = driver.findElement(By.xpath("//span[@aria-labelledby='select2-operatorR0-container']"));
		Operator.click();
		WebElement opersearch = driver.findElement(By.xpath("//input[@type='search']"));
		opersearch.click();
		opersearch.sendKeys("Greater than");
		driver.findElement(By.xpath("//li[contains(text(),'Greater than')]")).click();
		WebElement Value = driver.findElement(By.xpath("//input[@name='svalue']"));
		Value.clear();
		Value.sendKeys("4");
		String condtion = Value.getAttribute("value");
		System.out.println(condtion);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Ok = driver.findElement(By.xpath("//div[@class='modal-footer']/button[2]"));
		Ok.click();
		WaitForLoadingIconDisappear();
		WebElement Print_Name = driver.findElement(By.xpath("//span[@aria-labelledby='select2-printdoc000F1-container']"));
		Print_Name.click();
		WebElement prname_search = driver.findElement(By.xpath("//input[@type='search']"));
		prname_search.click();
		prname_search.sendKeys("defaulttempwoutdsrce");
		driver.findElement(By.xpath("(//li[contains(text(),'defaulttempwoutdsrce')])[1]")).click();
		 // Find the checkbox element
        WebElement checkbox = driver.findElement(By.xpath("//input[@id='directprint000F1']"));
        checkbox.click();
//         // Check if the checkbox is currently enabled
//        if (!checkbox.isSelected()) {
//            // If checkbox is disabled, perform an action to enable it (e.g., click it)
//            checkbox.click();
//        }
//        else if (!checkbox.isSelected()==false) {
//        	checkbox.click();
//		}
        driver.findElement(By.xpath("//a[@id='btn17']")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
//		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
//		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
//		srch.click();
//		srch.click();
//		srch.sendKeys("Purchase Order Form");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order Form')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btnClearCacheReloadForm']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute1 = PO_NO1.getAttribute("value");
	    System.out.println(attribute1);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute2 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute2);
	    WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
	    String attribute3 = PR_No.getText();
	    System.out.println(attribute3);
	    WebElement supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
	    String attribute4 = supplier.getText();
	    System.out.println(attribute4);
	    WebElement Gross_Amount = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
	    String attribute5 = Gross_Amount.getAttribute("value");
	    System.out.println(attribute5);
	    WebElement Freight_Charges = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
	    String attribute6 = Freight_Charges.getAttribute("value");
	    System.out.println(attribute6);
	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
	    String attribute7 = Address.getText();
	    System.out.println(attribute7);
	    WebElement GSTIN = driver.findElement(By.xpath("//input[@id='gst000F1']"));
	    String attribute8 = GSTIN.getAttribute("value");
	    System.out.println(attribute8);
	    WebElement Contact_No = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
	    String attribute9 = Contact_No.getAttribute("value");
	    System.out.println(attribute9);
	    WebElement Email = driver.findElement(By.xpath("//input[@id='email000F1']"));
	    String attribute10 = Email.getAttribute("value");
	    System.out.println(attribute10);
	    WebElement Payment_Date = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
	    String attribute11 = Payment_Date.getAttribute("value");
	    System.out.println(attribute11);
	    WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
	    String attribute12 = Payment_Mode.getAttribute("value");
	    System.out.println(attribute12);
	    WebElement Payment_Terms = driver.findElement(By.xpath("//input[@id='payment_terms000F1']"));
	    String attribute13 = Payment_Terms.getAttribute("value");
	    System.out.println(attribute13);
	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
	    String attribute14 = Bill_To.getText();
	    System.out.println(attribute14);
	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute15 = Ship_To.getText();
	    System.out.println(attribute15);
	    WebElement Company_Logo = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
	    String attribute16 = Company_Logo.getText();
	    System.out.println(attribute16);
	    Thread.sleep(2000);
	    Actions a4 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
	    WebElement Item_Category = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
	    String attribute17 = Item_Category.getText();
	    System.out.println(attribute17);
	    WebElement Item_Name = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
	    String attribute18 = Item_Name.getText();
	    System.out.println(attribute18);
	    WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
	    String attribute19 = Quantity.getAttribute("value");
	    System.out.println(attribute19);
	    WebElement Uni_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
	    String attribute20 = Uni_Price.getAttribute("value");
	    System.out.println(attribute20);
	    WebElement Total_Amount = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
	    String attribute21 = Total_Amount.getAttribute("value");
	    System.out.println(attribute21);
	    WebElement Summary = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
	    String attribute22 = Summary.getText();
	    System.out.println(attribute22);
		driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\defaulttempwoutdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot12 = screenshot("TC_AP12 Checking and the user can modify the created Auto Print using ID Hyperlink.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+attribute7+"\r\n"
	    		+ "PO No - "+attribute1+" Payment Mode - "+attribute12+"\r\n"
	    		+ "GSTIN - "+attribute8+"\r\n"
	    		+ "PR No. -\r\n"
	    		+ ""+attribute3+"\r\n"
	    		+ "Payment Terms - "+attribute13+"\r\n"
	    		+ "Contact - "+attribute9+"\r\n"
	    		+ "Freight Charges - \r\n"
	    		+ ""+attribute6+"\r\n"
	    		+ " \r\n"
	    		+ "Email - "+attribute10+"    \r\n"
	    		+ "SHIP TO: BILL TO:\r\n"
	    		+ ""+attribute15+"\r\n"
	    		+ ""+attribute14+"\r\n"
	    		+ "                                    \r\n"
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+condtion+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 12 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(12, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 12 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(12, actual, Expected, e);
		}
	}
	
	@Test(priority=13)
	public void Delete_Autoprint_Using_Name_Hyperlink() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_AP13 Checking and delete the created Fast Print using Name Hyperlink")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		WaitForLoadingIconDisappear();		
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		driver.findElement(By.xpath("//button[@title='Confirm']")).click();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("Purchase Order Form-(purch)");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 13 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(13, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 13 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(13, actual, Expected, e);
		}
	
	}
	public static String screenshot(String screenshotname) throws IOException 
	{
		TakesScreenshot ss = (TakesScreenshot) driver;
		File screenshotAs1 = ss.getScreenshotAs(OutputType.FILE);
		File Destination1 = new File(Screenshotpath+screenshotname);
		FileUtils.copyFile(screenshotAs1, Destination1);
		String absolutePath = Destination1.getAbsolutePath();
		return absolutePath;
	}

	public void login()  
	{
		driver.get(schemaURL);
		try {
			selectproject();
		}
		catch(Exception e)
		{
		System.out.println("exception handled");
		}
		WebElement user = driver.findElement(By.id("axUserName"));
		user.sendKeys(UserLogin.username);
		driver.findElement(By.name("btnNext")).click();
		WebElement pass = driver.findElement(By.id("axPassword"));
		pass.sendKeys(UserLogin.password);
		driver.findElement(By.id("btnSubmitNew")).click();
	}
	
	public void ReloadIframe()
	{
	    JavascriptExecutor js= (JavascriptExecutor) driver;
	    js.executeScript("document.getElementsByTagName('iframe')[0].contentWindow.location.reload();");
	}
	
}

