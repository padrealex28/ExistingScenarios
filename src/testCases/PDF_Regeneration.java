package testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.FindQuoteObjects;
import pageObjects.PDF_Regeneration_Objects;
import pageObjects.QuoteStatusObjects;
import utilClass.CommonFunctions;

public class PDF_Regeneration extends CommonFunctions{

	public void regenerate_PDF(Map map) throws ClassNotFoundException, SQLException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		Logger logger = Logger.getLogger(PDF_Regeneration.class);
		Class.forName("com.mysql.cj.jdbc.Driver"); 
	    Connection connection = DriverManager.getConnection("jdbc:mysql://10.200.17.25:3700/?user=root","root","redhat"); 
	    Statement statement = connection.createStatement(); 
		PageFactory.initElements(driver, PDF_Regeneration_Objects.class);
		PageFactory.initElements(driver, QuoteStatusObjects.class);

	
		String QuoteStatus = QuoteStatusObjects.quoteStatus.getText();   
		if(QuoteStatus.equalsIgnoreCase("Offered")) {
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Clicking UW Sheet Button");
		FindQuoteObjects.UWsheetButton.click();
		wait.until(ExpectedConditions.visibilityOf(PDF_Regeneration_Objects.CQProposal_button));
		PDF_Regeneration_Objects.CQProposal_button.click();
		logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Clicking Create Quote Proposal Button");

		wait.until(ExpectedConditions.visibilityOf(PDF_Regeneration_Objects.CreatingQuoteLoader));	    		
		try {
             	wait.until(ExpectedConditions.invisibilityOf(PDF_Regeneration_Objects.CreatingQuoteLoader));
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Quote Created");
				statement.executeUpdate("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','Completed')");
				logger.info("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','Completed')");
			
		}
		catch(Exception e) {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Issue in Quote creation");
			statement.executeUpdate("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','Error')");
			logger.info("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','Error')");
			logger.error(e);
		}

				driver.quit();
	}
		else {
			logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+ " Issue in Quote Creation, Quote Status " +QuoteStatus);
			logger.info("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','"+QuoteStatus+"')");	
			statement.executeUpdate("INSERT INTO Allrisks_Regression.OneLondon_PDF_Regeneration Values ("+map.get("Test Data No").toString()+",'"+map.get("Quote Number").toString()+"','"+QuoteStatus+"')");
			driver.quit();
		}
	}
}
