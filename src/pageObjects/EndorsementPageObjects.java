package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EndorsementPageObjects {
	
	@FindBy(xpath="//*[@id=\"HeaderForm:quoteHeaderPanelTest:HeaderBlock\"]/table/tbody/tr[5]/td[2]/label")
	public static WebElement billType;

	@FindBy(xpath = "//button[@id=\"staticLeftPaneAccordian:EndorsementListInfo\"]")
	public static WebElement EndoresementSidePanelButton;
	
	@FindBy(xpath = "//button[@id=\"EndorsementForm:AddEndorsementButton\"]")
	public static WebElement AddEndorsementButton;
	
	@FindBy(xpath = "//button[@id=\"EndorsementForm:ProceedToEndorsementButton\"]")
	public static WebElement ProceedEndorsementButton;
	//Wait for  Adding New Endorsement alert message to disappear after clicking this button
	
	@FindBy(xpath = "//button[@id=\"insuredTile:InsuredFormEquifax:EndoInsuredCheckButton\"]")
	public static WebElement SaveInsuredButton;
	
	@FindBy(xpath = "//button[@id=\"IssuePanelNavigate\"]")
	public static WebElement IssueEndorsementinNavigationPanel;
	
	@FindBy(xpath = "//button[@id=\"EndoContinuetoLocationsButton\"]")
	public static WebElement ContinueToLocationButton;
	//Wait for  proceeding to location alert message to disappear after clicking this button
	
	@FindBy(xpath = "//button[@id=\"locations:PLMLContinueToUWButton\"]")
	public static WebElement ContinueToUWbutton;
	//Wait for  proceeding to Underwriting details alert message to disappear after clicking this button
		
	public static final String policyLink = "//td[a[text()=\"%s\"]]/following-sibling::td/a";
	
	@FindBy(xpath = "//button[@id=\"AdditionalButton:ContinueToCS\"]")
	public static WebElement ContinueToCSpage;
	
	@FindBy(xpath = "//button[@id=\"EndoContinueToCoverage\"]")
	public static WebElement ContinueToCovButton;
	//LOADS CARRIER SELECTION PAGE
	//Wait for  proceeding to coverages details alert message to disappear after clicking this button
	
	@FindBy(xpath = "//button[@id=\"CoverageSave\"]")
	public static WebElement ContinueToOptionalFormsButton;
	//wait for Saving Coverage Information to disappear
	
	@FindBy(xpath = "//button[@id=\"ContinueToSummaryButton\"]")
	public static WebElement ContinueToSummaryButton;
	
	@FindBy(xpath = "//select[@id=\"CompareTile:IssueEndorsementForm:Object__Attachment__EndoType\"]")
	public static WebElement TypeOFEndorsement;
	
	@FindBy(xpath = "//textarea[@id=\"CompareTile:IssueEndorsementForm:Object__Endoresement__Description\"]")
	public static WebElement EndorsementNotes;
		
	@FindBy(xpath = "//*[contains(text(),'Endorsement Notes')]")
	public static WebElement Endorsement_Notes_Text;
	
	@FindBy(xpath = "(//*[contains(text(),\"Company\")]/following::label)[1]")
	public static WebElement Carrier_Name;
	
	@FindBy(xpath = "//*[contains(text(),\"Quote Number\")]")
	public static WebElement QuoteNumber;	
	
	@FindBy(xpath = "//*[contains(text(),'BUSINESS RULES')]")
	public static WebElement businessRulesERROR;	
	
	@FindBy(xpath = "//*[contains(text(),'Issue Endorsement')]")
	public static WebElement IssueEndorsementButton;	
     //wait for  Issuing Endorsement to disappear
	// wait till //*[contains(text(),'Agent_Endorsement')]  to appear
	public static final String pdf_file_path = "D:\\\\PDF DOCUMENTS\\\\%s\\\\Policy PDF.pdf";
	
	@FindBy(xpath="//span[contains(text(),\"Total Premium\")]/following::span[1]")
	public static WebElement TotalPremium;
	
	
	


}
