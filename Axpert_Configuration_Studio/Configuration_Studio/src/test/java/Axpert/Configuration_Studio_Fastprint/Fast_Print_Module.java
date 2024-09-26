package Axpert.Configuration_Studio_Fastprint;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Axpert.Configuration_Studio.Launch;

public class Fast_Print_Module extends Launch {

	@Test (priority=1)
	public void Button_click_verification() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP01 Checking and clicking the new button")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Option = driver.findElement(By.xpath("//span[contains(text(),'apps')]"));
		Option.click();
		WebElement button_click = driver.findElement(By.xpath("//span[contains(text(),'New')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		Boolean isSelected = button_click.isSelected();
		if(isSelected == false)
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
	
	@Test (priority=2)
	public void Fast_print_creation_page_Open() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP02 Checking the Fast print creation page is opening or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WaitForLoadingIconDisappear();
		WebElement Fastprint_page = driver.findElement(By.xpath("//div[@id='dvlayout']/div/div/div/h1[1]"));
		String actual = Fastprint_page.getText();
		System.out.println(actual);
		String Screenshot2= screenshot("TC_FP02 Checking the Fast print creation page is opening or not.Png");
		String Expected = "Fast print";
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

	@Test (priority=3)
	public void Without_Print_Form_Name() throws IOException
	{
		ExtentTest test = extent.createTest("TC_FP03 Checking the Generating Fast Report without entering print form name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot3 = screenshot("TC_FP03 Checking the Generating Fast Report without entering print form name.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Print Form Name cannot be empty.";
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
	
	@Test (priority=4)
	public void With_Print_Form_Name() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP04 Checking the Generating Fast Report with entering print form name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement print_form_name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		print_form_name.sendKeys("default");
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot4 = screenshot("TC_FP04 Checking the Generating Fast Report with entering print form name.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Print format cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 4 Passed");
			test.addScreenCaptureFromPath(Screenshot4);
			excelpass(4, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 4 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot4);
			excelfail(4, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=5)
	public void Without_Print_format() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP05 Checking the Generating Fast Report without selecting print format")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot5 = screenshot("TC_FP05 Checking the Generating Fast Report without selecting print format.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Print format cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 5 Passed");
			test.addScreenCaptureFromPath(Screenshot5);
			excelpass(5, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 5 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot5);
			excelfail(5, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=6)
	public void With_Print_format() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP06 Checking the Generating Fast Report with selecting print format")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot6 = screenshot("TC_FP06 Checking the Generating Fast Report with selecting print format.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Form/Tstruct cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 6 Passed");
			test.addScreenCaptureFromPath(Screenshot6);
			excelpass(6, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 6 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot6);
			excelfail(6, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=7)
	public void without_Form_Tstruct() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP07 Checking the Generating Fast Report without Form/Tstruct")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot7 = screenshot("TC_FP07 Checking the Generating Fast Report without Form/Tstruct.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Form/Tstruct cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 7 Passed");
			test.addScreenCaptureFromPath(Screenshot7);
			excelpass(7, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 7 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot7);
			excelfail(7, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=8)
	public void with_Form_Tstruct() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP08 Checking the Generating Fast Report with Form/Tstruct")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		driver.findElement(By.xpath("//li[contains(text(),' Select From List-(sflis)')]")).click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot8 = screenshot("TC_FP08 Checking the Generating Fast Report with Form/Tstruct.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Paper Size cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 8 Passed");
			test.addScreenCaptureFromPath(Screenshot8);
			excelpass(8, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 8 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot8);
			excelfail(8, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=9)
	public void without_Paper_Size() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP09 Checking the Generating Fast Report without Paper Size")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot9 = screenshot("TC_FP09 Checking the Generating Fast Report without Paper Size.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Paper Size cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 9 Passed");
			test.addScreenCaptureFromPath(Screenshot9);
			excelpass(9, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 9 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot9);
			excelfail(9, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=10)
	public void with_Paper_Size() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP10 Checking the Generating Fast Report with Paper Size")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		driver.findElement(By.xpath("//li[contains(text(),'A4')]")).click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot10 = screenshot("TC_FP10 Checking the Generating Fast Report with Paper Size.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Report Output Format cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 10 Passed");
			test.addScreenCaptureFromPath(Screenshot10);
			excelpass(10, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 10 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot10);
			excelfail(10, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=11)
	public void Validate_Paper_Size() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP11 Checking the Generating Fast Report with Paper Size")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Paper_Height = driver.findElement(By.xpath("//input[@id='pheight000F1']"));
		Thread.sleep(2000);
		WebElement Paper_Width = driver.findElement(By.xpath("//input[@id='paper_width000F1']"));
		Thread.sleep(2000);
		String HeightValue = Paper_Height.getAttribute("value");
		String WidthValue = Paper_Width.getAttribute("value");
		String Screenshot11 = screenshot("TC_FP11 Checking the Generating Fast Report with Paper Size.Png");
		if(validatePaperSize1(WidthValue, HeightValue, "A4"))
		{	
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario Passed");
			test.addScreenCaptureFromPath(Screenshot11);
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(11).getCell(6).setCellValue("Paper size should get valid.");
			wb.getSheetAt(0).getRow(11).getCell(7).setCellValue("Paper size is valid.");
			wb.getSheetAt(0).getRow(11).getCell(8).setCellValue("Pass");
			wb.getSheetAt(0).getRow(11).getCell(9);
			wb.write(fos);
			wb.close();
			System.out.println("Passed");
		}
		else
		{
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario Failed due to Paper size is not valid");
			test.addScreenCaptureFromPath(Screenshot11);
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(11).getCell(6).setCellValue("Paper size should get valid.");
			wb.getSheetAt(0).getRow(11).getCell(7).setCellValue("Paper size is not valid.");
			wb.getSheetAt(0).getRow(11).getCell(8).setCellValue("Fail");
			wb.getSheetAt(0).getRow(11).getCell(9).setCellValue("Paper size is not valid");
			wb.write(fos);
			wb.close();
		}
	}
	
	@Test (priority=12)
	public void without_Report_Output_Format() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP12 Checking the Generating Fast Report without Report Output Format")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot12 = screenshot("TC_FP12 Checking the Generating Fast Report without Report Output Format.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Report Output Format cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 12 Passed");
			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(12, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 12 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(12, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=13)
	public void with_Report_Output_Format() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP13 Checking the Generating Fast Report with Report Output Format")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		Thread.sleep(2000);
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot13 = screenshot("TC_FP13 Checking the Generating Fast Report with Report Output Format.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Fast print Saved,,";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 13 Passed");
			test.addScreenCaptureFromPath(Screenshot13);
			excelpass(13, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 13 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot13);
			excelfail(13, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=14)
	public void Duplicate_Print_Form_Name() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("TC_FP14 Checking the Generating Fast Report with duplicate Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WaitForLoadingIconDisappear();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("default");
		WebElement Print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		Print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		driver.findElement(By.xpath("//li[contains(text(),' Select From List-(sflis)')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		Thread.sleep(2000);
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		driver.findElement(By.xpath("//li[contains(text(),'A4')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot14 = screenshot("TC_FP14 Checking the Generating Fast Report with duplicate Print Form Name.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Duplicate values are not allowed in Print Form Name*";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 14 Passed");
			test.addScreenCaptureFromPath(Screenshot14);
			excelpass(14, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 14 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot14);
			excelfail(14, Toastmessage, ExpectedToast, e);
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
}
