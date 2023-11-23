package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.Duration;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import pageObjects.LoginPageObjects;
import utilClass.CommonFunctions;

public class LoginPageTest extends CommonFunctions {

	Logger logger = Logger.getLogger(LoginPageTest.class);
    
	
     public void runnerMethod(Map map) throws Exception { 		 
	  loginPage(map);
      FindQuoteTest findQuote = new FindQuoteTest();
      findQuote.FindAndClickQuote(map);
     // PDF_Regeneration PDF_Regeneration = new PDF_Regeneration();
     // PDF_Regeneration.regenerate_PDF(map);
      
     QuoteStatusTest quoteStatus = new QuoteStatusTest();
      quoteStatus.checkQuoteStatus(map);
    //  EndorsementPageTest endorsement = new EndorsementPageTest();
   //  endorsement.issueEndorsement(map);
     RenewalPageTest RenewalPageTest = new RenewalPageTest();
    RenewalPageTest.renewPolicy(map);
  //   Cancel_Reinstate_Test cancel_reinstate = new Cancel_Reinstate_Test();
  //   cancel_reinstate.cancelPolicy(map);
  //   cancel_reinstate.reinstatePolicy(map);
  }
		
	public void loginPage(Map map) throws Exception {
	  PageFactory.initElements(driver, LoginPageObjects.class);
	//  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	//  wait.until(ExpectedConditions.visibilityOf(LoginPageObjects.userName));
	  logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Test Data no "+map.get("Test Data No").toString());	
	  LoginPageObjects.userName.clear();
      LoginPageObjects.userName.sendKeys(map.get("Username").toString());		
      LoginPageObjects.password.clear();
      LoginPageObjects.password.sendKeys(map.get("Password").toString());
	  LoginPageObjects.signInBtn.click();
	  logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": UserName,PassWord Entered, SignIn button Clicked");
	  if(driver.getCurrentUrl().contains("Home")==true) {
		  Assert.assertEquals(true, true);
	  }
	  else {
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File("D:\\Failed ss\\td_"+map.get("Test Data No").toString()+"_"+Math.random()+".jpg");
			FileUtils.copyFile(SrcFile, DestFile);
	  }
	  

	}
}