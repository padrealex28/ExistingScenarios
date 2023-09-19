package testCases;

import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.SummaryPageObjects;
import utilClass.CommonFunctions;
import utilClass.RetryElements;

public class SummaryPageTest extends CommonFunctions {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row1;
	public static XSSFCell cell1, cell2;
	public static String firstName, lastName, payeeFirstName, payeeLastName, payeeName2;
	public static int chequeNumber;
	Logger logger = Logger.getLogger(SummaryPageTest.class);
	public void issuePolicy(Map map) throws NoSuchElementException, InterruptedException {
		

		PageFactory.initElements(driver, SummaryPageObjects.class);		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		try {

		Thread.sleep(10000);
		String policyBillType = SummaryPageObjects.billType.getText();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Policy Bill Type" +policyBillType);
		//Thread.sleep(10000);
				
		if (policyBillType.equalsIgnoreCase("DirectBill")) {
			
			try {				
			
			// Direct bill
				
				SummaryPageObjects.payNowForDirectBill.click();
				Thread.sleep(10000);
				if (!map.get("Occupancy Type").toString().equalsIgnoreCase("Vacant (including cosmetic/non-structural renovations)") 				
				|| !map.get("Occupancy Type").toString().equalsIgnoreCase("Builders Risk (Structural Renovations & Ground Up Construction)"))
			    {
				SummaryPageObjects.ITPCheckbox.click();
				Thread.sleep(3000);
				}
				
				
				try {
				RetryElements.Element_sendKeys(SummaryPageObjects.contactFirstName,map.get("First Name").toString());				
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Contact First Name"+map.get("First Name").toString());
				Thread.sleep(3000);
				RetryElements.Element_sendKeys(SummaryPageObjects.contactLastName,map.get("Last Name").toString());
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Contact First Name"+map.get("Last Name").toString());
				Thread.sleep(3000);
				}
				catch (Exception e) {
					logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Contact First name and Last Name NOT PRESENT",e);
					
				}
				RetryElements.Element_sendKeys(SummaryPageObjects.chequeNo,map.get("Cheque Number").toString());
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Cheque Number"+map.get("Cheque Number").toString());
				Thread.sleep(3000);
				RetryElements.Element_sendKeys(SummaryPageObjects.payeeName,map.get("Payee Name").toString());
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": Payee Name"+map.get("Payee Name").toString());
				Thread.sleep(3000);
				SummaryPageObjects.payNowBtn.click();
				Thread.sleep(20000);
				wait.until(ExpectedConditions.visibilityOf(SummaryPageObjects.goToPolicySummary));
				SummaryPageObjects.goToPolicySummary.click();
				Thread.sleep(5000);
				SummaryPageObjects.postBoundCheck1.click();
				Thread.sleep(3000);
				SummaryPageObjects.postBoundCheck2.click();
				Thread.sleep(3000);
				SummaryPageObjects.postBoundCheck3.click();
				Thread.sleep(3000);
				SummaryPageObjects.savePostBoundCheck.click();
				Thread.sleep(20000);
				SummaryPageObjects.issuePolicyBtn.click();
				Thread.sleep(20000);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
				
			} else if (policyBillType.equalsIgnoreCase("AgencyBill"))  {
			
			// code to execute when cease bind message is not displayed
			// Enter Contact first name
			SummaryPageObjects.bindRequest.click();	
			Thread.sleep(20000);
			
			SummaryPageObjects.issueBinderMenu.click();
			SummaryPageObjects.issueBinder.click();
			
			RetryElements.Element_sendKeys(SummaryPageObjects.contactFirstname, map.get("First Name").toString());			
			Thread.sleep(2000);
			// Enter Contact last name
			RetryElements.Element_sendKeys(SummaryPageObjects.contactLastname,map.get("Last Name").toString());
			Thread.sleep(2000);
			SummaryPageObjects.issueBinderBtn.click();
			Thread.sleep(20000);
			RetryElements.Element_click(SummaryPageObjects.postBoundCheck1);			
			Thread.sleep(2000);
			RetryElements.Element_click(SummaryPageObjects.postBoundCheck2);
			Thread.sleep(2000);
			RetryElements.Element_click(SummaryPageObjects.postBoundCheck3);
			Thread.sleep(2000);
			SummaryPageObjects.savePostBoundCheck.click();
			Thread.sleep(15000);
			SummaryPageObjects.issuePolicyBtn.click();
			Thread.sleep(10000);
		} 
		} catch (Exception nse) {

			nse.printStackTrace();
		}  

		if (!driver.findElements(By.xpath("//*[@id=\"BindReqMessage\"]/label")).isEmpty()
				&& SummaryPageObjects.ceaseBindMsg.isDisplayed() == true) {

			try {

				Thread.sleep(15000);
				// Enter Contact first name
				SummaryPageObjects.contactFirstname.sendKeys(map.get("First Name").toString());
				Thread.sleep(5000);
				// Enter Contact last name
				SummaryPageObjects.contactLastname.sendKeys(map.get("Last Name").toString());
				Thread.sleep(5000);
				// select Yes to override DTC
				SummaryPageObjects.overrideDTC.click();
				Thread.sleep(5000);
				// click Issue Binder button
				SummaryPageObjects.issueBinderCeaseBind.click();
				Thread.sleep(15000);
				// post bound check 1
				SummaryPageObjects.postBoundCheck1.click();
				Thread.sleep(3000);
				// post bound check 2
				SummaryPageObjects.postBoundCheck2.click();
				Thread.sleep(3000);
				// post bound check 3
				SummaryPageObjects.postBoundCheck3.click();
				Thread.sleep(3000);
				SummaryPageObjects.savePostBoundCheck.click();
				Thread.sleep(10000);
				// click Issue Policy button
				SummaryPageObjects.issuePolicyBtn.click();
				Thread.sleep(10000);
			} catch (Exception nse) {

				nse.printStackTrace();
			}
		} 

		/*
		 * List<WebElement> forms =
		 * SummaryPageObjects.scheduleOfForms.findElements(By.tagName("tr")); int
		 * formCount = forms.size(); System.out.println("No of rows are : " +
		 * formCount);
		 * 
		 * for (int form = 0; form < formCount; form++) { System.out.println(form); }
		 */
		try {

			String policyNo = SummaryPageObjects.policyNumber.getText();
			Thread.sleep(5000);
			logger.info(policyNo + " & this policy is created for " + map.get("Carrier").toString()
					+ " Carrier & "+map.get("Product").toString()+" product");
			Thread.sleep(4000);
			// fis.close();

		} catch (NoSuchElementException e1) {

			e1.printStackTrace();

		}
	}
}