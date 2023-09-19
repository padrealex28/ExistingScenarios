package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.UWSheetObjects;
import utilClass.CommonFunctions;
import uwSheet_DB_Entry.*;

public class UWSheetTest extends CommonFunctions {
    
	Logger logger = Logger.getLogger(UWSheetTest.class);
	public void getUWSheetData(Map map) throws Exception {
		
		  PageFactory.initElements(driver, UWSheetObjects.class); WebDriverWait wait =
		  new WebDriverWait(driver, Duration.ofSeconds(25));
		  wait.until(ExpectedConditions.elementToBeClickable(UWSheetObjects.UWSheet));
		  UWSheetObjects.UWSheet.click();
		 
		

	  if (map.get("Carrier").toString().toLowerCase().contains("scottsdale") ||  map.get("Carrier").toString().toLowerCase().contains("nationwide"))
	  {		  
		  Scottsdale_UWSheet_DB_Entry Scottsdale_UWSheet_DB_Entry = new Scottsdale_UWSheet_DB_Entry();
		  Scottsdale_UWSheet_DB_Entry.scottsDale_UWSheet(map);
	  }
	  else if(map.get("Carrier").toString().toLowerCase().contains("certain under") ) 
			
	  {	  
		  OneLondon_UWSheet_DB_Entry OneLondon_UWSheet_DB_Entry = new OneLondon_UWSheet_DB_Entry();
		  OneLondon_UWSheet_DB_Entry.oneLondon_UWSheet(map);
	  }
	  	  
	  else if((map.get("Carrier").toString().toLowerCase().contains("evanston") || map.get("Carrier").toString().toLowerCase().contains("markel") ))
	  {		 
		  Evanston_UWSheet_DB_Entry Evanston_UWSheet_DB_Entry = new Evanston_UWSheet_DB_Entry();
		  Evanston_UWSheet_DB_Entry.Evanston_UWSheet(map);
	  }
	  else if(map.get("Carrier").toString().toLowerCase().contains("londoncondo")) {
		  
		  LondonCondo_UWSheet_DB_Entry LondonCondo_UWSheet_DB_Entry = new LondonCondo_UWSheet_DB_Entry();
		  LondonCondo_UWSheet_DB_Entry.LondonCondo_UWsheet(map);
	  }
	  else if(map.get("Carrier").toString().toLowerCase().contains("great") || map.get("Carrier").toString().toLowerCase().contains("munich")){
		  GreatLakes_UWSheet_DB_Entry GreatLakes_UWSheet_DB_Entry = new GreatLakes_UWSheet_DB_Entry();
		  GreatLakes_UWSheet_DB_Entry.GreatLakes_UWsheet(map);
	  }
	  else if (map.get("Carrier").toString().toLowerCase().contains("canopius") || map.get("Carrier").toString().toLowerCase().contains("1153")){
		  Canopius_UK_UWSheet_DB_Entry Canopius_UK_UWSheet_DB_Entry = new Canopius_UK_UWSheet_DB_Entry();
		  Canopius_UK_UWSheet_DB_Entry.CanopiusUK_UWsheet(map);		  
		  logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Moved to Canopius UK UW sheet");

	  }		

	}
}