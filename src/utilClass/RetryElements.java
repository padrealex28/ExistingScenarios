package utilClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class RetryElements {
	public static boolean Element_click(WebElement locator) {
		boolean outcome=false;
		int repeat =0;
		while (repeat <=5) {	
			try {	
				locator.click();
				outcome = true;
				break;
				}
			
			catch(Exception e) {
				System.out.println("exception occured during click for the element: "+locator+ e.getClass().getSimpleName());
				
				
			}
			repeat++;
		}
	return outcome;
	}
	
	public static boolean Element_sendKeys(WebElement locator,String value) {
		boolean outcome=false;
		int repeat =0;
		while (repeat <=5) {
			try {
				locator.sendKeys(value);
				outcome = true;
				break;
			}
			catch(Exception e) {
				System.out.println("exception occured during sendKeys for the element: " +locator+ "and value: "+value + e.getClass().getName());
				
			}
			repeat++;
		}
	return outcome;
	}
	
	public static boolean Element_select(WebElement locator,String value) {
		boolean outcome=false;
		int repeat =0;
		while (repeat <=5) {
			try {
				Select select = new Select(locator);
				select.selectByValue(value);
				outcome = true;
				break;
			}
			catch(Exception e) {
				System.out.println("exception occured during select for element: " +locator+ "and value: " +value );
				e.printStackTrace();
			}
			repeat++;
		}
	return outcome;
	}
	public static boolean Element_action(WebDriver driver,WebElement locator,String action,String value) {
		boolean outcome=false;
		int repeat =0;
		while (repeat <=5) {
			try {
				Actions action01 = new Actions(driver);
				action01.doubleClick(locator);
				outcome = true;
				break;
			}
			catch(Exception e) {
				System.out.println("exception occured during action for element: " +locator+ "and value: " +value );
				e.printStackTrace();
			}
			repeat++;
		}
	return outcome;
	}

	
	public static String Element_getText(List <WebElement> locator,int number) {
		boolean outcome=false;
		String value = null;
		int repeat =0;
		while (repeat <=5) {
			try {
				value = locator.get(number).getText();
				break;
			}
			catch(Exception e) {
				System.out.println("exception occured during action for element: " +locator);
				e.printStackTrace();
			}
			repeat++;
		
		}
		return value;
	
	}

}

