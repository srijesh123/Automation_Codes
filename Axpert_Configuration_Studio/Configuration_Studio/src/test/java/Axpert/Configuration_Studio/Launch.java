package Axpert.Configuration_Studio;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Launch extends Base {
	 //static WebDriver driver;
	
	ExtentSparkReporter spark = new ExtentSparkReporter(Extendreportpath);
	protected ExtentReports extent = new ExtentReports();
	
	@BeforeTest
	public void launch() throws InterruptedException{
		
		BrowserLaunch(browsername2);
		Thread.sleep(2000);
		driver.get(schemaURL); 
		driver.manage().window().maximize();
	
	        //initialize ExtentReports and attach the HtmlReporter
	        extent = new ExtentReports();
	        extent.attachReporter(spark);
			spark.config().setDocumentTitle(Reportname);
			spark.config().setReportName(Reportname);
			spark.config().setTheme(Theme.DARK);
		}
	@AfterTest
	public void close() throws Exception
	{
		System.gc();
//		driver.close(); 
		extent.flush();
		
	}	
	
		public static void selectproject() throws InterruptedException {
	WebElement selectproject = driver.findElement(By.xpath(
			"//span[@id='select2-axSelectProj-container']"));
		selectproject.click();
		WebElement searchbox = driver.findElement(By.xpath("//input[@type='search']"));
		searchbox.sendKeys(Projectname);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		List<WebElement> suggestionvalues = driver.findElements(By.xpath("//ul/li[@role=\"option\"]"));
		//System.out.println("listsize " + suggestionvalues.size());
		for (int i = 0; i < suggestionvalues.size(); i++) {
			//System.out.println(suggestionvalues.get(i).getText());
			if (suggestionvalues.get(i).getText().equals(Projectname)) {
				suggestionvalues.get(i).click();
				break;
			}
		}
	}
		
}