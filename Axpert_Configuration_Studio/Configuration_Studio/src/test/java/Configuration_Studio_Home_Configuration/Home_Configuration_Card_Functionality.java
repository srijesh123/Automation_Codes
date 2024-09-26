package Configuration_Studio_Home_Configuration;


import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Axpert.Configuration_Studio.Launch;
import Axpert.Configuration_Studio_Fastprint.UserLogin;
import net.bytebuddy.matcher.MethodSortMatcher.Sort;

public class Home_Configuration_Card_Functionality extends Launch{

	@Test (priority=1)
	public void Home_Configuration_creation_page_Open() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_HC01 Checking the Home configuration creation page is opening or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable = driver.findElement(By.xpath("//span[contains(text(),'Home Configuration')]"));
        Actions c = new Actions(driver);
        Thread.sleep(2000);
              c.clickAndHold(clickable);
                Thread.sleep(2000);
                c.click();
               c.perform();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement button_click = driver.findElement(By.xpath("//div[@data-bs-original-title='New']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		WaitForLoadingIconDisappear();
		WebElement Home_Configuration_page = driver.findElement(By.xpath("//h1[contains(text(),'Home configuration')]"));
		String actual = Home_Configuration_page.getText();
		System.out.println(actual);
		String Screenshot2= screenshot("TC_HC02 Checking the Home configuration creation page is opening or not.Png");
		String Expected = "Home configuration";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 2 Passed");
			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(1, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 2 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(1, actual, Expected, e);
		}
	}
	
	@Test (priority=2)
	public void without_selecting_Page_type() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC02 Checking the submit the page without selecting Page type")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);	
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot2 = screenshot("TC_HC02 Checking the submit the page without selecting Page type.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Page type cannot be empty.";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 2 Passed");
			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(2, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 2 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(2, Toastmessage, ExpectedToast, e);
		}
	}
	
	@Test (priority=3)
	public void without_selecting_Page_Caption() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC03 Checking the submit the page without selecting Page caption")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Form");
		Thread.sleep(2000);
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot3 = screenshot("TC_HC03 Checking the submit the page without selecting Page caption.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Page caption cannot be empty.";
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
	public void without_Entering_Caption() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC04 Checking the submit the page without entering caption")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("All Fields in a Grid DC");
		driver.findElement(By.xpath("//li[contains(text(),'All Fields in a Grid DC')]")).click();
		WebElement Caption = driver.findElement(By.xpath("//input[@id='caption000F1']"));
		Caption.click();
		Caption.clear();
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot4 = screenshot("TC_HC04 Checking the submit the page without entering caption.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Caption cannot be empty.";
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
	public void without_entering_Button_name() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC05 Checking the submit the page without entering Button name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Caption = driver.findElement(By.xpath("//input[@id='caption000F1']"));
		Caption.click();
		Caption.sendKeys("All Fields in a Grid DC");
		WebElement More_Options = driver.findElement(By.xpath("//a[@id='ank2']"));
		More_Options.click();
		WebElement Button_Name = driver.findElement(By.xpath("//input[@id='btnname001F2']"));
		Button_Name.click();
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot5 = screenshot("TC_HC05 Checking the submit the page without entering Button name.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Button name cannot be empty. Rowno :1";
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
	public void without_entering_Title() throws InterruptedException, IOException
	{ 
		ExtentTest test = extent.createTest("TC_HC06 Checking the submit the page without entering Title")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Button_Name = driver.findElement(By.xpath("//input[@id='btnname001F2']"));
		Button_Name.click();
		Button_Name.sendKeys("Button1");
		WaitForLoadingIconDisappear();
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot6 = screenshot("TC_HC06 Checking the submit the page without entering Title.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Title cannot be empty. Rowno :1";
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
	public void without_Selecting_Action() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC07 Checking the submit the page without selecting action")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		WebElement Title = driver.findElement(By.xpath("//input[@id='btntitle001F2']"));
		Title.click();
		Title.click();
		Title.sendKeys("Load Form");
		WaitForLoadingIconDisappear();
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot7 = screenshot("TC_HC07 Checking the submit the page without selecting action.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Action cannot be empty. Rowno :1";
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
	public void Verify_Page_caption_lov_is_listing() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC08 Checking without selecting Page type the Page caption lov is listing or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("//a[@id='ank1']")).click();
		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		driver.findElement(By.xpath("//button[@title='Confirm']")).click();
		WaitForLoadingIconDisappear();
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Result = driver.findElement(By.xpath("//li[contains(text(),'No results found')]"));
		String Screenshot8 = screenshot("TC_HC08 Checking without selecting Page type the Page caption lov is listing or not.Png");
		String actual = Result.getText();
		System.out.println(actual);
		String Expected = "No results found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 8 Passed");
			test.addScreenCaptureFromPath(Screenshot8);
			excelpass(8, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 8 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot8);
			excelfail(8, actual, Expected, e);
		}
	}
	
	@Test (priority=9)
	public void Checking_Default_Formats() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC09 Checking the Defaults formats are listing or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.findElement(By.xpath("//select[@id='pagetype000F1']")).click();
		WebElement Option1 = driver.findElement(By.xpath("//option[@value='Form']"));
		String actual1 = Option1.getAttribute("value");
		System.out.println(actual1); 
		WebElement Option2 = driver.findElement(By.xpath("//option[@value='Report']"));
		String actual2 = Option2.getAttribute("value");
		System.out.println(actual2); 
		WebElement Option3 = driver.findElement(By.xpath("//option[@value='Custom page']"));
		String actual3 = Option3.getAttribute("value");
		System.out.println(actual3); 
		String actual = actual1 + "\r\n" + actual2 + "\r\n" + actual3;
		String Expected = "Form\r\n"
				+ "Report\r\n"
				+ "Custom page";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 9 Passed");
//			test.addScreenCaptureFromPath(Screenshot8);
			excelpass(9, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 9 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot8);
			excelfail(9, actual, Expected, e);
		}
	}
	
	@Test (priority=10)
	public void Verify_all_Form_captions_lov_is_listing() throws InterruptedException, IOException, SQLException
	{
		ExtentTest test = extent.createTest("TC_HC10 Checking all Form captions are listing or not when Form Page type is selected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable = driver.findElement(By.xpath("//span[contains(text(),'Home Configuration')]"));
        Actions c = new Actions(driver);
        Thread.sleep(2000);
              c.clickAndHold(clickable);
                Thread.sleep(2000);
                c.click();
               c.perform();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement button_click = driver.findElement(By.xpath("//div[@data-bs-original-title='New']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		WaitForLoadingIconDisappear();		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Form");
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		Thread.sleep(2000);
		WebElement Result = driver.findElement(By.xpath("//ul[@id='select2-pagecaption000F1-results']"));
		String Screenshot10 = screenshot("TC_HC1 Checking all Form captions are listing or not when Form Page type is selected.Png");
		String actual = Result.getText();
		System.out.println(actual);
		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
	    Statement statement = connection.createStatement();
	    ResultSet res= statement.executeQuery(Form_query);
	    ArrayList<String> col_value = new ArrayList<String>();
			while (res.next())
			{
//			String Form_Caption = res.getString("caption");	
			col_value.add(res.getString("caption"));
////			String Expected_Value_DB = Form_Caption;
////			System.out.println(Expected_Value_DB);
			}
		ArrayList<String> value = col_value;
		String abc = value.toString();
		String Expected = abc;
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 10 Passed");
			test.addScreenCaptureFromPath(Screenshot10);
			excelpass(10, actual, Expected);
			
		} 
		catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 10 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot10);
			excelfail(10, actual, Expected, e);
		}
			}
//	}
	
	@Test (priority=11)
	public void Verify_all_Report_captions_lov_is_listing() throws InterruptedException, IOException, SQLException
	{
		ExtentTest test = extent.createTest("TC_HC11 Checking all Report captions are listing or not when Form Page type is selected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Report");
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		Thread.sleep(2000);
		WebElement Result = driver.findElement(By.xpath("//ul[@id='select2-pagecaption000F1-results']"));
		String Screenshot11 = screenshot("TC_HC11 Checking all Report captions are listing or not when Form Page type is selected.Png");
		String actual = Result.getText();
		System.out.println(actual);
		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
	    Statement statement = connection.createStatement();
	    ResultSet res= statement.executeQuery(Report_query);
			while (res.next())
			{
			String Form_Caption = res.getString("caption");	
			String Expected_Value_DB = Form_Caption;
			System.out.println(Expected_Value_DB);
		String Expected = Expected_Value_DB;
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 11 Passed");
			test.addScreenCaptureFromPath(Screenshot11);
			excelpass(11, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 11 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot11);
			excelfail(11, actual, Expected, e);
		}
			}
	}
	
	@Test (priority=12)
	public void Verify_all_Custom_Page_captions_lov_is_listing() throws InterruptedException, IOException, SQLException
	{
		ExtentTest test = extent.createTest("TC_HC12 Checking all Custom page captions are listing or not when Form Page type is selected")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Custom page");
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		Thread.sleep(2000);
		WebElement Result = driver.findElement(By.xpath("//ul[@id='select2-pagecaption000F1-results']"));
		String Screenshot12 = screenshot("TC_HC12 Checking all Custom page captions are listing or not when Form Page type is selected.Png");
		String actual = Result.getText();
		System.out.println(actual);
		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
	    Statement statement = connection.createStatement();
	    ResultSet res= statement.executeQuery(Html_query);
			while (res.next())
			{
			String Form_Caption = res.getString("caption");	
			String Expected_Value_DB = Form_Caption;
			System.out.println(Expected_Value_DB);
		String Expected = Expected_Value_DB;
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 12 Passed");
			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(12, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 12 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(12, actual, Expected, e);
		}
			}
	}
	
	@Test (priority=13)
	public void Verify_Caption_field_is_filled() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC13 Checking based on Page caption the Caption field is filled or not")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Active list");
		driver.findElement(By.xpath("//li[contains(text(),'Active list')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Caption = driver.findElement(By.xpath("//input[@id='caption000F1']"));
		String Screenshot13 = screenshot("TC_HC13 Checking based on Page caption the Caption field is filled or not.Png");
		String actual = Caption.getAttribute("value");
		System.out.println(actual);
		String Expected = "Active list";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 13 Passed");
			test.addScreenCaptureFromPath(Screenshot13);
			excelpass(13, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 13 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot13);
			excelfail(13, actual, Expected, e);
		}
	}
	
	@Test (priority=14)
	public void Submit_With_Previous_Page_Caption() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC14 Checking and submit the modified page type format with previous page caption")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		Select objSelect1 = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect1.selectByVisibleText("Form");
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot14 = screenshot("TC_HC14 Checking and submit the modified page type format with previous page caption.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Invalid selection \"Active list\" in Page caption*";
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
	
	@Test (priority=15)
	public void Submit_Duplicate_Caption() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC15 Checking and submit the duplicate caption")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Sales Order");
		driver.findElement(By.xpath("(//li[contains(text(),'Sales Order')])")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		Save();
		driver.switchTo().defaultContent();
		WebElement toast = driver.findElement(By.xpath("//div[@class='toast-message']"));
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(toast));
		String Screenshot15 = screenshot("TC_HC15 Checking and submit the duplicate caption.Png");
		String Toastmessage = toast.getText();
		System.out.println(toast);
		String ExpectedToast = "Duplicate values are not allowed in Caption*";
		try {
			assertEquals(Toastmessage, ExpectedToast);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 15 Passed");
			test.addScreenCaptureFromPath(Screenshot15);
			excelpass(15, Toastmessage, ExpectedToast);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 15 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot15);
			excelfail(15, Toastmessage, ExpectedToast, e);
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
