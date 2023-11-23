package utilClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import testCases.LoginPageTest;
import testCases.readFromExcel;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonFunctions {
    
	public static WebDriver driver = null;
	protected static Properties properties = null;
	public static XSSFWorkbook output = new XSSFWorkbook();
	public static XSSFWorkbook ScheduleOfFormsWorkBook = new XSSFWorkbook();	
	//ExtentReports extentReport;
	//ExtentHtmlReporter extentHTML;
	//ExtentTest testCase;
	@BeforeSuite
	public Properties loadPropertyFile() throws IOException {

		FileInputStream fileInputStream = new FileInputStream("config.properties");
		properties = new Properties();
		properties.load(fileInputStream);	
		return properties;

	}
	
	
	@Test(dataProvider ="Datafromexcel",dataProviderClass = readFromExcel.class)
	public void launchBrowser(Map map) throws Exception {
	//	extentReport = new ExtentReports();
	//	extentHTML = new ExtentHtmlReporter("extentreport.html");
	//	extentReport.attachReporter(extentHTML);
		
		PropertyConfigurator.configure("log4j.properties");
		loadPropertyFile();
		String browser = properties.getProperty("browser");
		String URL = properties.getProperty("url");
		String driverLoc = properties.getProperty("driverLocation");
		System.setProperty("webdriver.chrome.driver", driverLoc);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("download.default_directory","D:\\PDF DOCUMENTS\\"+map.get("Test Data No"));
		
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		if (browser.equalsIgnoreCase("Chrome")) {
		//	testCase = extentReport.createTest("Opening Google Chrome");
			
			//System.setProperty("webdriver.chrome.driver", driverLoc);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}

		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LoginPageTest runner = new LoginPageTest();
		runner.runnerMethod(map);
		
		//	testCase.log(Status.PASS, "Google Chrome Opened Successfully");
    //    extentReport.flush();
	}

//	@AfterTest
	public void tearDown() throws IOException {
//		 driver.quit();
	}

}