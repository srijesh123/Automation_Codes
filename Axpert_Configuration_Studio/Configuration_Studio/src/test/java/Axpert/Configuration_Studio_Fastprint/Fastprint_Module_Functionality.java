package Axpert.Configuration_Studio_Fastprint;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

public class Fastprint_Module_Functionality extends Launch{
	
	@Test (priority=1)
	public void Verify_Default_Tempelate() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP15 Checking & verifying whether the default template is listing or not in Print Form Name")
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
		WaitForLoadingIconDisappear();
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		String Screenshot1 = screenshot("TC_FP15 Checking & verifying whether the default template is listing or not in Print Form Name.Png");
		WebElement Template = driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]"));
		String actual = Template.getText();
		System.out.println(actual);
		String Expected = "defaulttemplate";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 1 Passed");
			test.addScreenCaptureFromPath(Screenshot1);
			excelpass(15, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 1 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot1);
			excelfail(15, actual, Expected, e);
		}
		
	}

	@Test (priority=2)
	public void Default_template_without_DataSource() throws InterruptedException, IOException, AWTException, SQLException
	{
		ExtentTest test = extent.createTest("TC_FP16 Checking & submit the default template without datasource in Print Form Name")
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
		WaitForLoadingIconDisappear();
//		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WaitForLoadingIconDisappear();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("defaulttempwoutdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]")).click();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("Form data");
		driver.findElement(By.xpath("//li[contains(text(),'Form data')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(2000);
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("A5");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[contains(text(),'A5')]")).click();
		Actions a1 = new Actions(driver);
		a1.sendKeys(Keys.PAGE_DOWN).build().perform();	
		WebElement Print_application_logo = driver.findElement(By.xpath("//input[@id='print_app_logo000F1']"));
		Print_application_logo.click();
		WebElement Print_application_title = driver.findElement(By.xpath("//input[@id='print_app_title000F1']"));
		Print_application_title.click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Center.click();
		Center.sendKeys("<div style=\"text-align:right\"><span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong><span style=\"font-size:22px\"><span style=\"color:#f39c12\">PURCHASE ORDER</span></span></strong></span></div>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		Thread.sleep(2000);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		WebElement Report_Title_Left_Image = driver.findElement(By.xpath("//span[@aria-labelledby='select2-rt_image_leftui000F2-container']"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Report_Title_Left_Image));
		Report_Title_Left_Image.click();
		driver.findElement(By.xpath("//li[contains(text(),'Company Logo-(img)')]")).click();
		WebElement Report_Title_Left_Image_Height = driver.findElement(By.xpath("//input[@id='rt_image_left_height000F2']"));
		Report_Title_Left_Image_Height.click();
		Report_Title_Left_Image_Height.sendKeys("130");
		WebElement Report_Title_Left_Image_Width = driver.findElement(By.xpath("//input[@id='rt_image_left_Width000F2']"));
		Report_Title_Left_Image_Width.click();
		Report_Title_Left_Image_Width.sendKeys("70");
		Actions aa = new Actions(driver);
		aa.sendKeys(Keys.PAGE_UP).build().perform();
		Thread.sleep(2000);
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_Header));
		Page_Header.click();
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		WebElement Body = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(Body));
		Body.click();
		Body.sendKeys("<div style=\"text-align:center\"><strong><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"color:#2980b9\"><span style=\"font-size:22px\">INVOICE</span></span></span></strong><br />\r\n"
				+ "&nbsp;</div>");
		Body.sendKeys("<div>\r\n"
				+ "<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n"
				+ "	<tbody>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"color:#c0392b\"><span style=\"font-family:Arial,Helvetica,sans-serif\"><span");
		Body.sendKeys("style=\"font-size:16px\"><strong>Supplier -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{supplier[supplier]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PO Date -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{podate[podate]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>Payment Date -&nbsp;&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_date[payment_date]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Address -&nbsp;&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{address[address]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PO No -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{pono[pono]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Payment Mode -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_mode[payment_mode]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>GSTIN -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{grossamt[grossamt]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PR No. -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{tprno[tprno]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Payment Terms -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_terms[payment_terms]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Contact -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{contact_no[contact_no]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"color:#c0392b\"><span style=\"font-size:16px\">Freight Charges -&nbsp;</span></span></strong></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong> </strong><span style=\"font-size:12px\">{freight_charges[freight_charges]}</span></span></td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Email -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{email[email]}</span></span></td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("</tr>");
		Body.sendKeys("</tbody>");
		Body.sendKeys("</table>");
		Body.sendKeys("</div>");
		Actions a2 = new Actions(driver);
		 for (int i = 0; i < 9; i++) {
	            // Scroll up by 1000 pixels
			 	a2.sendKeys(Keys.PAGE_UP).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement srce2b = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2b));
		srce2b.click();
		Thread.sleep(2000);
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		WebElement srce3a = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce3a));
		srce3a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, fr_body000F4']"));
		Body1.click();
		Body1.sendKeys("<div>");
		Body1.sendKeys("<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Body1.sendKeys("<tbody>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><strong>SHIP TO:</strong></span></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><strong>BILL TO:</strong></span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{ship_to[ship_to]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{billto[billto]}</span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "	</tbody>\r\n"
				+ "</table>");
		Body1.sendKeys("&nbsp");
		Body1.sendKeys("&nbsp");
		Body1.sendKeys("<span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong></span></div>");
		Body1.sendKeys("<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Body1.sendKeys("<tbody>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">S.no</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Item Category</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Item Name</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Quantity</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Uni&nbsp;Price</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Total Amount</span></span></strong></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">1</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{prdcode[prdcode]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{prodname[prodname]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{quantity[quantity]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{uprice[uprice]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{totalamount[totalamount]}</span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Total</span></span></strong></span></td>");
		Body1.sendKeys("<td colspan=\"4\" rowspan=\"1\">&nbsp;</td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:14px\">{totpuamt[totpuamt]}</span></span></td>");
		Body1.sendKeys("</tr>");
		Body1.sendKeys("</tbody>");
		Body1.sendKeys("</table>");
		Body1.sendKeys("&nbsp");
		Actions a3 = new Actions(driver);
		 for (int i = 0; i < 9; i++) {
	            // Scroll up by 1000 pixels
			 	a3.sendKeys(Keys.PAGE_UP).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement srce3b = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce3b));
		srce3b.click();
		Thread.sleep(2000);
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body2 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body2.click();
		Body2.sendKeys("<div style=\"text-align:center\"><span style=\"font-family:Tahoma,Geneva,sans-serif\">43, Kambar Street, Chennai - 600543, Website :&nbsp;<span style=\"color:#c0392b\"><u>www.sm-purchase.com&nbsp; </u></span><span style=\"color:#000000\">Email:</span><span style=\"color:#c0392b\"><u> purchase-sm@gmail.com</u></span></span></div>");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
//	    WebElement Address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
//	    String attribute7 = Address.getText();
//	    System.out.println(attribute7);
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
//	    WebElement Bill_To = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
//	    String attribute14 = Bill_To.getText();
//	    System.out.println(attribute14);
//	    WebElement Ship_To = driver.findElement(By.xpath("//textarea[@id='ship_to000F1']"));
//	    String attribute15 = Ship_To.getText();
//	    System.out.println(attribute15);
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
	    Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
        Statement statement = connection.createStatement();
		ResultSet res= statement.executeQuery(query3);
		while (res.next())
		{
		String address = res.getString("address");	
		String billto = res.getString("billto");	
		String shipto = res.getString("ship_to");	
		String Item_details = address + " " + billto + " " + shipto;
		System.out.println(Item_details);
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Default Temp Withoutdtasrce ')]")).click();
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
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_defaulttempwoutdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot2 = screenshot("TC_FP16 Checking & submit the default template without datasource in Print Form Name.png");
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "PURCHASE ORDER\r\n"
	    		+ "INVOICE\r\n"
	    		+ " \r\n"
	    		+ "Supplier - "+attribute4+" PO Date - "+attribute2+"\r\n"
	    		+ "Payment Date -  "+attribute11+"\r\n"
	    		+ "Address -  "+address+"\r\n"
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
	    		+ ""+billto+""
	    		+ ""+shipto+""
	    		+ "S.no Item Category Item Name Quantity Uni Price Total Amount\r\n"
	    		+ "1 "+attribute17+" "+attribute18+" "+attribute19+" "+attribute20+" "+attribute21+"\r\n"
	    		+ "Total   "+attribute22+"\r\n"
	    		+ "43, Kambar Street, Chennai - 600543, Website : www.sm-purchase.com  Email: purchase-\r\n"
	    		+ "sm@gmail.com";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 2 Passed");
			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(16, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 2 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(16, actual, Expected, e);
		}
		}
	}
	
	@Test (priority=3)
	public void Delete_Default_template_without_DataSource() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP16 Checking & deleting the Default template without DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("//a[contains(text(),'defaulttempwoutdsrce')]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("defaulttempwoutdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 3 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(17, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 3 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(17, actual, Expected, e);
		}
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test (enabled = false) 
	public void Verify_Xlsx_Report_Format() throws InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP17 Checking & submit the xlsx report format for default template without datasource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
//		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
//		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
//		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
//		Fastprint.click();
//		WaitForLoadingIconDisappear();
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
//		Thread.sleep(2000);
//		WebElement Option = driver.findElement(By.xpath("//span[contains(text(),'apps')]"));
//		Option.click();
//		WebElement button_click = driver.findElement(By.xpath("//span[contains(text(),'New')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
//		button_click.click();
//		WaitForLoadingIconDisappear();
//		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
//		Print_Form_Name.sendKeys("defaultxlsx");
//		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
//		print_format.click();
//		driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]")).click();
//		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
//		Form_Tstruct.click();
//		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
//		search.sendKeys("Purchase Order");
//		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order-(purod)')]")).click();
//		WaitForLoadingIconDisappear();
//		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
//		Data_Source.click();
//		driver.findElement(By.xpath("//li[contains(text(),'Form data')]")).click();
//		WaitForLoadingIconDisappear();
//		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
//		Report_Output_Format.selectByVisibleText("XLSX");
//		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
//		Paper_Size.click();
//		driver.findElement(By.xpath("//li[contains(text(),'A4')]")).click();
//		Actions a = new Actions(driver);
//		a.sendKeys(Keys.PAGE_UP).build().perform();	
//		Thread.sleep(1000);
//		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
//		Title.click();
//		driver.findElement(By.xpath("(//a[@title='Source'])[1]")).click();
//		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
//		Center.click();
//		Center.sendKeys("<div style=\"text-align:center\">Purchase Order<br />\r\n"
//				+ "{Login username[username]}</div>");
//		driver.findElement(By.xpath("(//a[@title='Source'])[1]")).click();
//		WebElement Center_Alignment = driver.findElement(By.xpath("(//a[@title='Center'])[1]"));
//		Center_Alignment.click();
//		Center_Alignment.click();
//		Actions a1 = new Actions(driver);
//		a1.sendKeys(Keys.PAGE_UP).build().perform();	
//		Thread.sleep(1000);
//		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
//		Page_Header.click();
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("(//a[@title='Source'])[2]")).click();
//		WebElement Body = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
//		Body.click();
//		Body.sendKeys("PO NO. - {PO No.[pono]}<br />\r\n"
//				+ "PO Date - {Purchase Date[podate]}");
//		driver.findElement(By.xpath("(//a[@title='Source'])[2]")).click();
//		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
//		Generate_Fast_Report.click();
//		WaitForLoadingIconDisappear();
//		driver.switchTo().defaultContent();
//		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
//		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
		srch.click();
		srch.click();
		srch.sendKeys("Purchase Order");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'Purchase Order')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
		WebElement Purchase_Order = driver.findElement(By.xpath("//h1[contains(text(),'Purchase Order')]"));
	    new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Purchase_Order));
	    String attribute1 = Purchase_Order.getText();
	    System.out.println(attribute1);
	    String uname = username;
	    System.out.println(uname);
	    WebElement PO_NO1 = driver.findElement(By.xpath("//input[@id='pono000F1']"));
	    String attribute3 = PO_NO1.getAttribute("value");
	    System.out.println(attribute3);
	    WebElement Purchase_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
	    String attribute4 = Purchase_Date.getAttribute("value");
	    System.out.println(attribute4);
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Default xlsx')]")).click();
		WaitForLoadingIconDisappear();
		Thread.sleep(4000);
		
    }
	
	@Test (priority=4)
	public void Default_template_with_DataSource() throws InterruptedException, IOException, AWTException
	{
		ExtentTest test = extent.createTest("TC_FP21 Checking & submit the default template with datasource in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs1.get(0));
//		login();
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
		WaitForLoadingIconDisappear();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("defaulttempwithdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'defaulttemplate')]")).click();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order Form-(purch)");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("purchaseorders");
		driver.findElement(By.xpath("//li[contains(text(),'purchaseorders')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("A5");
		driver.findElement(By.xpath("//li[contains(text(),'A5')]")).click();
		Actions p = new Actions(driver);
		p.sendKeys(Keys.PAGE_DOWN).build().perform();	
		Thread.sleep(1000);
		WebElement Print_application_logo = driver.findElement(By.xpath("//input[@id='print_app_logo000F1']"));
		Print_application_logo.click();
		WebElement Print_application_title = driver.findElement(By.xpath("//input[@id='print_app_title000F1']"));
		Print_application_title.click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Center.click();
		Center.sendKeys("<div style=\"text-align:right\"><span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong><span style=\"font-size:22px\"><span style=\"color:#f39c12\">PURCHASE ORDER</span></span></strong></span></div>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		Thread.sleep(2000);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		WebElement Report_Title_Right_Image = driver.findElement(By.xpath("//span[@aria-labelledby='select2-rt_image_rightui000F2-container']"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Report_Title_Right_Image));
		Report_Title_Right_Image.click();
		driver.findElement(By.xpath("//li[contains(text(),'Company Logo-(img)')]")).click();
		WebElement Report_Title_Right_Image_Height = driver.findElement(By.xpath("//input[@id='rt_image_right_height000F2']"));
		Report_Title_Right_Image_Height.click();
		Report_Title_Right_Image_Height.sendKeys("130");
		WebElement Report_Title_Right_Image_Width = driver.findElement(By.xpath("//input[@id='rt_image_right_width000F2']"));
		Report_Title_Right_Image_Width.click();
		Report_Title_Right_Image_Width.sendKeys("70");
		Actions a1 = new Actions(driver);
		a1.sendKeys(Keys.PAGE_UP).build().perform();
		Thread.sleep(2000);
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_Header));
		Page_Header.click();
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		WebElement Body = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(Body));
		Body.click();
		Body.sendKeys("<div style=\"text-align:center\"><strong><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"color:#2980b9\"><span style=\"font-size:22px\">INVOICE</span></span></span></strong><br />\r\n"
				+ "&nbsp;</div>");
		Body.sendKeys("<div>\r\n"
				+ "<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">\r\n"
				+ "	<tbody>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"color:#c0392b\"><span style=\"font-family:Arial,Helvetica,sans-serif\"><span");
		Body.sendKeys("style=\"font-size:16px\"><strong>Supplier -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{supplier[supplier]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PO Date -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{podate[podate]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>Payment Date -&nbsp;&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_date[payment_date]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Address -&nbsp;&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{address[address]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PO No -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{pono[pono]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Payment Mode -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_mode[payment_mode]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>GSTIN -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{grossamt[grossamt]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:16px\"><span style=\"color:#c0392b\"><strong>PR No. -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{tprno[tprno]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Payment Terms -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{payment_terms[payment_terms]}</span></span></td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Contact -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{contact_no[contact_no]}</span></span></td>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"color:#c0392b\"><span style=\"font-size:16px\">Freight Charges -&nbsp;</span></span></strong></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong> </strong><span style=\"font-size:12px\">{freight_charges[freight_charges]}</span></span></td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><span style=\"font-size:16px\"><strong>Email -&nbsp;</strong></span></span></span><span style=\"font-family:Tahoma,Geneva,sans-serif\"><span style=\"font-size:12px\">{email[email]}</span></span></td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("<td>&nbsp;</td>");
		Body.sendKeys("</tr>");
		Body.sendKeys("</tbody>");
		Body.sendKeys("</table>");
		Body.sendKeys("</div>");
		Actions a2 = new Actions(driver);
		 for (int i = 0; i < 9; i++) {
	            // Scroll up by 1000 pixels
			 	a2.sendKeys(Keys.PAGE_UP).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement srce2b = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2b));
		srce2b.click();
		Thread.sleep(2000);
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		WebElement srce3a = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce3a));
		srce3a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, fr_body000F4']"));
		Body1.click();
		Body1.sendKeys("<div>");
		Body1.sendKeys("<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Body1.sendKeys("<tbody>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><strong>SHIP TO:</strong></span></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#c0392b\"><strong>BILL TO:</strong></span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{ship_to[ship_to]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{billto[billto]}</span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "	</tbody>\r\n"
				+ "</table>");
		Body1.sendKeys("&nbsp");
		Body1.sendKeys("&nbsp");
		Body1.sendKeys("<span style=\"font-family:Tahoma,Geneva,sans-serif\"><strong>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</strong></span></div>");
		Body1.sendKeys("<br />");
		Body1.sendKeys("<table align=\"center\" border=\"2\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Body1.sendKeys("<tbody>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">S.no</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Item Category</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Item Name</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Quantity</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Uni&nbsp;Price</span></span></strong></span></td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Total Amount</span></span></strong></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">1</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{prdcode[prdcode]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{prodname[prodname]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{quantity[quantity]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{uprice[uprice]}</span></span></td>");
		Body1.sendKeys("<td><span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">{totalamount[totalamount]}</span></span></td>");
		Body1.sendKeys("</tr>\r\n"
				+ "		<tr>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong><span style=\"font-size:16px\"><span style=\"color:#c0392b\">Total</span></span></strong></span></td>");
		Body1.sendKeys("<td colspan=\"4\" rowspan=\"1\">&nbsp;</td>");
		Body1.sendKeys("<td><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"font-size:14px\">{totpuamt[totpuamt]}</span></span></td>");
		Body1.sendKeys("</tr>");
		Body1.sendKeys("</tbody>");
		Body1.sendKeys("</table>");
		Body1.sendKeys("&nbsp");
		Actions a3 = new Actions(driver);
		 for (int i = 0; i < 9; i++) {
	            // Scroll up by 1000 pixels
			 	a3.sendKeys(Keys.PAGE_UP).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement srce3b = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce3b));
		srce3b.click();
		Thread.sleep(2000);
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body2 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body2.click();
		Body2.sendKeys("<div style=\"text-align:center\"><span style=\"font-family:Tahoma,Geneva,sans-serif\">43, Kambar Street, Chennai - 600543, Website :&nbsp;<span style=\"color:#c0392b\"><u>www.sm-purchase.com&nbsp; </u></span><span style=\"color:#000000\">Email:</span><span style=\"color:#c0392b\"><u> purchase-sm@gmail.com</u></span></span></div>");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Default Temp Withdtasrce ')]")).click();
		WaitForLoadingIconDisappear();
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
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_defaulttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot2 = screenshot("TC_FP21 Checking & submit the default template with datasource in Print Form Name.png");
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
			test.pass("Test Case scenario 2 Passed");
			test.addScreenCaptureFromPath(Screenshot2);
			excelpass(21, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 2 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot2);
			excelfail(21, actual, Expected, e);
		}
	}
	
	@Test (priority=5)
	public void Delete_Default_template_with_DataSource() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP22 Checking & deleting the Default template with DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("(//a[contains(text(),'defaulttempwithdsrce')])[1]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("defaulttempwithdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 3 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(22, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 3 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(22, actual, Expected, e);
		}
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test(priority=6)
	public void Verify_Letter_Tempelate() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP26 Checking & verifying whether the letter template is listing or not in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
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
		WaitForLoadingIconDisappear();
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		String Screenshot4 = screenshot("TC_FP26 Checking & verifying whether the letter template is listing or not in Print Form Name.Png");
		WebElement Template = driver.findElement(By.xpath("//li[contains(text(),'Letter format')]"));
		String actual = Template.getText();
		System.out.println(actual);
		String Expected = "Letter format";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 4 Passed");
			test.addScreenCaptureFromPath(Screenshot4);
			excelpass(26, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 4 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot4);
			excelfail(26, actual, Expected, e);
		}
	}
	
	@Test (priority=7)
	public void Letter_template_without_DataSource() throws InterruptedException, IOException, AWTException
	{
		ExtentTest test = extent.createTest("TC_FP27 Checking & submit the letter template without datasource in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
//		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
//		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
//		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
//		Fastprint.click();
//		WaitForLoadingIconDisappear();
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
//		Thread.sleep(2000);
//		WebElement Option = driver.findElement(By.xpath("//span[contains(text(),'apps')]"));
//		Option.click();
//		WebElement button_click = driver.findElement(By.xpath("//span[contains(text(),'New')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
//		button_click.click();
//		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("lttrtempwoutdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'Letter format')]")).click();
		Thread.sleep(2000);
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order Form-(purch)");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("Form data");
		driver.findElement(By.xpath("//li[contains(text(),'Form data')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("Letter 2");
		driver.findElement(By.xpath("//li[contains(text(),'Letter 2')]")).click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce1a = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1a));
		srce1a.click();
		WebElement Centera = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Centera.click();
		Centera.sendKeys("<div style=\"text-align:center\"><strong><span style=\"color:#e74c3c\">&quot;Purchase Order Letter&quot;</span></strong></div>");
		WebElement srce1b = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1b));
		srce1b.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		Page_Header.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		Center.click();
		Center.sendKeys("<div><strong>To</strong><br />\r\n"
				+ "{Address[address]}&nbsp;<br />\r\n"
				+ "<br />\r\n"
				+ "<strong>Supplier Name - </strong>{supplier[supplier]}<br />\r\n"
				+ "<br />\r\n"
				+ "<strong>Place - </strong>Chennai<br />\r\n"
				+ "<br />\r\n"
				+ "<strong><span style=\"color:#e74c3c\">Dear Sir/Madam</span></strong></div>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		WebElement Body = driver.findElement(By.xpath("//textarea[@aria-label='Editor, fr_body000F4']"));
		Body.click();
		Body.sendKeys("<strong><span style=\"color:#e74c3c\">Sub</span><span style=\"color:#c0392b\"> - </span><span style=\"color:#e74c3c\">Purchase Order &quot;{supplier[supplier]}&quot;</span></strong><br />\r\n"
				+ "<br />\r\n"
				+ "<span style=\"font-size:16px\"><span style=\"font-family:Times New Roman,Times,serif\">As per our EMail confirmation /Telephonic discussion with <span style=\"color:#e74c3c\">{Login username[username]}</span>&nbsp;we are very happy to place an order for <span style=\"color:#e74c3c\">{supplier[supplier]}</span>&nbsp;as per the following details and specifications:<br />\r\n"
				+ "<br />\r\n"
				+ "Mention (Freight Charges -&nbsp;<span style=\"color:#e74c3c\">{Freight Charges[freight_charges]}</span>), (Gst -&nbsp;<span style=\"color:#e74c3c\">{GSTIN[gst]}</span>), (Gross Amount -&nbsp;<span style=\"color:#e74c3c\">{Gross Amount[grossamt]}</span>), Any other specifications, and the Business Terms. Also mention the Payment Date -&nbsp;<span style=\"color:#e74c3c\">{Payment Date[payment_date]}</span>, Toral Purchase Amount -&nbsp;<span style=\"color:#e74c3c\">{Total Purchase Amount[totpuamt]}</span>, Contact No -&nbsp;<span style=\"color:#e74c3c\">{Contact No[contact_no]}</span> and Bill To Address -&nbsp;<span style=\"color:#e74c3c\">{Bill To[billto]}</span>.<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "Thanking You<br />\r\n"
				+ "<br />\r\n"
				+ "Yours Faithfully<br />\r\n"
				+ "<span style=\"color:#e74c3c\">{Login username[username]}</span><br />\r\n"
				+ "Authorized Signatory<br />\r\n"
				+ "<span style=\"color:#e74c3c\">Srijesh</span><br />\r\n"
				+ "Company name and seal<br />\r\n"
				+ "<span style=\"color:#e74c3c\">{supplier[supplier]}</span></span></span>");
		WebElement srce2b = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2b));
		srce2b.click();
		Thread.sleep(2000);
		a.sendKeys(Keys.PAGE_UP).build().perform();
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body1.click();
		Body1.sendKeys("123 Main Street, Cityville, State, <span style=\"color:#e74c3c\"><strong>ZIP Phone:</strong></span> (123) 456-7890 | <span style=\"color:#e74c3c\"><strong>Email:</strong></span> info@company.com | <span style=\"color:#e74c3c\"><strong>Website:</strong></span> www.company.com");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
		WebElement Supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
		String attribute = Supplier.getText();
		System.out.println(attribute);
		WebElement Gross_Amt = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
		String attribute1 = Gross_Amt.getAttribute("value");
		System.out.println(attribute1);
		WebElement Freight_chrgs = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
		String attribute2 = Freight_chrgs.getAttribute("value");
		System.out.println(attribute2);
		Actions a4 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
		address.click();
		String attribute3 = address.getText();
		System.out.println(attribute3);
		Actions a5 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a5.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Gst = driver.findElement(By.xpath("//input[@id='gst000F1']"));
		String attribute4 = Gst.getAttribute("value");
		System.out.println(attribute4);
		WebElement Ctno = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
		String attribute5 = Ctno.getAttribute("value");
		System.out.println(attribute5);
		WebElement Payment_Dte = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
		String attribute6 = Payment_Dte.getAttribute("value");
		System.out.println(attribute6);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Billto = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
		Billto.click();
		String attribute7 = Billto.getText();
		System.out.println(attribute7);
		WebElement totpuamt = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
		String attribute8 = totpuamt.getAttribute("value");
		System.out.println(attribute8);
		String uname = username;
		System.out.println(uname);
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Letter Template Without Data Source')]")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs3.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_lttrtempwoutdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot5 = screenshot("TC_FP27 Checking & submit the letter template without datasource in Print Form Name.png"); 
	    String Expected ="Purchase Order Letter\"\r\n"
	    		+ "To\r\n"
	    		+ ""+attribute3+" \r\n"
	    		+ "Supplier Name - "+attribute+"\r\n"
	    		+ "Place - Chennai\r\n"
	    		+ "Dear Sir/Madam\r\n"
	    		+ "Sub - Purchase Order "+attribute+"\r\n"
	    		+ "As per our EMail confirmation /Telephonic discussion with admin we are very happy to place an order for\r\n"
	    		+ ""+attribute+" as per the following details and specifications:\r\n"
	    		+ "Mention (Freight Charges - "+attribute2+"), (Gst - "+attribute4+"), (Gross Amount - "+attribute1+"), Any other specifications, and the Business Terms. Also mention the Payment Date - "+attribute6+", Toral Purchase Amount - "+attribute8+", Contact No - "+attribute5+" and Bill To Address - "+attribute7+".\r\n"
	    		+ "Thanking You\r\n"
	    		+ "Yours Faithfully\r\n"
	    		+ ""+username+"\r\n"
	    		+ "Authorized Signatory\r\n"
	    		+ "Srijesh\r\n"
	    		+ "Company name and seal\r\n"
	    		+ ""+attribute+"\r\n"
	    		+ "123 Main Street, Cityville, State, ZIP Phone: (123) 456-7890 | Email: info@company.com | Website:\r\n"
	    		+ "www.company.com";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 5 Passed");
			test.addScreenCaptureFromPath(Screenshot5);
			excelpass(27, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 5 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot5);
			excelfail(27, actual, Expected, e);
		}
	}
	   
	@Test (priority=8)
	public void Delete_Letter_template_without_DataSource() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP28 Checking & deleting the Letter template without DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("//a[contains(text(),'lttrtempwoutdsrce')]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("lttrtempwoutdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 6 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(28, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 6 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(28, actual, Expected, e);
		}
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test (priority=9)
	public void Letter_template_with_DataSource() throws InterruptedException, IOException, AWTException
	{
		ExtentTest test = extent.createTest("TC_FP28 Checking & submit the letter template with datasource in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs1.get(0));
//		login();
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
		WaitForLoadingIconDisappear();
//		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("lttrtempwithdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'Letter format')]")).click();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order Form-(purch)");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("purchaseorders");
		driver.findElement(By.xpath("//li[contains(text(),'purchaseorders')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("Letter 2");
		driver.findElement(By.xpath("//li[contains(text(),'Letter 2')]")).click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce1a = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1a));
		srce1a.click();
		WebElement Centera = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Centera.click();
		Centera.sendKeys("<div style=\"text-align:center\"><strong><span style=\"color:#e74c3c\">&quot;Purchase Order Letter&quot;</span></strong></div>");
		WebElement srce1b = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1b));
		srce1b.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		Page_Header.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		Center.click();
		Center.sendKeys("<div><strong>To</strong><br />\r\n"
				+ "{Address[address]}&nbsp;<br />\r\n"
				+ "<br />\r\n"
				+ "<strong>Supplier Name - </strong>{supplier[supplier]}<br />\r\n"
				+ "<br />\r\n"
				+ "<strong>Place - </strong>Chennai<br />\r\n"
				+ "<br />\r\n"
				+ "<strong><span style=\"color:#e74c3c\">Dear Sir/Madam</span></strong></div>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		WebElement Body = driver.findElement(By.xpath("//textarea[@aria-label='Editor, fr_body000F4']"));
		Body.click();
		Body.sendKeys("<strong><span style=\"color:#e74c3c\">Sub</span><span style=\"color:#c0392b\"> - </span><span style=\"color:#e74c3c\">Purchase Order &quot;{supplier[supplier]}&quot;</span></strong><br />\r\n"
				+ "<br />\r\n"
				+ "<span style=\"font-size:16px\"><span style=\"font-family:Times New Roman,Times,serif\">As per our EMail confirmation /Telephonic discussion with <span style=\"color:#e74c3c\">{Login username[username]}</span>&nbsp;we are very happy to place an order for <span style=\"color:#e74c3c\">{supplier[supplier]}</span>&nbsp;as per the following details and specifications:<br />\r\n"
				+ "<br />\r\n"
				+ "Mention (Freight Charges -&nbsp;<span style=\"color:#e74c3c\">{Freight Charges[freight_charges]}</span>), (Quantity -&nbsp;</span></span><span style=\"color:#e74c3c\">{quantity[quantity]}</span><span style=\"font-size:16px\"><span style=\"font-family:Times New Roman,Times,serif\">), (Gst -&nbsp;<span style=\"color:#e74c3c\">{GSTIN[gst]}</span>), (Gross Amount -&nbsp;<span style=\"color:#e74c3c\">{Gross Amount[grossamt]}</span>), Any other specifications, and the Business Terms. Also mention the Payment Date -&nbsp;<span style=\"color:#e74c3c\">{Payment Date[payment_date]}</span>, Toral Purchase Amount -&nbsp;<span style=\"color:#e74c3c\">{Total Purchase Amount[totpuamt]}</span>, Contact No -&nbsp;<span style=\"color:#e74c3c\">{Contact No[contact_no]}</span> and Bill To Address -&nbsp;<span style=\"color:#e74c3c\">{Bill To[billto]}</span>.<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "<br />\r\n"
				+ "Thanking You<br />\r\n"
				+ "<br />\r\n"
				+ "Yours Faithfully<br />\r\n"
				+ "<span style=\"color:#e74c3c\">{Login username[username]}</span><br />\r\n"
				+ "Authorized Signatory<br />\r\n"
				+ "<span style=\"color:#e74c3c\">Srijesh</span><br />\r\n"
				+ "Company name and seal<br />\r\n"
				+ "<span style=\"color:#e74c3c\">{supplier[supplier]}</span></span></span>");
		WebElement srce2b = driver.findElement(By.xpath("(//a[@title='Source'])[3]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2b));
		srce2b.click();
		Thread.sleep(2000);
		a.sendKeys(Keys.PAGE_UP).build().perform();
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body1.click();
		Body1.sendKeys("123 Main Street, Cityville, State, <span style=\"color:#e74c3c\"><strong>ZIP Phone:</strong></span> (123) 456-7890 | <span style=\"color:#e74c3c\"><strong>Email:</strong></span> info@company.com | <span style=\"color:#e74c3c\"><strong>Website:</strong></span> www.company.com");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement PO_NO = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		PO_NO.click();
		WaitForLoadingIconDisappear();
		WebElement Supplier = driver.findElement(By.xpath("//span[@id='select2-supplier000F1-container']"));
		String attribute = Supplier.getText();
		System.out.println(attribute);
		WebElement Gross_Amt = driver.findElement(By.xpath("//input[@id='grossamt000F1']"));
		String attribute1 = Gross_Amt.getAttribute("value");
		System.out.println(attribute1);
		WebElement Freight_chrgs = driver.findElement(By.xpath("//input[@id='freight_charges000F1']"));
		String attribute2 = Freight_chrgs.getAttribute("value");
		System.out.println(attribute2);
		Actions a4 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement address = driver.findElement(By.xpath("//textarea[@id='address000F1']"));
		address.click();
		String attribute3 = address.getText();
		System.out.println(attribute3);
		Actions a5 = new Actions(driver);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a5.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Gst = driver.findElement(By.xpath("//input[@id='gst000F1']"));
		String attribute4 = Gst.getAttribute("value");
		System.out.println(attribute4);
		WebElement Ctno = driver.findElement(By.xpath("//input[@id='contact_no000F1']"));
		String attribute5 = Ctno.getAttribute("value");
		System.out.println(attribute5);
		WebElement Payment_Dte = driver.findElement(By.xpath("//input[@id='payment_date000F1']"));
		String attribute6 = Payment_Dte.getAttribute("value");
		System.out.println(attribute6);
		 for (int i = 0; i < 1; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Billto = driver.findElement(By.xpath("//textarea[@id='billto000F1']"));
		Billto.click();
		String attribute7 = Billto.getText();
		System.out.println(attribute7);
		 for (int i = 0; i < 2; i++) {
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
		WebElement Quantity = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
		String attribute8 = Quantity.getAttribute("value");
		System.out.println(attribute8);
		WebElement totpuamt = driver.findElement(By.xpath("//input[@id='totpuamt000F3']"));
		String attribute9 = totpuamt.getAttribute("value");
		System.out.println(attribute9);
		String uname = username;
		System.out.println(uname);
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Letter Template Without Data Source')]")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs3.get(1));
	    driver.manage().window().maximize();
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(4000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_lttrtempwoutdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot6 = screenshot("TC_FP27 Checking & submit the letter template without datasource in Print Form Name.png"); 
	    String Expected ="Purchase Order Letter\"\r\n"
	    		+ "To\r\n"
	    		+ ""+attribute3+" \r\n"
	    		+ "Supplier Name - "+attribute+"\r\n"
	    		+ "Place - Chennai\r\n"
	    		+ "Dear Sir/Madam\r\n"
	    		+ "Sub - Purchase Order "+attribute+"\r\n"
	    		+ "As per our EMail confirmation /Telephonic discussion with admin we are very happy to place an order for\r\n"
	    		+ ""+attribute+" as per the following details and specifications:\r\n"
	    		+ "Mention (Freight Charges - "+attribute2+"), (Quantity -"+attribute8+"), (Gst - "+attribute4+"), (Gross Amount - "+attribute1+"), Any other specifications, and the Business Terms. Also mention the Payment Date - "+attribute6+", Toral Purchase Amount - "+attribute8+", Contact No - "+attribute5+" and Bill To Address - "+attribute7+".\r\n"
	    		+ "Thanking You\r\n"
	    		+ "Yours Faithfully\r\n"
	    		+ ""+username+"\r\n"
	    		+ "Authorized Signatory\r\n"
	    		+ "Srijesh\r\n"
	    		+ "Company name and seal\r\n"
	    		+ ""+attribute+"\r\n"
	    		+ "123 Main Street, Cityville, State, ZIP Phone: (123) 456-7890 | Email: info@company.com | Website:\r\n"
	    		+ "www.company.com";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 6 Passed");
			test.addScreenCaptureFromPath(Screenshot6);
			excelpass(32, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 6 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot6);
			excelfail(32, actual, Expected, e);
		}
	}
	
	@Test (priority=10)
	public void Delete_Letter_template_with_DataSource() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP22 Checking & deleting the Letter template with DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("//a[contains(text(),'lttrtempwithdsrce')]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("defaulttempwithdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 8 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(34, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 8 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(34, actual, Expected, e);
		}
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test(priority=11)
	public void Verify_Report_Tempelate() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP37 Checking & verifying whether the report template is listing or not in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs1.get(0));
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
		WaitForLoadingIconDisappear();
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		String Screenshot7 = screenshot("TC_FP37 Checking & verifying whether the report template is listing or not in Print Form Name.Png");
		WebElement Template = driver.findElement(By.xpath("//li[contains(text(),'Report format')]"));
		String actual = Template.getText();
		System.out.println(actual);
		String Expected = "Report format";
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 7 Passed");
			test.addScreenCaptureFromPath(Screenshot7);
			excelpass(37, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 7 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot7);
			excelfail(37, actual, Expected, e);
		}
	}
	
	@Test (priority=12)
	public void Report_template_without_DataSource() throws InterruptedException, IOException, AWTException, SQLException
	{
		ExtentTest test = extent.createTest("TC_FP38 Checking & submit the report template without datasource in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
//		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
//		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
//		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
//		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
//		Fastprint.click();
//		WaitForLoadingIconDisappear();
//		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
//		Thread.sleep(2000);
//		WebElement Option = driver.findElement(By.xpath("//span[contains(text(),'apps')]"));
//		Option.click();
//		WebElement button_click = driver.findElement(By.xpath("//span[contains(text(),'New')]"));
//		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
//		button_click.click();
//		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("rprttempwoutdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'Report format')]")).click();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order Form-(purch)");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("Form data");
		driver.findElement(By.xpath("//li[contains(text(),'Form data')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("Letter 2");
		driver.findElement(By.xpath("//li[contains(text(),'Letter 2')]")).click();
		WebElement Print_application_logo = driver.findElement(By.xpath("//input[@id='print_app_logo000F1']"));
		Print_application_logo.click();
		WebElement Print_application_title = driver.findElement(By.xpath("//input[@id='print_app_title000F1']"));
		Print_application_title.click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Center.click();
		Center.sendKeys("<h2 style=\"font-style:italic; text-align:center\"><u><span style=\"color:#e67e22\"><strong>Purchase Order</strong></span></u></h2>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(2000);
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		Page_Header.click();
		WebElement srce2 = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2));
		srce2.click();
		WebElement Editor = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Editor));
		Editor.click();
		Editor.sendKeys("<div style=\"text-align:center\">&nbsp;</div>");
		Editor.sendKeys("<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Editor.sendKeys("<tbody>");
		Editor.sendKeys("<tr>");
		Editor.sendKeys("<td>");
		Editor.sendKeys("<p>Address : The Venture Studio,<br />");
		Editor.sendKeys("L-176, 3rd Floor, 5th Main, Sector - 6,<br />");
		Editor.sendKeys("HSR Layout, Bangalore &ndash; 560102</p>");
		Editor.sendKeys("<div>E-MAIL: {Email[email]}</div>");
		Editor.sendKeys("</td>");
		Editor.sendKeys("<td>PR No: {PR No.[tprno]}<br />");
		Editor.sendKeys("POR Date : {Purchase Date[podate]}<br />");
		Editor.sendKeys("Payment Mode&nbsp;: {Payment Mode[payment_mode]}</td>");
		Editor.sendKeys("</tr>");
		Editor.sendKeys("</tbody>");
		Editor.sendKeys("</table>");
		Editor.sendKeys("<br />");
		Editor.sendKeys("&nbsp;");
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-detail_data_source000F4-container']")).click();
		driver.findElement(By.xpath("//li[contains(text(),'Item Details-(dc2)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Detail_columns = driver.findElement(By.xpath("//input[@id='tbl_detail_band000F4']"));
		Detail_columns.click();
		Detail_columns.sendKeys("prdcode|Product Code|||Centre||||Bold|~prodname|Product Name|||Centre||||Bold|~quantity|Quantity|||Centre||||Bold|~uprice|Unit Price|||Centre||||Bold|~totalamount|Total Amount|||Centre||||Bold|");
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body1.click();
		Body1.sendKeys("<div style=\"text-align:center\"><span style=\"font-family:Arial,Helvetica,sans-serif\">Copyright 2001-2023 By Agile Labs Pvt Ltd. All Rights Reserved.SiteMap<br />\r\n"
				+ "<span style=\"color:#3498db\">Agile Labs Powered By Axpert.</span></span></div>");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement Employee_Code = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		Employee_Code.click();
		WaitForLoadingIconDisappear();
		WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
		String attribute1 = PR_No.getText();
		System.out.println(attribute1);
		WebElement PR_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
		String attribute2 = PR_Date.getAttribute("value");
		System.out.println(attribute2);
		WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
		String attribute3 = Payment_Mode.getAttribute("value");
		System.out.println(attribute3);
		WebElement Mail = driver.findElement(By.xpath("//input[@id='email000F1']"));
		String attribute4 = Mail.getAttribute("value");
		System.out.println(attribute4);
		WebElement Summary_Logo = driver.findElement(By.xpath("//img[@id='img000F1']"));
		String attribute5 = Summary_Logo.getAttribute("value");
		System.out.println(attribute5);
		 Actions a4 = new Actions(driver);
		 for (int i = 0; i < 4; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
//		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
//        Statement statement = connection.createStatement();
//        ResultSet res= statement.executeQuery(query2);
//		while (res.next())
//		{
//			String itemcat = res.getString("itemcat");	
//			String itemname = res.getString("itemname");	
//			String quantity = res.getString("quantity");	
////			System.out.println(itemcat + " " + itemname + " " + quantity);
//		String Item_details = itemcat + " " + itemname + " " + quantity;
//		System.out.println(Item_details);
		WebElement item_category = driver.findElement(By.xpath("//th[@id='th-prdcode']"));
		String cat = item_category.getText();
		WebElement Item_Name = driver.findElement(By.xpath("//th[@id='th-prodname']"));
		String Itname = Item_Name.getText();
		WebElement Quantity = driver.findElement(By.xpath("//th[@id='th-quantity']"));
		String Qty = Quantity.getText();
		WebElement Unit_Price1 = driver.findElement(By.xpath("//th[@id='th-uprice']"));
		String Uprice = Unit_Price1.getText();
		WebElement Total_Amount = driver.findElement(By.xpath("//th[@id='th-totalamount']"));
		String ttlamount = Total_Amount.getText();
		String Item_dtils = cat + " " + Itname + " " + Qty + " " + Uprice + " " + ttlamount;
		driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
		WebElement Item_cat1 = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
		String icat1 = Item_cat1.getText();
		WebElement Item_Name1 = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
		String inam1 = Item_Name1.getText();
		WebElement quantity1 = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
		String qty1 = quantity1.getAttribute("value");
		WebElement Unit_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
		String Uprce = Unit_Price.getAttribute("value");
		WebElement Total_Amt = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
		String ttlamt = Total_Amt.getAttribute("value");
//		driver.findElement(By.xpath("//label[@id='lblSlNo002F2']")).click();
//		WebElement Item_cat2 = driver.findElement(By.xpath("//span[@id='select2-itemcat002F2-container']"));
//		String icat2 = Item_cat2.getText();
//		WebElement Item_Name2 = driver.findElement(By.xpath("//span[@id='select2-itemname002F2-container']"));
//		String inam2 = Item_Name2.getText();
//		WebElement quantity2 = driver.findElement(By.xpath("//input[@id='quantity002F2']"));
//		String qty2 = quantity2.getAttribute("value");
//		driver.findElement(By.xpath("//label[@id='lblSlNo003F2']")).click();
//		WebElement Item_cat3 = driver.findElement(By.xpath("//span[@id='select2-itemcat003F2-container']"));
//		String icat3 = Item_cat3.getText();
//		WebElement Item_Name3 = driver.findElement(By.xpath("//span[@id='select2-itemname003F2-container']"));
//		String inam3 = Item_Name3.getText();
//		WebElement quantity3 = driver.findElement(By.xpath("//input[@id='quantity003F2']"));
//		String qty3 = quantity3.getAttribute("value");
//		driver.findElement(By.xpath("//label[@id='lblSlNo004F2']")).click();
//		WebElement Item_cat4 = driver.findElement(By.xpath("//span[@id='select2-itemcat004F2-container']"));
//		String icat4 = Item_cat4.getText();
//		WebElement Item_Name4 = driver.findElement(By.xpath("//span[@id='select2-itemname004F2-container']"));
//		String inam4 = Item_Name4.getText();
//		WebElement quantity4 = driver.findElement(By.xpath("//input[@id='quantity004F2']"));
//		String qty4 = quantity4.getAttribute("value");
//		driver.findElement(By.xpath("//label[@id='lblSlNo005F2']")).click();
//		WebElement Item_cat5 = driver.findElement(By.xpath("//span[@id='select2-itemcat005F2-container']"));
//		String icat5 = Item_cat5.getText();
//		WebElement Item_Name5 = driver.findElement(By.xpath("//span[@id='select2-itemname005F2-container']"));
//		String inam5 = Item_Name5.getText();
//		WebElement quantity5 = driver.findElement(By.xpath("//input[@id='quantity005F2']"));
//		String qty5 = quantity5.getAttribute("value");
//		String Item_Details = 
//				           icat1 + " " + inam1 + " " + qty1 
//				+ "\r\n" + icat2 + " " + inam2 + " " + qty2 
//				+ "\r\n" + icat3 + " " + inam3 + " " + qty3 
//				+ "\r\n" + icat4 + " " + inam4 + " " + qty4 
//				+ "\r\n" + icat5 + " " + inam5 + " " + qty5;
		String Item_Details = icat1 + " " + inam1 + " " + qty1 + " " + Uprce + " " + ttlamt;
		System.out.println(Item_Details);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Report Template Without Data Source')]")).click();
		WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs3.get(1));
	    driver.manage().window().maximize();
	    Actions a5 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a5.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_rprttempwoutdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot8 = screenshot("TC_FP38 Checking & submit the report template without datasource in Print Form Name.png"); 
	    String Expected = "AGILE LABS PVT LTD\r\n"
	    		+ "Purchase Order\r\n"
	    		+ "\r\n"
	    		+ "Address : The Venture Studio,\r\n"
	    		+ "L-176, 3rd Floor, 5th Main, Sector - 6,\r\n"
	    		+ "HSR Layout, Bangalore – 560102\r\n"
	    		+ "E-MAIL: "+attribute4+" \r\n"
	    		+ "PR No: "+attribute1+" \r\n"
	    		+ "POR Date : "+attribute2+" \r\n"
	    		+ "Payment Mode : "+attribute3+" \r\n"
	    		+ "\r\n"
	    		+ ""+Item_dtils+" \r\n"
	    		+ ""+Item_Details+""
	    		+ "\r\n"
	    		+ "Copyright 2001-2023 By Agile Labs Pvt Ltd. All Rights Reserved.SiteMap\r\n"
	    		+ "Agile Labs Powered By Axpert.";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 8 Passed");
			test.addScreenCaptureFromPath(Screenshot8);
			excelpass(38, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 8 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot8);
			excelfail(38, actual, Expected, e);
		}
		}
	
	@Test (priority=13)
	public void Delete_Report_template_without_DataSource() throws IOException, InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP16 Checking & deleting the Report template without DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("//a[contains(text(),'rprttempwoutdsrce')]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("rprttempwoutdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 10 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(40, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 10 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(40, actual, Expected, e);
		}
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test (priority=14)
	public void Report_template_with_DataSource() throws InterruptedException, IOException, AWTException
	{
		ExtentTest test = extent.createTest("TC_FP39 Checking & submit the report template with datasource in Print Form Name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
//		driver.switchTo().window(tabs1.get(0));
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
		WaitForLoadingIconDisappear();
//		driver.findElement(By.xpath("//a[@id='ftbtn_iNew']")).click();
		WebElement Print_Form_Name = driver.findElement(By.xpath("//input[@id='template_name000F1']"));
		Print_Form_Name.sendKeys("rprttempwithdsrce");
		WebElement print_format = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_templateui000F1-container']"));
		print_format.click();
		driver.findElement(By.xpath("//li[contains(text(),'Report format')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Form_Tstruct = driver.findElement(By.xpath("//span[@aria-labelledby='select2-form_caption000F1-container']"));
		Form_Tstruct.click();
		WebElement search = driver.findElement(By.xpath("//input[@type='search']"));
		search.sendKeys("Purchase Order Form-(purch)");
		driver.findElement(By.xpath("//li[contains(text(),'Purchase Order Form-(purch)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Data_Source = driver.findElement(By.xpath("//span[@aria-labelledby='select2-master_data_source000F1-container']"));
		Data_Source.click();
		WebElement search1 = driver.findElement(By.xpath("//input[@type='search']"));
		search1.sendKeys("purchaseorders");
		driver.findElement(By.xpath("//li[contains(text(),'purchaseorders')]")).click();
		WaitForLoadingIconDisappear();
		Select Report_Output_Format = new Select(driver.findElement(By.id("report_outputformat000F1")));
		Report_Output_Format.selectByVisibleText("PDF");
		WebElement Paper_Size = driver.findElement(By.xpath("//span[@aria-labelledby='select2-paper_size000F1-container']"));
		Paper_Size.click();
		WebElement search2 = driver.findElement(By.xpath("//input[@type='search']"));
		search2.sendKeys("Letter 2");
		driver.findElement(By.xpath("//li[contains(text(),'Letter 2')]")).click();
		WebElement Print_application_logo = driver.findElement(By.xpath("//input[@id='print_app_logo000F1']"));
		Print_application_logo.click();
		WebElement Print_application_title = driver.findElement(By.xpath("//input[@id='print_app_title000F1']"));
		Print_application_title.click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		Thread.sleep(1000);
		WebElement Title = driver.findElement(By.xpath("//a[contains(text(),'Title')]"));
		Title.click();
		WebElement srce = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce));
		srce.click();
		WebElement Center = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_title000F2']"));
		Center.click();
		Center.sendKeys("<h2 style=\"font-style:italic; text-align:center\"><u><span style=\"color:#e67e22\"><strong>Purchase Order</strong></span></u></h2>");
		WebElement srce1 = driver.findElement(By.xpath("(//a[@title='Source'])[1]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce1));
		srce1.click();
		Actions a0 = new Actions(driver);
		 for (int i = 0; i < 4; i++) {
	            // Scroll up by 1000 pixels
			 	a0.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		WebElement Report_Title_Right_Image = driver.findElement(By.xpath("//span[@aria-labelledby='select2-rt_image_rightui000F2-container']"));
		Report_Title_Right_Image.click();
		driver.findElement(By.xpath("//li[contains(text(),'Company Logo-(img)')]")).click();
		WebElement Height = driver.findElement(By.xpath("//input[@id='rt_image_right_height000F2']"));
		Height.click();
		Height.sendKeys("130");
		WebElement Width = driver.findElement(By.xpath("//input[@id='rt_image_right_width000F2']"));
		Width.click();
		Width.sendKeys("70");
		a.sendKeys(Keys.PAGE_UP).build().perform();	
		WebElement Page_Header = driver.findElement(By.xpath("//a[contains(text(),'Page header')]"));
		Page_Header.click();
		WebElement srce2 = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2));
		srce2.click();
		WebElement Editor = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_header000F3']"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Editor));
		Editor.click();
		Editor.sendKeys("<div style=\"text-align:center\">&nbsp;</div>");
		Editor.sendKeys("<table align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:700px\">");
		Editor.sendKeys("<tbody>");
		Editor.sendKeys("<tr>");
		Editor.sendKeys("<td>");
		Editor.sendKeys("<p>Address : The Venture Studio,<br />");
		Editor.sendKeys("L-176, 3rd Floor, 5th Main, Sector - 6,<br />");
		Editor.sendKeys("HSR Layout, Bangalore &ndash; 560102</p>");
		Editor.sendKeys("<div>E-MAIL: {Email[email]}</div>");
		Editor.sendKeys("</td>");
		Editor.sendKeys("<td>PR No: {PR No.[tprno]}<br />");
		Editor.sendKeys("POR Date : {Purchase Date[podate]}<br />");
		Editor.sendKeys("Payment Mode&nbsp;: {Payment Mode[payment_mode]}</td>");
		Editor.sendKeys("</tr>");
		Editor.sendKeys("</tbody>");
		Editor.sendKeys("</table>");
		Editor.sendKeys("<br />");
		Editor.sendKeys("&nbsp;");
		WebElement srce2a = driver.findElement(By.xpath("(//a[@title='Source'])[2]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce2a));
		srce2a.click();
		a.sendKeys(Keys.PAGE_UP).build().perform();
		WebElement Body_Detail = driver.findElement(By.xpath("//a[contains(text(),'Body/Detail')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Body_Detail));
		Body_Detail.click();
		driver.findElement(By.xpath("//span[@aria-labelledby='select2-detail_data_source000F4-container']")).click();
		driver.findElement(By.xpath("//li[contains(text(),'Item Details-(dc2)')]")).click();
		WaitForLoadingIconDisappear();
		WebElement Detail_columns = driver.findElement(By.xpath("//input[@id='tbl_detail_band000F4']"));
		Detail_columns.click();
		Detail_columns.sendKeys("prdcode|Product Code|||Centre||||Bold|~prodname|Product Name|||Centre||||Bold|~quantity|Quantity|||Centre||||Bold|~uprice|Unit Price|||Centre||||Bold|~totalamount|Total Amount|||Centre||||Bold|");
		WebElement Page_footer = driver.findElement(By.xpath("//a[contains(text(),'Page footer')]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(Page_footer));
		Page_footer.click();
		WebElement srce4a = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4a));
		srce4a.click();
		WebElement Body1 = driver.findElement(By.xpath("//textarea[@aria-label='Editor, page_footer000F5']"));
		Body1.click();
		Body1.sendKeys("<div style=\"text-align:center\"><span style=\"font-family:Arial,Helvetica,sans-serif\">Copyright 2001-2023 By Agile Labs Pvt Ltd. All Rights Reserved.SiteMap<br />\r\n"
				+ "<span style=\"color:#3498db\">Agile Labs Powered By Axpert.</span></span></div>");
		WebElement srce4b = driver.findElement(By.xpath("(//a[@title='Source'])[4]"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(srce4b));
		srce4b.click();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement Employee_Code = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		Employee_Code.click();
		WaitForLoadingIconDisappear();
		WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
		String attribute1 = PR_No.getText();
		System.out.println(attribute1);
		WebElement PR_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
		String attribute2 = PR_Date.getAttribute("value");
		System.out.println(attribute2);
		WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
		String attribute3 = Payment_Mode.getAttribute("value");
		System.out.println(attribute3);
		WebElement Mail = driver.findElement(By.xpath("//input[@id='email000F1']"));
		String attribute4 = Mail.getAttribute("value");
		System.out.println(attribute4);
		WebElement Summary_Logo = driver.findElement(By.xpath("//img[@id='img000F1']"));
		String attribute5 = Summary_Logo.getAttribute("value");
		System.out.println(attribute5);
		 Actions a4 = new Actions(driver);
		 for (int i = 0; i < 4; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
//		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
//        Statement statement = connection.createStatement();
//        ResultSet res= statement.executeQuery(query2);
//		while (res.next())
//		{
//			String itemcat = res.getString("itemcat");	
//			String itemname = res.getString("itemname");	
//			String quantity = res.getString("quantity");	
////			System.out.println(itemcat + " " + itemname + " " + quantity);
//		String Item_details = itemcat + " " + itemname + " " + quantity;
		 	WebElement item_category = driver.findElement(By.xpath("//th[@id='th-prdcode']"));
			String cat = item_category.getText();
			WebElement Item_Name = driver.findElement(By.xpath("//th[@id='th-prodname']"));
			String Itname = Item_Name.getText();
			WebElement Quantity = driver.findElement(By.xpath("//th[@id='th-quantity']"));
			String Qty = Quantity.getText();
			WebElement Unit_Price1 = driver.findElement(By.xpath("//th[@id='th-uprice']"));
			String Uprice = Unit_Price1.getText();
			WebElement Total_Amount = driver.findElement(By.xpath("//th[@id='th-totalamount']"));
			String ttlamount = Total_Amount.getText();
			String Item_dtils = cat + " " + Itname + " " + Qty + " " + Uprice + " " + ttlamount;
			driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
			WebElement Item_cat1 = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
			String icat1 = Item_cat1.getText();
			WebElement Item_Name1 = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
			String inam1 = Item_Name1.getText();
			WebElement quantity1 = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
			String qty1 = quantity1.getAttribute("value");
			WebElement Unit_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
			String Uprce = Unit_Price.getAttribute("value");
			WebElement Total_Amt = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
			String ttlamt = Total_Amt.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo002F2']")).click();
//			WebElement Item_cat2 = driver.findElement(By.xpath("//span[@id='select2-itemcat002F2-container']"));
//			String icat2 = Item_cat2.getText();
//			WebElement Item_Name2 = driver.findElement(By.xpath("//span[@id='select2-itemname002F2-container']"));
//			String inam2 = Item_Name2.getText();
//			WebElement quantity2 = driver.findElement(By.xpath("//input[@id='quantity002F2']"));
//			String qty2 = quantity2.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo003F2']")).click();
//			WebElement Item_cat3 = driver.findElement(By.xpath("//span[@id='select2-itemcat003F2-container']"));
//			String icat3 = Item_cat3.getText();
//			WebElement Item_Name3 = driver.findElement(By.xpath("//span[@id='select2-itemname003F2-container']"));
//			String inam3 = Item_Name3.getText();
//			WebElement quantity3 = driver.findElement(By.xpath("//input[@id='quantity003F2']"));
//			String qty3 = quantity3.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo004F2']")).click();
//			WebElement Item_cat4 = driver.findElement(By.xpath("//span[@id='select2-itemcat004F2-container']"));
//			String icat4 = Item_cat4.getText();
//			WebElement Item_Name4 = driver.findElement(By.xpath("//span[@id='select2-itemname004F2-container']"));
//			String inam4 = Item_Name4.getText();
//			WebElement quantity4 = driver.findElement(By.xpath("//input[@id='quantity004F2']"));
//			String qty4 = quantity4.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo005F2']")).click();
//			WebElement Item_cat5 = driver.findElement(By.xpath("//span[@id='select2-itemcat005F2-container']"));
//			String icat5 = Item_cat5.getText();
//			WebElement Item_Name5 = driver.findElement(By.xpath("//span[@id='select2-itemname005F2-container']"));
//			String inam5 = Item_Name5.getText();
//			WebElement quantity5 = driver.findElement(By.xpath("//input[@id='quantity005F2']"));
//			String qty5 = quantity5.getAttribute("value");
//			String Item_Details = 
//					           icat1 + " " + inam1 + " " + qty1 
//					+ "\r\n" + icat2 + " " + inam2 + " " + qty2 
//					+ "\r\n" + icat3 + " " + inam3 + " " + qty3 
//					+ "\r\n" + icat4 + " " + inam4 + " " + qty4 
//					+ "\r\n" + icat5 + " " + inam5 + " " + qty5;
			String Item_Details = icat1 + " " + inam1 + " " + qty1 + " " + Uprce + " " + ttlamt;
			System.out.println(Item_Details);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Report Template With Data Source')]")).click();
			WaitForLoadingIconDisappear();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs3.get(1));
	    driver.manage().window().maximize();
	    Actions a5 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a5.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_rprttempwithdsrce.pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot9 = screenshot("TC_FP39 Checking & submit the report template with datasource in Print Form Name.png"); 
	    String Expected = "AGILE LABS PVT LTD\r\n"
		+ "Purchase Order\r\n"
		+ "\r\n"
		+ "Address : The Venture Studio,\r\n"
		+ "L-176, 3rd Floor, 5th Main, Sector - 6,\r\n"
		+ "HSR Layout, Bangalore – 560102\r\n"
		+ "E-MAIL: "+attribute4+" \r\n"
		+ "PR No: "+attribute1+" \r\n"
		+ "POR Date : "+attribute2+" \r\n"
		+ "Payment Mode : "+attribute3+" \r\n"
		+ "\r\n"
		+ ""+Item_dtils+" \r\n"
		+ ""+Item_Details+""
		+ "\r\n"
		+ "Copyright 2001-2023 By Agile Labs Pvt Ltd. All Rights Reserved.SiteMap\r\n"
		+ "Agile Labs Powered By Axpert.";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 9 Passed");
			test.addScreenCaptureFromPath(Screenshot9);
			excelpass(43, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 9 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot9);
			excelfail(43, actual, Expected, e);
		}
	}
	
	@Test(priority=15)
	public void Enabling_Add_to_auto_print() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP48 Checking & enabling the autoprint option")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("//a[contains(text(),'rprttempwithdsrce')]"));
		Name.click();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();	
		Thread.sleep(1000);
		WebElement button_click = driver.findElement(By.xpath("//input[@id='autoprint000F1']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(button_click));
		button_click.click();
		Boolean isEnabled = button_click.isEnabled();
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
		Thread.sleep(2000);
		if(isEnabled == true)
		{	
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario Passed");
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(48).getCell(6).setCellValue("Auto print option should get Enabled");
			wb.getSheetAt(0).getRow(48).getCell(7).setCellValue("Auto print option is Enabled");
			wb.getSheetAt(0).getRow(48).getCell(8).setCellValue("Pass");
			wb.getSheetAt(0).getRow(48).getCell(9);
			wb.write(fos);
			wb.close();
			System.out.println("Passed");
		}
		else
		{
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario Failed due to User is not able to enable the Auto print option" );
			File f = new File(excelreportpath);
			FileInputStream fis =  new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			FileOutputStream fos= new FileOutputStream(f);
			wb.getSheetAt(0).getRow(48).getCell(6).setCellValue("Auto print option should get Enabled");
			wb.getSheetAt(0).getRow(48).getCell(7).setCellValue("Auto print option is not Enabled");
			wb.getSheetAt(0).getRow(48).getCell(8).setCellValue("Fail");
			wb.getSheetAt(0).getRow(48).getCell(9).setCellValue("User is not able to enable the Auto print option");
			wb.write(fos);
			wb.close();
		}
		
	}
	
	@Test(priority=16)
	public void AutogeneratedID_In_Autoprint() throws InterruptedException, SQLException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP49 Checking the Autogenrated ID is created or not in autoprint screen")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement autoprintid1 = driver.findElement(By.xpath("(//td[@data-field-name='autoprintid'])[2]"));
		String actual = autoprintid1.getText();
		System.out.println(actual);
		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
        Statement statement = connection.createStatement();
        ResultSet res= statement.executeQuery(query1);
		while (res.next())
		{
		String autoprintid = res.getString("autoprintid");	
		System.out.print(autoprintid);
		String Expected = autoprintid;
		try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 11 Passed");
//			test.addScreenCaptureFromPath(Screenshot9);
			excelpass(49, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 11 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot9);
			excelfail(49, actual, Expected, e);
		}
	}
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	
	@Test (priority=17)
	public void Deleted_Printname_Auto_print() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP51 Checking the deleted fastprint print name is listing or not in Auto prints print name")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);	
//		login();
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("defaulttempwithoutdsrce");
		WebElement searchresult = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		System.out.println(searchresult);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Autoprint = driver.findElement(By.xpath("//span[contains(text(),'Auto Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Autoprint));
		Autoprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		driver.findElement(By.xpath("(//a[@class='l2'])[2]")).click();
		WebElement print_name = driver.findElement(By.xpath("//span[@aria-labelledby='select2-printdoc000F1-container']"));
		print_name.click();
		WebElement Search = driver.findElement(By.xpath("//input[@type='search']"));
		Search.sendKeys("defaulttempwithoutdsrce");
		WebElement result = driver.findElement(By.xpath("//li[contains(text(),'No results found')]"));
		String Screenshot13 = screenshot("TC_FP51 Checking the deleted fastprint print name is listing or not in Auto prints print name.Png");
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No results found";
		 try {
				assertEquals(actual, Expected);
				test.log(Status.PASS, "passed");
				test.pass("Test Case scenario 13 Passed");
				test.addScreenCaptureFromPath(Screenshot13);
				excelpass(50, actual, Expected);
				
			} catch (Exception e) {
				test.log(Status.FAIL, "Failed");
				test.fail("Test Case scenario 13 Failed due to " + e.getMessage());
				test.addScreenCaptureFromPath(Screenshot13);
				excelfail(50, actual, Expected, e);
			}
		 Thread.sleep(2000);
		    driver.switchTo().defaultContent();
			driver.findElement(By.xpath("(//button[@aria-label='Close'])[2]")).click();
	}
	@Test(priority=18)
	public void Modify_rprttempwithdsrce_Fastprint() throws InterruptedException, AWTException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP52 Checking & modifying the Report Format with DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
//		login();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("rprttempwithdsrce");
		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement Name = driver.findElement(By.xpath("(//a[contains(text(),'rprttempwithdsrce')])[1]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP);
		driver.findElement(By.xpath("//a[contains(text(),'Summary')]")).click();
		WebElement sourcea = driver.findElement(By.xpath("(//a[@title='Source'])[5]"));
		sourcea.click();
		WebElement Editor = driver.findElement(By.xpath("//textarea[@aria-label='Editor, report_summary000F6']"));
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Editor));
		Editor.click();
		Editor.sendKeys("<div><br />");
		Editor.sendKeys("<strong><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#e67e22\">BENEFITS</span></span>");
		Editor.sendKeys("</strong><br />");
		Editor.sendKeys("<span style=\"font-size:20px\"><span style=\"font-family:Arial,Helvetica,sans-serif\"><span style=\"color:#2980b9\">");
		Editor.sendKeys("<strong>Helping Clients Grow</strong></span></span></span><br />");
		Editor.sendKeys("<span style=\"font-size:14px\"><span style=\"font-family:Arial,Helvetica,sans-serif\"><strong>Join Agile Labs&rsquo; partner program and provide your clients with a scalable, reliable and secure platform. Being an Agile Labs partner has many perks including leveraging our brand value and domain expertise to explore new revenue streams.");
		Editor.sendKeys("</strong></span></span></div>");
		WebElement sourceb = driver.findElement(By.xpath("(//a[@title='Source'])[5]"));
		sourceb.click();
		  Actions a5 = new Actions(driver);
			 for (int i = 0; i < 5; i++) {
		            // Scroll up by 1000 pixels
				 	a5.sendKeys(Keys.PAGE_DOWN).build().perform();
		            try {
		                // Add a delay to see the scrolling action
		                Thread.sleep(1000);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		WebElement Report_Title_Left_Image = driver.findElement(By.xpath("//span[@aria-labelledby='select2-rs_image_leftui000F6-container']"));
		Report_Title_Left_Image.click();
		driver.findElement(By.xpath("//li[contains(text(),'Company Logo-(img)')]")).click();
		WebElement Height = driver.findElement(By.xpath("//input[@id='rs_image_left_height000F6']"));
		Height.click();
		Height.sendKeys("130");
		WebElement Width = driver.findElement(By.xpath("//input[@id='rs_image_left_Width000F6']"));
		Width.click();
		Width.sendKeys("70");
		WebElement Generate_Fast_Report = driver.findElement(By.xpath("//span[contains(text(),'addchart')]"));
		Generate_Fast_Report.click();
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
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement Employee_Code = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		Employee_Code.click();
		WaitForLoadingIconDisappear();
		WebElement PR_No = driver.findElement(By.xpath("//span[@id='select2-tprno000F1-container']"));
		String attribute1 = PR_No.getText();
		System.out.println(attribute1);
		WebElement PR_Date = driver.findElement(By.xpath("//input[@id='podate000F1']"));
		String attribute2 = PR_Date.getAttribute("value");
		System.out.println(attribute2);
		WebElement Payment_Mode = driver.findElement(By.xpath("//option[@selected='selected']"));
		String attribute3 = Payment_Mode.getAttribute("value");
		System.out.println(attribute3);
		WebElement Mail = driver.findElement(By.xpath("//input[@id='email000F1']"));
		String attribute4 = Mail.getAttribute("value");
		System.out.println(attribute4);
		WebElement Summary_Logo = driver.findElement(By.xpath("//img[@id='img000F1']"));
		String attribute5 = Summary_Logo.getAttribute("value");
		System.out.println(attribute5);
		 Actions a4 = new Actions(driver);
		 for (int i = 0; i < 4; i++) {
	            // Scroll up by 1000 pixels
			 	a4.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
//		Connection connection = DriverManager.getConnection(jdbcUrl, usernamedb, passworddb);
//        Statement statement = connection.createStatement();
//        ResultSet res= statement.executeQuery(query2);
//		while (res.next())
//		{
//			String itemcat = res.getString("itemcat");	
//			String itemname = res.getString("itemname");	
//			String quantity = res.getString("quantity");	
////			System.out.println(itemcat + " " + itemname + " " + quantity);
//		String Item_details = itemcat + " " + itemname + " " + quantity;
		 	WebElement item_category = driver.findElement(By.xpath("//th[@id='th-prdcode']"));
			String cat = item_category.getText();
			WebElement Item_Name = driver.findElement(By.xpath("//th[@id='th-prodname']"));
			String Itname = Item_Name.getText();
			WebElement Quantity = driver.findElement(By.xpath("//th[@id='th-quantity']"));
			String Qty = Quantity.getText();
			WebElement Unit_Price1 = driver.findElement(By.xpath("//th[@id='th-uprice']"));
			String Uprice = Unit_Price1.getText();
			WebElement Total_Amount = driver.findElement(By.xpath("//th[@id='th-totalamount']"));
			String ttlamount = Total_Amount.getText();
			String Item_dtils = cat + " " + Itname + " " + Qty + " " + Uprice + " " + ttlamount;
			driver.findElement(By.xpath("//label[@id='lblSlNo001F2']")).click();
			WebElement Item_cat1 = driver.findElement(By.xpath("//span[@id='select2-prdcode001F2-container']"));
			String icat1 = Item_cat1.getText();
			WebElement Item_Name1 = driver.findElement(By.xpath("//span[@id='select2-prodname001F2-container']"));
			String inam1 = Item_Name1.getText();
			WebElement quantity1 = driver.findElement(By.xpath("//input[@id='quantity001F2']"));
			String qty1 = quantity1.getAttribute("value");
			WebElement Unit_Price = driver.findElement(By.xpath("//input[@id='uprice001F2']"));
			String Uprce = Unit_Price.getAttribute("value");
			WebElement Total_Amt = driver.findElement(By.xpath("//input[@id='totalamount001F2']"));
			String ttlamt = Total_Amt.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo002F2']")).click();
//			WebElement Item_cat2 = driver.findElement(By.xpath("//span[@id='select2-itemcat002F2-container']"));
//			String icat2 = Item_cat2.getText();
//			WebElement Item_Name2 = driver.findElement(By.xpath("//span[@id='select2-itemname002F2-container']"));
//			String inam2 = Item_Name2.getText();
//			WebElement quantity2 = driver.findElement(By.xpath("//input[@id='quantity002F2']"));
//			String qty2 = quantity2.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo003F2']")).click();
//			WebElement Item_cat3 = driver.findElement(By.xpath("//span[@id='select2-itemcat003F2-container']"));
//			String icat3 = Item_cat3.getText();
//			WebElement Item_Name3 = driver.findElement(By.xpath("//span[@id='select2-itemname003F2-container']"));
//			String inam3 = Item_Name3.getText();
//			WebElement quantity3 = driver.findElement(By.xpath("//input[@id='quantity003F2']"));
//			String qty3 = quantity3.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo004F2']")).click();
//			WebElement Item_cat4 = driver.findElement(By.xpath("//span[@id='select2-itemcat004F2-container']"));
//			String icat4 = Item_cat4.getText();
//			WebElement Item_Name4 = driver.findElement(By.xpath("//span[@id='select2-itemname004F2-container']"));
//			String inam4 = Item_Name4.getText();
//			WebElement quantity4 = driver.findElement(By.xpath("//input[@id='quantity004F2']"));
//			String qty4 = quantity4.getAttribute("value");
//			driver.findElement(By.xpath("//label[@id='lblSlNo005F2']")).click();
//			WebElement Item_cat5 = driver.findElement(By.xpath("//span[@id='select2-itemcat005F2-container']"));
//			String icat5 = Item_cat5.getText();
//			WebElement Item_Name5 = driver.findElement(By.xpath("//span[@id='select2-itemname005F2-container']"));
//			String inam5 = Item_Name5.getText();
//			WebElement quantity5 = driver.findElement(By.xpath("//input[@id='quantity005F2']"));
//			String qty5 = quantity5.getAttribute("value");
//			String Item_Details = 
//					           icat1 + " " + inam1 + " " + qty1 
//					+ "\r\n" + icat2 + " " + inam2 + " " + qty2 
//					+ "\r\n" + icat3 + " " + inam3 + " " + qty3 
//					+ "\r\n" + icat4 + " " + inam4 + " " + qty4 
//					+ "\r\n" + icat5 + " " + inam5 + " " + qty5;
			String Item_Details = icat1 + " " + inam1 + " " + qty1 + " " + Uprce + " " + ttlamt;
			System.out.println(Item_Details);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[contains(text(),'apps')]")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Report Template With Data Source')]")).click();
			WaitForLoadingIconDisappear();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
//	    driver.switchTo().window(tabs2.get(1));
//	    driver.close();
//	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs3.get(1));
	    driver.manage().window().maximize();
	    Actions a6 = new Actions(driver);
		 for (int i = 0; i < 5; i++) {
	            // Scroll up by 1000 pixels
			 	a6.sendKeys(Keys.PAGE_DOWN).build().perform();
	            try {
	                // Add a delay to see the scrolling action
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    FileInputStream obj = new FileInputStream("C:\\Users\\srijesh\\Downloads\\prv_frx_rprttempwithdsrce (1).pdf");
	    PDDocument objdoc = PDDocument.load(obj);
	    PDFTextStripper objPDFStrp = new PDFTextStripper();
	    String actual = objPDFStrp.getText(objdoc);
	    System.out.println(actual);
	    String Screenshot12 = screenshot("TC_FP52 Checking & modifying the Report Format with DataSource.png"); 
	    String Expected = "AGILE LABS PVT LTD\r\n"
		+ "Purchase Order\r\n"
		+ "\r\n"
		+ "Address : The Venture Studio,\r\n"
		+ "L-176, 3rd Floor, 5th Main, Sector - 6,\r\n"
		+ "HSR Layout, Bangalore – 560102\r\n"
		+ "E-MAIL: "+attribute4+" \r\n"
		+ "PR No: "+attribute1+" \r\n"
		+ "POR Date : "+attribute2+" \r\n"
		+ "Payment Mode : "+attribute3+" \r\n"
		+ "\r\n"
		+ ""+Item_dtils+" \r\n"
		+ ""+Item_Details+""
		+ "\r\n"
		+ "BENEFITS\r\n"
		+ "Helping Clients Grow\r\n"
		+ "Join Agile Labs’ partner program and provide your clients with a scalable, reliable and secure\r\n"
		+ "platform. Being an Agile Labs partner has many perks including leveraging our brand value and\r\n"
		+ "domain expertise to explore new revenue streams."
		+ "Copyright 2001-2023 By Agile Labs Pvt Ltd. All Rights Reserved.SiteMap\r\n"
		+ "Agile Labs Powered By Axpert.";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 12 Passed");
			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(50, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 12 Failed due to " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(50, actual, Expected, e);
		}
	}
	
	@Test(priority=19)
	public void Delete_Created_Fastprint() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("TC_FP51 Checking & deleting the Report Format with DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs1.get(0));
		driver.findElement(By.xpath("//div[@id='AllUserOptions']")).click();
		driver.findElement(By.xpath("(//span[@title='Configuration'])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='vw-100 vh-100']")));
		WebElement Fastprint = driver.findElement(By.xpath("//span[contains(text(),'Fast Prints')]"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(Fastprint));
		Fastprint.click();
		WaitForLoadingIconDisappear();
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[@id='axpiframeac'])[1]")));
		Thread.sleep(2000);
		WebElement Name = driver.findElement(By.xpath("(//a[contains(text(),'rprttempwithdsrce')])[1]"));
		Name.click();	
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn8']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//a[@id='iconsNewSearchIcon']")).click();
//		driver.findElement(By.xpath("//button[@id='ivInSearchInputButton']")).click();
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search records...']"));
		search.sendKeys("rprttempwithdsrce");
		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'No matching records found')]"));
		String actual = result.getText();
		System.out.println(actual);
		String Expected = "No matching records found";
	    try {
			assertEquals(actual, Expected);
			test.log(Status.PASS, "passed");
			test.pass("Test Case scenario 13 Passed");
//			test.addScreenCaptureFromPath(Screenshot12);
			excelpass(51, actual, Expected);
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Failed");
			test.fail("Test Case scenario 13 Failed due to " + e.getMessage());
//			test.addScreenCaptureFromPath(Screenshot12);
			excelfail(51, actual, Expected, e);
		}
	}
	
	@Test(enabled = false)
	public void Verify_Deleted_Fastprint() throws InterruptedException
	{
		ExtentTest test = extent.createTest("TC_FP52 Checking & deleting the Report Format with DataSource")
				.assignAuthor(Testauthor).assignCategory(Testcategory).assignDevice(browsername);
		login();
		driver.findElement(By.xpath("//div[@id='kt_header_search']")).click();
		WebElement srch = driver.findElement(By.xpath("//input[@id='globalSearchinp']"));
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(srch));
		srch.click();
		srch.click();
		srch.sendKeys("Report Form Fastprint");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[contains(text(),'Report Form Fastprint')])[2]")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='middle1']")));
		driver.findElement(By.xpath("//a[@id='btn13']")).click();
		WaitForLoadingIconDisappear();
		WebElement Employee_Code = driver.findElement(By.xpath("(//a[@class='l2'])[1]"));
		Employee_Code.click();
		WaitForLoadingIconDisappear();
		driver.findElement(By.xpath("//button[@id='btnAppsHeader']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Report Template With Datasource')]")).click();
		WaitForLoadingIconDisappear();
		
		
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
