package testCases;

import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.RenewalPageObjects;
import utilClass.CommonFunctions;

public class RenewalPageTest extends CommonFunctions {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	Logger logger = Logger.getLogger(RenewalPageTest.class);

	public void renewPolicy(Map map) throws NoSuchElementException, InterruptedException {
		EndorsementPageTest endo = new EndorsementPageTest();
		endo.clickPolicyNumberLink(map);
		
		wait.until(ExpectedConditions.visibilityOf(RenewalPageObjects.renewalsSidePanelButton));
		
		RenewalPageObjects.renewalsSidePanelButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Retrieving the Policy renewals')]")));
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateRenewalQuoteButton));
		
		RenewalPageObjects.CreateRenewalQuoteButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Creating Renewal Quote')]")));
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateQuoteProposalButton));
		
		
		RenewalPageObjects.CreateQuoteProposalButton.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(RenewalPageObjects.CreateQuoteProposalDlgBoxButton));
		RenewalPageObjects.CreateQuoteProposalDlgBoxButton.click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Creating Quote Proposal')]")));
		
		// to get the policy status
		Cancel_Reinstate_Test Cancel_Reinstate_Test = new Cancel_Reinstate_Test();
		String policyStatus = Cancel_Reinstate_Test.getPolicyStatus(map);
		
		if(policyStatus.equalsIgnoreCase("Offered")) {
			SummaryPageTest SummaryPageTest = new SummaryPageTest();
			SummaryPageTest.issuePolicy(map);
		}
		
	}
}
