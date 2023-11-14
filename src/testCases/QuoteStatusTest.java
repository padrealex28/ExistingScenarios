package testCases;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.QuoteStatusObjects;
import utilClass.CommonFunctions;
import utilClass.RetryElements;

public class QuoteStatusTest extends CommonFunctions {
	

	public void checkQuoteStatus(Map map) throws InterruptedException {
		PageFactory.initElements(driver, QuoteStatusObjects.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		String QuoteStatus = QuoteStatusObjects.quoteStatus.getText();
		WebElement nextPageLink = QuoteStatusObjects.businessRuleTable2ndPage;
		Logger logger = Logger.getLogger(QuoteStatusTest.class);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Quote Status is "+QuoteStatus);
		
		if (QuoteStatus.equalsIgnoreCase("Referred") || QuoteStatus.equalsIgnoreCase("Offered") ) {
			
		if	(QuoteStatus.equalsIgnoreCase("Referred")) {
		
			ClickOverride();		

		try {
			if (nextPageLink.isDisplayed()) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()
						+ ": 2nd page for Referral rule ovverride DISPLAYED");
				nextPageLink.click();
				ClickOverride();
			}
		} catch (Exception e) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ ": 2nd page for Referral rule ovverride NOT DISPLAYED");
		}	
	//	wait.until(ExpectedConditions.elementToBeClickable(QuoteStatusObjects.createQuoteProposal)).click();
		if(map.get("Change Bill Type to Agency Bill?").toString().equalsIgnoreCase("yes")) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'UNDERWRITER WORKSHEET')])[1]")));
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+" Trying to Click UW sheet button");
		driver.findElement(By.xpath("(//*[contains(text(),'UNDERWRITER WORKSHEET')])[1]")).click();
		RetryElements.Element_select(QuoteStatusObjects.billing_Type_inUWSheet, "AgencyBill");
		RetryElements.Wait(3000);
		QuoteStatusObjects.UW_Sheet_SaveButton.click();
		}

		wait.until(ExpectedConditions.invisibilityOf(QuoteStatusObjects.SavingRates_UW_Sheet_Loader));
		wait.until(ExpectedConditions.elementToBeClickable(QuoteStatusObjects.createQuoteProposal));
		QuoteStatusObjects.createQuoteProposal.click();
		QuoteStatusObjects.createQuoteProposalBtn.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Creating Quote Proposal')]")));
		}
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Entering Summary Page Test");
		SummaryPageTest summ = new SummaryPageTest();
		summ.issuePolicy(map);
		
		}		
		else {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Did Not Go Inside Any IF Condition");
		}
					
				
}


	private void ClickOverride() throws InterruptedException {
		try {
		List<WebElement> businessRuleRows = QuoteStatusObjects.businessRuleTable.findElements(By.tagName("tr"));
		int rowCount = businessRuleRows.size();
		for (int row = 1; row < rowCount; row++) {
			Thread.sleep(2000);
			WebElement selectElement = businessRuleRows.get(row).findElements(By.tagName("td")).get(2)
					.findElement(By.tagName("select"));
			Select select = new Select(selectElement);
			select.selectByVisibleText("Override");
			
		}
		QuoteStatusObjects.saveStatus.click();
		}
		catch (Exception e) {
			List<WebElement> businessRuleRows = QuoteStatusObjects.businessRuleTable.findElements(By.tagName("tr"));
			int rowCount = businessRuleRows.size();
			for (int row = 1; row < rowCount; row++) {
				Thread.sleep(2000);
				WebElement selectElement = businessRuleRows.get(row).findElements(By.tagName("td")).get(2)
						.findElement(By.tagName("select"));
				Select select = new Select(selectElement);
				select.selectByVisibleText("Override");
				
			}
			QuoteStatusObjects.saveStatus.click();
		}
	}
}
