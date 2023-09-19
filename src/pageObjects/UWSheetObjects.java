package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UWSheetObjects {

	public static WebDriver driver = null;

	@FindBy(xpath = "//*[@id=\"PLHeaderTile:PlHeaderForm:findoption\"]")
	public static WebElement findMenu;
	
	@FindBy(xpath = "(//*[@class=\"ui-menuitem-link ui-corner-all PlFindMenu\"])[5]")
	public static WebElement findByQuote;

	@FindBy(xpath = "//*[@id=\"FindQuoteForm:FindQuoteTile:Object__Quote__QuoteNumber\"]")
	public static WebElement enterQuoteNo;

	@FindBy(xpath = "//*[@id=\"FindQuoteForm:QuoteSearchResultTile:quoteListDataTable:0:QuoteNavPanel\"]/a")
	public static WebElement quoteLink;
	
	@FindBy(xpath= "//*[contains(text(),'Total Premium')]/following-sibling::span")
	public static WebElement TotalPremium;

	@FindBy(xpath = "//*[@id=\"DocumentsTile:UWWorksheetViewButton\"]/span")
	public static WebElement UWSheet;
	
	@FindBy(xpath = "//*[@id=\"DocumentsTile:Worksheet\"]")
	public static WebElement tableData;
	
	@FindBy(xpath= "(//*[contains(text(),'2nd')])[1]/following-sibling::td[1]")
	public static WebElement ChartRate;
	
	@FindBy(id = "DocumentsTile:UW_WorksheetForm:Object__PlanFactor__MiscSurcharge_Factor")
	public static WebElement miscSurcharge;
	
	@FindBy(id="Object__Plan__DefaultPolicyFee")
	public static WebElement pFee;
	
	@FindBy(id="Object__Plan__InspectionFee")
	public static WebElement inspFee;
	
	@FindBy(id="Object__Plan__FilingFee")
	public static WebElement FilingFee;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:Object__Commission__Agent__DefaultCommission")
	public static WebElement DAC;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:Object__QuoteCommission__CommissionAdjustmentValue")
	public static WebElement CommissionAdjustment;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:Object__QuoteCommission__CommissionPercentage")
	public static WebElement AdjustedAgentCommission;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:BillingType")
	public static WebElement BillingType;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:Object__Plan__InspectionFee__IsFeeBasedOnRule:0")
	public static WebElement OverrideInspectionRuleYes;
	
	@FindBy(id="DocumentsTile:UW_WorksheetForm:Object__Plan__InspectionFee__IsFeeBasedOnRule:1")
	public static WebElement OverrideInspectionRuleNo;
	
	@FindBy(xpath = "//*[@id=\"DocumentsTile:UWWorksheetDialog\"]/div[1]/a")
	public static WebElement closeWorksheet;
	
	@FindBy (xpath="//*[@id='DocumentsTile:UW_WorksheetForm:TotalPolicyPremiumPanel']")
	public static WebElement PolicyPremium;
}