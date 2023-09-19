package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SummaryPageObjects {

	public static WebDriver driver = null;
	
	@FindBy(xpath="//*[@id=\"HeaderForm:quoteHeaderPanelTest:HeaderBlock\"]/table/tbody/tr[5]/td[2]/label")
	public static WebElement billType;
	
	@FindBy(xpath="//*[@id=\"PayNowButton\"]/span")
	public static WebElement payNowForDirectBill;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:PaymentFormTile:ACDataTable:0:Object__Additional__Include\"]")
	public static WebElement ITPCheckbox;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:PaymentFormTile:Object__QuoteInsured__FName\"]")
	public static WebElement contactFirstName;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:PaymentFormTile:Object__QuoteInsured__LName\"]")
	public static WebElement contactLastName;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:PaymentFormTile:Object__Payment__CheckNumber\"]")
	public static WebElement chequeNo;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:PaymentFormTile:Object__Payment__PayeeFirstName\"]")
	public static WebElement payeeName;
	
	@FindBy(xpath="//*[@id=\"PaymentForm:NavigateToXpress\"]/span")
	public static WebElement payNowBtn;
	
	@FindBy(xpath="//*[@id=\"GotoPolicySummaryButton\"]/span")
	public static WebElement goToPolicySummary;

	@FindBy(xpath = "//*[@id=\"BindRequestButton\"]")
	public static WebElement bindRequest;

	@FindBy(xpath = "//*[@id=\"IssueBinderPanel\"]")
	public static WebElement issueBinderMenu;
	
	@FindBy(xpath="//*[@id=\"IssueBinderLink\"]")
	public static WebElement issueBinder;
	
	@FindBy(xpath="//*[@id=\"BindReqMessage\"]/label")
	public static WebElement ceaseBindMsg;
	
	@FindBy(xpath="//*[@id=\"IssueBinderTile:binder:Object__CeaseBind__Override\"]/div[1]/span")
	public static WebElement overrideDTC;

	@FindBy(xpath = "//*[@id=\"IssueBinderTile:binder:IssueBinderButton\"]/span")
	public static WebElement issueBinderCeaseBind;

	@FindBy(xpath = "//*[@id=\"IssueBinderLink\"]]")
	public static WebElement issueBinderNormalFlow;
	
	@FindBy(xpath = "//*[@id=\"IssueBinderTile:binder:Object__QuoteInsured__ContactFName\"]")
	public static WebElement contactFirstname;

	@FindBy(xpath = "//*[@id=\"IssueBinderTile:binder:Object__QuoteInsured__ContactLName\"]")
	public static WebElement contactLastname;

	@FindBy(xpath = "//*[@id=\'IssueBinderTile:binder:IssueBinderButton\']")
	public static WebElement issueBinderBtn;
	
	@FindBy(xpath="//*[@id=\"ScheduleOfFormTile:j_idt12035:ScheduleOfFormsDatatable_data\"]")
	public static WebElement scheduleOfForms;

	@FindBy(xpath = "//*[@id=\"PostBoundCheckForm:PostBoundCheckid:Object__Policy__Issuance__Application__Signed\"]/div[1]/span")
	public static WebElement postBoundCheck1;

	@FindBy(xpath = "//*[@id=\"PostBoundCheckForm:PostBoundCheckid:Object__Policy__Issuance__Application__Proof\"]/div[1]/span")
	public static WebElement postBoundCheck2;

	@FindBy(xpath = "//*[@id=\"PostBoundCheckForm:PostBoundCheckid:Object__Policy__Issuance__Application__Applicat\"]/div[1]/span")
	public static WebElement postBoundCheck3;

	@FindBy(xpath = "//*[@id=\"PostBoundCheckForm:PostBoundCheckid:SavePostBoundCheckList\"]/span")
	public static WebElement savePostBoundCheck;
	
	@FindBy(xpath = "//*[@id=\"IssuePolicyButton\"]/span")
	public static WebElement issuePolicyBtn;

	@FindBy(xpath = "//*[@id=\"HeaderForm:quoteHeaderPanelTest:quoteNumberValue\"]/span")
	public static WebElement policyNumber;
	
	
	
}