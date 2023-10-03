package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuoteStatusObjects {

	@FindBy(xpath = "//*[@id=\"HeaderForm\"]/div[1]/div[1]/ul/li/table/tbody/tr/td[2]/span")
	public static WebElement quoteStatus;
	
	@FindBy(xpath="//*[@id=\"BusinessRuleTile:BusinessRuleform:HamburgerBusinssRuleDatatable\"]/div/table")
	public static WebElement businessRuleTable;
	

	@FindBy(xpath="//*[@id=\"BusinessRuleTile:BusinessRuleform:HamburgerBusinssRuleDatatable_paginator_bottom\"]/span[1]/a[2]")
	public static WebElement businessRuleTable2ndPage;
	
	@FindBy(xpath="//*[@id=\"BusinessRuleTile:BusinessRuleform:SaveBusinessRule\"]/span")
	public static WebElement saveStatus;
	
	@FindBy(xpath="//*[@id=\"CreateQuoteProposalButton\"]")
	public static WebElement createQuoteProposal;
	
	@FindBy(xpath="//*[@id=\"QuoteProposalForm:QuoteProposalButton\"]")
	public static WebElement createQuoteProposalBtn;
}
