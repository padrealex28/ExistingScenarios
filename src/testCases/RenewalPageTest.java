package testCases;

import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import pageObjects.RenewalPageObjects;
import utilClass.CommonFunctions;
import utilClass.RetryElements;

public class RenewalPageTest extends CommonFunctions {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
	Logger logger = Logger.getLogger(RenewalPageTest.class);

	public void renewPolicy(Map map) throws NoSuchElementException, InterruptedException {
		PageFactory.initElements(driver, RenewalPageObjects.class);
		EndorsementPageTest endo = new EndorsementPageTest();
		endo.clickPolicyNumberLink(map);
		
		wait.until(ExpectedConditions.visibilityOf(RenewalPageObjects.renewalsSidePanelButton));
		RetryElements.Wait(4000);
		RenewalPageObjects.renewalsSidePanelButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Retrieving the Policy renewals')]")));
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateRenewalQuoteButton));

		RetryElements.Wait(3000);
		RenewalPageObjects.CreateRenewalQuoteButton.click();
		logger.info("Clicked Create Renewal Quote proposal");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Creating Renewal Quote')]")));
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateQuoteProposalButton));
	
		
	/*	RenewalPageObjects.refer_renewal_quote.click();
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.refer_renewal_quote_pop_up));
		RenewalPageObjects.refer_renewal_quote_pop_up.click();
        RetryElements.Wait(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Referring the quote to underwriter')]")));
		logger.info("Clicked Refer Renewal Quote proposal"); */

		
		RenewalPageObjects.CreateQuoteProposalButton.click();		
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateQuoteProposalDlgBoxButton));
		RenewalPageObjects.CreateQuoteProposalDlgBoxButton.click();
		

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Creating Quote Proposal')]")));
	
		
		// to get the policy status
	//	Cancel_Reinstate_Test Cancel_Reinstate_Test = new Cancel_Reinstate_Test();
	//	String policyStatus = Cancel_Reinstate_Test.getPolicyStatus(map);
		
		QuoteStatusTest QuoteStatusTest = new QuoteStatusTest();
		QuoteStatusTest.checkQuoteStatus(map);
		
	}
}
