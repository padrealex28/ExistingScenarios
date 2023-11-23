package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PDF_Regeneration_Objects {

	@FindBy(xpath="//button[@id=\"DocumentsTile:CQPButton\"]") 
	public static WebElement CQProposal_button;	
	
	@FindBy(xpath="//*[contains(text(),\"Creating Quote Proposal\")]") 
	public static WebElement CreatingQuoteLoader;
	
}
