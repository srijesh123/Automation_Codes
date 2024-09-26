package Axpert.Configuration_Studio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {
	protected static WebDriver driver;
	 ExtentTest test;
	 ExtentReports extent;
	 ExtentSparkReporter spark;
	
	 ////////////////////////////////////////////////////////---Schema URL & Project---////////////////////////////////////////////////////////////////////
	//URL
//	protected String schemaURL = "http://172.16.0.85/run11.2/aspx/signin.aspx";
	
	//URL
//	protected String schemaURL = "http://localhost/AxpertWeb/aspx/signin.aspx";
	
	//URL Public
	protected String schemaURL = "https://qa.agilecloud.biz/Axpert11.3Alpha";
	
	//URL1 demo
//	String schemaURL1 = "https://agilecloud.biz/AxpertRun/aspx/signin.aspx";
	
	//URL demo product
//	String schemaURL = "https://agilecloud.biz/axpertrun/aspx/mainnew.aspx";
	
	//project
	protected static String Projectname ="testbed";
		
	//Browser name
	public static String browsername="chrome";
	
	//Browser name
	static String browsername1="firefox";
	
	//Browser name
	static String browsername2="edge";
		
	 ////////////////////////////////////////////////////////---Report---////////////////////////////////////////////////////////////////////
	
	//Release
	static String Release="Release_8";
	//Module
	static String module="Fast_Print";
	
	//Screenshot Path
	public static String Screenshotpath = "D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Screenshot\\Schema11.3\\"+Release+"\\Fast_Print\\";

	//Extendreport Path
	public String Extendreportpath= "D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Extend Report\\version 11.3\\"+Release+"\\"+module+".html";
	
	//Excelreport Path
	public String excelreportpath= "D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Excel Report\\FastPrint Automation.xlsx";
		
	
	//TestCategory
	public String Testcategory="Functional Testing";
	
	//Testauthor
	public String Testauthor="Srijesh";
	
	//ReportName
	public String Reportname="Fast_print";
	
	 ////////////////////////////////////////////////////////---Report---////////////////////////////////////////////////////////////////////	
	
	
    ////////////////////////////////////////////////////////---User Credentials---////////////////////////////////////////////////////////////////////
	//valid username
	protected static String username="admin";
	//valid password
	protected static String password="agile";
	
	//valid username another
	static String username1="user1";
	//valid password
	static String password1="agile";
		
	//valid username another
	static String username2="user2";
	//valid password
	static String password2="agile";
	
	//valid username another
	static String username3="HRuser";
	//valid password
	static String password3="agile";
	
	//valid username another
	static String username4="muser1";
	//valid password
	static String password4="agile";
	
	//valid username another
	static String username5="HRuser1";
	//valid password
	static String password5="agile";
	
	//valid username another
	static String username6="muser2";
	//valid password
	static String password6="agile";
	
	//invalid username
	static String invalidusername="admin123";
	//invalid password
	static String invalidpassword="123agile";
	
	//nil value
	static String nilvalue="";
	
	//invalid username
	static String invalidname="invalidusername";
	
	//valid username
	static String validusername="Testuser"; 
	
	//valid NewUsermail
	static String validusermail="karthikeyan@agile-labs.com";
	
	//valid newuser
	static String validnewusename="Newtestuser";
	
	//default newuser password
	static String defaultpassword="12345";
	
	//java database URL
	protected static String jdbcUrl = "jdbc:postgresql://172.16.0.135:5432/qatestbed";
		
	//Database Username
	protected static String usernamedb = "testbed";
		
	//Database Password
	protected static String passworddb= "log";
		
	//Query
	protected static String query1 = "select autoprintid from axautoprints a\r\n"
			+ "order by createdon desc limit 1;";
	
	//Query
	protected static String query2 = "select itemcat, itemname, quantity from preqq2\r\n"
			+ "limit 5";
	
	//Query
	protected static String query3 = "select address,billto,ship_to from purch1 p";
	
	//Query
	protected static String Form_query = "select caption from tstructs t order by 1";
		
	//Query
	protected static String Report_query = "select caption from iviews i order by 1";
		
	//Query
	protected static String Html_query = "select caption from htmlsections h order by 1";
	
	//Query
	protected static String Hide_query_Form = "select cardhide from axhomeconfig a where caption ='Purchase Request Form'";
	
	//Query
	protected static String Hide_query_Report = "select cardhide from axhomeconfig a where caption ='Iview with Picklist MOE'";
		
	//Query
	protected static String Hide_query_Custom = "select cardhide from axhomeconfig a where caption ='Hello Page'";	
		
	//Query
	protected static String Single_Group_Caption = "select 'Group folder - '||groupfolder as groupfolder,'Caption - '||caption as caption from axhomeconfig where groupfolder ='Tstruct'";
	
	//Query
	protected static String Single_Group_Caption_With_Form = "select 'Group folder - '||groupfolder as groupfolder,'Caption - '||caption as caption from axhomeconfig where groupfolder ='Ticket Management'";
	
	//Query
	protected static String Multiple_Group_Caption = "select 'Group folder : ' as groupfolder from dual d\r\n"
			+ "union all\r\n"
			+ "select distinct groupfolder from axhomeconfig where groupfolder ='Product Details'\r\n"
			+ "union all\r\n"
			+ "Select 'Caption : ' from dual d2 \r\n"
			+ "union all\r\n"
			+ "select caption from axhomeconfig where groupfolder ='Product Details'";	
	
	//Query
	protected static String Without_Group_Folder = "select caption from axhomeconfig a where groupfolder =''";

	
	public void Save()
	{
		WebElement Save = driver.findElement(By.xpath("//a[@id='ftbtn_iSave']"));
		Save.click();
	}
	
	 ////////////////////////////////////////////////////////---User Credentials---////////////////////////////////////////////////////////////////////
	
	 ////////////////////////////////////////////////////////---Browser ---////////////////////////////////////////////////////////////////////
	//browser
	public static WebDriver BrowserLaunch(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			return driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Driver\\geckodriver.exe");
			return driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"D:\\local_disk_c_bkp\\git\\Axpert_Configuration_Studio\\Configuration_Studio\\Driver\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			return driver = new EdgeDriver(options);
		}
		else {
			System.out.println("Please Enter Chrome Or Firefox or edge");
		}
		return driver;
	}
	////////////////////////////////////////////////////////---Browser ---////////////////////////////////////////////////////////////////////
	
	public void WaitForLoadingIconDisappear() throws InterruptedException
    {
        List<WebElement> element = driver.findElements(By.xpath("//div[@class=\"page-loader rounded-2 bg-radial-gradient\"]"));
        System.out.println("LoadingIcon :"+element.size());
        int count=0;
        while (element.size()!=0 && count < 20)
        {
        	new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfAllElements(element));
            count++;
            
        }
    }

	public void excelpass(int row,String Expected,String actual) throws IOException {
		File f = new File(excelreportpath);
		FileInputStream fis =  new FileInputStream(f);
		Workbook wb = new XSSFWorkbook(fis);
		FileOutputStream fos= new FileOutputStream(f);
		wb.getSheetAt(0).getRow(row).getCell(6).setCellValue(Expected);
		wb.getSheetAt(0).getRow(row).getCell(7).setCellValue(actual);
		wb.getSheetAt(0).getRow(row).getCell(8).setCellValue("Pass");
		wb.getSheetAt(0).getRow(row).createCell(9);
		fis.close();
		wb.write(fos);
		fos.close();
		wb.close();
		System.out.println("Passed");
	}
	public void excelfail(int row,String Expected,String actual,Exception e) throws IOException {	
	File f = new File(excelreportpath);
	FileInputStream fis =  new FileInputStream(f);
	Workbook wb = new XSSFWorkbook(fis);
	FileOutputStream fos= new FileOutputStream(f);
	wb.getSheetAt(0).getRow(row).getCell(6).setCellValue(Expected);
	wb.getSheetAt(0).getRow(row).getCell(7).setCellValue(actual);
	wb.getSheetAt(0).getRow(row).getCell(8).setCellValue("Fail");
	String message = e.getMessage();
	wb.getSheetAt(0).getRow(row).getCell(9).setCellValue(message);
	wb.write(fos);
	wb.close();
	}
	
	public static boolean validatePaperSize1(String width, String height, String paperSizeFormat) {
	        // Example logic: Validate paper size based on the selected format (e.g., A4)
	        // You would need a reference table or some logic to determine the allowed ranges for width and height
	        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
	        return width.matches("210") && height.matches("297");
	    }
	
	public static boolean validatePaperSize2(String width, String height, String paperSizeFormat) {
        // Example logic: Validate paper size based on the selected format (e.g., A5)
        // You would need a reference table or some logic to determine the allowed ranges for width and height
        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
        return width.matches("148") && height.matches("210");
    }
	
	public static boolean validatePaperSize3(String width, String height, String paperSizeFormat) {
        // Example logic: Validate paper size based on the selected format (e.g., letter1)
        // You would need a reference table or some logic to determine the allowed ranges for width and height
        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
        return width.matches("279") && height.matches("431");
    }
	
	public static boolean validatePaperSize4(String width, String height, String paperSizeFormat) {
        // Example logic: Validate paper size based on the selected format (e.g., letter2)
        // You would need a reference table or some logic to determine the allowed ranges for width and height
        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
        return width.matches("216") && height.matches("356");

    }
	
	public static boolean validatePaperSize5(String width, String height, String paperSizeFormat) {
        // Example logic: Validate paper size based on the selected format (e.g., letter3)
        // You would need a reference table or some logic to determine the allowed ranges for width and height
        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
        return width.matches("215") && height.matches("279");
    }
	
	public static boolean validatePaperSize6(String width, String height, String paperSizeFormat) {
        // Example logic: Validate paper size based on the selected format (e.g., letter4)
        // You would need a reference table or some logic to determine the allowed ranges for width and height
        // For demonstration purposes, let's assume a simple validation that both width and height must be numeric
        return width.matches("184") && height.matches("267");
    }
	
}
