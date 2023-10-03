package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RenewalPageObjects {

	@FindBy(xpath="//label[text()='Renewals']/parent::td/following-sibling::td/button")
	public static WebElement renewalsSidePanelButton;
	
	// Retrieving the Policy renewals has to disappear
	
	@FindBy(xpath="//*[@id=\"createRenewalForm:CreateRenewalButton\"]")
	public static WebElement CreateRenewalQuoteButton;
	
	// Creating Renewal Quote has to disappear	
	
	@FindBy(xpath="//*[@id=\"CreateQuoteProposalButton\"]")
	public static WebElement CreateQuoteProposalButton;
	//Opening proposal dialog has to disappear
	
	@FindBy(xpath="//*[@id=\"QuoteProposalForm:QuoteProposalButton\"]")
	public static WebElement CreateQuoteProposalDlgBoxButton;
	// Creating Quote Proposal has to disappear
	
	// get the quote status... if equal to offer, proceed to summarypage test file


} 

