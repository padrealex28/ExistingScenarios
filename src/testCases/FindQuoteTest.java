package testCases;

import java.time.Duration;
import java.util.Map;

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

		FindQuoteObjects.FindMenu.click();
		FindQuoteObjects.byQuoteMenu.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(FindQuoteObjects.byQuoteTextBox));
		FindQuoteObjects.byQuoteTextBox.click();		
		FindQuoteObjects.byQuoteTextBox.sendKeys(map.get("Quote Number").toString());
		
		FindQuoteObjects.searchButton.click();
		
		String quoteResult = FindQuoteObjects.quoteLink;			
		String quoteresultXpath = String.format(quoteResult, map.get("Quote Number").toString());	
		WebElement quoteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(quoteresultXpath)));
		quoteLink.click();
		
		wait.until(ExpectedConditions.visibilityOf(FindQuoteObjects.UWsheetButton));
		
	}

}
