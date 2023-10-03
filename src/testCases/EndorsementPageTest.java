package testCases;

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.EndorsementPageObjects;
import pageObjects.FindQuoteObjects;
import pageObjects.QuoteStatusObjects;
import utilClass.CommonFunctions;
import utilClass.RetryElements;

public class EndorsementPageTest extends CommonFunctions {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	Logger logger = Logger.getLogger(EndorsementPageTest.class);
public void issueEndorsement(Map map) throws InterruptedException, ClassNotFoundException, SQLException {
	PageFactory.initElements(driver, EndorsementPageObjects.class);
		
	String billType = EndorsementPageObjects.billType.getText();
	clickPolicyNumberLink(map);
	
//	Set<String> windowHandles = driver.getWindowHandles();
//	ArrayList<String> tabs =
	WebElement policyPDF = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\"Policy PDF\")]")));	
	RetryElements.Wait(4000);
	policyPDF.click();
	
	String pdf_file_path = String.format(EndorsementPageObjects.pdf_file_path, map.get("Test Data No").toString());
	PDF_Checker checkText = new PDF_Checker();
	checkText.searchText(pdf_file_path,map);
				
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Policy Number Clicked and Moving to Endorsements Panel");
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.EndoresementSidePanelButton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.EndoresementSidePanelButton));
	EndorsementPageObjects.EndoresementSidePanelButton.click();
	
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.AddEndorsementButton));
	EndorsementPageObjects.AddEndorsementButton.click();
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Clicked On Add Endorsements");
	
	EndorsementPageObjects.ProceedEndorsementButton.click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Adding New Endorsement')]")));	
	
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.SaveInsuredButton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.SaveInsuredButton));
	EndorsementPageObjects.SaveInsuredButton.click();

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Saving Insured Information')]")));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToLocationButton));
	EndorsementPageObjects.ContinueToLocationButton.click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'proceeding to location')]")));
    logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Saving Insured Information and Moving to Location Page");   
    
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.ContinueToUWbutton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToUWbutton));
	EndorsementPageObjects.ContinueToUWbutton.click();
    logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Location Page Saved and Moving to UW page");
	
    
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'proceeding to Underwriting')]")));
	
	if(billType.equalsIgnoreCase("DirectBill")) 
	{
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Inside "+billType+" if condition");
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.ContinueToCovButton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToCovButton));
	EndorsementPageObjects.ContinueToCovButton.click();
    logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Saving UW Information and Moving to Carrier Selection Page");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Proceeding to Coverages')]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Proceeding to Coverages')]")));;
	}
	else if(billType.equalsIgnoreCase("AgencyBill"))
	{
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Inside "+billType+" if condition");
		wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.ContinueToCSpage));
		wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToCSpage));
		EndorsementPageObjects.ContinueToCSpage.click();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Saving UW Information and Moving to Carrier Selection Page");		 
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Saving Underwriting Details')]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Saving Underwriting Details')]")));;
	}
	
	
	//wait to load the carrier selection page
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Optimizing our pricing')]")));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Optimizing our pricing')]")));
	
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.ContinueToOptionalFormsButton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToOptionalFormsButton));
	EndorsementPageObjects.ContinueToOptionalFormsButton.click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Saving Coverage Information')]")));
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Saving Carrier Selection Page info and Moving to Optional Forms Page");
	
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.ContinueToSummaryButton));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.ContinueToSummaryButton));
	EndorsementPageObjects.ContinueToSummaryButton.click();
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Saving Optional Forms Info and Moving to Summary Page");
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Proceeding to')]")));
	
	wait.until(ExpectedConditions.visibilityOf(EndorsementPageObjects.TypeOFEndorsement));
	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.TypeOFEndorsement));
	Select select = new Select(EndorsementPageObjects.TypeOFEndorsement);
	select.selectByVisibleText(map.get("Type of Endorsement").toString());
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Type of Endorsement Selected");

	wait.until(ExpectedConditions.elementToBeClickable(EndorsementPageObjects.EndorsementNotes));
	RetryElements.Element_sendKeys(EndorsementPageObjects.EndorsementNotes, map.get("Endorsement Notes").toString());
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Endorsement Notes Entered");
	
	EndorsementPageObjects.IssueEndorsementButton.click();
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Issuing Endorsement..");
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Issuing Endorsement')]")));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Endorsement#')]")));
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": ENDORSEMENT ISSUED");
}



public void clickPolicyNumberLink(Map map) {
	logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Trying to Click Policy Number");
	String policyNum = EndorsementPageObjects.policyLink;			
	String policyNumXpath = String.format(policyNum, map.get("Quote Number").toString());	
	WebElement policyNumLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(policyNumXpath)));
	wait.until(ExpectedConditions.elementToBeClickable(policyNumLink));
	policyNumLink.click();
}
}
