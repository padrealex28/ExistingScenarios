package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageObjects {

	public static WebDriver driver = null;

	@FindBy(id = "loginForm:login_username")
	public static WebElement userName;
	@FindBy(id = "loginForm:login_password")
	public static WebElement password;
	@FindBy(xpath="//*[@id=\"loginForm\"]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/input[6]") 
	public static WebElement signInBtn;
	@FindBy(xpath="//*[@id=\"header\"]/tbody/tr/td[9]/table/tbody/tr/td[2]/a")
	public static WebElement logoutBtn;
	
}