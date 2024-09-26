package Configuration_Studio_Home_Configuration;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Axpert.Configuration_Studio.Launch;

public class UserLogin extends Launch {
	// static WebDriver driver;

	@Test(priority = 1)
	public void validuname_validpassword() throws InterruptedException, IOException {
		//User login with valid username and password
		ExtentTest test= extent.createTest("TS-1101 (valid Username With Valid Password)").assignAuthor(Testauthor)
				.assignCategory(Testcategory).assignDevice(browsername);
		try {
			selectproject();
		} catch (Exception e) {
			System.out.println("Exception Handled");
		}
		WebElement username = driver.findElement(By.id("axUserName"));
		username.sendKeys(UserLogin.username);
		WebElement password = driver.findElement(By.id("axPassword"));
		password.sendKeys(UserLogin.password);
		WebElement login = driver.findElement(By.id("btnSubmit"));
		login.click();
		WaitForLoadingIconDisappear();
		WebElement usericon = driver.findElement(By.xpath("//span[contains(text(),'person')]"));
		usericon.click();
		WaitForLoadingIconDisappear();
		String screenshot1 = screenshot("TS-1101 Valid Username With Valid Password.Png");
		WebElement uname = driver.findElement(By.xpath("//div[@id=\"AxNickName\"]"));
		String actual = uname.getText();
		System.out.println(actual);
		String expected = UserLogin.username;
		File f = new File(excelreportpath);
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		try {
			assertEquals(actual, expected);
			test.log(Status.PASS,"Passed");
			test.pass("Test Case scenario 1 Passed");
			test.addScreenCaptureFromPath(screenshot(screenshot1));
			wb.getSheetAt(0).getRow(10).getCell(6).setCellValue(expected);
			wb.getSheetAt(0).getRow(10).getCell(7).setCellValue(actual);
			wb.getSheetAt(0).getRow(10).getCell(8).setCellValue("Pass");
			wb.getSheetAt(0).getRow(10).createCell(9);	
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			wb.close();
		} catch (AssertionError e) {
			test.fail("Failed due to "+e.getMessage());
			test.addScreenCaptureFromPath(screenshot(screenshot1));
			wb.getSheetAt(0).getRow(10).getCell(6).setCellValue(expected);
			wb.getSheetAt(0).getRow(10).getCell(7).setCellValue(actual);
			wb.getSheetAt(0).getRow(10).getCell(8).setCellValue("Fail");
			String message = e.getMessage();
			wb.getSheetAt(0).getRow(10).getCell(9).setCellValue(message);
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			wb.close();
		}	
	}

	
		public static String screenshot(String screenshotname) throws IOException {
			TakesScreenshot ss = (TakesScreenshot) driver;
			File screenshotAs1 = ss.getScreenshotAs(OutputType.FILE);
			File Destination1 = new File(Screenshotpath+screenshotname);
			FileUtils.copyFile(screenshotAs1, Destination1);
			String absolutePath = Destination1.getAbsolutePath();
			return absolutePath;
		}	
	}