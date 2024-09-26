package Configuration_Studio_Home_Configuration;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
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

public class Group_folder extends Launch{

	@Test (priority=1)
	public void Single_Group_Folder_with_Form_Name() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP37 Checking create under group card with formname in home page at runtime")
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
		objSelect.selectByVisibleText("Form");
		WebElement Caption = driver.findElement(By.xpath("//label[@for='caption000F1']"));
		String Actual1 = Caption.getText();
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Ticket Management");
		driver.findElement(By.xpath("(//li[contains(text(),'Ticket Management')])")).click();
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
		WebElement Group_folder = driver.findElement(By.xpath("(//label[contains(text(),'Group folder')])[1]"));
		String Actual2 = Group_folder.getText();
		Thread.sleep(2000);
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
		 WebElement cardname = driver.findElement(By.xpath("(//span[contains(text(),'Ticket Management')])[2]"));
		 String Actual4 = cardname.getText();
//		 System.out.println(Actual2);
		 String actual = Actual2 + " - " + Group + " " + Actual1 + " - " + Actual4;
		 System.out.println(actual);
		 Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
		    Statement statement = connection.createStatement();
		    ResultSet res= statement.executeQuery(Single_Group_Caption_With_Form);
				while (res.next())
				{
				String groupfolder = res.getString("groupfolder");	
				String caption = res.getString("caption");
				String Expected_Value_DB = groupfolder + " " + caption;
				System.out.println(Expected_Value_DB);
			String Expected = Expected_Value_DB;
			 try {
					assertEquals(actual, Expected);
					test.log(Status.PASS, "passed");
					test.pass("Test Case scenario 37 Passed");
//					test.addScreenCaptureFromPath(Screenshot2);
					excelpass(37, actual, Expected);
					
				} catch (Exception e) {
					test.log(Status.FAIL, "Failed");
					test.fail("Test Case scenario 37 Failed due to " + e.getMessage());
//					test.addScreenCaptureFromPath(Screenshot2);
					excelfail(37, actual, Expected, e);
				}
				} 
	}
	
	@Test(priority=2)
	public void Delete_Single_Group_Folder_with_Form_Name() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP38 Checking & deleting the Single Group Folder Card with form name")
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Ticket Management')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='List View']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("Ticket Management");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 38 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(38, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 38 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(38, actual, Expected, e);
		}
	}
	
	@Test (priority=3)
	public void Single_Group_Folder_with_Folder_Name() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP39 Checking create under single group card in home page at runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
//		login();
//		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		Thread.sleep(2000);
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
		objSelect.selectByVisibleText("Form");
		WebElement Caption = driver.findElement(By.xpath("//label[@for='caption000F1']"));
		String Actual1 = Caption.getText();
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("All Fields in a Grid DC");
		driver.findElement(By.xpath("(//li[contains(text(),'All Fields in a Grid DC')])")).click();
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
		WebElement Group_folder = driver.findElement(By.xpath("(//label[contains(text(),'Group folder')])[1]"));
		String Actual2 = Group_folder.getText();
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
		 WebElement Gpname = driver.findElement(By.xpath("//h4[contains(text(),'Tstruct')]"));
		 String Actual3 = Gpname.getText();
//		 System.out.println(Actual1);
		 WebElement cardname = driver.findElement(By.xpath("(//span[contains(text(),'All Fields in a Grid DC')])[2]"));
		 String Actual4 = cardname.getText();
//		 System.out.println(Actual2);
		 String actual = Actual2 + " - " + Actual3 + " " + Actual1 + " - " + Actual4;
		 System.out.println(actual);
		 Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
		    Statement statement = connection.createStatement();
		    ResultSet res= statement.executeQuery(Single_Group_Caption);
				while (res.next())
				{
				String groupfolder = res.getString("groupfolder");	
				String caption = res.getString("caption");
				String Expected_Value_DB = groupfolder + " " + caption;
				System.out.println(Expected_Value_DB);
			String Expected = Expected_Value_DB;
			try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 39 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(39, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 39 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(39, actual, Expected, e);
			}
				} 
	}
	
	@Test(priority=4)
	public void Delete_Single_Group_Folder_Card() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP40 Checking & deleting the Single Group Folder Card")
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'All Fields in a Grid DC')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='List View']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("All Fields in a Grid DC");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 40 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(40, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 40 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(40, actual, Expected, e);
		}
	}
	
	@Test (priority=5)
	public void Multiple_Group_Folder() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP41 Checking create under multiple group card in home page at runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
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
		WebElement button_click = driver.findElement(By.xpath("//div[@data-bs-original-title='New']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		Select objSelect = new Select(driver.findElement(By.xpath("//Select[@id='pagetype000F1']")));
		objSelect.selectByVisibleText("Report");
		WebElement Caption = driver.findElement(By.xpath("//label[@for='caption000F1']"));
		String Actual1 = Caption.getText();
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Purchase Request with params");
		driver.findElement(By.xpath("(//li[contains(text(),'Purchase Request with params')])")).click();
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
		WebElement Group_folder = driver.findElement(By.xpath("(//label[contains(text(),'Group folder')])[1]"));
		String Actual2 = Group_folder.getText();
		Thread.sleep(2000);
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
		 WebElement Gpname = driver.findElement(By.xpath("//h4[contains(text(),'Product Details')]"));
		 String Actual3 = Gpname.getText();
//		 System.out.println(Actual1);
		 WebElement Sales = driver.findElement(By.xpath("(//span[contains(text(),'Sales Order')])[2]"));
		 String Actual4 = Sales.getText();
		 WebElement Pur_order = driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order')])[3]"));
		 String Actual5 = Pur_order.getText();
		 WebElement Pur_req = driver.findElement(By.xpath("(//span[contains(text(),'Purchase Request with params')])[2]"));
		 String Actual6 = Pur_req.getText();
//		 System.out.println(Actual2);
		 String actual = "" + Actual2 + ":" + "\r\n"
		 		+ Actual3 + "\r\n"
		 		+ Actual1 + " " +  ":" + "\r\n"
		 		+ Actual4 + "\r\n"
		 		+ Actual5
		 		+ Actual6;
		 System.out.println(actual);
		 Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
		    Statement statement = connection.createStatement();
		    ResultSet res= statement.executeQuery(Multiple_Group_Caption);
				while (res.next())
				{
				String groupfolder = res.getString("groupfolder");	
				String Expected_Value_DB = groupfolder;
				System.out.println(Expected_Value_DB);
			String Expected = Expected_Value_DB;
			try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 41 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(41, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 41 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(41, actual, Expected, e);
			}
				} 
	}
	
	@Test(priority=6)
	public void Delete_Multiple_Group_Folder_Card() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP42 Checking & deleting the Multiple Group Folder Card")
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Purchase Request with params')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='List View']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("Purchase Request with params");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 42 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(42, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 42 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(42, actual, Expected, e);
		}
	}
	
	@Test (priority=7)
	public void Without_Under_Group_Folder() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP43 Checking without under group card in home page at runtime")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
//		login();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
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
		objSelect.selectByVisibleText("Report");
//		WebElement Caption = driver.findElement(By.xpath("//label[@for='caption000F1']"));
//		String Actual1 = Caption.getText();
		WebElement Page_Caption = driver.findElement(By.xpath("//span[@aria-labelledby='select2-pagecaption000F1-container']"));
		Page_Caption.click();
		WebElement Search = driver.findElement(By.xpath("//input[@aria-label='Search']"));
		Search.sendKeys("Iview Properties");
		driver.findElement(By.xpath("(//li[contains(text(),'Iview Properties')])")).click();
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
//		WebElement Create_under_group = driver.findElement(By.xpath("//input[@id='grouped000F1']"));
//		Create_under_group.click();
//		WebElement Group_folder = driver.findElement(By.xpath("(//label[contains(text(),'Group folder')])[1]"));
//		String Actual2 = Group_folder.getText();
//		Thread.sleep(2000);
//		WebElement Group_Folder = driver.findElement(By.xpath("//input[@id='groupfolder000F1']"));
//		String Group = Group_Folder.getAttribute("value");
//		System.out.println(Group);
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
		 WebElement card1 = driver.findElement(By.xpath("(//span[@id='attendancetittle'])[1]"));
		 String Actual1 = card1.getText();
//		 System.out.println(Actual1);
		 WebElement card2 = driver.findElement(By.xpath("(//span[@id='attendancetittle'])[2]"));
		 String Actual2 = card2.getText();
		 WebElement card3 = driver.findElement(By.xpath("(//span[@id='attendancetittle'])[3]"));
		 String Actual3 = card3.getText();
//		 System.out.println(Actual2);
		 String actual = Actual1 + "\r\n"
		 		+ Actual2 + "\r\n"
		 		+ Actual3 + "\r\n";
		 System.out.println(actual);
		 Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
		    Statement statement = connection.createStatement();
		    ResultSet res= statement.executeQuery(Without_Group_Folder);
				while (res.next())
				{
				String groupfolder = res.getString("caption");	
				String Expected_Value_DB = groupfolder;
				System.out.println(Expected_Value_DB);
			String Expected = Expected_Value_DB;
			try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 43 Passed");
//				test.addScreenCaptureFromPath(Screenshot2);
				excelpass(43, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 43 Failed due to " + e.getMessage());
//				test.addScreenCaptureFromPath(Screenshot2);
				excelfail(43, actual, Expected, e);
			}
				} 
	}
	
	@Test(priority=8)
	public void Delete_Without_Group_Folder_Card() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP44 Checking & deleting the without Group Folder Card")
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
        WebElement Purchase_Request_Form = driver.findElement(By.xpath("(//a[contains(text(),'Iview Properties')])[1]"));
        Purchase_Request_Form.click();
        WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='List View']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("Iview Properties");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 44 Passed");
//			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(44, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 44 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(44, actual, Expected, e);
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
