package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindQuoteObjects {

	@FindBy(xpath="//img[@id=\"PlForms:find_expand_display\"]") 
	public static WebElement FindMenu;	

	@FindBy(xpath="(//*[text()='By Quote'])[1]") 
	public static WebElement byQuoteMenu;
	
	@FindBy(id="FindQuoteForm:FindQuoteTile:Object__Quote__QuoteNumber") 
	public static WebElement byQuoteTextBox;
	
	@FindBy(id="FindQuoteForm:FindQuoteTile:Searchbutton") 
	public static WebElement searchButton;
	
	public static final String quoteLink ="//*[text()='%s']"; 

	@FindBy(xpath="//*[text()='UNDERWRITER WORKSHEET']") 
	public static WebElement UWsheetButton;
	

}
