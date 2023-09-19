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

public class QuoteStatusTest extends CommonFunctions {
	Logger logger = Logger.getLogger(QuoteStatusTest.class);

	public void checkQuoteStatus(Map map) throws InterruptedException {
		PageFactory.initElements(driver, QuoteStatusObjects.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		String QuoteStatus = QuoteStatusObjects.quoteStatus.getText();
		WebElement nextPageLink = QuoteStatusObjects.businessRuleTable2ndPage;

		if (QuoteStatus.equalsIgnoreCase("Referred")) {
			ClickOverride();		

		try {
			if (nextPageLink.isDisplayed()) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()
						+ "2nd page for Referral rule ovverride DISPLAYED");
				ClickOverride();
			}
		} catch (Exception e) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()
					+ "2nd page for Referral rule ovverride NOT DISPLAYED");
		}			
		wait.until(ExpectedConditions.elementToBeClickable(QuoteStatusObjects.createQuoteProposal)).click();
		QuoteStatusObjects.createQuoteProposalBtn.click();
		
		}		

		else if(QuoteStatus.equalsIgnoreCase("Offered")) {
			SummaryPageTest summ = new SummaryPageTest();
			summ.issuePolicy(map);
		}							
	}

	private void ClickOverride() throws InterruptedException {
		List<WebElement> businessRuleRows = QuoteStatusObjects.businessRuleTable.findElements(By.tagName("tr"));
		int rowCount = businessRuleRows.size();
		for (int row = 1; row < rowCount; row++) {
			Thread.sleep(2000);
			WebElement selectElement = businessRuleRows.get(row).findElements(By.tagName("td")).get(2)
					.findElement(By.tagName("select"));
			Select select = new Select(selectElement);
			select.selectByVisibleText("Override");
			QuoteStatusObjects.saveStatus.click();
		}
	}
}
