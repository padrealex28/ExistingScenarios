package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cancel_Reinstate_PageObjects {

	
	@FindBy(xpath = "//button[@id=\"staticLeftPaneAccordian:CancelReinstateInfo\"]")
	public static WebElement Cancel_Reinstate_SidePanel;
	
	@FindBy(xpath = "//input[@id=\"CancelTile:cancelForm:Object__Quote__EffectiveDate__Display\"]")
	public static WebElement Cancel_Effective_Date;
	
	@FindBy(xpath = "//*[@id=\"CancelTile:cancelForm:Object__Plan__CancelType\"]")
	public static WebElement Cancel_Type;		
	
	@FindBy(xpath = "//input[@id=\"CancelTile:cancelForm:Object__Plan__IsPolicyFeeReturned:0\"]")
	public static WebElement Return_Policy_Fee_YES;
	
	@FindBy(xpath = "//input[@id=\"CancelTile:cancelForm:Object__Plan__IsPolicyFeeReturned:1\"]")
	public static WebElement Return_Policy_Fee_NO;
	
	@FindBy(xpath = "//select[@id=\"CancelTile:cancelForm:Object__Cancel__Reason\"]")
	public static WebElement Cancel_Reason;
	
	@FindBy(xpath = "//button[@id=\"CancelTile:cancelForm:ConfirmCancelButton\"]")
	public static WebElement Cancel_Policy_Button;	
	// invisibility of Saving the Cancellation Information
	
	public static final String policyStatus = "//td[a[text()='%s']]/following-sibling::td[3]/label";
	//validate Text in the xpath is canceled 
	
	@FindBy(xpath = "//select[@id=\"ReinstateTile:ReinstateForm:Object__ReinstateType\"]")
	public static WebElement Reinstate_Reason;
	
	@FindBy(xpath = "//button[@id=\"ReinstateTile:ReinstateForm:ConfirmReinstateButton\"]")
	public static WebElement Reinstate_Policy_Button;
	
	// invisibility of Issuing the Reinstate
	//validate Text in the xpath is issued //td[a[text()='%s']]/following-sibling::td[3]/label 
}
