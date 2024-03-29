package testCases;

import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.Cancel_Reinstate_PageObjects;
import pageObjects.EndorsementPageObjects;
import utilClass.CommonFunctions;
import utilClass.RetryElements;

public class Cancel_Reinstate_Test extends CommonFunctions{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	Logger logger = Logger.getLogger(Cancel_Reinstate_Test.class);


	public void cancelPolicy(Map map) throws InterruptedException {
		
		PageFactory.initElements(driver, Cancel_Reinstate_PageObjects.class);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Inside Cancel Policy Method");	
		EndorsementPageTest endo = new EndorsementPageTest();
		endo.clickPolicyNumberLink(map);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Policy Number clicked , Clicking on Cancel/Reinstate side panel");		
		wait.until(ExpectedConditions.visibilityOf(Cancel_Reinstate_PageObjects.Cancel_Reinstate_SidePanel));
		RetryElements.Wait(3000);
		Cancel_Reinstate_PageObjects.Cancel_Reinstate_SidePanel.click();
			
		
		wait.until(ExpectedConditions.visibilityOf(Cancel_Reinstate_PageObjects.Cancel_Effective_Date));
		wait.until(ExpectedConditions.elementToBeClickable(Cancel_Reinstate_PageObjects.Cancel_Effective_Date));
		Cancel_Reinstate_PageObjects.Cancel_Effective_Date_Button.click();
		Select_EffectiveDate(map);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Clicked on Cancel/Reinstate Side panel, Entered Cancel Effective Date "+ map.get("Cancel Effective Date").toString());
		RetryElements.Element_select(Cancel_Reinstate_PageObjects.Cancel_Type,map.get("Cancel Type").toString());
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Selected Cancel Type "+map.get("Cancel Type").toString());
		
		if(map.get("Return Policy Fee").toString().equalsIgnoreCase("Yes")) 
		{
			RetryElements.Element_click(Cancel_Reinstate_PageObjects.Return_Policy_Fee_YES);
		}
		else 
		{
			RetryElements.Element_click(Cancel_Reinstate_PageObjects.Return_Policy_Fee_NO);
		}
		
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Selected Return Policy Fee "+map.get("Return Policy Fee").toString());
		wait.until(ExpectedConditions.visibilityOf(Cancel_Reinstate_PageObjects.Cancel_Reason));
		RetryElements.Element_select(Cancel_Reinstate_PageObjects.Cancel_Reason,map.get("Cancel Reason").toString());
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Entered Cancel Reason "+map.get("Cancel Reason").toString());

		wait.until(ExpectedConditions.elementToBeClickable(Cancel_Reinstate_PageObjects.Cancel_Policy_Button));
		Cancel_Reinstate_PageObjects.Cancel_Policy_Button.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Saving the Cancellation Information')]")));

		String Status_Of_The_POLICY = getPolicyStatus(map);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Policy CANCEL STATUS: "+Status_Of_The_POLICY);
		
		
	}
	
	public void reinstatePolicy(Map map) throws InterruptedException {
		PageFactory.initElements(driver, Cancel_Reinstate_PageObjects.class);
		EndorsementPageTest endo = new EndorsementPageTest();
		endo.clickPolicyNumberLink(map);
		String Status_Of_The_POLICY = getPolicyStatus(map);
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Inside Reinstate Method");

		if(Status_Of_The_POLICY.equalsIgnoreCase("Canceled")) 
		{
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Clicking Cancel/Reinstate side panel");
			RetryElements.Wait(3000);
			Cancel_Reinstate_PageObjects.Cancel_Reinstate_SidePanel.click();
			wait.until(ExpectedConditions.elementToBeClickable(Cancel_Reinstate_PageObjects.Reinstate_Policy_Button));
			RetryElements.Wait(3000);
		    RetryElements.Element_select(Cancel_Reinstate_PageObjects.Reinstate_Reason,map.get("Reinstate Reason").toString());		   
		   logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Selected Reinstate reason "+map.get("Reinstate Reason").toString());
		  
		   Cancel_Reinstate_PageObjects.Reinstate_Policy_Button.click();
		   logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Reinstating the Policy");
		   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Issuing the Reinstate')]")));		   
		}
		   else
		   {
			   logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Policy Status is NOT 'CANCELED'");
		   }
		   String policyStatus = getPolicyStatus(map);
		   if (!policyStatus.equalsIgnoreCase("Canceled")) {
			   logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Current Policy Status: "+policyStatus);
		   }
		}
		
	public void Select_EffectiveDate(Map map) throws InterruptedException {
	    RetryElements.Wait(5000);
		String[] MonthDateYear = map.get("Cancel Effective Date").toString().split("/");
		 for (String element : MonthDateYear) {
	         System.out.println("String" +element);
	     }
		WebElement effectiveDateYear = driver.findElement(By.className(Cancel_Reinstate_PageObjects.effectiveDate_YEAR));
		RetryElements.Element_select(effectiveDateYear, MonthDateYear[2]);
		
		
		WebElement effectiveDateMonth = driver.findElement(By.className(Cancel_Reinstate_PageObjects.effectiveDate_MONTH));
		effectiveDateMonth.click();
		int actualMonth = Integer.parseInt(MonthDateYear[0])-1;
		System.out.println(String.valueOf(actualMonth)+","+MonthDateYear[1]+","+MonthDateYear[2]);
		RetryElements.Element_select(effectiveDateMonth,String.valueOf(actualMonth));
		System.out.println("//td[@data-month='"+actualMonth+"' and @data-year='"+MonthDateYear[2]+"']/a[text()='"+MonthDateYear[1]+"']");
	    WebElement effectiveDate = driver.findElement(By.xpath("//td[@data-month='"+actualMonth+"' and @data-year='"+MonthDateYear[2]+"']/a[text()='"+MonthDateYear[1]+"']"));   
	    effectiveDate.click();
	}
	
	public String getPolicyStatus(Map map) {
		String policyStatus = Cancel_Reinstate_PageObjects.policyStatus;			
		String policyStatusXpath = String.format(policyStatus, map.get("Quote Number").toString());	
		WebElement Policy_Status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(policyStatusXpath)));
	    String Status_Of_The_POLICY = Policy_Status.getText();
	    return Status_Of_The_POLICY;
	}
}
