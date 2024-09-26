package Configuration_Studio_Home_Configuration;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Axpert.Configuration_Studio.Launch;
import Axpert.Configuration_Studio_Fastprint.UserLogin;

public class Custom_Page_Type_Card extends Launch{

	@Test (priority=1)
	public void Submit_Page_Type_With_Custom_page() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC30 Checking and submit the Custom page, Page Type home configuration")
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
		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Custom page");
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Welcome Page");
		driver.findElement(By.xpath("//li[contains(text(),'Welcome Page')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//h1[contains(text(),'Home configuration')]")).click();
        Actions a = new Actions(driver);
        for (int i = 0; i < 3; i++) {
            // Scroll up by 1000 pixels
		 	a.sendKeys(Keys.PAGE_DOWN).build().perform();
            try {
                // Add a delay to see the scrolling action
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		WebElement Create_under_group = driver.findElement(By.xpath("//input[@id='grouped000F1']"));
		Create_under_group.click();
		WebElement Group_Folder = driver.findElement(By.xpath("//input[@id='groupfolder000F1']"));
		String Group = Group_Folder.getAttribute("value");
		System.out.println(Group);
		Save();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable1 = driver.findElement(By.xpath("//span[contains(text(),'Mem DB Console')]"));
        Actions c1 = new Actions(driver);
        Thread.sleep(2000);
              c1.clickAndHold(clickable1);
                Thread.sleep(2000);
                c1.click();
                c1.click();
                c1.perform();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));     
        Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='chkall'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Clear cache')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		driver.navigate().refresh();
		login();
		Actions a1 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Card = driver.findElement(By.xpath("(//span[contains(text(),'Welcome Page')])[2]"));
		String card = Card.getText();
		System.out.println(card);
		Card.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		WebElement Formname = driver.findElement(By.xpath("//h1[contains(text(),'Welcome Page')]"));
		String actual = Formname.getText();
		System.out.println(actual);
		String Expected = "Welcome Page";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 30 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(30, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 30 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(30, actual, Expected, e);
		}
	}
	
	@Test (priority=2)
	public void With_Datasource_For_Custom_Page() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC31 Checking and submit the card with data source \"Custom Page\" format and after click on nurmeric hyperlink the respective html page is opening or not at runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
        WebElement Purchase_Order_Form = driver.findElement(By.xpath("(//a[contains(text(),'Welcome Page')])[1]"));
        Purchase_Order_Form.click();
        WaitForLoadingIconDisappear();
        driver.findElement(By.xpath("//h1[contains(text(),'Home configuration')]")).click();
        Actions a = new Actions(driver);
        for (int i = 0; i < 3; i++) {
            // Scroll up by 1000 pixels
		 	a.sendKeys(Keys.PAGE_DOWN).build().perform();
            try {
                // Add a delay to see the scrolling action
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.findElement(By.xpath("//span[@aria-labelledby='select2-datasource000F1-container']")).click();
        WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Custom_Page_Datasource");
		driver.findElement(By.xpath("(//li[contains(text(),'Custom_Page_Datasource')])")).click();
        Save();
        WaitForLoadingIconDisappear();
        driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable1 = driver.findElement(By.xpath("//span[contains(text(),'Mem DB Console')]"));
        Actions c1 = new Actions(driver);
        Thread.sleep(2000);
              c1.clickAndHold(clickable1);
                Thread.sleep(2000);
                c1.click();
                c1.click();
                c1.perform();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));  
        Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='chkall'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Clear cache')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		driver.navigate().refresh();
		login();
		Actions a1 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		 driver.findElement(By.xpath("(//span[contains(text(),'more_vert')])[5]")).click();
		 driver.findElement(By.xpath("(//span[contains(text(),'ordno')])[1]")).click();
		 WaitForLoadingIconDisappear();
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		 WebElement Formname = driver.findElement(By.xpath("//h1[contains(text(),'Welcome Page')]"));
		 String actual = Formname.getText();
		 System.out.println(actual);
		 String Expected = "Welcome Page";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 31 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(31, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 31 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(31, actual, Expected, e);
			}
	}
	
	@Test (priority=3)
	public void Add_Custom_page_Action_Button() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC32 Checking and Add the the action button in \"Custom page\" format and the button is reflected or not and on click able to Open the respective Custom page at runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
		 // Find the page caption element
//        WebElement pageCaption = driver.findElement(By.xpath("//th[contains(text(),'Analytics')]"));

        // Check if the caption contains "Analytics"
//        if (pageCaption.getText().contains("Analytics")) {
//            // Find and click the "Custom page" hyperlink
//            WebElement customPageLink = driver.findElement(By.xpath("(//a[contains(text(),'Custom page')])[1]"));
//            customPageLink.click();
//        }
        WebElement Purchase_Order_Form = driver.findElement(By.xpath("(//a[contains(text(),'Welcome Page')])[1]"));
        Purchase_Order_Form.click();
        WaitForLoadingIconDisappear();
        WebElement More_Options = driver.findElement(By.xpath("(//a[contains(text(),'More options')])[1]"));
        More_Options.click();
        WebElement BtnName = driver.findElement(By.xpath("//input[@id='btnname001F2']"));
        BtnName.click();
        BtnName.sendKeys("Button");
        WaitForLoadingIconDisappear();
        Thread.sleep(2000);
        WebElement BtnTitle = driver.findElement(By.xpath("//input[@id='btntitle001F2']"));
        BtnTitle.click();
        BtnTitle.click();
        BtnTitle.sendKeys("Open Custom page");
        driver.findElement(By.xpath("//span[@aria-labelledby='select2-btnaction001F2-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'Custom')]")).click();
        WebElement Custom = driver.findElement(By.xpath("//input[@id='custompage001F2']"));
        Custom.click();
        Custom.sendKeys("Page with Html and CSS");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[2]/a[1]")).click();
        WaitForLoadingIconDisappear();
        driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable1 = driver.findElement(By.xpath("//span[contains(text(),'Mem DB Console')]"));
        Actions c1 = new Actions(driver);
        Thread.sleep(2000);
              c1.clickAndHold(clickable1);
                Thread.sleep(2000);
                c1.click();
                c1.click();
                c1.perform();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));  
        Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@id='chkall'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Clear cache')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		driver.navigate().refresh();
		login();
		Actions a1 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		 driver.findElement(By.xpath("(//span[contains(text(),'more_vert')])[5]")).click();
		 driver.findElement(By.xpath("//div[contains(text(),'Open Custom page')]")).click();
		 WaitForLoadingIconDisappear();
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		 WebElement Formname = driver.findElement(By.xpath("//h1[contains(text(),'Page with Html and CSS')]"));
		 String actual = Formname.getText();
		 System.out.println(actual);
		 String Expected = "Page with Html and CSS";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 32 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(32, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 32 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(32, actual, Expected, e);
			}
	}
	
	@Test (priority=4)
	public void Modify_Page_Type_With_Custom_Page() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC33 Checking and modify the Custom page Page Type home configuration")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
        WebElement Purchase_Order_Form = driver.findElement(By.xpath("(//a[contains(text(),'Welcome Page')])[1]"));
        Purchase_Order_Form.click();
        WaitForLoadingIconDisappear();
        WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Hello Page");
		driver.findElement(By.xpath("(//li[contains(text(),'Hello Page')])")).click();
		WaitForLoadingIconDisappear();
//		WebElement Create_under_group = driver.findElement(By.xpath("//input[@id='grouped000F1']"));
//		Create_under_group.click();
		WebElement Group_Folder = driver.findElement(By.xpath("//input[@id='groupfolder000F1']"));
		String Group = Group_Folder.getAttribute("value");
		System.out.println(Group);
		 Actions a = new Actions(driver);
	        for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    driver.findElement(By.xpath("(//button[@aria-label='Remove all items'])[2]")).click();
		Save();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable1 = driver.findElement(By.xpath("//span[contains(text(),'Mem DB Console')]"));
        Actions c1 = new Actions(driver);
        Thread.sleep(2000);
              c1.clickAndHold(clickable1);
                Thread.sleep(2000);
                c1.click();
                c1.click();
                c1.perform();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));        
		driver.findElement(By.xpath("(//input[@id='chkall'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Clear cache')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.navigate().refresh();
		login();
		Actions a1 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Card = driver.findElement(By.xpath("(//span[contains(text(),'Hello Page')])[2]"));
		String card = Card.getText();
		System.out.println(card);
		Card.click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		WebElement Formname = driver.findElement(By.xpath("//h1[contains(text(),'Hello Page')]"));
		String actual = Formname.getText();
		System.out.println(actual);
		String Expected = "Hello Page";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 33 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(33, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 33 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(33, actual, Expected, e);
			}
	}
	
	@Test (priority=5)
	public void Modify_Add_New_Button() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_HC34 Checking, modify and add the button for Report Page Type home configuration")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Hello Page')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
        WebElement More_Options = driver.findElement(By.xpath("(//a[contains(text(),'More options')])[1]"));
        More_Options.click();
        driver.findElement(By.xpath("//a[@id='gridAddBtn2']")).click();
        WebElement Button_Name = driver.findElement(By.xpath("//input[@id='btnname002F2']"));
        Button_Name.click();
        Button_Name.sendKeys("Button1");
        WaitForLoadingIconDisappear();
        WebElement Title = driver.findElement(By.xpath("//input[@id='btntitle002F2']"));
        Title.click();
        Title.click();
        Title.sendKeys("Open Form");
        driver.findElement(By.xpath("//span[@aria-labelledby='select2-btnaction002F2-container']")).click();
        driver.findElement(By.xpath("//li[contains(text(),'Open form')]")).click();
        driver.findElement(By.xpath("//span[@aria-labelledby='select2-axpstruct002F2-container']")).click();
        WebElement Search1 = driver.findElement(By.xpath("//input[@aria-label='Search']"));
        Search1.sendKeys("Loan Application Form-(Lnapp)");
        driver.findElement(By.xpath("//li[contains(text(),'Loan Application Form-(Lnapp)')]")).click();
        WaitForLoadingIconDisappear();
        driver.findElement(By.xpath("//a[@id='ftbtn_iSave']")).click();
        WaitForLoadingIconDisappear();
        driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement clickable1 = driver.findElement(By.xpath("//span[contains(text(),'Mem DB Console')]"));
        Actions c1 = new Actions(driver);
        Thread.sleep(2000);
              c1.clickAndHold(clickable1);
                Thread.sleep(2000);
                c1.click();
                c1.click();
                c1.perform();
        driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));        
		driver.findElement(By.xpath("(//input[@id='chkall'])[1]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Clear cache')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		driver.navigate().refresh();
		login();
		Actions a1 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		 driver.findElement(By.xpath("(//span[contains(text(),'more_vert')])[5]")).click();
		 driver.findElement(By.xpath("//div[contains(text(),'Open Form')]")).click();
		 WaitForLoadingIconDisappear();
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		 WebElement Reportname = driver.findElement(By.xpath("//h1[contains(text(),'Loan Application Form')]"));
		 String actual = Reportname.getText();
		 System.out.println(actual);
		 String Expected = "Loan Application Form";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 34 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(34, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 34 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(34, actual, Expected, e);
			}
	}
	
	@Test (priority=6)
	public void Hide_Card() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP35 Checking & hide the card")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Hello Page')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
        driver.findElement(By.xpath("//h1[contains(text(),'Home configuration')]")).click();
        Actions a1 = new Actions(driver);
		 for (int i = 0; i < 3; i++) {
	            // Scroll up by 1000 pixels
			 	a1.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		 
		 // Find all checkboxes
	        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@id='cardhide000F1']"));

	        // Loop through all checkboxes
	        for (WebElement checkbox : checkboxes) {
	            // Check if the checkbox is already enabled (checked)
	            if (checkbox.isSelected()) {
	                // If the checkbox is enabled, do nothing or you can print a message
	                System.out.println("Hide Checkbox is already enabled.");
	            } else {
	                // If the checkbox is disabled, enable it (check it)
	                checkbox.click();
	                System.out.println("Hide Checkbox is now enabled.");
	            }
	        }
			 Thread.sleep(2000);
			 WebElement Card_Hide = driver.findElement(By.xpath("//input[@id='cardhide000F1']"));
			 String actual = Card_Hide.getAttribute("value");
			 System.out.println(actual);
			 Save();
			 WaitForLoadingIconDisappear();
			 driver.switchTo().defaultContent();
				driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
				driver.findElement(By.xpath("//div[@id='mainProfileIcon']")).click();
				driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
				driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
				driver.navigate().refresh();
				login();
				Actions a2 = new Actions(driver);
				 for (int i = 0; i < 1; i++) {
			            // Scroll up by 1000 pixels
					 	a2.sendKeys(Keys.PAGE_DOWN).build().perform();
			            try {
			                // Add a delay to see the scrolling action
			                Thread.sleep(1000);
			            } catch (InterruptedException e) {
			                e.printStackTrace();
			            }
			        }
				 Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
				    Statement statement = connection.createStatement();
				    ResultSet res= statement.executeQuery(Hide_query_Custom);
						while (res.next())
						{
						String Form_Caption = res.getString("cardhide");	
						String Expected_Value_DB = Form_Caption;
						System.out.println(Expected_Value_DB);
					String Expected = Expected_Value_DB;
						boolean wordExists = actual.contains(Expected);
						if(wordExists==true)
						{
							test.log(Status.PASS, "passed");
							test.pass("Test Case scenario Passed");
//							test.addScreenCaptureFromPath(Screenshot1);
							File f = new File(excelreportpath);
							FileInputStream fis =  new FileInputStream(f);
							Workbook wb = new XSSFWorkbook(fis);
							FileOutputStream fos= new FileOutputStream(f);
							wb.getSheetAt(0).getRow(35).getCell(6).setCellValue("Hello Page Custom card should get hided from dashboard");
							wb.getSheetAt(0).getRow(35).getCell(7).setCellValue("Hello Page Custom is hided from dashboard");
							wb.getSheetAt(0).getRow(35).getCell(8).setCellValue("pass");
							wb.getSheetAt(0).getRow(35).getCell(9);
							wb.write(fos);
							wb.close();
							System.out.println("Passed");
						}
						else
						{
							test.log(Status.FAIL, "Failed");
							test.fail("Test Case scenario Failed due to the Hello Page Custom card is not hiding from dashboard" );
//							test.addScreenCaptureFromPath(Screenshot1);
							File f = new File(excelreportpath);
							FileInputStream fis =  new FileInputStream(f);
							Workbook wb = new XSSFWorkbook(fis);
							FileOutputStream fos= new FileOutputStream(f);
							wb.getSheetAt(0).getRow(35).getCell(6).setCellValue("Hello Page Custom card should get hided from dashboard");
							wb.getSheetAt(0).getRow(35).getCell(7).setCellValue("Hello Page Custom card is does not hide from dashboard");
							wb.getSheetAt(0).getRow(35).getCell(8).setCellValue("Fail");
							wb.getSheetAt(0).getRow(35).getCell(9).setCellValue("Hello Page Custom card is not hiding from dashboard");
							wb.write(fos);
							wb.close();
							System.out.println("Failed");
						}	
						}
	}
	
	@Test(priority=7)
	public void Delete_Created_Home_Configuration() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP36 Checking & deleting the Report Home Configuration through hyperlink")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		driver.switchTo().defaultContent();
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Hello Page')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("Hello Page");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 36 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(36, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 36 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(36, actual, Expected, e);
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
