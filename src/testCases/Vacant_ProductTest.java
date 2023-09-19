package testCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import pageObjects.BRVacantPageObjects;
import utilClass.CommonFunctions;

public class Vacant_ProductTest extends CommonFunctions {

	static Logger logger = Logger.getLogger(BR_ProductTest.class);
	@SuppressWarnings("unlikely-arg-type")
	public static void getVacantProductData(Map map) throws Exception {
		

		PageFactory.initElements(driver, BRVacantPageObjects.class);

		try {

			Thread.sleep(15000);
			if (map.get("VA_Purchasedin12months").toString().equals("Yes")) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Purchasedin12months : Yes");
				BRVacantPageObjects.purchasedWithin12MonthsYes.click();
				Thread.sleep(15000);
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Purchase Price "+map.get("VA_Purchase Price").toString());
				BRVacantPageObjects.purchasePrice.sendKeys(map.get("VA_Purchase Price").toString());
				Thread.sleep(2000);
				BRVacantPageObjects.dateOfPurchase.click();
				Thread.sleep(3000);
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Purchase Date "+map.get("VA_Date of Purchase").toString());
				BRVacantPageObjects.dateOfPurchase.sendKeys(map.get("VA_Date of Purchase").toString());
				Thread.sleep(2000);
				BRVacantPageObjects.dateOfPurchase.sendKeys(Keys.TAB);
				
			} else if (map.get("VA_Purchasedin12months").toString().equals("No")) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Purchasedin12months : No");
				BRVacantPageObjects.purchasedWithin12MonthsNo.click();
				Thread.sleep(2000);
				BRVacantPageObjects.VA_Vacant_Date.click();
				Thread.sleep(3000);
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Vacant_Date "+map.get("VA_Vacant Date").toString());
				BRVacantPageObjects.VA_Vacant_Date.sendKeys(map.get("VA_Vacant Date").toString());
				Thread.sleep(2000);
				BRVacantPageObjects.VA_Vacant_Date.sendKeys(Keys.TAB);
			}
			

			BRVacantPageObjects.renovationDetailsNo.click();
			Thread.sleep(2000);
			BRVacantPageObjects.propertyDetailsYes.click();
			Thread.sleep(2000);

			if (map.get("VA_Property to be sold in next 12 months").toString().equals("Yes")) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Property to be sold in next 12 months : Yes");
				BRVacantPageObjects.sellPropertyYes.click();
			} else if (map.get("VA_Property to be sold in next 12 months").toString().equals("No")) {
				logger.info(Thread.currentThread().getStackTrace()[1].getLineNumber()+": VA_Property to be sold in next 12 months : No");
				BRVacantPageObjects.sellPropertyNo.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}