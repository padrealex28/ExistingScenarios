package testCases;

import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.FindQuoteObjects;
import utilClass.CommonFunctions;

public class FindQuoteTest extends CommonFunctions {

	public void FindAndClickQuote(Map map) {
		PageFactory.initElements(driver, FindQuoteObjects.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		Logger logger = Logger.getLogger(FindQuoteTest.class);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Trying to Click Find Menu and By Quote");
		FindQuoteObjects.FindMenu.click();
		FindQuoteObjects.byQuoteMenu.click();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+":Clicked Find Menu,Now Trying to Enter Quote number in By Quote Text Box");
		wait.until(ExpectedConditions.elementToBeClickable(FindQuoteObjects.byQuoteTextBox));
		FindQuoteObjects.byQuoteTextBox.click();		
		FindQuoteObjects.byQuoteTextBox.sendKeys(map.get("Quote Number").toString());
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Quote Number Entered, Now Trying to Click Search Button"); 	
		FindQuoteObjects.searchButton.click();
		
		String quoteResult = FindQuoteObjects.quoteLink;			
		String quoteresultXpath = String.format(quoteResult, map.get("Quote Number").toString());	
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+":Search Button Clicked,Waiting for Search Result..."); 
		WebElement quoteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(quoteresultXpath)));
		quoteLink.click();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+":Clicking the Search Result..."); 
		wait.until(ExpectedConditions.visibilityOf(FindQuoteObjects.UWsheetButton));
		
	}

}
